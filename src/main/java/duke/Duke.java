package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    private static TaskList taskList = new TaskList();

    private static final String FILE_NAME = "duke.state";

    private static void writeState(OutputStream file) throws IOException {
        try (ObjectOutputStream stream = new ObjectOutputStream(file)) {
            stream.writeObject(taskList);
        }
    }

    private static void readState(InputStream file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream stream = new ObjectInputStream(file)) {
            taskList = (TaskList) stream.readObject();
        }
    }

    private static void cat() {
        System.out.println(" |\\ /| ");
        System.out.println("=(O O)=");
        System.out.println(" /   \\ ");
    }

    private static void line() {
        for (int i = 0; i < 72; i++) {
            System.out.print('─');
        }
        System.out.print('\n');
    }

    private static void hello() {
        cat();
        System.out.println("Hello! I'm the cat that lives in your walls.");
        System.out.println("What do you need?");
        line();
    }

    private static void bye() {
        System.out.println("*The cat recedes into the wall with a bored look on its face*");
        line();
    }

    public static void repl() {
        Scanner sc = new Scanner(System.in);

        label:
        while (sc.hasNextLine()) {
            line();
            String command = sc.next();
            String data = sc.nextLine().trim();
            switch (command) {
            case "bye":
                break label;
            case "list":
                System.out.print(taskList);
                break;
            case "mark":
            case "unmark":
            case "delete":
                try {
                    int index = Integer.parseInt(data) - 1;
                    Task task = taskList.getTask(index);
                    if (command.equals("delete")) {
                        taskList.deleteTask(index);
                        System.out.println("Alright, I've deleted the task:\n  " + task);
                        break;
                    }
                    if (command.equals("mark")) {
                        task.setComplete();
                    } else {
                        task.setIncomplete();
                    }
                    System.out.println("Alright, I've set the task as " + task.status() + ":\n  " + task);
                } catch (TaskList.TaskNotFound e) {
                    System.out.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("\"" + data + "\" is not a number. Please try again.");
                }
                ;
                break;
            case "todo":
            case "deadline":
            case "event":
                try {
                    Task task = Task.of(command, data);
                    taskList.addTask(task);
                    System.out.println("Added task " + task.describe());
                } catch (Task.InvalidComponents | Task.InvalidType e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println("Could not parse date: " + e.toString());
                }
                break;
            default:
                System.out.println("I have no idea what you want.\n" +
                        "I can respond to \"list\", \"deadline\", \"event\", \"todo\", \"mark\" and \"unmark\"");
            }
            line();
        }
    }

    public static void main(String[] args) {
        File file;
        file = new File(FILE_NAME);
        boolean successful = false;
        try {
            FileInputStream in = new FileInputStream(file);
            readState(in);
            successful = true;
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find state file \"" + FILE_NAME + "\"");
        } catch (IOException e) {
            System.out.println("Cannot read from state file \"" + FILE_NAME + "\"");
        } catch (ClassNotFoundException e) {
            System.out.println("The data has been corrupted");
        } finally {
            if (!successful) {
                System.out.println("Continuing with no saved state.");
            }
        }

        hello();
        repl();
        bye();

        try {
            FileOutputStream out = new FileOutputStream(file);
            writeState(out);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find state file \"" + FILE_NAME + "\"");
        } catch (IOException e) {
            System.out.println("Cannot write to state file \"" + FILE_NAME + "\"");
        }
    }
}
