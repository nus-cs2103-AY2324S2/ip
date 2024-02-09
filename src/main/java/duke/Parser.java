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
        assert instr.length() != 0: "Instructions must contain some form of command.";
        if (instr.equals("list")) {
            res = tskLst.listOut();
        } else {
            String cmdWord = instr.split(" ")[0];
            try {
                if (cmdWord.equals("unmark")) {
                    res = tskLst.unmark(instr, storage);
                } else if (cmdWord.equals("mark")) {
                    res = tskLst.mark(instr, storage);
                } else if (cmdWord.equals("delete")) {
                    res = tskLst.delete(instr, storage);
                } else if (cmdWord.equals("find")) {
                    res = tskLst.find(instr);
                } else {
                    if (cmdWord.equals("todo")) {
                        res = tskLst.addTask(TaskList.TaskCommand.TODO, instr, storage);
                    } else if (cmdWord.equals("deadline")) {
                        res = tskLst.addTask(TaskList.TaskCommand.DEADLINE, instr, storage);
                    } else if (cmdWord.equals("event")) {
                        res = tskLst.addTask(TaskList.TaskCommand.EVENT, instr, storage);
                    } else {
                        throw new DukeException("OOPS!!! What is that?"
                            + "I'm sorry, but I don't recognise this command :-( \nTry another command!");
                    }
                }
            } catch (DukeException e) {
                return(e.toString());
            }
        }
        return res;
    }
}
