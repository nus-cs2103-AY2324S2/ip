package duke;

public class UI {

    private String greet = "Hello! I'm ELIAS\n"
            + "     What can I do for you?";

    private String logo = "______ _ _\n"
            + "|  ____| (_)\n"
            + "| |__  | |_  __ _ ___\n"
            + "|  __| | | |/ _` / __|\n"
            + "| |____| | | (_| \\__ \\\n"
            + "|______|_|_|\\__,_|___/\n";

    private String bye = "Bye. Hope to see you again soon!";

    public String getGreet() {
        return greet;
    }

    public String getLogo() {
        return logo;
    }

    public String getBye() {
        return bye;
    }

    public String format(String s) {
        return "    ____________________________________________________________\n     "
                + s
                + "\n    ____________________________________________________________\n";
    }
}
