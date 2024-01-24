import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm Tom \nWhat can I do for you?\n");
        System.out.println(line);
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true){
            input = scanner.nextLine();
            if ("bye".equals(input)){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(input+"\n");

        }



    }
}
