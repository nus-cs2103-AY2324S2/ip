import java.util.Scanner;

public class Earl {
    public static void divider() {
        System.out.println("\t" + "_".repeat(50));
    }

    public static void respond(String... arr) {
        divider();
        for (String s : arr) {
            System.out.println("\t" + s);
        }
        divider();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // starting messages
        String logo = " ______           _ \n"
                + "\t|  ____|         | |\n"
                + "\t| |__   __ _ _ __| |\n"
                + "\t|  __| / _` | '__| |\n"
                + "\t| |___| (_| | |  | |\n"
                + "\t|______\\__,_|_|  |_|";
        respond(logo, "Hello! I'm Earl", "What can I do for you?");
        // main loop
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            respond(command);
            command = sc.nextLine();
        }
        respond("Bye. Hope to see you again soon!");
        sc.close();
    }

}
