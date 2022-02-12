package main.java.com.company.UserService;

public interface UserBuilder {
    void Reset();
    User SetName(String name);
    User SetSurname(String surname);
    User SetAdress(String adress);
    User SetPassport(String passport);
    User GetUser();
}
