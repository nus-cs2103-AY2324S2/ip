import java.util.Scanner;

public class Luke {

    //Logo created using https://patorjk.com/software/taag/#p=display&f=Varsity&t=Luke
    public static String logo = "  _____             __             \n"
            + " |_   _|           [  |  _         \n"
            + "   | |     __   _   | | / ] .---.  \n"
            + "   | |   _[  | | |  | '' < / /__\\\\ \n"
            + "  _| |__/ || \\_/ |, | |`\\ \\| \\__., \n"
            + " |________|'.__.'_/[__|  \\_]'.__.' ";

    public static void greet() {
        System.out.println("Hello! I'm\n" + logo + "\n");
    }

    public static void bye() {
        System.out.println("Don't be ridiculous!\n" +
                "It's... it's not like I want to see you again or anything!\n");
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (true) {
            //echo mode
            String cmd = sc.nextLine();
            System.out.println(">>> " + cmd);
            if (cmd.equals("bye")) {
                bye();
                sc.close();
                break;
            }
        }
    }
}
