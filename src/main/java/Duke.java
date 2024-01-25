import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String horizontalLine = "____________________________________________________________\n";
        String greet = horizontalLine
                + "Greetings, mortal! I am Alastor, the Radio Demon at your service.\n"
                + "What desires or inquiries do you bring to my infernal realm?\n";
        String exit = horizontalLine
                + "Farewell, fleeting soul! 'Til our paths entwine once more.\n"
                + horizontalLine;
        System.out.println(greet);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(horizontalLine + input +"\n" + horizontalLine);
        }
        System.out.println(exit);
    }
}
