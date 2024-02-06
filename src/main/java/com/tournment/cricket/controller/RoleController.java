package com.tournment.cricket.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tournment.cricket.model.Role;
import com.tournment.cricket.model.User;
import com.tournment.cricket.service.RoleService;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;


    
    
    @PostMapping("/createRole")
    public String createRole(Role role , HttpSession  session,   Model model) {
    	  User user = (User) session.getAttribute("currentUser");

          if (user == null) {
              return "redirect:/";
          }
        role = roleService.createRole(role);
          if(role==null) {
          	return "redirect:/";
          }else {
              model.addAttribute("MSG", "Role CREATED SUCCESSFULLY");
              model.addAttribute("action", "roleList");
            	
          }
          

          return "home";

    }
    @PostMapping("/updateRole")
    public String updateRole(Role role , Model model) {
        if( roleService.updateRole(role.getId(),role)== null) {

            model.addAttribute("MSG", "FAILED TO UPDATE Role DETAILES");
            }else {
                model.addAttribute("MSG", "Role DETAILES UPDATED SUCCESSFULLY");
                model.addAttribute("action", "roleList");
            	
            }
            

            return "home";
        
    }
    


    @GetMapping("/newRole")
    public String newRole(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null ) {
            return "redirect:/";
        }
        model.addAttribute("user", user);

        return "createNewRole";
    }
    
    @GetMapping("/getRoleList")
    public String getRoleList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       model.addAttribute("roles", roleService.getAllRoles());	
        return "roleList";
    }
    
    
    
    @GetMapping("/editRoleForm")
    public String editUserForm(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       Role role = roleService.getRoleById(id);
       model.addAttribute("role", role);
        return "editRole";
    }
    
    @PostMapping("/removeRole")
    public String removeUser(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        try {
       roleService.deleteRole(id);
       model.addAttribute("MSG", "Role REMOVED");
        }catch (Exception e) {

            model.addAttribute("MSG", "Unable To Remove Role");
		} 
       model.addAttribute("action", "roleList");
        return "home";
    }
    
    @GetMapping("/removeRoleForm")
    public String removeRoleForm(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       Role role = roleService.getRoleById(id);
       model.addAttribute("role", role);
        return "confirmRemoveRole";
    }

}
