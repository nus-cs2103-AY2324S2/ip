package Victor.Ui;

import java.util.Scanner;

public class Ui {
    protected String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    protected String intro;
    protected String ending;
    protected String barrier = "____________________________________________________________";
    public Ui() {
        this.intro = "____________________________________________________________\n" +
                " Hello! I'm VICTOR\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        this.ending = "____________________________________________________________\n"+
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
    }

    public void showIntro() {
        System.out.println("Hello from\n" + logo);
        System.out.println(this.intro);
    }

    public void showEnding() {
        System.out.println(this.ending);
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void displayBarrier() {
        System.out.println(this.barrier);
    }

}
