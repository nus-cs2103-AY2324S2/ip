import Exceptions.InvalidInputException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Iris {
    private BufferedReader reader;
    private final String INDENT = "    ";
    private final String CHATDELIMITER = INDENT + "_____________________";
    private List<Task> cachedTasks;
    public Iris() {
        this.reader = new BufferedReader(
                new InputStreamReader(System.in));
        this.cachedTasks = new ArrayList<>();
    }

    public void start(){
        this.print("Hello I am Iris.\nWhat can I do for you?");
    }

    public void echo() throws IOException {
        while (true) {
            String line = this.reader.readLine();
            if (line.equals("bye")) {
                return;
            }
            this.print(line);
        }
    }

    public void add() throws IOException, InvalidInputException {
        while (true) {
            String line = this.reader.readLine();
            String[] str = line.split(" ");
            switch (str[0]) {
                case "bye": return;
                case "list":
                    this.print(this.listTasks());
                    break;
                case "mark":
                    try {
                        this.cachedTasks.get(Integer.parseInt(str[1])-1).markCompleted();
                        this.print("I've marked this task as completed:\n"
                                + this.cachedTasks.get(Integer.parseInt(str[1])-1).toString());
                    } catch (Exception e) {
                        throw new InvalidInputException("Invalid input");
                    }
                    break;
                case "unmark":
                    try {
                        this.cachedTasks.get(Integer.parseInt(str[1])-1).markUncompleted();
                        this.print("I've marked this task as uncompleted:\n"
                                + this.cachedTasks.get(Integer.parseInt(str[1])-1).toString());
                    } catch (Exception e) {
                        throw new InvalidInputException("Invalid input");
                    }
                    break;
                default:
                    this.cachedTasks.add(new Task(line));
                    this.print("Added: " + line);
            }
        }
    }

    private String listTasks() {
        StringBuilder str = new StringBuilder();
        str.append("Here are your tasks:\n");
        for (int i = 0; i < this.cachedTasks.size(); i++) {
            str.append((i+1) + ". " + this.cachedTasks.get(i).toString() + "\n");
        }
        return str.toString();
    }

    public void exit() {
        this.print("GoodBye. Hope to see you again.");
    }

    private void print(String output) {
        System.out.println(this.CHATDELIMITER);
        System.out.println(this.indent(output));
        System.out.println(this.CHATDELIMITER);
    }

    private String indent(String str) {
        String[] lines = str.split("\n");
        StringBuilder indentedString = new StringBuilder();
        for (String line : lines) {
            String indentedLine = this.INDENT + line;
            indentedString.append(indentedLine).append("\n");
        }
        return indentedString.toString();
    }
}
