import java.util.Scanner;

public class BadPingGuo {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to White Space!");
        System.out.println("Waiting for something to happen?");

        Scanner sc = new Scanner(System.in);

        while(true) {
            String response = sc.nextLine();
            if (response.equalsIgnoreCase("bye")) break;
            System.out.println(response);
        }

        System.out.println("--------------------------------");
        System.out.println("Everything is going to be okay.");
    }
}
