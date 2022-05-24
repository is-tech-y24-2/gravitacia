package com.kotiki.utils;


import Entities.Cat;
import com.kotiki.dto.CatDTO;
import entitiesDAO.OwnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class CatDTOMapping{
    @Autowired
    private OwnerDAO ownerDao;

    public CatDTO mapToDTO(Cat cat) {
        CatDTO catDTO = new CatDTO();
        catDTO.setId(cat.getId());
        catDTO.setName(cat.getName());
        catDTO.setOwnerId(cat.getOwner()!=null?cat.getOwner().getId():null);
        catDTO.setFriendsId(cat.getFriends());
        catDTO.setBreed(cat.getBreed());
        String dateString;
        if(cat.getDateOfBirth() != null) {
            dateString = cat.getDateOfBirth().get(Calendar.DAY_OF_MONTH)
                    +"-"+cat.getDateOfBirth().get(Calendar.MONTH)
                    +"-"+cat.getDateOfBirth().get(Calendar.YEAR);
        } else {
            dateString = null;
        }
        catDTO.setDateOfBirth(dateString);
        return catDTO;
    }

    public Cat mapToEntity(CatDTO catDTO) throws ParseException {
        Cat cat = new Cat();
        cat.setBreed(catDTO.getBreed());
        cat.setName(catDTO.getName());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        if(catDTO.getDateOfBirth() != null){
            Date date = sdf.parse(catDTO.getDateOfBirth());
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cat.setDateOfBirth(cal);
        }else{
            Date date = new Date(1, 1, 1);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cat.setDateOfBirth(cal);
        }
        if(catDTO.getOwnerId()!=null) {
            cat.setOwner(ownerDao.findById(catDTO.getOwnerId()).orElse(null));
        }
        return cat;
    }
}