import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    List<Task> list = new ArrayList<>();

    public void add(Task item) {
        list.add(item);
        int numberOfTasks = list.size();
        line();
        System.out.println("Got it dawg. I've added this task: \n");
        System.out.println(item.toString() + "\n");
        System.out.println("Now you got "+ numberOfTasks + " tasks fam \n");
        line();
    }

    public void mark(String input) {
        try {
            int index = Integer.parseInt(input);
            list.get(index - 1).markAsDone();
            line();
            System.out.println("Marked item " + index + " as done dawg.");
            line();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            line();
            System.out.println("Invalid input for 'mark' command dawg.");
            line();
        }
    }

    public void unmark(String input) {
        try {
            int index = Integer.parseInt(input);
            list.get(index - 1).markAsUndone();
            line();
            System.out.println("Unmarked item " + index + " as done.");
            line();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            line();
            System.out.println("Invalid input for 'unmark' command.");
            line();
        }
    }

    public void delete(String input) {
        try {
            int index = Integer.parseInt(input);
            String task = list.get(index - 1).toString();
            list.remove(index - 1);
            line();
            System.out.println("Deleted item: \n" + task + "\n");
            System.out.println("Now you have " + list.size() + " tasks left in the list.");
            line();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            line();
            System.out.println("Invalid input for 'delete' command.");
            line();
        }
    }

    public void print() {
        line();
        System.out.println("Here are the tasks in your list: \n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString() + "\n");
        }
        line();
    }

    private static void line() {
        System.out.println("_______________________\n");
    }
}