/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.machrent.controllers;

import cz.muni.fi.pa165.machrent.facade.RentalUserFacade;
import cz.muni.fi.pa165.machrent.dto.RentalUserDto;
import cz.muni.fi.pa165.machrent.dto.RentalUserAuthenticateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Peter Benus
 */
@Controller
@RequestMapping("/auth")
public class AuthenticationController {
    final static Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    RentalUserFacade rentalUserFacade;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String authForm(
            Model model,
            HttpServletRequest req,
            HttpServletResponse res) {
        log.error("request: GET /auth/login");
        HttpSession session = req.getSession(true);
        if (session.getAttribute("authUser") != null) {
            return "redirect:/rental";
        }
        return "auth/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authenticate(
            @RequestParam String email,
            @RequestParam String password,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req,
            HttpServletResponse res) {
        log.error("request: POST /auth/login");
        RentalUserAuthenticateDto authDto = new RentalUserAuthenticateDto();
        authDto.setEmail(email);
        authDto.setPassword(password);
        RentalUserDto user = rentalUserFacade.authUser(authDto);
        if (user == null) {
            redirectAttributes.addFlashAttribute("alert_info", "Wrong email or password");
            return "redirect:/auth/login";
        }
        HttpSession session = req.getSession(true);
        user.setIsAdmin(rentalUserFacade.isUserAdmin(user.getId()));
        session.setAttribute("authUser", user);
        redirectAttributes.addFlashAttribute("alert_info", "You have been logged in.");
        return "redirect:/rental";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest req) {
        log.error("request: /auth/logout");
        HttpSession session = req.getSession(true);
        session.removeAttribute("authUser");
        redirectAttributes.addFlashAttribute("alert_info", "You have been successfully logged out.");
        return "redirect:/auth/login";
    } 
}
