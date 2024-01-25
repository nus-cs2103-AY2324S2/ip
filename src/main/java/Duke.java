import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        //create dependencies
        Scanner reader = new Scanner(System.in);
        ArrayList<Task> sl = new ArrayList<>();
        Duke.intro();

        //reading
        String command = reader.nextLine();
        while (!command.equals("bye")) {
            String[] commandSplit = command.split(" ");
            if (command.equals("list")) {
                for (int i = 0; i < sl.size(); i ++) {
                    System.out.println((i + 1) + "." + sl.get(i));
                }
                System.out.println("______________________________________");
            }
            else if (commandSplit[0].equals("mark")) {
                try {
                    if (commandSplit.length != 2) {
                        throw new DukeExceptionMissingDependencies(command);
                    }
                    int id = Integer.parseInt(commandSplit[1]);
                    if (id > sl.size()) {
                        throw new DukeExceptionInvalidNumbering(command, id);
                    }
                    Duke.setMark(sl, id - 1);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            else if (commandSplit[0].equals("unmark")) {
                try {
                    if (commandSplit.length != 2) {
                        throw new DukeExceptionMissingDependencies(command);
                    }
                    int id = Integer.parseInt(commandSplit[1]);
                    if (id > sl.size()) {
                        throw new DukeExceptionInvalidNumbering(command, id);
                    }
                    Duke.setUnmark(sl, id - 1);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            else {
                Duke.createTask(sl, command, commandSplit[0]);
            }
            command = reader.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        reader.close();
    }
    
    public static void intro() {
        String logo = "  ____        _     ____        _   \n"
            + " |  _ \\      | |   |  _ \\      | |  \n"
            + " | |_) | ___ | |__ | |_) | ___ | |_ \n"
            + " |  _ < / _ \\| '_ \\|  _ < / _ \\| __|\n"
            + " | |_) | (_) | |_) | |_) | (_) | |_ \n"
            + " |____/ \\___/|_.__/|____/ \\___/ \\__|\n";
        System.out.println("Hellooo! I'm \n" + logo);
        System.out.println("As I am a Chatbot, I therefore have no arms.");
        System.out.println("Knock knock, who's there? Definitely not BobBot!");
        System.out.println("Jokes aside, what can I do for you?");
        System.out.println("______________________________________");
    }

    public static void setMark(ArrayList<Task> arr, int id) {
        arr.get(id).mark();
        System.out.println("Nicely done! I've marked this task as done: ");
        System.out.println(arr.get(id));
        System.out.println("______________________________________");
    }

    public static void setUnmark(ArrayList<Task> arr, int id) {
        arr.get(id).unmark();
        System.out.println("Hey you! I've marked this task as not done, yet: ");
        System.out.println(arr.get(id));
        System.out.println("______________________________________");
    }

    public static void createTask(ArrayList<Task> arr, String command, String commandType) throws DukeException{
        Task t = null;
        try {
            if (commandType.equals("deadline")) {
                String[] newCommandSplit = command.split("/");
                String[] taskName = newCommandSplit[0].split("deadline ");
                if (newCommandSplit.length != 2) {
                    throw new DukeExceptionMissingDependencies(command);
                }
                t = new Deadlines(taskName[1], newCommandSplit[1]);
            }
            else if (commandType.equals("event")) {
                String[] newCommandSplit = command.split("/");
                String[] taskName = newCommandSplit[0].split("event ");
                if (newCommandSplit.length != 3) {
                    throw new DukeExceptionMissingDependencies(command);
                }
                t = new Events(taskName[1], newCommandSplit[1], newCommandSplit[2]);
            }
            else if (commandType.equals("todo")) {
                String[] taskName = command.split("todo ");
                if (taskName.length != 2) {
                    throw new DukeExceptionMissingDependencies(command);
                }
                t = new ToDo(taskName[1]);
            }
            else {
                throw new DukeExceptionInvalidCommand(command);
            }
            arr.add(t);
            System.out.println("Gotchu! I've added this task: ");
            System.out.println(t);
            System.out.println("You now have "+arr.size()+" tasks in the list.");
            System.out.println("______________________________________");
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}