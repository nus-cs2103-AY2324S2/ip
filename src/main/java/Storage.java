import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
    }
    public static void save(State state) {
        StringBuilder sb = new StringBuilder();
        for (Task t : state.getTasks()) {
            List<String> contents = new ArrayList<>();
            contents.add(t.isDone() ? "Y" : "N");
            switch (t) {
                case Todo todo -> {
                    contents.add("TODO");
                    contents.add(t.getDescription());
                }
                case Deadline deadline -> {
                    contents.add("DEADLINE");
                    contents.add(deadline.getDescription());
                    contents.add(deadline.getByWhen());
                }
                case Event event -> {
                    contents.add("EVENT");
                    contents.add(event.getDescription());
                    contents.add(event.getStart());
                    contents.add(event.getDeadline());
                }
                default -> {
                    // TODO: add catch for default
                }
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

    public static State load() {
        State s = new State();
        try {
            File file = new File("state.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split("\\|");
                boolean done = false;
                if (words[0].equals("Y")) {
                    done = true;
                }
                String description = words[2];
                Task t;
                switch (words[1]) {
                    case "TODO":
                        t = new Todo(description, done);
                        break;
                    case "EVENT":
                        t = new Event(description, words[3], words[4], done);
                        break;
                    case "DEADLINE":
                        t = new Deadline(description, words[3], done);
                        break;
                    default:
                        t = new Task("UNKNOWN FORMAT", false);
                        System.out.println("Unexpected case");
                        break;
                }
                s.getTasks().add(t);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return s;
    }
}
