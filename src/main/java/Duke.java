import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String lineBreak = "_______________________________________________________________________________";
    private static final ArrayList<Task> lst = new ArrayList<>();
    private static final File f = new File("data/list.txt");

    public static void main(String[] args) throws IOException {
        greet();
        loadExistingList(f);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command;

        while (!(command = br.readLine()).equals("bye")) {
            try {
                Duke.checkCmd(command);
            } catch (InvalidCommandException | IncompleteCommandException ice){
                System.out.println(ice.getMessage());
            }
        }

        exit();
    }

    public static void checkCmd(String cmd) throws InvalidCommandException, IncompleteCommandException {
        String[] commandArr = cmd.split(" ");
        switch (commandArr[0]) {
            case "list":
                printLst();
                break;
            case "mark":
                if (commandArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, please input the list index for me to mark\n%s", lineBreak, lineBreak));
                }
                if (!commandArr[1].matches("\\d+")) {
                    throw new InvalidCommandException(String.format("%s\n Sorry, please input a valid list index for me to mark\n%s", lineBreak, lineBreak));
                }
                int toMark = Integer.parseInt(commandArr[1]);
                if (toMark > lst.size()) {
                    throw new InvalidCommandException(String.format("%s\n Sorry, please input a valid list index for me to mark\n%s", lineBreak, lineBreak));
                }
                markTask(toMark);
                break;
            case "unmark":
                if (commandArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, please input the list index for me to unmark\n%s", lineBreak, lineBreak));
                }
                if (!commandArr[1].matches("\\d+")) {
                    throw new InvalidCommandException(String.format("%s\n Sorry, please input a valid list index for me to unmark\n%s", lineBreak, lineBreak));
                }
                int toUnmark = Integer.parseInt(commandArr[1]);
                if (toUnmark > lst.size()) {
                    throw new InvalidCommandException(String.format("%s\n Sorry, please input a valid list index for me to unmark\n%s", lineBreak, lineBreak));
                }
                unmarkTask(toUnmark);
                break;
            case "todo":
                if (commandArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, the description of a todo cannot be empty :(\n%s", lineBreak, lineBreak));
                }
                String todo = cmd.substring(5);
                Task newTodo = new Todo(todo);
                addLst(newTodo);
                break;
            case "deadline":
                if (commandArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, the description and date of a deadline cannot be empty :(\n%s", lineBreak, lineBreak));
                }
                String[] deadlineArr = cmd.substring(9).split(" /by ");
                if (deadlineArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, the date of a deadline cannot be empty :(\n%s", lineBreak, lineBreak));
                }
                LocalDate by;
                try {
                    by = LocalDate.parse(deadlineArr[1]);
                } catch (DateTimeParseException dateTimeParseE) {
                    throw new InvalidCommandException(String.format("%s\n Sorry, please input the date of a deadline in the format YYYY-MM-DD\n%s", lineBreak, lineBreak));
                }
                Task newDeadline = new Deadline(deadlineArr[0], by);
                addLst(newDeadline);
                break;
            case "event":
                if (commandArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, the description and start and end dates of an event cannot be empty :(\n%s", lineBreak, lineBreak));
                }
                String[] eventFromArr = cmd.substring(6).split(" /from ");
                if (eventFromArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, the start and end dates of an event cannot be empty :(\n%s", lineBreak, lineBreak));
                }
                String[] eventToArr = eventFromArr[1].split(" /to ");
                if (eventToArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, the end date of an event cannot be empty :(\n%s", lineBreak, lineBreak));
                }
                LocalDate from;
                LocalDate to;
                try {
                    from = LocalDate.parse(eventToArr[0]);
                    to = LocalDate.parse(eventToArr[1]);
                } catch (DateTimeParseException dateTimeParseE) {
                    throw new InvalidCommandException(String.format("%s\n Sorry, please input the dates of an event in the format YYYY-MM-DD\n%s", lineBreak, lineBreak));
                }
                if (from.isAfter(to)) {
                    throw new InvalidCommandException(String.format("%s\n Sorry, please input the start date of an event before/on the end date\n%s", lineBreak, lineBreak));
                }
                Task newEvent = new Event(eventFromArr[0], from, to);
                addLst(newEvent);
                break;
            case "delete":
                if (commandArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, please input the list index for me to delete\n%s", lineBreak, lineBreak));
                }
                if (!commandArr[1].matches("\\d+")) {
                    throw new InvalidCommandException(String.format("%s\n Sorry, please input a valid list index for me to delete\n%s", lineBreak, lineBreak));
                }
                int toDelete = Integer.parseInt(commandArr[1]);
                if (toDelete > lst.size()) {
                    throw new InvalidCommandException(String.format("%s\n Sorry, please input a valid list index for me to delete\n%s", lineBreak, lineBreak));
                }
                deleteTask(toDelete);
                break;
            default:
                throw new InvalidCommandException(String.format("%s\n Sorry, %s is not a valid command :(\n%s", lineBreak, cmd, lineBreak));
        }
    }

    public static void greet() {
        String snowBoyAscii =
                "      *      \n" +
                        "     ***     \n" +
                        "   *******   \n" +
                        "  *  o o  *  \n" +
                        " *    >    * \n" +
                        " *  \\___/  * \n" +
                        "  *       *  \n" +
                        "   *******   \n" +
                        "     ***     \n" +
                        "      *      ";
        System.out.println(lineBreak);
        System.out.println(snowBoyAscii);
        System.out.println(" Hello! I'm SnowBoy\n" + " What can I do for you?");
        System.out.println(lineBreak);
    }

    public static void loadExistingList(File f) throws IOException{
        File parentD = f.getParentFile();
        if (parentD != null && !parentD.exists()) {
            parentD.mkdirs();
        }
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] taskArr = s.nextLine().split(" \\| ");
            switch (taskArr[0]) {
                case "T":
                    Task newTodo = new Todo(taskArr[2]);
                    if (taskArr[1].equals("1")) {
                        newTodo.markAsDone();
                    }
                    lst.add(newTodo);
                    break;
                case "D":
                    Task newDeadline = new Deadline(taskArr[2], LocalDate.parse(taskArr[3]));
                    if (taskArr[1].equals("1")) {
                        newDeadline.markAsDone();
                    }
                    lst.add(newDeadline);
                    break;
                case "E":
                    Task newEvent = new Event(taskArr[2], LocalDate.parse(taskArr[3]), LocalDate.parse(taskArr[4]));
                    if (taskArr[1].equals("1")) {
                        newEvent.markAsDone();
                    }
                    lst.add(newEvent);
                    break;
            }
        }
    }

    public static void addLst(Task newTask) {
        lst.add(newTask);
        System.out.println(lineBreak);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newTask.toString());
        System.out.println(" Now you have " + lst.size() + " tasks in the list.");
        System.out.println(lineBreak);
    }
    public static void printLst() {
        System.out.println(lineBreak);
        if (lst.isEmpty()) {
            System.out.println(" Whoops! Your list is empty :(");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < lst.size(); i++) {
                System.out.println(" " + (i + 1) + "." + lst.get(i).toString());
            }
        }
        System.out.println(lineBreak);
    }

    public static void markTask(int num) {
        lst.get(num - 1).markAsDone();
        System.out.println(lineBreak);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + lst.get(num - 1).toString());
        System.out.println(lineBreak);
    }

    public static void unmarkTask(int num) {
        lst.get(num - 1).markAsUndone();
        System.out.println(lineBreak);
        System.out.println(" OK! I've marked this task as not done yet:");
        System.out.println("   " + lst.get(num - 1).toString());
        System.out.println(lineBreak);
    }

    public static void deleteTask(int num) {
        System.out.println(lineBreak);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + lst.get(num - 1).toString());
        lst.remove(num - 1);
        System.out.println(" Now you have " + lst.size() + " tasks in the list.");
        System.out.println(lineBreak);
    }

    public static void exit() {
        try {
            FileWriter cfw = new FileWriter(f);
            for (Task t : lst) {
                cfw.write(t.toSaveFormat() + "\n");
            }
            cfw.close();
        } catch (IOException ie) {
            System.out.println("Unable to save task(s) to local file");
        }
        System.out.println(lineBreak);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }
}