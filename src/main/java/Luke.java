import java.util.Scanner;
import java.util.ArrayList;

public class Luke {

    //Logo created using https://patorjk.com/software/taag/#p=display&f=Varsity&t=Luke
    private static String logo = "  _____             __             \n"
            + " |_   _|           [  |  _         \n"
            + "   | |     __   _   | | / ] .---.  \n"
            + "   | |   _[  | | |  | '' < / /__\\\\ \n"
            + "  _| |__/ || \\_/ |, | |`\\ \\| \\__., \n"
            + " |________|'.__.'_/[__|  \\_]'.__.' ";

    private static ArrayList<String> history = new ArrayList<>();

    private static void greet() {
        System.out.println("Hello! I'm\n" + logo + "\n");
    }

    private static void bye() {
        System.out.println("Don't be ridiculous!\n" +
                "It's... it's not like I want to see you again or anything!\n");
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (true) {
            //task mode
            String task = sc.nextLine();
            if (task.equals("bye")) {
                bye();
                sc.close();
                break;
            } else if (task.equals("list")) {
                int num = 1;
                for (String s : history) {
                    System.out.println(num + ". " + s);
                    num += 1;
                }
            } else {
                history.add(task);
                System.out.println(">>> Added: " + task);
            }
        }
//        while (true) {
//            //echo mode
//            String cmd = sc.nextLine();
//            System.out.println(">>> " + cmd);
//            if (cmd.equals("bye")) {
//                bye();
//                sc.close();
//                break;
//            }
//        }
    }
}


