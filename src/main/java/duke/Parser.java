package duke;

import java.util.Scanner;

/**
 * The Parser class handles parsing user input and executing corresponding actions.
 */
public class Parser {
    private Scanner sc;
    public Ui ui;
    public Storage storage;

    /**
     * Constructs a new Parser instance.
     *
     * @param store The storage object to interact with task storage.
     */
    public Parser(Storage store) {
        sc = new Scanner(System.in);
        ui = new Ui();
        storage = store;

    }

    /**
     * Parses the input string to extract the action keyword.
     * The action keyword is the first word in the input string.
     *
     * @param input A string representing the input to be parsed.
     * @return The action keyword extracted from the input string.
     */
    public String parseAction(String input) {

        String[] actionArray = input.split(" ");
        assert actionArray.length > 0 : "input cannot be empty!";
        return actionArray[0];
    }
}
//    public String parse(String input) {
//        try {
//            List<String> inputParts = Arrays.asList(input.split(" "));
//            if (inputParts.get(0).equals("deadline") || inputParts.get(0).equals("todo") ||
//                    inputParts.get(0).equals("event")) {
//                if (inputParts.size() == 1) {
//                    throw new EmptyTaskException();
//                } else if (inputParts.get(0).equals("deadline")) {
//                    int index = inputParts.indexOf("/by");
//                    String descriptor = "";
//                    String deadline = "";
//                    for (int i=1; i<index; i++) {
//                        descriptor += inputParts.get(i)+ " ";
//                    }
//                    for (int i=index+1; i<inputParts.size(); i++) {
//                        deadline += inputParts.get(i)+ " ";
//                    }
//                    deadline = deadline.trim();
//                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
//                    LocalDateTime localDateTime = LocalDateTime.parse(deadline, formatter);
//
//                    descriptor = descriptor.trim();
//                    if (descriptor.isEmpty()) {
//                        throw new EmptyTaskException();
//                    }
//                    Deadlines d = new Deadlines(descriptor, localDateTime);
//                    storage.add(d);
//                    ui.onTaskAddition(storage.addToListOutput(d));
//                } else if (inputParts.get(0).equals("event")) {
//                    int index1 = inputParts.indexOf("/from");
//                    int index2 = inputParts.indexOf("/to");
//                    String descriptor = "";
//                    String from = "";
//                    String to = "";
//                    for (int i=index1 + 1; i<index2; i++) {
//                        from += inputParts.get(i)+ " ";
//                    }
//                    for (int i=index2 + 1; i<inputParts.size(); i++) {
//                        to += inputParts.get(i)+ " ";
//                    }
//                    for (int i=1; i<index1; i++) {
//                        descriptor += inputParts.get(i)+ " ";
//                    }
//                    descriptor = descriptor.trim();
//                    if (descriptor.isEmpty()) {
//                        throw new EmptyTaskException();
//                    }
//                    from = from.trim();
//                    to = to.trim();
//                    Events e =  new Events(descriptor, from, to);
//                    storage.add(e);
//                    ui.onTaskAddition(storage.addToListOutput(e));
//                } else if (inputParts.get(0).equals("todo")) {
//                    String descriptor = "";
//                    for (int i=1; i<inputParts.size(); i++) {
//                        descriptor += inputParts.get(i) + " ";
//                    }
//                    descriptor = descriptor.trim();
//                    if (descriptor.isEmpty()) {
//                        throw new EmptyTaskException();
//                    }
//                    return descriptor;
////                    ToDos t = new ToDos(descriptor);
////                    storage.add(t);
////                    ui.onTaskAddition(storage.addToListOutput(t));
//
//                }
//
//            } else if (inputParts.get(0).equals("mark")) {
//                Task t = storage.get(Integer.parseInt(inputParts.get(1)) - 1);
//                t.markAsDone();
//                ui.onMark(t.toString());
//            } else if (inputParts.get(0).equals("unmark")) {
//                Task t = storage.get(Integer.parseInt(inputParts.get(1)) - 1);
//                t.markAsUndone();
//                ui.onUnmark(t.toString());
//            } else if (input.equals("list")) {
//                ui.onPrintList(storage.printList());
//            } else if (inputParts.get(0).equals("delete")) {
//                int index = Integer.parseInt(inputParts.get(1));
//                Task t = storage.pop(index-1);
//                ui.onTaskDeletion(t.toString(), storage.size());
//            }  else if (inputParts.get(0).equals("find")) {
//                String identifier = "";
//                for (int j=1; j<inputParts.size(); j++) {
//                    identifier += inputParts.get(j) + " ";
//                }
//                identifier = identifier.trim();
//                String output = storage.find(identifier);
//                ui.onPrintFind(output);
//            } else {
//                throw new UnknownInputException();
//            }
//        } catch (DukeExceptions d){
//
//        }

