import java.util.Scanner;

public class Youdon {
    public static void main(String[] args) {
        String line = "----------------------------------------------------------------";
        String message1 = "Hello! I'm Youdon!\nWhat can I do for you?\n";
        String message2 = "Bye. Hope to see you again soon!";

        System.out.println(line);
        System.out.println(message1);
        System.out.println(line);

        Scanner input = new Scanner(System.in);
        String data = input.nextLine();
        String bye = "bye";

        while(!(data.isEmpty())) {
            if (data.equals(bye)) {
                System.out.println(line);
                System.out.println(message2);
                System.out.println(line);
                break;
            }
            System.out.println(line);
            System.out.println(data + "~");
            System.out.println(line);
            data = input.nextLine();
        }
    }
}
