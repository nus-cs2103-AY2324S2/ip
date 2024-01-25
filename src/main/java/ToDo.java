public class ToDo extends Task {

    public ToDo(String input) {
        super(TaskType.TODO);
        delimiter = "todo";
        command = "todo";
        setUpTask(input);
    }

    @Override
    public String printType() {
        return "[T]";
    }

    @Override
    public void setUpTask(String input) {
        input = input.trim();
        if (!input.contains(command)) throw new RuntimeException("not todo");
        String[] inputArray = Task.NextWords(input.split(delimiter));
        description = inputArray[0].trim();
    }

    @Override
    public String toString() {
        return printType() + "[" + getIsDoneStatus() + "] " + getDescription();
    }
}
