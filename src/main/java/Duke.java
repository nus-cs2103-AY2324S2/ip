import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;

/**
 * Duke Class.
 * This is the main class for the chatbot.
 */
public class Duke {

    public static void main(String[] args) {
        String fileName = "./data/history.txt";
        Storage storage = new Storage(fileName);
        List<Task> lst = storage.getInputFromFile();
        Ui ui = new Ui();
        DateTimeFormatter inTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter outTimeFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hhmma");

        while (true) {
            try {
                String[] subStr = ui.validateInput();
                String instr = subStr[0];

                if (instr.equalsIgnoreCase(Instruction.BYE.toString())) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                } else if (instr.equalsIgnoreCase(Instruction.LIST.toString())) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < lst.size(); i++) {
                        System.out.println(i + 1 + "." + lst.get(i));
                    }

                } else if (instr.equalsIgnoreCase(Instruction.MARK.toString())) {
                    int lstNo = Integer.parseInt(subStr[1]);
                    for (int i = 0; i < lst.size(); i++) {
                        if (i + 1 == lstNo) {
                            lst.get(i).isCompleted();
                            System.out.println("Nice! I've marked this task as done:\n" + lst.get(i));
                        }
                    }

                } else if (instr.equalsIgnoreCase(Instruction.UNMARK.toString())) {
                    int lstNo = Integer.parseInt(subStr[1]);
                    for (int i = 0; i < lst.size(); i++) {
                        if (i + 1 == lstNo) {
                            lst.get(i).isNotCompleted();
                            System.out.println("OK, I've marked this task as not done yet:\n" + lst.get(i));
                        }
                    }

                } else if (instr.equalsIgnoreCase(Instruction.TODO.toString())) {
                    Todo todo = new Todo(subStr[1]);
                    lst.add(todo);
                    System.out.println("Got it. I've added this task:\n" + todo);
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");

                } else if (instr.equalsIgnoreCase(Instruction.DEADLINE.toString())) {
                    String[] ss = subStr[1].split("/by");
                    LocalDateTime inputTime = LocalDateTime.parse(ss[1].trim(), inTimeFormat);
                    String formattedTime = inputTime.format(outTimeFormat);

                    Deadline deadline = new Deadline(ss[0], formattedTime);
                    lst.add(deadline);
                    System.out.println("Got it. I've added this task:\n" + deadline);
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");

                } else if (instr.equalsIgnoreCase(Instruction.EVENT.toString())) {
                    String[] ss = subStr[1].split("/from");
                    String[] ss2 = ss[1].split("/to");
                    LocalDateTime inputFromTime = LocalDateTime.parse(ss2[0].trim(), inTimeFormat);
                    String formattedFromTime = inputFromTime.format(outTimeFormat);
                    LocalDateTime inputToTime = LocalDateTime.parse(ss2[1].trim(), inTimeFormat);
                    String formattedToTime = inputToTime.format(outTimeFormat);

                    Event event = new Event(ss[0], formattedFromTime, formattedToTime);
                    lst.add(event);
                    System.out.println("Got it. I've added this task:\n" + event);
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");

                } else if (instr.equalsIgnoreCase(Instruction.DELETE.toString())) {
                    int lstNo = Integer.parseInt(subStr[1]);
                    System.out.println("Noted. I've removed this task:\n" + lst.get(lstNo - 1));
                    lst.remove(lstNo - 1);
                    System.out.println("Now you have " + lst.size() + " tasks in the list.");
                }
                storage.writeDataToFile(lst);
            } catch (DukeException | IOException | DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}