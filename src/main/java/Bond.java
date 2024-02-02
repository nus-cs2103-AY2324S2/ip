import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class Bond {

    private Ui ui;

    private TaskList taskList;

    private Storage storage;

    public Bond() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.storage = new Storage(System.getProperty("user.home") + "/data/Bond.txt");
    }

    private void addTask(String taskName, boolean marked, boolean tellUser,
            String filePath, boolean isWrittenToFile) {
        Task newTask = Task.makeTask(taskName);
        taskList.addTask(newTask);

        if (isWrittenToFile) {
            try {
                storage.storeTask(newTask, taskList);
            } catch (IOException e) {
                ui.showError(e);
                System.exit(0);
            }
        }

        if (marked) {
            markTask(taskList.numberOfTasks() - 1, tellUser, filePath);
        }

        if (tellUser) {
            ui.taskAdded(newTask, taskList);
        }
    }

    private void parseAndAddTask(String task, String filePath) throws IOException {
        // System.out.println(task);
        String remainder = task.substring(4);
        String taskName = "";
        boolean isMarked = false;

        if (remainder.startsWith("[X]")) {
            isMarked = true;
        }

        remainder = remainder.substring(4);

        // Determine task name
        if (remainder.indexOf("(") == -1) {
            taskName = remainder;
            remainder = "";
        } else {
            int openIndex = remainder.indexOf("(");
            taskName = remainder.substring(0, openIndex - 1);
            remainder = remainder.substring(openIndex + 1);
        }

        // Add task to taskList
        if (task.startsWith("[T]")) {
            addTask("todo " + taskName, isMarked, false, filePath, false);
        } else if (task.startsWith("[D]")) {
            int spaceIndex = remainder.indexOf(" ");
            int closeIndex = remainder.indexOf(")");
            String deadline = remainder.substring(spaceIndex + 1, closeIndex);
            String[] components = deadline.split(" ");
            deadline = Parser.changeDateFormat(components[0], components[1], components[2]) + " " + components[3];

            addTask("deadline " + taskName + " /by " + deadline, isMarked, false, filePath, false);
        } else if (task.startsWith("[E]")) {
            String start = "";
            String end = "";
            int closeIndex = remainder.indexOf(")");
            remainder = remainder.substring(0, closeIndex);
            String[] components = remainder.split(" ");

            for (int i = 0; i < components.length; i++) {
                if (components[i].equals("from:")) {

                    for (int j = i + 1; j < components.length; j++) {
                        if (components[j].equals("to:")) {
                            break;
                        }
                        start += components[j] + " ";
                    }

                } else if (components[i].equals("to:")) {
                    end = components[i + 1];
                }
            }

            String[] startComponents = start.split(" ");
            start = Parser.changeDateFormat(startComponents[0], startComponents[1], startComponents[2]) + " "
                    + startComponents[3];
            String[] endComponents = end.split(" ");
            end = Parser.changeDateFormat(endComponents[0], endComponents[1], endComponents[2]) + " "
                    + endComponents[3];

            addTask("event " + taskName + " /from " + start + " /to " + end, isMarked, false, filePath,
                    false);
        }
    }

    private void loadTasksFromFile(String filePath) {
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source

            while (s.hasNextLine()) {
                // System.out.println("I have reached Loop in " + "loadTasksFromFIle");
                String currTask = s.nextLine();
                // System.out.println("Current task read is: " + currTask);
                parseAndAddTask(currTask, filePath);
            }

            s.close();
        } catch (FileNotFoundException e) {
            ui.showError(e);
            System.exit(0);
        } catch (IOException e) {
            ui.showError(e);
            System.exit(0);
        }
    }

    private void updateFile(String filePath) {
        try {
            storage.overwritePreviousSave(taskList);
        } catch (IOException e) {
            ui.showError(e);
            System.exit(0);
        }
    }

    private void deleteTask(int taskIndex, Boolean tellUser, String filePath) {
        Task deletedTask = taskList.getTask(taskIndex);
        taskList.deleteTask(taskIndex);

        updateFile(filePath);

        if (tellUser) {
            ui.taskDeleted(deletedTask, taskList);
        }
    }

    private void markTask(int taskIndex, Boolean tellUser, String filePath) {
        taskList.getTask(taskIndex).markAsComplete();
        updateFile(filePath);

        if (tellUser) {
            ui.taskMarked(taskIndex, taskList);
        }
    }

    private void unmarkTask(int taskIndex, Boolean tellUser, String filePath) {
        taskList.getTask(taskIndex).markAsIncomplete();
        updateFile(filePath);

        if (tellUser) {
            ui.taskUnmarked(taskIndex, taskList);
        }
    }

    public void run() {
        ui.showWelcome();

        try {
            // Check for directory / file existence
            String home = System.getProperty("user.home");
            java.nio.file.Path directoryPath = java.nio.file.Paths.get(home, "data");
            boolean directoryExists = java.nio.file.Files.exists(directoryPath);

            if (!directoryExists) {
                java.nio.file.Files.createDirectory(directoryPath);
            }

            java.nio.file.Path filePath = java.nio.file.Paths.get(home, "data", "Bond.txt");
            boolean fileExists = java.nio.file.Files.exists(filePath);

            if (!fileExists) {
                java.nio.file.Files.createFile(filePath);
            }
        } catch (java.io.IOException e) {
            ui.showError(e);
            System.exit(0);
        }

        String filePath = System.getProperty("user.home") + "/data/Bond.txt";

        // Read from file: Bond.txt

        loadTasksFromFile(filePath);

        Scanner scNext = new Scanner(System.in);

        String userInput = "";

        while (scNext.hasNextLine()) {
            // System.out.println("I have reached Loop " + "in MAIN function");

            userInput = scNext.nextLine();

            String[] components = userInput.split(" ");

            try {

                // Invalid Command syntax
                if (Parser.isValidCommand(components[0])) {
                    BondException.raiseException("NA",
                            "INVALID_COMMAND_TYPE");
                }

                if (components[0].equalsIgnoreCase("todo")) {

                    // No valid task name specified for a todo task
                    if (components.length == 1) {
                        BondException.raiseException("todo", "EMPTY_DESCRIPTION");
                    }

                    addTask(userInput, false, true, filePath, true);

                } else if (components[0].equalsIgnoreCase("deadline")) {

                    if (components.length == 1) {
                        BondException.raiseException("deadline", "EMPTY_DESCRIPTION");
                    }

                    addTask(userInput, false, true, filePath, true);

                } else if (components[0].equalsIgnoreCase("event")) {

                    if (components.length == 1) {
                        BondException.raiseException("event", "EMPTY_DESCRIPTION");
                    }

                    addTask(userInput, false, true, filePath, true);

                } else if (components[0].equalsIgnoreCase("list")) {

                    if (components.length != 1) {
                        BondException.raiseException("list", "EXTRA_DETAILS");
                    }

                    ui.showList(taskList);
                } else if (components[0].equalsIgnoreCase("mark")) {

                    if (components.length == 1) {
                        BondException.raiseException("mark", "MISSING_INDEX");
                    } else if (!Parser.isNumber(components[1])) {
                        BondException.raiseException("mark", "INVALID_INDEX");
                    } else if (Integer.parseInt(components[1]) - 1 >= taskList.numberOfTasks()) {
                        BondException.raiseException("mark", "INVALID_INDEX");
                    }

                    int index = Integer.parseInt(components[1]) - 1;

                    markTask(index, true, filePath);

                } else if (components[0].equalsIgnoreCase("unmark")) {

                    if (components.length == 1) {
                        BondException.raiseException("unmark", "MISSING_INDEX");
                    } else if (!Parser.isNumber(components[1])) {
                        BondException.raiseException("unmark", "INVALID_INDEX");
                    } else if (Integer.parseInt(components[1]) - 1 >= taskList.numberOfTasks()) {
                        BondException.raiseException("unmark", "INVALID_INDEX");
                    }

                    int index = Integer.parseInt(components[1]) - 1;

                    unmarkTask(index, true, filePath);

                } else if (components[0].equalsIgnoreCase("bye")) {

                    if (components.length != 1) {
                        BondException.raiseException("bye", "EXTRA_DETAILS");
                    }

                    ui.showGoodbye();
                    break;
                } else if (components[0].equalsIgnoreCase("delete")) {

                    if (taskList.noTasks()) {
                        BondException.raiseException("delete", "EMPTY_LIST");
                    } else if (components.length == 1) {
                        BondException.raiseException("delete", "MISSING_INDEX");
                    } else if (!Parser.isNumber(components[1])) {
                        BondException.raiseException("delete", "INVALID_INDEX");
                    } else if (Integer.parseInt(components[1]) - 1 >= taskList.numberOfTasks()) {
                        BondException.raiseException("delete", "INVALID_INDEX");
                    }

                    int index = Integer.parseInt(components[1]) - 1;

                    deleteTask(index, true, filePath);
                }

            } catch (BondException e) {
                ui.showError(e);
                continue;
            }
        }

        scNext.close();
        System.exit(0);
    }

    public static void main(String[] args) {
        Bond bond = new Bond();
        bond.run();
    }
}
