package G21_CENG211_HW1;

public class Query {
    private TransactionManagement transactionManagement = new TransactionManagement();
    private Transaction[][] transactions = transactionManagement.createPairedTransactions();
    private ShopAssistant[] assistantArray = transactionManagement.getSalaryManagement().getAssistants();

    public double getHighestTotalPrice() {
        double maxValue = findMax(transactions);
        return maxValue;
    }

    public double findMostLowest() {
        int[] values = findMinIndex(transactions);
        double maxPrice = transactions[values[0]][values[1]].getExpensiveItem();
        return maxPrice;
    }

    public double lowestTransactionFee() {
        double lowestTransactionFee = 0;
        for (int i = 0; i < transactions.length; i++) {
            for (int j = 0; j < transactions[i].length; j++) {
                if (lowestTransactionFee < transactions[i][j].getTransactionFee()) {
                    lowestTransactionFee = transactions[i][j].getTransactionFee();
                }
            }
        }
        return lowestTransactionFee;
    }

    public ShopAssistant getHighestShopAssistant() {
        double highestIncome = 0;
        int id = 0;
        for (int i = 0; i < assistantArray.length; i++) {
            Transaction[] assistantsTransactions = assistantArray[i].getTransactions();
            for (int j = 0; j < assistantsTransactions.length; j++) {
                if (highestIncome < (assistantArray[i].getWeeklySalary() * 4)) {
                    highestIncome = (assistantArray[i].getWeeklySalary() * 4);
                    id = i;
                }

            }
        }
        return assistantArray[id];
    }

    public double calculateTotalRevenue() {
        double totalRevenue = 0;
        for (int i = 0; i < transactions.length; i++) {
            for (int j = 0; j < transactions[i].length; j++) {
                totalRevenue += transactions[i][j].getTotalPrice() + transactions[i][j].getTransactionFee();
            }
        }
        return totalRevenue;
    }

    public double calulateTotalProfit() {
        double assistantsSalaries = 0;
        double totalRevenue = calculateTotalRevenue();
        for (int i = 0; i < assistantArray.length; i++) {
            assistantsSalaries += assistantArray[i].getWeeklySalary() * 4;
        }
        return (totalRevenue - assistantsSalaries);
    }

    public void display() {
        System.out.println("1. The highest-total-price transaction: " +
                String.format("%.2f", getHighestTotalPrice()) + " TL");
        System.out.println("2. The most expensive product in the lowest-price transaction: "
                + String.format("%.2f", findMostLowest()) + " TL");
        System.out.println("3. The lowest transaction fee: " +
                String.format("%.2f", lowestTransactionFee()) + " TL");
        System.out.println(
                "4. The highest-salary shop assistant: " +
                        "ID: " + getHighestShopAssistant().getShopAssistantID()
                        + ", Name Surname: "
                        + getHighestShopAssistant().getShopAssistantName() + " "
                        + getHighestShopAssistant().getShopAssitantSurname()
                        + ", Seniority: " + getHighestShopAssistant().getSeniority()
                        + ", Total salary: "
                        + String.format("%.2f",
                                getHighestShopAssistant().getWeeklySalary())
                        + " TL," + " Weekly basis salary: "
                        + (getHighestShopAssistant().getWeeklySalary()
                                - getHighestShopAssistant().calculateCommission())
                        + " TL," + " Commission: "
                        + String.format("%.2f",
                                getHighestShopAssistant().calculateCommission())
                        + " TL");
        System.out.println("5. The total revenue that is: " +
                String.format("%.2f", calculateTotalRevenue()) + " TL");
        System.out.println("6. The total profit that is earned after paying the shop assistant salaries: " +
                String.format("%.2f", calulateTotalProfit()) + " TL");
    }

    // private methods
    private double findMax(Transaction[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("The array is empty.");
        }
        double max = array[0][0].getTotalPrice();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j].getTotalPrice() > max) {
                    max = array[i][j].getTotalPrice();
                }
            }
        }
        return max;
    }

    private int[] findMinIndex(Transaction[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("The array is empty.");
        }

        double min = array[0][0].getTotalPrice();
        int[] values = new int[2];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j].getTotalPrice() < min) {
                    min = array[i][j].getTotalPrice();
                    values[0] = i;
                    values[1] = j;
                }
            }
        }
        return values;
    }
}
