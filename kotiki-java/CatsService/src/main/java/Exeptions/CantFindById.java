package Exeptions;

public class CantFindById extends Exception{
    public CantFindById(String message) {
        super(message);
    }
}