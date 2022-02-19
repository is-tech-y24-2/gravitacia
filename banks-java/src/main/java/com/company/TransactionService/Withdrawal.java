package main.java.com.company.TransactionService;

public class Withdrawal implements ITransaction{
    @Override
    public void ProcessTransaction(BankTransactions bankTransaction) {
        bankTransaction.getFirstAcc().Withdraw(bankTransaction.getAmount());
    }

    @Override
    public void Cancel(BankTransactions bankTransaction) throws Exception {
        bankTransaction.getFirstAcc().Replenish(bankTransaction.getAmount());
        bankTransaction.setCanceled(true);
    }
}
