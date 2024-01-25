import java.util.Scanner;

public class Blawg {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String intro = "____________________________________________________________\n" +
                " Hello! I'm Blawg\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(intro);
        String outro = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(outro);
                break;
            } else {
                System.out.println("____________________________________________________________\n" +
                        userInput + "\n" +
                        " ____________________________________________________________\n");
            }
        }
        sc.close();
    }
}
