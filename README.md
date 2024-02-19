# *Skyler Feature Enhancement*

## *Changes Made*

1. Implemented new features and improvements for Skyler.
2. Enhanced user experience with text-based interactions.
3. Added functionality to manage tasks efficiently.

## *Features Added*

- Managing tasks with Skyler is now text-based and easy to learn.
- Skyler is FAST, SUPER FAST to use.
- Download Skyler [here](https://github.com/eunrcn/ip).
- Double-click the downloaded file to launch Skyler.
- Add your tasks and let Skyler manage them for you 😉.
- Skyler is FREE!

## *Features*

[x] Managing tasks.
[x] Managing deadlines.
[   ] Reminders.

> skyler is the name of a very cutie doge

## *For Java Programmers*

If you are a Java programmer, you can use Skyler to practice Java too. Here's the main method:

```java
public class Main extends Application {

    private Skyler skyler = new Skyler();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSkyler(skyler);
            stage.setTitle("Skyler");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

