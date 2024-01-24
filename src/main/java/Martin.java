import java.util.Scanner;

public class Martin {
    static String NAME = "Martin";
    public static void main(String[] args) {
        sayGreeting();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                sayBye();
                break;
            }
            System.out.println(line);
        }
    }

    public static void sayGreeting() {
        System.out.println("Hello from " + NAME);
        System.out.println("What can I do for you?");
    }

    public static void sayBye() {
        System.out.println("Bye from " + NAME);
    }
}
