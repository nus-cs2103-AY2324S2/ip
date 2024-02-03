package duke;

/**
 * Represent the class that deals with making sense of the user command
 * CS2103T
 * AY23/24 Semester 2
 * Author: Chua Zen Khoon
 */
public class Parser {


    /**
     * Obtains the integer value in the taskList that the user wants to operate on
     *
     * @param echo input to be analysed
     * @return an integer that represents the Task to be operated on
     */
    public int digOutInt(String echo) {
        String echoParts[] = echo.split(" ", 2);

        int numberToOperateOn = Integer.parseInt(echoParts[1]);

        return numberToOperateOn;
    }


    /**
     * Breaks down the input of the user into relevant details needed later
     * for forming Tasks
     *
     * @param echo input to be analysed
     * @return a string array that contains all parsed info ready for operation
     * @throws DukeException if input cannot be decrypted properly meaning invalid input
     */
    public String[] decryptInput(String echo) throws DukeException {
        String[] results = new String[3];

        String keyword = echo.split(" ")[0];
        if (keyword.equals("deadline")) {
            String echoParts[] = echo.split("deadline", 2);
            String deadlineParts[] = echoParts[1].split("/by", 2);

            if ((deadlineParts[0]).matches("\\s+") || ((deadlineParts[1]).matches("\\s+"))
                    || (deadlineParts[1].equals(""))) {
                throw new DukeException("Empty task fields where applicable are not allowed.\n");
            } else {
                results[0] = deadlineParts[0];
                results[1] = deadlineParts[1];
            }
        } else if (keyword.equals("event")) {
            String echoParts[] = echo.split("event", 2);
            String eventfirstHalfParts[] = echoParts[1].split("/from", 2);
            String eventsecondHalfParts[] = eventfirstHalfParts[1].split("/to", 2);

            if (((eventfirstHalfParts[0]).matches("\\s+")) || (eventsecondHalfParts[0].matches("\\s+"))
                    || (eventsecondHalfParts[1].matches(  "\\s+")) || (eventsecondHalfParts[1].matches(""))) {
                throw new DukeException("Empty task fields where applicable are not allowed.\n");
            } else {
                results[0] = eventfirstHalfParts[0];
                results[1] = eventsecondHalfParts[0];
                results[2] = eventsecondHalfParts[1];
            }
        } else if (keyword.equals("todo")) {
            String todoParts[] = echo.split("todo", 2);

            //test if empty task
            if ((todoParts[1]).matches("\\s+") || (todoParts[1]).equals("")) {
                throw new DukeException("Empty task fields where applicable are not allowed.\n");
            } else {
                results [0] = todoParts[1];
            }
        } else {
            throw new DukeException("Invalid command. Please ensure"
                    + "delete/mark/unmark commands\n"
                    + "only contain numbers after the command.\n"
                    + "Please enter a todo, deadline or event with the relevant details!\n"
                    + "Todo: todo + task ;\n"
                    + "Event: event + task + /from yyyy-MM-dd HH:mm + /to yyyy-MM-dd HH:mm;\n"
                    + "Deadline: deadline + task + /by yyyy-MM-dd HH:mm;\n");
        }
        return results;
    }

}
