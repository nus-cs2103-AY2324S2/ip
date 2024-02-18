import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;

import java.time.LocalDate;

public class Damon {
    private static ArrayList<Task> storage;

    Damon() {
        storage = new ArrayList<Task>();
    }

    public static void main(String[] args) {
        Damon damon = new Damon();
        String logo = " ____\n"
                + "|  _  \\\n"
                + "| | | |\n"
                + "| |_| |\n"
                + "|____/ \n";
        System.out.println("Hello from\n" + logo);

        createFile();

        //Solution below adapted from https://stackoverflow.com/a/16252290
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String inputString = scanner.nextLine();
            if (inputString.equals("bye")) {
                stop();
                break;
            } else if (inputString.equals("list")) {
                showList();
            } else if (inputString.startsWith("mark")) {
                int index = Integer.parseInt(inputString.substring(5)) - 1;
                mark(index);
            } else if (inputString.startsWith("unmark")) {
                int index = Integer.parseInt(inputString.substring(7)) - 1;
                unmark(index);
            } else if (inputString.startsWith("delete")){
                int index = Integer.parseInt(inputString.substring(7)) - 1;
                delete(index);
            } else {
                storeInput(inputString);
            }
        }

        saveFile();
    }

    static void stop() {
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n");
    }

    static void showList () {
        int n = storage.size();
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list:\n");
        for (int i = 0; i < n; i++) {
            Task currentTask = storage.get(i);
            System.out.println((i + 1) + "." + currentTask.toString() + "\n");
        }
        System.out.println("____________________________________________________________\n");
    }

    static void storeInput(String inputString) {
        Task newTask;
        if (inputString.startsWith("todo")) {
            newTask = storeToDoTask(inputString);
        } else if (inputString.startsWith("deadline")) {
            newTask = storeDeadlineTask(inputString);
        } else {
            newTask = storeEventTask(inputString);
        }
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + newTask + "\n"
                + "Now you have " + storage.size() + " tasks in the list.\n"
                + "____________________________________________________________\n");
    }

    static Task storeToDoTask(String inputString) {
        String description = inputString.substring(5);
        ToDo newToDo = new ToDo(description);
        storage.add(newToDo);
        return newToDo;
    }

    static Task storeDeadlineTask(String inputString) {
        String[] splittedString = inputString.substring(9).split(" /by ");
        String description = splittedString[0];
        String by = splittedString[1];
        Deadline newDeadline = new Deadline(description, LocalDate.parse(by));
        storage.add(newDeadline);
        return newDeadline;
    }

    static Task storeEventTask(String inputString) {
        String[] firstSplittedString = inputString.substring(6).split(" /from ");
        String description = firstSplittedString[0];
        String[] secondSplittedString = firstSplittedString[1].split(" /to ");
        String startTime = secondSplittedString[0];
        String endTime = secondSplittedString[1];
        Event newEvent = new Event(description, startTime, endTime);
        storage.add(newEvent);
        return newEvent;
    }

    static void echo(String inputString) {
            System.out.println("____________________________________________________________\n"
                    + inputString + "\n"
                    + "____________________________________________________________\n");
    }

    static void mark(int index) {
        storage.get(index).markAsDone();
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + storage.get(index).toString() + "\n"
                + "____________________________________________________________\n");
    }

    static void unmark(int index) {
        storage.get(index).markBackNotDone();
        System.out.println("____________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n"
                + storage.get(index).toString() + "\n"
                + "____________________________________________________________\n");
    }

    static void delete(int index) {
        System.out.println("____________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + storage.get(index).toString()
                + "Now you have " + (storage.size() - 1) + " tasks in the list.\n"
                + "____________________________________________________________\n");
        storage.remove(index);
    }

    //Solution below inspired by https://www.w3schools.com/java/java_files_create.asp
    static void createFile() {
        try {
            File storageFile = new File("..\\damon.txt");
            if (storageFile.createNewFile()) {
                System.out.println("File: " + storageFile + " is created");
            } else {
                System.out.println("File: " + storageFile + " is already here");
            }
        } catch (IOException e) {
            System.out.println("Some IOException happens");
        }
    }

    //Solution below inspired by https://www.w3schools.com/java/java_files_create.asp
    static void saveFile() {
        try {
            FileWriter writer = new FileWriter("..\\damon.txt", false);
            for (Task task : storage) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Some IOException happens");
        }
    }
}
