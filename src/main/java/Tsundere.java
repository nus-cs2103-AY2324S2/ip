import java.util.ArrayList;
import java.util.Scanner;

public class Tsundere {

    private static final String hi = "( ｡ •̀ ᴖ •́ ｡)";
    private static final String bye ="(\uD83D\uDCA2,,>﹏<,,) b-baka!";

    public static final Scanner sc = new Scanner(System.in);

    public static final ArrayList<Task> taskList = new ArrayList<>();

    public static void greet() {
        System.out.println("______________________________________________\n" +
                "Don't get the wrong idea!\n" +
                "I'm not doing this to help you or anything!\n" +
                hi +
                "\n______________________________________________\n");
    }

    public static void exit() {
        System.out.println("______________________________________________\n" +
                //"Don't forget about me!\n" +
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
            System.out.println("______________________________________________\n");

            Action action = new Action(str);
            try {
                action.execute();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Tell me something I understand (｡•ˇ‸ˇ•｡)");
            }

            System.out.println("______________________________________________\n");
            str = sc.nextLine();
        }
        exit();
    }
    public static void main(String[] args) {

        greet();
        addTasks();

    }
}
