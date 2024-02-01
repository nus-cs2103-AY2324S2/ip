import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private final DateTimeFormatter inTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public Ui() {
        System.out.println("Hello! I'm GHBot");
        System.out.println("What can I do for you?");
        this.sc = new Scanner(System.in);
    }

    /**
     * validateInput Method
     * To check whether the user have key in the input correctly.
     * @return String array containing instruction and descriptions.
     * @throws DukeException
     */
    public String[] validateInput() throws DukeException {
        String input = this.sc.nextLine();
        if (input.isEmpty()) {
            throw new DukeException("Helloooo!! please type something and follow the format below!!\n" +
                    "todo 'task description'\n" +
                    "deadline 'task description' /by 'time'\n" +
                    "event 'task description' /from 'start time' /to 'end time'");
        }
        String[] subStr = input.split(" ", 2);
        if (!subStr[0].equalsIgnoreCase(Instruction.TODO.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.DEADLINE.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.EVENT.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.LIST.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.MARK.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.UNMARK.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.BYE.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.DELETE.toString())) {
            throw new DukeException("Sorry! I don't get the instruction!\n" +
                    "please use either todo, deadline, event, list, mark, unmark, bye or delete!");
        }

        if (subStr[0].equalsIgnoreCase(Instruction.TODO.toString()) && subStr.length < 2) {
            throw new DukeException("Sorry! You need to include a description for your task!");
        } else if (subStr[0].equalsIgnoreCase(Instruction.DEADLINE.toString()) && subStr.length < 2) {
            throw new DukeException("Sorry! You need to include a description and deadline for your task!");
        } else if (subStr[0].equalsIgnoreCase(Instruction.EVENT.toString()) && subStr.length < 2) {
            throw new DukeException("Sorry! You need to include a description, start time and end time for your task!");
        } else if (subStr[0].equalsIgnoreCase(Instruction.MARK.toString()) && subStr.length < 2) {
            throw new DukeException("Sorry! You need to include a number to mark your task!");
        } else if (subStr[0].equalsIgnoreCase(Instruction.UNMARK.toString()) && subStr.length < 2) {
            throw new DukeException("Sorry! You need to include a number to unmark your task!");
        } else if (subStr[0].equalsIgnoreCase(Instruction.DELETE.toString()) && subStr.length < 2) {
            throw new DukeException("Sorry! You need to include a number to delete your task!");
        }

        if (subStr[0].equalsIgnoreCase(Instruction.DEADLINE.toString())) {
            String[] ss = subStr[1].split("/by");
            try {
                LocalDateTime.parse(ss[1].trim(), this.inTimeFormat);
            } catch (DateTimeParseException e) {
                throw new DateTimeParseException("Your date/time format is wrong.\n" +
                        "Please use yyyy-MM-dd HHmm format (e.g. 2024-01-01 1500).", ss[1].trim(), 0);
            }
        } else if (subStr[0].equalsIgnoreCase(Instruction.EVENT.toString())) {
            String[] ss = subStr[1].split("/from");
            String[] ss2 = ss[1].split("/to");
            try {
                LocalDateTime.parse(ss2[0].trim(), this.inTimeFormat);
                LocalDateTime.parse(ss2[1].trim(), this.inTimeFormat);
            } catch (DateTimeParseException e) {
                if (e.getParsedString().equals(ss2[0].trim())) {
                    throw new DateTimeParseException("Your date/time format is wrong.\n" +
                            "Please use yyyy-MM-dd HHmm format (e.g. 2024-01-01 1500).", ss2[0].trim(), 0);
                } else if (e.getParsedString().equals(ss2[1].trim())) {
                    throw new DateTimeParseException("Your date/time format is wrong.\n" +
                            "Please use yyyy-MM-dd HHmm format (e.g. 2024-01-01 1500).", ss2[1].trim(), 0);
                }
            }
        }
        return subStr;
    }
}
