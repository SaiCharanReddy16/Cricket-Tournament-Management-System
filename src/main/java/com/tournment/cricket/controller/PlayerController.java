package com.tournment.cricket.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tournment.cricket.model.Player;
import com.tournment.cricket.model.User;
import com.tournment.cricket.service.PlayerService;
import com.tournment.cricket.service.TeamService;

@Controller
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private TeamService teamService;

    
    
    @PostMapping("/createPlayer")
    public String createPlayer(Player player ,@RequestParam Long teamId, HttpSession  session,   Model model) {
    	  User user = (User) session.getAttribute("currentUser");

          if (user == null) {
              return "redirect:/";
          }

         
          player.setTeam(teamService.getTeamById(teamId));
        
        player = playerService.createPlayer(player);
          if(player==null) {
          	return "redirect:/";
          }else {
              model.addAttribute("MSG", "Player CREATED SUCCESSFULLY");
              model.addAttribute("action", "playerList");
            	
          }
          

          return "home";

    }
    @PostMapping("/updatePlayer")
    public String updatePlayer(Player player , Model model) {
        if( playerService.updatePlayer(player.getId(),player)== null) {

            model.addAttribute("MSG", "FAILED TO UPDATE Player DETAILES");
            }else {
                model.addAttribute("MSG", "Player DETAILES UPDATED SUCCESSFULLY");
                model.addAttribute("action", "playerList");
            	
            }
            

            return "home";
        
    }
    
    @PostMapping("/exchangePlayer")
    public String exchangePlayer(Player player ,@RequestParam Long newTeamId,  Model model) {
    	try {
        playerService.exchangePlayer(player.getId(),newTeamId);
            model.addAttribute("MSG", "FAILED TO Exchange Player");

            model.addAttribute("MSG", "Player Exchaneged SUCCESSFULLY");
            
    	}catch (Exception e) {
    		e.printStackTrace();
            model.addAttribute("MSG", e.getMessage());
        }
            

            return "home";
        
    }

    @GetMapping("/newPlayer")
    public String newPlayer(@RequestParam Long teamId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null ) {
            return "redirect:/";
        }
        model.addAttribute("user", user);

        if( teamId==null) {
        model.addAttribute("teams",teamService.getAllTeams());
        	return "createNewPlayerAndAssignTeam";
        	
        }

    	model.addAttribute("teamId", teamId);
        	model.addAttribute("players", playerService.getPlayersByTeamId(teamId));
        return "createNewPlayer";
    }
    
    
    @GetMapping("/newPlayerWithOutTeam")
    public String newPlayerWithOutTeam( HttpSession session, Model model) {
    	return newPlayer(null, session, model);
    }
    
    
    
    
    @PostMapping("/makePlayerAsCaptain")
    public String makePlayerAsCaptain(Player player,@RequestParam Long teamId, HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null || teamId==null) {
            return "redirect:/";
        }
        
        playerService.makeAsCaptain(player.getId(),teamId);
        model.addAttribute("user", user);

    	model.addAttribute("teamId", teamId);
        	model.addAttribute("players", playerService.getPlayersByTeamId(teamId));
           	model.addAttribute("MSG","PLAYER HAS APPOINTED AS CAPTAIN");
            
        	return "home";
    }
    
    @GetMapping("/getPlayerList")
    public String getPlayerList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       model.addAttribute("players", playerService.getAllPlayers());	
        return "playerList";
    }
    
    @GetMapping("/getUserPlayerList")
    public String getUserPlayerList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       model.addAttribute("players", playerService.getAllPlayers());	
        return "playerListUser";
    }
    
    
    
    @GetMapping("/editPlayerForm")
    public String editUserForm(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       Player player = playerService.getPlayerById(id);
       model.addAttribute("player", player);
        return "editPlayer";
    }
    
    @PostMapping("/removePlayer")
    public String removeUser(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        try {
       playerService.deletePlayer(id);
       model.addAttribute("MSG", "Player REMOVED");
        }catch (Exception e) {

            model.addAttribute("MSG", "Unable To Remove Player");
		} 
       model.addAttribute("action", "playerList");
        return "home";
    }
    
    @GetMapping("/removePlayerForm")
    public String removePlayerForm(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       Player player = playerService.getPlayerById(id);
       model.addAttribute("player", player);
        return "confirmRemovePlayer";
    }
    


@GetMapping("/exchangePlayerForm")
public String exchangePlayerForm(@RequestParam Long id , HttpSession session, Model model) {
	
    User user = (User) session.getAttribute("currentUser");

    if (user == null) {
        return "redirect:/";
    }

    model.addAttribute("user", user);
   Player player = playerService.getPlayerById(id);
   model.addAttribute("player", player);
   
   model.addAttribute("teamList", teamService.getAllTeams());
	
	 return "exchangePlayer";
}  
}
