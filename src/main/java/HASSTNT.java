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
                input = scanner.nextLine();
                System.out.println(input);
                if (input.equalsIgnoreCase("bye")){
                    System.out.println(goodbye_message);
                    break;
                }
                if(input.equalsIgnoreCase("list")) {
                    System.out.println(l);
                }
                else{
                    l.addToList(input);
                }
            }

            scanner.close();
        }
}
