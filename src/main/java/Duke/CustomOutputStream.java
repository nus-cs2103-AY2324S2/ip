package Duke;
import java.io.IOException;
import java.io.OutputStream;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class CustomOutputStream extends OutputStream {
    private TextArea outputArea;

    public CustomOutputStream(TextArea outputArea) {
        this.outputArea = outputArea;
    }

    @Override
    public void write(int b) throws IOException {
        Platform.runLater(() -> outputArea.appendText(String.valueOf((char) b)));
    }
}