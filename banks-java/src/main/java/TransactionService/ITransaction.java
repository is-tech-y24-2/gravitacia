package TransactionService;

public interface ITransaction {
    public void ProcessTransaction(BankTransactions bankTransaction);

    public void Cancel(BankTransactions bankTransaction) throws Exception;
}
