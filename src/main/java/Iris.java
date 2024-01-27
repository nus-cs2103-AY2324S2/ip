import Exceptions.InvalidInputException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Iris {
    private BufferedReader reader;
    private final String INDENT = "    ";
    private final String CHATDELIMITER = INDENT + "_____________________";
    private List<Task> cachedTasks;
    private final String filePath = "../cache.txt";
    public Iris() {
        this.reader = new BufferedReader(
                new InputStreamReader(System.in));
        this.cachedTasks = new ArrayList<>();
        try {
            this.load();
        } catch (IOException e) {
            this.print("Could not load cached tasks");
        }
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

    public void taskRecorder() throws IOException {
        boolean b = true;
        while (b) {
            String line = this.reader.readLine();
            b = this.add(line, true);
        }
        return;
    }

    public boolean add(String input, boolean shouldPrint) throws IOException {
        String[] str = input.split(" ");
        String outputString = null;
            try {
            switch (str[0]) {
                case "bye": return false;
                case "list":
                    outputString = this.listTasks();
                    break;
                case "mark":
                        this.cachedTasks.get(Integer.parseInt(str[1])-1).markCompleted();
                        outputString = "I've marked this task as completed:\n"
                                + this.cachedTasks.get(Integer.parseInt(str[1])-1).toString();
                        this.save();
                    break;
                case "unmark":
                        this.cachedTasks.get(Integer.parseInt(str[1])-1).markUncompleted();
                        outputString = "I've marked this task as uncompleted:\n"
                                + this.cachedTasks.get(Integer.parseInt(str[1])-1).toString();
                    this.save();
                    break;
                case "todo":
                    Task todo = ToDo.ToDoFactory(input);
                    this.cachedTasks.add(todo);
                    outputString = "I have added this task:\n" + todo + "\n" +
                            "Now you have " + this.cachedTasks.size() + " tasks in your list.";
                    this.save();
                    break;
                case "event":
                    Task event = Event.EventFactory(input);
                    this.cachedTasks.add(event);
                    outputString = "I have added this task:\n" + event + "\n" +
                            "Now you have " + this.cachedTasks.size() + " tasks in your list.";
                    this.save();
                    break;
                case "deadline":
                    Task deadline = Deadline.deadlineFactory(input);
                    this.cachedTasks.add(deadline);
                    outputString = "I have added this task:\n" + deadline + "\n" +
                            "Now you have " + this.cachedTasks.size() + " tasks in your list.";
                    this.save();
                    break;
                case "delete":
                    Task toBeDeleted = this.cachedTasks.get(Integer.parseInt(str[1]) - 1);
                    this.cachedTasks.remove(Integer.parseInt(str[1])-1);
                    outputString = "I have removed the following task:\n" + toBeDeleted.toString()+"\nNow you have "+ this.cachedTasks.size() + " tasks in your list.";
                    this.save();
                    break;
                case "clear":
                    this.clear();
                    this.save();
                    outputString = "Cleared your cache!";
                    break;
                default:
                    outputString = "Invalid input. I don't know what you mean.";
            }} catch (InvalidInputException e) {
                outputString = e.toString();
            } catch (IndexOutOfBoundsException e) {
                outputString = "Invalid index. Index does not exist in current task list.";
            }
            if (shouldPrint) {
                this.print(outputString);
            }
            return true;
    }

    private String listTasks() {
        StringBuilder str = new StringBuilder();
        str.append("Here are your tasks:\n");
        for (int i = 0; i < this.cachedTasks.size(); i++) {
            str.append((i+1) + ". " + this.cachedTasks.get(i).toString() + "\n");
        }
        return str.toString();
    }

    private void save() throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task task: this.cachedTasks) {
            fw.write(task.save()+ "\n");
        }
        fw.write("completed");
        for (int i = 0; i < this.cachedTasks.size(); i++) {
            if (this.cachedTasks.get(i).isCompleted()) {
                fw.write(" " + i);
            }
        }
        fw.close();
    }

    private void load() throws IOException {
        File f = new File(this.filePath); // create a File for the given file path
        if (f.exists() && !f.isDirectory()) {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] str = line.split(" ");
                switch (str[0]) {
                    case "completed":
                        for (int i = 1; i < str.length; i++) {
                            this.cachedTasks.get(Integer.parseInt(str[i])).markCompleted();
                        }
                        break;
                    default:
                        this.add(line, false);
                }
            }
        }
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
    private void clear() {
        this.cachedTasks.clear();
    }
}
