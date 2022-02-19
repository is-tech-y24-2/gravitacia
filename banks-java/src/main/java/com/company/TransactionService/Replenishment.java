package main.java.com.company.TransactionService;

public class Replenishment implements ITransaction{
    @Override
    public void ProcessTransaction(BankTransactions bankTransaction) {
        bankTransaction.getFirstAcc().Replenish(bankTransaction.getAmount());

    }

    @Override
    public void Cancel(BankTransactions bankTransaction) throws Exception {
        boolean t = bankTransaction.getFirstAcc().Withdraw(bankTransaction.getAmount());

        if (t) {
            bankTransaction.setCanceled(true);
        } else{
            throw new Exception();
        }
    }
}
