import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * This class implements the chatbot Scribbles.
 *
 * @author danielle
 */
public class Scribbles {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private String filePath;
    private Scanner scanner;

    public Scribbles(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        storage = new Storage(this.filePath);
        taskList = new TaskList();
        scanner = new Scanner(System.in);
    }

    /**
     * Checks if file path exists.
     * Creates file path if it does not exist.
     */
    public void loadFileScribbles() {
        File f = new File(this.filePath);

        try {
            // check if filePath directory exists
            File directory = f.getParentFile();
            if (!directory.exists()) {
                boolean hasCreatedDirectory = directory.mkdir();
                if (!hasCreatedDirectory) {
                    System.out.println("Failed to create directory for file.\n");
                }
            }

            // Check if filePath file exists
            if (!f.exists()) {
                boolean hasCreatedFile = f.createNewFile();
                if (!hasCreatedFile) {
                    System.out.println("Failed to create file.\n");
                }
            }

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    public void run() {
        // load file for Scribbles
        loadFileScribbles();

        // load data from file into task list
        try {
            storage.loadFileData(taskList);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.\n");
        }
        //stopped here

        // Greets the user
        ui.greet();

        boolean isByeCommand = false;
        while (!isByeCommand) {
            String input = scanner.nextLine();
            isByeCommand = parseInput(input);
        }
        scanner.close();

        // Exits
        ui.exit();
    }

    public boolean parseInput(String input) {
        Parser parsedInput = new Parser(input);
        String command = parsedInput.getCommand();

        switch (command){
            case "bye":
                return true;
            case "list":
                ui.listTask(taskList);
                break;
            case "mark":
                try {
                    int index = parsedInput.getIndex();
                    taskList.get(index - 1).markComplete();
                    ui.markCompletedMessage(index, taskList);
                    storage.saveFileData(taskList);
                } catch (IndexOutOfBoundsException e) {
                    ui.invalidIndexMessage(taskList);
                } catch (FileNotFoundException e) {
                    ui.fileNotFoundMessage();
                }
                break;
            case "unmark":
                try {
                    int index = parsedInput.getIndex();
                    taskList.get(index - 1).markIncomplete();
                    ui.markIncompleteMessage(index, taskList);
                    storage.saveFileData(taskList);
                } catch (IndexOutOfBoundsException e) {
                    ui.invalidIndexMessage(taskList);
                } catch (FileNotFoundException e) {
                    ui.fileNotFoundMessage();
                }
                break;
            case "todo":
                try {
                    String description = parsedInput.getTodoDescription();
                    taskList.addTask(new Todo(description, false));
                    ui.confirmTaskAddition(taskList);
                    storage.saveFileData(taskList);
                } catch (IndexOutOfBoundsException e) {
                    ui.taskMissingInformationMessage();
                } catch (FileNotFoundException e) {
                    ui.fileNotFoundMessage();
                }
                break;
            case "deadline":
                if (parsedInput.isMissingDeadlineInformation()) {
                    ui.taskMissingInformationMessage();
                } else {
                    try {
                        String description = parsedInput.getDeadlineDescription();
                        LocalDateTime by = parsedInput.getDeadlineBy();
                        taskList.addTask(new Deadline(description, false, by));
                        ui.confirmTaskAddition(taskList);
                        storage.saveFileData(taskList);
                    } catch (IndexOutOfBoundsException e) {
                        ui.taskMissingInformationMessage();
                    } catch (DateTimeParseException e) {
                        ui.wrongDateTimeFormatMessage();
                    } catch (FileNotFoundException e) {
                        ui.fileNotFoundMessage();
                    }
                }
                break;
            case "event":
                if (parsedInput.isInvalidEvent()) {
                    ui.taskMissingInformationMessage();
                } else {
                    try {
                        String description = parsedInput.getEventDescription();
                        LocalDateTime start = parsedInput.getStartDateTime();
                        LocalDateTime end = parsedInput.getEndDateTime();
                        taskList.addTask(new Event(description, false, start, end));
                        ui.confirmTaskAddition(taskList);
                        storage.saveFileData(taskList);
                    } catch (IndexOutOfBoundsException e) {
                        ui.taskMissingInformationMessage();
                    } catch (DateTimeParseException e) {
                        ui.wrongDateTimeFormatMessage();
                    } catch (FileNotFoundException e) {
                        ui.fileNotFoundMessage();
                    }
                }
                break;
            case "delete":
                try {
                    int index = parsedInput.getIndex();
                    String taskRemoved = taskList.get(index - 1).toString();
                    taskList.deleteTask(index - 1);
                    ui.taskDeletionMessage(taskRemoved, taskList);
                    storage.saveFileData(taskList);
                } catch (IndexOutOfBoundsException e) {
                    ui.invalidIndexMessage(taskList);
                } catch (FileNotFoundException e) {
                    ui.fileNotFoundMessage();
                }
                break;
            default:
                ui.invalidInputMessage();
        }
        return false;
    }


    public static void main(String[] args) {
        new Scribbles("src/main/java/taskData.txt").run();
    }

}
