public interface IBorrowable {
    public int borrowItem(String startDate, String endDate);

    public int borrowCharge();

    public int lateCharge();

    public double discount();

    public double calculateTotalCharge();

    public String isExceed();
}