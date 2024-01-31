import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Luke {

    //Logo created using https://patorjk.com/software/taag/#p=display&f=Varsity&t=Luke
    private static String logo = "  _____             __             \n"
            + " |_   _|           [  |  _         \n"
            + "   | |     __   _   | | / ] .---.  \n"
            + "   | |   _[  | | |  | '' < / /__\\\\ \n"
            + "  _| |__/ || \\_/ |, | |`\\ \\| \\__., \n"
            + " |________|'.__.'_/[__|  \\_]'.__.' ";

    public static void main(String[] args) throws LukeException {
        History history;
        greet();

        String wd = System.getProperty("user.dir");
        Path directoryPath = Paths.get(wd,  "data");
        Path historyPath = Paths.get(wd, "data", "history.txt");
        //create directory (if it does not already exist)
        try {
            Files.createDirectory(directoryPath);
        } catch (FileAlreadyExistsException e) {
            //directory already exists.
        } catch (IOException e) {
            System.out.println("Failed to create directory");
        }
        //create file (if it does not already exist)
        try {
            Files.createFile(historyPath);
        } catch (FileAlreadyExistsException e) {
            //file already exists.
        } catch (IOException e) {
            System.out.println("Failed to create file");
            return;
        }

        //load the file if there is save data (reference: https://www.baeldung.com/java-serialization)
        //otherwise, create a new History object.
        File historyFile = new File(String.valueOf(historyPath));
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(historyFile));
            history = (History) inputStream.readObject();
        } catch (IOException e) {
            //System.out.println("No save data found, creating new save.");
            history = new History();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            return;
        }

        Scanner sc = new Scanner(System.in);
        while (true) {
            //task mode
            //first, determine the type of input.
            String input = sc.nextLine().trim(); //trim removes preceding and trailing whitespace.
            if (input.equals("bye")) {
                history.saveHistory(historyFile);
                bye();
                sc.close();
                break;
            } else if (input.equals("list")) {
                history.listHistory();
            } else if (input.split(" ")[0].equals("mark")) {
                history.markTask(input);
            } else if (input.split(" ")[0].equals("delete")) {
                history.deleteTask(input);
            } else {
                //it is a task.
                Task task = makeTask(input);
                history.addTask(task);
            }
        }
    }

    private static void greet() {
        System.out.println("I'm\n" + logo + "\n");
        System.out.println("Don't expect to get too chummy with me, you got that?\n");
    }

    private static void bye() {
        System.out.println("Don't be ridiculous!\n" +
                "It's... it's not like I want to see you again or anything!\n");
    }

    //Makes a task from string input. Returns null if invalid string.
    private static Task makeTask(String input) {
        Task task;
        String taskType = input.split(" ")[0];
        if (taskType.equals("todo")) {
            task = new Todo(input.substring(4).trim()); //TODO: better not hardcode 5 lol
            if (input.split(" ").length < 2) {
                System.out.println("You have eyes for a reason, don't you?");
                System.out.println("[Missing todo description]\n");
                return null;
            }
        } else if (taskType.equals("deadline")) {
            try {
                task = new Deadline(input.split("/")[0].substring(8).trim(),
                        input.split("/")[1].substring(2).trim());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Hey! You forgot something! Be glad I'm here to remind you.");
                System.out.println("[Missing deadline parameter(s)]\n");
                return null;
            }
        } else if (taskType.equals("event")) {
            try {
                task = new Event(input.split("/")[0].substring(5).trim(),
                        input.split("/")[1].substring(4).trim(),
                        input.split("/")[2].substring(2).trim());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("/// I don't know when you are free... ///");
                System.out.println("[Missing event parameter(s)]\n");
                return null;
            }
        } else {
            System.out.println("/// What on earth are you saying! ///");
            System.out.println("[Command not found]\n");
            return null;
        }
        return task;
    }

}


