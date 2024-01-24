import java.util.ArrayList;
import java.util.Random;

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
        private PrependMessages(String label) {
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
        private AppendMessages(String label) {
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
    public static ArrayList<String> tasks = new ArrayList<>();

    public static void ProcessQuery(String s) {
        if (!s.equalsIgnoreCase("list")) addTask(s);
        else listTasks();
    }
    public static void addTask(String task) {
        tasks.add(task);
        System.out.println(PrependMessages.randomMsg() + task + AppendMessages.randomMsg());
    }

    public static String listTasks() {
        int index = 1;
        StringBuilder result = new StringBuilder();
        for (String task: tasks) {
            String item = index + ". " + task + "\n";
            result.append(item);
            index++;
        }
        System.out.println(result);
        return result.toString();
    }
}
