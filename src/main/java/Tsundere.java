import java.util.ArrayList;
import java.util.Scanner;

public class Tsundere {



    public static final Scanner sc = new Scanner(System.in);

    public static final ArrayList<Task> taskList = new ArrayList<>();

    public static void greet() {
        System.out.println("_________________________________________________________________________________\n" +
                            "Don't get the wrong idea!\n" +
                            "I'm not doing this to help you or anything!\n" +
                            "_________________________________________________________________________________");
    }

    public static void exit() {
        System.out.println("_________________________________________________________________________________\n" +
                            //"Don't forget about me!\n" +
                            "You better come back soon!\n" +
                            "_________________________________________________________________________________\n");
    }

    public static void echo() {
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            System.out.println("_________________________________________________________________________________\n" +
                                str +
                                "\n_________________________________________________________________________________\n");
            str = sc.nextLine();
        }
        exit();
    }

    public static void addTasks() {
        String str = sc.nextLine();
        while (!str.equals("bye")) {
            System.out.println("_________________________________________________________________________________");

            Action action = new Action(str);
            try {
                action.execute();
            } catch (GeneralException e) {
                System.out.println(e);
            }

            System.out.println("_________________________________________________________________________________\n");
            str = sc.nextLine();
        }
        exit();
    }
    public static void main(String[] args) {

        greet();
        addTasks();

    }
}
