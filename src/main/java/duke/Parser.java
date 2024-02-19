package duke;

import duke.task.TaskList;

/**
 * A class to decode instructions from the user.
 */
public class Parser {
    /**
     * Constructor to create an instance.
     */
    public Parser() {
    }

    /**
     * Decodes and run the instructions.
     *
     * @param tskLst The task manager to manage the list of tasks.
     * @param storage The file accessor to write or read from local file.
     * @param instr The actual instruction string to decode.
     *
     * @return A string that represents the results of instruction.
     */
    public String parseInstr(TaskList tskLst, Storage storage, String instr) {
        String res = "";
        assert instr.length() != 0 : "Instructions must contain some form of command.";
        if (instr.equals("list")) {
            res = tskLst.listOut();
        } else {
            String cmdWord = instr.split(" ")[0];
            try {
                switch(cmdWord) {
                case("unmark"):
                    return tskLst.unmark(instr, storage);
                case("mark"):
                    return tskLst.mark(instr, storage);
                case("delete"):
                    return tskLst.delete(instr, storage);
                case("find"):
                    return tskLst.find(instr);
                case("todo"):
                    return tskLst.addTask(TaskList.TaskCommand.TODO, instr, storage);
                case("deadline"):
                    return tskLst.addTask(TaskList.TaskCommand.DEADLINE, instr, storage);
                case("event"):
                    return tskLst.addTask(TaskList.TaskCommand.EVENT, instr, storage);
                case("edit"):
                    String validDescription = instr.substring(5);
                    String descriptionCommand = validDescription.split("/")[0];
                    String descriptionDetails = validDescription.split("/")[1];
                    int tsk = Integer.parseInt(validDescription.split("/")[2]);
                    return tskLst.edit(descriptionCommand, descriptionDetails, tsk);
                case("bye"):
                    System.exit(0);
                    return "";
                default:
                    throw new DukeException("OOPS!!! What is that?"
                        + "I'm sorry, but I don't recognise this command :-( \nTry another command!");
                }
            } catch (DukeException e) {
                return (e.toString());
            }
        }
        return res;
    }
}
