# BobBot User Guide

// Product screenshot goes here
![DaExample.png](..%2Fsrc%2Fmain%2Fresources%2Fimages%2FDaExample.png)

Bob cooks, cleans and keeps your quintessential information for you. It's a chatbot that helps you keep track of your tasks, deadlines and events. It's a simple and easy to use chatbot that is perfect for anyone who wants to keep track of their tasks and deadlines.

## Adding deadlines

The Deadline feature allows users to create, manage, and track tasks that have specific deadlines. This feature is an integral part of our application, designed to help users stay organized and on top of their tasks.

// Give examples of usage

Deadline newDeadline = new Deadline("Task Description", LocalDate.parse("YYYY-MM-DD"));

// A description of the expected outcome goes here


```
[D][ ] Task Description (by: YYYY-MM-DD) TagName
```

## Adding Event

The Event Management feature designed to help users effectively manage their scheduled events. This feature extends the basic task functionality to include events with specific start and end times, making it easier for users to organize their schedules.

Create events with a detailed description, along with precise start and end times. This feature allows users to define and manage activities within a designated time frame.

//Give examples of usage

Event newEvent = new Event("Event Description", LocalDateTime.parse("YYYY-MM-DDTHH:MM"), LocalDateTime.parse("YYYY-MM-DDTHH:MM"));

// A description of the expected outcome goes here

```
[E][ ] Event Description (from: YYYY-MM-DDTHH:MM to: YYYY-MM-DDTHH:MM) TagName
```

## Tagging feature

The tagging feature enables users to categorize their tasks using simple labels. Tags provide an easy way to mark, identify, or group tasks, enhancing the task management capabilities of our application.

A tag can be created with a specific name, which can then be associated with one or more tasks.

// Give examples of usage

Tag urgentTag = new Tag("Urgent");

Associating Tags with Tasks
Once a tag is created, it can be associated with tasks. This association helps in categorizing tasks based on the tag's name.

Example:

To associate a tag with a task, the task class should have methods to add or remove tags. 

For instance:

task.addTag(urgentTag);

Removing Tags from Tasks

If a task's priority changes or if the tag is no longer relevant, the tag can be removed from the task.

// Example of usage

task.removeTag(urgentTag);

Integration

The tagging feature is designed to work seamlessly with the task management system of the application. It complements other features such as deadlines and events, providing a comprehensive solution for organizing and managing tasks.