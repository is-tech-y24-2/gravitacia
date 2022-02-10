package BankInfoBuilder;

import BankService.DepositYearPercent;

import java.util.List;

public interface IBankInfoBuilder {
    void Reset();
    BankInformation SetTrustLimit(double trustlimit);
    BankInformation SetDebitYearlyPercent(double debityearlypercent);
    BankInformation SetCreditLimit(double creditlimit);
    BankInformation SetCreditMonthlyComission(double creditmonthlycomission);
    BankInformation SetDepositYearlyPercent(List<DepositYearPercent> deposityearlypercent);
    BankInformation GetBankInfo();
}
