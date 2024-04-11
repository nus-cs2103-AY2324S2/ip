package dukeexceptions;

public class InvalidDataFormat extends DukeException{
    private String filePath;
    public InvalidDataFormat(String err) {
        super(String.format("My apologies Sir/Mdm, %s is of the wrong data format.Please edit it at %s", err));
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
