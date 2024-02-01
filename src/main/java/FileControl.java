import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileControl {
    public static void saveToFile(String filePath, String textToAdd) throws FileNotFoundException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            String amendedText = textToAdd.replaceAll("\\[\\s\\]", "|0|")
                    .replaceAll("\\[X\\]", "|1|")
                    .replaceAll("\\[", "")
                    .replaceAll("\\]", "")
                    .replaceAll("\\(by:", "|")
                    .replaceAll("\\)", "")
                    .replaceAll("\\(from:", "|")
                    .trim();
            fw.write(amendedText + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    public static void loadFile(String filePath, ArrayList<Task> ls) throws FileNotFoundException {
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String[] items = s.nextLine().split("|");
                String type = items[0].trim();
                if (type.equals("T")) {
                    boolean b = Integer.parseInt(items[1].trim()) == 1;
                    ls.add(new TODO(items[2].trim(), b));
                } else if (type.equals("D")) {
                    boolean b = Integer.parseInt(items[1].trim()) == 1;
                    ls.add(new Deadline(items[2].trim(), b, items[3].trim()));
                } else if (type.equals("E")) {
                    boolean b = Integer.parseInt(items[1].trim()) == 1;
                    ls.add(new Event(items[2].trim(), b, items[3].trim(), ""));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File is not found! " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Oops, something is wrong! " + e.getMessage());
        }
    }

}
