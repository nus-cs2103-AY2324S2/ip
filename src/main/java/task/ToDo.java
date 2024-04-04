package task;

public class ToDo extends Task {

    /**
     * Constructor for a ToDo.
     * @param description Description of the Todo.
     * */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor for a ToDo.
     * @param description Description of the Todo.
     * @param isDone Boolean value to state if the task is done or not.
     * */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Prints the task in the console.
     * */
    @Override
    public String taskPrinter() {
        String result = "    " + "[T][ ]" + " " + description
                + System.lineSeparator()
                + "        " + this.printTags();

        if (this.tags.isEmpty()) {
            result = "    " + "[T][ ]" + " " + description;
        }
        return result;
    }

    /**
     * Prints the task and its corresponding index in the list of tasks in the console.
     * */
    @Override
    public String taskPrinter(int index) {
        String result = "    " + (index+1) + ".[T]" + getStatusIcon() + " " + description
                + System.lineSeparator()
                + "        " + this.printTags();

        if (this.tags.isEmpty()) {
            result = "    " + (index+1) + ".[T]" + getStatusIcon() + " " + description;
        }
        return result;
    }

    /**
     * Prints the task in the format of the saved txt file.
     * */
    @Override
    public String storagePrinter() {

        if (this.tags.isEmpty()) {
            return "T" + "|isdone" + (isDone ? 1 : 0) + "|desc" + description;
        }

        String tagString = "|tag";

        for (String t : this.tags) {
            tagString += "_" + t;
        }

        return "T" + "|isdone" + (isDone ? 1 : 0) + "|desc" + description + tagString ;
    }
}
