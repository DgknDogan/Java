import java.util.ArrayList;

public class LibraryManager {
    private ArrayList<BorrowableItem> sortedList;

    public LibraryManager() {
        FileOperations fileOperations = new FileOperations();
        ArrayList<BorrowableItem> itemsList = new ArrayList<>();

        ArrayList<Book> bookList = fileOperations.getBookList();
        ArrayList<Magazine> magazineList = fileOperations.getMagazineList();

        itemsList.addAll(bookList);
        itemsList.addAll(magazineList);

        SortedListADT sortedListADT = new SortedListADT(itemsList);
        sortedList = sortedListADT.sortList();
    }

    public void displayItems() {
        for (BorrowableItem borrowableItem : sortedList) {
            System.out.printf(borrowableItem.toString());
        }
    }

    public void searchWithTitle() {
        String message = "";
        for (BorrowableItem borrowableItem : sortedList) {
            message = borrowableItem.searchItem("Animal Farm");
            if (!message.isEmpty()) {
                System.out.println(message);
                break;
            }
        }
        if (message.isEmpty()) {
            System.out.println("Do not exist");
        }
    }

    public void searchWithType() {
        String message = "";
        for (BorrowableItem borrowableItem : sortedList) {
            message = borrowableItem.searchItem("History of Art", "book");
            if (!message.isEmpty()) {
                System.out.println(message);
                break;
            }
        }
        if (message.isEmpty()) {
            System.out.println("Do not exist");
        }
    }

    public ArrayList<BorrowableItem> getSortedList() {
        return sortedList;
    }

}
