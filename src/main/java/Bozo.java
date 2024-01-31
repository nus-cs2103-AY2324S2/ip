import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Bozo {
    protected static ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) throws BozoException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Bozo bozo = new Bozo();
        Bozo.loadFile();

        printLine();
        System.out.println("Hello! I'm Bozo");
        System.out.println("What can I do for you?");
        printLine();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (true) {
            if (input.equals("bye")) {
                Bozo.saveList();
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            } else if (input.equals("list")){
                printLine();
                if (list.size() == 0) {
                    System.out.println("No tasks! You're a free man! :DD");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    int counter = 1;
                    for (Task task : list) {
                        System.out.println(counter + ". " + task.toString());
                        counter++;
                    }
                }
                printLine();
                input = sc.nextLine();
            } else if (input.startsWith("mark")) {
                printLine();
                String taskStr = input.substring(input.indexOf(" ") + 1);
                int taskNum = Integer.parseInt(taskStr);
                if (taskNum > 0 && taskNum < list.size() + 1) {
                    Task currentTask = list.get(taskNum - 1);
                    currentTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + currentTask);
                    printLine();
                    input = sc.nextLine();
                } else {
                    System.out.println("Error: Task does not exist!");
                    printLine();
                    input = sc.nextLine();
                }
            } else if (input.startsWith("unmark")) {
                printLine();
                String taskStr = input.substring(input.indexOf(" ") + 1);
                int taskNum = Integer.parseInt(taskStr);
                if (taskNum > 0 && taskNum < list.size() + 1) {
                    Task currentTask = list.get(taskNum - 1);
                    currentTask.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + currentTask);
                    printLine();
                    input = sc.nextLine();
                } else {
                    System.out.println("Error: Task does not exist!");
                    printLine();
                    input = sc.nextLine();
                }
            } else if (input.startsWith("delete")) {
                printLine();
                if (list.size() == 0) {
                    System.out.println("You have no tasks to delete! :O");
                } else {
                    String taskStr = input.substring(input.indexOf(" ") + 1);
                    int taskNum = Integer.parseInt(taskStr);
                    Task currentTask = list.remove(taskNum - 1);
                    System.out.println("Noted: I've removed this task:");
                    System.out.println("  " + currentTask);
                }
                printLine();
                input = sc.nextLine();
            } else {
                printLine();
                if (input.startsWith("todo")) {
                    if (input.length() < 6) {
                        throw new BozoException("I want that too but ur todo can't be empty :-((");
                    } else {
                        Todo td = new Todo(input.substring(input.indexOf(" ") + 1));
                        list.add(td);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + td);
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    }
                } else if (input.startsWith("deadline")) {
                    if (input.length() < 10) {
                        throw new BozoException("I want that too but ur deadline can't be empty :-((");
                    } else {
                        int indexOfSlash = input.indexOf("/by");
                        Deadline d = new Deadline(input.substring(input.indexOf(" ")+ 1, indexOfSlash - 1),
                                input.substring(indexOfSlash + 4));
                        list.add(d);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + d);
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    }
                } else if (input.startsWith("event")) {
                    if (input.length() < 7) {
                        throw new BozoException("I want that too but ur event can't be empty :-((");
                    } else {
                        int indexOfFrom = input.indexOf("/from");
                        int indexOfTo = input.indexOf("/to");
                        Event e = new Event(input.substring(input.indexOf(" ")+ 1, indexOfFrom - 1),
                                input.substring(indexOfFrom + 6, indexOfTo - 1),
                                 input.substring(indexOfTo + 4));
                        list.add(e);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + e);
                        System.out.println("Now you have " + list.size() + " tasks in the list.");
                    }
                } else {
                    throw new BozoException("Oops! I don't know what you are saying :(");

                }
                printLine();
                input = sc.nextLine();
            }
        }
    }

    public static void loadFile() {
        try {
            File txtFile = new File("./data/bozo.txt");
            Scanner scan = new Scanner(txtFile);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                int isDone = Integer.parseInt(parts[1]);
                String taskName = parts[2];

                switch (type) {
                    case "T":
                        Todo td = new Todo(taskName);
                        if (isDone == 1) {
                            td.isDone = true;
                        } else {
                            td.isDone = false;
                        }
                        list.add(td);
                        break;
                    case "D":
                        Deadline d = new Deadline(taskName, parts[3]);
                        if (isDone == 1) {
                            d.isDone = true;
                        } else {
                            d.isDone = false;
                        }
                        list.add(d);
                        break;
                    case "E":
                        Event e = new Event(taskName, parts[3], parts[4]);
                        if (isDone == 1) {
                            e.isDone = true;
                        } else {
                            e.isDone = false;
                        }
                        list.add(e);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No tasks saved :-((");
        }
    }

    public static void saveList() {
        try {
            File txtFile = new File("./data/bozo.txt");
            txtFile.getParentFile().mkdirs();
            FileWriter f = new FileWriter(txtFile);
            for (Task task : list) {
                f.write(task.save());
                f.write(System.lineSeparator());
            }
            f.close();

        } catch (IOException e) {
            System.out.println("I can't save ur list :((");
        }
    }

    public static void printLine() {
        System.out.println("_________________________________________________");
    }
}
