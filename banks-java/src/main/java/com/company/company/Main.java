package main.java.com.company.company;

import main.java.com.company.AccountType.AccountReference;
import main.java.com.company.BankInfoBuilder.BankInfoBuilderImpl;
import main.java.com.company.BankInfoBuilder.BankInfoBuilder;
import main.java.com.company.BankService.Bank;
import main.java.com.company.BankService.DepositYearPercent;
import main.java.com.company.TransactionService.BankTransactions;
import main.java.com.company.UserService.UserBuilder;
import main.java.com.company.UserService.User;
import main.java.com.company.UserService.UserBuilderImpl;
import main.java.com.company.Utilities.DateProvider;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void ShowClients(ArrayList<User> users)
    {
        System.out.println("-----Clientlist:-------");
        System.out.println("ID\tName\tSurname\tTrusted\tPassprt\tAddress");
        for (User user: users) {
            System.out.println(user.getUId() + "\t" + user.getName() + "\t" + user.getSurname() +
                    "\t" + user.Istrustful() + "\t" + user.getPassport() + "\t" + user.getAddress());
        }
            System.out.println("-----------------------");
        }


    public static void ShowAccounts(ArrayList<AccountReference> accounts)
    {
        System.out.println("-----Accountlist:------");
        System.out.println("ID\tClID\tType\tMoney");
        for(AccountReference account : accounts)
            System.out.println(account.getId() + "\t" + account.getClientId() + "\t" + account.getAccType() + "\t" + account.getMoney());
        System.out.println("-----------------------");
    }

    public static void ShowTransactions(List<BankTransactions> transactions)
    {
        System.out.println("-----Transactions:-----");
        System.out.println("ID\tType\tID1\tID2\tCncld\tMoney");
        for (BankTransactions transaction : transactions)
            System.out.println(transaction.getId() + "\t" + transaction.getType() + "\t"
                    + transaction.getFirstAcc() + "\t" + transaction.getSecAcc() + "\t" + transaction.isCanceled() + "\t" + transaction.getAmount());
        System.out.println("-----------------------");
    }
    public static void main(String[] args) throws Exception {
        var users = new ArrayList<User>();
        var date1 = new Date();
        var date = new DateProvider(date1.getDay(), date1.getMonth(), date1.getYear());
        var depositspercents = new ArrayList<DepositYearPercent>();
        depositspercents.add(new DepositYearPercent(0, 5));
        depositspercents.add(new DepositYearPercent(50000, 5.5));
        depositspercents.add(new DepositYearPercent(100000, 6));
        UserBuilder userBuilder = new UserBuilderImpl();
        BankInfoBuilder bankInfoBuilder = new BankInfoBuilderImpl();
        bankInfoBuilder.SetTrustLimit(100000);
        bankInfoBuilder.SetDebitYearlyPercent(3.65);
        bankInfoBuilder.SetCreditLimit(-100000);
        bankInfoBuilder.SetCreditMonthlyComission(500);
        bankInfoBuilder.SetDepositYearlyPercent(depositspercents);
        var bank = new Bank(0, bankInfoBuilder.GetBankInfo());

        String ans = "1";
        while (ans == "1")
        {
            System.out.println("Choose one option");
            System.out.println("1. Create users bank account");
            System.out.println("2. Choose account type");
            System.out.println("3. Withdraw some cash");
            System.out.println("4. Replenish some cash");
            System.out.println("5. Transfer money");
            System.out.println("6. Show clients");
            System.out.println("7. Show accounts");
            System.out.println("8. Show transactions");
            System.out.println("0. Close");
            String key = System.in.toString();

            if (key == "1")
            {
                System.out.println("Write client name");
                userBuilder.SetName(System.in.toString());

                System.out.println("Write client surname");
                userBuilder.SetSurname(System.in.toString());

                System.out.println("Do you want add address? (type y or yes)");
                if (System.in.toString() == "y" || System.in.toString() == "yes")
                {
                    userBuilder.SetAdress(System.in.toString());
                }
                    else if (System.in.toString() == "n" || System.in.toString() == "no")
                {
                    System.out.println("Ok, next step, but we can't trust you");
                }

                System.out.println("Do you want add passport? (type y or yes)");
                if (System.in.toString() == "y" || System.in.toString() == "yes")
                {
                    userBuilder.SetPassport(System.in.toString());
                }
                    else if (System.in.toString() == "n" || System.in.toString() == "no")
                {
                    System.out.println("Wow, we can't trust you!");
                }

                users.add(userBuilder.GetUser());
            }

            if (key == "2")
            {
                System.out.println("Now choose a type of account");
                System.out.println("1. Debit account");
                System.out.println("2. Credit account");
                System.out.println("3. Deposit account");
                String accType = System.in.toString();

                if (accType == "1")
                {
                    System.out.println("Okay, choose our hero");
                    ShowClients(users);
                    System.out.println("Type an UId");
                    String uid = System.in.toString();
                    System.out.println("Type a count of money");
                    int money = System.in.read();
                    bank.NewDebitAccount(uid, users, money, date);
                }

                if (accType == "2")
                {
                    System.out.println("Okay, choose our hero");
                    ShowClients(users);
                    System.out.println("Type an UId");
                    String uid = System.in.toString();
                    System.out.println("Type a count of money");
                    int money = System.in.read();
                    bank.NewCreditAccount(uid, users, money, date);
                }

                if (accType == "3")
                {
                    System.out.println("Okay, choose our hero");
                    ShowClients(users);
                    System.out.println("Type an UId");
                    String uid = System.in.toString();
                    System.out.println("Type a count of money");
                    int money = System.in.read();
                    bank.NewDepositAccount(uid, users, money, date, new DateProvider(21, 01, 2022));
                }
            }

            if (key == "3")
            {
                System.out.println("Okay, choose our hero");
                ShowClients(users);
                String uid = System.in.toString();
                System.out.println("Type an UId");
                System.out.println("Type amount sum");
                int amount = System.in.read();
                bank.Withdraw(amount, Integer.parseInt(uid), date);
                System.out.println("Withdrawal of " + amount + "from " + uid);
            }

            if (key == "4")
            {
                System.out.println("Okay, choose our hero");
                ShowClients(users);
                String uid = System.in.toString();
                System.out.println("Type an UId");
                System.out.println("Type amount sum");
                int amount = System.in.read();
                bank.Replenish(amount, Integer.parseInt(uid));
                System.out.println("Replenishment of" + amount + "to " + uid);
            }

            if (key == "5")
            {
                System.out.println("Okay, choose our heroes");
                ShowClients(users);
                System.out.println("Type an UIds of 1st user");
                int fromId = System.in.read();
                System.out.println("Type an UIds of 2nd user");
                int toId = System.in.read();
                System.out.println("Type amount sum");
                int amount = System.in.read();
                bank.Transfer(amount, fromId, toId, date);
                System.out.println("Transfer of " + amount + "from " + fromId + "to " + toId);
            }

            if (key == "6")
            {
                ShowClients(users);
            }

            if (key == "7")
            {
                ShowAccounts(bank.Accounts);
            }

            if (key == "8")
            {
                ShowTransactions(bank.Transactions);
            }

            System.out.println("Type anything");
            ans = System.in.toString();
        }
    }
}
