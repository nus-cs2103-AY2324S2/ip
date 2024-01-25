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
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<String>(100);

        System.out.println(greet);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                System.out.print(horizontalLine);
                for (String added:list)
                    System.out.println(list.indexOf(added) + ". " + added);
                System.out.print(horizontalLine);
                continue;
            }
            list.add(input);
            System.out.println(horizontalLine + "added: " + input + "\n" + horizontalLine);
        }
        System.out.println(exit);
    }
}
