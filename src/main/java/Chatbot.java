import java.util.Scanner;

public class Chatbot {
    private Scanner scanner = new Scanner(System.in);
    private String currentInput;

    public Chatbot() {
        this.currentInput = "";
    }

    public void start() {
        greetings();
        this.currentInput = scanner.nextLine();
        while (!this.currentInput.equals("bye")) {
            echo();
            this.currentInput = scanner.nextLine();
        }
        exit();
    }

    public void echo() {
        String msg = "____________________________________________________________\n" +
                this.currentInput + "\n" +
                "____________________________________________________________\n";
        System.out.println(msg);
    }

    public void greetings() {
        String msg = "____________________________________________________________\n" +
                " Hello! I'm LITE\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(msg);
    }

    public void exit() {
        String msg = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(msg);
    }
}
