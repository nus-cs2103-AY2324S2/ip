import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Fireraya {

    public static void Start() {
        System.out.println("--------------------------------");
        System.out.println("Hello, my name is Fireraya!");
        System.out.println("What can I do for you today sir?");
        System.out.println("--------------------------------");
    }

    public static void Echo(String a) {
        System.out.println(a);
    }

    public static void End() {

        System.out.println("Bye, hope to see you soon!");
    }

    public static void main(String[] args) {

        Start();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                End();
                break;
            }

            Echo(input);
        }
        scanner.close();
    }
}
