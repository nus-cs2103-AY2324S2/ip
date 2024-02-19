# ConvoBot User Guide

Welcome to the ConvoBot User Guide!

This guide will walk you through the features and functionalities of our conversational bot, designed to assist you in managing your daily tasks efficiently.

![ConvoBot Screenshot](./Ui.png)

To interact with ConvoBot, simply start typing messages in the text area provided.

Commands are case-insensitive and should begin with a keyword followed by any necessary arguments.

# Create New Tasks
Add different kinds of tasks easily:

## ToDo Tasks
Create a simple ToDo task without due dates or times:

`todo task_description`

Substitute `task_description` with what needs to be accomplished.

`todo study math`

Upon adding your new task, ConvoBot will provide confirmation and include them in your updated task list.

## Deadline Tasks
Set up a deadline for specific tasks:

`deadline task_description /by yyyy-mm-dd`

Fill in `task_description` according to the desired task, replacing `yyyy-mm-dd` with the proper date. For example:

`deadline submit project proposal /by 2023-06-28`

Once executed correctly, ConvoBot will acknowledge the addition of the deadline task.

## Event Tasks
Schedule events with custom durations:

`event event_name /from yyyy-mm-dd /to yyyy-mm-dd`

Enter the appropriate information into `event_name`, substituting `yyyy-mm-dd` for the start and end of the event. An example would look like this:

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

`mark task_number`

Replace `task_number` with the appropriate number from your task list, such as `1`.  
Upon successful completion, ConvoBot will confirm that the task has been marked as complete.

## Incomplete Tasks
Unmark tasks that were mistakenly marked as completed:

`unmark task_number`

Similar to the `mark` command, replace `task_number` with the correct number from your task list, such as `1`.  
After processing this request, ConvoBot will notify you whether the task was successfully unmarked.

## Deleting Tasks
Remove unwanted tasks from your list:

`delete task_number`

Again, substitute `task_number` with the correct one from your task list, such as `1`.  
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

# Future Development
Our team is committed to enhancing ConvoBot continuously, ensuring we deliver even more value over time. Below are several planned improvements and upcoming features:

## Support for Multiple Date/Time Formats
In order to make interaction easier for users who prefer alternative date and time representations, ConvoBot will soon accept various formats beyond just `yyyy-mm-dd`. We aim to accommodate common regional variations and preferences.

For instance, the following forms might become acceptable inputs:

"Monday, January 2nd 2023"
"January 2, 2023"
"01/02/2023 14:30"
"2 Jan 2023 14:30"
By expanding supported date/time formats, ConvoBot becomes increasingly accessible to users worldwide.

## Reminder System
As part of our ongoing efforts to improve productivity management capabilities, we plan to introduce reminder alerts for approaching deadlines or events.

These notifications could arrive via email, push notification, or other messaging platforms depending upon integration possibilities.

With timely reminders enabled, staying organized and maintaining focus becomes simpler than ever before.  

## Cloud Storage Integration
Last but certainly not least, integrating cloud services represents another exciting avenue under exploration.

Integrating ConvoBot with popular online storage providers would offer numerous benefits including automatic syncing across devices, secure backup functionality, and effortless collaboration between users sharing tasks.

Furthermore, connecting to calendaring applications may allow scheduling options directly within ConvoBot, making planning even more convenient.

# Disclaimer
AI tool Mixtral-8x7B-Instruct-v0.1 assisted in the making of this user guide.
