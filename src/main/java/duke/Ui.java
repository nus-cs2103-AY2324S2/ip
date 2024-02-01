package duke;

public class Ui {

    public Ui() {

    }

    public void divider() {
        String d = "_____________________________________________";
        System.out.println(d);
    }

    public void sayHi() {
        String hi = "Helloo! I'm LilyBot ;)\nWhat's up'?\n";
        System.out.print(hi);
        divider();
    }

    public void sayBye() {
        System.out.println("Bye Bye. See u later!");
        divider();
    }

    public void printMarkDone(String task) {
        System.out.println("Good job! I've marked this task as done:");
        System.out.println("  " + task);
        divider();
    }

    public void printMarkNotDone(String task) {
        System.out.println("Okie, Marked this task as not done yet:");
        System.out.println("  " + task);
        divider();
    }

    public void printAdded(String task) {
        System.out.println("  Got it. I've added this task:");
        System.out.println("  " + task);
    }

    public void printRemoved(String task) {
        System.out.println("Noted. Task Removed:"+"\n"+ "  " + task);
    }

    public void printTaskAmount(TaskList taskList) {
        System.out.println("  Now u have " + taskList.size() +
                " tasks in the list." + "\n");
        divider();
    }

    public void invalidDescription() {
        System.out.println("Oops! Sorry, I don't know what that means. Description is empty");
        divider();
    }

    public void invalidInput() {
        System.out.println("Oops! I don't understand the instruction.");
        divider();
    }

    public void invalidInputNumber() {
        System.out.println("Plz tell me which task." + "\n");
        divider();
    }

    public void invalidDdlFormat() {
        System.out.println("Plz enter a date for the deadline using '/by'");
        System.out.println("Also notice the format should be like this: yyyy-mm-dd'");
        divider();
    }

    public void invalidEventFormat() {
        System.out.println("Plz enter a date for the event using '/from' and '/to'" );
        divider();
    }

    public static void botUnknownFormat(int i) {
        System.out.println("Oops, I don't understand the file format");
        i = i + 1;
        System.out.println("Line " + i + " in the given file will be ignored");
    }



}
