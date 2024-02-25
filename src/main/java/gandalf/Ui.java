package gandalf;

public class Ui {
    public Ui() {

    }
    public void welcome() {
        System.out.println("Through fire and shadow, I'm Gandalf");
        System.out.println("What can I do for you?\n");
    }
    public void listUi(TaskList tasks) {
        System.out.println("Total number of tasks so far: " + (tasks.getList().size()));
    }
    public void showLine() {
        System.out.println("_____________________________________________________________________________");
    }
    public void showError(String exception) {
        System.out.println(exception);
    }
    public void mark() {
        System.out.println("The task is done, humans truly are remarkable creatures");
    }

    public void unmark() {
        System.out.println("The task is undone, but fret not, for its not about how much we do, but how much we did");
    }
    public void bye() {
        System.out.println("So here at last, comes the end of our fellowship. I will not say: Do not weep. "
                + "For not all tears are an evil.");
    }
}
