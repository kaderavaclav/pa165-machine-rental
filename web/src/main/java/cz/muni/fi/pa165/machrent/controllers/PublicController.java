package cz.muni.fi.pa165.machrent.controllers;

import cz.muni.fi.pa165.machrent.SampleDataLoadingFacadeImpl;
import cz.muni.fi.pa165.machrent.facade.MachineFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by vaclav.kadera on 17-Dec-16.
 */
@Controller
@RequestMapping("/public")
public class PublicController {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private MachineFacade machineFacade;


    @RequestMapping(value = "/machineList", method = RequestMethod.GET)
    public String machineList(Model model, HttpServletRequest req) {
        log.error("request: GET /public/machineList");
        model.addAttribute("machines", machineFacade.findAllMachines());
        return "/public/machineList";
    }
}