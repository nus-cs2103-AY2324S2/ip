package BotChat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) throws BotChatException {
        if (tasks.size() < 100) {
            tasks.add(task);
            System.out.println("____________________________________________________________\n" +
                    " Okay! Added to your list: \n"
                    + "   " + task
                    + "\n Now you have " + tasks.size() + " tasks in your list.\n" +
                    "____________________________________________________________\n");
        } else {
            throw new BotChatException(" Ohno :( Your task list is full. Complete some tasks first.");
        }
    }

    public void deleteTask(String input) throws BotChatException {
        try {
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task removedTask = tasks.get(taskIndex);
                tasks.remove(taskIndex);
                System.out.println("____________________________________________________________\n" +
                        " Okay. This task has been removed: \n" +
                        "   " + removedTask + "\n" +
                        " Now you have " + tasks.size() + " tasks in your list.\n" +
                        "____________________________________________________________\n");
            } else {
                throw new BotChatException(" Invalid task index inputted. Please try again.");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new BotChatException(" Please indicate the task number you want to delete.");
        }
    }

    public void listTasks() {
        System.out.println("____________________________________________________________\n" +
                " Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________\n");
    }

    public void markTask(String input) throws BotChatException {
        try {
            int taskIndex = Integer.parseInt(input.substring(5)) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).mark();
                System.out.println("____________________________________________________________\n" +
                        " Nice! This task has been marked as done:\n" +
                        "   " + tasks.get(taskIndex) + "\n" +
                        "____________________________________________________________\n");
            } else {
                throw new BotChatException(" Invalid task index inputted. Please try again.");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new BotChatException(" Please indicate the task number you want to mark complete.");
        }
    }

    public void unmarkTask(String input) throws BotChatException {
        try {
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).unmark();
                System.out.println("____________________________________________________________\n" +
                        " Okay. This task has been unmarked. \n" +
                        "   " + tasks.get(taskIndex) + "\n" +
                        "____________________________________________________________\n");
            } else {
                throw new BotChatException(" Invalid task index inputted. Please try again.");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new BotChatException(" Please indicate the task number you want to unmark.");
        }
    }

    public void addEventTask(String input) throws BotChatException {
        String[] parts = input.split("/", 3);
        if (parts.length == 3) {
            String description = parts[0].substring(5);
            String from = parts[1].substring(5).trim();
            String to = parts[2].substring(3);

            if (!description.isEmpty()) {
                Event eventTask = createEventTask(description, from, to);
                addTask(eventTask);
            } else {
                throw new BotChatException(" Please provide a valid description of the task.");
            }
        } else {
            throw new BotChatException(" Invalid format of Event task. Please try again with the correct format.\n" +
                    " event (event name) /from (start) /to (end)");
        }
    }

    private Event createEventTask(String description, String from, String to) throws BotChatException {
        try {
            return new Event(description, from, to);
        } catch (Exception e) {
            throw new BotChatException("Invalid date format. Please use yyyy-MM-dd or yyyy-MM-dd HHmm format for the event.");
        }
    }

    public void addDeadlineTask(String input) throws BotChatException {
        String[] parts = input.split("/", 2);
        if (parts.length == 2) {
            String description = parts[0].substring(8);
            String by = parts[1].substring(3);

            if (!description.isEmpty()) {
                Deadline deadlineTask = createDeadlineTask(description, by);
                addTask(deadlineTask);
            } else {
                throw new BotChatException("Please provide a valid description of the task.");
            }
        } else {
            throw new BotChatException("Invalid format of Deadline task. Please try again with the correct format.\n" +
                    "deadline (event name) /by (deadline)");
        }
    }

    private Deadline createDeadlineTask(String description, String by) throws BotChatException {
        if (isValidDateFormat(by)) {
            return new Deadline(description, by);
        } else {
            throw new BotChatException("Invalid date format. Please use yyyy-mm-dd or yyyy-mm-dd HHmm format for the deadline.");
        }
    }

    private boolean isValidDateFormat(String by) {
        try {
            LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            try {
                LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                return true;
            } catch (DateTimeParseException ex) {
                return false;
            }
        }
    }

    public void addTodoTask(String input) throws BotChatException {
        if (!input.substring(4).isEmpty()) {
            Task task = new Todo(input.substring(4));
            addTask(task);
        } else {
            throw new BotChatException(" Please provide a valid description of the task.");
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
