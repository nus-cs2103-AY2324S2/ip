package main;
public class Poe {

    public static void main(String[] args) {
    }

    /**
     * Prints welcome screen and reminder if there exist any reminders
     *
     * @return String of greeting message and reminders if any
     */
    public String welcome() {
        try {
            String data = Storage.loadFromFile("./data.txt");
            TaskList taskList = new TaskList(data);
            return Ui.greetings() + taskList.remindTask();
        } catch (Exception e) {
            return Ui.greetings();
        }
    }

    /**
     * Process input and returns a response according to the command
     *
     * @param input input of user commands
     * @return response according to command
     */
    public String getResponse(String input) {
        String response;
        try {
            String data = Storage.loadFromFile("./data.txt");
            TaskList taskList = new TaskList(data);
            Parser parser = new Parser(taskList);
            response = parser.processInput(input);
        } catch (Exception e) {
            TaskList taskList = new TaskList();
            Parser parse = new Parser(taskList);
            response = parse.processInput(input);
        }

        return response;
    }
}
