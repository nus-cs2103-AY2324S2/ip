package Duke;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private Ui ui;
    private Parser parser;
    private ArrayList<Task> taskList = new ArrayList<>();
    private Storage storage;
    private int currIndex = 0;
    public TaskList(Ui ui, Parser parser, Storage storage) {
        this.ui = ui;
        this.parser = parser;
        this.storage = storage;
        loadTasksFromFile();
    }
    enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID;
        static CommandType getCommandType(String command) {
            try {
                return valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return INVALID;
            }
        }
    }
    public void loadTasksFromFile() {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdirs(); // create directory if does not exist
        }
        File file = new File(directory, "duke.txt");
        try {
            file.createNewFile();
            Scanner fileScanner = new Scanner(file);
            int lines = 0;
            while (fileScanner.hasNext()) {
                String taskLine = fileScanner.nextLine();
                Task task = parser.parseTask(taskLine); // Implement this method based on your task format
                if (task != null) {
                    this.taskList.add(task);
                }
                lines++;
                this.currIndex++;
            }
            ui.loadFiles(lines);
        } catch(IOException e){
            ui.createFileError(e);
        }

    }
    public void listFunction() {
        // Duke.Duke.TaskManager taskManager = new Duke.Duke.TaskManager(taskList);
        Scanner scanner = new Scanner(System.in);
        String currLine = scanner.nextLine();
        while (!currLine.equals("bye")) {
            String[] command = currLine.split(" ", 2);
            CommandType commandType = CommandType.getCommandType(command[0]);
            switch (commandType) {
            case LIST:
                ui.displayList(taskList);
                break;
            case MARK:
                try {
                    int taskIndex = Integer.parseInt(command[1]) - 1;
                    if (taskIndex < currIndex && taskIndex >= 0) {
                        Task task = taskList.get(taskIndex);
                        task.markDone();
                        ui.markTask(task);
                        this.storage.saveAllTasksToFile(this.taskList);
                        break;
                    } else { // out of range
                        ui.taskOutOfBounds(currIndex);
                    }
                } catch (NumberFormatException e) {
                    ui.invalidTaskNumber(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.taskOutOfBounds(currIndex);
                }
                break;
            case UNMARK:
                try {
                    int taskIndex = Integer.parseInt(command[1]) - 1;
                    if (taskIndex < currIndex && taskIndex >= 0) {
                        Task task = taskList.get(taskIndex);
                        task.markUndone();
                        ui.unmarkTask(task);
                        this.storage.saveAllTasksToFile(this.taskList);
                        break;
                    } else { // out of range
                        ui.invalidTaskNumber(command[1]);
                    }
                } catch (NumberFormatException e) {
                    ui.invalidTaskNumber(command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.taskOutOfBounds(currIndex);
                }

                break;
            case TODO:
                try {
                    Task newTodo = new Todo(command[1]);
                    taskList.add(newTodo);
                    currIndex++;
                    ui.addMessage(newTodo, currIndex);
                    this.storage.saveTaskToFile(newTodo);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.todoFormatError();
                }

                break;
            case DEADLINE:
                try {
                    String[] details = command[1].split(" /by ");
                    String description = details[0];
                    String by = details[1];

                    Deadline newDeadline = new Deadline(description, by);
                    if (newDeadline.hasValidDate()) {
                        taskList.add(newDeadline);
                        currIndex++;
                        ui.addMessage(newDeadline, currIndex);
                        this.storage.saveTaskToFile(newDeadline);
                    } else {
                        ui.deadlineDateError();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.deadlineFormatError();
                }
                break;
            case EVENT:
                try {
                    String[] details = command[1].split(" /from ");
                    String description = details[0];
                    String[] fromTo = details[1].split(" /to ");
                    String from = fromTo[0];
                    String to = fromTo[1];

                    Event newEvent = new Event(description, from, to);
                    if (newEvent.hasValidDates()) {
                        taskList.add(newEvent);
                        currIndex++;
                        ui.addMessage(newEvent, currIndex);
                        this.storage.saveTaskToFile(newEvent);
                    } else {
                        ui.eventDateError();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.eventFormatError();
                }
                break;
            case DELETE:
                try {
                    int taskIndex = Integer.parseInt(command[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < taskList.size()) {
                        Task removedTask = taskList.remove(taskIndex);
                        ui.deleteTask(removedTask.toString(), taskList);
                        this.storage.saveAllTasksToFile(this.taskList);
                        currIndex--;
                    } else {
                        ui.invalidTaskNumber(String.valueOf(taskIndex + 1));
                    }
                } catch (NumberFormatException e) {
                    ui.invalidTaskNumber(command[1]);
                }
                break;
            default:
                ui.unknownCommand();
                break;
            }
            currLine = scanner.nextLine();
        }
        ui.exit();
    }
}
