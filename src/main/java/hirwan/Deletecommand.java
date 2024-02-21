package hirwan;

public class Deletecommand extends Command {
    String input;
    Tasklist tasks;

    /**
     * the deleteCommand constructor that creates a deleteCommand object
     * @param input the string input to be translated into the delete command
     * @param tasks the tasklist to be manipulated
     */
    public Deletecommand(String input, Tasklist tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    /**
     * the getMessage method that returns the string to be displayed to the user
     * @return the string output to be printed to the user
     */
    public String getMessage() {
        String output = "";
        try {
            int numberInt = Integer.parseInt(this.input.substring(7)) - 1;
            output = "Noted. I've removed this task:\n"
                    + "  " + this.tasks.get(numberInt).substring(2) + "\n"
                    + "Now you have " + (this.tasks.size() - 1) + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            output = "Error: Please enter a valid index for deletion!";
        } catch (NumberFormatException e) {
            output = "Error: Please enter a numerical index to delete!";
        }
        return output;
    }

    /**
     * the update datae method that updates the date in the external text file after the command has executed
     */
    public void updateData() {
        int numberInt = Integer.parseInt(this.input.substring(7)) - 1;
        this.tasks.delete(numberInt);
        Storage.writeTask(this.tasks.getList());
    }
}
