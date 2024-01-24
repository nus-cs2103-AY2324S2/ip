public class Event extends Task{
    String startDate;
    String endDate;
    public Event(String name, String startDate, String endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        System.out.println(toString());
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format("[E]%s(from: %sto:%s)", super.toString(), startDate, endDate);
        return str;
    }

}
