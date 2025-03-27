import java.util.ArrayList;

/**
 * Manages a collection of BankAccount objects,
 * allowing the addition of new accounts and
 * management through unique account IDs.
 *
 * @author Ted , Mohammand
 * @version 1.0
 * */

public class Bank {

    private ArrayList<BankAccount> accountList;

    /**
     * Constructor to instantiate a Bank object.*/
    public Bank() {
        accountList = new ArrayList<BankAccount>();
    }

    public void addAccount(final BankAccount account) {
        accountList.add(account);
    }

    /**To retrieve a bank account object from all bank accounts
     * using account number
     * @param accountNum Account Number
     * */
    public BankAccount retrieveAccount(final String accountNum) {

        for (int i=0; i < accountList.size(); i++) {
            if (accountList.get(i).getAccountNum().equalsIgnoreCase(accountNum)){
                return accountList.get(i);
            }
        }

        throw new IllegalArgumentException("Account not found");
    }


    public int totalBalanceUsd()
    {
        int totalBalance = 0;

        for (BankAccount account : accountList)
        {
            totalBalance += account.getBalanceUsd();
        }

        return totalBalance;
    }
}
