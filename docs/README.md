# BobBot User Guide

![Ui.png](Ui.png)

Bob cooks, cleans and keeps your quintessential information for you. It's a chatbot that helps you keep track of your tasks, deadlines and events. It's a simple and easy to use chatbot that is perfect for anyone who wants to keep track of their tasks and deadlines.

## Adding Deadlines

### Usage

To create a new deadline, you need to use the `deadline` command followed by the task description and the `/by` keyword with the date. The date should be in the format "YYYY-MM-DD". Here's an example:

```markdown
deadline Finish report /by 2023-03-16
```

### Expected Outcome

The new deadline will be added to your task list. It will be displayed in the following format:

```markdown
[D][ ] Finish report (by: 2023-03-16)
```

## Adding Events

### Usage

To create a new event, you need to use the `event` command followed by the task description and the `/from` and `/to` keywords with the start and end times. The times should be in the format "YYYY-MM-DD HH:mm". Here's an example:

```markdown
event Team meeting /from 2023-03-16 10:00 /to 2023-03-16 11:00
```

### Expected Outcome

The new event will be added to your task list. It will be displayed in the following format:

```markdown
[E][ ] Team meeting (from: 2023-03-16T10:00 to: 2023-03-16T11:00)
```

## Tagging Feature

### Usage

To add a tag to a task, you need to use the `tag` command followed by the task number and the tag name. Here's an example:

```markdown
tag 1 Urgent
```

To remove a tag from a task, you need to use the `removetag` command followed by the task number and the tag name. Here's an example:

```markdown
removetag 1
```

### Expected Outcome

The tag will be displayed next to the task in the task list. For example, if you add the "Urgent" tag to a task, it will be displayed like this:

```markdown
[D][ ] Finish report (by: 2023-03-16) #Urgent
```

If you remove the "Urgent" tag from a task, the tag will no longer be displayed next to the task.

The Deadline feature allows users to create, manage, and track tasks that have specific deadlines. This feature is an integral part of our application, designed to help users stay organized and on top of their tasks.

## Deleting Tasks

### Usage

To delete a task, you need to use the `delete` command followed by the task number. Here's an example:

```markdown
delete 1
```

### Expected Outcome

The task will be removed from your task list.

The Delete feature allows users to remove tasks that are no longer relevant or necessary. This feature is designed to help users keep their task list clean and organized.

## Finding Tasks

### Usage

To find tasks that contain a specific keyword, you need to use the `find` command followed by the keyword. Here's an example:

```markdown
find report
```

### Expected Outcome

The tasks that contain the keyword will be displayed.

The Find feature allows users to search for tasks that contain a specific keyword. This feature is designed to help users quickly locate tasks that are relevant to their search.

## Listing Tasks

### Usage

To list all the tasks, you need to use the `list` command. Here's an example:

```markdown
list
```

### Expected Outcome

All the tasks will be displayed.

The List feature allows users to view all the tasks that are currently in their task list. This feature is designed to help users get an overview of their tasks and deadlines.

## Marking Tasks as Done

### Usage

To mark a task as done, you need to use the `mark` command followed by the task number. Here's an example:

```markdown
mark 1
```

### Expected Outcome

The task will be marked as done.

The Mark as Done feature allows users to keep track of the tasks that they have completed. This feature is designed to help users stay organized and focused on their remaining tasks.

## Unmarking Tasks as Done

### Usage

To unmark a task as done, you need to use the `unmark` command followed by the task number. Here's an example:

```markdown
unmark 1
```

### Expected Outcome

The task will be unmarked as done.

The Unmark as Done feature allows users to undo the action of marking a task as done. This feature is designed to help users correct any mistakes and keep their task list accurate.

## Exiting the Application

### Usage

To exit the application, you need to use the `bye` command. Here's an example:

```markdown
bye
```

### Expected Outcome

The application will close.

The Exit feature allows users to close the application and end their session. This feature is designed to help users exit the application when they are done using it.