package com.kotiki.service;

import Entities.Cat;
import com.kotiki.Exeptions.CantFindById;
import entitiesDAO.CatDAO;
import entitiesDAO.OwnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan("common")
public class CatService {
    @Autowired
    private CatDAO catDao;

    @Autowired
    private OwnerDAO ownerDao;

    public Cat findCatById(Long id) throws CantFindById {
        Cat cat = catDao.findById(id)
                .orElse(null);
        if(cat == null) {
            throw new CantFindById("Not Found");
        }
        return cat;
    }

    public Long saveNewCat(Cat cat) {
        catDao.save(cat);
        System.out.println(cat.getName());
        return cat.getId();
    }

    public void deleteCatById(Long id) {
        catDao.deleteById(id);
    }

    public List<Cat> getAllCats() {
        List<Cat> cats = catDao.findAll();
        return cats;
    }

    public void deleteAllCats() {
        catDao.deleteAll();
    }

    public void addFriend(Long id, Long friendId) throws CantFindById {
        Optional<Cat> cat = catDao.findById(id);
        Optional<Cat> friend = catDao.findById(friendId);
        if(cat.isPresent() & friend.isPresent()) {
            cat.get().setFriends(friend.get());
            catDao.save(cat.get());
            catDao.save(friend.get());
        } else {
            throw new CantFindById("Not found");
        }
    }
}
