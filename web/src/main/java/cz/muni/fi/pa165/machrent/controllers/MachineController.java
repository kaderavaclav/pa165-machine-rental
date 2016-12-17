package cz.muni.fi.pa165.machrent.controllers;

import cz.muni.fi.pa165.machrent.SampleDataLoadingFacadeImpl;
import cz.muni.fi.pa165.machrent.dto.MachineCreateDto;
import cz.muni.fi.pa165.machrent.dto.MachineDto;
import cz.muni.fi.pa165.machrent.dto.MachineUpdateDto;
import cz.muni.fi.pa165.machrent.exceptions.MachineServiceException;
import cz.muni.fi.pa165.machrent.facade.MachineFacade;
import cz.muni.fi.pa165.machrent.validators.MachineCreateDtoValidator;
import cz.muni.fi.pa165.machrent.validators.MachineUpdateDtoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by vaclav.kadera on 16-Dec-16.
 */
@Controller
@RequestMapping("admin/machine")
public class MachineController {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private MachineFacade machineFacade;


    @InitBinder
    protected void initBinder(WebDataBinder binder) {

        if (binder.getTarget() instanceof MachineCreateDto) {
            MachineCreateDtoValidator validator = new MachineCreateDtoValidator();
            validator.setMachineFacade(machineFacade);
            binder.addValidators(validator);
        }
        if (binder.getTarget() instanceof MachineUpdateDto){
            MachineUpdateDtoValidator validator = new MachineUpdateDtoValidator();
            validator.setMachineFacade(machineFacade);
            binder.addValidators(validator);
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listMachines(Model model, HttpServletRequest req) {
        log.error("request: GET /admin/machine/list");
        model.addAttribute("machines", machineFacade.findAllMachines());
        return "/admin/machine/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String create(Model model){
        log.error("new()");
        model.addAttribute("newMachine", new MachineCreateDto());
        return "/admin/machine/new";
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createMachine(@Valid
            @ModelAttribute("newMachine") MachineCreateDto formBean, BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder) {

        log.debug("create(machineCreate={})", formBean);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.error("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.error("FieldError: {}", fe);
            }
            return "/admin/machine/new";
        }

        try {
            Long id = machineFacade.createMachine(formBean);
            redirectAttributes.addFlashAttribute("alert_success", "Machine was successfully created. ID: " + id);
            return "redirect:" + uriBuilder.path("/admin/machine/list").build().encode().toUriString();

        } catch (MachineServiceException e) {

            redirectAttributes.addFlashAttribute("alert_danger", "Attempt to create machine failed: " + e.getMessage());
        }
        return "redirect:" + uriBuilder.path("/admin/machine/list").build().encode().toUriString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") long id,
                                   Model model,
                                   UriComponentsBuilder uriBuilder) {

        log.error("updateMachine(id={})", id);

        MachineDto m = machineFacade.findById(id);
        MachineUpdateDto updateMachine = new MachineUpdateDto();

        updateMachine.setDescription(m.getDescription());
        updateMachine.setName(m.getName());
        updateMachine.setId(id);


        model.addAttribute("updateMachine", updateMachine);
        return "/admin/machine/update";
    }

    @RequestMapping(value = "/updateMachine", method = RequestMethod.POST)
    public String updateMachine(@Valid @ModelAttribute("updateMachine") MachineUpdateDto formBean,
                                  BindingResult bindingResult,
                                  Model model,
                                  RedirectAttributes redirectAttributes,
                                  UriComponentsBuilder uriBuilder) {

        log.error("update(machineUpdate={})", formBean);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.error("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.error("FieldError: {}", fe);
            }
            return "/admin/machine/update";
        }

        machineFacade.updateMachine(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Machine was successfully updated.");
        return "redirect:" + uriBuilder.path("/admin/machine/list").build().encode().toUriString();
    }




    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteMachine(
            @PathVariable("id") long id,
            HttpServletRequest req,
            RedirectAttributes redirectAttributes,
            Model model) {

        log.error("request: GET /admin/machine/delete/" + id);
        MachineDto m = machineFacade.findById(id);

        if (m == null) {
            redirectAttributes.addFlashAttribute("alert_warning", "Machine not found.");
            return "redirect:/admin/machine/list";
        }

        try {
            machineFacade.deleteMachine(id);
            redirectAttributes.addFlashAttribute("alert_success", "Machine successfully deleted.");

        } catch(Exception e) {

            redirectAttributes.addFlashAttribute("alert_danger", "Attempt to delete machine failed. Machine is used in Revisions or Rentals.");
        }
        return "redirect:/admin/machine/list";
    }


}
