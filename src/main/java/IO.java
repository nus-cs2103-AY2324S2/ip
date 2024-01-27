import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class IO {
    private ArrayList<Task> tasks = new ArrayList<>();
    private TaskList taskList;
    private List<String> cmdList = Arrays.asList("todo", "deadline", "event", "list", "mark", "unmark", "delete", "bye");
    private Scanner sc;
    private String hLine = "________________________________________________";
    private String logo = "                            ╱|、\n" +
            "                          (˚ˎ 。7  \n" +
            "                           |、˜〵          \n" +
            "                          じしˍ,)ノ\n";
    private String greetMsg = "Hello! I'm Hatsune Miku!\n"
            + " What can I do for you?";
    private String exitMsg = "Bye. Hope to see you again soon!";

    public IO() {
        sc = new Scanner(System.in);
    }

    public void greet() {
        System.out.println(logo);
        System.out.println(hLine);
        System.out.println(greetMsg);
        System.out.println(hLine);
        this.taskList = new TaskList();
        this.tasks = this.taskList.load();
    }

    public void command() {
        try {
            String input = sc.nextLine();
            String cmd = input.split(" ")[0];
            if (!cmdList.contains(cmd)) {
                throw new InvalidCmdException("Invalid command, please try again.");
            }
            if (cmd.equals("bye")) {
                echo(exitMsg);
                this.taskList.save(this.tasks);
                System.exit(0);
            } else if (cmd.equals("list")) {
                list();
            } else if (cmd.equals("mark") || cmd.equals("unmark")) {
                mark(input);
            } else if (cmd.equals("delete")) {
                deleteTask(input);
            } else {
                addTask(input, cmd);
            }
        } catch (InvalidTaskException de) {
            System.out.println(de);
        } catch (InvalidCmdException ce) {
            System.out.println(ce);
        } finally {
            command();
        }
    }

    private void addTask(String input, String cmd) throws InvalidTaskException {
        Task t;
        String[] token = input.split("todo |deadline |event | \\/from | \\/by | \\/to ");
        if (cmd.equals("todo") && token.length == 2) {
            t = new Todo(token[1]);
        } else if (cmd.equals("deadline") && token.length == 3) {
            t = new Deadline(token[1], token[2]);
        } else if (cmd.equals("event") && token.length == 4) {
            t = new Event(token[1], token[2], token[3]);
        } else {
            throw new InvalidTaskException("Invalid task syntax for " + cmd + ".");
        }

        tasks.add(t);
        this.taskList.save(this.tasks);
        echo("Got it. I've added this task:\n  "
                + t + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }

    private void deleteTask(String input) {
        String[] token = input.split(" ");
        Task t = tasks.get(Integer.parseInt(token[1]) - 1);
        echo("Noted. I've removed this task:\n  "
                + t + "\n"
                + "Now you have " + (tasks.size() - 1) + " tasks in the list.");
        tasks.remove(t);
        this.taskList.save(this.tasks);
    }

    private void list() {
        int count = 0;
        System.out.println("Here are the tasks in your list:");
        for (Task t : tasks) {
            count++;
            System.out.println(count + ". " + t);
        }
        System.out.println(hLine);
    }

    private void mark(String input) {
        String[] token = input.split(" ");
        Task t = tasks.get(Integer.parseInt(token[1]) - 1);
        if (token[0].equals("unmark")) {
            t.markUndone();
            echo("OK, I've marked this task as not done yet:\n"
                    + t);
        } else if (token[0].equals("mark")) {
            t.markAsDone();
            echo("Nice! I've marked this task as done:\n"
                    + t);
        }
    }

    private void echo(String msg) {
        System.out.println(msg);
        System.out.println(hLine);
    }
}