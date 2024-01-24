import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(Greet.logo);
        System.out.println(FormatOutput.format(Greet.greet));
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            String[] splitted = command.split(" ");
            int[] items = new int[10000];
            if (splitted[0].equals("mark") && splitted.length > 1) {
                try {
                    for (int i = 1; i < splitted.length; i++) {
                        items[i - 1] = Integer.parseUnsignedInt(splitted[i]);
                    }
                    for (int j = 0; j < splitted.length-1; j++) {
                        ItemList.markDone(items[j]);
                        System.out.println(ItemList.doneMessage(j));
                    }
                } catch (NumberFormatException e) {
                    System.out.println(FormatOutput.format(Echo.echo(command)));
                    ItemList.addToList(command);
                }
            } else if (splitted[0].equals("unmark") && splitted.length > 1) {
                try {
                    for (int i = 1; i < splitted.length; i++) {
                        items[i - 1] = Integer.parseUnsignedInt(splitted[i]);
                    }
                    for (int j = 0; j < splitted.length-1; j++) {
                        ItemList.markUndone(items[j]);
                        System.out.println(ItemList.undoneMessage(j));
                    }
                } catch (NumberFormatException e) {
                    System.out.println(FormatOutput.format(Echo.echo(command)));
                    ItemList.addToList(command);
                }
            } else if (command.equals("list")) {
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
