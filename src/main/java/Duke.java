import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Greeting.print();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String echoCommand = sc.nextLine();
            if (echoCommand.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye! Till we meet again ~");
                break;
            }
            System.out.println("------------------------------------");
            System.out.println(echoCommand);
            System.out.println("------------------------------------");
        }


    }
}
