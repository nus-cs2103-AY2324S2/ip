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
    public int digOutInt(String echo){
        String echo1[] = echo.split(" ", 2);
        int numberToOperateOn = Integer.parseInt(echo1[1]);
        return numberToOperateOn;
    }


    /**
     * Breaks down the input of the user into relevant details needed later
     * for forming Tasks
     *
     * @param echo input to be analysed
     * @return a string array that contains all parsed info ready for operation
     */
    public String[] decryptInput(String echo) throws DukeException{
        String[] result = new String[3];
        try {
            String keyword = echo.split(" ")[0];
            if (keyword.equals("deadline")) {
                String echo1[] = echo.split("deadline", 2);
                String deadline[] = echo1[1].split("/by", 2);
                if ((deadline[0]).matches("\\s+") || ((deadline[1]).matches("\\s+"))
                        || (deadline[1].equals(""))) {
                    throw new DukeException("Empty task fields where applicable are not allowed.\n");
                } else {
                    result[0] = deadline[0];
                    result[1] = deadline[1];
                }
            } else if (keyword.equals("event")) {
                String echo1[] = echo.split("event", 2);
                String event[] = echo1[1].split("/from", 2);
                String event1[] = event[1].split("/to", 2);

                if (((event[0]).matches("\\s+")) || (event1[0].matches("\\s+"))
                        || (event1[1].matches(  "\\s+")) || (event1[1].matches(""))) {
                    throw new DukeException("Empty task fields where applicable are not allowed.\n");
                } else {
                    result[0] = event[0];
                    result[1] = event1[0];
                    result[2] = event1[1];
                }
            } else if (keyword.equals("todo")) {
                String todo[] = echo.split("todo", 2);
                //test if empty task
                if ((todo[1]).matches("\\s+") || (todo[1]).equals("")) {
                    throw new DukeException("Empty task fields where applicable are not allowed.\n");
                } else {
                    result[0] = todo[1];
                }
            } else {
                throw new DukeException("Error.Please enter a todo, deadline or event with the relevant details!\n" +
                        "Todo: todo + task ;\n" +
                        "Event: event + task + /from yyyy-MM-dd HH:mm + /to yyyy-MM-dd HH:mm;\n" +
                        "Deadline: deadline + task + /by yyyy-MM-dd HH:mm;\n");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Enter a todo, deadline or event with the relevant details!\n" +
                    "Todo: todo + task ; \n" +
                    "Event: event + task + /from yyyy-MM-dd HH:mm + /to yyyy-MM-dd HH:mm;\n" +
                    "Deadline: deadline + task + /by yyyy-MM-dd HH:mm;\n");
        }
        return result;
    }

}
