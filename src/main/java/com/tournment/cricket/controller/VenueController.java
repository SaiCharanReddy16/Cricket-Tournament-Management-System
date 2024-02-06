package com.tournment.cricket.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tournment.cricket.model.User;
import com.tournment.cricket.model.Venue;
import com.tournment.cricket.service.VenueService;

@Controller
public class VenueController {
    @Autowired
    private VenueService venueService;

    
    @GetMapping("/newVenue")
    public String newVenue(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);

        return "createNewVenue";
    }
    
    @GetMapping("/editVenueForm")
    public String newVenue(@RequestParam Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        model.addAttribute("venue",venueService.getVenueById(id));

        return "editVenue";
    }
    
    
    
    @PostMapping("/createNewVenue")
    public String createNewVenue(Venue venue,  HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }
        Venue venueNew = venueService.createVenue(venue);
        if(venueNew==null) {
        	return "redirect:/";
        }else {
            model.addAttribute("MSG", "VENUE CREATE SUCCESSFULLY");
        	
        }
        

        return "home";
    }
    
    @PostMapping("/updateVenue")
    public String updateVenue(Venue venue,  HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }
        Venue venueNew = venueService.updateVenue(venue.getId(), venue);
        if(venueNew==null) {
        	return "redirect:/";
        }else {
            model.addAttribute("MSG", "VENUE Updated SUCCESSFULLY");
        	
        }
        

        return "home";
    }
    
    
    @GetMapping("/getVenueList")
    public String getVenueList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       model.addAttribute("venues", venueService.getAllVenues());	
        return "venueList";
    }
    @GetMapping("/removeVenueForm")
    public String removeVenueForm(@RequestParam Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       model.addAttribute("venue", venueService.getVenueById(id));	
        return "confirmRemoveVenue";
    }
    @PostMapping("/removeVenue")
    public String removeVenue(@RequestParam Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }
        try {
        venueService.removeVenue(id);
        model.addAttribute("user", user);
        model.addAttribute("MSG", "Venue Removed");
        }catch (Exception e) {

            model.addAttribute("user", user);
            model.addAttribute("MSG", "Unable to Remove Venue");
		}
        return "home";
    }
    
}
