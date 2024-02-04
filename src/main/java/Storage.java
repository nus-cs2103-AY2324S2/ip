import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String FILE_PATH = "./data/Duke.txt";

    public Storage() {
    }

    public static void loadFileContents() throws DukeException {
        File f = new File(FILE_PATH);
        try {
            if (f.exists()) {
                try (Scanner scanner = new Scanner(f)) {
                    while (scanner.hasNextLine()) {
                        String s = scanner.nextLine();
                        try {
                            Duke.loadLine(s);
                        } catch (DukeException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            } else {
                throw new DukeException("eh walao i cant find the file sia where u put");
            }
        } catch (IOException e) {
            throw new DukeException("omg i cant read");
        }
    }

    /**
     * Writes current tasklist into specified file.
     *
     * @throws DukeException If there is a problem with writing into file.
     */
    public static void writeToFile(ArrayList<Task> list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : list) {
                fw.write(task.writeToFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("omg i cant write sia :( too bad lol");
        }
    }

}
