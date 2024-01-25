import java.util.*;
import java.lang.*;

enum Users {
    ADD,
    MARK,
    UNMARK
}
class CinnamoRoll {

    private ArrayList<Task> tasks;

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

    private String add(String[] instruction) throws CinnamoArrayException {
        try {
            if (instruction[0].equals("todo")) {
                Task task = new Todos(instruction[1]);
                this.tasks.add(task);
                return task.added(this.tasks.size());
            }
            if (instruction[0].equals("deadline")) {
                String[] schedule = instruction[1].split("/by", 2);
                Task task = new Deadlines(schedule[0], schedule[1]);
                this.tasks.add(task);
                return task.added(this.tasks.size());
            }
            if (instruction[0].equals("event")) {
                String[] schedule = instruction[1].split("/from | /to");
                Task task = new Events(schedule[0], schedule[1], schedule[2]);
                this.tasks.add(task);
                return task.added(this.tasks.size());
            }
            return "";
        } catch (ArrayIndexOutOfBoundsException exception) {
            throw new CinnamoArrayException();
        }
    }

    private String list() {
        String output = "   Here are the tasks in your list:\n";
        for(int i = 0; i < this.tasks.size(); i++) {
            output += "      " + String.valueOf(i + 1) + "." + this.tasks.get(i).toString() + "\n";
        }
        return output;
    }

    String mark(int index) throws CinnamoIndexException {
        try {
            this.tasks.get(index).marked();
            return "   Nice! I've marked this task as done:\n" + "      " + this.tasks.get(index).toString();
        } catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    String unmark(int index) throws CinnamoIndexException {
        try {
            this.tasks.get(index).unmarked();
            return "   Ok! I've marked this task as not done yet:\n" + "      " + this.tasks.get(index).toString();
        }
        catch (IndexOutOfBoundsException exception) {
            throw new CinnamoIndexException();
        }
    }

    void respond(String str) throws CinnamoException {
        try {
            String[] arr = str.split(" ", 2);
            String instruction = arr[0];
            if (instruction.equals("mark")) {
                int input = Integer.parseInt(arr[1]);
                System.out.println(this.mark(input - 1));
            }
            if (instruction.equals("unmark")) {
                int input = Integer.parseInt(arr[1]);
                System.out.println(this.unmark(input - 1));
            }
            if (instruction.equals("list")) {
                System.out.println(this.list());
            }
            if (instruction.equals("todo") || instruction.equals("deadline") || instruction.equals("event")){
                System.out.println(this.add(arr));
            } else {
                throw new CinnamoException();
            }
        } catch (CinnamoException cin) {
            System.out.println(cin.toString());
        }
    }
}