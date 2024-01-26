public class Event extends Task {
    String from;
    String to;


    public Event(String taskName, String from, String to) {
        this(taskName, from, to, false);
    }

    public Event(String taskName, String from, String to, Boolean done) {
        super(taskName, done);
        super.identifier = "E";
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("%s from:%s to:%s", super.toString() , from, to);
    }

    @Override
    public String[] encode() {
        String[] encodedEvent = new String[6];
        String[] encodedTask = super.encode();

        for (int i = 0; i < encodedTask.length; i++) {
            encodedEvent[i] = encodedTask[i];
        }

        encodedEvent[4] = from;
        encodedEvent[5] = to;

        return encodedEvent;
    }
}
