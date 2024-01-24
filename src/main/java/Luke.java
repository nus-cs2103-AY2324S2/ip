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

    private static ArrayList<Task> history = new ArrayList<>();

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
            Task task = new Task(sc.nextLine());
            if (task.getName().equals("bye")) {
                bye();
                sc.close();
                break;
            } else if (task.getName().equals("list")) {
                int num = 1;
                if (history.size() == 0) {
                    System.out.println("Looks like you have way too much free time on your hands, huh.");
                }
                for (Task s : history) {
                    if (s.isDone()) {
                        System.out.println(num + ".[X] " + s);
                    } else {
                        System.out.println(num + ".[ ] " + s);
                    }
                    num += 1;
                }
                System.out.println();
            } else if (task.getName().split(" ")[0].equals("mark")) {
                int idx = Integer.parseInt(task.getName().split(" ")[1]) - 1;
                history.get(idx).complete();
                System.out.println("Good work, I guess.");
                System.out.println((idx + 1) + ".[X] " + history.get(idx));
                System.out.println();
            } else {
                history.add(task);
                System.out.println("I helped you add task [" + task + "]. But do it yourself next time! Hmmph!"  + "\n");
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


