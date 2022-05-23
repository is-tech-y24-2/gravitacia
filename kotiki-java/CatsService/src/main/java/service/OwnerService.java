package service;



import Entities.Cat;
import Entities.Owner;
import Exeptions.CantFindById;
import entitiesDAO.CatDAO;
import entitiesDAO.OwnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan("common")
public class OwnerService {
    @Autowired
    private OwnerDAO ownerDao;

    @Autowired
    private CatDAO catsDao;

    public OwnerService() {}

    public Owner findOwnerById(Long id) throws CantFindById {
        Owner owner = ownerDao.findById(id)
                .orElse(null);
        if(owner == null) {
            throw new CantFindById("Not Found");
        }
        return owner;
    }

    public Long saveNewOwner(Owner owner) {
        ownerDao.save(owner);
        return owner.getId();
    }

    public void deleteOwnerById(Long id) {
        ownerDao.deleteById(id);
    }

    public List<Owner> getAllOwners() {
        List<Owner> owners = ownerDao.findAll();
        return owners;
    }

    public void deleteAllOwners() {
        ownerDao.deleteAll();
    }

    public void addCat(Long id, Long catId) throws CantFindById {
        Optional<Owner> owner = ownerDao.findById(id);
        Optional<Cat> cat = catsDao.findById(catId);
        if(owner.isPresent() & cat.isPresent()) {
            owner.get().addCat(cat.get());
            cat.get().setOwner(owner.get());
            catsDao.save(cat.get());
            ownerDao.save(owner.get());
        } else {
            throw new CantFindById("Not found lol ahahha");
        }
    }
}