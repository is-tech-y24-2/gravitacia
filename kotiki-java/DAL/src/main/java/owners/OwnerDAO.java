package owners;

import Common.CommonDAO;

public interface OwnerDAO extends CommonDAO<Owner> {
    Owner findByName(String name);
}
