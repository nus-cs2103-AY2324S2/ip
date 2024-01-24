import java.util.Scanner;
public class ChatbotFunctionality {
        private final Scanner scanner;

        public ChatbotFunctionality() {
            // initialize a scanner
            scanner = new Scanner(System.in);
        }

        public void startConversation() {
            // Method containing the conversation logic
            String input;
            while (true){
                input = scanner.nextLine();
                System.out.println(input);
                if (input.equalsIgnoreCase("bye")){
                    System.out.println("____________________________________________________________\n" +
                            "Bye. Hope to see you again soon!\n" +
                            "____________________________________________________________\n");
                    break;
                }
            }
            scanner.close();
        }
}
