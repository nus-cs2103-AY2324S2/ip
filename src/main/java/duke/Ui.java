package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "_______________________________________________________________________\n";
    private Scanner sc = new Scanner(System.in);

    public String readInput() {
        return this.sc.nextLine();
    }

    public void close() {
        this.sc.close();
    }

    public void greet() {
        System.out.println(DIVIDER +
                "Hello! I'm KwunTalk!\nWhat can I do for you?\n" +
                DIVIDER);
    }

    public void goodbye() {
        System.out.println(DIVIDER + "Bye. Hope to see you again soon!\n" + DIVIDER);
    }

    public void listTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println(DIVIDER + "Your list is currently empty.\n" + DIVIDER);

        } else {
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= taskList.getLength(); i++) {
                sb.append(String.format("%d. %s\n", i, taskList.get(i)));
            }
            System.out.println(DIVIDER + "Here are the tasks in your list:\n" + sb + DIVIDER);
        }
    }

    public void deleteTask(Task task, int taskTotal) {
        String s = String.format("OK. I've deleted this task:\n%s\nNow you have %s tasks in the list.\n",
                task, taskTotal);
        System.out.println(DIVIDER + s + DIVIDER);
    }

    public void markTask(Task task) {
        String s = String.format("Nice! I've marked this task as done:\n%s\n", task);
        System.out.println(DIVIDER + s + DIVIDER);
    }

    public void unmarkTask(Task task) {
        String s = String.format("OK, I've marked this task as not done yet:\n%s\n", task);
        System.out.println(DIVIDER + s + DIVIDER);
    }

    public void addTask(Task task, int taskTotal) {
        String s = String.format("Got it. I've added this task:\n%s\nNow you have %s tasks in the list.\n",
                task, taskTotal);
        System.out.println(DIVIDER + s + DIVIDER);
    }

    public void error(DukeException e) {
        System.out.println(DIVIDER + e + DIVIDER);
    }

    public void findTask(TaskList filteredList) {
        if (filteredList.isEmpty()) {
            System.out.println(DIVIDER + "There are no matching tasks in your list.\n" + DIVIDER);

        } else {
            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= filteredList.getLength(); i++) {
                sb.append(String.format("%d. %s\n", i, filteredList.get(i)));
            }
            System.out.println(DIVIDER + "Here are the matching tasks in your list:\n" + sb + DIVIDER);
        }
    }
}
