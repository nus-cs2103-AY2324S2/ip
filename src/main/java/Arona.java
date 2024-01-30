import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;

public class Arona {
    private String name;
    private ArrayList<Task> tasks = new ArrayList<Task>();

    public Arona(String name) {
        this.name = name;
    }
    public void greetings() {
        String logo ="    _____ \n" +
/* */                "   /  _  \\_______   ____   _____ _____ \n" +
/*   */              "  /  /_\\  \\_  __  \\/  _ \\ /     \\___  \\ \n" +
        /*   */      " /    |    \\  | \\_ ( <_> )   |   \\/ __ \\_ \n" +
/* */                " \\____|__  /__|    \\____/|___|_  (____  / \n" +
/* */                "         \\/                    \\/     \\/ \n";
        System.out.println("開始中... \n" + logo);
//        String reply = "Hi! I'm " + this.name + ". What can I do for you?";
        String reply = "こんにちは先生、私は" + this.name + "アロナです. \n"
                + "どういうご用件ですか?　\n"
                + "ここで先生のスケジュールが決まります";
        System.out.println(reply);
    }

    public void quitApplication() {
        String reply = "Goodbye sensei! Hope to see you soon!";
        System.out.println(reply);
    }

    public void addTask(String input) throws TaskException {
        String[] splitInput = input.split(" ", 2);
        String type = splitInput[0];

        if (splitInput.length == 0) {
            throw new TaskException("Sensei! Please enter some tasks!");
        } else if (!containsEnumValue(TaskEnum.class, type)) {
            throw new TaskException("Sensei, Arona does not know what that means!.");
        } else if (splitInput.length == 1) {
            throw new TaskException("Sensei! Please provide some task description!");
        }

        String[] info = splitInput[1].split("/");
        String description = info[0];
        switch(type) {
            case "todo":
                tasks.add(new Todo(description));
                break;
            case "deadline":
                if (info.length < 2) throw new TaskException("Sensei! Please provide a deadline!");

                String by = info[1].replaceAll("by", "").trim();
                tasks.add(new Deadline(description, by));
                break;
            case "event":
                if (info.length < 3) throw new TaskException("Sensei! Please provide an event begin and deadline.");

                String from = info[1].replaceAll("from", "").trim();
                by = info[2].replaceAll("to", "").trim();
                tasks.add(new Event(description, from, by));
                break;
        }

        System.out.println("Arona has added this task to sensei's task list!: ");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Arona has counted " + tasks.size() + " tasks in the list!");
    }

    public void DeleteTask(int taskNum) throws IndexOutOfBoundsException {
        if (taskNum > tasks.size()) throw new IndexOutOfBoundsException("Sensei, the task doesn't exist!");

        int index = taskNum - 1;
        Task task = tasks.get(index);
        tasks.remove(index);

        System.out.println("Arona has removed this task!: ");
        System.out.println(task.toString());
        System.out.println("Arona has counted " + tasks.size() + " tasks in the list!");
    }

    public void printTasks() {
        System.out.println("Sensei! Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) break;
            Task task = tasks.get(i);
            System.out.println(i + 1 + "." + task.toString());
        }
    }

    public void changeTaskStatus(int taskNum, boolean status) throws IndexOutOfBoundsException {
        if (taskNum > tasks.size()) throw new IndexOutOfBoundsException("Sensei! The task doesn't exist!");

        int index = taskNum - 1;
        Task task = tasks.get(index);

        if (task.getStatus() == status) {
            System.out.println("Sensei, the task has already been marked as " + (status ? "done!" : "not done!"));
            return;
        }

        task.setStatusIcon(status);

        if (status) {
            System.out.println("Congratulation, sensei! Arona has marked the task as done!:");
        } else {
            System.out.println("I understand, sensei! Arona has marked the task as not done!:");
        }
        System.out.println(task.toString());
    }

