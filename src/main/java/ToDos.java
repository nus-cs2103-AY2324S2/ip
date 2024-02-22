public class ToDos extends Task{
    public ToDos(String description) {
        super(description);
    }

    @Override
    public void print() {
        System.out.println( type() + getStatusIcon() + getDescription());
    }

    @Override
    public String type() {
        return ("\t" + "[T]");
    }
}
