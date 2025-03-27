/**
 * Manages a bank account with functionalities
 * such as depositing, withdrawing, and balance enquiry
 *
 * @author Ted , Mohammand
 * @version 1.0
 * */

public class BankAccount {

    private String accountNum;
    private int balanceUSD;

    /** Constructor of BankAccount Object.
     * @param accountNum A string of account Number
     * @param initialBalanceUSD initial balance of bank account in USD
    */
    public void BankAccount(final String accountNum, int initialBalanceUSD ) {
        this.accountNum = accountNum;
        this.balanceUSD = initialBalanceUSD;
    }

    /**
     * To get balance of account in USD.*/
    public int getBalanceUsd() {
        return this.balanceUSD;
    }

    /**
     * To get account Number.*/
    public String getAccountNum() {
        return accountNum;
    }

    /** To deposit into account.
     * @param amount to deposit in USD
     * */
    private void deposit(final int amount) {
        this.balanceUSD += amount;
    }

    /** To withdraw from account.
     * @param amount to withdraw from account USD
     * */
    private void withdraw(final int amount) {
        this.balanceUSD -= amount;
    }

    /**
     * To transfer balance from one account to another.
     * @param targetAccount the account to send money to
     * @param originatingAccountNum the account number of originating account
     *                              to send money from
     * @param amountToTransfer the amount to send
     * */
    private void transferToBank(final BankAccount targetAccount,
                                 final String originatingAccountNum,
                                 final int amountToTransfer)
    {
        if (this.accountNum.equals(originatingAccountNum)){
            this.withdraw(amountToTransfer);
            targetAccount.deposit(amountToTransfer);
        }
    }



}
