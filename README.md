# *Howie To-Do App*

> Just Do It - Nike

## Here are the reasons why you **definitely** need Howie :ghost::ghost::ghost: :
- Howie is easy-to-use
- Free
- Keeps your life ~~in~~ on track!

## How to use Howie?
1. - [ ] Download the latest JAR file from [here](https://github.com/smolegz/ip/releases/tag/A-Jar)!
1.   - [ ] Open up your command line.
1.  - [ ] Type in the following command:
  ```powershell
  java -jar C:\filepath\of\the\downloaded\jar\file
  ```

## Sample Commands
Adds shower into the to-do list. `todo shower` 

Add a deadline. `deadline Assignment Submission /by Thursday`

Add an event. `event Prom date /from Wed 9pm /to Wed 11pm`

Mark an item as completed. `mark 1`/ `mark 3`

Unmark an item as uncompleted `unmark 3`

To exit. `bye`

Alternatively, you can clone the repository and run Howie.java, as shown below.
```Java
public class Howie {

    public static void main(String[] args) throws Exception {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskLs = storage.readFile();
        while (true) {
            String[] input = ui.getUserCommand();
            try {
                Command command = new Parser().parseCommand(input);
                command.setData(taskLs);
                command.execute();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                Ui.printVLine();
            }
        }
    }
}
```
