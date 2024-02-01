import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Save {
    File file = new File("data/data.txt");

    public Save() {}

    public void loadTasks(ArrayList<Task> tasks) throws IOException {
        if (!file.exists()) {
            handleFileAccessErrors();
        }

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(s, "|");
            String t = st.nextToken().strip();
            boolean done = st.nextToken().strip().equalsIgnoreCase("1");
            String description = st.nextToken().strip();

            if (t.equalsIgnoreCase("t")) {
                tasks.add(new ToDo(description, done));

            } else if (t.equalsIgnoreCase("e")) {
                String[] time =  st.nextToken().strip().split("-");
                tasks.add(new Event(description, done, time[0], time[1]));

            } else if (t.equalsIgnoreCase("d")) {
                tasks.add(new Deadline(description, done, st.nextToken().strip()));

            } else {
                System.out.println("File error, cannot read list");
            }
        }
        scanner.close();
    }

    }

    // Move to error handling class?
    private void handleFileAccessErrors() throws IOException {
        try {
            file.createNewFile();
        } catch (IOException e) {
            new File("./data").mkdirs();
        } finally {
            file.createNewFile();
        }
    }
}