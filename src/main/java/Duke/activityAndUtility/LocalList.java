package Duke.activityAndUtility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LocalList {
    private final String filePath;

    public LocalList(String filePath) {
        this.filePath = filePath;
    }

    // Load tasks from the file
    public ArrayList<Activity> load() {
        ArrayList<Activity> loadedActivities = new ArrayList<>();
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                Activity activity = null;
                switch (parts[0]) {
                    case "T":
                        activity = new Todo(parts[1], parts[2]);
                        break;
                    case "D":
                        activity = new Deadline(parts[1], parts[2], parts[3]);
                        break;
                    case "E":
                        activity = new Event(parts[1], parts[2], parts[3], parts[4]);
                        break;
                }
                loadedActivities.add(activity);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("File is not there or has been corrupted");
        }
        return loadedActivities;
    }

    // Save tasks to the file
    public void save(ArrayList<Activity> activities) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Activity activity : activities) {
                if (activity instanceof Todo) {
                    writer.write("T | " + ((Todo) activity).act.get(0) + " | " + ((Todo) activity).act.get(1) + "\n");
                } else if (activity instanceof Deadline) {
                    writer.write("D | " + ((Deadline) activity).act.get(0) + " | " + ((Deadline) activity).act.get(1)
                            + " | " + ((Deadline) activity).act.get(2) + "\n");
                } else if (activity instanceof Event) {
                    writer.write("E | " + ((Event) activity).act.get(0) + " | " + ((Event) activity).act.get(1)
                            + " | " + ((Event) activity).act.get(2) + " | " + ((Event) activity).act.get(3) + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the activities.");
        }
    }
}
