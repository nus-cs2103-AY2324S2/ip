public class Todo extends Task{

    static Todo from(String s){
        //Expects a string in the format "todo <description>"

        //get rid of the command
        String description = utils.discardFirstWord(s.trim()).trim();

        System.out.println("Description is: " + description);

        if (!description.isEmpty()){
            return new Todo(description);
        }else {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }
    }

    public Todo(String description) {
        super(description);
    }

    @Override
    public void markAsDone() {
        super.markAsDone();
    }

    @Override
    public void markAsUndone() {
        super.markAsUndone();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
