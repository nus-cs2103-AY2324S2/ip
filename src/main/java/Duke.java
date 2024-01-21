import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String greet = "Hi! I am your favourite friend, Lelu :) \nWhat can I do for you?\n";
        String exit = "Ok bye, you shall be missed :(";
        System.out.println(greet);

        while (true) {
            String echo = sc.nextLine();
            if (echo.equals("bye")) {
                System.out.println(exit);
                break;
            }
            System.out.println("    " + echo + "\n");
        }

    }
}
