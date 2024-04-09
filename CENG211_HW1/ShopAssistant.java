package G21_CENG211_HW1;

public class ShopAssistant {
    private int shopAssistantID;
    private String shopAssistantName;
    private String shopAssitantSurname;
    private String shopAssitantNumber;

    private int seniority;
    private double weeklySalary;
    private Transaction[] transactions = new Transaction[15];

    public ShopAssistant(int shopAssistantID, String shopAssistantName, String shopAssitantSurname,
            String shopAssitantNumber) {
        this.shopAssistantID = shopAssistantID;
        this.shopAssistantName = shopAssistantName;
        this.shopAssitantSurname = shopAssitantSurname;
        this.shopAssitantNumber = shopAssitantNumber;
    }

    public void setTransactions(Transaction[] transactions) {
        this.transactions = transactions;
    }

    public double calculateCommission() {
        double totalRevenue = 0;
        for (Transaction transaction : transactions) {
            totalRevenue += transaction.getTotalPrice();
        }
        double commissionRate = (totalRevenue > 7500) ? 0.03 : 0.01;
        return totalRevenue * commissionRate;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }

    public void setSalary(double weeklySalary) {
        this.weeklySalary = weeklySalary;
    }

    public String getShopAssitantNumber() {
        return shopAssitantNumber;
    }

    public int getShopAssistantID() {
        return shopAssistantID;
    }

    public String getShopAssistantName() {
        return shopAssistantName;
    }

    public String getShopAssitantSurname() {
        return shopAssitantSurname;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }
}
