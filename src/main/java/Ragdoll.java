import java.util.Scanner;

public class Ragdoll{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = " /$$$$$$$                            /$$           /$$ /$$\n"
                + "| $$__  $$                          | $$          | $$| $$\n"
                + "| $$  \\ $$  /$$$$$$   /$$$$$$   /$$$$$$$  /$$$$$$ | $$| $$\n"
                + "| $$$$$$$/ |____  $$ /$$__  $$ /$$__  $$ /$$__  $$| $$| $$\n"
                + "| $$__  $$  /$$$$$$$| $$  \\ $$| $$  | $$| $$  \\ $$| $$| $$\n"
                + "| $$  \\ $$ /$$__  $$| $$  | $$| $$  | $$| $$  | $$| $$| $$\n"
                + "| $$  | $$|  $$$$$$$|  $$$$$$$|  $$$$$$$|  $$$$$$/| $$| $$\n"
                + "|__/  |__/ \\_______/ \\____  $$ \\_______/ \\______/ |__/|__/\n"
                + "                     /$$  \\ $$                            \n"
                + "                    |  $$$$$$/                            \n"
                + "                     \\______/                             \n";
        System.out.println("Hello! I am\n" + "\n" + logo);

        System.out.println("How can I assist you today?");

        System.out.println("____________________________________________________________");

        while(true) {
            String input = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if ("bye".equalsIgnoreCase(input.trim())) {
                break;
            }

            System.out.println(input);

            System.out.println("____________________________________________________________");

        }

        scanner.close();
        System.out.println("See ya!");
        System.out.println("____________________________________________________________");
    }
}
