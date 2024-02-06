package com.tournment.cricket.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tournment.cricket.model.TournamentOrganizer;
import com.tournment.cricket.model.User;
import com.tournment.cricket.service.OrganizerService;

@Controller
public class OrganizerController {
    
    @Autowired
    private OrganizerService organizerService;

    
    @GetMapping("/newTournamentOrganizer")
    public String newTournament(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);

        return "createNewTournamentOrganizer";
    }
    
    
    
    
    @PostMapping("/createNewTournamentOrganizer")
    public String createNewTournamentOrganizer(TournamentOrganizer tournamentOrganizer,  HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }
        
        tournamentOrganizer = organizerService.createTournamentOrganizer(tournamentOrganizer);
        if(tournamentOrganizer==null) {
        	return "redirect:/";
        }else {
            model.addAttribute("MSG", "Organizer CREATED SUCCESSFULLY");
        	
        }
        

        return "home";
    }
    
    @PostMapping("/updateTournamentOrganizer")
    public String updateVenue(TournamentOrganizer tournamentOrganizer,  HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }
        TournamentOrganizer newTournamentOrganizer = organizerService.updateTournamentOrganizer(tournamentOrganizer.getId(), tournamentOrganizer);
        if(newTournamentOrganizer==null) {
        	return "redirect:/";
        }else {
            model.addAttribute("MSG", "Tournament UPDATED SUCCESSFULLY");
        	
        }
        

        return "home";
    }
    
    
    @GetMapping("/getOrganizerList")
    public String getOrganizerList(HttpSession session , Model  model ) {
    	User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }
       model.addAttribute("organizers",organizerService.getAllOrganizers());
       return "TournamentListOrganizer";
    	
    }
    
    @GetMapping("/editOrganizerForm")
    public String editOrganizerForm(@RequestParam Long id , HttpSession session , Model  model ) {
    	User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }
       model.addAttribute("organizer",organizerService.getTournamentOrganizerById(id));
       return "editOrganizer";
    	
    }
}
