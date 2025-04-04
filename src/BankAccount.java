/**
 * Manages a bank account with functionalities
 * such as depositing, withdrawing, and balance enquiry
 *
 * @author Ted , Mohammand
 * @version 1.0
 * */

public class BankAccount {

    final private String accountNum;
    private int balanceUSD;

    /** Constructor of BankAccount Object.
     * @param accountNum A string of account Number
     * @param initialBalanceUSD initial balance of bank account in USD
    */
    public BankAccount(final String accountNum, int initialBalanceUSD ) {

        if(accountNum == null || accountNum.isBlank())
        {
            throw new IllegalArgumentException("Invalid account number.");
        }

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
    public void deposit(final int amount) {

        if(amount <= 0)
        {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }

        this.balanceUSD += amount;
    }

    /** To withdraw from account.
     * @param amount to withdraw from account USD
     * */
    public void withdraw(final int amount) {

        if(amount <= 0)
        {
            throw new IllegalArgumentException("Withdraw amount must be greater than zero.");
        }

        if (amount <= this.balanceUSD)
        {
            this.balanceUSD -= amount;
        } else
        {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

    /**
     * To transfer balance from one account to another.
     * @param targetAccount the account to send money to
     * @param originatingAccountNum the account number of originating account
     *                              to send money from
     * @param amountToTransfer the amount to send
     * */
    public void transferToBank(final BankAccount targetAccount,
                                 final String originatingAccountNum,
                                 final int amountToTransfer)
    {
        if (this.accountNum.equals(originatingAccountNum)){
            this.withdraw(amountToTransfer);
            targetAccount.deposit(amountToTransfer);
        }
        else
        {
            throw new IllegalArgumentException("Invalid account number.");
        }
    }
}
