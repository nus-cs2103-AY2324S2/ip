package ghbot;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parser class.
 * It deals with making sense of the user command.
 */
public class Parser {
    private static final DateTimeFormatter inTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter outTimeFormat = DateTimeFormatter.ofPattern("dd MMM yyyy hhmma");

    /**
     * Prints corresponding string for different commands.
     * @param input User input.
     * @param lst List of tasks.
     * @param storage Tasks from saved file.
     * @throws IOException Throws an IOException when there is an issue updating the file.
     */
    public static void parse(String[] input, TaskList lst, Storage storage) throws IOException {
        String instr = input[0];

        if (instr.equalsIgnoreCase(Instruction.BYE.toString())) {
            System.out.println("Bye. Hope to see you again soon!");
            System.exit(0);

        } else if (instr.equalsIgnoreCase(Instruction.LIST.toString())) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < lst.taskSize(); i++) {
                System.out.println(i + 1 + "." + lst.getTask(i));
            }

        } else if (instr.equalsIgnoreCase(Instruction.MARK.toString())) {
            int lstNo = Integer.parseInt(input[1]);
            for (int i = 0; i < lst.taskSize(); i++) {
                if (i + 1 == lstNo) {
                    lst.getTask(i).isCompleted();
                    System.out.println("Nice! I've marked this task as done:\n" + lst.getTask(i));
                }
            }

        } else if (instr.equalsIgnoreCase(Instruction.UNMARK.toString())) {
            int lstNo = Integer.parseInt(input[1]);
            for (int i = 0; i < lst.taskSize(); i++) {
                if (i + 1 == lstNo) {
                    lst.getTask(i).isNotCompleted();
                    System.out.println("OK, I've marked this task as not done yet:\n" + lst.getTask(i));
                }
            }

        } else if (instr.equalsIgnoreCase(Instruction.TODO.toString())) {
            Todo todo = new Todo(input[1]);
            lst.addTask(todo);
            System.out.println("Got it. I've added this task:\n" + todo);
            System.out.println("Now you have " + lst.taskSize() + " tasks in the list.");

        } else if (instr.equalsIgnoreCase(Instruction.DEADLINE.toString())) {
            String[] subStr = input[1].split("/by");
            LocalDateTime inputTime = LocalDateTime.parse(subStr[1].trim(), inTimeFormat);
            String formattedTime = inputTime.format(outTimeFormat);

            Deadline deadline = new Deadline(subStr[0], formattedTime);
            lst.addTask(deadline);
            System.out.println("Got it. I've added this task:\n" + deadline);
            System.out.println("Now you have " + lst.taskSize() + " tasks in the list.");

        } else if (instr.equalsIgnoreCase(Instruction.EVENT.toString())) {
            String[] subStr = input[1].split("/from");
            String[] subStr2 = subStr[1].split("/to");
            LocalDateTime inputFromTime = LocalDateTime.parse(subStr2[0].trim(), inTimeFormat);
            String formattedFromTime = inputFromTime.format(outTimeFormat);
            LocalDateTime inputToTime = LocalDateTime.parse(subStr2[1].trim(), inTimeFormat);
            String formattedToTime = inputToTime.format(outTimeFormat);

            Event event = new Event(subStr[0], formattedFromTime, formattedToTime);
            lst.addTask(event);
            System.out.println("Got it. I've added this task:\n" + event);
            System.out.println("Now you have " + lst.taskSize() + " tasks in the list.");

        } else if (instr.equalsIgnoreCase(Instruction.DELETE.toString())) {
            int lstNo = Integer.parseInt(input[1]);
            System.out.println("Noted. I've removed this task:\n" + lst.getTask(lstNo - 1));
            lst.deleteTask(lstNo - 1);
            System.out.println("Now you have " + lst.taskSize() + " tasks in the list.");
        }
        storage.writeDataToFile(lst.toList());
    }
}