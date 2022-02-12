package main.java.com.company.BankInfoBuilder;

import main.java.com.company.BankService.DepositYearPercent;

import java.util.List;

public class BankInfoBuilderImpl implements BankInfoBuilder {

    private BankInformation _bankInformation = new BankInformation();

    public BankInfoBuilderImpl(){
        this.Reset();
    }


    @Override
    public void Reset() {
        this._bankInformation = new BankInformation();
    }

    @Override
    public BankInformation SetTrustLimit(double trustlimit) {
        this._bankInformation.setTrustLimit(trustlimit);
        return _bankInformation;
    }

    @Override
    public BankInformation SetDebitYearlyPercent(double debityearlypercent) {
        this._bankInformation.setDebitYearlyPercent(debityearlypercent);
        return _bankInformation;
    }

    @Override
    public BankInformation SetCreditLimit(double creditlimit) {
        this._bankInformation.setCreditLimit(creditlimit);
        return _bankInformation;
    }

    @Override
    public BankInformation SetCreditMonthlyComission(double creditmonthlycomission) {
        this._bankInformation.setCreditMonthlyComission(creditmonthlycomission);
        return _bankInformation;
    }

    @Override
    public BankInformation SetDepositYearlyPercent(List<DepositYearPercent> deposityearlypercent) {
        this._bankInformation.setDepositYearlyPercent(deposityearlypercent);
        return _bankInformation;
    }

    @Override
    public BankInformation GetBankInfo() {
        BankInformation result = this._bankInformation;
        this.Reset();
        return result;
    }
}
