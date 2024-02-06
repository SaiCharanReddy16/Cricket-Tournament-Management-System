package com.tournment.cricket.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tournment.cricket.model.Match;
import com.tournment.cricket.model.Role;
import com.tournment.cricket.model.Tournament;
import com.tournment.cricket.model.TournamentOrganizer;
import com.tournment.cricket.model.User;
import com.tournment.cricket.model.Venue;
import com.tournment.cricket.service.MatchService;
import com.tournment.cricket.service.OrganizerService;
import com.tournment.cricket.service.RoleService;
import com.tournment.cricket.service.TournamentService;
import com.tournment.cricket.service.UserService;
import com.tournment.cricket.service.VenueService;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MatchService matService;
    @Autowired
    private VenueService venueService;
    @Autowired
    private OrganizerService organizerService;
    @Autowired
    private TournamentService tournamentService;
    @GetMapping("/")
    public String showLoginPage(HttpSession session ) {
        User user = (User) session.getAttribute("currentUser");
        if(user!=null) {
        	if (user.getRole().getName().equals("ADMIN")) {
                return "redirect:/adminDashboard";
            } else {
                return "redirect:/userDashboard";
            }
        }else {
    	return "login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session ) {
        session.invalidate();
        
    	return "login";
        
    }

    
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session,RedirectAttributes redirectAttributes,Model model) {
        Optional<User> optionalUser = userService.getUserByEmailAndPassword(email,password);
        
        
        if ( optionalUser.isPresent()) {
            User user = optionalUser.get();

            session.setAttribute("currentUser", user);
            model.addAttribute("user",user);
            if (user.getRole().getName().equals("ADMIN")) {
                return "redirect:/adminDashboard";
            } else {
                return "redirect:/userDashboard";
            }
        }
      
        redirectAttributes.addFlashAttribute("RMSG", "INVALID USER CREDENTIALS");

        return "redirect:/";
    }
    @GetMapping("/adminDashboard")
    public String showAdminDashboardView(Model model) {
        List<Match> matchList = matService.getAllMatches();
           
               model.addAttribute("matches", matchList);

        return "adminDashboard";
    }

    @GetMapping("/userDashboard")
    public String showUserDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
    
        model.addAttribute("matches", matService.getAllMatches());

        return "userDashboard";
}
    
    @PostMapping("/createNewUser")
    public String createNewUser(User user , @RequestParam String roleName , Model model) {
    	Optional<Role> r = roleService.getRoleByName(roleName);
    	if(r!=null) {
    	user.setRole(r.get());
        if( userService.createUser(user)== null) {

            model.addAttribute("MSG", "FAILED TO SAVE USER DETAILES");
            }else {
                model.addAttribute("MSG", "USER CREATED SUCCESSFULLY");
            	
            }
            
    	}else {
    	    model.addAttribute("MSG", "ROLE NOT FOUND");
            
    	}
            return "home";
        
    }
    @PostMapping("/updateUser")
    public String updateUser(User user , Model model) {
        if( userService.updateUser(user.getId(),user)== null) {

            model.addAttribute("MSG", "FAILED TO UPDATE USER DETAILES");
            }else {
                model.addAttribute("MSG", "USER DETAILES UPDATED SUCCESSFULLY");
            	
            }
            

            return "home";
        
    }

    @GetMapping("/newUser")
    public String newUser(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);

        return "createNewUser";
    }
    
    @GetMapping("/getUserList")
    public String getUserList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       model.addAttribute("users", userService.getAllUsers());	
        return "userList";
    }
    
    
    
    @GetMapping("/editUserForm")
    public String editUserForm(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       User u = userService.getUserById(id);
       model.addAttribute("editUser", u);
        return "editUser";
    }
    
    @PostMapping("/removeUser")
    public String removeUser(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        try {
       userService.deleteUser(id);
       model.addAttribute("MSG", "USER REMOVED");
        }catch (Exception e) {

            model.addAttribute("MSG", "Unable To Remove User");
		} 
        return "home";
    }
    
    @GetMapping("/removeUserForm")
    public String removeUserForm(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       User u = userService.getUserById(id);
       model.addAttribute("eUser", u);
        return "confirmRemoveUser";
    }

    
}
