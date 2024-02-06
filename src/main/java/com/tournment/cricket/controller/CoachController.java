package com.tournment.cricket.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tournment.cricket.model.Coach;
import com.tournment.cricket.model.User;
import com.tournment.cricket.service.CoachService;

@Controller
public class CoachController {
    @Autowired
    private CoachService coachService;

    
    @PostMapping("/createCoach")
    public String createCoach(Coach coach , HttpSession  session,   Model model) {
    	  User user = (User) session.getAttribute("currentUser");

          if (user == null) {
              return "redirect:/";
          }

         
        
        
        coach = coachService.createCoach(coach);
          if(coach==null) {
          	return "redirect:/";
          }else {
              model.addAttribute("MSG", "Coach CREATED SUCCESSFULLY");
              model.addAttribute("action", "coachList");
            	
          }
          

          return "home";

    }
    @PostMapping("/updateCoach")
    public String updateUser(Coach coach , Model model) {
        if( coachService.updateCoach(coach.getId(),coach)== null) {

            model.addAttribute("MSG", "FAILED TO UPDATE USER DETAILES");
            }else {
                model.addAttribute("MSG", "Coach DETAILES UPDATED SUCCESSFULLY");
                model.addAttribute("action", "coachList");
            	
            }
            

            return "home";
        
    }

    @GetMapping("/newCoach")
    public String newUser(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);

        return "createNewCoach";
    }
    
    @GetMapping("/getCoachList")
    public String getCoachList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       model.addAttribute("coaches", coachService.getAllCoaches());	
        return "coachList";
    }
    
    
    
    @GetMapping("/editCoachForm")
    public String editUserForm(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       Coach coach = coachService.getCoachById(id);
       model.addAttribute("coach", coach);
        return "editCoach";
    }
    
    @PostMapping("/removeCoach")
    public String removeUser(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        try {
       coachService.deleteCoach(id);
       model.addAttribute("MSG", "Coach REMOVED");
    }catch (Exception e) {

        model.addAttribute("MSG", "Unable To Remove Coach");
	} 
       model.addAttribute("action", "coachList");
        return "home";
    }
    
    @GetMapping("/removeCoachForm")
    public String removeCoachForm(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       Coach coach = coachService.getCoachById(id);
       model.addAttribute("coach", coach);
        return "confirmRemoveCoach";
    }

    
}
