package AccountType;

import BankInfoBuilder.BankInformation;
import Utilities.DateProvider;
import Utilities.EnumAccountType;

public class DepositAccount extends AccountReference{

    private DateProvider withdrawalLimitedDue;
    private DateProvider _lastMonthlyPercentCharge;
    private DateProvider _lastDailyPercentCharge;
    private double yearlyPercent;
    private double _percentPay;

    public DepositAccount(String clientid, double money, DateProvider today, boolean trust, BankInformation bankinfo, DateProvider wlimit)
    {
        AccType = EnumAccountType.Deposit;
        Id = bankinfo.getAvailibleAccountId();
        ClientId = clientid;
        Money = money;
        Trustful = trust;
        withdrawalLimitedDue = new DateProvider(wlimit.Day, wlimit.Month, wlimit.Year);
        _lastMonthlyPercentCharge = new DateProvider(today.Day, today.Month, today.Year);
        _lastDailyPercentCharge = new DateProvider(today.Day, today.Month, today.Year);
        TrustLimit = bankinfo.getTrustLimit();
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
        _percentPay += (yearlyPercent / 36500) * Money;
        _lastDailyPercentCharge = new DateProvider(today.Day, today.Month, today.Year);
    }

    @Override
    public void Replenish(double amount) {
        Money += amount;
    }

    @Override
    public boolean Withdraw(double amount, DateProvider today) {
        if (withdrawalLimitedDue.Day > today.Day || Money < amount || (!Trustful && amount > TrustLimit))
        {
            return false;
        }
        else
        {
            Money -= amount;
            return true;
        }
    }
}
