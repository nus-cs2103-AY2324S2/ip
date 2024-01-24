import java.util.Scanner;

public class Steven {

    public static void main(String args[]) {
        String line = "========\n";
        String bootMsg = ("This is Steven!\nHow can I advise?\n");
        System.out.println(line + bootMsg + line);

        boolean exit = false;
        while (!exit){
            Scanner userInput = new Scanner(System.in);
            String command = userInput.nextLine();
            switch (command){
                case "bye":
                    exit = true;
                    break;
                default:
                    System.out.println(command);
            }
            System.out.println(line);
        }

        System.out.println("I'll see you soon then!\n" + line);
    }
}
