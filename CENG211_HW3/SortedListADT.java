import java.util.ArrayList;

public class SortedListADT {
    private ArrayList<BorrowableItem> itemsList = new ArrayList<>();

    public SortedListADT(ArrayList<BorrowableItem> itemsList) {
        this.itemsList = itemsList;
    }

    public ArrayList<BorrowableItem> sortList() {
        for (int i = 0; i < itemsList.size(); i++) {
            for (int j = 0; j < itemsList.size(); j++) {
                if (itemsList.get(i).getItemNumber() < itemsList.get(j).getItemNumber()) {
                    BorrowableItem deletedItem = itemsList.remove(i);
                    itemsList.add(j, deletedItem);
                }
            }
        }
        return itemsList;
    }
}