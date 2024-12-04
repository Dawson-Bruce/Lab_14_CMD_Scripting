import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class DataSaver
{
    public static void main(String[] args)
    {
        ArrayList<String>tempFile = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        do {
            tempFile.add(SafeInput.getNonZeroLenString(in, "Please enter the first name"));
            tempFile.add(SafeInput.getNonZeroLenString(in, "Please enter the last name"));
            tempFile.add(String.valueOf(SafeInput.getRangedInt(in, "Please enter the ID number", 0, 999999)));
            tempFile.add(SafeInput.getNonZeroLenString(in, "Please enter the email"));
            tempFile.add(String.valueOf(SafeInput.getRangedInt(in, "Please enter the birth year", 0, 9999)));
        }while (SafeInput.getYNConfirm(in, "Continue?"));

        String fileName = SafeInput.getNonZeroLenString(in, "Please enter the file name");
        Path file = Paths.get(new File(System.getProperty("user.dir")).getPath() + "\\src\\" + fileName + ".csv");

        try
        {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            int index = 0;
            for(String entry : tempFile)
            {
                if (index % 5 == 4) {
                    writer.write(entry, 0, entry.length());
                    writer.newLine();
                } else {
                    writer.write(entry + ",", 0, entry.length() + 1);
                }

                index++;
            }
            writer.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

}