/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.controllers;

import static cz.muni.fi.pa165.machrent.controllers.RevisionController.log;
import cz.muni.fi.pa165.machrent.dto.MachineDto;
import cz.muni.fi.pa165.machrent.dto.RentalCreateDto;
import cz.muni.fi.pa165.machrent.dto.RentalDto;
import cz.muni.fi.pa165.machrent.dto.RentalUpdateDto;
//import cz.muni.fi.pa165.machrent.dto.RentalUpdateDto;
import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.enums.RentalUserRole;
import cz.muni.fi.pa165.machrent.exceptions.RentalServiceException;
import cz.muni.fi.pa165.machrent.facade.MachineFacade;
import cz.muni.fi.pa165.machrent.facade.RentalFacade;
import cz.muni.fi.pa165.machrent.facade.RentalUserFacade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Peter Benus
 */
@Controller
@RequestMapping("/admin/rental")
public class RentalController {

    @Autowired
    private RentalUserFacade rentalUserFacade;

    @Autowired
    private MachineFacade machineFacade;

    @Autowired
    private RentalFacade rentalFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listRentals(Model model, HttpServletRequest req) {
        log.error("request: GET /admin/rental/list");
        HttpSession session = req.getSession(true);
        RentalUserDto rentalUser = (RentalUserDto) session.getAttribute("authUser");
        //if (rentalUserFacade.isUserAdmin(rentalUser.getId())) {
            model.addAttribute("rental", rentalFacade.findAllRentals());
        //}
        return "admin/rental/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String showRental(
            @PathVariable("id") long id,
            HttpServletRequest req,
            RedirectAttributes redirectAttributes,
            Model model) {

        log.error("request: GET /admin/rental/view/" + id);
        RentalDto r;
        try{
            r = rentalFacade.findRentalWithId(id);
        }
        catch (RentalServiceException e){
            redirectAttributes.addFlashAttribute("alert_warning", "Unknown rental");
            return "redirect:/admin/rental/list";
        }
        model.addAttribute("rental", r);
        return "/admin/rental/view";
    }

    @RequestMapping(value = "/deleteRental/{id}", method = RequestMethod.POST)
    public String deleteRental(
            @PathVariable("id") long id,
            HttpServletRequest req,
            RedirectAttributes redirectAttributes,
            Model model) {

        log.error("request: GET /admin/rental/deleteRental/" + id);
        RentalDto r = rentalFacade.findRentalWithId(id);
        if (r == null) {
            redirectAttributes.addFlashAttribute("alert_warning", "Unknown rental.");
            return "redirect:/admin/rental/list";
        }
        try {
            rentalFacade.deleteRental(id);
            redirectAttributes.addFlashAttribute("alert_success", "Rental deleted.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("alert_danger", "Deletion of specified rental failed.");
        }
        return "redirect:/admin/rental/list";
    }

    @RequestMapping(value = "/newRental", method = RequestMethod.GET)
    public String newRental(Model model) {
        log.error("newRental()");
        model.addAttribute("newRental", new RentalCreateDto());
        return "admin/rental/newRental";
    }

    @RequestMapping(value = "/updateRental/{id}", method = RequestMethod.GET)
    public String updateRental(
            @PathVariable long id,
            Model model) {

        RentalDto updateRental = rentalFacade.findRentalWithId(id);
        if (updateRental == null) {
            return "redirect:/admin/rental/list";
        }
        
        List<String> machineList = new ArrayList();
        List<MachineDto> machines = machineFacade.findAllMachines();
        for (int i = 0; i < machines.size(); i++){
             machineList.add(machines.get(i).getName() + " (id: " + machines.get(i).getId() + ")");
        }
        
        List<String> customerList = new ArrayList();
        Collection<RentalUserDto> customers = rentalUserFacade.getAllUsers();
        for (RentalUserDto tmpCustomer : customers) {
            if (tmpCustomer.getRoles().contains(RentalUserRole.CUSTOMER)){
                customerList.add(tmpCustomer.getUsername() + " (id: " + tmpCustomer.getId() + ")");
            }
        }
        model.addAttribute("customerList", customerList);
        model.addAttribute("machineList", machineList);
        model.addAttribute("updateRental", updateRental);
        return "admin/rental/updateRental";
    }

    @RequestMapping(value = "/createRental", method = RequestMethod.POST)
    public String createRental(@Valid @ModelAttribute("rentalCreate") RentalCreateDto formBean,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder) {

        log.error("createRental(rentalCreate={})", formBean);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.error("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.error("FieldError: {}", fe);
            }
            return "/admin/rental/newRental";
        }

        Long id = rentalFacade.createRental(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Rental with " + id + " was created");
        return "redirect:" + uriBuilder.path("/admin/rental/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/updatingRental", method = RequestMethod.POST)
    public String updatingRental(@Valid @ModelAttribute("rentalUpdate") RentalUpdateDto formBean,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder) {

        log.error("updating(rentalUpdate={})", formBean);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.error("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.error("FieldError: {}", fe);
            }
            return "/admin/rental/list";
        }

        rentalFacade.updateRental(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Rental with " + formBean.getId() + " was updated");
        return "redirect:" + uriBuilder.path("/admin/rental/view/{id}").buildAndExpand(formBean.getId()).encode().toUriString();
    }
}