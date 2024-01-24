import javax.annotation.processing.SupportedSourceVersion;
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

    public static void addTask(Task t) {
        tasks.add(t);
        numT++;
        System.out.println("  I added this task: " + t);
    }

    public static void markTask(String mark, int taskIndex) {
        tasks.get(taskIndex).setDone(mark);
    }

    public static void printTasks() {
        if (Storage.numT == 0) {
            System.out.println("  No tasks in list yet!");
            return;
        }
        for (int i = 0; i < Storage.numT; i++) {
            System.out.format("  %d. %s%n", i+1, tasks.get(i).toString());
        }
    }

    public static void report() {
        String out = String.format("  You have %s undone tasks in the list.", Storage.numT);
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
