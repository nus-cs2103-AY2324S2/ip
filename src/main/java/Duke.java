import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hi, I'm Lighthouse.\nHow can I help you?\n";
        System.out.println(greeting);

        Scanner scan = new Scanner(System.in);
        boolean continueRun = true;

        while (continueRun) {
            String info = scan.nextLine();
            if (info.equals("bye")) {
                continueRun = false;
                System.out.println("Goodbye! See you next time!");
                break;
            }
            System.out.println(info);

        }






    }
}
