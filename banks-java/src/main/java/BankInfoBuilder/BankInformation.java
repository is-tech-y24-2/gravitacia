package BankInfoBuilder;

import BankService.DepositYearPercent;

import java.util.List;

public class BankInformation {

    private int AvailibleClientId;
    private int AvailibleAccountId;
    private int AvailibleTransactionId;
    private double TrustLimit;
    private double DebitYearlyPercent;
    private double CreditLimit;
    private double CreditMonthlyComission;
    private List<DepositYearPercent> DepositYearlyPercent;

    public BankInformation()
    {
        AvailibleClientId = 0;
        AvailibleAccountId = 0;
        AvailibleTransactionId = 0;
    }

    public double getCreditLimit() {
        return CreditLimit;
    }

    public double getCreditMonthlyComission() {
        return CreditMonthlyComission;
    }

    public double getDebitYearlyPercent() {
        return DebitYearlyPercent;
    }

    public double getTrustLimit() {
        return TrustLimit;
    }

    public int getAvailibleAccountId() {
        return AvailibleAccountId;
    }

    public int getAvailibleClientId() {
        return AvailibleClientId;
    }

    public int getAvailibleTransactionId() {
        return AvailibleTransactionId;
    }

    public List<DepositYearPercent> getDepositYearlyPercent() {
        return DepositYearlyPercent;
    }

    public void setAvailibleAccountId(int availibleAccountId) {
        AvailibleAccountId = availibleAccountId;
    }

    public void setAvailibleClientId(int availibleClientId) {
        AvailibleClientId = availibleClientId;
    }

    public void setAvailibleTransactionId(int availibleTransactionId) {
        AvailibleTransactionId = availibleTransactionId;
    }

    public void setCreditLimit(double creditLimit) {
        CreditLimit = creditLimit;
    }

    public void setCreditMonthlyComission(double creditMonthlyComission) {
        CreditMonthlyComission = creditMonthlyComission;
    }

    public void setDebitYearlyPercent(double debitYearlyPercent) {
        DebitYearlyPercent = debitYearlyPercent;
    }

    public void setDepositYearlyPercent(List<DepositYearPercent> depositYearlyPercent) {
        DepositYearlyPercent = depositYearlyPercent;
    }

    public void setTrustLimit(double trustLimit) {
        TrustLimit = trustLimit;
    }
}

