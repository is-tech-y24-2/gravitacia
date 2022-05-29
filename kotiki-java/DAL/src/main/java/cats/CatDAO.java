package cats;

import Common.CommonDAO;

public interface CatDAO extends CommonDAO<Cat> {
    Cat findByName(String name);
}
