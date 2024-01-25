import java.util.*;
import java.lang.*;

class CinnamoRoll {

    private ArrayList<Task> tasks;
    private enum Users {
        MARK,
        UNMARK,
        LIST,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    CinnamoRoll() {
        this.tasks = new ArrayList<>();
    }

    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    void greet() {
        System.out.println(logo);
        System.out.println("Hello! I'm CinnamoRoll!" + "\n" + "What can I do for you? \n");
    }

    void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private String todo(String[] instruction) throws CinnamoTodoException {
        try {
            Task task = new Todos(instruction[1]);
            this.tasks.add(task);
            return task.added(this.tasks.size());
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new CinnamoTodoException();
        }
    }

    private String deadline(String[] instruction) throws CinnamoDeadlineException {
        try {
            String[] schedule = instruction[1].split("/by", 2);
            Task task = new Deadlines(schedule[0], schedule[1]);
            this.tasks.add(task);
            return task.added(this.tasks.size());
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new CinnamoDeadlineException();
        }
    }

    private String event(String[] instruction) throws CinnamoEventException {
        try {
            String[] schedule = instruction[1].split("/from | /to");
            Task task = new Events(schedule[0], schedule[1], schedule[2]);
            this.tasks.add(task);
            return task.added(this.tasks.size());
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new CinnamoEventException();
        }
    }

    private String list() {
        String output = "   Here are the tasks in your list:\n";
        for (int i = 0; i < this.tasks.size(); i++) {
            output += "      " + String.valueOf(i + 1) + "." + this.tasks.get(i).toString() + "\n";
        }
        return output;
    }

    private String delete(String[] str) throws CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]);
            Task temp = this.tasks.get(index - 1);
            this.tasks.remove(index - 1);
            return "   Noted. I've removed the following task:\n" + "      " + temp.toString() + "\n" + "   Now, you have " +
                    String.valueOf(this.tasks.size()) + " tasks in the list";
        } catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    String mark(String[] str) throws CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]) - 1;
            this.tasks.get(index).marked();
            return "   Nice! I've marked this task as done:\n" + "      " + this.tasks.get(index).toString();
        } catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    String unmark(String[] str) throws CinnamoIndexException {
        try {
            int index = Integer.parseInt(str[1]) - 1;
            this.tasks.get(index).unmarked();
            return "   Ok! I've marked this task as not done yet:\n" + "      " + this.tasks.get(index).toString();
        } catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    void respond(String str) throws CinnamoException {
        try {
            String[] arr = str.split(" ", 2);
            String instruction = arr[0].toUpperCase();
            Users user = Users.valueOf(instruction);
            switch (user) {
                case MARK:
                    System.out.println(this.mark(arr));
                    break;

                case UNMARK:
                    System.out.println(this.unmark(arr));
                    break;

                case LIST:
                    System.out.println(this.list());
                    break;

                case DELETE:
                    System.out.println(this.delete(arr));
                    break;

                case TODO:
                    System.out.println(this.todo(arr));
                    break;

                case DEADLINE:
                    System.out.println(this.deadline(arr));
                    break;

                case EVENT:
                    System.out.println(this.event(arr));
                    break;

                default:
                    throw new CinnamoException();
            }
        } catch (CinnamoException cin) {
            System.out.println(cin.toString());
        } catch (IllegalArgumentException e) {
            CinnamoException cin = new CinnamoException();
            System.out.println(cin.toString());
        }
    }
}