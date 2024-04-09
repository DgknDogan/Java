package G21_CENG211_HW1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileOperations {

    public static Product[] getProducts() {
        Product[] products = new Product[90];
        try {
            FileReader fileReader = new FileReader("G21_CENG211_HW1/products.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {

                String[] values = line.split(";");
                String[] value = values[2].split(",");

                Product product = new Product(Integer.parseInt(values[0]), values[1],
                        Double.parseDouble(value[0] + "." + value[1]));
                products[i] = product;

                i++;
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static ShopAssistant[] getShopAssistants() {
        ShopAssistant[] assistantsArray = new ShopAssistant[100];

        try {
            FileReader fileReader = new FileReader("G21_CENG211_HW1/shopAssistants.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {

                String[] values = line.split(";");

                ShopAssistant shopAssistant = new ShopAssistant(Integer.parseInt(values[0]), values[1], values[2],
                        values[3]);

                assistantsArray[i] = shopAssistant;

                i++;
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return assistantsArray;
    }

}
