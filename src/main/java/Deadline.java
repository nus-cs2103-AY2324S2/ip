public class Deadline extends Task{

    protected String by;
    public Deadline(String desc) {
        String[] str = desc.split("/");
        this.description = str[0];
        this.by = str[1].split(" ",2)[1];
        this.type = "D";
        this.isDone = false;
    }

    public Deadline(String desc, String isDoneNumber) {
        String[] str = desc.split("/");
        this.description = str[0];
        this.by = str[1].split(" ",2)[1];
        this.type = "D";

        if (isDoneNumber.equals("1")) {
            this.isDone = true;
        } else {
            this.isDone = false;
        }
    }

    @Override
    public String getStatus() {
        return String.format("[%s][%s] %s(by: %s)", this.type, this.getStatusIcon(),
                this.description, this.by);
    }

    @Override
    public String saveTask() {
        return String.format("deadline-%s/by %s-%d", this.description, this.by, this.isDoneNumerical());
    }
}
