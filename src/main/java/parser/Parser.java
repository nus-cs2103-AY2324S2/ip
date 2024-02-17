package parser;

import java.util.Scanner;

import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import task.TaskList;

/**
 * Read in user input and execute relative commands.
 */

public class Parser {
    protected TaskList todoList;
    protected Storage storage;
    protected Storage archived;

    public Parser(TaskList todoList, Storage storage, Storage archived) {
        this.todoList = todoList;
        this.storage = storage;
        this.archived = archived;
    }

    /**
     * Recognize keywords from the user input such as "bye" and "list".
     * If not, pass it to further cases of commands.
     */

    public void parse() {
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        while ((!isExit) && scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                isExit = true;
            } else if (userInput.equals("list")) {
                this.todoList.printList();
            } else if (userInput.equals("archived")) {
                TaskList archivedTasks = new TaskList(this.archived.getHistory());
                archivedTasks.printList();
            } else if (isMarkTask(userInput)) {
                this.todoList.changeMarkingOfTask(userInput, storage);
            } else if (isDeleteTask(userInput)) {
                this.todoList.deleteTask(userInput, storage, archived);
            } else if (isFindTask(userInput)) {
                this.todoList.findTask(userInput, storage);
            } else {
                this.echo(userInput);
            }
        }
        scanner.close();
    }

    /**
     * Handle the three cases when the user input is "todo", "deadline" or "event".
     * 
     * @param userInput the input from the user.
     */

    public void echo(String userInput) {
        String[] words = userInput.split("\\s+");
        if (words.length > 0) {
            String firstWord = words[0];
            int firstSpaceIndex = userInput.indexOf(' ');
            String description = userInput.substring(firstSpaceIndex + 1);
            switch (firstWord) {
                case "todo": {
                    todoCase(words, description);
                    break;
                }
                case "deadline": {
                    deadlineCase(words, description);
                    break;
                }
                case "event": {
                    eventCase(userInput, words);
                    break;
                }
                default:
                    System.out.println("Sorry, I don't understand your command.");
                    break;
            }
        } else {
            System.out.println("Sorry, I don't understand your command.");
        }
    }

    private void eventCase(String userInput, String[] words) {
        if (words.length == 1) {
            System.out.println("The description of a todo cannot be empty!");
        } else {
            String[] parts = userInput.split("\\\\from|\\\\to");
            String event_description = parts.length > 0 ? parts[0].trim() : "";
            String event_from = parts.length > 1 ? parts[1].trim() : "";
            String event_to = parts.length > 2 ? parts[2].trim() : "";
            Task t = new Event(event_description, this.storage.readAsDate(event_from),
                    this.storage.readAsDate(event_to));
            this.todoList.add(t);
            this.todoList.listOverviewAfterAdding(t, this.storage);
        }
    }

    private void deadlineCase(String[] words, String description) {
        if (words.length == 1) {
            System.out.println("The description of a deadline cannot be empty!");
        } else {
            String[] parts = description.split("\\\\by");
            String ddl_description = parts.length > 0 ? parts[0].trim() : "";
            String ddl_time = parts.length > 1 ? parts[1].trim() : "";
            Task t = new Deadline(ddl_description, this.storage.readAsDate(ddl_time));
            this.todoList.add(t);
            this.todoList.listOverviewAfterAdding(t, this.storage);
        }
    }

    private void todoCase(String[] words, String description) {
        if (words.length == 1) {
            System.out.println("The description of a todo cannot be empty!");
        } else {
            Task t = new Todo(description);
            this.todoList.add(t);
            this.todoList.listOverviewAfterAdding(t, this.storage);
        }
    }

    /**
     * Check whether the user is intend to mark one of the tasks.
     * 
     * @param userInput
     * @return True means the user want to mark, or false, which is otherwise.
     */

    public boolean isMarkTask(String userInput) {
        String[] words = userInput.split("\\s+");
        if (words.length == 2) {
            if (words[0].equals("mark") || words[0].equals("unmark")) {
                try {
                    Integer.parseInt(words[1]);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Check whether the user is intend to delete one of the tasks.
     * 
     * @param userInput
     * @return True means the user want to delete, or false, which is otherwise.
     */

    public boolean isDeleteTask(String userInput) {
        String[] words = userInput.split("\\s+");
        if (words.length == 2) {
            if (words[0].equals("delete")) {
                try {
                    Integer.parseInt(words[1]);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean isFindTask(String userInput) {
        String[] words = userInput.split("\\s+");
        if (words.length == 2) {
            if (words[0].equals("find")) {
                return true;
            }
        }
        return false;
    }
}
