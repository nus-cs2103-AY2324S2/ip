import java.util.Scanner;
import java.util.function.Function;

public class CommandExecutor {
    private static Scanner sc = new Scanner(System.in);
//    private HashMap<String, Function>

    public CommandExecutor() {
    }

    public static void readCommandAndExecute() {
        boolean bye = false;

        while (!bye) {
            String inputs = sc.nextLine();
            if (inputs.equals("bye")) {
                bye = true;
            } else if (inputs.equals("list")) {
                TextList.printList();
            } else {
                TextList.addToList(inputs);
            }

        }

        System.out.println("I bid you farewell");
    }
}
