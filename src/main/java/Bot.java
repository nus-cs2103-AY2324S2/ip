import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

public class Bot {
    private final PrintWriter pw = new PrintWriter(System.out);
    private static final String SEPARATOR = "__________________________________________________________________\n";
    private static final String INDENTATION = "    ";
    private LinkedList<Task> list = new LinkedList<Task>();

    public Bot(){}

    public void greet() {
        this.pw.print(INDENTATION + SEPARATOR
                + INDENTATION + "Hello, Notnow here!\n"
                + INDENTATION + "What can I do for you?\n"
                + INDENTATION + SEPARATOR);
        this.pw.flush();
    }

    public void exit() {
        this.pw.print(INDENTATION + SEPARATOR
                + INDENTATION + "Bye. Hope to see u again soon!\n"
                + INDENTATION + SEPARATOR);
        this.pw.flush();
    }

    public void consume(String input) {
        String[] tokens = input.split(" ");
        String taskType = tokens[0];
        String content = String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
        switch (taskType) {
            case "mark":
                this.mark(Integer.parseInt(tokens[1]));
                break;
            case "unmark":
                this.unmark(Integer.parseInt(tokens[1]));
                break;
            case "todo":
                if (content.isEmpty()) {
                    this.echo("Error! todo need a command body of the content of the task.");
                    break;
                }
                this.add(new ToDo(content));
                break;
            case "event":
                if (content.isEmpty()) {
                    this.echo("Error! event need a command body of the content of the event, "
                            + "starting and ending time for the event");
                    break;
                }
                if (content.split("/").length != 3) {
                    this.echo(String.format("Error! 3 parts split by '/' is expected, got %d", content.split("/").length));
                    break;
                }
                this.add(new Event(String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length))));
                break;
            case "deadline":
                if (content.isEmpty()) {
                    this.echo("Error! deadline need a command body of the content of the deadline "
                            + " and the time of the deadline");
                    break;
                }
                if (content.split("/").length != 2) {
                    this.echo(String.format("Error! 2 parts split by '/' is expected, got %d", content.split("/").length));
                    break;
                }
                this.add(new Deadline(String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length))));
                break;
            case "list":
                this.list();
                break;
            default:
                this.echo("Sorry! I don't understand your words. you can try to use key words like "
                        +  "'todo', 'event', 'deadline' at the beginning of each sentences");
        }

    }

    public void echo(String input) {
        this.pw.print(INDENTATION + SEPARATOR
                + INDENTATION + input + "\n"
                + INDENTATION + SEPARATOR);
        this.pw.flush();
    }

    public void add(Task task) {
        this.echo("added: " + task);
        list.add(task);
    }

    public void list() {
        pw.print(INDENTATION + SEPARATOR);
        for (int i = 0; i < list.size(); i += 1) {
            pw.print(INDENTATION + String.format("%d.%s\n", i + 1, list.get(i)));
        }
        pw.print(INDENTATION + SEPARATOR);
        pw.flush();
    }

    public void mark(Integer index) {
        list.get(index - 1).mark();
        pw.print(INDENTATION + SEPARATOR);
        pw.print(INDENTATION +  "Nice! I've marked this task as done:\n");
        pw.print(INDENTATION + INDENTATION + list.get(index - 1) + "\n");
        pw.print(INDENTATION + SEPARATOR);
        pw.flush();
    }

    public void unmark(Integer index) {
        list.get(index - 1).unmark();
        pw.print(INDENTATION + SEPARATOR);
        pw.print(INDENTATION +  "OK, I've marked this task as not done yet:\n");
        pw.print(INDENTATION + INDENTATION + list.get(index - 1) + "\n");
        pw.print(INDENTATION + SEPARATOR);
        pw.flush();
    }
}
