package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import duke.task.Task;

public class UI {
    public BufferedReader br;
    public final String LINE = "-----------------------------";
    public UI() {
        br = new BufferedReader(new InputStreamReader(System.in));
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

    public void byeMsg() throws IOException {
        line();
        System.out.println("Farewell!");
        line();

        br.close();
    }

    public String read() throws IOException {
        return br.readLine();
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

    public void showAddMsg(Task t, int size) {
        line();
        System.out.println("Understood. Added the following:");
        System.out.println("\t " + t);
        System.out.println("You have " + size + " remaining tasks.");
        line();
    }

    public void showDeleteMsg(Task t, int size) {
        line();
        System.out.println("Removed the following: ");
        System.out.println("\t" + t);
        System.out.println((size-1) + " tasks remaining.");
        line();
    }

    public void showMark(String desc) {
        line();
        System.out.println("I have set this task < " + desc + " > as completed." );
        line();
    }

    public void showUnmark(String desc) {
        line();
        System.out.println("I have set this task < " + desc + " > as incomplete." );
        line();
    }
}
