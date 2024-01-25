import java.util.Scanner;

public class Jiayou {
    private static final String LINE = "____________________________________________________________";
    public static void main(String[] args) {
        Jiayou jiayou = new Jiayou();
        Scanner sc = new Scanner(System.in);
        System.out.println(Jiayou.LINE);
        System.out.println("Hello! I'm Jiayou.");
        System.out.println("What can I do for you?");
        System.out.println(Jiayou.LINE);

        while (true) {
            String input = sc.nextLine();
            System.out.println(Jiayou.LINE);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(Jiayou.LINE);
                break;
            }
            System.out.println(input);
            System.out.println(Jiayou.LINE);
        }
        sc.close();
    }
}
