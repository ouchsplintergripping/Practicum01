import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main (String[] args) {
        ArrayList <String> productList = new ArrayList<>();
        String ID = "";
        String name = "";
        String description = "";
        double cost = 0.0;
        Scanner fromUser = new Scanner(System.in);
        boolean met = false;
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath(), "src", "ProductTestData.txt");

        do {
            ID = SafeInput.getNonZeroLenString(fromUser, "Enter product ID");
            name = SafeInput.getNonZeroLenString(fromUser, "Enter product name");
            description = SafeInput.getNonZeroLenString(fromUser, "Enter product description");
            cost = SafeInput.getDouble(fromUser, "Enter product cost");

            String record = ID + ", " + name + ", " + description + ", " + cost;
            productList.add(record);

            met = SafeInput.getYNConfirm(fromUser, "Are you done?");
        } while(!met);

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : productList) {
                writer.write(rec);
                writer.newLine();
            }
            writer.close();
            System.out.println("Successfully wrote data file.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
