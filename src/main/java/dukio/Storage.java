package dukio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that can load and save the state of the tasks.
 */
public class Storage {

    /**
     * Enum to represent the type of task.
     */
    enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
    }

    /**
     * Saves the state of the tasks to a file.
     *
     * @param state The state of the app.
     */
    public static void save(State state) {
        StringBuilder sb = new StringBuilder();
        for (Task t : state.getTasks()) {
            List<String> contents = new ArrayList<>();
            contents.add(t.isDone() ? "Y" : "N");
            if (t instanceof Todo) {
                contents.add("TODO");
                contents.add(t.getDescription());
            } else if (t instanceof Deadline) {
                Deadline deadline = (Deadline) t;
                contents.add("DEADLINE");
                contents.add(deadline.getDescription());
                contents.add(deadline.getByWhen().toString());
            } else if (t instanceof Event) {
                Event event = (Event) t;
                contents.add("EVENT");
                contents.add(event.getDescription());
                contents.add(event.getStart().toString());
                contents.add(event.getDeadline().toString());
            }
            String result = String.join("|", contents);
            sb.append(result).append("\n");
        }
        try {
            FileWriter writer = new FileWriter("state.txt");
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("oh no... saving!");
        }
    }

    /**
     * Loads the state of the tasks from a file.
     *
     * @return The state of the app.
     */
    public static State load() {
        State s = new State();
        try {
            File file = new File("state.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\|");
                boolean isDone = false;
                if (words[0].equals("Y")) {
                    isDone = true;
                }
                String description = words[2];
                Task t;
                switch (words[1]) {
                case "TODO":
                    t = new Todo(description, isDone);
                    break;
                case "EVENT":
                    t = new Event(
                            description,
                            LocalDate.parse(words[3]),
                            LocalDate.parse(words[4]),
                            isDone);
                    break;
                case "DEADLINE":
                    t = new Deadline(description, LocalDate.parse(words[3]), isDone);
                    break;
                default:
                    t = new Task("UNKNOWN FORMAT", false);
                    System.out.println("Unexpected case");
                    break;
                }
                s.getTasks().add(t);

            }
            scanner.close();
        } catch (FileNotFoundException e) {

        }
        return s;
    }
}
