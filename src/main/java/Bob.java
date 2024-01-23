import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        Scanner sc = new Scanner(System.in);
        String input = "";
        String[] inputs = new String[100];
        int curr = 0;

        System.out.println(line + "Hello! I'm Bob.\nWhat can I do for you?");

        while (true) {
            System.out.println(line);
            input = sc.nextLine();
            System.out.print(line);

            if ("bye".equals(input)) {
                break;
            }

            if ("list".equals(input)) {
                for (int i = 0; i < curr; i++) {
                    System.out.println(i + 1 + ". " + inputs[i]);
                }
            } else {
                inputs[curr++] = input;
                System.out.println("added: " + input);
            }
        }

        System.out.println("Bye! You'll be back ;)\n" + line);
    }
}
