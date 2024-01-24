import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        Scanner user = new Scanner(System.in);
        System.out.println("Hello! I'm EUEU!!");
        System.out.println("how was your day??");
        String echo = user.nextLine();
        while (!echo.equals("bye")) {
                System.out.println("    " + echo);
                echo = user.nextLine();
        }

        System.out.println("    byeee ttyl ok!");

    }
}
