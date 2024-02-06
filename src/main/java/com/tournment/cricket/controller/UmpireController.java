package com.tournment.cricket.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tournment.cricket.model.Umpire;
import com.tournment.cricket.model.User;
import com.tournment.cricket.service.UmpireService;

@Controller
public class UmpireController {
    
    @Autowired
    private UmpireService umpireService;

    
    @GetMapping("/newUmpire")
    public String newUmpire(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);

        return "createNewUmpire";
    }
    
    
    @GetMapping("/getUmpireList")
    public String getUmpireList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       model.addAttribute("umpires", umpireService.getAllUmpireList());	
        return "umpireList";
    }
    
    
    
    
    @PostMapping("/createUmpire")
    public String createUmpire(Umpire umpire,  HttpSession session, Model model) {
       
    	if(session!=null) {
    	User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }
    }
        umpire = umpireService.createUmpire(umpire);
        if(umpire==null) {
        	return "redirect:/";
        }else {
            model.addAttribute("MSG", "Umpire CREATED SUCCESSFULLY");
        	
        }
        

        return "home";
    }
    
    @PostMapping("/updateUmpire")
    public String updateUmpire(Umpire umpire,  HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }
         umpire = umpireService.updateUmpire(umpire.getId(), umpire);
        if(umpire==null) {
        	return "redirect:/";
        }else {
            model.addAttribute("MSG", "Umpire UPDATED SUCCESSFULLY");
        	
        }
        

        return "home";
    }
    
    @GetMapping("/editUmpireForm")
    public String editUmpireForm(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       Umpire u = umpireService.getUmpireById(id);
       model.addAttribute("umpire", u);
        return "editUmpire";
    }
    
    @PostMapping("/removeUmpire")
    public String removeUmpire(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        try {
       umpireService.removeUmpire(id);
       model.addAttribute("MSG", "UMPIRE REMOVED");
        }catch (Exception e) {

            model.addAttribute("MSG", "Unable To Remove UMPIRE REMOVED");
		} 
       return "home";
    }
    
    @GetMapping("/removeUmpireForm")
    public String removeUmpireForm(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       Umpire u = umpireService.getUmpireById(id);
       model.addAttribute("umpire", u);
        return "confirmRemoveUmpire";
    }
    

    
}
