import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Gronk {
    public static String WELCOME = "\tHi, I'm Gronk!\n"
            + "\tWhat are we up to today?";
    public static String GOODBYE = "\tSystem closing. Goodbye!";
    public static ArrayList<Task> tasks;

    public static void lines() { // prints out a line
        System.out.println("  ----------------------------------------");
    }

    public static void printMessage(String message) { // the system sends a message
        lines();
        System.out.println(message);
        lines();
    }

    public static void printList() {
        try {
            if (tasks.size() == 0) {
                throw new EmptyListException();
            }
            String m = "";
            for (int j = 0; j < tasks.size(); j++) {
                m += "\t" + Integer.toString(j + 1) + ". " + tasks.get(j).toString() + "\n";
            }
            printMessage(m.substring(0, m.length() - 1));
        } catch (EmptyListException e) {
            printMessage(e.toString());
        }
    }

    public static void saveTasks() {
        try {
            FileWriter myWriter = new FileWriter("tasks.txt");
            for (Task t: tasks) {
                myWriter.write(t.saveFormat() + "\n");
            }
            myWriter.close();
            printMessage("\tTasks saved successfully!");
        } catch (IOException e) {
            printMessage("\tFailed to save.");
        }
    }

    public static void loadTasks() {
        try {
            File myFile = new File("tasks.txt");
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                loadMessage(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            printMessage("\tNothing saved.");
        }
    }

    public static void loadMessage(String m) {
        String[] message = m.split(",");
        int messageType = message.length;
        if (messageType == 2) {
            tasks.add(new Todo(
                    message[1],
                    Integer.parseInt(message[0])
            ));
        } else if (messageType == 3) {
            tasks.add(new Deadline(
                    message[1],
                    Integer.parseInt(message[0]),
                    message[2]
            ));
        } else if (messageType == 4) {
            tasks.add(new Event(
                    message[1],
                    Integer.parseInt(message[0]),
                    message[2],
                    message[3]
            ));
        }
        //ArrayList<String> message = new ArrayList<>(Arrays.asList(m.split(",")));

    }

    // TODO: Add "Task added" message to each statement
    public static void parseMessage(String m) {
        try {
            if (m.equals("list")) {
                printList();
            } else {
                ArrayList<String> splitMessage = new ArrayList<>(Arrays.asList(m.split("")));
                int messageLength = splitMessage.size();
                if (messageLength == 1) {
                    String word = splitMessage.get(1);
                    if (word.equals("todo") || word.equals("deadline") || word.equals("event")) {
                        throw new EmptyDescException();
                    } else {
                        throw new DukeException();
                    }
                } else if (splitMessage.get(0).equals("todo")) {
                    tasks.add(new Todo(m.substring(5), 0));
                    printMessage("\tTask added: " + m.substring(5));
                } else if (splitMessage.get(0).equals("deadline")) {
                    String[] words = m.substring(9).split(" /by");
                    tasks.add(new Deadline(words[0], 0, words[1]));
                    printMessage("\tDeadline added: " + words[0]);
                } else if (splitMessage.get(0).equals("event")) {
                    String[] words = m.substring(6).split(" /from");
                    if (words[1].equals("")) {
                        throw new EmptyDescException();
                    }
                    String[] t2 = words[1].split(" /to");
                    if (t2[0].equals("") || t2[1].equals("")) {
                        throw new EmptyDescException();
                    }
                    tasks.add(new Event(words[0], 0, t2[0], t2[1]));
                    printMessage("\tEvent added: " + words[0]);
                } else if (splitMessage.get(0).equals("mark")) {
                    int ind = Integer.parseInt(splitMessage.get(1));
                    tasks.get(ind).flip();
                    printMessage(tasks.get(ind).statusMessage());
                } else if (splitMessage.get(0).equals("delete")) {
                    int ind = Integer.parseInt(splitMessage.get(1)) - 1;
                    printMessage("\tItem: " + tasks.get(ind).getDesc() + " removed from list.");
                    tasks.remove(ind);
                }
                else {
                    throw new DukeException();
                }
            }
        } catch (DukeException e) {
            printMessage(e.toString());
        } catch (EmptyDescException e) {
            printMessage(e.toString());
        }
    }

    public Gronk() {
        this.tasks = new ArrayList<Task>();
        loadTasks();

        Scanner sc = new Scanner(System.in);
        String message = "";
        printMessage(WELCOME);

        while (true) {
            message = sc.nextLine();
            if (message.equals("bye")) {
                saveTasks();
                break;
            } else {
                parseMessage(message);
            }
        }
        printMessage(GOODBYE);
    }
}
