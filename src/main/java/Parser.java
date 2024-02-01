import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Parser {
    private static final DateTimeFormatter inTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter outTimeFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hhmma");
    public static void parse(String[] input, List<Task> lst, Storage storage) throws IOException {
        String instr = input[0];

        if (instr.equalsIgnoreCase(Instruction.BYE.toString())) {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);

        } else if (instr.equalsIgnoreCase(Instruction.LIST.toString())) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < lst.size(); i++) {
                System.out.println(i + 1 + "." + lst.get(i));
            }

        } else if (instr.equalsIgnoreCase(Instruction.MARK.toString())) {
            int lstNo = Integer.parseInt(input[1]);
            for (int i = 0; i < lst.size(); i++) {
                if (i + 1 == lstNo) {
                    lst.get(i).isCompleted();
                    System.out.println("Nice! I've marked this task as done:\n" + lst.get(i));
                }
            }

        } else if (instr.equalsIgnoreCase(Instruction.UNMARK.toString())) {
            int lstNo = Integer.parseInt(input[1]);
            for (int i = 0; i < lst.size(); i++) {
                if (i + 1 == lstNo) {
                    lst.get(i).isNotCompleted();
                    System.out.println("OK, I've marked this task as not done yet:\n" + lst.get(i));
                }
            }

        } else if (instr.equalsIgnoreCase(Instruction.TODO.toString())) {
            Todo todo = new Todo(input[1]);
            lst.add(todo);
            System.out.println("Got it. I've added this task:\n" + todo);
            System.out.println("Now you have " + lst.size() + " tasks in the list.");

        } else if (instr.equalsIgnoreCase(Instruction.DEADLINE.toString())) {
            String[] ss = input[1].split("/by");
            LocalDateTime inputTime = LocalDateTime.parse(ss[1].trim(), inTimeFormat);
            String formattedTime = inputTime.format(outTimeFormat);

            Deadline deadline = new Deadline(ss[0], formattedTime);
            lst.add(deadline);
            System.out.println("Got it. I've added this task:\n" + deadline);
            System.out.println("Now you have " + lst.size() + " tasks in the list.");

        } else if (instr.equalsIgnoreCase(Instruction.EVENT.toString())) {
            String[] ss = input[1].split("/from");
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
            int lstNo = Integer.parseInt(input[1]);
            System.out.println("Noted. I've removed this task:\n" + lst.get(lstNo - 1));
            lst.remove(lstNo - 1);
            System.out.println("Now you have " + lst.size() + " tasks in the list.");
        }
        storage.writeDataToFile(lst);
    }
}