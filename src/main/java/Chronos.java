import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

public class Chronos {
    private static final String DIVIDER = "        ------------------------------------------------------------";
    private static final String POSSIBLE_COMMANDS = "        TODO     --- todo [task name]\n" +
                                                    "        DEADLINE --- deadline [task name] /by [due date]\n" +
                                                    "        EVENT    --- event [task name] /from [date] /to [date]" ;

    private static String filePath = "./data/chronos.txt";
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int noOfTasks = 0;

    public static void greetUser() {
        System.out.println(DIVIDER);
        System.out.println("        Hello! I'm Chronos.");
        System.out.println("        What can I do for you?");
        System.out.println(DIVIDER);
    }

    public static void bidGoodbye() {
        System.out.println(DIVIDER);
        System.out.println("        Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void printTasks() {
        System.out.println(DIVIDER);
        System.out.println("        Here are the tasks in your list:");
        for (int i = 1; i < noOfTasks + 1; i++) {
            Task currentTask = tasks.get(i - 1);
            System.out.println("        " + i + ". " + currentTask.toString());
        }
        System.out.println(DIVIDER);
    }

    public static void printHelp() {
        System.out.println(DIVIDER);
        System.out.println("        There are no outstanding tasks in your list.\n");
        System.out.println("        You may add various tasks with the commands below:\n" + POSSIBLE_COMMANDS);
        System.out.println(DIVIDER);
    }

    public static void saveTasks(FileWriter fw) throws IOException {
        fw = new FileWriter(filePath);
        fw.write(DIVIDER + "\n");
        for (int j = 1; j < noOfTasks + 1; j++) {
            Task currentTask = tasks.get(j - 1);
            fw.write("        " + j + ". " + currentTask.toString() + "\n");
        }
        fw.write(DIVIDER + "\n");
        fw.close();
    }

    public static void addToDo(String[] token) {
        Todo todo = new Todo(token[1]);
        tasks.add(todo);
        noOfTasks++;

        System.out.println(DIVIDER);
        System.out.println("        Got it. I've added this task:");
        System.out.println("          " + todo);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public static void addDeadline(String[] descriptionAndBy, String[] dueDate) {
        Deadline deadline = new Deadline(descriptionAndBy[0].trim(), dueDate[1]);
        tasks.add(deadline);
        noOfTasks++;

        System.out.println(DIVIDER);
        System.out.println("        Got it. I've added this task:");
        System.out.println("          " + deadline);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public static void addEvent(String task, String from, String to) {
        Event event = new Event(task.trim(), from.trim(), to.trim());
        tasks.add(event);
        noOfTasks++;

        System.out.println(DIVIDER);
        System.out.println("        Got it. I've added this task:");
        System.out.println("          " + event);
        System.out.println("        Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public static void markTask(int selectedTaskNumberToBeMarked) {
        Task selectedTaskToBeMarked = tasks.get(selectedTaskNumberToBeMarked - 1);
        selectedTaskToBeMarked.setMarked();
        tasks.set(selectedTaskNumberToBeMarked - 1, selectedTaskToBeMarked);
        System.out.println(DIVIDER);
        System.out.println("        Nice! I've marked this task as done:");
        System.out.println("          " + selectedTaskToBeMarked);
        System.out.println(DIVIDER);
    }

    public static void deleteTask(int i) {
        Task deletedTask = tasks.get(i - 1);
        noOfTasks--;

        System.out.println(DIVIDER);
        System.out.println("        Noted. I've removed this task:");
        System.out.println("          " + deletedTask);
        System.out.println("        Now you have " + noOfTasks + " tasks in the list.");
        System.out.println(DIVIDER);
        tasks.remove(i - 1);
    }

    public static void main(String[] args) throws IOException {
        String dirName = "data";
        Path dirPath = Paths.get(dirName);
        if (!Files.exists(dirPath)) {
            Files.createDirectory(dirPath);
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write("There are no outstanding tasks in your list.");
        fw.close();

        Chronos.greetUser();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            String[] token = command.split(" ", 2);

            if (token[0].equals("bye")) {
                Chronos.bidGoodbye();
                break;
            } else {
                switch(token[0]) {
                    case "list":
                        if (token.length != 1) {
                            System.out.println(DIVIDER);
                            System.out.println("        Invalid command. To print the list of tasks, please use list only.");
                            System.out.println(DIVIDER);
                        } else if (tasks.isEmpty()) {
                            Chronos.printHelp();
                        } else {
                            Chronos.printTasks();
                        }
                        break;
                    case "mark":
                        if (token.length != 2 || token[1].trim().isEmpty()) {
                            System.out.println(DIVIDER);
                            System.out.println("        Missing task number. Please include the task number to be marked.");
                            System.out.println(DIVIDER);
                        } else {
                            try {
                                int i = Integer.parseInt(token[1]);
                                Chronos.markTask(i);
                                Chronos.saveTasks(fw);
                            } catch (NumberFormatException e) {
                                System.out.println(DIVIDER);
                                System.out.println("        Task number is not an integer. Please include a valid task number.");
                                System.out.println(DIVIDER);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println(DIVIDER);
                                System.out.println("        Task number out of range. Please include a valid task number.");
                                System.out.println(DIVIDER);
                            }
                        }
                        break;
                    case "unmark":
                        if (token.length != 2 || token[1].trim().isEmpty()) {
                            System.out.println(DIVIDER);
                            System.out.println("        Missing task number. Please include the task number to be unmarked.");
                            System.out.println(DIVIDER);
                        } else {
                            try {
                                int selectedTaskNumberToBeUnmarked = Integer.parseInt(token[1]);
                                Task selectedTaskToBeUnmarked = tasks.get(selectedTaskNumberToBeUnmarked - 1);
                                selectedTaskToBeUnmarked.setUnmarked();
                                tasks.set(selectedTaskNumberToBeUnmarked - 1, selectedTaskToBeUnmarked);
                                System.out.println(DIVIDER);
                                System.out.println("        OK, I've marked this task as not done yet:");
                                System.out.println("          " + selectedTaskToBeUnmarked);
                                System.out.println(DIVIDER);

                                Chronos.saveTasks(fw);
                            } catch (NumberFormatException e) {
                                System.out.println(DIVIDER);
                                System.out.println("        Task number is not an integer. Please include a valid task number.");
                                System.out.println(DIVIDER);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println(DIVIDER);
                                System.out.println("        Task number out of range. Please include a valid task number.");
                                System.out.println(DIVIDER);
                            }
                        }
                        break;
                    case "todo":
                        if (token.length != 2 || token[1].trim().isEmpty()) {
                            System.out.println(DIVIDER);
                            System.out.println("        Missing description. Please include a description of your todo.");
                            System.out.println(DIVIDER);
                        } else {
                            Chronos.addToDo(token);
                            Chronos.saveTasks(fw);
                        }
                        break;
                    case "deadline":
                        try {
                            String[] descriptionAndBy = token[1].split("/");
                            String[] dueDate = descriptionAndBy[1].split(" ", 2);

                            Chronos.addDeadline(descriptionAndBy, dueDate);
                            Chronos.saveTasks(fw);
                        } catch (Exception e) {
                            System.out.println(DIVIDER);
                            System.out.println("        Invalid command. Please include a task name and a valid due date following the syntax of the example below:");
                            System.out.println("        e.g. deadline return library book /by 6th March");
                        }
                        break;
                    case "event":
                        try {
                            String[] descriptionAndFromAndTo = token[1].split("/");
                            String task = descriptionAndFromAndTo[0];
                            String from = descriptionAndFromAndTo[1].split(" ", 2)[1];
                            String to = descriptionAndFromAndTo[2].split(" ", 2)[1];

                            Chronos.addEvent(task, from, to);
                            Chronos.saveTasks(fw);
                        } catch (Exception e) {
                            System.out.println(DIVIDER);
                            System.out.println("        Invalid command. Please include a task name and a valid due date following the syntax of the example below:");
                            System.out.println("        e.g. event project meeting /from 2 Feb 2pm /to 2 Feb 4pm");
                            System.out.println(DIVIDER);
                        }
                        break;
                    case "delete":
                        if (token.length != 2 || token[1].trim().isEmpty()) {
                            System.out.println(DIVIDER);
                            System.out.println("        Missing task number. Please include the task number to be deleted.");
                            System.out.println(DIVIDER);
                        } else {
                            try {
                                int i = Integer.parseInt(token[1]);
                                Chronos.deleteTask(i);
                                Chronos.saveTasks(fw);
                            } catch (NumberFormatException e) {
                                System.out.println(DIVIDER);
                                System.out.println("        Task number is not an integer. Please include a valid task number.");
                                System.out.println(DIVIDER);
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println(DIVIDER);
                                System.out.println("        Task number out of range. Please include a valid task number.");
                                System.out.println(DIVIDER);
                            }
                        }
                        break;
                    default:
                        System.out.println(DIVIDER);
                        System.out.println("        Invalid command. Please try again.");
                        System.out.println(DIVIDER);
                        break;
                }
            }
        }

        sc.close();
    }
}