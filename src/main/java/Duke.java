import java.util.Scanner;
import java.util.ArrayList;

enum Commands {
    HELLO("hello") {
        @Override
        public void execute() {
            System.out.println("Hello!");
            System.out.println("What can I do for you?");
        }

        @Override
        public void execute(ArrayList<Task> list, String description) throws DukeException {
            return;
        }
    },
    BYE("bye") {
        @Override
        public void execute() {
            System.out.println("See you soon!");
        }

        @Override
        public void execute(ArrayList<Task> list, String description) throws DukeException {
            return;
        }
    },
    MARK("mark") {
        @Override
        public void execute() {
            return;
        }
        @Override
        public void execute(ArrayList<Task> list, String description) throws DukeException {
            String[] arr = description.split(" ", 2);
            if (arr.length <= 1) {
                throw new DukeException("Please use the format: mark <index>");
            }
            try {
                int index = Integer.valueOf(arr[1]);
                Task target = list.get(index - 1);
                target.mark();
                System.out.println("Task have been marked as done.");
                System.out.println(target);
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide an integer for the index.");
            }


        }
    },
    UNMARK("unmark") {
        @Override
        public void execute() {
            return;
        }
        @Override
        public void execute(ArrayList<Task> list, String description) throws DukeException {
            String[] arr = description.split(" ", 2);
            if (arr.length <= 1) {
                throw new DukeException("Please use the format: unmark <index>");
            }
            try {
                int index = Integer.valueOf(arr[1]);
                Task target = list.get(index - 1);
                target.unmark();
                System.out.println("Task have been unmarked.");
                System.out.println(target);
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide an integer for the index.");
            }
        }

    },
    LIST("list") {
        @Override
        public void execute() {
            return;
        }
        @Override
        public void execute(ArrayList<Task> list, String description) {
            int n = list.size();
            for (int i = 0; i < n; i++) {
                int index = i + 1;
                System.out.println(index + ". " + list.get(i));
            }

            System.out.println("You have " + n + " tasks.");

        }

    },
    DELETE("delete") {
        @Override
        public void execute() {
            return;
        }
        @Override
        public void execute(ArrayList<Task> list, String description) throws DukeException {
            String[] arr = description.split(" ", 2);
            if (arr.length <= 1) {
                throw new DukeException("Please use the format: delete <index>");
            }
            try {
                int index = Integer.valueOf(arr[1]);
                Task target = list.get(index - 1);
                list.remove(index - 1);
                System.out.println("Task have been removed.");
                System.out.println(target);
                int n = list.size();
                System.out.println("You now have " + n + " tasks.");
            } catch (NumberFormatException e) {
                throw new DukeException("Please provide an integer for the index.");
            }
        }

    },
    EVENT("event") {
        @Override
        public void execute() {
            return;
        }
        @Override
        public void execute(ArrayList<Task> list, String description) throws DukeException {
            String[] arr = description.split(" ", 2);
            if (arr.length <= 1) {
                throw new DukeException("Please use the format: event <task> /from <date/time> /to <date/time>");
            }

            String[] evarr = arr[1].split("/", 3);
            if (evarr.length <= 2) {
                throw new DukeException("Please use the format: event /from <date/time> /to <date/time>");
            }
            String name = evarr[0];
            String from = evarr[1];
            String to = evarr[2];
            Task task = new Event(name, from, to);
            list.add(task);
            int n = list.size();
            System.out.println("added: " + task);
            System.out.println("You now have " + n + " tasks.");
        }

    },
    DEADLINE("deadline") {
        @Override
        public void execute() {
            return;
        }
        @Override
        public void execute(ArrayList<Task> list, String description) throws DukeException {
            String[] arr = description.split(" ", 2);
            if (arr.length <= 1) {
                throw new DukeException("Please use the format: deadline <task> /by <date/time>");
            }

            String[] dlarr = arr[1].split("/", 2);
            if (dlarr.length <= 1) {
                throw new DukeException("Please use the format: deadline <task> e/by <deadline>.");
            }
            String name = dlarr[0];
            String by = dlarr[1];
            Task task = new Deadline(name, by);
            list.add(task);
            int n = list.size();
            System.out.println("added: " + task);
            System.out.println("You now have " + n + " tasks.");
        }

    },
    TODO("todo") {
        @Override
        public void execute() {
            return;
        }

        @Override
        public void execute(ArrayList<Task> list, String description) throws DukeException {
            String[] arr = description.split(" ", 2);
            if (arr.length <= 1) {
                throw new DukeException("Please use the format: todo <task>");
            }
            Task task = new Todo(arr[1]);
            list.add(task);
            int n = list.size();
            System.out.println("added: " + task);
            System.out.println("You now have " + n + " tasks.");

        }

    },
    UNKNOWN("unknown") {

        @Override
        public void execute() throws DukeException {
            throw new DukeException("I don't know what that means");
        }
        @Override
        public void execute(ArrayList<Task> list, String description) {
            return;
        }

    };

    private final String command;
    Commands(String command) {
        this.command = command;
    }
    public String getCommand() {
        return this.command;
    }
    public abstract void execute() throws DukeException;
    public abstract void execute(ArrayList<Task> list, String description) throws DukeException;
}
public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) throws DukeException {

        Commands currentCommand = Commands.HELLO;
        currentCommand.execute();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String[] arr = str.split(" ", 2);
            String keyword = arr[0].toLowerCase();

            switch (keyword) {
                case "bye":
                    currentCommand = Commands.BYE;
                    currentCommand.execute();
                    break;
                case "mark":
                    currentCommand = Commands.MARK;
                    currentCommand.execute(list, str);
                    break;
                case "unmark":
                    currentCommand = Commands.UNMARK;
                    currentCommand.execute(list, str);
                    break;
                case "delete":
                    currentCommand = Commands.DELETE;
                    currentCommand.execute(list, str);
                    break;
                case "list":
                    currentCommand = Commands.LIST;
                    currentCommand.execute(list, str);
                    break;
                case "event":
                    currentCommand = Commands.EVENT;
                    currentCommand.execute(list, str);
                    break;
                case "deadline":
                    currentCommand = Commands.DEADLINE;
                    currentCommand.execute(list, str);
                    break;
                case "todo":
                    currentCommand = Commands.TODO;
                    currentCommand.execute(list, str);
                    break;
                default:
                    currentCommand = Commands.UNKNOWN;
                    currentCommand.execute();
                    break;

            }

        }
        scanner.close();
        return;
    }
}
