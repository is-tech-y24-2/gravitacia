package main.java.com.company.AccountType;

import main.java.com.company.BankInfoBuilder.BankInformation;
import main.java.com.company.Utilities.DateProvider;
import main.java.com.company.Utilities.EnumAccountType;

public class DebitAccount extends AccountReference{

    private DateProvider _lastMonthlyPercentCharge;
    private DateProvider _lastDailyPercentCharge;
    private double _yearlyPercent;
    private double _percentPay;
    private double TrustLimit;
    private boolean trustful = isTrustful();



    public DebitAccount(String clientid, double money, DateProvider today, boolean trust, BankInformation bankinfo)
    {
        this.setAccType(EnumAccountType.Debit);
        this.setId(bankinfo.getAvailibleAccountId());
        this.setClientId(clientid);
        _percentPay = 0;
        this.setMoney(money);
        this.setTrustful(trust);
        _lastMonthlyPercentCharge = new DateProvider(today.Day, today.Month, today.Year);
        _lastDailyPercentCharge = new DateProvider(today.Day, today.Month, today.Year);
        _yearlyPercent = bankinfo.getDebitYearlyPercent();
        TrustLimit = bankinfo.getTrustLimit();
    }

    @Override
    public void ReNew(DateProvider today) {

        if (today.Year > _lastMonthlyPercentCharge.Year ||
                (today.Year == _lastMonthlyPercentCharge.Year && today.Month > _lastMonthlyPercentCharge.Month))
        {
            _lastMonthlyPercentCharge = new DateProvider(today.Day, today.Month, today.Year);
            Replenish(_percentPay);
            _percentPay = 0;
        }

        if (!today.equals(_lastDailyPercentCharge))
        {
            _percentPay += (_yearlyPercent / 36500) * getMoney();
            _lastDailyPercentCharge = new DateProvider(today.Day, today.Month, today.Year);
        }
    }

    @Override
    public void Replenish(double amount) {
        setMoney(getMoney() + amount);
    }

    @Override
    public boolean Withdraw(double amount, DateProvider today) {

        if (getMoney() < amount && (isTrustful() || amount <= TrustLimit))
        {
            setMoney(getMoney() - amount);
            return true;
        }
        else
        {
            return false;
        }
    }
}
