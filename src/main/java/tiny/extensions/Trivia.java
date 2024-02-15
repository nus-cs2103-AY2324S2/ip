package tiny.extensions;

public class Trivia {
    String body;

    public Trivia(String body) {
        this.body = body;
    }

    public String formatToSave() {
        return "TR | " + body;
    }        

    @Override
    public String toString() {
        return body;
    }    
}
