package Victor.Ui;
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

    public String getIntro() {
        System.out.println("Hello from\n" + logo);
        return this.intro;
    }

    public String getEnding() {
        return this.ending;
    }

}
