
import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String input;
        String line = "______________________________________________________\n";
        Storage storage = new Storage();
        System.out.print(line);
        System.out.println("Hello! I'm ChatterPal!");
        System.out.println("What can I do for you?");
        System.out.print(line);
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        input = sc.nextLine();
        List<String> inputParts = Arrays.asList(input.split(" "));

        while (!input.equals("bye")) {
            try {
                if (inputParts.get(0).equals("delete")) {
                    int index = Integer.parseInt(inputParts.get(1));
                    Task t = storage.pop(index-1);
                    String message = String.format("%sNoted. I've removed this task:\n" +
                            "%s\nNow you have %d tasks left.\n%s", line, t.toString(), storage.size(), line);

                    System.out.println(message);
                }
                else if (input.equals("list")) {
                System.out.println(line + storage.printList() + line);
            } else if (inputParts.get(0).equals("mark")) {
                Task t = storage.get(Integer.parseInt(inputParts.get(1)) - 1);
                t.markAsDone();
                String output = line +
                        "Great job on completing the task!\n" +
                        t.toString() + "\n" +line;
                System.out.print(output);
            } else if (inputParts.get(0).equals("unmark")) {
                Task t = storage.get(Integer.parseInt(inputParts.get(1)) - 1);
                t.markAsUndone();
                String output = line ;
                output += "OK, I've marked this task as not done yet: \n" +
                        t.toString() + "\n" + line;
                System.out.println(output);
            } else if (inputParts.get(0).equals("deadline") || inputParts.get(0).equals("todo") || inputParts.get(0).equals("event")) {
                    if (inputParts.size() == 1) {
                        throw new EmptyTaskException();
                    }
                    else if (inputParts.get(0).equals("deadline")) {
                        int index = inputParts.indexOf("/by");
                        String descriptor = "";
                        String deadline = "";
                        for (int i=1; i<index; i++) {
                            descriptor += inputParts.get(i)+ " ";
                        }
                        for (int i=index+1; i<inputParts.size(); i++) {
                            deadline += inputParts.get(i)+ " ";
                        }
                        deadline = deadline.trim();
                        descriptor = descriptor.trim();
                        Deadlines d = new Deadlines(descriptor, deadline);
                        storage.add(d);
                        System.out.println(line + storage.addToListOutput(d) + "\n" + line);
                    } else if (inputParts.get(0).equals("event")) {
                        int index1 = inputParts.indexOf("/from");
                        int index2 = inputParts.indexOf("/to");
                        String descriptor = "";
                        String from = "";
                        String to = "";
                        for (int i=index1 + 1; i<index2; i++) {
                            from += inputParts.get(i)+ " ";
                        }
                        for (int i=index2 + 1; i<inputParts.size(); i++) {
                            to += inputParts.get(i)+ " ";
                        }
                        for (int i=1; i<index1; i++) {
                            descriptor += inputParts.get(i)+ " ";
                        }
                        descriptor = descriptor.trim();
                        from = from.trim();
                        to = to.trim();

                        Events e =  new Events(descriptor, from, to);
                        storage.add(e);
                        System.out.println(line + storage.addToListOutput(e) + "\n" + line);
                    } else if (inputParts.get(0).equals("todo")) {
                        String descriptor = "";
                        for (int i=1; i<inputParts.size(); i++) {
                            descriptor += inputParts.get(i) + " ";
                        }
                        descriptor = descriptor.trim();
                        ToDos t = new ToDos(descriptor);
                        storage.add(t);
                        System.out.println(line + storage.addToListOutput(t) + "\n" + line);
                    }
                }
                else {
                    throw new UnknownInputException();
                }
            } catch(DukeExceptions e) {
                System.out.println(e.output());
            }

            input = sc.nextLine();
            inputParts = Arrays.asList(input.split(" "));
        }
        System.out.println(line);
        System.out.println("Farewell! Can't wait to catch up with you again. Until next time, " +
                "take care and stay awesome! ");
        System.out.println(line);
    }
}
