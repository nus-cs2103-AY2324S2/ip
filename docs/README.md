# Chatimous Maximous

![Screenshot of Ui](/Ui.png)

Chatimous Maximous is a chatbot assistant created to help users organize their tasks.

## Features:

### Adding tasks: `task`
Adds tasks that do not have specific deadlines.

Input format: todo [Name]

Expected output: [Name] has been accounted for!

Example: `todo Read book`
```
Read book has been accounted for!
```

### Adding deadlines: `deadline`
Adds tasks that need to be completed by a specific deadline.

Input format: deadline [Name] by [Date: YYYY-MM-DD]

Expected output: [Name] has been accounted for!

Example: `deadline Homework by 2024-03-01`
```
Homework has been accounted for!
```

### Adding events: `event`
Adds events that occur on a specific date.

Input format: event [Name] by [Date: YYYY-MM-DD]

Expected output: [Name] has been accounted for!

Example: `event Doctor's appointment by 2024-03-01`
```
Doctor's appointment has been accounted for!
```

### Listing all tasks: `list`
Shows a list of all the tasks currently saved

Input format: list

### Removing tasks: `remove`
Removes task from the task list.

Input format: remove [Index]

Expected output: I have removed the task from the list Sir!

### Mark as completed: `mark`
Marks task as completed and get a compliment from Chatimous Maximous.

Input format: mark [Index]

Expected output: A job well done Sir!

### Mark as incomplete: `unmark`
Marks task as incomplete and get an encouraging message from Chatimous Maximous.

Input format: unmark [Index]

Expected output: Don't fret Sir! You'll get it soon

### End session: `bye`
Ends the session with Chatimous Maximous

Input format: bye

### Find task: `find`
Find task with a given name

Input format: find [Name]

Example:
![Screenshot example of find](/Find.png)

### Dealing with duplicates: `dupe`
Finds and deletes duplicated tasks in the task list

Input format: dupe