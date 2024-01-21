import java.util.Scanner;

public class Duke {

    final static String horizontalLine = "____________________________________________________________";
    final static String logoElephant =
                                        "     __\n" +
                                        " .--()°'.'\n" +
                                        "'|, . ,'\n" +
                                        " !_-(_\\";


    public static void main(String[] args) {

        hello();

        String input = "";
        Scanner reader = new Scanner(System.in);

        while ( !input.toLowerCase().equals("bye") ) {
            System.out.println(input);
            input = reader.next();
        }


        goodbye();
    }

    private static void hello() {
        System.out.println("Hello! I'm Ellie, your CS2103T chat bot!");
        System.out.println(logoElephant + "\n" + horizontalLine);
        System.out.println("What can I do for you?");
    }

    private static void goodbye() {
        System.out.println("\n Bye! Hope to see you again soon!");
        System.out.println(horizontalLine);
    }


}
