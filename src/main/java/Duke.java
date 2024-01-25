import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> storage = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //SAY HI
        String name = "____________________________________________________________ \n"
                + "Hello! I'm RATZCHAT \n"
                + "How can I help you today?";
        System.out.println(name);
        printLine();

        //RESPONSIVE
        while(true) {
            String input = scanner.nextLine();
            printLine();

            if("bye".equalsIgnoreCase(input)) {
                System.out.println("BYEBYE. Thank you for using RATZCHAT!");
                printLine();
                break;
            }

            if("list".equalsIgnoreCase(input)) {
                printList(storage);
                printLine();
            }

            if(!"list".equalsIgnoreCase(input)) {
                storage.add(input);
                System.out.println("added: " + input);
                printLine();
            }
        }
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printList(ArrayList<String> list) {
        if(!list.isEmpty()) {
            for(int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "." + list.get(i));
            }
        }
    }
}
