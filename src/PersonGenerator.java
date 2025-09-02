import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {
        ArrayList <String> peoples = new ArrayList <>();
        Scanner fromUser = new Scanner(System.in);
        boolean met = false;

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath(), "src", "PersonTestData.txt");

        String record = "";
        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        Integer YearOfBirth = 0;

        do {
            ID = SafeInput.getNonZeroLenString(fromUser, "Enter person ID [6 digits]");
            firstName = SafeInput.getNonZeroLenString(fromUser, "Enter person first name");
            lastName = SafeInput.getNonZeroLenString(fromUser, "Enter person last name");
            title = SafeInput.getNonZeroLenString(fromUser, "Enter person title");
            YearOfBirth = SafeInput.getRangedInt(fromUser, "Enter person year of birth", 1000, 9999);

            record = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YearOfBirth;
            peoples.add(record);

            met = SafeInput.getYNConfirm(fromUser, "Are you done?" );
        }while(!met);

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!

            for(String rec : peoples)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
