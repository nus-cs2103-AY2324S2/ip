import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String userInput;

        // To read in user input
        Scanner sc = new Scanner(System.in);

        String welcomeMessage = "\t ____________________________________________________________\n" +
                "\t Hello! I'm JeromeGPT \n" +
                "\t What can I do for you?\n" +
                "\t ____________________________________________________________";

        System.out.println(welcomeMessage);

        while (true) {
            // Keep reading user input until they type "bye"
            userInput = sc.nextLine();


            if (userInput.equals("bye")) {
                // Use this construct because we don't want to echo the bye message.
                break;
            } else {
                // Continuously echo user input if it is not a bye.
                System.out.println("\t ____________________________________________________________");
                System.out.println("\t " + userInput);
                System.out.println("\t ____________________________________________________________");
            }
        }


        String goodByeMessage = "\t ____________________________________________________________\n" +
                "\t Bye. Hope to see you again soon!\n" +
                "\t ____________________________________________________________\n";

        System.out.println(goodByeMessage);
    }
}
