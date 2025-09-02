import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonReader {
    public static void main (String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                int line = 0;
                System.out.printf("%-9s %-12s %-12s %-9s %-9s", "ID","Firstname","Lastname","Title","Birth Year\n");
                System.out.print("========================================================");
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;
                    String[] data = rec.split(",");
                    System.out.printf("\n%-9s %-12s %-12s %-9s %-9s", data[0], data[1], data[2], data[3], data[4]);
                }
                reader.close();
                System.out.println("\n\nData file read.");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        catch (IOException e){

        }
    }
}
