public class Ui {
    private String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String name = "Anxi";
    private String line = "------------------------------------------------------------";

    public Ui() {
    }

    public void printWelcomeMessage() {
        System.out.println(line);
        System.out.println(logo);
        System.out.println("Hello! I'm " + name + "\r\nWhat can I do for you? \r\n" + line);
    }

    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!\r\n" + line);
    }

    public void showLoadingError() {
        System.out.println("File corrupted, unable to load saved tasks");
        System.out.println("Resetting list.............................");
    }

    public void printLine() {
        System.out.println(line);
    }

    public void printTaskList() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
    }

    public void printMarkTask(String taskString) {
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + taskString);
    }

    public void printUnmarkTask(String taskString) {
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + taskString);
    }

    public void printAddToDo(String taskString, int numOfTasks) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:\r\n " + taskString);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    public void printAddEvent(String taskString, int numOfTasks) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:\r\n " + taskString);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    public void printAddDeadline(String taskString, int numOfTasks) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:\r\n " + taskString);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    public void printDeleteTask(String taskString, int numOfTasks) {
        System.out.println(line);
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + taskString);
        System.out.println("Now you have " + numOfTasks + " tasks in the list.");
    }

    public void printUnknowCommandError(String command) {
        System.out.println(line);
        System.out.println("Are you as clueless about \"" + command + "\" as I am?");
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }
}