    public void readTaskFromFile(String filePath) throws TaskException {
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            int counter = 1;

            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(new OutputStream() {
                @Override
                public void write(int b) {
                    // Do nothing
                }
            }));

            while (line != null) {
                String[] splitString = line.split("\\|", 0);
                String taskStatus = splitString[1];
                String taskInput = line.replaceAll("\\| 0 \\|", "")
                        .replaceAll("\\| 1 \\|", "")
                        .replaceAll("\\|", "/")
                        .replaceAll("T", "todo")
                        .replaceAll("D", "deadline")
                        .replaceAll("E", "event")
                        .replaceAll("  ", " ");

                addTask(taskInput);
                boolean isTaskDone = taskStatus.trim().equals("1") ? true : false;
                changeTaskStatus(counter, isTaskDone);
                counter++;

                line = reader.readLine();
            }
            reader.close();
            System.setOut(originalOut);
        } catch (FileNotFoundException e) {
            System.err.println("Oops sensei, Arona cannot find your task file!");
        } catch (IOException e) {
            System.err.println("Sensei! There were some errors reading tasks from the file: " + e.getMessage());
        }
    }

    public void saveTaskToFile(String filePath) {
        try {
            File file = new File(filePath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Task task : tasks) {
                System.out.println(task.toString());
                writer.write(taskToString(task));
                writer.newLine();
            }

            writer.flush();
            writer.close();
        } catch (IOException e ) {
            System.err.println("Sensei! There were some errors reading tasks from the file: " + e.getMessage());
        }
    }
    public String taskToString(Task task) {
        String output = task.toString()
                .replaceAll("\\[T]", "T")
                .replaceAll("\\[D]", "D")
                .replaceAll("\\[E]", "E")
                .replaceAll("\\[ ]", " \\| 0 \\|")
                .replaceAll("\\[X]", " \\| 1 \\|")
                .replaceAll("\\(by:", "\\|")
                .replaceAll("\\)", "")
                .replaceAll("\\(from:", "\\|")
                .replaceAll("to:", "\\|")
                .replaceAll("\\)", "");
        return output;
    }

    public static void main(String[] args) throws TaskException {
        Scanner scanner = new Scanner(System.in);
        Arona arona = new Arona("");
        arona.greetings();
        String filePath = "./src/data/tasklist.txt";
        arona.readTaskFromFile(filePath);


        while (true) {
            try {
                String input = scanner.nextLine();
                String command = input.split(" ", 0)[0];
                switch(command) {
                    case "bye":
                        arona.quitApplication();
                        return;
                    case "list":
                        arona.printTasks();
                        break;
                    case "mark":
                        if (input.split(" ", 0).length == 1) throw new AronaException("Sensei! Please provide a task number!");
                        int taskNum = Integer.parseInt(input.split(" ", 0)[1]);
                        arona.changeTaskStatus(taskNum, true);
                        arona.saveTaskToFile(filePath);
                        break;
                    case "unmark":
                        if (input.split(" ", 0).length == 1) throw new AronaException("Sensei! Please provide a task number!");
                        taskNum = Integer.parseInt(input.split(" ", 0)[1]);
                        arona.changeTaskStatus(taskNum, false);
                        arona.saveTaskToFile(filePath);
                        break;
                    case "delete":
                        if (input.split(" ", 0).length == 1) throw new AronaException("Sensei! Please provide a task number!");
                        taskNum = Integer.parseInt(input.split(" ", 0)[1]);
                        arona.DeleteTask(taskNum);
                        arona.saveTaskToFile(filePath);
                        break;
                    default:
                        arona.addTask(input);
                        arona.saveTaskToFile(filePath);
                        break;
                }
            } catch (TaskException e) {
                System.err.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.err.println(e.getMessage());
            } catch (AronaException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    private static <E extends Enum<E>> boolean containsEnumValue(Class<E> enumClass, String value) {
        for (Enum<E> enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
