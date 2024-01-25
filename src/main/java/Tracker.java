import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class Tracker {

    /**
     * When a user adds a task, a response of PrependMessages + task is rendered
     * To add a prependMessage: Write the enum and then place the message in brackets
     * e.g. ANGRY("You better get your ass up and"),
     */
    enum PrependMessages {

        DEFAULT("Added: "),
        SUSPENSE("You really want to: "),
        HAPPY("I'm sure you can "),
        DEMEANING("Don't tell me you can't ");
        private final String label;
        private static final Random RNG = new Random();
        PrependMessages(String label) {
            this.label = label;
        }

        private static String getLabel(PrependMessages msg) {
            return msg.label;
        }

        public static String randomMsg() {
            PrependMessages[] pm = values();
            return getLabel(pm[RNG.nextInt(pm.length)]);
        }
    }

    enum AppendMessages {
        DEFAULT(""),
        DISPARAGE(", you sure about that?"),
        ENDEARING(". I'll always be By Your Side");

        private final String label;
        private static final Random RNG = new Random();
        AppendMessages(String label) {
            this.label = label;
        }

        private static String getLabel(AppendMessages msg) {
            return msg.label;
        }

        public static String randomMsg() {
            AppendMessages[] am = values();
            return getLabel(am[RNG.nextInt(am.length)]);
        }
    }
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void ProcessQuery(String s) {
        String[] tokens = s.split(" ");
        ArrayList<String> args = new ArrayList<String>(Arrays.asList(tokens));

        switch (args.getFirst().toLowerCase()) {
            case "list":
                listTasks();
                break;
            case "mark":
                try {
                    int taskIndex = parseInt(tokens[1]) - 1;
                    Task t = tasks.get(taskIndex);
                    t.mark(true, taskIndex);
                } catch(NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("Usage: mark <taskNumber>");
                }
                break;
            case "unmark":
                try {
                    int taskIndex = parseInt(tokens[1]) - 1;
                    Task t = tasks.get(taskIndex);
                    t.mark(false, taskIndex);
                } catch(NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("Usage: mark <taskNumber>");
                }
                break;
            case "todo":
                // Can we get the class itself to perform regex?
                addTask(Todo.extractDetails(s));
                break;
            case "deadline":
                addTask(Deadline.extractDetails(args));
                break;
            case "event":
                addTask(Event.extractDetails(args));
                break;
            default:
                System.out.println("Whatcha sayin? scream 'help!' for list of my services");
        }
    }
    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println(PrependMessages.randomMsg() + task.brief() + AppendMessages.randomMsg());
    }

    public static String listTasks() {
        int index = 1;
        StringBuilder result = new StringBuilder();
        for (Task t : tasks) {
            String task = index + t.toString();
            System.out.println(task);
            index++;
        }
        System.out.println(result);
        return result.toString();
    }

}
