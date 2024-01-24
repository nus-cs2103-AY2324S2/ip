import java.util.Scanner;
public class Duke {
    private static String format(String text){
        return "\t____________________________________________________________\n" +
                "\t" +
                text +
                "\n" +
                "\t____________________________________________________________";

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(format("Heyo, how u doing, Im Quacky. How can I help you?"));
        String command = scanner.nextLine();
        while(!command.equals("bye")){
            System.out.println(format(command));
            command = scanner.nextLine();
        }
        System.out.println(format("bye bye"));
        scanner.close();
    }
}
