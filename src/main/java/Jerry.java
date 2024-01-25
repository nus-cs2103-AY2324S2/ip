import java.util.Scanner;

public class Jerry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = "Hello! I'm Jerry.\n" + "Anything I can do for you?";
        System.out.println(message);
        String[] list =  new String[100];
        int index = 0;

        while (true) {
            String input = scanner.nextLine();

            if (input.trim().equalsIgnoreCase("bye")) {
                System.out.println("Bye!");
                break;
            }

            if (input.trim().equalsIgnoreCase("list")) {
                for (int x = 0; list[x] != null; x++){
                    System.out.println((x+1) + ". " + list[x]);
                }
                continue;
            }
            list[index] = input;
            index++;

            System.out.println("added: " + input);
        }

        scanner.close();
    }
}

