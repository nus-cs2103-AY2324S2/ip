package duke.task;

public class ToDos extends Task {
    public ToDos(String name, Boolean status) {
        super(name, status);
    }

    @Override
    public String saveOutput() {
        return "T " + super.saveOutput();
    }

    @Override
    public String printMatch(String match) {
        if (super.contains(match)) {
            return taskInfo();
        }
        return "";
    }

    @Override
    public String taskInfo() {
        String output = "";
        output += "[T]";
        return output + super.taskInfo() + System.lineSeparator();
    }
}
