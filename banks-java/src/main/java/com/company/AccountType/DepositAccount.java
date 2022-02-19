package main.java.com.company.AccountType;

import main.java.com.company.BankInfoBuilder.BankInformation;
import main.java.com.company.Utilities.DateProvider;
import main.java.com.company.Utilities.EnumAccountType;

public class DepositAccount extends AccountReference{

    private DateProvider withdrawalLimitedDue;
    private DateProvider _lastMonthlyPercentCharge;
    private DateProvider _lastDailyPercentCharge;
    private double yearlyPercent;
    private double _percentPay;
    private double TrustLimit = this.getTrustLimit();
    private boolean trustful = isTrustful();

    public DepositAccount(String clientid, double money, DateProvider today, boolean trust, BankInformation bankinfo, DateProvider wlimit)
    {
        this.setAccType(EnumAccountType.Deposit);
        this.setId(bankinfo.getAvailibleAccountId());
        this.setClientId(clientid);
        this.setMoney(money);
        this.setTrustful(trust);
        withdrawalLimitedDue = new DateProvider(wlimit.Day, wlimit.Month, wlimit.Year);
        _lastMonthlyPercentCharge = new DateProvider(today.Day, today.Month, today.Year);
        _lastDailyPercentCharge = new DateProvider(today.Day, today.Month, today.Year);
        this.setTrustLimit(bankinfo.getTrustLimit());
        _percentPay = 0;

        int i = 0;
        while (i < bankinfo.getDepositYearlyPercent().size())
        {
            if (bankinfo.getDepositYearlyPercent().get(i).NeededAmount <= money)
                yearlyPercent = bankinfo.getDepositYearlyPercent().get(i).AppropriatePercent;
            else
                break;
            i++;
        }
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

        if (today.equals(_lastDailyPercentCharge)) return;
        _percentPay += (yearlyPercent / 36500) * getMoney();
        _lastDailyPercentCharge = new DateProvider(today.Day, today.Month, today.Year);
    }

    @Override
    public void Replenish(double amount) {
        setMoney(getMoney() + amount);
    }

    @Override
    public boolean Withdraw(double amount, DateProvider today) {
        if (withdrawalLimitedDue.Day > today.Day || getMoney() < amount || (!isTrustful() && amount > TrustLimit))
        {
            return false;
        }
        else
        {
            setMoney(getMoney() - amount);
            return true;
        }
    }
}
