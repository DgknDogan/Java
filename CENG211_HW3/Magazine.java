public class Magazine extends BorrowableItem {
    private final int borrowingCharge = 10;
    private int borrowedDays = borrowItem(getStartBorrow(), getEndBorrow());

    public Magazine(int itemNumber, String title, String priority, String itemType, String authorOrGenre,
            String publisherOrProucer, String customerType, String startBorrow, String endBorrow) {
        super(itemNumber, title, priority, itemType, authorOrGenre, publisherOrProucer, customerType, startBorrow,
                endBorrow);
    }

    // Interface methods.
    @Override
    public int borrowCharge() {
        return borrowedDays * borrowingCharge * PriorityEnum.getPriorityValue(getPriority());
    }

    @Override
    public int lateCharge() {
        return (borrowedDays <= 7) ? 0 : 2;
    }
}
