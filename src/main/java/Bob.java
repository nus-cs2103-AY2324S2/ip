import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        Scanner sc = new Scanner(System.in);
        String input = "";

        System.out.println(line + "Hello! I'm Bob.\nWhat can I do for you?");

        while (!"bye".equals(input)) {
            System.out.println(line);
            input = sc.nextLine();
            System.out.println(line + input);
        }
        System.out.println("Bye! You'll be back ;)\n" + line);
    }
}
