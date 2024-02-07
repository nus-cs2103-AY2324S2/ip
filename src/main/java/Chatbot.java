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
        if (isNumeric(instruction[1])) {
            int index = Integer.parseInt(instruction[1]) - 1;
            if (instruction[0].equals("mark")) {
                this.mark(index);
                return;
            } else {
                this.unmark(index);
                return;
            }
        }

        printHorizontalLine();
        System.out.println("Got it. I've added this task: ");
        if (instruction[0].equals("todo")) {
            addToDo(instruction[1]);
        } else if (instruction[0].equals("deadline")) {
            String splits[] = instruction[1].split("/");
            addDeadline(splits[0], splits[1]);
        } else if (instruction[0].equals("event")) {
            String splits[] = instruction[1].split("/");
            addEvent(splits[0], splits[1], splits[2]);
        }
        numOfItems++;
        System.out.println("Now you have " + numOfItems + " tasks in the list");
        printHorizontalLine();

    }

    private void addToDo(String description) {
        Task todo = new Todo(description);
        this.list[this.numOfItems] = todo;
        System.out.println(todo);
    }

    private void addDeadline(String description, String due) {
        Task deadline = new Deadline(description, due);
        this.list[this.numOfItems] = deadline;
        System.out.println(deadline);
    }

    private void addEvent(String description, String start, String end) {
        Task event = new Event(description, start, end);
        this.list[this.numOfItems] = event;
        System.out.println(event);
    }
    private void unmark(int index) {
        printHorizontalLine();
        System.out.println(this.list[index].unmark());
        printHorizontalLine();
    }

    private void mark(int index) {
        printHorizontalLine();
        System.out.println(this.list[index].mark());
        printHorizontalLine();
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
