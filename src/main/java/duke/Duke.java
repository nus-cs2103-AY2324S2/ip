package duke;

import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    private static final String FILE_NAME = "duke.state";

    private TaskList taskList;
    private Storage storage;
    private final Ui ui;

    public Duke(String fileName) {
        storage = new Storage(fileName);
        ui = new Ui();
        try {
            taskList = storage.readTaskList();
        } catch (IOException | ClassNotFoundException e) {
            taskList = new TaskList();
        }
    }

    public void repl() {
        Scanner sc = new Scanner(System.in);

        label:
        while (sc.hasNextLine()) {
            ui.showLine();
            String command = sc.next();
            String data = sc.nextLine().trim();
            switch (command) {
            case "bye":
                break label;
            case "list":
                System.out.print(taskList);
                break;
            case "mark":
            case "unmark":
            case "delete":
                try {
                    int index = Integer.parseInt(data) - 1;
                    Task task = taskList.getTask(index);
                    if (command.equals("delete")) {
                        taskList.deleteTask(index);
                        System.out.println("Alright, I've deleted the task:\n  " + task);
                        break;
                    }
                    if (command.equals("mark")) {
                        task.setComplete();
                    } else {
                        task.setIncomplete();
                    }
                    System.out.println("Alright, I've set the task as " + task.status() + ":\n  " + task);
                } catch (TaskList.TaskNotFound e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("\"" + data + "\" is not a number. Please try again.");
                }
                ;
                break;
            case "todo":
            case "deadline":
            case "event":
                try {
                    Task task = Task.of(command, data);
                    taskList.addTask(task);
                    System.out.println("Added task " + task.describe());
                } catch (Task.InvalidComponents | Task.InvalidType e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println("Could not parse date: " + e.toString());
                }
                break;
            default:
                System.out.println("I have no idea what you want.\n" + "I can respond to \"list\", \"deadline\", \"event\", \"todo\", \"mark\" and \"unmark\"");
            }
            ui.showLine();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke(FILE_NAME);
        boolean successful = false;
        try {
            duke.taskList = duke.storage.readTaskList();
            successful = true;
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find state file \"" + FILE_NAME + "\"");
        } catch (IOException e) {
            System.out.println("Cannot read from state file \"" + FILE_NAME + "\"");
        } catch (ClassNotFoundException e) {
            System.out.println("The data has been corrupted");
        } finally {
            if (!successful) {
                System.out.println("Continuing with no saved state.");
            }
        }

        duke.ui.showWelcome();
        duke.repl();
        duke.ui.showBye();

        try {
            duke.storage.writeTaskList(duke.taskList);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find state file \"" + FILE_NAME + "\"");
        } catch (IOException e) {
            System.out.println("Cannot write to state file \"" + FILE_NAME + "\"");
        }
    }
}
