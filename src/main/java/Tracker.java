import java.util.ArrayList;
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
        switch (tokens[0].toLowerCase()) {
            case "list":
                listTasks();
                break;
            case "mark":
                if (tasks.isEmpty()) {
                    System.out.println("There's nothing to mark, Yay!");
                    break;
                }
                try {
                    if (parseInt(tokens[1]) > tasks.size() || parseInt(tokens[1]) <= 0) {
                        System.out.println("You don't have that task silly!");
                        break;
                    }
                    Task t = tasks.get(parseInt(tokens[1]) - 1);
                    t.mark(true);
                    System.out.println("I've marked task " + tokens[1]);
                } catch(NumberFormatException | ArrayIndexOutOfBoundsException  e) {
                    System.out.println("Usage: mark <taskNumber>");
                }
                break;
            case "unmark":
                if (tasks.isEmpty()) {
                    System.out.println("Hmm Where is there a task to unmark... THERE'S NONE!");
                    break;
                }
                try {
                    if (parseInt(tokens[1]) > tasks.size() || parseInt(tokens[1]) <= 0) {
                        System.out.println("You don't have that many tasks silly!");
                        break;
                    }
                    Task t = tasks.get(parseInt(tokens[1]) - 1);
                    t.mark(false);
                    System.out.println("I've unmarked task " + tokens[1]);
                } catch(NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Usage: mark <taskNumber>");
                }
                break;
            default:
                addTask(s);
                //System.out.println("Whatcha sayin? scream 'help!' for list of my services");
        }
    }
    public static void addTask(String task) {
        Task t = new Task(task);
        tasks.add(t);
        System.out.println(PrependMessages.randomMsg() + task + AppendMessages.randomMsg());
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
