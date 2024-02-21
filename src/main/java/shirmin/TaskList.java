package shirmin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class TaskList {

    private final Parser parser;
    private final ArrayList<Task> taskList = new ArrayList<>();
    private final Storage storage;
    private int currIndex = 0;

    public TaskList(Parser parser, Storage storage) {
        assert parser != null : "Parser should not be null";
        assert storage != null : "Storage should not be null";
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
        assert directory.exists() || directory.mkdirs() : "Failed to create directory";

        File file = new File(directory, "Shirmin.txt");
        try {
            assert file.createNewFile() : "Failed to create Shirmin.txt file";

            file.createNewFile();
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String taskLine = fileScanner.nextLine();
                Task task = parser.parseTask(taskLine); // Implement this method based on your task format
                assert task != null : "Failed to parse task";

                this.taskList.add(task);
                this.currIndex++;
            }
        } catch(IOException ignored){ // exception should not occur at all

        }
    }

    public String runCommand(String input) {
        // long function but I've already encapsulated the add task logic and the length is due to the number of
        // cases so i feel that the length is reasonable here
        String[] commandParts = input.split(" ", 2);
        CommandType commandType = CommandType.getCommandType(commandParts[0]);
        String response;
        assert commandType != null : "Invalid commandType";

        try {
            switch (commandType) {
            case LIST:
                StringBuilder listBuilder = new StringBuilder("Here is your task list:\n");
                for (int i = 0; i < taskList.size(); i++) {
                    listBuilder.append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
                }
                response = listBuilder.toString();
                break;
            case MARK:
                int markIndex = Integer.parseInt(commandParts[1]) - 1;
                Task taskToMark = taskList.get(markIndex);
                taskToMark.markDone();
                storage.saveAllTasksToFile(this.taskList);
                response = "Marked as done: " + taskToMark;
                break;
            case UNMARK:
                int unmarkIndex = Integer.parseInt(commandParts[1]) - 1;
                Task taskToUnmark = taskList.get(unmarkIndex);
                taskToUnmark.markUndone();
                storage.saveAllTasksToFile(this.taskList);
                response = "Marked as not done: " + taskToUnmark;
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
                int deleteIndex = Integer.parseInt(commandParts[1]) - 1;
                Task taskToDelete = taskList.remove(deleteIndex);
                storage.saveAllTasksToFile(this.taskList);
                response = "Deleted task: " + taskToDelete.toString();
                break;
            case FIND:
                try {
                    if (commandParts.length < 2) {
                        return "Error: Missing search keyword for FIND command.";
                    }
                    String keyword = commandParts[1];
                    response = findTasksByKeyword(keyword);


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
        assert details != null : "Task details should not be null";
        assert type != null : "TaskType should not be null";

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

        if (isDuplicateTask(newTask)) {
            return "Error: Duplicate task already exists.";
        }

        taskList.add(newTask);
        storage.saveTaskToFile(newTask);
        return "Added: " + newTask;

    }

    private boolean isDuplicateTask(Task newTask) {
        for (Task task : taskList) {
            if (task.toString().equals(newTask.toString())) {
                return true;
            }
        }
        return false;
    }

    enum TaskType {
        TODO, DEADLINE, EVENT
    }
    private String findTasksByKeyword(String keyword) {
        assert keyword != null : "Keyword should not be null";
        StringBuilder foundTasksBuilder = new StringBuilder();
        boolean isFound = false;
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasksBuilder.append(i + 1).append(". ").append(task).append("\n");
                isFound = true;
            }
        }
        if (isFound) {
            return "Here are the matching tasks in your list:\n" + foundTasksBuilder;
        } else {
            return "No tasks found matching: " + keyword;
        }
    }
}
