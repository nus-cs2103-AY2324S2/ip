import java.util.Scanner;

public class Chatbot {
    private Scanner scanner = new Scanner(System.in);
    private String currentInput;
    private Task[] list;
    private int numOfItems;


    public Chatbot() {
        this.currentInput = "";
        this.list = new Task[100];
        this.numOfItems = 0;
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

    private void addToDo(String instruction[]) {
        try {
            String description = instruction[1];
            printHorizontalLine();
            System.out.println("Got it. I've added this task: ");
            Task todo = new Todo(description);
            this.list[this.numOfItems] = todo;
            System.out.println(todo);
            this.numOfItems++;
            System.out.println("Now you have " + this.numOfItems + " tasks in the list");
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
            this.list[this.numOfItems] = deadline;
            System.out.println(deadline);
            this.numOfItems++;
            System.out.println("Now you have " + this.numOfItems + " tasks in the list");
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
            this.list[this.numOfItems] = event;
            System.out.println(event);
            this.numOfItems++;
            System.out.println("Now you have " + this.numOfItems + " tasks in the list");
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
            System.out.println(this.list[index].unmark());
            printHorizontalLine();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            printHorizontalLine();
            System.out.println("Please input a valid index. \n"
                + "The correct format is unmark <index>. \n"
                + "The minimum index is 1 and the maximum index is " + list.length);
        }
    }

    private void mark(String instruction[]) {
        try {
            int index = Integer.parseInt(instruction[1]) - 1;
            printHorizontalLine();
            System.out.println(this.list[index].mark());
            printHorizontalLine();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            printHorizontalLine();
            System.out.println("Please input a valid index. \n"
                    + "The correct format is mark <index>. \n"
                    + "The minimum index is 1 and the maximum index is " + list.length);
        }
    }

    private void outputList() {
        printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.numOfItems; i++) {
            System.out.println((i+1) + ". " + this.list[i]);
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
