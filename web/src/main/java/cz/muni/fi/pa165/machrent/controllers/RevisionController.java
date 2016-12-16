package cz.muni.fi.pa165.machrent.controllers;

import cz.muni.fi.pa165.machrent.SampleDataLoadingFacadeImpl;
import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.dto.RevisionCreateDto;
import cz.muni.fi.pa165.machrent.dto.RevisionDto;
import cz.muni.fi.pa165.machrent.facade.MachineFacade;
import cz.muni.fi.pa165.machrent.facade.RentalFacade;
import cz.muni.fi.pa165.machrent.facade.RentalUserFacade;
import cz.muni.fi.pa165.machrent.facade.RevisionFacade;
import cz.muni.fi.pa165.machrent.validators.RevisionCreateDtoValidator;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zuz-schwarzova on 15. 12. 2016.
 */

@Controller
@RequestMapping("/admin/revision")
public class RevisionController {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private RevisionFacade revisionFacade;

    @Autowired
    private RentalUserFacade rentalUserFacade;

    @Autowired
    private MachineFacade machineFacade;

    @Autowired
    private RentalFacade rentalFacade;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listRevisions(Model model, HttpServletRequest req) {
        log.error("request: GET /admin/revision/list");
        HttpSession session = req.getSession(true);
        RentalUserDto user = (RentalUserDto) session.getAttribute("authUser");
        if (rentalUserFacade.isUserAdmin(user.getId())) {
            model.addAttribute("revisions", revisionFacade.findAllRevisions());
        } /*else {
            model.addAttribute("revisions", revisionFacade.getTripsByUser(user.getId()));
        }*/
        return "/admin/revision/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String showRevision(
            @PathVariable("id") long id,
            Model model) {

        log.error("request: GET /admin/revision/view/" + id);
        RevisionDto revision = revisionFacade.findById(id);
        if (revision == null) {
            return "redirect:/index";
        }
        //TODO SALALALALALALALALALA
        model.addAttribute("revision", revision);
        return "/admin/revision/view";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable long id,
                         Model model,
                         UriComponentsBuilder uriBuilder,
                         RedirectAttributes redirectAttributes) {

        RevisionDto revision = revisionFacade.findById(id);

        revisionFacade.deleteRevision(id);
        log.debug("delete({})", id);

        redirectAttributes.addFlashAttribute("alert_success", "Revision \"" + revision.getRevisionDate() + "\" was deleted.");
        return "redirect:" + uriBuilder.path("/admin/revision/list").toUriString();
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newRevision(Model model){
        log.error("new()");
        model.addAttribute("revisionCreate", new RevisionCreateDto());
        return "admin/revision/new";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(
            @PathVariable long id,
            Model model) {

        RevisionDto updateRevision = revisionFacade.findById(id);
        if (updateRevision == null) {
            return "redirect:/admin/revision/list";
        }
        model.addAttribute("revisionFacade", revisionFacade);
        return "admin/revision/update";
    }


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        binder.registerCustomEditor(Date.class, editor);
        if (binder.getTarget() instanceof RevisionCreateDto) {
            RevisionCreateDtoValidator validator = new RevisionCreateDtoValidator();
            binder.addValidators(validator);
        }
    }


    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createRevision(@Valid @ModelAttribute("revisionCreate") RevisionCreateDto formBean,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes,
                                 UriComponentsBuilder uriBuilder) {

        log.error("create(revisionCreate={})", formBean);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.error("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.error("FieldError: {}", fe);
            }
            return "/admin/revision/new";
        }

        Long id = revisionFacade.createRevision(formBean);

        redirectAttributes.addFlashAttribute("alert_success", "Revision with " + id + " was created");
        return "redirect:" + uriBuilder.path("/admin/revision/view/{id}").buildAndExpand(id).encode().toUriString();
    }

    @RequestMapping(value = "/updating", method = RequestMethod.POST)
    public String updateRevision(
            @Valid @ModelAttribute("revisionUpdate") RevisionDto formBean,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            UriComponentsBuilder uriBuilder) {

        log.error("update(revisionUpdate={})", formBean);

        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                log.error("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                log.error("FieldError: {}", fe);
            }
            return "/admin/revision/update";
        }

        revisionFacade.updateRevision(formBean);

        redirectAttributes.addFlashAttribute(
                "alert_success", "Revision " + formBean.getId() + " was updated"
        );
        return "redirect:" + uriBuilder.path("/admin/revision/view/{id}").buildAndExpand(formBean.getId()).encode().toUriString();
    }
}


