package Common;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.lang.annotation.Inherited;
import java.util.Calendar;
import java.util.List;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CommonEntity {
    public abstract Long getId();
    public abstract void setId(Long Id);
    public abstract String getName();
    public abstract void setName(String name);
    public abstract void setBirth(Calendar dateOfBirth);
    public abstract Calendar getBirth();
}
