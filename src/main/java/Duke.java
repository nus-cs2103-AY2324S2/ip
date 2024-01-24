import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //SAY HI
        String name = "____________________________________________________________ \n"
                + "Hello! I'm RATZCHAT \n"
                + "How can I help you today?";
        System.out.println(name);
        printLine();

        //RESPONSIVE
        while(true) {
            String input = scanner.nextLine();
            printLine();

            if("bye".equalsIgnoreCase(input)) {
                System.out.println("BYEBYE. Thank you for using RATZCHAT!");
                printLine();
                break;
            }


            System.out.println(input);
            printLine();
        }

    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
