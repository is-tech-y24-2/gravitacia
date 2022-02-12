package main.java.com.company.UserService;

public class User {
    public User()
    {
    }

    private String Passport;
    private String Address;
    private String Surname;
    private String Name;
    private int UId;

    public int getUId() {
        return UId;
    }

    public String getAddress() {
        return Address;
    }

    public String getName() {
        return Name;
    }

    public String getPassport() {
        return Passport;
    }

    public String getSurname() {
        return Surname;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPassport(String passport) {
        Passport = passport;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public void setUId(int UId) {
        this.UId = UId;
    }

    public boolean Istrustful()
    {
        if (Address == null || Passport == null)
            return false;
        else
            return true;
    }
}
