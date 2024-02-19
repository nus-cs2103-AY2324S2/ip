package luke;

import java.util.Scanner;

public class Ui {

    protected TaskList taskList;

    protected Storage storage;

    protected Parser parser;
    Ui() {
        this.taskList = new TaskList();
        this.parser = new Parser(taskList);
        this.storage = new Storage("data/tasks.txt");
    }

    Ui(TaskList taskList) {
        this.taskList = taskList;
        this.parser = new Parser(taskList);
        this.storage = new Storage("data/tasks.txt");
    }

    protected String handleInput(String input) {
            try {
                parser.isInputValid(input);
                String command = parser.getCommand(input);
                return switch (command) {
                    case "list" -> this.list(input);
                    case "mark" -> this.mark(input);
                    case "unmark" -> this.unmark(input);
                    case "delete" -> this.delete(input);
                    case "todo" -> this.todo(input);
                    case "deadline" -> this.deadline(input);
                    case "event" -> this.event(input);
                    case "find" -> this.find(input);
                    case "edit" -> this.edit(input);
                    case "bye" -> this.end();
                    default -> "Invalid command";
                };
            } catch (LukeException e) {
                return e.getMessage();
            }
    }

    public void showLoadingError() {
        System.out.println("File not found.");
    }

    public String welcome() {
        String name = "Luke";
        return "Hello! I'm " + name + "\nWhat can I do for you?";
    }

    private String list(String input) {
        try {
            parser.isListCommandValid(input);
        } catch (LukeException e) {
            return e.getMessage();
        }
        return "Here are the tasks in your list: \n" + parser.commandList(taskList);
    }

    private String mark(String input) {
        try {
            Task taskMarked = parser.commandMark(input);
            return "Nice! I've marked this task as done: \n" + taskMarked.toString();
        } catch (LukeException e) {
            return e.getMessage();
        }
    }

    private String unmark(String input) {
        try {
            Task taskUnmarked = parser.commandUnmark(input);
            return "OK, I've marked this task as not done yet: \n" + taskUnmarked.toString();
        } catch (LukeException e) {
            return e.getMessage();
        }
    }

    private String delete(String input) {
        try {
            Task taskDeleted = parser.commandDelete(input);
            return "Noted. I've removed this task:\n" + taskDeleted.toString()
                    + "\nNow you have " + taskList.getNoTasks() + " tasks in the list.";
        } catch (LukeException e) {
            return e.getMessage();
        }
    }

    private String todo(String input) {
        try {
            Task todoAdded = parser.commandTodo(input);
            return taskSuccessfullyAdded(todoAdded, taskList.getNoTasks());
        } catch (LukeException e) {
            return e.getMessage();
        }
    }

    private String deadline(String input) {
        try {
            Task deadlineAdded = parser.commandDeadline(input);
            return taskSuccessfullyAdded(deadlineAdded, taskList.getNoTasks());
        } catch (LukeException e) {
            return e.getMessage();
        }
    }

    private String event(String input) {
        try {
            Task eventAdded = parser.commandEvent(input);
            return taskSuccessfullyAdded(eventAdded, taskList.getNoTasks());
        } catch (LukeException e) {
            return e.getMessage();
        }
    }

    private String taskSuccessfullyAdded(Task task, int noTasks) {
        return "I've added this task:\n" + task.toString() +
            "\nNow you have " + noTasks + " tasks in the list.";
    }

    private String find(String input) {
        try {
            String keyword = parser.commandFind(input);
            TaskList tasksFound = taskList.search(keyword);
            if (tasksFound.getNoTasks() == 0) {
                return "No tasks with the keyword found.";
            } else {
                return "Here are the matching tasks in your list:\n" + tasksFound.list();
            }
        } catch (LukeException e) {
            return e.getMessage();
        }
    }

    private String edit(String input) {
        try {
            Task taskEdited = parser.commandEdit(input);
            return "Task change successful! This is the new task:\n" + taskEdited.toString();
        } catch (LukeException e) {
            return e.getMessage();
        }
    }

    public String end() {
        return storage.saveFile(taskList);
    }
}
