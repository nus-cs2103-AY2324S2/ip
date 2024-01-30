import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String cat = "(\\_/)\n" +
                "( •,•)\n" +
                "(\")_(\")";
        System.out.println("RAWR!\n" + cat);
        String intro = "____________________________________________________________\r\n" +
                " Hello! I'm Mickey\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\r\n";

        String outro = "____________________________________________________________\r\n" +
                " Bye. See you soon!\n" +
                "____________________________________________________________\r\n";

        System.out.println(intro);

        Scanner sc = new Scanner(System.in);
        String userInput;

        while(true) {
            userInput = sc.nextLine();
             if (userInput.equals("bye")) {
                 System.out.println(outro);
                 break;
             } else {
                 System.out.println(userInput);
             }
        }

    }
}
