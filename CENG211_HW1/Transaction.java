package G21_CENG211_HW1;

import java.util.Random;

public class Transaction {
    private int transactionID;
    private Product[] productArray;
    private double totalPrice;
    private double transactionFee;

    public Transaction(int transactionID, Product[] productArray, double totalPrice, double transactionFee) {
        this.transactionID = transactionID;
        this.productArray = productArray;
        this.totalPrice = calculateTotalPrice();
        this.transactionFee = calculateTransactionFee();
    }

    public double getExpensiveItem() {
        double expensive = productArray[0].getProductPrice();
        for (int i = 0; i < productArray.length; i++) {
            if (productArray[i].getProductPrice() > expensive) {
                expensive = productArray[i].getProductPrice();
            }
        }
        return expensive;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    // Private methods
    private double calculateTotalPrice() {
        Random random = new Random();
        double totalPrice = 0;
        for (int i = 0; i < productArray.length; i++) {
            totalPrice += productArray[i].getProductPrice() * (random.nextInt(10) + 1);
        }
        return totalPrice;
    }

    private double calculateTransactionFee() {
        if (totalPrice <= 499) {
            return totalPrice * 0.01;
        }

        else if (500 <= totalPrice || totalPrice <= 799) {
            return totalPrice * 0.03;
        }

        else if (800 <= totalPrice || totalPrice <= 999) {
            return totalPrice * 0.05;
        }

        else {
            return totalPrice * 0.09;
        }
    }

}
