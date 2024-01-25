
import java.util.Scanner;
public class HASSTNT {
        private final Scanner scanner;
        private final ToDoList  l;

        private static final String welcome_message = "____________________________________________________________\n" +
            "Hello! I'm HASSTNT\n" +
            "What can I do for you?\n" +
            "____________________________________________________________\n";
        private static final String goodbye_message =  "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        public HASSTNT() {
            // initialize a scanner
            scanner = new Scanner(System.in);
            l = new ToDoList();
        }

        public void startConversation() {
            // Method containing the conversation logic
            System.out.println(welcome_message);
            String input;
            while (true){
                input = scanner.nextLine().toLowerCase();
                if (input.equalsIgnoreCase("bye")){
                    System.out.println(goodbye_message);
                    break;
                }
                if(input.equals("list")) {
                    l.showLists();
                }
                 else if (input.startsWith("mark ")) {
                // Extract the number after "mark " using substring
                String numberString = input.substring("mark ".length()); // Substring from index of 'k' to end
                try {
                    int index = Integer.parseInt(numberString);
                    l.showMark(index);
                } catch (NumberFormatException e) {
                    //handling invalid format of mark xxx
                    System.out.println("Invalid input. Please enter 'mark' followed by an integer");
                }
            } else if (input.startsWith("unmark ")) {
                    // Extract the number after "unmark " using substring
                    String numberString = input.substring("unmark ".length()); // Substring from index of 'k' to end
                    try {
                        int index = Integer.parseInt(numberString);
                        l.showUnmark(index);
                    } catch (NumberFormatException e) {
                        //handling invalid format of unmark xxx
                        System.out.println("Invalid input. Please enter 'unmark' followed by an interger");
                    }
                }
                else{
                    l.addToList(input);
                }
            }

            scanner.close();
        }
}
