public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public void taskPrinter() {
        String result = "    " + "[T][ ]" + " " + description;
        System.out.println(result);
    }

    @Override
    public void taskPrinter(int index) {
        String result = "";
        if (isDone) {
            result = "    " + (index+1) + ".[T][X]" + " " + description;
        } else {
            result = "    " + (index+1) + ".[T][ ]" + " " + description;
        }

        System.out.println(result);
    }
}
