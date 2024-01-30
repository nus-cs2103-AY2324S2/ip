import java.util.ArrayList;

public class UI {

    public final String LINE = "-----------------------------";
    public UI() {

    }

    private void line() {
        System.out.println(LINE);
    }

    public void startMsg() {
        line();
        System.out.println("Greetings friend! I am Datuk");
        System.out.println("How can I serve you today? ^_^' \n");
        line();
    }

    public void byeMsg() {
        line();
        System.out.println("Farewell!");
        line();
    }

    public void showError(DukeException de) {
        System.out.println(de.toString());
    }

    public void printList(ArrayList<Task> taskList) {
        line();
        System.out.println("These are all your tasks:");
        if (taskList.isEmpty()) System.out.println("\tOh noes! The list is empty! :(");

        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + taskList.get(i));
        }
        line();
    }
}
