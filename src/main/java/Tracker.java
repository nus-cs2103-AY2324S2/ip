import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class Tracker {

    /**
     * When a user adds a task, a response of PrependMessages + task is rendered
     * To add a prependMessage: Write the enum and then place the message in brackets
     * e.g. ANGRY("You better get your ass up and"),
     */
    enum CustomMessages {

        DEFAULT("Added: ", ""),
        SUSPENSE("Are you sure you want to: ", " Mewo looks at you, frightened"),
        HAPPY("I'm sure you can ", " I'll always be By Your Side"),
        DEMEANING("Don't tell me you can't ", " don't disappoint me");
        private final String prepend;
        private final String append;
        private static final Random RNG = new Random();
        CustomMessages(String label, String append) {
            this.prepend = label;
            this.append = append;
        }

        private static String getPrepend(CustomMessages msg) {
            return msg.prepend;
        }
        private static String getAppend(CustomMessages msg) { return msg.append;}


        public static String randomMsg(Task task) {
            CustomMessages[] pm = values();
            int msgIndex = RNG.nextInt(pm.length);
            return getPrepend(pm[msgIndex]) + task.brief() + getAppend(pm[msgIndex]);
        }
    }

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println(CustomMessages.randomMsg(task));
        System.out.println(task);
    }

    public static void removeTask(int i) {
        tasks.remove(i);
        System.out.println("Exploooosion! now task " + i + " has been Kazuma-ed out of existence");
        listTasks();
        System.out.println("You now have " + tasks.size() + " tasks in your list");
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

    public static void ProcessQuery(String s) {
        String[] tokens = s.split(" ");
        ArrayList<String> args = new ArrayList<String>(Arrays.asList(tokens));

        switch (args.get(0).toLowerCase()) {
            case "list":
                listTasks();
                break;
            case "mark":
                try {
                    int taskIndex = parseInt(tokens[1]) - 1;
                    Task t = tasks.get(taskIndex);
                    t.mark(true, taskIndex);
                } catch(NumberFormatException e) {
                    System.out.println("Usage: mark <taskNumber>");
                } catch(IndexOutOfBoundsException e) {
                    System.out.println("Hey you don't have that task!");
                }
                break;
            case "unmark":
                try {
                    int taskIndex = parseInt(tokens[1]) - 1;
                    Task t = tasks.get(taskIndex);
                    t.mark(false, taskIndex);
                } catch(NumberFormatException e) {
                    System.out.println("Usage: mark <taskNumber>");
                } catch(IndexOutOfBoundsException e) {
                    System.out.println("Calm down! You don't have THAT many tasks!");
                }
                break;
            case "todo":
                // it is possible to relegate exception handling to addTask
                // you must use fp and implement lazy evaluation
                try {
                    addTask(Todo.extractDetails(s));
                } catch (BadAppleException be) {
                    System.out.println(be);
                }
                break;
            case "deadline":
                try {
                    addTask(Deadline.extractDetails(args));
                } catch (BadAppleException be) {
                    System.out.println(be);
                }
                break;
            case "event":
                try {
                    addTask(Event.extractDetails(args));
                } catch (BadAppleException be) {
                    System.out.println(be);
                }
                break;
            case "delete":
                if (tokens.length < 1) {
                    System.out.println("Kel nuked, but he missed what task you wanted to remove!");
                }
                int taskIndex = parseInt(tokens[1]) - 1;
                if (taskIndex >= 0) {
                    removeTask(taskIndex);
                } else {
                    System.out.println("welcome to BLACK SPACE, you keyed in a negative number!");
                }
                break;
            default:
                System.out.println("Whatcha sayin? scream 'help!' for list of my services");
        }
    }

}
