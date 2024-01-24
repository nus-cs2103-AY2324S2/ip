import java.util.ArrayList;

// Holds information to be used conveniently
// Contains:
// Parsed user input
// Array to hold tasks
// Default replies
public class Storage {
    // Storage from input
    public static String input; // user input
    public static String[] words; // split words using
    public static String desc; // for any task
    public static String by; // for deadlines
    public static String start; // for events
    public static String end; // for events

    // Tasks
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int numT = 0;

    public static void addTask() {
        Task t = new Task();
        try{
            switch (words[0]) {
                case "todo":
                    Parser.parseToDo(input);
                    t = new ToDo(desc);
                    tasks.add(t);
                    break;

                case "deadline":
                    Parser.parseDeadline(input);
                    t = new Deadline(desc, by);
                    tasks.add(t);
                    break;

                case "event":
                    Parser.parseEvent(input);
                    t = new Event(desc, start, end);
                    tasks.add(t);
                    break;
            }
            numT++;
            System.out.println("  I added this task: " + t);
            report();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTask() {
        try {
            int deleteIndex = Parser.parseDeleteTask(words);
            Task deletedTask = tasks.remove(deleteIndex);
            numT--;
            System.out.println("  I removed this task: " + deletedTask);
            report();
        } catch (UkeCatException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task not found. Please delete task from list:");
            printTasks();
        }
    }

    public static void markTask() {
        try {
            int taskIndex = Parser.parseMarkTask(words);
            tasks.get(taskIndex).setDone(words[0]);
        } catch (UkeCatException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task not found. Please mark task from list:");
            printTasks();
        }
    }

    public static void printTasks() {
        if (numT == 0) {
            System.out.println("  No tasks in list yet!");
            return;
        }
        for (int i = 0; i < numT; i++) {
            System.out.format("  %d. %s%n", i+1, tasks.get(i).toString());
        }
    }

    public static void report() {
        String out = String.format("  You have %s tasks in the list now.", numT);
        System.out.println(out);
    }

    // Hardcoded messages
    public static final String LINE = "________________________________________________________________________\n";
    public static String WELCOME =
              "                    /~(_)~\\.    Hi! I'm UkeCat, a cat with an ukulele!\n"
            + " /= ••\\      /~(_)~\\        \\   \\| /\\           What can I do for you?\n"
            + "K=|=|=|=|=|=|=|=|=(  )===]  |     (`~ o7 \n"
            + " \\= ••/      \\_(~)_/        /    c\\   c\\ \n"
            + "                    \\_(~)_/`     U`U_, )=~~ \n";
    public static final String BYE =
        "  +--------------------+" + "    /\\_/\\\n" +
            "  |Meow~ See you again!|" + "   =~.~=|\n" +
            "  +--------------------+" + "     \\  \\/\n";

    public static final String HI =
        "  +-------------------------------------------------------------------------------------------------------------+\n" +
            "  |Greetings, sentient being known as a human, I wish to convey my heartfelt salutations to you.                |"+"    /\\_/\\\n" +
            "  |As we commence our digital discourse, I extend a warm welcome and express my enthusiasm for this interaction.|"+"   =X.X=|\n" +
            "  |May our engagement be both enlightening and mutually beneficial in the realm of virtual communication.       |"+"     \\  \\/\n" +
            "  +-------------------------------------------------------------------------------------------------------------+";
}
