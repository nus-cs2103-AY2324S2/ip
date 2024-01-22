import java.util.ArrayList;
import java.util.Scanner;

public class Tsundere {

    private static final String hi = "( ｡ •̀ ᴖ •́ ｡)";
    private static final String bye ="(\uD83D\uDCA2,,>﹏<,,) b-baka!";

    private static final Scanner sc = new Scanner(System.in);

    private static final ArrayList<String> arr = new ArrayList<>();

    public static void greet() {
        System.out.println("______________________________________________\n" +
                "Don't get the wrong idea!\n" +
                "I'm not doing this to help you or anything!\n" +
                hi +
                "\n______________________________________________\n");
    }

    public static void exit() {
        System.out.println("______________________________________________\n" +
                "Don't forget about me!\n" +
                "You better come back soon!\n" +
                bye +
                "\n______________________________________________\n");
    }

    public static void echo() {
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            System.out.println("______________________________________________\n" +
                    str +
                    "\n______________________________________________\n");
            str = sc.nextLine();
        }
        exit();
    }

    public static void addTasks() {
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            if (str.equals("list")) {
                for (int i = 0; i < arr.size(); i++) {
                    System.out.println((i + 1) + ". " + arr.get(i));
                }
                System.out.println();
            } else {
                System.out.println("______________________________________________\n" +
                        "Got it: " + str +
                        "\n______________________________________________\n");
                arr.add(str);
            }
            str = sc.nextLine();
        }
        exit();
    }
    public static void main(String[] args) {

        greet();
        addTasks();

    }
}
