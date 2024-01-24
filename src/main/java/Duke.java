import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "Hi, I'm Lighthouse.\nHow can I help you?\n";
        System.out.println(greeting);

        Scanner scan = new Scanner(System.in);
        boolean continueRun = true;
        ArrayList<String> str = new ArrayList<>();

        while (continueRun) {
            String info = scan.nextLine();
            if (info.equals("bye")) {
                continueRun = false;
                System.out.println("Goodbye! See you next time!");
                break;
            } else if (info.equals("list")) {
                for (int i = 0; i < str.size(); i++) {
                    str.set(i, (i + ". " + str.get(i)));
                }
                System.out.println(str);
            } else {
                str.add(info);
                System.out.println("added: " + info);
            }



        }






    }
}
