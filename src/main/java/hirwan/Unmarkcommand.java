package hirwan;

public class Unmarkcommand extends Command {
    String input;
    Tasklist tasks;

    /**
     * the unmark command constructor that creates an unmark command instance
     * @param input the text to be converted into the unmark instruction
     * @param tasks the tasklist to unmark
     */
    public Unmarkcommand(String input, Tasklist tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    /**
     * the getMessage method that returns the string to print to the user after the unmark command is called
     * @return the string to be printed to the user
     */
    public String getMessage() {
        String output = "";
        try {
            String number = this.input.substring(7);
            int numberInt = Integer.parseInt(number);
            String temp = tasks.get(numberInt - 1).substring(9);

            output = "OK, I've marked this task as not done yet: \n" + "[ ] " + temp;
        } catch (IndexOutOfBoundsException e) {
            output = output + "Error: Please enter a valid index for unmarking!";
        } catch (NumberFormatException e) {
            output = output + "Error: Please enter a numerical index to unmark!";
        }
        return output;
    }

    /**
     * the updateData method which updates the tasks in the external text file everytime a task is unmarked
     */
    public void updateData() {
        String number = this.input.substring(7);
        int numberInt = Integer.parseInt(number);
        String temp = tasks.get(numberInt - 1).substring(9);
        String type = tasks.get(numberInt - 1).substring(2, 5);

        tasks.set(numberInt - 1, ". " + type + "[ ] " + temp);
        Storage.writeTask(tasks.getList());
    }
}
