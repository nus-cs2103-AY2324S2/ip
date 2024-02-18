# Cleo Chatbot User Guide

Welcome to **Cleo**, your friendly Desktop GUI chatbot app designed to help you manage your tasks with ease. Cleo can help you keep track of your todos, deadlines, and events, making sure you stay organized and productive.
This is optimized for use via a Command Line Interface (CLI) while still having the benefits of a GUI. If you can type fast, Cleo can help you manage your tasks faster than traditional GUI apps.

## Getting Started

### Installation

To get started with Cleo, follow these simple steps:

1. Download the latest release from [Cleo Releases](#).
2. Unzip the downloaded file to your desired location.
3. Double-click on the `Cleo.jar` file to start the app.

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