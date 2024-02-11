package ukecat;


/**
 * The Ui class handles user interface interactions for the UkeCat application.
 * It provides methods for responding to user commands, displaying welcome and farewell messages,
 * and interacting with the Storage class to manage tasks.
 */
public class Ui {
    /**
     * Responds to user commands based on the provided array of words.
     * Asks Storage class to perform relevant actions in response to user commands.
     *
     * @param words The array of words representing the user input.
     */
    public String getReply(String[] words) {
        switch (words[0]) {
        case "bye":
            Parser.closeScanner();
            return BYE;
        case "hi":
            return HI;
        case "list":
            return Storage.displayTasks();
        case "mark":
            return Storage.markTask(MarkType.MARK);
        case "unmark":
            return Storage.markTask(MarkType.UNMARK);
        case "delete":
            return Storage.deleteTask();
        case "todo":
        case "deadline":
        case "event":
            return Storage.addTask();
        case "find":
            return Storage.findTask();
        default:
            return "  I don't understand! Try adding a task!";
        }
    }

    /* Hardcoded messages */
    public static String line0 = " Hi! I'm UkeCat, a cat with an ukulele!\n What can I do for you?";
    public static String line1 = "                     /~(_)~\\.   ";
    public static String line2 = "  /=.. \\      /~(_)~\\        \\   /\\";
    public static String line3 = " K=|=|=|=|=|=|=|=|=(  )===]  |  (`~ o7";
    public static String line4 = "  \\=.. /      \\_(~)_/        / c\\   c\\";
    public static String line5 = "                     \\_(~)_/`   U`U_, )=~~";

    public static final String WELCOME_GUI = String.format("%s%n%s%n%s%n%s%n%s%n%s%n",
            line0, line1, line2, line3, line4, line5);


    public static String lineB0 = "  +--------------------+   /\\_/\\";
    public static String lineB1 = "  |Meow~ See you again!|  =~.~=|";
    public static String lineB2 = "  +--------------------+   c\\ c\\~~";

    public static final String BYE = String.format("%s%n%s%n%s%n", lineB0, lineB1, lineB2);

    public static final String HI =
            "  +-------------------------------------------------------------------------------------------------------------+\n" +
                    "  |Greetings, sentient being known as a human, I wish to convey my heartfelt salutations to you.                |"+"    /\\_/\\\n" +
                    "  |As we commence our digital discourse, I extend a warm welcome and express my enthusiasm for this interaction.|"+"   =X.X=|\n" +
                    "  |May our engagement be both enlightening and mutually beneficial in the realm of virtual communication.       |"+"     \\  \\/\n" +
                    "  +-------------------------------------------------------------------------------------------------------------+";

}
