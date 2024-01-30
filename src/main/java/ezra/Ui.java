package ezra;

public class Ui {
    public static void horizontalLine() {
        System.out.println("\t____________________________________________________________");
    }
    public void greet() {
        Ui.horizontalLine();
        System.out.println("\tHello! I'm Ezra.\n\tWhat can I do for you?");
        Ui.horizontalLine();
    }
}
