package dune;

public class DuneException extends Exception{
    private String error;
    public DuneException(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "OOPS! " + this.error;
    }

}
