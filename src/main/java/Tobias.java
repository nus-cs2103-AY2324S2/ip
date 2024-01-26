import javax.imageio.IIOException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
public class Tobias {

    public static void addedTaskPrinter(Task task, int size, String divider) {
        System.out.println(divider);
        System.out.println("    Got it. I've added this task: ");
        task.taskPrinter();
        System.out.println("    Now you have " + size + " tasks in the list!");
        System.out.println(divider);
    }

    public static void storeToLocal(List<Task> tasks) {
        String result = "";

        for (Task task : tasks) {
            result += task.storagePrinter() + System.lineSeparator();
        }

        try {
            FileWriter fw = new FileWriter("data/tobias.txt");
            fw.write(result);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void localToList(String data, List<Task> tasks) {
        try {
            if (data.startsWith("T")) {
                boolean isDone = Integer.parseInt(data.substring(8,9)) == 1;

                int desc = data.indexOf("|desc");
                String description = data.substring(desc+5);

                Task newTask = new ToDo(description, isDone);
                tasks.add(newTask);

            }
            else if (data.startsWith("D")) {
                boolean isDone = Integer.parseInt(data.substring(8,9)) == 1;

                int desc = data.indexOf("|desc");
                int by = data.indexOf("|by");
                String description = data.substring(desc+5, by);
                String byDate = data.substring(by+3);
                LocalDateTime dd = dateFromString(byDate);

                Task newTask = new Deadline(description, isDone, dd);

                tasks.add(newTask);

            }
            else if (data.startsWith("E")) {
                boolean isDone = Integer.parseInt(data.substring(8,9)) == 1;

                int desc = data.indexOf("|desc");
                int from = data.indexOf("|from");
                int to = data.indexOf("|to");
                String description = data.substring(desc+5, from);
                String fromDate = data.substring(from+5, to);
                String toDate = data.substring(to+3);

                LocalDateTime f = dateFromString(fromDate);
                LocalDateTime t = dateFromString(toDate);

                Task newTask = new Event(description, isDone, f, t);
                tasks.add(newTask);
            }
            else {
                throw new TobiasException("   Saved file is corrupted!");
            }
        } catch (Exception e) {
            System.out.println("local to list function " + e.getMessage());
        }
    }

    public static List<Task> localToCurrent() {
        List<Task> tasks = new ArrayList<Task>();
        try {
            File f = new File("data/tobias.txt");
            Scanner s = new Scanner(f);

            while (s.hasNext()) {
                localToList(s.nextLine(), tasks);
            }
            s.close();
        } catch (IOException e) {
            System.out.println("local to current function " + e.getMessage());
        }
        return tasks;
    }

    public static LocalDateTime dateFromString(String dateTime) throws TobiasException {
        try {
            return LocalDateTime.parse(dateTime.trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (Exception e) {
            throw new TobiasException("    Kindly enter the date or time in this format : dd-MM-yyyy HHmm");
        }
    }

    public static void commandHandler(String divider, List<Task> tasks) {
        Scanner scanner = new Scanner(System.in);
        String outro = "Bye. Hope to see you soon!";

        Boolean isExit = false;
        String command = "";

        while (!isExit) {
            try {
                command = scanner.nextLine().trim();
                if (command.equals("bye")) {
                    storeToLocal(tasks);
                    System.out.println(divider);
                    System.out.println("    " + outro);
                    System.out.println(divider);
                    isExit = true;
                }
                else if (command.equals("list")) {
                    try {
                        if (tasks.isEmpty()) {
                            throw new TobiasException("    Your list is empty at the moment !");
                        } else {
                            System.out.println(divider);
                            System.out.println("    Here are the tasks in your list:");
                            for (Task task : tasks) {
                                int index = tasks.indexOf(task);
                                task.taskPrinter(index);
                            }
                            System.out.println(divider);
                        }
                    } catch (TobiasException e) {
                        e.printMessage();
                    }
                }
                else if (command.startsWith("mark")) {
                    try {
                        int size = tasks.size();
                        if (command.equals("mark")) {
                            if (size == 0) {
                                throw new TobiasException("    Your list is empty at the moment, add some todos/events/deadlines to MARK them!");
                            } else {
                                throw new TobiasException("    No index provided! Please give a valid index from 1 to " + size +" !!");
                            }
                        }

                        int index = Integer.parseInt(command.substring(5)) - 1;

                        if (index >= 0 && index < tasks.size()) {
                            Task curr = tasks.get(index);
                            curr.markAsDone();

                            System.out.println(divider);
                            System.out.println("    Nice! I've marked this task as done: ");
                            curr.taskPrinter(index);
                            System.out.println(divider);
                        } else {
                            throw new TobiasException("    Invalid number provided! Please give a valid index from 1 to " + size +" !!");
                        }
                    } catch (TobiasException e) {
                        e.printMessage();
                    }
                }
                else if (command.startsWith("unmark")) {
                    try {
                        int size = tasks.size();

                        if (command.equals("unmark")) {
                            if (size == 0) {
                                throw new TobiasException("    Your list is empty at the moment, add some todos/events/deadlines to UNMARK them!");
                            } else {
                                throw new TobiasException("    No index provided! Please give a valid index from 1 to " + size +" !!");
                            }
                        }

                        int index = Integer.parseInt(command.substring(7)) - 1;

                        if (index >= 0 && index < tasks.size()) {
                            Task curr = tasks.get(index);
                            curr.markAsUndone();

                            System.out.println(divider);
                            System.out.println("    OK, I've marked this task as not done : ");
                            curr.taskPrinter(index);
                            System.out.println(divider);
                        } else {
                            throw new TobiasException("    Invalid number provided! Please give a valid index from 1 to " + size +" !!");
                        }
                    } catch (TobiasException e) {
                        e.printMessage();
                    }
                }
                else if (command.startsWith("delete")) {
                    try {
                        int size = tasks.size();

                        if (command.equals("delete")) {
                            if (size == 0) {
                                throw new TobiasException("    Your list is empty at the moment, add some todos/events/deadlines to DELETE them!");
                            } else {
                                throw new TobiasException("    No index provided! Please give a valid index from 1 to " + size +" !!");
                            }
                        }

                        int index = Integer.parseInt(command.substring(7)) - 1;
                        if (index >= 0 && index < tasks.size()) {
                            Task curr = tasks.get(index);
                            tasks.remove(index);

                            System.out.println(divider);
                            System.out.println("    Noted, I've removed this task: ");
                            curr.taskPrinter();
                            System.out.println(divider);
                        } else {
                            throw new TobiasException("    Invalid number provided! Please give a valid index from 1 to " + size +" !!");
                        }
                    }   catch (TobiasException e) {
                        e.printMessage();
                    }
                }
                else if (command.startsWith("todo")) {
                    try {
                        if (command.equals("todo")) {
                            throw new TobiasException("    Hey, please enter a description !");
                        }

                        String blank = command.substring(4,5);

                        if (!blank.isBlank()) {
                            throw new TobiasException("    Kindly type your task a space after todo!");
                        }

                        String description = command.substring(5);

                        Task newTask = new ToDo(description);
                        tasks.add(newTask);
                        addedTaskPrinter(newTask, tasks.size(), divider);

                    } catch(TobiasException e) {
                        e.printMessage();
                    }
                }
                else if (command.startsWith("deadline")) {
                    try {
                        if (command.equals("deadline")) {
                            throw new TobiasException("    Hey, please enter a description !");
                        }

                        String blank = command.substring(8,9);

                        if (!blank.isBlank()) {
                            throw new TobiasException("    Kindly type your task a space after deadline!");
                        }

                        int byIndex = command.indexOf("/by");

                        if (byIndex == -1) {
                            throw new TobiasException("    Hey, please use '/by' to specify the deadline!!");
                        }

                        String description = command.substring(9, byIndex);

                        int deadlineIndex = byIndex+4;

                        if (deadlineIndex > command.length()) {
                            throw new TobiasException("     Hey, please enter a date & time in this format : dd-MM-yyyy HHmm !");
                        }

                        String deadline = command.substring(byIndex + 4);

                        LocalDateTime dd = dateFromString(deadline);

                        if (deadline.isEmpty() || deadline.isBlank()) {
                            throw new TobiasException("     Hey, please enter a date & time in this format : dd-MM-yyyy HHmm !");
                        }

                        Task newTask = new Deadline(description, dd);
                        tasks.add(newTask);
                        addedTaskPrinter(newTask, tasks.size(), divider);

                    } catch (TobiasException e) {
                        e.printMessage();
                    }

                }
                else if (command.startsWith("event")) {
                    try {

                        if (command.equals("event")) {
                            throw new TobiasException("     Hey, please enter a description !");
                        }

                        String blank = command.substring(5,6);

                        if (!blank.isBlank()) {
                            throw new TobiasException("    Kindly type your task a space after event!");
                        }

                        int fromIndex = command.indexOf("/from");
                        int toIndex = command.indexOf("/to");

                        if (fromIndex == -1 && toIndex == -1) {
                            throw new TobiasException("     Hey, please enter a /from and /to date/day/time!!");
                        }

                        if (fromIndex == -1) {
                            throw new TobiasException("     Hey, please enter a /from date/day/time!!");
                        }

                        if (toIndex == -1) {
                            throw new TobiasException("     Hey, please enter a /to date/day/time!!");
                        }

                        if (fromIndex > toIndex && toIndex > 0) {
                            throw new TobiasException("     Hey, you have to enter /from before /to !!");
                        }

                        String from = command.substring(fromIndex + 6, toIndex);

                        if (from.isEmpty() || from.isBlank()) {
                            throw new TobiasException("     Hey, please enter a /from date/day/time!!");
                        }

                        if ((toIndex + 4) > command.length()) {
                            throw new TobiasException("     Hey, please enter a /to date/day/time!!");
                        }

                        String to = command.substring(toIndex + 4);

                        if (to.isEmpty() || to.isBlank()) {
                            throw new TobiasException("     Hey, please enter a /to date/day/time!!");
                        }

                        String description = command.substring(6, fromIndex);

                        LocalDateTime f = dateFromString(from);
                        LocalDateTime t = dateFromString(to);

                        Task newTask = new Event(description, f, t);
                        tasks.add(newTask);
                        addedTaskPrinter(newTask, tasks.size(), divider);

                    } catch (TobiasException e) {
                        e.printMessage();
                    }

                }
                else {
                    throw new TobiasException("    You have said something I cannot yet understand !!!\n" +
                            "    You can ask me simple stuff like : 'bye', 'list', 'mark', 'unmark','delete'\n" +
                            "    You can ask me to create these   : 'todo', 'deadline', 'event'");
                }
            } catch (TobiasException e) {
                e.printMessage();
            }
        }

        scanner.close();


    }

    public static void introPrinter(String botName, String divider) {
        System.out.println(divider);
        System.out.println("   Hello there! I'm " + botName);
        System.out.println("   What can I do for you today ?");
        System.out.println(divider);
    }

    public static void createLocalStorage() {
        try {
            File file = new File("data/tobias.txt");

            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            if (file.createNewFile()) {
                System.out.println("   File created successfully: " + file.getAbsolutePath());
            } else {
                System.out.println("   File alr exists: " + file.getAbsolutePath());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String botName = "TOBIAS";
        String divider = "  ---------------------------------------------------------------------------------------";
        createLocalStorage();
        List<Task> tasks = localToCurrent();
        System.out.println("   Data loaded!");
        introPrinter(botName, divider);
        commandHandler(divider, tasks);
    }
}
