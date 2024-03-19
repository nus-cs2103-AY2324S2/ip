# TaskYapper User Guide

Welcome to TaskYapper by [Justin Tan](https://www.linkedin.com/in/tan-wee-kian-justin/)

The ultimate task management tool that helps you keep track of your todos, deadlines, and events with ease! 🚀🚀🚀

![Representative Screenshot for TaskYapper](Ui.png)

TaskYapper helps you manage your tasks efficiently with a variety of commands. Below is a guide structured by command descriptors to help you navigate and use TaskYapper effectively.

## Getting Started

1. Download the jar file.
2. Open the folder containing the downloaded jar file in Terminal
3. Run `java -jar taskyapper.jar`

## TaskYapper Commands

### Listing All Tasks
- **Command Descriptor:** `yap`
- **Format:** `yap`
- **Example:** Typing `yap` lists all your tasks.

### Marking a Task as Done
- **Command Descriptor:** `mark`
- **Format:** `mark <task_number>`
- **Example:** `mark 1` marks the first task in your list as done.

### Unmarking a Task
- **Command Descriptor:** `unmark`
- **Format:** `unmark <task_number>`
- **Example:** `unmark 1` reverts the first task in your list to not done.

### Adding a Todo Task
- **Command Descriptor:** `todo`
- **Format:** `todo <task_description>`
- **Example:** `todo finish reading TaskYapper user guide` adds a new todo task.

### Adding a Deadline Task
- **Command Descriptor:** `deadline`
- **Format:** `deadline <task_description> /by <date: yyyy-mm-dd>`
- **Example:** `deadline cs2103t ip /by 2024-02-23` adds a new deadline task.

> Note that deadlines require a date, representing the date this task should be done by.

### Adding an Event Task
- **Command Descriptor:** `event`
- **Format:** `event <task_description> /from <date: yyyy-mm-dd> /to <date: yyyy-mm-dd>`
- **Example:** `event ay23/24 semester 2 /from 2024-01-15 /to 2024-05-11` adds a new event task.

> Note that events require a start date and an end date.

### Exiting the Application
- **Command Descriptor:** `bye`
- **Format:** `bye`
- **Example:** Typing `bye` exits the application and automatically saves your tasks.

## Tips
> **Pro Tip:** Regularly use the `yap` command to review your tasks and stay on top of your commitments!

## Final Acknowledgements
- [X] Congratulations for reading the user guide finish!
  You are now ready to start using TaskYapper!

Thank you to the CS2103 teaching team, and fellow CS2103T team project mates for all your contributions and advice in this project!

Feel free to contact [@jyztintan](https://github.com/jyztintan) for any enquiries, bug reports or suggestions for improvements.
