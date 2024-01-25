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
        String result = "    " + (index+1) + ".[T]" + getStatusIcon() + " " + description;
        System.out.println(result);
    }
}
