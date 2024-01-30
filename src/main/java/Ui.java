// Based on the parsed input, generate a response
public class Ui {
    public void respond(String[] words) {
        switch(words[0]) {
        case "hi":
            System.out.println(Ui.HI);
            break;
        case "list":
            Storage.printTasks();
            break;
        case "mark":
            Storage.markTask(MarkType.MARK);
            break;
        case "unmark":
            Storage.markTask(MarkType.UNMARK);
            break;
        case "delete":
            Storage.deleteTask();
            break;
        case "todo":
        case "deadline":
        case "event":
            Storage.addTask();
            break;
        default:
            System.out.println("  I don't understand! Try adding a task!");
        }
    }

    public void welcome() {
        System.out.println(Ui.LINE + Ui.WELCOME + Ui.LINE);
    }

    public void bye() {
        System.out.println(Ui.BYE);
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
