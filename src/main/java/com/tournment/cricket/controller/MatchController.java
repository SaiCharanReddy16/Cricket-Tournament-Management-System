package com.tournment.cricket.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tournment.cricket.model.Match;
import com.tournment.cricket.model.MatchScorecard;
import com.tournment.cricket.model.MatchScorecardDetails;
import com.tournment.cricket.model.User;
import com.tournment.cricket.service.MatchScorecardDetailsService;
import com.tournment.cricket.service.MatchScorecardService;
import com.tournment.cricket.service.MatchService;
import com.tournment.cricket.service.TeamService;
import com.tournment.cricket.service.TournamentService;
import com.tournment.cricket.service.UmpireService;
import com.tournment.cricket.service.VenueService;

@Controller
public class MatchController {
    @Autowired
    private MatchService matchService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private UmpireService umpireService;    
    @Autowired
    private VenueService venueService;
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private MatchScorecardService matchScorecardService;
    @Autowired 
    MatchScorecardDetailsService matchScorecardDetailsService;



    
    
    @PostMapping("/createMatch")
    public String createMatch(@RequestParam String matchType , @RequestParam String matchDate,@RequestParam String matchTime,@RequestParam Long venueId,@RequestParam Long umpireId,@RequestParam Long tournamentId,@RequestParam Long team1Id,@RequestParam Long team2Id, HttpSession  session,   Model model) {
    	  User user = (User) session.getAttribute("currentUser");

          if (user == null) {
              return "redirect:/";
          }
       try {
    	   matchService.createNewMatch(matchType,matchDate,matchTime,venueId,umpireId,tournamentId,team1Id,team2Id);
              model.addAttribute("MSG", "Match CREATED SUCCESSFULLY");
              model.addAttribute("action", "matchList");
            	
          }catch (Exception e) {
        	  e.printStackTrace();
        	  model.addAttribute("MSG", "FAILED TO CREATE MATCH");
              		}
          

          return "home";

    }
    @PostMapping("/updateMatch")
    public String updateMatch(Match match , Model model) {
        if( matchService.updateMatch(match.getId(),match)== null) {

            model.addAttribute("MSG", "FAILED TO UPDATE Match DETAILES");
            }else {
                model.addAttribute("MSG", "Match DETAILES UPDATED SUCCESSFULLY");
                model.addAttribute("action", "matchList");
            	
            }
            

            return "home";
        
    }
    


    @GetMapping("/newMatch")
    public String newMatch(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null ) {
            return "redirect:/";
        }
        model.addAttribute("user", user);
        model.addAttribute("teams", teamService.getAllTeams());
        model.addAttribute("venues", venueService.getAllVenues());
        model.addAttribute("umpires", umpireService.getAllUmpireList());
        model.addAttribute("tournaments", tournamentService.getAllTournments());

