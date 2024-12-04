import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileScan
{
    public static void main(String[] args)
    {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try {
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

            if (args.length > 0 || chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                Path file;
                if (args.length > 0 ) {
                    selectedFile = Paths.get(System.getProperty("user.dir") + "\\" + args[0]).toFile();
                    file = selectedFile.toPath();
                } else {
                    System.out.println("Reading JFileChooser input..");
                    selectedFile = chooser.getSelectedFile();
                    file = selectedFile.toPath();
                }
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                int line = 0;
                int words = 0;
                int chars = 0;
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;
                    words += rec.split("[ ]").length;
                    chars += rec.length();
                }
                reader.close();
                System.out.println("\nSuccessfully read file " + selectedFile.getName() + "\n\n" + line + " Lines\n" + words + " Words\n" + chars + " Characters");
            } else
            {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}