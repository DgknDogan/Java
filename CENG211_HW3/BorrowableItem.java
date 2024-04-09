import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class BorrowableItem implements IBorrowable, ISearchable {
    private int itemNumber;
    private String title;
    private String priority;
    private String itemType;
    private String authorOrGenre;
    private String publisherOrProucer;
    private String customerType;
    private String startBorrow;
    private String endBorrow;

    public BorrowableItem(int itemNumber, String title, String priority, String itemType, String authorOrGenre,
            String publisherOrProucer, String customerType, String startBorrow, String endBorrow) {
        this.itemNumber = itemNumber;
        this.title = title;
        this.priority = priority;
        this.itemType = itemType;
        this.authorOrGenre = authorOrGenre;
        this.publisherOrProucer = publisherOrProucer;
        this.customerType = customerType;
        this.startBorrow = startBorrow;
        this.endBorrow = endBorrow;
    }

    public abstract int borrowCharge();

    public abstract int lateCharge();

    // Interface methods.
    @Override
    public int borrowItem(String startDate, String endDate) {
        String[] startTime = startDate.split("/");
        String[] endTime = endDate.split("/");

        LocalDate sDate = LocalDate.of(Integer.parseInt(startTime[2]), Integer.parseInt(startTime[1]),
                Integer.parseInt(startTime[0]));
        LocalDate eDate = LocalDate.of(Integer.parseInt(endTime[2]), Integer.parseInt(endTime[1]),
                Integer.parseInt(endTime[0]));

        return (int) ChronoUnit.DAYS.between(sDate, eDate);
    }

    @Override
    public double discount() {
        return borrowCharge() * MemberEnum.getDiscountRate(getCustomerType());
    }

    @Override
    public double calculateTotalCharge() {
        return borrowCharge() - discount() + lateCharge();
    }

    @Override
    public String isExceed() {
        if (lateCharge() == 0) {
            return "Not Exceeds";
        }
        return "Exceeds";
    }

    @Override
    public String searchItem(String titleName) {
        String message = "";
        if (title.equals(titleName)) {
            message = "Exist " + toString();
        }
        return message;
    }

    @Override
    public String searchItem(String titleName, String type) {
        String message = "";
        if (title.equals(titleName) && itemType.equals(type)) {
            message = "Exist " + toString();
        }
        return message;
    }

    @Override
    public String toString() {
        return String.format(
                "Item Number: %d Title: %s Item Type: %s Borrowing Days: %d days Exceed: %s Total Price: $%f \n",
                getItemNumber(), getTitle(), getItemType(),
                borrowItem(getStartBorrow(), getEndBorrow()),
                isExceed(), calculateTotalCharge());
    }

    // Getter methods.
    public int getItemNumber() {
        return itemNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getPriority() {
        return priority;
    }

    public String getItemType() {
        return itemType;
    }

    public String getAuthorOrGenre() {
        return authorOrGenre;
    }

    public String getPublisherOrProucer() {
        return publisherOrProucer;
    }

    public String getCustomerType() {
        return customerType;
    }

    public String getStartBorrow() {
        return startBorrow;
    }

    public String getEndBorrow() {
        return endBorrow;
    }

}