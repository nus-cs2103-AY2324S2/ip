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

    private void parseInput() {
        if (this.currentInput.equals("list")) {
            outputList();
            return;
        }
        String instruction[] = this.currentInput.split(" ");
        if (instruction.length == 2) {
            int index = Integer.parseInt(instruction[1]) - 1;
            if (instruction[0].equals("mark")) {
                this.mark(index);
            } else {
                this.unmark(index);
            }
            return;
        }
        addToList();
        echo();
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
        for (int i = 0; i < this.numOfItems; i++) {
            System.out.println((i+1) + ". " + this.list[i]);
        }
        printHorizontalLine();
    }

    private void addToList() {
        this.list[this.numOfItems] = new Task(this.currentInput);
        this.numOfItems++;
    }

    private void echo() {
        printHorizontalLine();
        String msg = "added: " + this.currentInput;
        System.out.println(msg);
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
