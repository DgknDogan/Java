import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileOperations {
    private ArrayList<Book> bookList = new ArrayList<Book>();
    private ArrayList<Magazine> magazineList = new ArrayList<Magazine>();

    public FileOperations() {
        readFile();
    }

    public void readFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("items.csv", StandardCharsets.UTF_8));
            String line;

            while ((line = br.readLine()) != null) {
                if (line != null && line.startsWith("\uFEFF")) {
                    line = line.substring(1);
                }
                String[] parcedLine = line.split(";");
                if (parcedLine[3].equals("book")) {
                    Book book = new Book(Integer.parseInt(parcedLine[0]), parcedLine[1],
                            PriorityEnum.getPriorityType(parcedLine[2]), parcedLine[3], parcedLine[4], parcedLine[5],
                            parcedLine[6], parcedLine[7], parcedLine[8]);

                    bookList.add(book);
                } else if (parcedLine[3].equals("magazine")) {
                    Magazine magazine = new Magazine(Integer.parseInt(parcedLine[0]), parcedLine[1],
                            PriorityEnum.getPriorityType(parcedLine[2]), parcedLine[3], parcedLine[4], parcedLine[5],
                            parcedLine[6], parcedLine[7], parcedLine[8]);

                    magazineList.add(magazine);
                }
            }
            br.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public ArrayList<Magazine> getMagazineList() {
        return magazineList;
    }
}
