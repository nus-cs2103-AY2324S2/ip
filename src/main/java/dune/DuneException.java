package dune;

public class DuneException extends Exception{
    public DuneException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "OOPS! " + this.getMessage();
    }

}
