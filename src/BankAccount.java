/**
 * Manages a bank account with functionalities
 * such as depositing, withdrawing, and balance enquiry
 *
 * @author Ted , Mohammand
 * @version 1.0
 * */

public class BankAccount {

    private String accountNum;
    private double balanceUSD;

    /** Constructor of BankAccount Object.
     * @param accountNum A string of account Number
     * @param initialBalanceUSD initial balance of bank account in USD
    */
    public void BankAccount(final String accountNum, int initialBalanceUSD ) {
        this.accountNum = accountNum;
        this.balanceUSD = initialBalanceUSD;
    }

    /**
     * To get balance of account in USD*/
    private double getBalanceUSD() {
        return this.balanceUSD;
    }

    /** T deposit into account.
     * @param amount to deposit in USD
     * */
    private void deposit(final double amount) {
        this.balanceUSD += amount;
    }

    /** To withdraw from account.
     * @param amount to withdraw from account USD
     * */
    private void withdraw(final double amount) {
        this.balanceUSD -= amount;
    }

}
