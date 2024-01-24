import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(Greet.logo);
        System.out.println(FormatOutput.format(Greet.greet));
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        ItemList itemList = new ItemList();

        while (!command.equals("bye")) {
            String[] splitted = command.split(" ");
            int[] index = new int[10000];
            if (splitted[0].equals("mark") && splitted.length > 1) {
                try {
                    for (int i = 1; i < splitted.length; i++) {
                        index[i - 1] = Integer.parseUnsignedInt(splitted[i]);
                    }
                    for (int j = 0; j < splitted.length-1; j++) {
                        if (index[j] == 0) {
                            CustomExceptions.zeroIndexException e =
                                    new CustomExceptions.zeroIndexException("Erroneously accessing 0th task ");
                            throw e;
                        }
                        else if (itemList.getList()[index[j]-1] == null) {
                            System.out.println(FormatOutput.format("No such task at index " + (index[j])));
                        } else {
                            itemList.getList()[index[j] - 1].markDone();
                            System.out.println(FormatOutput.format(itemList.getList()[index[j] - 1].doneMessage()));
                        }
                    }
                } catch (CustomExceptions.zeroIndexException e) {
                    System.out.println(FormatOutput.format("No such task at index: 0"));
                } catch (NumberFormatException e) {
                    System.out.println(FormatOutput.format(Echo.echo(command)));
                }
            } else if (splitted[0].equals("unmark") && splitted.length > 1) {
                try {
                    for (int i = 1; i < splitted.length; i++) {
                        index[i - 1] = Integer.parseUnsignedInt(splitted[i]);
                    }
                    for (int j = 0; j < splitted.length-1; j++) {
                        if (itemList.getList()[index[j]-1] == null) {
                            System.out.println(FormatOutput.format("No such task at index " + (index[j])));
                        } else {
                            itemList.getList()[index[j] - 1].markUndone();
                            System.out.println(FormatOutput.format(itemList.getList()[index[j] - 1].undoneMessage()));
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println(FormatOutput.format(Echo.echo(command)));
                }
            } else if (splitted[0].equals("todo")) {
                if (splitted.length == 1) {
                    System.out.println(FormatOutput.format("Please re-enter Todo with a name"));
                } else {
                    System.out.println(FormatOutput.format(itemList.addToDo(splitted)));
                }
            } else if (splitted[0].equals("deadline")) {
                if (splitted.length == 1) {
                    System.out.println(FormatOutput.format("Please re-enter Deadline with a name"));
                } else {
                    System.out.println(FormatOutput.format(itemList.addDeadline(splitted)));
                }
            } else if (splitted[0].equals("event")) {
                if (splitted.length == 1) {
                    System.out.println(FormatOutput.format("Please re-enter Event with a name"));
                } else {
                    try {
                        System.out.println(FormatOutput.format(itemList.addEvent(splitted)));
                    } catch (CustomExceptions.toBeforeFromException e) {
                        System.out.println(FormatOutput.format("Please re-enter Event /from BEFORE /to"));
                    }
                }
            } else if (command.equals("list")) {
                System.out.println(FormatOutput.format(itemList.toString()));
            } else {
                System.out.println(FormatOutput.format(Echo.echo(command)));
            }
            command = sc.nextLine();
        }
        System.out.println(FormatOutput.format(Greet.bye));
    }
}
