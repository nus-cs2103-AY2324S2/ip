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
    private static String storePath = "./data/storage.txt";
    public static void echoText(String text) {
        System.out.println("    ____________________________________________________________");
        System.out.printf("      %s\n", text);
        System.out.println("    ____________________________________________________________");
    }

    public static void addList(Task task, ArrayList<Task> list) {
        list.add(task);
        System.out.println("    ____________________________________________________________");
        System.out.println("      Got it! I added:");
        System.out.printf("        %s\n", task.toString());
        System.out.printf("      You now have %d tasks in your list :D\n", list.size());
        System.out.println("    ____________________________________________________________");
    }

    public static void printList(ArrayList<Task> list) {
        int count = 1;
        System.out.println("    ____________________________________________________________");
        if (list.size() == 0) {
            System.out.println("      Nothing added to list yet!");
        }
        for (Task task : list) {
            System.out.printf("      %d. %s\n", count, task.toString());
            count++;
        }
        System.out.println("    ____________________________________________________________");
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

    private static void createTask(String[] split, ArrayList<Task> list) throws InvalidCommandException {
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
        addList(newTask, list);
    }

    private static void loadTasks(Scanner scanner, ArrayList<Task> list) {
        while (scanner.hasNext()) {
            String currentTask = scanner.nextLine();
            String[] taskDetails = currentTask.split("\\|");
            Task newTask = null;
            switch (taskDetails[0]) {
            case "T":
                newTask = new ToDo(taskDetails[2]);
                break;
            case "D":
                newTask = new Deadline(taskDetails[2], taskDetails[3]);
                break;
            case "E":
                newTask = new Event(taskDetails[2], taskDetails[3], taskDetails[4]);
                break;
            }
            if (newTask != null) {
                if (taskDetails[1].equals("true")) {
                    newTask.markDone();
                } else {
                    newTask.markNotDone();
                }
                list.add(newTask);
            }
        }
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
    public static void main(String[] args) {
        String name = "Yippee";
        ArrayList<Task> list = new ArrayList<>();

        //load previous tasks
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(storePath);
        Scanner fileSc = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error trying to create new data storage: " + e.getMessage());
            }
        }
        try {
            fileSc = new Scanner(file);
        } catch(FileNotFoundException e) {
            System.err.println("Storage data file does not exist: " + e.getMessage());
        }
        loadTasks(fileSc, list);
        fileSc.close();
        //greeting
        System.out.println("    ____________________________________________________________");
        System.out.printf("      Hello! I'm %s\n", name);
        System.out.println("      What can I do for you?");
        System.out.println("    ____________________________________________________________");

        //scan for input
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        //keep checking for commands until user says bye
        while (!command.toLowerCase().equals("bye")) {
            // split command by spaces
            String[] split = command.split("\\s+", 2);
            if (split[0].toLowerCase().equals("list")) {
                printList(list);
            } else if (split[0].toLowerCase().equals("mark")) {
                try {
                    markTask(split, list);
                } catch (InvalidCommandException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.printf("      %s\n", e.getMessage());
                    System.out.println("    ____________________________________________________________");
                }
            } else if (split[0].toLowerCase().equals("unmark")) {
                try {
                    unmarkTask(split, list);
                } catch (InvalidCommandException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.printf("      %s\n", e.getMessage());
                    System.out.println("    ____________________________________________________________");
                }
            } else if (split[0].toLowerCase().equals("delete")) {
                try {
                    deleteTask(split, list);
                } catch (InvalidCommandException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.printf("      %s\n", e.getMessage());
                    System.out.println("    ____________________________________________________________");
                }
            } else {
                try {
                    createTask(split, list);
                } catch (InvalidCommandException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.printf("      %s\n", e.getMessage());
                    System.out.println("    ____________________________________________________________");
                }
            }
            command = sc.nextLine();
        }
        sc.close();
        //exit
        System.out.println("    ____________________________________________________________");
        System.out.println("      Bye! Hope to see you again soon wooo!");
        System.out.println("    ____________________________________________________________");

        resetSave();
        storeData(list);
    }
}
