package lumiere.lumiere;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TaskList {
    List<Task> list = new ArrayList<>();

    public void loadTask(Task task) {
        this.list.add(task);
    }

<<<<<<< Updated upstream
    public void printList() {
=======
    /**
     * An instance method that prints the TaskList, task by task.
     * 
     * @return Nothing, this is a void method.
     */
    public String printList() {
        String result = "";
>>>>>>> Stashed changes
        for (int i = 0; i < list.size(); i++) {
            result += Integer.toString(i + 1) + "." + list.get(i).stringify() + "\n";
        }
        return result;
    }

<<<<<<< Updated upstream
    public void addTask(String command, Storage storage) throws IOException {
        if (command.contains("unmark")) {
            System.out.println("OK, I've marked this task as not done yet:");
            int space = command.indexOf(" ");
            int num = Integer.parseInt(command.substring(space + 1)); // unmark X
            Task curr = list.get(num - 1);
            curr.unmark();
            list.set(num - 1, curr);
            storage.saveTasksToFile(list);
            System.out.println(list.get(num - 1).stringify());
        } else if (command.contains("mark")) {
            System.out.println("Nice! I've marked this task as done:");
            int space = command.indexOf(" ");
            int num = Integer.parseInt(command.substring(space + 1)); // mark X
            Task curr = list.get(num - 1);
            curr.mark();
            list.set(num - 1, curr);
            storage.saveTasksToFile(list);
            System.out.println(list.get(num - 1).stringify());
        } else if (command.contains("delete")) {
            System.out.println("Noted. I've removed this task:");
            int space = command.indexOf(" ");
            int num = Integer.parseInt(command.substring(space + 1)); // delete X
            System.out.println(list.get(num - 1).stringify());
            list.remove(num - 1);
            storage.saveTasksToFile(list);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } else if (command.contains("find")) {
            int space = command.indexOf(" ");
            String search_phrase = command.substring(space + 1);
            System.out.println("Here are the matching tasks in your list:\n");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getItem().contains(search_phrase))
                    System.out.println(i + 1 + "." + list.get(i).stringify());
            }
=======
    /**
     * An instance method that executes a command input by user.
     * 
     * @param command The string that user input into the program.
     * @param storage A storage object that represents local storage of the tasks.
     * @return Nothing, this is a void method.
     * @throws IOException If saveTasksToFile method throws an exception.
     */
    public String addTask(String command, Storage storage) throws IOException {
        if (command.contains("unmark")) {
            return unmarkTask(command, storage);
        } else if (command.contains("mark")) {
            return markTask(command, storage);
        } else if (command.contains("delete")) {
            return deleteTask(command, storage);
        } else if (command.contains("find")) {
            return findByKeyword(command, storage);
>>>>>>> Stashed changes
        } else if (command.contains("todo") || command.contains("deadline") || command.contains("event")) {
            // create Task object with command
            if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                return "OOPS!!! The description of a todo cannot be empty.";
            } else {
                int space = command.indexOf(" ");
                String type = command.substring(0, space);

                if (type.equals("todo")) {
<<<<<<< Updated upstream
                    String rest = command.substring(space + 1);
                    Todo task = new Todo(rest, false);
                    System.out.println(task.stringify());
                    list.add(task);
                    System.out.println("Got it. I've added this task:");
                    storage.saveTasksToFile(list);
                    System.out.println("Now you have " + Integer.toString(list.size()) + " tasks in the list.");
                } else if (type.equals("deadline")) {
                    String rest = command.substring(space + 1);
                    String[] description = rest.split(" /by ");
                    try {
                        LocalDate time = LocalDate.parse(description[1]);
                        Deadline task = new Deadline(description[0], false, time, description[1]);
                        System.out.println(task.stringify());
                        list.add(task);
                        System.out.println("Got it. I've added this task:");
                        storage.saveTasksToFile(list);
                        System.out.println("Now you have " + Integer.toString(list.size()) + " tasks in the list.");
                    } catch (DateTimeParseException err) {
                        System.out.println(
                                "Sorry! Your deadline is in the wrong format. Correct format is YYYY-MM-DD");
                    }
                } else if (type.equals("event")) {
                    String rest = command.substring(space + 1);
                    String[] description = rest.split(" /from ");
                    String[] time = description[1].split(" /to ");
                    Event task = new Event(description[0], false, time[0], time[1]);
                    System.out.println(task.stringify());
                    list.add(task);
                    System.out.println("Got it. I've added this task:");
                    storage.saveTasksToFile(list);
                    System.out.println("Now you have " + Integer.toString(list.size()) + " tasks in the list.");
=======
                    return addAndSaveTodo(space, command, storage);
                } else if (type.equals("deadline")) {
                    return addAndSaveDeadline(space, command, storage);
                } else {
                    return addAndSaveEvent(space, command, storage);
>>>>>>> Stashed changes
                }
            }
        } else {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
<<<<<<< Updated upstream
=======

    private String unmarkTask(String command, Storage storage) throws IOException {
        int space = command.indexOf(" ");
        int num = Integer.parseInt(command.substring(space + 1)); // unmark X
        Task curr = list.get(num - 1);
        curr.unmark();
        list.set(num - 1, curr);
        storage.saveTasksToFile(list);
        return "OK, I've marked this task as not done yet:\n" + list.get(num - 1).stringify();
    }

    private String markTask(String command, Storage storage) throws IOException {
        int space = command.indexOf(" ");
        int num = Integer.parseInt(command.substring(space + 1)); // mark X
        Task curr = list.get(num - 1);
        curr.mark();
        list.set(num - 1, curr);
        storage.saveTasksToFile(list);
        return "Nice! I've marked this task as done:\n" + list.get(num - 1).stringify();
    }

    private String deleteTask(String command, Storage storage) throws IOException {
        int space = command.indexOf(" ");
        int num = Integer.parseInt(command.substring(space + 1)); // delete X
        String temp = list.get(num - 1).stringify();
        list.remove(num - 1);
        storage.saveTasksToFile(list);
        return "Noted. I've removed this task:\n" + temp + "\nNow you have " + list.size() + " tasks in the list.";
    }

    private String findByKeyword(String command, Storage storage) throws IOException {
        int space = command.indexOf(" ");
        String search_phrase = command.substring(space + 1);
        String result = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getItem().contains(search_phrase))
                result += i + 1 + "." + list.get(i).stringify() + "\n";
        }
        return result;
    }

    private String addAndSaveTodo(int space, String command, Storage storage) throws IOException {
        String rest = command.substring(space + 1);
        Todo task = new Todo(rest, false);
        list.add(task);
        storage.saveTasksToFile(list);
        return "Got it. I've added this task:\n" + task.stringify() + "\n" +
                "Now you have " + Integer.toString(list.size()) + " tasks in the list.";
    }

    private String addAndSaveDeadline(int space, String command, Storage storage) throws IOException {
        String rest = command.substring(space + 1);
        String[] description = rest.split(" /by ");
        try {
            LocalDate time = LocalDate.parse(description[1]);
            Deadline task = new Deadline(description[0], false, time, description[1]);
            list.add(task);
            storage.saveTasksToFile(list);
            return "Got it. I've added this task:\n" + task.stringify() + "\n" +
                    "Now you have " + Integer.toString(list.size()) + " tasks in the list.";
        } catch (DateTimeParseException err) {
            return "Sorry! Your deadline is in the wrong format. Correct format is YYYY-MM-DD";
        }
    }

    private String addAndSaveEvent(int space, String command, Storage storage) throws IOException {
        String rest = command.substring(space + 1);
        String[] description = rest.split(" /from ");
        String[] time = description[1].split(" /to ");
        Event task = new Event(description[0], false, time[0], time[1]);
        list.add(task);
        storage.saveTasksToFile(list);
        return "Got it. I've added this task:\n" + task.stringify() + "\n" +
                "Now you have " + Integer.toString(list.size()) + " tasks in the list.";
    }

>>>>>>> Stashed changes
}
