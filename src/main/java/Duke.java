import java.util.*;

public class Duke {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Dad\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        loop: {
            while(true) {
                String input = scanner.nextLine();
                switch (input) {
                    case "list":
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    List: \n        " + list);
                        System.out.println("    ____________________________________________________________");
                        break;
                    case "talk":
                        System.out.println("    ____________________________________________________________");
                        System.out.println("    talk");
                        System.out.println("    ____________________________________________________________");
                        break;
                    case "bye":
                        break loop;
                }
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Later");
        System.out.println("    ____________________________________________________________");
    }
}
