import java.util.Scanner;
public class Duke {
    private Scanner scanner = new Scanner(System.in);

    private void sendSystemMessage(String... strs) {
        for (String s: strs) {
            System.out.println("\t" + s);
        }
    }
    private void greet() {
        this.sendSystemMessage(TextTemplate.LINE_BREAK, TextTemplate.GREETING, TextTemplate.LINE_BREAK);
    }

    private void bye() {
        this.sendSystemMessage(TextTemplate.EXIT, TextTemplate.LINE_BREAK);
    }

    public void run() {
        this.greet();
        while (true) {
            String input = scanner.nextLine();
            this.sendSystemMessage(TextTemplate.LINE_BREAK);
            if (input.equals("bye")) {
                break;
            } else {
                this.sendSystemMessage(input, TextTemplate.LINE_BREAK);
            }
        }
        this.bye();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
