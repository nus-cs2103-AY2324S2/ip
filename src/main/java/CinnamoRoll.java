import java.util.*;

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

    private String add(String str) {
        Task task = new Task(str);
        this.tasks.add(task);
        return task.added();
    }

    private String list() {
        String output = "   Here are the tasks in your list:\n";
        for(int i = 0; i < this.tasks.size(); i++) {
            output += "   " + String.valueOf(i + 1) + ". " + this.tasks.get(i).toString() + "\n";
        }
        return output;
    }

    String mark(int index) {
        this.tasks.get(index).marked();
        return "   Nice! I've marked this task as done:\n" + "      " + this.tasks.get(index).toString();
    }

    String unmark(int index) {
        this.tasks.get(index).unmarked();
        return "   Nice! I've marked this task as done:\n" + "      " + this.tasks.get(index).toString();
    }

    String respond(String str) {
        if (str.length() >= 6) {

            if (str.substring(0, 4).equals("mark")) {
                int input = Integer.parseInt(str.substring(str.length() - 1));

                return (input > this.tasks.size() ? "Your input integer value is out of range!" : this.mark(input - 1));

            }
            if (str.substring(0, 6).equals("unmark")) {
                int input = Integer.parseInt(str.substring(str.length() - 1));

                return (input > this.tasks.size() ? "Your input integer value is out of range!" : this.unmark(input - 1));
            }
        }
        if (str.equals("list")) {
            return this.list();
        } else {
            return this.add(str);
        }
    }
}