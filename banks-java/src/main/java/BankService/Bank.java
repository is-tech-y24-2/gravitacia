package BankService;

import AccountType.AccountReference;
import AccountType.CreditAccount;
import AccountType.DebitAccount;
import AccountType.DepositAccount;
import BankInfoBuilder.BankInformation;
import TransactionService.*;
import UserService.User;
import Utilities.DateProvider;

import java.util.ArrayList;

public class Bank {

    public Bank(int id, BankInformation bankinfo)
    {
        Id = id;
        Accounts = new ArrayList<AccountReference>();
        Transactions = new ArrayList<BankTransactions>();
    }

    public int Id;
    public ArrayList<AccountReference> Accounts;
    public ArrayList<BankTransactions> Transactions;
    public BankInformation BankInfo;

    public void setId(int id) {
        Id = id;
    }

    public BankInformation getBankInfo() {
        return BankInfo;
    }

    public ArrayList<AccountReference> getAccounts() {
        return Accounts;
    }

    public int getId() {
        return Id;
    }

    public ArrayList<BankTransactions> getTransactions() {
        return Transactions;
    }

    public void setAccounts(ArrayList<AccountReference> accounts) {
        Accounts = accounts;
    }

    public void setBankInfo(BankInformation bankInfo) {
        BankInfo = bankInfo;
    }

    public void setTransactions(ArrayList<BankTransactions> transactions) {
        Transactions = transactions;
    }

    public void NewDebitAccount(String clientid, ArrayList<User> users, double money, DateProvider today) throws Exception {
        int cl = FindClient(clientid, users);
        Accounts.add(new DebitAccount(clientid, money, today, users.get(cl).Istrustful(), BankInfo));
        BankInfo.setAvailibleAccountId(BankInfo.getAvailibleAccountId()+1);
    }

    public void NewDepositAccount(String clientid, ArrayList<User> users, double money, DateProvider today, DateProvider limitdue) throws Exception {
        int cl = FindClient(clientid, users);
        Accounts.add((new DepositAccount(clientid, money, today, users.get(cl).Istrustful(), BankInfo, limitdue)));
        BankInfo.setAvailibleAccountId(BankInfo.getAvailibleAccountId()+1);
    }

    public void NewCreditAccount(String clientid, ArrayList<User> users, double money, DateProvider today) throws Exception {
        int cl = FindClient(clientid, users);
        Accounts.add(new CreditAccount(clientid, money, today, users.get(cl).Istrustful(), BankInfo));
        BankInfo.setAvailibleAccountId(BankInfo.getAvailibleAccountId()+1);
    }

    public void DailyAccountReNewal(DateProvider today)
    {
        for (AccountReference account: Accounts) {
            account.ReNew(today);
        }
    }

    public void Cancel(String id) throws Exception {
        int i = FindTransaction(id);
        if (Transactions.get(i).isCanceled())
        {
            throw new Exception("Transaction was already canceled");
        }
        else
        {
            Transactions.get(i).getType().Cancel(Transactions.get(i));
        }
    }

    public void Replenish(double amount, int toId) throws Exception {
        ITransaction replenish = new Replenishment();
        int id = FindAccount(toId);
        replenish.ProcessTransaction(new BankTransactions(Accounts.get(id), amount, BankInfo));
        BankInfo.setAvailibleTransactionId(BankInfo.getAvailibleTransactionId()+1);
    }

    public void Withdraw(double amount, int fromId, DateProvider today) throws Exception {
        var withdraw = new Withdrawal();
        int id = FindAccount(fromId);
        withdraw.ProcessTransaction(new BankTransactions(Accounts.get(id), amount, BankInfo));
        BankInfo.setAvailibleTransactionId(BankInfo.getAvailibleTransactionId()+1);
    }

    public void Transfer(double amount, int fromId, int toId, DateProvider today) throws Exception {
        ITransaction transfer = new Transfer();
        int id1 = FindAccount(fromId);
        int id2 = FindAccount(toId);
        transfer.ProcessTransaction(new BankTransactions(Accounts.get(id1), Accounts.get(id2), amount, BankInfo));
        BankInfo.setAvailibleTransactionId(BankInfo.getAvailibleTransactionId()+1);
    }

    public int FindAccount(int id) throws Exception {
        int t = 0;
        int ans = -1;
        while (t < Accounts.size())
        {
            if ((Accounts.get(t).Id) == id)
            {
                ans = t;
                break;
            }

            t++;
        }

        if (ans == -1)
            throw new Exception("Requested ID does not exists");
        return ans;
    }

    public int FindClient(String id, ArrayList<User> users) throws Exception {
        int t = 0;
        int ans = -1;
        while (t < users.size())
        {
            if (Integer.toString(users.get(t).getUId()) == id)
            {
                ans = t;
                break;
            }

            t++;
        }

        if (ans == -1)
            throw new Exception("Requested ID does not exists");
        return ans;
    }

    public int FindTransaction(String id) throws Exception {
        int t = 0;
        int ans = -1;
        while (t < Transactions.size())
        {
            if (Transactions.get(t).getId() == id)
            {
                ans = t;
                break;
            }

            t++;
        }

        if (ans == -1)
            throw new Exception("Requested ID does not exists");
        return ans;
    }
}
