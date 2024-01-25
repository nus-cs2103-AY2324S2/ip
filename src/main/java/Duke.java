import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String hello = "Hello!\n" + "What can I do for you?\n";
        System.out.println(hello);

        String bye = "See you soon!";

        String indent = "    ";

        while (true) {
            Scanner input = new Scanner(System.in);
            String str = input.nextLine();
            if (str.equals("bye") || str.equals("Bye")) {
                System.out.println(bye);
                break;
            } else if (str.equals("list")){
                for (int i = 0; i < list.size(); i++) {
                    int n = i + 1;
                    System.out.println(indent + n + ". " + list.get(i));
                }
            } else {
                list.add(str);
                System.out.println(indent + "added: " + str);
            }
        }
    }
}
