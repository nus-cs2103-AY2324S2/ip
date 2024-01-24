package Tasks;

public class Date {
    private String date;
    private String formattedDate;
    private void formatDate() {
        this.formattedDate = this.date;
    }
    public Date(String date) {
        this.date = date;
        formatDate();
    }

    public String toString() {
        return this.formattedDate;
    }
}