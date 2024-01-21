import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "_______________________________________________________\n" +
                "Hello! I'm Thames and I'll be your assistant chatbot.\n" +
                "What can I do for you today?\n" +
                "_______________________________________________________\n";

        System.out.println(greeting);

        while(true) {
            Scanner sc = new Scanner(System.in);

            String echo = sc.nextLine();
            if (echo.equals("bye")) break;
            else System.out.println("_______________________________________________________\n" +
                    echo + "\n" +
                    "_______________________________________________________\n");
        }

        String exit = "_______________________________________________________\n" +
                "Bye. Hope to see you soon!\n" +
                "_______________________________________________________\n";
        System.out.println(exit);
    }
}
