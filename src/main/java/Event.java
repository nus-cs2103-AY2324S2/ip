public class Event extends Task{
    protected String from;
    protected String to;
    public Event(String desc) {
        String[] str = desc.split("/");
        this.description = str[0];
        this.from = str[1].split(" ",2)[1];
        this.to = str[2].split(" ", 2)[1];
        this.type = "E";
        this.isDone = false;
    }

    @Override
    public String getStatus() {
        return String.format("[%s][%s] %s(from: %sto: %s)", this.type, this.getStatusIcon(),
                this.description, this.from, this.to);
    }
}
