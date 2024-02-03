import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static List<String> validCommands = new ArrayList<>(List.of("todo", "deadline", "event"));
    private String storePath;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.storePath = filePath;
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.taskList = storage.load();
        } catch (DukeException e) {
            ui.printError(e);
            this.taskList = new TaskList();
        }

    }

    private static void markTask(String[] split, ArrayList<Task> list) throws InvalidCommandException {
        if (split.length == 1) {
            throw new InvalidCommandException("Wrong format! Please include the number that you want me to mark >:(");
        } else {
            int number = Integer.parseInt(split[1]);
            if (number < 1 || number > list.size()) {
                throw new InvalidTaskNumberException("Invalid number! Index does not exist >:((");
            } else {
                Task task = list.get(number - 1);
                task.markDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("      Yippee! I have marked this task as done ;D");
                System.out.printf("        %s\n", task.toString());
                System.out.println("    ____________________________________________________________");
            }
        }
    }

    private static void unmarkTask(String[] split, ArrayList<Task> list) throws InvalidCommandException {
        if (split.length == 1) {
            throw new InvalidCommandException("Wrong format! Please include the number that you want me to unmark >:(");
        } else {
            int number = Integer.parseInt(split[1]);
            if (number < 1 || number > list.size()) {
                throw new InvalidTaskNumberException("Invalid number! Index does not exist >:((");
            } else {
                Task task = list.get(number - 1);
                task.markNotDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("      Awww...I have marked this task as not done yet :(");
                System.out.printf("        %s\n", task.toString());
                System.out.println("    ____________________________________________________________");
            }
        }
    }

    private static void deleteTask(String[] split, ArrayList<Task> list) throws InvalidCommandException {
        if (split.length == 1) {
            throw new InvalidCommandException("Wrong format! Please include the number that you want me to delete >:(");
        } else {
            int number = Integer.parseInt(split[1]);
            if (number < 1 || number > list.size()) {
                throw new InvalidTaskNumberException("Invalid number! Index does not exist >:((");
            } else {
                Task task = list.get(number - 1);
                list.remove(number - 1);
                System.out.println("    ____________________________________________________________");
                System.out.println("      Successfully removed task!");
                System.out.printf("        %s\n", task.toString());
                System.out.printf("      You have %d tasks left in the list :D\n", list.size());
                System.out.println("    ____________________________________________________________");
            }
        }
    }

    private void createTask(String[] split, ArrayList<Task> list) throws InvalidCommandException {
        Task newTask;
        if (!validCommands.contains(split[0].toLowerCase())) {
            throw new InvalidCommandException("I don't quite understand that command :'( Sorry...");
        }
        if (split.length == 1) {
            throw new InvalidCommandException("You need to tell me the task name >:0");
        }
        if (split[0].toLowerCase().equals("todo")) {
            newTask = new ToDo(split[1].trim());
        } else if (split[0].toLowerCase().equals("deadline")) {
            String[] deadlineSplit = split[1].trim().split("/by");
            if (deadlineSplit.length == 1) {
                throw new InvalidCommandException(
                        "Invalid format >:( Make sure you used '/by' to indicate the deadline!");
            }
            newTask = new Deadline(deadlineSplit[0].trim(), deadlineSplit[1].trim());
        } else {
            String[] fromSplit = split[1].split("/from");
            if (fromSplit.length == 1) {
                throw new InvalidCommandException(
                        "Invalid format >:( Make sure you used '/from' to indicate event start time!"
                );
            }
            String eventName = fromSplit[0].trim();

            String[] toSplit = fromSplit[1].split("/to");
            if (toSplit.length == 1) {
                throw new InvalidCommandException(
                        "Invalid format >:( Make sure you used '/to' to indicate event end time!"
                );
            }
            String from = toSplit[0].trim();
            String to = toSplit[1].trim();
            newTask = new Event(eventName, from, to);
        }
        this.taskList.addNewTask(newTask);
    }

    private static void resetSave() {
        try {
            Files.delete(Paths.get(storePath));
        } catch (IOException e) {
            System.err.println("Error deleting last saved file: " + e.getMessage());
        }
    }
    private static void storeData(ArrayList<Task> list) {
        for (Task task : list) {
            try {
                task.writeToData(storePath);
            } catch (IOException e) {
                System.err.println("Error writing file to storage: " + e.getMessage());
            }
        }
    }
    public void greet() {
        String name = "Yippee";
        System.out.println("    ____________________________________________________________");
        System.out.printf("      Hello! I'm %s\n", name);
        System.out.println("      What can I do for you?");
        System.out.println("    ____________________________________________________________");
    }
    public void run() {
        greet();
        boolean isExit = false;

        while(!isExit) {
            try {
                String command = this.ui.readCommand();
                ui.showLine();
                command.execute();
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.printError(e);
            } finally {
                ui.showLine();
            }
        }
        this.ui.endCommands();


    }
    public static void main(String[] args) {

        new Duke("./data/storage.txt").run();

        //exit
        System.out.println("    ____________________________________________________________");
        System.out.println("      Bye! Hope to see you again soon wooo!");
        System.out.println("    ____________________________________________________________");

        resetSave();
        storeData(list);
    }
}
