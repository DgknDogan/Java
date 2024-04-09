package G21_CENG211_HW1;

import java.util.Random;

public class TransactionManagement {
    private Transaction[] transactions = new Transaction[1500];
    private Transaction[][] pairedTransactions = new Transaction[100][15];
    private SalaryManagement salaryManagement;

    private ShopAssistant[] shopAssistantsArray = new ShopAssistant[100];

    public TransactionManagement() {
        createTransactions();
        pairAssistantsWithTransaction();
        createPairedTransactions();
        createPairedTransactions();
    }

    public Transaction[] createTransactions() {
        for (int i = 0; i < 1500; i++) {
            Transaction transaction = new Transaction(i, selectRandomProducts(), 0, 0);
            transactions[i] = transaction;
        }
        return transactions;
    }

    public ShopAssistant[] pairAssistantsWithTransaction() {
        Random random = new Random();
        shopAssistantsArray = FileOperations.getShopAssistants();

        for (int i = 0; i < shopAssistantsArray.length; i++) {
            Transaction[] transactionsArray = new Transaction[15];
            for (int j = 0; j < 15; j++) {
                int randomIndex = random.nextInt(1500);
                transactionsArray[j] = transactions[randomIndex];
            }
            shopAssistantsArray[i].setTransactions(transactionsArray);
        }
        salaryManagement = new SalaryManagement(shopAssistantsArray);
        salaryManagement.setAssistantsSeniority();
        salaryManagement.setAssistantsSalary();
        return shopAssistantsArray;
    }

    public Transaction[][] createPairedTransactions() {
        for (int i = 0; i < shopAssistantsArray.length; i++) {
            for (int j = 0; j < shopAssistantsArray[i].getTransactions().length; j++) {
                pairedTransactions[i][j] = shopAssistantsArray[i].getTransactions()[j];
            }
        }
        return pairedTransactions;
    }

    public Product[] selectRandomProducts() {
        Product[] transactionArray = new Product[3];
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int randomIndex = random.nextInt(FileOperations.getProducts().length);
            transactionArray[i] = FileOperations.getProducts()[randomIndex];
        }
        return transactionArray;
    }

    public SalaryManagement getSalaryManagement() {
        return salaryManagement;
    }

}
