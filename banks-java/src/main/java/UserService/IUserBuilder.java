package UserService;

public interface IUserBuilder {
    void Reset();
    User SetName(String name);
    User SetSurname(String surname);
    User SetAdress(String adress);
    User SetPassport(String passport);
    User GetUser();
}
