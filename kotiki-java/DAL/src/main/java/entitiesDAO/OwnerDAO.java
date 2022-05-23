package entitiesDAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Entities.Owner;

@Repository
public interface OwnerDAO extends JpaRepository<Owner, Long> {
    Owner findByName(String name);
}