        return "createNewMatch";
    }
    
    @GetMapping("/getMatchList")
    public String getMatchList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        List<Match>list =  matchService.getAllMatches();
        System.out.println(list);
       model.addAttribute("matches",list);		
        return "matchList";
    }
    
    @GetMapping("/getUserMatchList")
    public String getUserMatchList(HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        List<Match>list =  matchService.getAllMatches();
        System.out.println(list);
       model.addAttribute("matches",list);		
        return "matchListUser";
    }
    
    
    @PostMapping("/viewMachDetails")
    public String viewMachDetails(@RequestParam Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
         Match match = matchService.getMatchById(id);

         List<MatchScorecard> matchScorecard = matchScorecardService.getMatchScorecardByMatchId(match.getId());
         List<MatchScorecardDetails> matchScorecardDetails = matchScorecardDetailsService.getMatchScorecardDetails(match.getId());

         if(match==null) {
        	 model.addAttribute("MSG", "UNABLE TO FETCH MATCH DETAILS");
        	 
         }else {
        	   model.addAttribute("match",match);	
        	   model.addAttribute("matchScorecard", matchScorecard);
        	   model.addAttribute("matchScorecardDetails", matchScorecardDetails);
        	        	 
         }
        return "matchDetails";
    }
    @PostMapping("/viewMachDetailsUser")
    public String viewMachDetailsUser(@RequestParam Long id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
         Match match = matchService.getMatchById(id);

         List<MatchScorecard> matchScorecard = matchScorecardService.getMatchScorecardByMatchId(match.getId());
         List<MatchScorecardDetails> matchScorecardDetails = matchScorecardDetailsService.getMatchScorecardDetails(match.getId());

         if(match==null) {
        	 model.addAttribute("MSG", "UNABLE TO FETCH MATCH DETAILS");
        	 
         }else {
        	   model.addAttribute("match",match);	
        	   model.addAttribute("matchScorecard", matchScorecard);
        	   model.addAttribute("matchScorecardDetails", matchScorecardDetails);
        	        	 
         }
        return "matchDetailsUser";
    }
    
        
    @GetMapping("/editMatchForm")
    public String editUserForm(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       Match match = matchService.getMatchById(id);
       model.addAttribute("match", match);
        return "editMatch";
    }
    
    @PostMapping("/removeMatch")
    public String removeUser(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
      try {  
       matchService.deleteMatch(id);
       model.addAttribute("MSG", "Match REMOVED");
    }catch (Exception e) {

        model.addAttribute("MSG", "Unable To Remove Match");
	} 
       model.addAttribute("action", "matchList");
        return "home";
    }
    
    @GetMapping("/removeMatchForm")
    public String removeMatchForm(@RequestParam Long id , HttpSession session, Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
       Match match = matchService.getMatchById(id);
       model.addAttribute("match", match);
        return "confirmRemoveMatch";
    }

    @GetMapping("/addOrModifiScoreCardDetails")
    public String addOrModifiScoreCardDetails(@RequestParam Long matchId,HttpSession session,Model model) {
    	List<MatchScorecard>  matchScorecards = matchScorecardService.getMatchScorecardByMatchId(matchId);
    	model.addAttribute("matchScoreCard", matchScorecards);
    	return "editScoreCardDetails";
    	
    }
    
    @GetMapping("/getScoreCardDetails")
    public String getScoreCardDetails(@RequestParam Long matchId,@RequestParam Long teamId,@RequestParam Long playerId,HttpSession session,Model model) {
    	List<MatchScorecardDetails>  matchScorecardDetail = matchScorecardDetailsService.getMatchScorecardDetails(matchId, teamId, playerId);
    	model.addAttribute("matchScorecardDetails", matchScorecardDetail);
    	return "editScoreCardDetails";
    	
    }
    
    @PostMapping("/updatePlayerScoreForm")
    public String updatePlayerScoreForm(@RequestParam Long matchId,@RequestParam Long teamId,@RequestParam Long playerId,HttpSession session,Model model) {
        User user = (User) session.getAttribute("currentUser");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);
        List<MatchScorecardDetails>  matchScorecardDetail = matchScorecardDetailsService.getMatchScorecardDetails(matchId, teamId, playerId);
        model.addAttribute("playerId", playerId);
        model.addAttribute("teamId", teamId);
        model.addAttribute("matchId", matchId);
    	    	
        model.addAttribute("matchScorecardDetails", matchScorecardDetail);
            	
	return "editScoreCardDetails";
    	
    }
    @PostMapping("/createUpdateMatchScoreDetails")
    public String createUpdateMatchScoreDetails(MatchScorecardDetails scoreCard , @RequestParam Long matchId, @RequestParam Long teamId, @RequestParam Long playerId ,HttpSession session , Model model) {
		
    	matchScorecardService.insertScoreCard(scoreCard,playerId,teamId,matchId);
    	return "redirect:/getMatchList"; 
    	}
    
    
}
