package main.java.com.company.TransactionService;

public class Transfer implements ITransaction{
    @Override
    public void ProcessTransaction(BankTransactions bankTransaction) {
        bankTransaction.getFirstAcc().Withdraw(bankTransaction.getAmount());
        bankTransaction.getSecAcc().Replenish(bankTransaction.getAmount());
    }

    @Override
    public void Cancel(BankTransactions bankTransaction) throws Exception {
        boolean t = bankTransaction.getSecAcc().Withdraw(bankTransaction.getAmount());
        if (t)
        {
            bankTransaction.getFirstAcc().Replenish(bankTransaction.getAmount());
            bankTransaction.setCanceled(true);
        }
        else
        {
            throw new Exception("Transaction cannot be canceled");
        }

    }
}
