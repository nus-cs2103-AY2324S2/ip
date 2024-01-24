import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        Scanner user = new Scanner(System.in);
        System.out.println("Hello! I'm EUEU!!");
        System.out.println("how was your day??");
        String echo = user.nextLine();
        ArrayList<String> arr = new ArrayList<String>();
        while (!echo.equals("bye")) {
                if (echo.equals("list")) {
                    for (int i = 0; i < arr.size(); i++) {
                        int j = i + 1;
                        System.out.println("    " + j + ". " + arr.get(i));
                    }

                } else {
                    System.out.println("    added: " + echo);
                    arr.add(echo);
                }
            echo = user.nextLine();
        }

        System.out.println("    byeee ttyl ok!");

    }
}