//    public void run() {
//        boolean isStopRunning = false;
//        while (!isStopRunning) {
//            try {
//                String input = sc.nextLine();
//                if (input.equals("bye")) {
//                    isStopRunning = true;
//                    break;
//                }
//                List<String> inputParts = Arrays.asList(input.split(" "));
//
//                if (inputParts.get(0).equals("deadline") || inputParts.get(0).equals("todo") ||
//                        inputParts.get(0).equals("event")) {
//                    if (inputParts.size() == 1) {
//                        throw new EmptyTaskException();
//                    } else if (inputParts.get(0).equals("deadline")) {
//                        int index = inputParts.indexOf("/by");
//                        String descriptor = "";
//                        String deadline = "";
//                        for (int i=1; i<index; i++) {
//                            descriptor += inputParts.get(i)+ " ";
//                        }
//                        for (int i=index+1; i<inputParts.size(); i++) {
//                            deadline += inputParts.get(i)+ " ";
//                        }
//                        deadline = deadline.trim();
//                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
//                        LocalDateTime localDateTime = LocalDateTime.parse(deadline, formatter);
//
//                        descriptor = descriptor.trim();
//                        if (descriptor.isEmpty()) {
//                            throw new EmptyTaskException();
//                        }
//                        Deadlines d = new Deadlines(descriptor, localDateTime);
//                        storage.add(d);
//                        ui.onTaskAddition(storage.addToListOutput(d));
//                    } else if (inputParts.get(0).equals("event")) {
//                        int index1 = inputParts.indexOf("/from");
//                        int index2 = inputParts.indexOf("/to");
//                        String descriptor = "";
//                        String from = "";
//                        String to = "";
//                        for (int i=index1 + 1; i<index2; i++) {
//                            from += inputParts.get(i)+ " ";
//                        }
//                        for (int i=index2 + 1; i<inputParts.size(); i++) {
//                            to += inputParts.get(i)+ " ";
//                        }
//                        for (int i=1; i<index1; i++) {
//                            descriptor += inputParts.get(i)+ " ";
//                        }
//                        descriptor = descriptor.trim();
//                        if (descriptor.isEmpty()) {
//                            throw new EmptyTaskException();
//                        }
//                        from = from.trim();
//                        to = to.trim();
//                        Events e =  new Events(descriptor, from, to);
//                        storage.add(e);
//                        ui.onTaskAddition(storage.addToListOutput(e));
//                    } else if (inputParts.get(0).equals("todo")) {
//                        String descriptor = "";
//                        for (int i=1; i<inputParts.size(); i++) {
//                            descriptor += inputParts.get(i) + " ";
//                        }
//                        descriptor = descriptor.trim();
//                        ToDos t = new ToDos(descriptor);
//                        storage.add(t);
//                        ui.onTaskAddition(storage.addToListOutput(t));
//                        if (descriptor.isEmpty()) {
//                            throw new EmptyTaskException();
//                        }
//                    }
//
//                } else if (inputParts.get(0).equals("mark")) {
//                    Task t = storage.get(Integer.parseInt(inputParts.get(1)) - 1);
//                    t.markAsDone();
//                    ui.onMark(t.toString());
//                } else if (inputParts.get(0).equals("unmark")) {
//                    Task t = storage.get(Integer.parseInt(inputParts.get(1)) - 1);
//                    t.markAsUndone();
//                    ui.onUnmark(t.toString());
//                } else if (input.equals("list")) {
//                    ui.onPrintList(storage.printList());
//                } else if (inputParts.get(0).equals("delete")) {
//                    int index = Integer.parseInt(inputParts.get(1));
//                    Task t = storage.pop(index-1);
//                    ui.onTaskDeletion(t.toString(), storage.size());
//                }  else if (inputParts.get(0).equals("find")) {
//                    String identifier = "";
//                    for (int j=1; j<inputParts.size(); j++) {
//                        identifier += inputParts.get(j) + " ";
//                    }
//                    identifier = identifier.trim();
//                    String output = storage.find(identifier);
//                    ui.onPrintFind(output);
//                } else {
//                    throw new UnknownInputException();
//                }
//            } catch (DukeExceptions e) {
//                System.out.println(e.output());
//            }
//        }
//    }

