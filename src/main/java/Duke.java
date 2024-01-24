import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(Greet.logo);
        System.out.println(FormatOutput.format(Greet.greet));
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println(FormatOutput.format(ItemList.getItemList()));
            } else {
                System.out.println(FormatOutput.format(Echo.echo(command)));
                ItemList.addToList(command);
            }
            command = sc.nextLine();
        }
        System.out.println(FormatOutput.format(Greet.bye));
    }
}
