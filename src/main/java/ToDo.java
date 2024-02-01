public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
        this.type = "T";
    }

    public ToDo(String description, String isDoneNumber) {
        super(description);
        this.type = "T";

        if (isDoneNumber.equals("1")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    @Override
    public String saveTask() {
        return String.format("todo_%s_%d",  this.description, this.isDoneNumerical());
    }
}
