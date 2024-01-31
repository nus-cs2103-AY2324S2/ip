import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Luke {

    //Logo created using https://patorjk.com/software/taag/#p=display&f=Varsity&t=Luke
    private static String logo = "  _____             __             \n"
            + " |_   _|           [  |  _         \n"
            + "   | |     __   _   | | / ] .---.  \n"
            + "   | |   _[  | | |  | '' < / /__\\\\ \n"
            + "  _| |__/ || \\_/ |, | |`\\ \\| \\__., \n"
            + " |________|'.__.'_/[__|  \\_]'.__.' ";

    public static void main(String[] args) throws LukeException {
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

        Scanner sc = new Scanner(System.in);
        while (true) {
            //task mode
            //first, determine the type of input.
            String input = sc.nextLine().trim(); //trim removes preceding and trailing whitespace.
            if (input.equals("bye")) {
                bye();
                sc.close();
                break;
            } else if (input.equals("list")) {
                listTasks();
            } else if (input.split(" ")[0].equals("mark")) {
                markTask(input);
            } else if (input.split(" ")[0].equals("delete")) {
                deleteTask(input);
            } else {
                //it is a task.
                Task task = makeTask(input);
                addTaskToHistory(task);
            }
        }
    }

    private static ArrayList<Task> history = new ArrayList<>();

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

    //Adds task to history.
    private static void addTaskToHistory(Task task) {
        if (task != null) {
            history.add(task);
            System.out.println("I helped you add task '" + task.fullStatus() + "'. But do it yourself next time! Hmmph!"  + "\n");
        }
    }

    //Lists all tasks in history.
    private static void listTasks() {
        int num = 1;
        if (history.size() == 0) {
            System.out.println("Looks like you have way too much free time on your hands, huh.");
            System.out.println("[No items in list]");
        }
        for (Task s : history) {
            if (s.isDone()) {
                System.out.println(num + "." + s.fullStatus());
            } else {
                System.out.println(num + "." + s.fullStatus());
            }
            num += 1;
        }
        System.out.println();
    }

    //Marks a particular task as done. Takes in a string command.
    public static void markTask(String input) {
        int idx;
        try {
            idx = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("I can't help you out if you don't tell me what to mark! ");
            System.out.println("[Missing input parameter for mark]\n");
            return;
        }
        try {
            history.get(idx).complete();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Jeez, you really ought to give me a number I can work with... got that?");
            System.out.println("[Item index exceeds history count]\n");
            return;
        }
        System.out.println("Good work, I guess.");
        System.out.println((idx + 1) + "." + history.get(idx).fullStatus());
        System.out.println();
    }

    //Deletes a specified task. Takes in a string input.
    public static void deleteTask(String input) {
        String fullStatus;
        try {
            int index = Integer.parseInt(input.split(" ")[1].strip()) - 1;
            fullStatus = history.get(index).fullStatus();
            history.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There's nothing there, dummy...");
            System.out.println("[Tried to remove non-existent event]\n");
            return;
        }
        System.out.println("Fine! If that's what you really want...");
        System.out.println("[Removed " + fullStatus + "]\n");
    }

}


