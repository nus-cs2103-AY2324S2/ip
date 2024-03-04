package yapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



class UiTest {

    @Test
    @DisplayName("testShowWelcomeMessageShouldReturnCorrectMessage")
    void testShowWelcomeMessageShouldReturnCorrectMessage() {
        // Arrange
        Ui ui = new Ui();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Act
        ui.showWelcomeMessage();
        String welcomeMessage = outputStream.toString().trim();

        // Assert
        assertEquals("Hello! I'm Yapper.", welcomeMessage);

        // Reset System.out to the original PrintStream
        System.setOut(originalOut);
    }
}
