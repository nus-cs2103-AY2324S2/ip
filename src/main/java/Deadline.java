public class Deadline extends Task{

    protected String by;
    public Deadline(String desc) {
        String[] str = desc.split("/");
        this.description = str[0];
        this.by = str[1].split(" ",2)[1];
        this.type = "D";
        this.isDone = false;
    }

    @Override
    public String getStatus() {
        return String.format("[%s][%s] %s(by: %s)", this.type, this.getStatusIcon(),
                this.description, this.by);
    }
}
