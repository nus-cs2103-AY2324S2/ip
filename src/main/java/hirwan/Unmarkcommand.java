package hirwan;

public class Unmarkcommand extends Command {
    String input;
    Tasklist tasks;
    public Unmarkcommand(String input, Tasklist tasks) {
        this.input = input;
        this.tasks = tasks;
    }

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

    public void updateData() {
        String number = this.input.substring(7);
        int numberInt = Integer.parseInt(number);
        String temp = tasks.get(numberInt - 1).substring(9);
        String type = tasks.get(numberInt - 1).substring(2, 5);

        tasks.set(numberInt - 1, ". " + type + "[ ] " + temp);
        Storage.writeTask(tasks.getList());
    }
}
