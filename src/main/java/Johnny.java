import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Johnny {

    private static ArrayList<Task> list = new ArrayList<>();
    private static DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");

    public static void main(String[] args) {
        try {
            System.out.println("Johnny here. What do you want bro?\n");
            Johnny.start();
            Johnny.takeCommands();
            System.out.println("Bye bro. I'm going back to sleep.");
        } catch (JohnnyException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    public static void takeCommands() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String input = scanner.nextLine();
                String command = input.split(" ")[0];

                if (command.equals(Command.BYE.getInput())) {
                    break;
                } else if (command.equals(Command.LIST.getInput())) {
                    Johnny.list();
                } else if (command.equals(Command.MARK.getInput())) {
                    Johnny.mark(input);
                } else if (command.equals(Command.UNMARK.getInput())) {
                    Johnny.unmark(input);
                } else if (command.equals(Command.TODO.getInput())) {
                    Johnny.addToDo(input);
                } else if (command.equals(Command.DEADLINE.getInput())) {
                    Johnny.addDeadline(input);
                } else if (command.equals(Command.EVENT.getInput())) {
                    Johnny.addEvent(input);
                } else if (command.equals(Command.DELETE.getInput())) {
                    Johnny.delete(input);
                } else {
                    throw new JohnnyException("Your command does not make sense bro.");
                }
            } catch (JohnnyException e) {
                System.out.println(e.getMessage() + "\n");
            }
        }

        scanner.close();
    }

    public static void start() throws JohnnyException {
        try {
            File file = new File("./data/johnny.txt");
            if (!file.isFile() && file.getParentFile().mkdir()) {
                file.createNewFile();
            } else {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNext()) {
                    String input = scanner.nextLine();
                    String[] parsedInput = input.split(" \\| ");
                    Task task;

                    if (parsedInput[0].equals("T")) {
                        task = new ToDo(parsedInput[2]);
                    } else if (parsedInput[0].equals("D")) {
                        LocalDateTime by = LocalDateTime.parse(parsedInput[3], INPUT_FORMAT);
                        task = new Deadline(parsedInput[2], by);
                    } else if (parsedInput[0].equals("E")) {
                        LocalDateTime from = LocalDateTime.parse(parsedInput[3], INPUT_FORMAT);
                        LocalDateTime to = LocalDateTime.parse(parsedInput[4], INPUT_FORMAT);
                        task = new Event(parsedInput[2], from, to);
                    } else {
                        throw new JohnnyException("The file has been corrupted bro.");
                    }

                    if (parsedInput[1].equals("1")) {
                        task.mark();
                    }

                    Johnny.list.add(task);
                }

                scanner.close();
            }
        } catch (IOException e) {
            throw new JohnnyException("I can't create a new file bro: " + e.getMessage());
        }
    }

    public static void list() {
        System.out.println("Get all these done bro:");
        for (int i = 0; i < Johnny.list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
        System.out.println();
    }

    public static void mark(String command) throws JohnnyException {
        try {
            String[] arr = command.split(" ");

            if (arr.length == 1) {
                throw new JohnnyException("Which task am I supposed to mark bro?");
            } else if (arr.length > 2) {
                throw new JohnnyException("I can only mark 1 task bro.");
            }

            int index = Integer.parseInt(arr[1]) - 1;
            Task task = Johnny.list.get(index);
            task.mark();
            rewriteFile();
            System.out.println("Finally done bro.");
            System.out.println(task + "\n");
        } catch (NumberFormatException e) {
            throw new JohnnyException("Key in a number bro.");
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("This task does not exist bro.");
        }
    }

    public static void unmark(String command) throws JohnnyException {
        try {
            String[] arr = command.split(" ");

            if (arr.length == 1) {
                throw new JohnnyException("Which task am I supposed to unmark bro?");
            } else if (arr.length > 2) {
                throw new JohnnyException("I can only unmark 1 task bro.");
            }

            int index = Integer.parseInt(arr[1]) - 1;
            Task task = Johnny.list.get(index);
            task.unmark();
            rewriteFile();
            System.out.println("Why are you not done yet bro?");
            System.out.println(task + "\n");
        } catch (NumberFormatException e) {
            throw new JohnnyException("Key in a number bro.");
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("This task does not exist bro.");
        }
    }

    public static void addToDo(String command) throws JohnnyException {
        List<String> l = Arrays.asList(command.split(" "));

        if (l.size() == 1) {
            throw new JohnnyException("What is your todo bro?");
        }
        String name = String.join(" ", l.subList(1, l.size()));
        Task task = new ToDo(name);
        Johnny.list.add(task);
        appendToFile(task);
        System.out.println("Go get this done bro:");
        System.out.println(task);
        System.out.println("You still have " + Johnny.list.size() + " tasks in your list bro.\n");
    }

    public static void addDeadline(String command) throws JohnnyException {
        try {
            List<String> l = Arrays.asList(command.split(" "));

            if (l.size() == 1) {
                throw new JohnnyException("What is your deadline bro?");
            }

            int i = l.indexOf("/by");

            if (i == -1) {
                throw new JohnnyException("When is your deadline by bro?");
            }
            String name = String.join(" ", l.subList(1, i));
            String by = String.join(" ", l.subList(i + 1, l.size()));
            LocalDateTime byDate = LocalDateTime.parse(by, INPUT_FORMAT);
            Task task = new Deadline(name, byDate);
            Johnny.list.add(task);
            appendToFile(task);
            System.out.println("Go get this done bro:");
            System.out.println(task);
            System.out.println("You still have " + Johnny.list.size() + " tasks in your list bro.\n");
        } catch (DateTimeParseException e) {
            throw new JohnnyException("Date and Time should be in the format of YYYY/MM/DD HHMM bro.");
        }
    }

    public static void addEvent(String command) throws JohnnyException {
        try {
            List<String> l = Arrays.asList(command.split(" "));

            if (l.size() == 1) {
                throw new JohnnyException("What is your event bro?");
            }

            int i = l.indexOf("/from");

            if (i == -1) {
                throw new JohnnyException("When does your event start from bro?");
            }

            int j = l.indexOf("/to");

            if (j == -1) {
                throw new JohnnyException("When does your event last to bro?");
            }

            String name = String.join(" ", l.subList(1, i));
            String from = String.join(" ", l.subList(i + 1, j));
            String to = String.join(" ", l.subList(j + 1, l.size()));
            LocalDateTime fromDate = LocalDateTime.parse(from, INPUT_FORMAT);
            LocalDateTime toDate = LocalDateTime.parse(to, INPUT_FORMAT);
            Task task = new Event(name, fromDate, toDate);
            Johnny.list.add(task);
            appendToFile(task);
            System.out.println("Go get this done bro:");
            System.out.println(task);
            System.out.println("You still have " + Johnny.list.size() + " tasks in your list bro.\n");
        } catch (DateTimeParseException e) {
            throw new JohnnyException("Date and Time should be in the format of YYYY/MM/DD HHMM bro.");
        }
    }

    public static void delete(String command) throws JohnnyException {
        try {
            String[] arr = command.split(" ");

            if (arr.length == 1) {
                throw new JohnnyException("Which task am I supposed to delete bro?");
            } else if (arr.length > 2) {
                throw new JohnnyException("I can only delete 1 task bro.");
            }

            int index = Integer.parseInt(arr[1]) - 1;
            Task task = Johnny.list.remove(index);
            rewriteFile();
            System.out.println("Task removed. Why so lazy bro?");
            System.out.println(task);
            System.out.println("You still have " + Johnny.list.size() + " tasks in your list bro.\n");
        } catch (NumberFormatException e) {
            throw new JohnnyException("Key in a number bro.");
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("This task does not exist bro.");
        }
    }

    public static void rewriteFile() throws JohnnyException {
        try {
            FileWriter fw = new FileWriter("./data/johnny.txt");
            for (Task task: Johnny.list) {
                fw.write(task.addToFile());
            }
            fw.close();
        } catch (IOException e) {
            throw new JohnnyException("Cannot write to file bro: " + e.getMessage());
        }
    }

    public static void appendToFile(Task task) throws JohnnyException {
        try {
            FileWriter fw = new FileWriter("./data/johnny.txt", true);
            fw.write(task.addToFile());
            fw.close();
        } catch (IOException e) {
            throw new JohnnyException("Cannot write to file bro: " + e.getMessage());
        }
    }

}
