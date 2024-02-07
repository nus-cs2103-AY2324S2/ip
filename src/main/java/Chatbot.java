import java.sql.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Chatbot {
    private Scanner scanner = new Scanner(System.in);
    private String currentInput;
    private ArrayList<Task> list;


    public Chatbot() {
        this.currentInput = "";
        this.list = new ArrayList<Task>();
    }

    public void start() {
        greetings();
        this.currentInput = scanner.nextLine();
        while (!this.currentInput.equals("bye")) {
            parseInput();
            this.currentInput = scanner.nextLine();
        }
        exit();
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private void parseInput() {
        if (this.currentInput.equals("list")) {
            outputList();
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
            addToDo(instruction);
            return;
        }

        if (instruction[0].equals("deadline")) {
            addDeadline(instruction);
            return;
        }

        if (instruction[0].equals("event")) {
            addEvent(instruction);
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
                    this.list.get(index) + "\n" +
                    "Now you have " + (this.list.size() - 1) + " tasks in the list.");
            list.remove(index);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            printHorizontalLine();
            System.out.println("Please input a valid index. \n"
                    + "The correct format is delete <index>. \n"
                    + "The minimum index is 1 and the maximum index is " + this.list.size());
            printHorizontalLine();
        }
    }

    private void addToDo(String instruction[]) {
        try {
            String description = instruction[1];
            printHorizontalLine();
            System.out.println("Got it. I've added this task: ");
            Task todo = new Todo(description);
            this.list.add(todo);
            System.out.println(todo);
            System.out.println("Now you have " + this.list.size() + " tasks in the list");
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
            this.list.add(deadline);
            System.out.println(deadline);
            System.out.println("Now you have " + this.list.size() + " tasks in the list");
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
            this.list.add(event);
            System.out.println(event);
            System.out.println("Now you have " + this.list.size() + " tasks in the list");
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
            System.out.println(this.list.get(index).unmark());
            printHorizontalLine();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            printHorizontalLine();
            System.out.println("Please input a valid index. \n"
                + "The correct format is unmark <index>. \n"
                + "The minimum index is 1 and the maximum index is " + list.size());
            printHorizontalLine();
        }
    }

    private void mark(String instruction[]) {
        try {
            int index = Integer.parseInt(instruction[1]) - 1;
            printHorizontalLine();
            System.out.println(this.list.get(index).mark());
            printHorizontalLine();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            printHorizontalLine();
            System.out.println("Please input a valid index. \n"
                    + "The correct format is mark <index>. \n"
                    + "The minimum index is 1 and the maximum index is " + list.size());
            printHorizontalLine();
        }
    }

    private void outputList() {
        printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println((i+1) + ". " + this.list.get(i));
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
