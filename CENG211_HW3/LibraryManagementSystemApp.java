public class LibraryManagementSystemApp {
    public static void main(String[] args) {
        LibraryManager manager = new LibraryManager();
        manager.displayItems();
        manager.searchWithTitle();
        manager.searchWithType();
    }
}
