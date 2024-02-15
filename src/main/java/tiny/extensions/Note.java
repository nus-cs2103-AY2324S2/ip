package tiny.extensions;

public class Note {
    protected String title;
    protected String body;

    public Note(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String formatToSave() {
        return "NO | " + title + " | " + body;
    }

    @Override
    public String toString() {
        return "Title: " + title + " | Body: " + body;
    }    
}
