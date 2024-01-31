package Lery;

public class Ui {
    private final String LINE = "____________________________________________________________\n";
    private final String logo =
            " _                    \n" +
                    "| |    ___ _ __ _   _ \n" +
                    "| |   / _ \\ '__| | | |\n" +
                    "| |__|  __/ |  | |_| |\n" +
                    "|_____\\___|_|   \\__, |\n" +
                    "                |___/ \n";

    public void printMessageWithLine(String m) {
        System.out.println(this.LINE + m + "\n" + this.LINE);
    }
    public void greet() {
        System.out.println(this.LINE + logo + "Hello! I'm Lery.Lery"
                + "\n" + "What can I do for you?\n" + this.LINE);

    }
    public void exit() {
        System.out.println(this.LINE + "Bye. Hope to see you again soon!\n" +  this.LINE);
    }

}
