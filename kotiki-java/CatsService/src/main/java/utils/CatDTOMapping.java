package utils;


import Entities.Cat;
import dto.CatDTO;
import entitiesDAO.OwnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
        catDTO.setBreed(cat.getColour());
        String dateString = cat.getBirth().get(Calendar.DAY_OF_MONTH)
                +"-"+cat.getBirth().get(Calendar.MONTH)
                +"-"+cat.getBirth().get(Calendar.YEAR);
        catDTO.setDateOfBirth(dateString);
        return catDTO;
    }

    public Cat mapToEntity(CatDTO catDTO) throws ParseException {
        Cat cat = new Cat();
        cat.setColour(catDTO.getBreed());
        cat.setName(catDTO.getName());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = sdf.parse(catDTO.getDateOfBirth());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cat.setBirth(cal);
        if(catDTO.getOwnerId()!=null) {
            cat.setOwner(ownerDao.findById(catDTO.getOwnerId()).orElse(null));
        }
        return cat;
    }
}