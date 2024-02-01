//Returns a list of Strings neatly parsed as arguments.

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

    private TaskList taskList;
    private History history;

    public Parser(History history) {
        this.history = history;
        ArrayList<Task> tasks = history.getHistory();
        this.taskList = new TaskList(tasks);
    }

    public boolean parseCommand(String input) {
        String trimmedLowercase = input.trim().toLowerCase();
        //bye, list
        if (trimmedLowercase.equals("bye")) {
            System.out.println("Don't be ridiculous!\n" +
                    "It's... it's not like I want to see you again or anything!\n");
            //TODO: save history
            return true;
        } else if (trimmedLowercase.equals("list")) {
            taskList.listTasks();
            return false;
        } else if (trimmedLowercase.split(" ")[0].trim().equals("delete")) {
            int index = Integer.parseInt(input.split(" ")[1].strip()) - 1;
            taskList.deleteTask(index);
            return false;
        } else if (trimmedLowercase.split(" ")[0].trim().equals("mark")) {
            int index = Integer.parseInt(input.split(" ")[1].strip()) - 1;
            taskList.markTask(index);
            return false;
        }

        //tasks
        Task task = null;
        for (TaskType t : TaskType.values()) {
            try {
                String prefix = trimmedLowercase.substring(0, t.toString().length());
                if (prefix.equals(t.toString().toLowerCase())) {
                    task = makeTask(trimmedLowercase);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("oob");
                return false;
            }
        }

        if (task != null) {
            taskList.addTask(task);
            return false;
        }

        System.out.println("/// What on earth are you saying! ///");
        System.out.println("[Command not found]\n");
        return false;
    }

    //assume that the input is a task
    private static Task makeTask(String input) {
        Task task;
        String trimmedLowercase = input.trim().toLowerCase();
        String taskType = trimmedLowercase.split(" ")[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String todoString = TaskType.TODO.toString();
        String deadlineString = TaskType.DEADLINE.toString();
        String eventString = TaskType.EVENT.toString();

        if (taskType.equals(todoString)) {
            task = new Todo(trimmedLowercase.substring(todoString.length()).trim());
            if (input.split(" ").length < 2) {
                System.out.println("You have eyes for a reason, don't you?");
                System.out.println("[Missing todo description]\n");
                return null;
            }
        } else if (taskType.equals(deadlineString)) {
            try {
                String taskName = input.split("/")[0].substring(deadlineString.length()).trim();
                LocalDate by = LocalDate.parse(input.split("/")[1].replace("by", "").trim(), formatter);
                task = new Deadline(taskName, by);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Hey! You forgot something! Be glad I'm here to remind you.");
                System.out.println("[Missing deadline parameter(s)]\n");
                return null;
            }
        } else if (taskType.equals(eventString)) {
            try {
                String taskName = input.split("/")[0].substring(eventString.length()).trim();
                LocalDate start = LocalDate.parse(input.split("/")[1].replace("from", "").trim(), formatter);
                LocalDate end = LocalDate.parse(input.split("/")[2].replace("to", "").trim(), formatter);
                task = new Event(taskName, start, end);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("/// I don't know when you are free... ///");
                System.out.println("[Missing event parameter(s)]\n");
                return null;
            }
        } else {
            System.out.println("/// What on earth are you saying! ///");
            System.out.println("[Command not found]\n");
            return null;
        }
        return task;
    }
}
