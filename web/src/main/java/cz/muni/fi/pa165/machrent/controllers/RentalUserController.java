package cz.muni.fi.pa165.machrent.controllers;

import cz.muni.fi.pa165.machrent.SampleDataLoadingFacadeImpl;
import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.enums.LegalPersonality;
import cz.muni.fi.pa165.machrent.enums.RentalUserRole;
import cz.muni.fi.pa165.machrent.facade.RentalUserFacade;
import cz.muni.fi.pa165.machrent.validators.RentalUserDtoValidator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
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
 * @version 2017-01-08
 */
@Controller
@RequestMapping ("/admin/rentalUser")
public class RentalUserController {
    private final static Logger LOGGER = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private RentalUserFacade rentalUserFacade;

    @RequestMapping (value = "/new", method = RequestMethod.GET)
    public String createPrepare (Model model) {
        LOGGER.info ("new()");
        
        model.addAttribute ("rentalUserCreate", new RentalUserDto ());
        
        Map <LegalPersonality, String> legalPersonalityList = new LinkedHashMap <> ();
        for (LegalPersonality legalPersonality : LegalPersonality.values ()) {
            legalPersonalityList.put (legalPersonality, legalPersonality.toString ());
        }
        model.addAttribute ("legalPersonalityList", legalPersonalityList);

        List <RentalUserRole> rentalUserRoleList = new ArrayList <> ();
        rentalUserRoleList.addAll (Arrays.asList (RentalUserRole.values ()));
        model.addAttribute ("rentalUserRoleList", rentalUserRoleList);        
        
        return ("admin/rentalUser/new");
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create (
        @Valid @ModelAttribute("rentalUserDto") RentalUserDto formBean,
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
            result = "/admin/rentalUser/new";
        }
        else {
            Long id = rentalUserFacade.registerUser (formBean, newPassword);
            redirectAttributes.addFlashAttribute (
                "alert_success",
                "RentalUser " + formBean.getUsername () + " (id = " + id + ") was created."
            );
            result = "redirect:" + uriBuilder.path ("/admin/rentalUser/view/{id}").buildAndExpand(id).encode().toUriString();
        }
        return result;
    }

    @RequestMapping (value = "/delete/{id}", method = RequestMethod.POST)
    public String delete (
        @PathVariable long id,
        Model model,
        UriComponentsBuilder uriBuilder,
        RedirectAttributes redirectAttributes
    ) {
        LOGGER.info ("delete({})", id);
        
        RentalUserDto rentalUser = rentalUserFacade.findUserById (id);
        rentalUserFacade.deleteUser(id);
        redirectAttributes.addFlashAttribute (
            "alert_success",
            "RentalUser " + rentalUser.getUsername () + "(id = " + id + ") was deleted."
        );
        return ("redirect:" + uriBuilder.path ("/admin/rentalUser/list").toUriString ());
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
        LOGGER.info ("request: GET /admin/rentalUser/list");
        model.addAttribute ("rentalUsers", rentalUserFacade.getAllUsers ());
        return ("/admin/rentalUser/list");
    }

    @RequestMapping (value = "/view/{id}", method = RequestMethod.GET)
    public String viewDetails (@PathVariable("id") long id, Model model) {
        LOGGER.info ("request: GET /admin/rentalUser/view/" + id);
        
        String result;
        RentalUserDto rentalUserDto = rentalUserFacade.findUserById (id);
        if (rentalUserDto == null) {
            result = "redirect:/index";
        }
        else {
            model.addAttribute ("rentalUserDto", rentalUserDto);
            result = ("/admin/rentalUser/view");
        }
        
        return result;
    }

    @RequestMapping (value = "/update/{id}", method = RequestMethod.GET)
    public String updatePrepare (@PathVariable long id, Model model) {
        RentalUserDto rentalUserDto = rentalUserFacade.findUserById (id);
        String result;
        if (rentalUserDto == null) {
            result = "redirect:/admin/rentalUser/list";
        }
        else {
            model.addAttribute ("rentalUserDto", rentalUserDto);
            
            Map <LegalPersonality, String> legalPersonalityList = new LinkedHashMap <> ();
            for (LegalPersonality legalPersonality : LegalPersonality.values ()) {
                legalPersonalityList.put (legalPersonality, legalPersonality.toString ());
            }
            model.addAttribute ("legalPersonalityList", legalPersonalityList);
            
            Set <RentalUserRole> roles = rentalUserDto.getRoles ();
            model.addAttribute ("hasRoleCustomer", roles.contains (RentalUserRole.CUSTOMER));
            model.addAttribute ("hasRoleEmployee", roles.contains (RentalUserRole.EMPLOYEE));
            
            List <RentalUserRole> rentalUserRoleList = new ArrayList <> ();
            rentalUserRoleList.addAll (Arrays.asList (RentalUserRole.values ()));
            model.addAttribute ("rentalUserRoleList", rentalUserRoleList);
            
            result = "admin/rentalUser/update";
        }
        return result;
    }

    @RequestMapping (value = "/updating", method = RequestMethod.POST)
    public String update (
        @Valid @ModelAttribute("rentalUserDto") RentalUserDto formBean,
        BindingResult bindingResult,
        Model model,
        RedirectAttributes redirectAttributes,
        UriComponentsBuilder uriBuilder
    ) {
        LOGGER.info ("update(rentalUserDto={})", formBean);
        
        String result;
        if (bindingResult.hasErrors ()) {
            for (ObjectError error : bindingResult.getGlobalErrors ()) {
                LOGGER.error ("ObjectError: {}", error);
            }
            for (FieldError error : bindingResult.getFieldErrors ()) {
                model.addAttribute (error.getField () + "_error", true);
                LOGGER.error ("FieldError: {}", error);
            }
            result = "/admin/rentalUser/update";
        }
        else {
            rentalUserFacade.updateUser (formBean);

            redirectAttributes.addFlashAttribute (
                "alert_success",
                "RentalUser " + formBean.getUsername () + " (id = " + formBean.getId () + ") was updated."
            );
            result = "redirect:" + uriBuilder.path("/admin/rentalUser/view/{id}").buildAndExpand(formBean.getId()).encode().toUriString();
        }
        return result;
    }
}
