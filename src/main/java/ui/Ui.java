package ui;

/**
 * The UI bot which can greet and say bye to the user.
 */

public class Ui {
    private String logo;
    private String botName;

    public Ui(String botName) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.logo = logo;
        this.botName = botName;
    }

    public String greeting() {
        // String output = "Hello from\n" + logo;
        String output = "";
        output += "Hello! I'm " + this.botName + ", your personal TodoList assistant!\n" + "What can I do for you?\n";
        return output;
    }

    public String exit() {
        return "Bye. Hope to see you again soon!";
    }
}
