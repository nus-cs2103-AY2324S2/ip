package hirwan;

public class Markcommand extends Command {
    String input;
    Tasklist tasks;
    public Markcommand(String input, Tasklist tasks) {
        this.input = input;
        this.tasks = tasks;
    }
    public String getMessage() {
        String output = "";
        try {
            String number = this.input.substring(5);
            int numberint = Integer.parseInt(number);
            String temp = tasks.get(numberint - 1).substring(9);
            output = "Nice! I've marked this task as done: \n" + "[X] " + temp;
        } catch (IndexOutOfBoundsException e) {
            output = "Error: Please enter a valid index for marking!";
        } catch (NumberFormatException e) {
            output = "Error: Please enter a numerical index to mark!";
        }
        return output;
    }
    public void updateData() {
        String number = this.input.substring(5);
        int numberint = Integer.parseInt(number);
        String temp = tasks.get(numberint - 1).substring(9);
        String type = tasks.get(numberint - 1).substring(2, 5);

        tasks.set(numberint - 1, ". " + type + "[X] " + temp);
        Storage.writeTask(this.tasks.getList());
    }
}
