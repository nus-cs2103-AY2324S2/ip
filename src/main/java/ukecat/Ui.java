package ukecat;


/**
 * The Ui class handles user interface interactions for the UkeCat application.
 * It provides methods for responding to user commands, displaying welcome and farewell messages,
 * and interacting with the Storage class to manage tasks.
 */
public class Ui {
    /* Hardcoded messages */
    public static final String LINEW0 = " Hi! I'm UkeCat, a cat with an ukulele!\n What can I do for you?";
    public static final String LINEW1 = "                     /~(_)~\\.   ";
    public static final String LINEW2 = "  /=.. \\      /~(_)~\\        \\   /\\";
    public static final String LINEW3 = " K=|=|=|=|=|=|=|=|=(  )===]  |  (`~ o7";
    public static final String LINEW4 = "  \\=.. /      \\_(~)_/        / c\\   c\\";
    public static final String LINEW5 = "                     \\_(~)_/`   U`U_, )=~~";

    public static final String WELCOME_GUI = String.format("%s%n%s%n%s%n%s%n%s%n%s%n",
            LINEW0, LINEW1, LINEW2, LINEW3, LINEW4, LINEW5);


    public static final String LINEB0 = "  +--------------------+   /\\_/\\    Z";
    public static final String LINEB1 = "  |Meow~ See you again!|  =~.~=|   z";
    public static final String LINEB2 = "  +--------------------+   c\\ c\\~z";

    public static final String BYE = String.format("%s%n%s%n%s%n", LINEB0, LINEB1, LINEB2);



    public static final String H1 = "  Meow! Did you know that the ukulele \n  has these four strings:";
    public static final String H2 = "  --A---";
    public static final String H3 = "  --E---   /\\_/\\";
    public static final String H4 = "  --C---  =^.^=| ~";
    public static final String H5 = "  --G---   c\\ c\\/";
    public static final String HI = String.format("%s%n%s%n%s%n%s%n%s%n", H1, H2, H3, H4, H5);

    public static final String X1 = "  +-------------------+   /\\_/\\";
    public static final String X2 = "  |I don't understand!|  =x.x=|";
    public static final String X3 = "  +-------------------+   c\\ c\\--";
    public static final String IDK = String.format("%s%n%s%n%s%n", X1, X2, X3);

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
            FileManager.updateTasks();
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
        case "recur":
            return Storage.addTask();
        case "find":
            return Storage.findTask();
        default:
            return IDK;
        }
    }
}
