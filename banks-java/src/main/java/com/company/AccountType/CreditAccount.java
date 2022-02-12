package main.java.com.company.AccountType;

import main.java.com.company.BankInfoBuilder.BankInformation;
import main.java.com.company.Utilities.DateProvider;
import main.java.com.company.Utilities.EnumAccountType;

public class CreditAccount extends AccountReference{

    private double creditLimit;
    private DateProvider lastComissionWithdrawal;
    private double comissionWithdrawal;
    private boolean accountWasNegative;
    private double TrustLimit = this.getTrustLimit();

    public CreditAccount(String clientid, double money, DateProvider today, boolean trust, BankInformation bankinfo)
    {
        this.setAccType(EnumAccountType.Credit);
        this.setId(bankinfo.getAvailibleAccountId());
        this.setClientId(clientid);
        this.setMoney(money);
        this.setTrustful(trust);
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
                setMoney(getMoney() - comissionWithdrawal);

            accountWasNegative = false;
            lastComissionWithdrawal = new DateProvider(today.Day, today.Month, today.Year);
        }

        if (getMoney() < 0)
            accountWasNegative = true;
    }

    @Override
    public void Replenish(double amount) {
        setMoney(getMoney() + amount);
    }

    @Override
    public boolean Withdraw(double amount, DateProvider today) {
        if (getMoney() - amount < creditLimit || (!isTrustful() && amount > TrustLimit))
        {
            return false;
        }
        else
        {
            setMoney(getMoney() - amount);
            if (getMoney() < 0)
                accountWasNegative = true;

            return true;
        }
    }
}
