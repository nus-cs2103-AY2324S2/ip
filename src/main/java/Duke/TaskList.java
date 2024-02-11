package Duke;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    private Parser parser;
    private ArrayList<Task> taskList = new ArrayList<>();
    private Storage storage;
    private int currIndex = 0;
    public TaskList(Parser parser, Storage storage) {
        this.parser = parser;
        this.storage = storage;
        loadTasksFromFile();
    }

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
        } catch(IOException e){
//            ui.createFileError(e);
        }
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
