package hirwan;

public class Deletecommand extends Command {
    String input;
    Tasklist tasks;
    public Deletecommand(String input, Tasklist tasks) {
        this.input = input;
        this.tasks = tasks;
    }

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

    public void updateData() {
        int numberInt = Integer.parseInt(this.input.substring(7)) - 1;
        this.tasks.delete(numberInt);
        Storage.writeTask(this.tasks.getList());
    }
}
