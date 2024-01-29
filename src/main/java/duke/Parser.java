package duke;

import duke.task.TaskList;

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
     */
    public void parseInstr(TaskList tskLst, Storage storage, String instr) {
        if (instr.equals("list")) {
            tskLst.listOut();
        } else {
            String cmdWord = instr.split(" ")[0];
            try {
                if (cmdWord.equals("unmark")) {
                    tskLst.unmark(instr, storage);
                } else if (cmdWord.equals("mark")) {
                    tskLst.mark(instr, storage);
                } else if (cmdWord.equals("delete")) {
                    tskLst.delete(instr, storage);
                } else if (cmdWord.equals("find")) {
                    tskLst.find(instr); 
                }else {
                    if (cmdWord.equals("todo")) {
                        tskLst.addTask(TaskList.TaskCommand.TODO, instr, storage);
                    } else if (cmdWord.equals("deadline")) {
                        tskLst.addTask(TaskList.TaskCommand.DEADLINE, instr, storage);
                    } else if (cmdWord.equals("event")) {
                        tskLst.addTask(TaskList.TaskCommand.EVENT, instr, storage);
                    } else {
                        throw new DukeException("OOPS!!! What is that? I'm sorry, but I don't recognise this command :-( \nTry another command!"); 
                    } 
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }  
    }
}
