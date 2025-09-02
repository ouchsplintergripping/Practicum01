import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader {
    public static void main (String[] args) {
        JFileChooser picker = new JFileChooser();
        File selectedFile;

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            picker.setCurrentDirectory(workingDirectory);

            if(picker.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = picker.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                int line = 0;
                System.out.printf("%-9s %-12s %-21s %-9s", "ID", "Name", "Description", "Cost");
                System.out.print("\n");
                System.out.print("==================================================");
                while(reader.ready()) {
                    String rec = reader.readLine();
                    line++;
                    String[] data = rec.split(",");
                    System.out.printf("\n%-9s %-12s %-21s %-9s", data[0], data[1], data[2], data[3]);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File find error!");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
