package cz.muni.fi.pa165.machrent.controllers;

import cz.muni.fi.pa165.machrent.SampleDataLoadingFacadeImpl;
import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.facade.RentalUserFacade;
import cz.muni.fi.pa165.machrent.validators.RentalUserDtoValidator;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * RentalUser controller.
 * 
 * @author  Josef Plch
 * @since   2016-12-16
 * @version 2016-12-16
 */
@Controller
@RequestMapping ("/admin/rental_user")
public class RentalUserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private RentalUserFacade rentalUserFacade;

    @RequestMapping (value = "/new", method = RequestMethod.GET)
    public String createByGet (Model model) {
        LOGGER.info ("new()");
        
        model.addAttribute ("rentalUserCreate", new RentalUserDto ());
        return ("admin/rental_user/new");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createByPost (
        @Valid @ModelAttribute("rentalUserCreate") RentalUserDto formBean,
        @ModelAttribute("newPassword") String newPassword,
        BindingResult bindingResult,
        Model model,
        RedirectAttributes redirectAttributes,
        UriComponentsBuilder uriBuilder
    ) {
        LOGGER.info ("create(rentalUserCreate={})", formBean);
        
        String result;
        if (bindingResult.hasErrors ()) {
            for (ObjectError error : bindingResult.getGlobalErrors ()) {
                LOGGER.error ("ObjectError: {}", error);
            }
            for (FieldError error : bindingResult.getFieldErrors ()) {
                model.addAttribute (error.getField () + "_error", true);
                LOGGER.error ("FieldError: {}", error);
            }
            result = "/admin/rental_user/new";
        }
        else {
            Long id = rentalUserFacade.registerUser (formBean, newPassword);
            redirectAttributes.addFlashAttribute (
                "alert_success",
                "RentalUser " + formBean.getUsername () + " (id = " + id + ") was created."
            );
            result = "redirect:" + uriBuilder.path ("/admin/rental_user/view/{id}").buildAndExpand(id).encode().toUriString();
        }
        return result;
    }

    @RequestMapping (value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteByPost (
        @PathVariable long id,
        Model model,
        UriComponentsBuilder uriBuilder,
        RedirectAttributes redirectAttributes
    ) {
        LOGGER.info ("delete({})", id);
        
        RentalUserDto rentalUser = rentalUserFacade.findUserById (id);
        rentalUserFacade.deleteUser (id);
        redirectAttributes.addFlashAttribute (
            "alert_success",
            "RentalUser " + rentalUser.getUsername () + "(id = " + id + ") was deleted."
        );
        return ("redirect:" + uriBuilder.path ("/admin/rental_user/list").toUriString ());
    }

    @InitBinder
    protected void initBinder (WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat ("dd.MM.yyyy");
        CustomDateEditor editor = new CustomDateEditor (dateFormat, true);
        binder.registerCustomEditor (Date.class, editor);
        if (binder.getTarget () instanceof RentalUserDto) {
            RentalUserDtoValidator validator = new RentalUserDtoValidator ();
            binder.addValidators (validator);
        }
    }

    @RequestMapping (value = "/list", method = RequestMethod.GET)
    public String listOfUsers (Model model, HttpServletRequest request) {
        LOGGER.info ("request: GET /admin/rental_user/list");
        
        HttpSession session = request.getSession (true);
        RentalUserDto user = (RentalUserDto) session.getAttribute ("authUser");
        if (rentalUserFacade.isUserAdmin (user.getId ())) {
            model.addAttribute ("rentalUsers", rentalUserFacade.getAllUsers ());
        }
        
        return ("/admin/rental_user/list");
    }

    @RequestMapping (value = "/view/{id}", method = RequestMethod.GET)
    public String findById (@PathVariable("id") long id, Model model) {
        LOGGER.info ("request: GET /admin/rental_user/view/" + id);
        
        String result;
        RentalUserDto rentalUser = rentalUserFacade.findUserById (id);
        if (rentalUser == null) {
            result = "redirect:/index";
        }
        else {
            // TODO
            model.addAttribute ("rentalUser", rentalUser);
            result = ("/admin/rental_user/view");
        }
        
        return result;
    }

    @RequestMapping (value = "/update/{id}", method = RequestMethod.GET)
    public String updateByGet (@PathVariable long id, Model model) {
        RentalUserDto updateRentalUser = rentalUserFacade.findUserById (id);
        String result;
        if (updateRentalUser == null) {
            result = "redirect:/admin/rental_user/list";
        }
        else {
            model.addAttribute ("rentalUserFacade", rentalUserFacade);
            result = "admin/rental_user/update";
        }
        return result;
    }

    @RequestMapping (value = "/updating", method = RequestMethod.POST)
    public String updateByPost (
        @Valid @ModelAttribute("rentalUserUpdate") RentalUserDto formBean,
        BindingResult bindingResult,
        Model model,
        RedirectAttributes redirectAttributes,
        UriComponentsBuilder uriBuilder
    ) {
        LOGGER.info ("update(rentalUserUpdate={})", formBean);
        
        String result;
        if (bindingResult.hasErrors ()) {
            for (ObjectError error : bindingResult.getGlobalErrors ()) {
                LOGGER.error ("ObjectError: {}", error);
            }
            for (FieldError error : bindingResult.getFieldErrors ()) {
                model.addAttribute(error.getField() + "_error", true);
                LOGGER.error ("FieldError: {}", error);
            }
            result = "/admin/rental_user/update";
        }
        else {
            rentalUserFacade.updateUser (formBean);

            redirectAttributes.addFlashAttribute (
                "alert_success",
                "RentalUser " + formBean.getUsername () + " (id = " + formBean.getId () + ") was updated."
            );
            result = "redirect:" + uriBuilder.path("/admin/rental_user/view/{id}").buildAndExpand(formBean.getId()).encode().toUriString();
        }
        return result;
    }
}
