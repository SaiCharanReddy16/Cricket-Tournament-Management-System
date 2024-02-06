package com.tournment.cricket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tournment.cricket.model.Venue;
import com.tournment.cricket.repository.VenueRepository;
@Service
public class VenueService {
	@Autowired
	VenueRepository repository ;



    public List<Venue> getAllVenues() {
        return repository.findAll();
    
    }
	
    public Venue createVenue(Venue venue) {
       

        return repository.save(venue);
    }
    
    

    public Venue getMatchById(Long id) {
        return repository.findById(id).orElse(null);
    }
    
    public Venue updateVenue(Long id, Venue venue) {
        Venue existingVenue = repository.findById(id).orElse(null);
        if (existingVenue != null) {
        	if(venue.getAddress()!=null) {
        		existingVenue.setAddress(venue.getAddress());
        	}
        	if(venue.getCity()!=null) {
        		existingVenue.setCity(venue.getCity());
        	}
        	if(venue.getState()!=null) {
        		existingVenue.setState(venue.getState());
        	}

        	if(venue.getName()!=null) {
        		existingVenue.setName(venue.getName());
        	}
        	if(venue.getCountry()!=null) {
        		existingVenue.setCountry(venue.getCountry());
        	}

            return repository.save(existingVenue);
        }
        
        return null;
    }

	public Object getVenueById(Long id) {
		return repository.getVenueById(id);
	}

	public void removeVenue(Long id) {
		repository.deleteById(id);
		
	}

}

