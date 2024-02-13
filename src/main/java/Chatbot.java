import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Chatbot {
    private Scanner scanner = new Scanner(System.in);
    private String currentInput;
    private ArrayList<Task> tasks;


    public Chatbot() {
        this.currentInput = "";
        this.tasks = new ArrayList<Task>();
    }

    public void start() {
        try {
            this.tasks = SavedFile.load();
        } catch (IOException e) {
            LiteException.LoadFileException();
        }
        greetings();
        this.currentInput = scanner.nextLine();
        while (!this.currentInput.equals("bye")) {
            parseInput();
            this.currentInput = scanner.nextLine();
        }
        SavedFile.save(this.tasks);
        exit();
    }

    private void parseInput() {
        if (this.currentInput.equals("list")) {
            this.outputTasks();
            return;
        }

        String instruction[] = this.currentInput.split(" ", 2); // It only splits the first " "
        if (instruction[0].equals("mark")) {
            this.mark(instruction);
            return;
        }

        if (instruction[0].equals("unmark")){
            this.unmark(instruction);
            return;
        }

        if (instruction[0].equals("delete")) {
            this.delete(instruction);
            return;
        }

        if (instruction[0].equals("todo")) {
            this.addToDo(instruction);
            return;
        }

        if (instruction[0].equals("deadline")) {
            this.addDeadline(instruction);
            return;
        }

        if (instruction[0].equals("event")) {
            this.addEvent(instruction);
            return;
        }

        printHorizontalLine();
        LiteException.InvalidInput();
    }

    private void delete(String instruction[]) {
        try {
            int index = Integer.parseInt(instruction[1]) - 1;
            printHorizontalLine();
            System.out.println("Noted. I've removed this task:\n" +
                    this.tasks.get(index) + "\n" +
                    "Now you have " + (this.tasks.size() - 1) + " tasks in the tasks.");
            tasks.remove(index);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            printHorizontalLine();
            System.out.println("Please input a valid index. \n"
                    + "The correct format is delete <index>. \n"
                    + "The minimum index is 1 and the maximum index is " + this.tasks.size());
            printHorizontalLine();
        }
    }

    private void addToDo(String instruction[]) {
        try {
            String description = instruction[1];
            printHorizontalLine();
            System.out.println("Got it. I've added this task: ");
            Task todo = new Todo(description);
            this.tasks.add(todo);
            System.out.println(todo);
            System.out.println("Now you have " + this.tasks.size() + " tasks in the tasks");
            printHorizontalLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            printHorizontalLine();
            System.out.println("Please give a name for the todo task. \n +" +
                    "The correct format is todo <description>");
            printHorizontalLine();
        }
    }

    private void addDeadline(String instruction[]) {
        try {
            String splits[] = instruction[1].split("/");
            String description = splits[0];
            String due = splits[1];
            printHorizontalLine();
            System.out.println("Got it. I've added this task: ");
            Task deadline = new Deadline(description, due);
            this.tasks.add(deadline);
            System.out.println(deadline);
            System.out.println("Now you have " + this.tasks.size() + " tasks in the tasks");
            printHorizontalLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            printHorizontalLine();
            System.out.println("Invalid input. \n" +
                    "Please follow the format of: deadline <description> /by <date>");
            printHorizontalLine();
        }
    }

    private void addEvent(String[] instruction) {
        try {
            String splits[] = instruction[1].split("/");
            String description = splits[0];
            String start = splits[1];
            String end = splits[2];
            printHorizontalLine();
            System.out.println("Got it. I've added this task: ");
            Task event = new Event(description, start, end);
            this.tasks.add(event);
            System.out.println(event);
            System.out.println("Now you have " + this.tasks.size() + " tasks in the tasks");
            printHorizontalLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            printHorizontalLine();
            System.out.println("Invalid input. \n" +
                    "Please follow the format: event <description> /from <date> /to <date>");
            printHorizontalLine();
        }
    }
    private void unmark(String instruction[]) {
        try {
            int index = Integer.parseInt(instruction[1]) - 1;
            printHorizontalLine();
            System.out.println(this.tasks.get(index).unmark());
            printHorizontalLine();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            printHorizontalLine();
            System.out.println("Please input a valid index. \n"
                + "The correct format is unmark <index>. \n"
                + "The minimum index is 1 and the maximum index is " + tasks.size());
            printHorizontalLine();
        }
    }

    private void mark(String instruction[]) {
        try {
            int index = Integer.parseInt(instruction[1]) - 1;
            printHorizontalLine();
            System.out.println(this.tasks.get(index).mark());
            printHorizontalLine();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            printHorizontalLine();
            System.out.println("Please input a valid index. \n"
                    + "The correct format is mark <index>. \n"
                    + "The minimum index is 1 and the maximum index is " + tasks.size());
            printHorizontalLine();
        }
    }

    private void outputTasks() {
        printHorizontalLine();
        System.out.println("Here are the tasks in your tasks:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i+1) + ". " + this.tasks.get(i));
        }
        printHorizontalLine();
    }

    private void greetings() {
        printHorizontalLine();
        String msg = " Hello! I'm LITE\n" +
                " What can I do for you?" ;
        System.out.println(msg);
        printHorizontalLine();
    }

    private void exit() {
        printHorizontalLine();
        String msg = " Bye. Hope to see you again soon!";
        System.out.println(msg);
        printHorizontalLine();
    }

    private void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
}
