# Pew ChatBot

This is a user guide on how to use Pew Chatbot. Given below are instructions on how to use it.

## Features

- Command Keywords are case-insensitive
- Words in UPPER_CASE are the parameters to be supplied by the user.
- Items in square brackets are optional.
Example
```
deadline submit TASK /by DATE [TIME]
```
TASK, DATE and TIME are parameters which can be used as:
```
deadline submit 2103T homework /by 22/2/2024 2359
```
- Date formats allowed:
   - M/d/yyyy
   - yyyy-MM-dd
   - dd-MM-yyyy
   - d/M/yyyy
   - M-d-yyyy
   - d-M-yyyy
- Time format allowed: HHmm

## Commands
### todo
Add todo task
```
todo eat
```
### deadline
Add an event with deadline, must include DATE, TIME is optional.
If TIME not included, it will be automatically set to 12AM.

Without TIME:
```
deadline submit 2103T homework /by 22/2/2024
```
With TIME
```
deadline submit 2103T homework /by 22/2/2024 2359
```
### event
Add an event with a start and end. Must include DATE for start and end, TIME is optional.
If TIME not included, it will be automatically set to 12AM.

Without TIME:
```
event submit 2103T homework /from 12/2/2024 /to 22/2/2024
```
With TIME
```
event submit 2103T homework /from 12/2/2024 1000 /to 22/2/2024 2359
```
### mark
Mark a task as done.

Example: You want to mark the first item in the list as done:
```
mark 1
```
### unmark
Mark a task as not done.

Example: You want to mark the first item in the list as not done:
```
unmark 1
```
### list
List all the tasks and the total number of tasks saved.
```
list
```
### delete
Delete a task.

Example: You want to delete the first item in the list:
```
delete 1
``` 
### find
Find a task based on a keyword or phrase. 

It will return all tasks that matches or partially matches the keyword or phrase.

Example: You want to find all the tasks that includes "2103T"
```
find 2103T
```
### pewpewpew
Easter egg 🥚

