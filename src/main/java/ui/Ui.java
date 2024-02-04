package ui;

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

    public void greeting() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm " + this.botName + "\n"
                + "What can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
