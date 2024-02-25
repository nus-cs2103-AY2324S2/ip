# Roland User Guide

<img width="512" alt="Ui" src="https://github.com/wolffe88/ip/assets/38790326/5fb8c759-8734-47be-855b-772540e7f843">

## Roland is a task manager chatbot that can assist its users in increasing their productivity.

## Installing Roland

1. Download Roland [here]().
2. Navigate to your Downloads folder using the terminal.
3. Type `java -jar Roland.jar` in your terminal.
4. Press enter.



## Commands
- Exit Command - `bye`
Description: Exits the application.

- List Command - `list`
Description: Lists all tasks.

- Find Command - `find [KEYWORD]`
Description: Finds tasks containing the specified keyword.

- Mark Command - `mark [INDEX]`
Description: Marks a task as done based on the index given in list.

- Unmark Command - `unmark [INDEX]`
Description: Unmarks a task as done based on the index given in list.

- Add Notes Command - `note [INDEX] /[NOTES]`
Description: Adds notes to a task.

- Delete Command - `delete [INDEX]`
Description: Deletes a task.

- Add To-Do Command - `todo [DESCRIPTION]`
Description: Adds a to-do task.

- Add Deadline Command - `deadline [DESCRIPTION] /by [YYYY-mm-dd]`
Description: Adds a task with a deadline.

- Add Event Command - `event [DESCRIPTION] /from [START] /to [END]`
Description: Adds an event task with start and end times.

## Command summary

| Action   | Format, Examples                                                                                                                       |
|----------|----------------------------------------------------------------------------------------------------------------------------------------|
| Todo     | `todo <description>` <br/> e.g. `todo CS2103T assignment`                                                                              |
| Event    | `event <description> /from <start> /to <end>`<br/> e.g. `event Hackathon /from Monday /to Friday`                                      |
| Deadline | `deadline <description> /by <YYYY-mm-dd>` <br/> e.g. `deadline assignment /by 2024-03-04`                                              |
| Note     | `note <index> /<description>` <br/> e.g. `note 1 /add user guide and release jar file`                                                 |
| List     | `list`                                                                                                                                 |
| Mark     | `mark <index>` <br/> e.g. `mark 1`                                                                                                     |
| Unmark   | `unmark <index>` <br/> e.g. `unmark 1`                                                                                                 |
| Delete   | `delete <index>` <br/> e.g. `delete 1`                                                                                                 |
| Find     | `find <keyword>` <br/> e.g. `find assignment`                                                                                          |
| Bye      | `bye`                                                                                                                                  |


