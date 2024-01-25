import java.util.Scanner;

public class Gandalf {
    public static void main(String[] args) {
        System.out.println("Through fire and shadow, I'm Gandalf");
        System.out.println("What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                scanner.close();
                System.out.println("So here at last, comes the end of our fellowship. I will not say: Do not weep. For not all tears are an evil.");
                break;
            }
            System.out.println(input);
        }
    }
}
