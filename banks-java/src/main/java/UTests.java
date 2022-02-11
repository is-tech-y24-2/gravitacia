import BankInfoBuilder.BankInfoBuilder;
import BankInfoBuilder.IBankInfoBuilder;
import BankService.Bank;
import BankService.DepositYearPercent;
import UserService.IUserBuilder;
import UserService.User;
import UserService.UserBuilder;
import Utilities.DateProvider;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UTests {

    @Test
    public void AddUsersMakeMoney() throws Exception {
        ArrayList<User> users = new ArrayList<User>();
        IUserBuilder userBuilder = new UserBuilder();
        IBankInfoBuilder bankInfoBuilder = new BankInfoBuilder();

        userBuilder.SetName("Alex");
        userBuilder.SetSurname("kos");
        userBuilder.SetAdress("asd");
        userBuilder.SetPassport("123123");
        User user1 = userBuilder.GetUser();
        users.add(user1);

        userBuilder.SetName("Egor");
        userBuilder.SetSurname("Lol");
        userBuilder.SetAdress("asd");
        userBuilder.SetPassport("321321");
        User user2 = userBuilder.GetUser();
        users.add(user2);

        var depositspercents = new ArrayList<DepositYearPercent>();
        depositspercents.add(new DepositYearPercent(0, 5));
        depositspercents.add(new DepositYearPercent(50000, 5.5));
        depositspercents.add(new DepositYearPercent(100000, 6));
        bankInfoBuilder.SetTrustLimit(100000);
        bankInfoBuilder.SetDebitYearlyPercent(3.65);
        bankInfoBuilder.SetCreditLimit(-100000);
        bankInfoBuilder.SetCreditMonthlyComission(500);
        bankInfoBuilder.SetDepositYearlyPercent(depositspercents);
        var bankInfo = bankInfoBuilder.GetBankInfo();
        var bank = new Bank(0, bankInfo);

        bank.NewDebitAccount(Integer.toString(user1.getUId()), users, 10000, new DateProvider(21, 2, 2022));
        bank.NewDebitAccount(Integer.toString(user2.getUId()), users, 5000, new DateProvider(21, 2, 2022));

        bank.Withdraw(5000, user1.getUId(), new DateProvider(21, 2, 2022));
        bank.Replenish(5000, user2.getUId());

        assertEquals(bank.Accounts.get(bank.FindAccount(user1.getUId())).Money, 5000);
        assertEquals(bank.Accounts.get(bank.FindAccount(user2.getUId())).Money, 10000);
    }

        @Test
    public void CancelTransaction() throws Exception {

        var users = new ArrayList<User>();
        IUserBuilder userBuilder = new UserBuilder();
        IBankInfoBuilder bankInfoBuilder = new BankInfoBuilder();

        userBuilder.SetName("Alex");
        userBuilder.SetSurname("kos");
        userBuilder.SetAdress("asd");
        userBuilder.SetPassport("123123");
        User user1 = userBuilder.GetUser();
        users.add(user1);

        userBuilder.SetName("Egor");
        userBuilder.SetSurname("Lol");
        userBuilder.SetAdress("asd");
        userBuilder.SetPassport("321321");
        User user2 = userBuilder.GetUser();
        users.add(user2);

        var depositspercents = new ArrayList<DepositYearPercent>();
        depositspercents.add(new DepositYearPercent(0, 5));
        depositspercents.add(new DepositYearPercent(50000, 5.5));
        depositspercents.add(new DepositYearPercent(100000, 6));
        bankInfoBuilder.SetTrustLimit(100000);
        bankInfoBuilder.SetDebitYearlyPercent(3.65);
        bankInfoBuilder.SetCreditLimit(-100000);
        bankInfoBuilder.SetCreditMonthlyComission(500);
        bankInfoBuilder.SetDepositYearlyPercent(depositspercents);
        var bank = new Bank(0, bankInfoBuilder.GetBankInfo());

        bank.NewDebitAccount(Integer.toString(user1.getUId()), users, 10000, new DateProvider(21, 2, 2022));
        bank.NewDebitAccount(Integer.toString(user2.getUId()), users, 5000, new DateProvider(21, 2, 2022));

        bank.Withdraw(5000, user1.getUId(), new DateProvider(21, 2, 2022));
        bank.Replenish(5000, user2.getUId());

        bank.Transfer(5000, user1.getUId(), user2.getUId(), new DateProvider(21, 2, 22));

        assertEquals(bank.Accounts.get(bank.FindAccount(user2.getUId())).Money, 15000);
        assertEquals(bank.Accounts.get(bank.FindAccount(user1.getUId())).Money, 0);
    }

}
