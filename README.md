# Cleo Chatbot User Guide 😸

Welcome to **Cleo** 🐈‍⬛, your friendly Desktop GUI chatbot app designed to help you manage your tasks with ease. Cleo can help you keep track of your todos, deadlines, and events, making sure you stay organized and productive.
This is optimized for use via a Command Line Interface (CLI) while still having the benefits of a GUI. If you can type fast, Cleo can help you manage your tasks faster than traditional GUI apps.

## Getting Started

### Installation

To get started with Cleo, follow these simple steps 🎉:

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest cleo.jar from here.
3. Copy the file to the folder you want to use as the home folder for Cleo.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar naruto.jar command to run the application.
   A GUI similar to the below should appear in a few seconds.

### Features

Cleo supports the following commands to help you manage your tasks:

- **Todo**: Adds a todo task.
- **Deadline**: Adds a task with a deadline.
- **Event**: Adds an event happening at a specific time.
- **List**: Lists all your tasks.
- **Mark**: Marks a task as completed.
- **Unmark**: Marks a task as not completed.
- **Delete**: Deletes a specific task.
- **Find**: Finds tasks containing a specific keyword.
- **Tasks on**: Lists tasks happening on a specific date.
- **Bye**: Exits the app.

### Usage

Below are some examples of how to use Cleo's commands:

#### Adding a Todo
todo read book

#### Adding a Deadline
deadline submit assignment /by 12/2/2024 1800

#### Adding an Event
event project meeting /from 12/2/2024 /to 12/2/2024

#### Listing all Tasks
list

#### Listing Tasks by Date
tasks on 12/2/2024

#### Marking a Task as Completed
mark 1

#### Unmarking a Task
unmark 1

#### Deleting a Task
delete 1

#### Finding Tasks by keyword
find book

#### Exiting the App
bye


## FAQs

**Q: How do I ensure my tasks are saved?**  
A: Cleo automatically saves your tasks in a file. Your tasks will be loaded the next time you start the app.

**Q: Can I edit a task after adding it?**  
A: Currently, Cleo does not support editing tasks. You'll need to delete and re-add the task with the updated details.
