package com.kotiki.service;



import Entities.Cat;
import Entities.Owner;
import com.kotiki.Exeptions.CantFindById;
import entitiesDAO.CatDAO;
import entitiesDAO.OwnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan("entitiesDAO")
public class OwnerService {
    @Autowired
    private OwnerDAO ownerDAO;

    @Autowired
    private CatDAO catsDAO;

    public OwnerService() {}

    public Owner findOwnerById(Long id) throws CantFindById {
        Owner owner = ownerDAO.findById(id)
                .orElse(null);
        if(owner == null) {
            throw new CantFindById("Not Found");
        }
        return owner;
    }

    public Long saveNewOwner(Owner owner) {
        ownerDAO.save(owner);
        return owner.getId();
    }

    public void deleteOwnerById(Long id) {
        ownerDAO.deleteById(id);
    }

    public List<Owner> getAllOwners() {
        List<Owner> owners = ownerDAO.findAll();
        return owners;
    }

    public void deleteAllOwners() {
        ownerDAO.deleteAll();
    }

    public void addCat(Long id, Long catId) throws CantFindById {
        Optional<Owner> owner = ownerDAO.findById(id);
        Optional<Cat> cat = catsDAO.findById(catId);
        if(owner.isPresent() & cat.isPresent()) {
            owner.get().addCat(cat.get());
            cat.get().setOwner(owner.get());
            catsDAO.save(cat.get());
            ownerDAO.save(owner.get());
        } else {
            throw new CantFindById("Not found");
        }
    }
}