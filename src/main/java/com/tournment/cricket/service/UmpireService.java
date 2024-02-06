package com.tournment.cricket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tournment.cricket.model.Umpire;
import com.tournment.cricket.repository.UmpireRepository;
@Service
public class UmpireService {
	@Autowired
	UmpireRepository repository ;



    public List<Umpire> getAllUmpireList() {
        return repository.findAll();
    
    }
	
    public Umpire createUmpire(Umpire umpire) {
        return repository.save(umpire);
    }
    
    

    public Umpire getUmpireById(Long id) {
        return repository.findById(id).orElse(null);
    }
    
    public Umpire updateUmpire(Long id, Umpire umpire) {
        Umpire existingUmpire= repository.findById(id).orElse(null);
        if(existingUmpire!=null) {
        	
            return repository.save(umpire);
                	
        }
        return existingUmpire;
    }

	public void removeUmpire(Long id) {
		 repository.deleteById(id);
	}
    

    

}

