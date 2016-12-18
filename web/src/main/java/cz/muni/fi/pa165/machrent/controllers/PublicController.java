package cz.muni.fi.pa165.machrent.controllers;

import cz.muni.fi.pa165.machrent.SampleDataLoadingFacadeImpl;
import cz.muni.fi.pa165.machrent.dto.RentalDto;
import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.facade.MachineFacade;
import cz.muni.fi.pa165.machrent.facade.RentalFacade;
import cz.muni.fi.pa165.machrent.facade.RentalUserFacade;
import cz.muni.fi.pa165.machrent.validators.RentalUserDtoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by vaclav.kadera on 17-Dec-16.
 */
@Controller
@RequestMapping("/public")
public class PublicController {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private MachineFacade machineFacade;

    @Autowired
    private RentalFacade rentalFacade;

    @Autowired
    private RentalUserFacade rentalUserFacade;

    @InitBinder
    protected void initBinder (WebDataBinder binder) {
        if (binder.getTarget () instanceof RentalUserDto) {
            RentalUserDtoValidator validator = new RentalUserDtoValidator ();
            binder.addValidators (validator);
        }
    }

    @RequestMapping(value = "/machineList", method = RequestMethod.GET)
    public String machineList(Model model, HttpServletRequest req) {
        log.error("request: GET /public/machineList");
        model.addAttribute("machines", machineFacade.findAllMachines());
        return "/public/machineList";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model, HttpServletRequest req) {
        log.error("request: GET /public/dashboard");
        return "/public/dashboard";
    }

    @RequestMapping(value = "/profileView", method = RequestMethod.GET)
    public String profileView(Model model, HttpServletRequest req) {
        log.error("request: GET /public/profileView");
        return "/public/profileView";
    }

    @RequestMapping(value = "/rentalList", method = RequestMethod.GET)
    public String rentalList(Model model, HttpServletRequest req) {
        log.error("request: GET /public/rentalList");
        HttpSession session = req.getSession(true);
        RentalUserDto authUser =  (RentalUserDto) session.getAttribute("authUser");
        List<RentalDto> rentals = rentalFacade.findAllByCustomerId(authUser.getId());
        model.addAttribute("rentals", rentals);
        return "/public/rentalList";
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.GET)
    public String updateProfile(Model model,
                         UriComponentsBuilder uriBuilder,
                         HttpServletRequest req) {

        HttpSession session = req.getSession(true);
        RentalUserDto authUser =  (RentalUserDto) session.getAttribute("authUser");

        log.error("updateProfile(id={})", authUser.getId());

        model.addAttribute("authUser", authUser);
        return "/public/updateProfile";
    }

    @RequestMapping(value = "/updatingProfile", method = RequestMethod.POST)
    public String updateMachine(@Valid @ModelAttribute("authUser") RentalUserDto formBean,
                                BindingResult bindingResult,
                                Model model,
                                RedirectAttributes redirectAttributes,
                                UriComponentsBuilder uriBuilder) {

        log.error("update(profileUpdate={})", formBean);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.error("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.error("FieldError: {}", fe);
            }
            return "/public/updateProfile";
        }

        rentalUserFacade.updateUser(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Your profile was successfully updated.");
        return "redirect:" + uriBuilder.path("/public/profileView").build().encode().toUriString();
    }
}