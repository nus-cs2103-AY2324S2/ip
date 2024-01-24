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
                        else if (index[j] - 1 > itemList.getList().size()) {
                            System.out.println(FormatOutput.format("No such task at index " + (index[j])));
                        } else {
                            itemList.getList().get(index[j] - 1).markDone();
                            System.out.println(FormatOutput.format(itemList.getList().get(index[j] - 1).doneMessage()));
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
                        if (index[j] - 1 > itemList.getList().size()) {
                            System.out.println(FormatOutput.format("No such task at index " + (index[j])));
                        } else {
                            itemList.getList().get(index[j] - 1).markUndone();
                            System.out.println(FormatOutput.format(itemList.getList().get(index[j] - 1).undoneMessage()));
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println(FormatOutput.format(Echo.echo(command)));
                }
            } else if (splitted[0].equals("delete") && splitted.length > 1) {
                try {
                    for (int i = 1; i < splitted.length; i++) {
                        index[i - 1] = Integer.parseUnsignedInt(splitted[i]);
                    }
                    for (int j = 0; j < splitted.length-1; j++) {
                        try {
                            System.out.println(FormatOutput.format(itemList.removeItem(index[j])));
                        } catch (CustomExceptions.noSuchIndexException e) {
                            System.out.println(FormatOutput.format("No such task at index " + (index[j])));
                        } catch (CustomExceptions e) {
                            System.out.println(FormatOutput.format("Could not parse command: " + command));
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println(FormatOutput.format(Echo.echo(command)));
                }
            } else if (splitted[0].equals("todo")) {
                if (splitted.length == 1) {
                    System.out.println(FormatOutput.format("Please re-enter Todo with a valid name"));
                } else {
                    System.out.println(FormatOutput.format(itemList.addToDo(splitted)));
                }
            } else if (splitted[0].equals("deadline")) {
                if (splitted.length == 1) {
                    System.out.println(FormatOutput.format("Please re-enter Deadline with a valid name."));
                } else {
                    try {
                        System.out.println(FormatOutput.format(itemList.addDeadline(splitted)));
                    } catch (CustomExceptions.namelessTaskException e) {
                        System.out.println(FormatOutput.format("Please re-enter Deadline with a valid name"));
                    }  catch (CustomExceptions e) {
                        System.out.println(FormatOutput.format("Could not parse command: " + command));
                    }
                }
            } else if (splitted[0].equals("event")) {
                if (splitted.length == 1) {
                    System.out.println(FormatOutput.format("Please re-enter Event with a valid name"));
                } else {
                    try {
                        System.out.println(FormatOutput.format(itemList.addEvent(splitted)));
                    } catch (CustomExceptions.toBeforeFromException e) {
                        System.out.println(FormatOutput.format("Please re-enter Event /from BEFORE /to: " + command));
                    } catch (CustomExceptions.eventExceptionForFromTo e) {
                        System.out.println(FormatOutput.format("Could not parse /from and /to strings: " + command));
                    } catch (CustomExceptions.namelessTaskException e) {
                        System.out.println(FormatOutput.format("Please re-enter Event with a valid name"));
                    } catch (CustomExceptions e) {
                        System.out.println(FormatOutput.format("Could not parse command: " + command));
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
