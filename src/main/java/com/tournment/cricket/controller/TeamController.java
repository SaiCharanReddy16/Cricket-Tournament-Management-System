package com.tournment.cricket.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tournment.cricket.model.Coach;
import com.tournment.cricket.model.Team;
import com.tournment.cricket.model.User;
import com.tournment.cricket.service.CoachService;
import com.tournment.cricket.service.TeamService;

@Controller
public class TeamController {
    @Autowired
    private TeamService teamService;
@Autowired
CoachService coachService ;
    
    @PostMapping("/createTeam")
    public String createTeam(Team team , HttpSession  session,   Model model) {
    	  User user = (User) session.getAttribute("currentUser");

          if (user == null) {
              return "redirect:/";
          }

         
        
        
        team = teamService.createTeam(team);
          if(team==null) {
          	return "redirect:/";
          }else {
              model.addAttribute("MSG", "Team CREATED SUCCESSFULLY");
              model.addAttribute("action", "teamList");
            	
          }
          

          return "home";

    }
    @PostMapping("/updateTeam")
    public String updateUser(Team team , Model model) {
        if( teamService.updateTeam(team.getId(),team)== null) {

            model.addAttribute("MSG", "FAILED TO UPDATE USER DETAILES");
            }else {
                model.addAttribute("MSG", "Team DETAILES UPDATED SUCCESSFULLY");
                model.addAttribute("action", "teamList");
            	
            }
            

            return "home";
        
    }

    @GetMapping("/newTeam")
    public String newUser(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);

        return "createNewTeam";
    }
    
    @GetMapping("/getTeamList")
    public String getTeamList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        List<Team> teams =  teamService.getAllTeams();
       model.addAttribute("teams",teams);	
        return "teamList";
    }
    @GetMapping("/getUserTeamList")
    public String getUserTeamList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        List<Team> teams =  teamService.getAllTeams();
       model.addAttribute("teams",teams);	
        return "teamListUser";
    }
        
    
    
    @GetMapping("/editTeamForm")
    public String editUserForm(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       Team team = teamService.getTeamById(id);
       model.addAttribute("team", team);
        return "editTeam";
    }
    
    @PostMapping("/removeTeam")
    public String removeTeam(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       teamService.deleteTeam(id);
       try {
       model.addAttribute("MSG", "Team REMOVED");
       }catch (Exception e) {

           model.addAttribute("MSG", "Unable To Remove Team");
		} 
       model.addAttribute("action", "teamList");
        return "home";
    }
    
    @GetMapping("/removeTeamForm")
    public String removeTeamForm(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       Team team = teamService.getTeamById(id);
       model.addAttribute("team", team);
        return "confirmRemoveTeam";
    }
    
    
    @GetMapping("/manageCoach")
    public String manageCoach(@RequestParam Long teamId , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       Team team = teamService.getTeamCoachesByTeamId(teamId);
       model.addAttribute("team", team);
        return "teamCoachDetails";
    }
    @GetMapping("/changeCoach")
    public String changeCoach(@RequestParam Long teamId , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       Team team = teamService.getTeamCoachesByTeamId(teamId);

       List<Coach> coaches = coachService.getAllCoaches();
       model.addAttribute("team", team);
       model.addAttribute("coaches", coaches);
       
       return "editTeamCoach";
    }
        
   @PostMapping("/updateTeamCoach")
   public String updateTeamCoach(@RequestParam Long teamId , @RequestParam Long coachId ,HttpSession session,Model model) {
      try {
	   teamService.updateTeamCoach(teamId,coachId);

	   teamService.updateCoach(teamId,coachId);

       model.addAttribute("MSG", "Coach Assigned To Team Failed");
	   }catch (Exception e) {
			
               model.addAttribute("MSG", "Coach Assigned To Team Failed");
           	
           }
           

           return "home";
	   
   }
}
