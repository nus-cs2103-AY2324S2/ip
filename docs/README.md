![Screenshot of a comment on a GitHub issue showing an image, added in the Markdown, of an Octocat smiling and raising a tentacle.](YourOnlyFriend.png)

# User Guide for Your Only Friend Project

## Introduction

Your Only Friend is a personalized task manager designed to help you keep track of your tasks. It allows you to add, delete, and mark tasks as done or not done, among other functionalities. The application supports various types of tasks such as to-dos, deadlines, and events, making it versatile for various needs.

## Getting Started

To use Your Only Friend, you need to start the application. If you're running it for the first time, Your Only Friend will try to load any existing tasks from storage. If none are found or an error occurs, it will start with an empty task list.

### Starting Your Only Friend

Run the `Launcher.java` class. This class serves as the entry point to the application and ensures that Your Only Friend is started properly, especially in environments that might have classpath or module path issues.

### Main Components

- **Your Only Friend**: The main class that initializes the application, loads tasks from storage, and processes user commands.
- **UI**: Manages interactions with the user, displaying welcome, goodbye messages, and error messages, among others.
- **Storage**: Handles loading from and saving tasks to a file, ensuring your tasks are persisted across sessions.
- **TaskList**: Contains the list of tasks and operations to manipulate these tasks (add, delete, mark, etc.).
- **Parser**: Analyzes user input and parses it into commands that Your Only Friend can execute.

## Features

### Adding Tasks

Your Only Friend supports adding three types of tasks: to-dos, deadlines, and events.

- **To-Do**: Use the command `todo` followed by the task description. Example: `todo read book`.
- **Deadline**: Use the command `deadline` followed by the task description and the due date/time. The date/time should follow the format `yyyy-MM-dd HH:mm`. Example: `deadline return book /by 2023-10-05 18:00`.
- **Event**: Use the command `event` followed by the task description and the event start and end date/time, following the format `yyyy-MM-dd HH:mm`. Example: `event book club meeting /from 2023-10-05 16:00 /to 2023-10-05 18:00`.

### Viewing Tasks

To view all tasks, use the `list` command. Your Only Friend will display the tasks with their status (done or not done), type, and associated times if applicable.

### Marking Tasks as Done

To mark a task as done, use the command `mark` followed by the task number. Example: `mark 1`.

### Unmarking Tasks

To mark a task as not done, use the command `unmark` followed by the task number. Example: `unmark 1`.

### Deleting Tasks

To delete a task, use the command `delete` followed by the task number. Example: `delete 1`.

### Finding Tasks

To find tasks containing a specific keyword, use the command `find` followed by the keyword. Example: `find book`.

### Exiting Your Only Friend

To exit the application, use the command `bye`. Your Only Friend will save your tasks and close the application.

## GUI

Your Only Friend comes with a graphical user interface (GUI) that makes interacting with the application more intuitive. The main window consists of a dialog box where you can type commands and view responses from Your Only Friend. The GUI is initiated from `Main.java`, which loads the necessary FXML and displays the primary stage.

### User Input

Enter commands in the text field and press the send button or hit enter to execute them. Your Only Friend's response will appear in the dialog area above.

## Conclusion

Your Only Friend is a handy tool for managing your daily tasks. Its simple yet powerful features are designed to improve your productivity and help you keep track of your commitments. Whether you prefer using command-line inputs or interacting with a GUI, Your Only Friend adapts to your preferred workflow.