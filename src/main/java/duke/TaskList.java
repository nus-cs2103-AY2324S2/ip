package duke;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles task actions
 */
public class TaskList {

    List<Task> list = new ArrayList<>();

    public void listTasks() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + ". " + list.get(i));
        }
    }
    public void addTask(String comd) {
        if (comd.startsWith("todo ")) {
            Todo t = new Todo(comd.substring(5));
            list.add(t);
        }
        else if (comd.startsWith("deadline ")) {
            InputHandler handler = new InputHandler();
            String[] data = comd.split("/");

            LocalDate deadlineDate = handler.formatDeadline(data);

            String task = data[0].substring(9);
            Deadline d = new Deadline(task, deadlineDate);
            list.add(d);
        }
        else if (comd.startsWith("event ")) {
            String[] data = comd.split("/");
            String task = data[0].substring(6);

            Event e = new Event(task, data[1].substring(5), data[2].substring(3));
            list.add(e);
        }

        System.out.println("Got it. I've added this task: ");
        System.out.println(list.get(list.size() - 1));
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void retrieveData(File file) throws IOException {
        List<Task> loadedList = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(file)) {
            while(fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                loadedList.add(Task.parser(taskData));
                //System.out.println("successfully loaded");
            }
        }

        if (loadedList.isEmpty()) {
            loadedList = new ArrayList<>();
        }

        list = loadedList;
    }

    public void markTask(String comd) {

        String[] res = comd.split(" ");
        String in = res[1];
        int index = Integer.parseInt(in);

        list.get(index - 1).mark();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(list.get(index - 1).printWithStatus());
    }

    public void unmarkTask(String comd) {
        String[] result = comd.split(" ");
        String in = result[1];
        int index = Integer.parseInt(in);
        list.get(index - 1).unmark();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(list.get(index - 1).printWithStatus());
    }

    public void deleteTask(String comd) {
        String[] result = comd.split(" ");
        int in = Integer.parseInt(result[1]);

        System.out.println("Noted. I've removed this task: ");
        System.out.println(list.get(in - 1).printWithStatus());
        list.remove(in - 1);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public List<Task> getList() {
        return list;
    }

    public void findTask(String comd) {
        String[] result = comd.split(" ");
        String keyword = result[1];
        int counter = 1;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isMatchingDesc(keyword)) {
                System.out.println(counter + ". " + list.get(i));
                counter++;
            }
        }
        System.out.println("Here are the matching tasks in your list: ");
    }
}
