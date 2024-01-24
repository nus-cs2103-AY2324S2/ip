import java.util.Scanner;

public class Earl {

    public static String[] data = new String[100];
    public static int dataSize = 0;

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
            switch (command) {
                case "list":
                    if (dataSize == 0) {
                        respond("There is nothing to list.");
                    } else {
                        String[] temp = new String[dataSize];
                        for (int i = 0; i < dataSize; ++i) {
                            temp[i] = i + 1 + ". " + data[i];
                        }
                        respond(temp);
                    }
                    break;
                default:
                    data[dataSize++] = command;
                    respond("Item added to list: " + command);
            }
            command = sc.nextLine();
        }
        respond("Goodbye! See you soon.");
        sc.close();
    }

}
