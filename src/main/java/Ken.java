import java.util.Scanner;
public class Ken {
    public static void main(String[] args) {
        //greet
        System.out.println("Hi Barbie!");
        System.out.println("I'm Ken!");

        System.out.println("What would you like to beach today?\n");

        //create scanner
        Scanner scanner = new Scanner(System.in);
        String command;

        //response
        do {
            command = scanner.nextLine();
            echoCommand(command);
        } while (!command.equalsIgnoreCase("bye"));


        //byee
        System.out.println("Beach off!\n");

        scanner.close();
    }

    private static  void echoCommand(String command) {
        System.out.println(command);
    }
}
