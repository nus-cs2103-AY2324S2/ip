// @author Tan Qin Yong
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Greeting.print();

        Scanner sc = new Scanner(System.in);

        // initialise tasklist array
        String[] tasks = new String[100];
        int count = 0;

        while (true) {
            String echoCommand = sc.nextLine();
            if (echoCommand.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye! Till we meet again ~");
                break;
            } else if (echoCommand.equalsIgnoreCase("list")) {
                System.out.println("------------------------------------");
                for (int i = 0; i < count; i++) {
                    System.out.println(i+1 + ". " + tasks[i]);
                }
                System.out.println("------------------------------------");
            } else {
                tasks[count] =  echoCommand;
                count++;
                System.out.println("------------------------------------");
                System.out.println("Added: " + echoCommand);
                System.out.println("------------------------------------");
            }

        }


    }
}
