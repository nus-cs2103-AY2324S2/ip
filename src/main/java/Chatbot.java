import java.util.Scanner;

public class Chatbot {
    private Scanner scanner = new Scanner(System.in);
    private String currentInput;
    private String[] list;
    private int numOfItems;


    public Chatbot() {
        this.currentInput = "";
        this.list = new String[100];
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
        } else {
            addToList();
            echo();
        }
    }

    private void outputList() {
        printHorizontalLine();
        for (int i = 0; i < this.numOfItems; i++) {
            System.out.println((i+1) + ". " + this.list[i]);
        }
        printHorizontalLine();
    }

    private void addToList() {
        this.list[this.numOfItems] = this.currentInput;
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
