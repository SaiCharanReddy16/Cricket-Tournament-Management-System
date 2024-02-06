package com.tournment.cricket.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tournment.cricket.model.Tournament;
import com.tournment.cricket.model.TournamentOrganizer;
import com.tournment.cricket.model.User;
import com.tournment.cricket.service.OrganizerService;
import com.tournment.cricket.service.TournamentService;

@Controller
public class TournamentController {
    
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private OrganizerService organizerService;

    
    @GetMapping("/newTournament")
    public String newTournament(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);

        return "createNewTournament";
    }
    
    
    
    
    @PostMapping("/createNewTournament")
    public String createNewVenue(Tournament tournament, @RequestParam String tStartDate,@RequestParam String tEndDate,@RequestParam String organizerName,  HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }
       tournament.setStartDate(LocalDate.parse(tStartDate));

       tournament.setEndDate(LocalDate.parse(tEndDate));
        
      List<TournamentOrganizer>  tournamentOrganizers =  organizerService.getOrganizerByName(organizerName);
      if(tournamentOrganizers!=null) {
    	 tournament.setOrganizer(tournamentOrganizers.get(0));
    	  
      } 
      
      
      tournament = tournamentService.createTournament(tournament);
        if(tournament==null) {
        	return "redirect:/";
        }else {
            model.addAttribute("MSG", "TOURNAMENT CREATED SUCCESSFULLY");
        	
        }
        

        return "home";
    }
    
    @PostMapping("/updateTournament")
    public String updateVenue(Tournament tournament,@RequestParam String tStartDate, @RequestParam String tEndDate,  HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        tournament.setStartDate(LocalDate.parse(tStartDate));

        tournament.setEndDate(LocalDate.parse(tEndDate));
       
        Tournament newTournament = tournamentService.updateTournament(tournament.getId(), tournament);
        if(newTournament==null) {
        	return "redirect:/";
        }else {
            model.addAttribute("MSG", "Tournament UPDATED SUCCESSFULLY");
        	
        }
        

        return "home";
    }
@GetMapping("/getTournmentList")
public String getTournmentList(HttpSession session, Model model) {
	
	List<Tournament> tournaments = tournamentService.getAllTournments();
	model.addAttribute("tournaments", tournaments);
	return "tournamentList";
}
@GetMapping("/getUserTournmentList")
public String getUserTournmentList(HttpSession session, Model model) {
	
	List<Tournament> tournaments = tournamentService.getAllTournments();
	model.addAttribute("tournaments", tournaments);
	return "tournamentListUser";
}



@GetMapping("/editTournamentForm")
public String editTournamentForm(@RequestParam Long id, HttpSession session, Model model) {
	
    User user = (User) session.getAttribute("currentUser");

    if (user == null) {
        return "redirect:/";
    }

    model.addAttribute("user", user);
   model.addAttribute("tournament", tournamentService.getTournamentById(id));
    return "editTournament";
}


}
