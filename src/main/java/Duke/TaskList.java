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
        assert ui != null : "Ui should not be null";
        assert parser != null : "Parser should not be null";
        assert storage != null : "Storage should not be null";

        this.ui = ui;
        this.parser = parser;
        this.storage = storage;
        loadTasksFromFile();
    }


    // ---------------------------CLI LOGIC HERE (antiquated, kept for testing)-----------------------------------------
    enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID, FIND, BYE;
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
        assert directory.exists() || directory.mkdirs() : "Failed to create directory";

//        if (!directory.exists()) {
//            directory.mkdirs(); // create directory if does not exist
//        }
        File file = new File(directory, "duke.txt");
        try {
            assert file.createNewFile() : "Failed to create duke.txt file";

            file.createNewFile();
            Scanner fileScanner = new Scanner(file);
            int lines = 0;
            while (fileScanner.hasNext()) {
                String taskLine = fileScanner.nextLine();
                Task task = parser.parseTask(taskLine); // Implement this method based on your task format
                assert task != null : "Failed to parse task";
                
                if (task != null) {
                    this.taskList.add(task);
                }
                lines++;
                this.currIndex++;
            }
            // ui.loadFiles(lines); // uncomment for CLI
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
            case FIND:
                try {
                    String keyword = command[1].toLowerCase(); // Convert keyword to lowercase for case-insensitive search
                    ArrayList<Task> matchingTasks = new ArrayList<>();

                    for (Task task : taskList) {
                        if (task.getDescription().toLowerCase().contains(keyword)) {
                            matchingTasks.add(task);
                        }
                    }
                    ui.displayMatchingTasks(matchingTasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.findFormatError();
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

// -------------------------------------- GUI LOGIC THIS POINT AND BELOW.---------------------------------------


    public String runCommand(String input) {
        String[] commandParts = input.split(" ", 2);
        CommandType commandType = CommandType.getCommandType(commandParts[0]);
        String response = "";

        try {
            switch (commandType) {
            case LIST:
                StringBuilder listBuilder = new StringBuilder();
                for (int i = 0; i < taskList.size(); i++) {
                    listBuilder.append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
                }
                response = listBuilder.toString();
                break;
            case MARK:
                // Parse index from commandParts[1], mark the task, and generate response
                int markIndex = Integer.parseInt(commandParts[1]) - 1;
                Task taskToMark = taskList.get(markIndex);
                taskToMark.markDone();
                storage.saveAllTasksToFile(this.taskList); // Save changes
                response = "Marked as done: " + taskToMark.toString();
                break;
            case UNMARK:
                // Similar to MARK, but for unmarking
                int unmarkIndex = Integer.parseInt(commandParts[1]) - 1;
                Task taskToUnmark = taskList.get(unmarkIndex);
                taskToUnmark.markUndone();
                storage.saveAllTasksToFile(this.taskList); // Save changes
                response = "Marked as not done: " + taskToUnmark.toString();
                break;
            case TODO:
                if (commandParts.length < 2) {
                    return "Error: Missing TODO description.";
                }
                return handleAddTask(commandParts[1], TaskType.TODO);
            case DEADLINE:
                if (commandParts.length < 2 || !commandParts[1].contains("/by")) {
                    return "Error: Missing DEADLINE description or /by part.";
                }
                return handleAddTask(commandParts[1], TaskType.DEADLINE);
            case EVENT:
                if (commandParts.length < 2 || !commandParts[1].contains("/to")) {
                    return "Error: Missing EVENT description or /at part.";
                }
                return handleAddTask(commandParts[1], TaskType.EVENT);
            case DELETE:
                // Parse index for deletion and update response
                int deleteIndex = Integer.parseInt(commandParts[1]) - 1;
                Task taskToDelete = taskList.remove(deleteIndex);
                storage.saveAllTasksToFile(this.taskList); // Save changes
                response = "Deleted task: " + taskToDelete.toString();
                break;
            // Implement other cases as needed
            case FIND:
//                String[] commandParts = input.split(" ", 2);
//                CommandType commandType = CommandType.getCommandType(commandParts[0]);
//                String response;
                try {
                    switch (commandType) {
                    // Other cases remain unchanged...

                    case FIND:
                        if (commandParts.length < 2) {
                            return "Error: Missing search keyword for FIND command.";
                        }
                        String keyword = commandParts[1];
                        response = findTasksByKeyword(keyword);
                        break;

                    // The rest of your switch cases...

                    default:
                        response = "Unknown command.";
                        break;
                    }
                } catch (Exception e) {
                    response = "Error processing command: " + e.getMessage();
                }
                return response;

            case BYE:
                return "Bye. Hope to see you again soon!";
            default:
                response = "OH NO I'm not sure what that command is. You may use the commands, " +
                        "todo, deadline, list, event, delete, mark, unmark and bye";
                break;
            }
        } catch (Exception e) {
            response = "Error processing command: " + e.getMessage();
        }
        return response;
    }

    private String handleAddTask(String details, TaskType type) {
        Task newTask = null;
        switch (type) {
        case TODO:
            newTask = new Todo(details);
            break;
        case DEADLINE:
            String[] parts = details.split(" /by ", 2);
            if (parts.length < 2) return "Error: Incorrect DEADLINE format.";
            newTask = new Deadline(parts[0], parts[1]);
            break;
        case EVENT:
            String[] eventParts = details.split(" /from ", 2);
            if (eventParts.length < 2 || !eventParts[1].contains(" /to ")) {
                return "Error: Incorrect EVENT format. Use '/from [start date/time] /to [end date/time]'.";
            }
            String[] fromToParts = eventParts[1].split(" /to ", 2);
            if (fromToParts.length < 2) return "Error: Missing EVENT end date/time.";
            newTask = new Event(eventParts[0], fromToParts[0], fromToParts[1]);
            break;
        }
        if (newTask == null) {
            return "Error creating task.";
        }
        taskList.add(newTask);
        storage.saveTaskToFile(newTask); // Assumes this method handles individual task saving
        return "Added: " + newTask;
    }

    enum TaskType {
        TODO, DEADLINE, EVENT
    }
    private String findTasksByKeyword(String keyword) {
        StringBuilder foundTasksBuilder = new StringBuilder();
        boolean found = false;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                // Task found, append it to the result string
                foundTasksBuilder.append(i + 1).append(". ").append(task).append("\n");
                found = true;
            }
        }
        if (found) {
            return "Here are the matching tasks in your list:\n" + foundTasksBuilder.toString();
        } else {
            return "No tasks found matching: " + keyword;
        }
    }
}
