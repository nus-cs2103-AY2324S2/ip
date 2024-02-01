import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import tasks.*;

public class Storage {

    Storage() {
    }

    public void readFile (File newFile, ArrayList<Task> taskList) throws FileNotFoundException {
        Scanner s = new Scanner(newFile);
        while (s.hasNextLine()) {
            String readLine = s.nextLine();
            String eventType = readLine.split(" \\| ")[0];
            boolean cleared = readLine.split(" \\| ")[1].equals("1");
            String description = readLine.split(" \\| ", 3)[2];
            Task t;
            switch (eventType) {
                case "T":
                    t = new Todo(description);
                    break;
                case "D":
                    t = new Deadline(description.split(" \\| ")[0], LocalDate.parse(description.split(" \\| ")[1]));
                    break;
                case "E":
                    t = new Event(description.split(" \\| ")[0], LocalDate.parse(description.split(" \\| ")[1]), LocalDate.parse(description.split(" \\| ")[2]));
                    break;
                default:
                    t = new Task("errorTask");
            }
            if (cleared) {
                t.toggleCompletion();
            }
            taskList.add(t);
        }
    }

    public void refreshFile(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter("./data/Steven.txt");
        for(Task t : list) {
            String description = t.storeFormat() + "\n";
            fw.write(description);
        }
        fw.close();
    }
}
