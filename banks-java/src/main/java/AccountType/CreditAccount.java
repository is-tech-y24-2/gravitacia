package AccountType;

import BankInfoBuilder.BankInformation;
import Utilities.DateProvider;
import Utilities.EnumAccountType;

public class CreditAccount extends AccountReference{

    private double creditLimit;
    private DateProvider lastComissionWithdrawal;
    private double comissionWithdrawal;
    private boolean accountWasNegative;

    public CreditAccount(String clientid, double money, DateProvider today, boolean trust, BankInformation bankinfo)
    {
        AccType = EnumAccountType.Credit;
        Id = bankinfo.getAvailibleAccountId();
        ClientId = clientid;
        Money = money;
        Trustful = trust;
        TrustLimit = bankinfo.getTrustLimit();
        creditLimit = bankinfo.getCreditLimit();
        comissionWithdrawal = bankinfo.getCreditMonthlyComission();
        lastComissionWithdrawal = new DateProvider(today.Day, today.Month, today.Year);
        accountWasNegative = false;
    }

    @Override
    public void ReNew(DateProvider today) {

        if (today.Year > lastComissionWithdrawal.Year ||
                (today.Year == lastComissionWithdrawal.Year && today.Month > lastComissionWithdrawal.Month))
        {
            if (accountWasNegative)
                Money -= comissionWithdrawal;

            accountWasNegative = false;
            lastComissionWithdrawal = new DateProvider(today.Day, today.Month, today.Year);
        }

        if (Money < 0)
            accountWasNegative = true;
    }

    @Override
    public void Replenish(double amount) {
        Money += amount;
    }

    @Override
    public boolean Withdraw(double amount, DateProvider today) {
        if (Money - amount < creditLimit || (!Trustful && amount > TrustLimit))
        {
            return false;
        }
        else
        {
            Money -= amount;
            if (Money < 0)
                accountWasNegative = true;

            return true;
        }
    }
}
