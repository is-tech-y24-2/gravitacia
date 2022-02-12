package main.java.com.company.UserService;

import java.util.Random;

public class UserBuilderImpl implements UserBuilder {

    private User _user = new main.java.com.company.UserService.User();

    public UserBuilderImpl(){
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
        Random rnd = new Random();
        User result = this._user;
        this._user.setUId(rnd.nextInt(40));
        this.Reset();
        return result;
    }
}
