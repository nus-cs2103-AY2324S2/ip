package venus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage (String filePath) {
        File folder = new File (filePath.split("/")[0]);
        if (!folder.exists()) { //handling folder does not exist issues
            folder.mkdir();
            System.out.println("Folder does not exist, data folder is created");
        }
        if (!Files.exists(Path.of("data/venus.txt"))) { //handling file does not exist
            File f = new File("data/venus.txt");
        }
        this.filePath = filePath;
    }
    public void saveToFile(String textToAdd) throws FileNotFoundException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            String amendedText = textToAdd.replaceAll("\\[\\s\\]", "|0|")
                    .replaceAll("\\[X\\]", "|1|")
                    .replaceAll("\\[", "")
                    .replaceAll("\\]", "")
                    .replaceAll("\\(by:", "|")
                    .replaceAll("\\)", "")
                    .replaceAll("\\(from:", "|")
                    .replaceAll("\\(to:", "|")
                    .trim();
            fw.write(System.lineSeparator() + amendedText);
            fw.close();
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    public void saveAllFile(ArrayList<Task> data) throws FileNotFoundException {
        try {

            File f = new File(filePath);
            f.delete();
            f.createNewFile();
            FileWriter fw = new FileWriter(filePath, true);
            for (Task d : data) {
                String amendedText = d.toString().replaceAll("\\[\\s\\]", "|0|")
                        .replaceAll("\\[X\\]", "|1|")
                        .replaceAll("\\[", "")
                        .replaceAll("\\]", "")
                        .replaceAll("\\(by:", "|")
                        .replaceAll("\\)", "")
                        .replaceAll("\\(from:", "|")
                        .replaceAll("\\(to:", "|")
                        .trim();
                fw.write(System.lineSeparator() + amendedText);
            }
            fw.close();
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    public void loadFile(ArrayList<Task> ls) throws FileNotFoundException {

        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String[] items = s.nextLine().split("\\|");
                String type = items[0].trim();
                if (items.length == 1) { //this is to handle the blank spaces
                    continue;
                }
                if (type.equals("T")) {
                    boolean b = Integer.parseInt(items[1].trim()) == 1;
                    ls.add(new TODO(items[2].trim(), b));
                } else if (type.equals("D")) {
                    boolean b = Integer.parseInt(items[1].trim()) == 1;
                    ls.add(new Deadline(items[2].trim(), b, items[3].trim()));
                } else if (type.equals("E")) {
                    boolean b = Integer.parseInt(items[1].trim()) == 1;
                    String[] times = items[3].split("to:");
                    ls.add(new Event(items[2].trim(), b, times[0].trim(), times[1].trim(), true));
                } else { //continue if file is corrupted or at the first line
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            Ui.formatResponse("File is not found! " + e.getMessage());
        } catch (DateTimeParseException e) {
            Ui.formatResponse("Invalid date time input or format, please try again");
        }
    }

}
