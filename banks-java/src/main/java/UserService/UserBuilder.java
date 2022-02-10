package UserService;

import java.util.Random;

public class UserBuilder implements IUserBuilder{

    private User _user = new UserService.User();

    public UserBuilder(){
        this.Reset();
    }
    @Override
    public void Reset() {
        this._user = new User();

    }

    @Override
    public User SetName(String name) {
        this._user.setName(name);
        return _user;
    }

    @Override
    public User SetSurname(String surname) {
        this._user.setSurname(surname);
        return _user;
    }

    @Override
    public User SetAdress(String adress) {
        this._user.setSurname(adress);
        return _user;
    }

    @Override
    public User SetPassport(String passport) {
        this._user.setPassport(passport);
        return _user;
    }

    @Override
    public User GetUser() {
        var rnd = new Random();
        User result = this._user;
        this._user.setUId(rnd.nextInt());
        this.Reset();
        return result;
    }
}
