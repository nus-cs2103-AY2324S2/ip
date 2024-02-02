import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private Scanner sc;
    public UI ui;
    public Storage storage;

    public Parser(Storage store) {
        sc = new Scanner(System.in);
        ui = new UI();
        storage = store;
    }

    public void run() {
        boolean stop = false;
        while (!stop) {
            try {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    stop = true;
                    break;
                }
                List<String> inputParts = Arrays.asList(input.split(" "));

                if (inputParts.get(0).equals("deadline") || inputParts.get(0).equals("todo") || inputParts.get(0).equals("event")) {
                    if (inputParts.size() == 1) {
                        throw new EmptyTaskException();
                    } else if (inputParts.get(0).equals("deadline")) {
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
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
                        LocalDateTime localDateTime = LocalDateTime.parse(deadline, formatter);

                        descriptor = descriptor.trim();
                        Deadlines d = new Deadlines(descriptor, localDateTime);
                        storage.add(d);
                        ui.onTaskAddition(storage.addToListOutput(d));
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
                        ui.onTaskAddition(storage.addToListOutput(e));
                    } else if (inputParts.get(0).equals("todo")) {
                        String descriptor = "";
                        for (int i=1; i<inputParts.size(); i++) {
                            descriptor += inputParts.get(i) + " ";
                        }
                        descriptor = descriptor.trim();
                        ToDos t = new ToDos(descriptor);
                        storage.add(t);
                        ui.onTaskAddition(storage.addToListOutput(t));
                    }

                } else if (inputParts.get(0).equals("mark")) {
                    Task t = storage.get(Integer.parseInt(inputParts.get(1)) - 1);
                    t.markAsDone();
                    ui.onMark(t.toString());
                } else if (inputParts.get(0).equals("unmark")) {
                    Task t = storage.get(Integer.parseInt(inputParts.get(1)) - 1);
                    t.markAsUndone();
                    ui.onUnmark(t.toString());
                } else if (input.equals("list")) {
                    ui.onPrintList(storage.printList());
                } else if (inputParts.get(0).equals("delete")) {
                    int index = Integer.parseInt(inputParts.get(1));
                    Task t = storage.pop(index-1);
                    ui.onTaskDeletion(t.toString(), storage.size());
                } else {
                    throw new UnknownInputException();
                }
            } catch (DukeExceptions e) {
                System.out.println(e.output());
            }
        }
    }

}
