package Martin;

import java.util.ArrayList;

public class Ui {
    public Ui() {

    }

    public void sayGreeting() {
        System.out.println("Hello from Martin");
        System.out.println("What can I do for you?");
    }

    public void sayBye() {
        System.out.println("Bye from Martin");
    }

    public void printFoundTasks(ArrayList<Task> foundTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.println((i + 1) + "." + foundTasks.get(i));
        }
    }
}
