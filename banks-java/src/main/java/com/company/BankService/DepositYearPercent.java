package main.java.com.company.BankService;

public class DepositYearPercent {

    public DepositYearPercent(double neededamount, double appropriatepercent)
    {
        NeededAmount = neededamount;
        AppropriatePercent = appropriatepercent;
    }

    public double AppropriatePercent;

    public double getAppropriatePercent() {
        return AppropriatePercent;
    }

    public void setAppropriatePercent(double appropriatePercent) {
        AppropriatePercent = appropriatePercent;
    }

    public double NeededAmount;

    public double getNeededAmount() {
        return NeededAmount;
    }

    public void setNeededAmount(double neededAmount) {
        NeededAmount = neededAmount;
    }
}
