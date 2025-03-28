import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankApplicationTests {
    private Bank bank1;
    private Bank bank2;
    private BankAccount account1;
    private BankAccount account2;

    @BeforeEach
    void setUp() {
        bank1 = new Bank();
        bank2 = new Bank();
        account1 = new BankAccount("12345", 1000);
        account2 = new BankAccount("67890", 500);
        bank1.addAccount(account1);
        bank2.addAccount(account2);
    }

    @Test
    void depositIncreasesBalanceAndVerify() {
        account1.deposit(200);
        assertEquals(1200, account1.getBalanceUsd());
        account2.deposit(300);
        assertEquals(800, account2.getBalanceUsd());
    }

    @Test
    void withdrawDecreasesBalanceAndVerify() {
        account1.withdraw(200);
        assertEquals(800, account1.getBalanceUsd());
        account2.withdraw(100);
        assertEquals(400, account2.getBalanceUsd());
    }

    @Test
    void cannotWithdrawMoreThanBalanceAndHandleException() {
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> account1.withdraw(1200));
        assertEquals("Insufficient funds", exception1.getMessage());

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> account2.withdraw(600));
        assertEquals("Insufficient funds", exception2.getMessage());
    }

    @Test
    void addingAndRetrievingAccountFromBank() {
        BankAccount newAccount = new BankAccount("54321", 100);
        bank2.addAccount(newAccount);
        assertEquals(newAccount, bank2.retrieveAccount("54321"));

        BankAccount newAccount2 = new BankAccount("11111", 300);
        bank1.addAccount(newAccount2);
        assertEquals(newAccount2, bank1.retrieveAccount("11111"));
    }

    @Test
    void transferBetweenBankAccountsAndVerifyBalances() {
        account1.transferToBank(account2, "12345", 200);
        assertEquals(800, account1.getBalanceUsd());
        assertEquals(700, account2.getBalanceUsd());

        account2.transferToBank(account1, "67890", 100);
        assertEquals(900, account1.getBalanceUsd());
        assertEquals(600, account2.getBalanceUsd());
    }

    @Test
    void totalBalanceCalculationForBanks() {
        assertEquals(1000, bank1.totalBalanceUsd());
        assertEquals(500, bank2.totalBalanceUsd());

        bank1.addAccount(new BankAccount("33333", 200));
        assertEquals(1200, bank1.totalBalanceUsd());
    }

    @Test
    void handlingInvalidAccountRetrieval() {
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> bank1.retrieveAccount("99999"));
        assertEquals("Account not found", exception1.getMessage());

        IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class, () -> bank2.retrieveAccount("00000"));
        assertEquals("Account not found", exception2.getMessage());
    }

    @Test
    void initialBalanceIsCorrect() {
        BankAccount newAccount = new BankAccount("24680", 750);
        assertEquals(750, newAccount.getBalanceUsd());
    }

    @Test
    void withdrawFullBalanceSucceeds() {
        BankAccount newAccount = new BankAccount("13579", 500);
        newAccount.withdraw(500);
        assertEquals(0, newAccount.getBalanceUsd());
    }

    @Test
    void depositThenWithdrawResultsInCorrectBalance() {
        BankAccount newAccount = new BankAccount("99887", 100);
        newAccount.deposit(200);
        newAccount.withdraw(150);
        assertEquals(150, newAccount.getBalanceUsd());
    }

    @Test
    void totalBalanceWithMultipleAccountsIsCorrect() {
        Bank bank = new Bank();
        bank.addAccount(new BankAccount("10001", 200));
        bank.addAccount(new BankAccount("10002", 300));
        bank.addAccount(new BankAccount("10003", 500));
        assertEquals(1000, bank.totalBalanceUsd());
    }

    @Test
    void depositingZeroThrowsException() {
        BankAccount newAccount = new BankAccount("11223", 400);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                newAccount.deposit(0));
        assertEquals("Deposit amount must be greater than zero.", ex.getMessage());
    }

    @Test
    void withdrawingZeroThrowsException() {
        BankAccount newAccount = new BankAccount("33445", 400);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                newAccount.withdraw(0));
        assertEquals("Withdraw amount must be greater than zero.", ex.getMessage());
    }

    @Test
    void addingAccountsWithSameIdAddsBoth() {
        Bank bank = new Bank();
        BankAccount acc1 = new BankAccount("DUPLICATE", 100);
        BankAccount acc2 = new BankAccount("DUPLICATE", 200);
        bank.addAccount(acc1);
        bank.addAccount(acc2);
        assertEquals(300, bank.totalBalanceUsd());
    }
}
