import java.util.*;

public class Duke {
    public static void main(String[] args) {
        List<String> activity = new ArrayList<>();
        List<String> completion = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Dad\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        while(true) {
            String input = scanner.next();

            if(Objects.equals(input, "list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    List: \n");
                for(int i = 0; i < activity.size(); i++) {
                    System.out.format("    %d. [%s]%s%n", i + 1,completion.get(i), activity.get(i));
                }
                System.out.println("    ____________________________________________________________");
            } else if (Objects.equals(input, "bye")) {
                break;
//            } else if(Objects.equals(input, "mark")) {
//                System.out.println("    ____________________________________________________________");
//                System.out.format("    Which activity you wish to %s: ", input);
//                String key = scanner.next();
//                completion.set(activity.indexOf(key), "√");
//                System.out.println("    ____________________________________________________________");
//                System.out.format("    %sed: %s%n", input, key);
//                System.out.println("    ____________________________________________________________");
//            } else if (Objects.equals(input, "unmark")) {
//                System.out.println("    ____________________________________________________________");
//                System.out.format("    Which activity you wish to %s: ", input);
//                String key = scanner.next();
//                completion.set(activity.indexOf(key), "√");
//                System.out.println("    ____________________________________________________________");
//                System.out.format("    %sed: %s%n", input, key);
//                System.out.println("    ____________________________________________________________");
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("    added: " + input);
                System.out.println("    ____________________________________________________________");
                activity.add(input);
                completion.add("X");
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Later");
        System.out.println("    ____________________________________________________________");
    }
}
