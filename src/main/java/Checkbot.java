import java.util.Scanner;

public class Checkbot {
    public static void main(String[] args) {
        String txt = "  ____________________________________________________________\n"
                + "  Hello, I'm Checkbot, your personal assistant.\n"
                + "  How can I help you?\n";
        System.out.println(txt);
        String input = "";
        while (!input.equals("bye")) {
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            String toPrint = input;
            if (input.equals("bye")) {
                toPrint = "Goodbye!";
            }
            txt = "  ____________________________________________________________\n"
                    + "  " + toPrint + "\n"
                    + "  ____________________________________________________________\n";
            System.out.println(txt);
        }
    }
}
