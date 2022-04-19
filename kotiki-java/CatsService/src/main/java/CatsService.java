import Common.CommonDAO;
import Entities.Cat;
import Entities.Owner;

import java.util.List;
import java.util.Set;

public class CatsService {
    private final ImplCatDAO implCatDAO;
    private final ImplOwnerDAO implOwnerDAO;

    public CatsService(ImplCatDAO _implCatDAO, ImplOwnerDAO _implOwnerDAO){
        implCatDAO = _implCatDAO;
        implOwnerDAO = _implOwnerDAO;
    }

    public List<Cat> FindAllCats(){
        return implCatDAO.findAll();
    }

    public List<Owner> FindAllOwners(){
        return implOwnerDAO.findAll();
    }

    public Cat FindCatByName(String name){
        return implCatDAO.findByName(name);
    }

    public Cat FindCatById(Long id){
        return implCatDAO.find(id);
    }

    public Set<Cat> findFriendsOfCat(String name) {
        var cat = implCatDAO.findByName(name);
        return cat.getFriends();
    }

}
