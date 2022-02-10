package AccountType;

import BankInfoBuilder.BankInformation;
import Utilities.DateProvider;
import Utilities.EnumAccountType;

import java.time.Month;
import java.time.Year;
import java.util.Date;

public class DebitAccount extends AccountReference{

    private DateProvider _lastMonthlyPercentCharge;
    private DateProvider _lastDailyPercentCharge;
    private double _yearlyPercent;
    private double _percentPay;



    public DebitAccount(String clientid, double money, DateProvider today, boolean trust, BankInformation bankinfo)
    {
        AccType = EnumAccountType.Debit;
        Id = bankinfo.getAvailibleAccountId();
        ClientId = clientid;
        _percentPay = 0;
        Money = money;
        Trustful = trust;
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
            _percentPay += (_yearlyPercent / 36500) * Money;
            _lastDailyPercentCharge = new DateProvider(today.Day, today.Month, today.Year);
        }
    }

    @Override
    public void Replenish(double amount) {
        Money += amount;
    }

    @Override
    public boolean Withdraw(double amount, DateProvider today) {

        if (Money < amount && (Trustful || amount <= TrustLimit))
        {
            Money -= amount;
            return true;
        }
        else
        {
            return false;
        }
    }
}
