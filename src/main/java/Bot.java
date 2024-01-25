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
        switch (taskType) {
            case "mark" -> this.mark(Integer.parseInt(tokens[1]));
            case "unmark" -> this.unmark(Integer.parseInt(tokens[1]));
            case "todo" -> this.add("T", String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length)));
            case "event" -> this.add("E", String.join( " ", Arrays.copyOfRange(tokens, 1, tokens.length)));
            case "deadline" -> this.add("D", String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length)));
            case "list" -> this.list();
            default -> this.echo(String.join(" ", Arrays.copyOfRange(tokens, 0, tokens.length)));
        }

    }

    public void echo(String input) {
        this.pw.print(INDENTATION + SEPARATOR
                + INDENTATION + input + "\n"
                + INDENTATION + SEPARATOR);
        this.pw.flush();
    }

    public void add(String type, String input) {
        this.echo("added: " + input);
        list.add(new Task(type,input));
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
