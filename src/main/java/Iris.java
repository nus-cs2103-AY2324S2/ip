import Exceptions.InvalidInputException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

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

    public void add() throws IOException {
        while (true) {
            String line = this.reader.readLine();
            String[] str = line.split(" ");
            try {
            switch (str[0]) {
                case "bye": return;
                case "list":
                    this.print(this.listTasks());
                    break;
                case "mark":
                        this.cachedTasks.get(Integer.parseInt(str[1])-1).markCompleted();
                        this.print("I've marked this task as completed:\n"
                                + this.cachedTasks.get(Integer.parseInt(str[1])-1).toString());
                    break;
                case "unmark":
                        this.cachedTasks.get(Integer.parseInt(str[1])-1).markUncompleted();
                        this.print("I've marked this task as uncompleted:\n"
                                + this.cachedTasks.get(Integer.parseInt(str[1])-1).toString());
                    break;
                case "todo":
                    Task todo = ToDo.ToDoFactory(line);
                    this.cachedTasks.add(todo);
                    this.print("I have added this task:\n" + todo + "\n" +
                            "Now you have " + this.cachedTasks.size() + " tasks in your list.");
                    break;
                case "event":
                    Task event = Event.EventFactory(line);
                    this.cachedTasks.add(event);
                    this.print("I have added this task:\n" + event + "\n" +
                            "Now you have " + this.cachedTasks.size() + " tasks in your list.");
                    break;
                case "deadline":
                    Task deadline = Deadline.deadlineFactory(line);
                    this.cachedTasks.add(deadline);
                    this.print("I have added this task:\n" + deadline + "\n" +
                            "Now you have " + this.cachedTasks.size() + " tasks in your list.");
                    break;
                case "delete":
                    Task toBeDeleted = this.cachedTasks.get(Integer.parseInt(str[1]) - 1);
                    this.cachedTasks.remove(Integer.parseInt(str[1])-1);
                    this.print("I have removed the following task:\n" + toBeDeleted.toString()+"\nNow you have "+ this.cachedTasks.size() + " tasks in your list.");
                    break;
                default:
                    this.print("Invalid input. I don't know what you mean.");
            }} catch (InvalidInputException e) {
                this.print(e.toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                this.print("Invalid index. Index does not exist in current task list.");
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
