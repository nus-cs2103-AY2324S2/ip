# ConvoBot User Guide

ConvoBot is revolutionary desktop chat application designed to streamline your task management process!

Say goodbye to clunky, slow-moving interfaces and hello to a new era of productivity.

![ConvoBot Screenshot](./Ui.png)

To interact with ConvoBot, simply start typing messages in the text area provided.

Commands are case-insensitive and should begin with a keyword followed by any necessary arguments.

# Create New Tasks
Add different kinds of tasks easily:

## ToDo Tasks
Create a simple ToDo task without due dates or times:

`todo <task_description>`

Substitute `<task_description>` with what needs to be accomplished.

`todo study math`

Upon adding your new task, ConvoBot will provide confirmation and include them in your updated task list.

## Deadline Tasks
Set up a deadline for specific tasks:

`deadline <task_description> /by yyyy-mm-dd`

Fill in `<task_description>` according to the desired task, replacing `yyyy-mm-dd` with the proper date. For example:

`deadline submit project proposal /by 2023-06-28`

Once executed correctly, ConvoBot will acknowledge the addition of the deadline task.

## Event Tasks
Schedule events with custom durations:

`event <event_description> /from yyyy-mm-dd /to yyyy-mm-dd`

Enter the appropriate information into `event_description`, substituting `yyyy-mm-dd` for the start and end of the event. An example would look like this:

`event annual company retreat /from 2024-07-04 /to 2024-07-06`

After validating the input data, ConvoBot will confirm the creation of the new event on your task list.

# Managing Tasks
Use these commands to manage your tasks effectively:

## View Your Task List
Check all current tasks at any time:

`list`

ConvoBot will display a numbered list of all existing tasks along with their descriptions.

## Completed Tasks
Mark completed tasks to keep track of your progress:

`mark <task_id>`

Replace `<task_id>` with the appropriate number from your task list, such as `1`.  
Upon successful completion, ConvoBot will confirm that the task has been marked as complete.

## Incomplete Tasks
Unmark tasks that were mistakenly marked as completed:

`unmark <task_id>`

Similar to the `mark` command, replace `<task_id>` with the correct number from your task list, such as `1`.  
After processing this request, ConvoBot will notify you whether the task was successfully unmarked.

## Deleting Tasks
Remove unwanted tasks from your list:

`delete <task_id>`

Again, substitute `<task_id>` with the correct one from your task list, such as `1`.  
Once processed, ConvoBot will inform you about the deletion status.

## Searching Your Task List
Find relevant tasks quickly:

`find search_term`

Input any keywords related to the task(s) you want to locate. Replace `search_term` with those words or phrases, such as:

`find meeting presentation`

### Fuzzy Search
ConvoBot supports fuzzy string matching using the Levenshtein distance.

# Exit the Application
Say goodbye to ConvoBot when you're finished using it:

`bye`

ConvoBot will close immediately.

# Command Summary

| Command             | Syntax                             | Example                                 |
|---------------------|------------------------------------|-----------------------------------------|
| Create todo         | todo <task_description>            | todo study mathematics                  |
| Create deadline     | deadline <task_description> /by yyyy-mm-dd | deadline submit report /by 2023-06-28 |
| Create event        | event <event_description> /from yyyy-mm-dd /to yyyy-mm-dd | event annual conference /from 2023-07-04 /to 2023-07-06 |
| List all tasks      | list                               | list                                    |
| Mark task as done   | mark <task_id>                     | mark 3                                  |
| Unmark task as done | unmark <task_id>                   | unmark 3                                |
| Delete task         | delete <task_id>                   | delete 3                                |
| Search for task     | find <search_string>               | find meeting                            |
| Exit                | bye                                | bye                                     |

# Disclaimer
AI tool Mixtral-8x7B-Instruct-v0.1 assisted in the making of this user guide.
