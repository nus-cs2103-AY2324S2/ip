import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Jojo :)");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String[] listArr = new String[100];
        String userTxt = sc.nextLine();
        while (!userTxt.equals("bye")) {
            System.out.println(userTxt);
            userTxt = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
