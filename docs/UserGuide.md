# User Guide
CEGMods is a desktop to manage NUS CEG's courses via a Command Line Interface (CLI). If you are an NUS CEG student and looking for an app to manage your course and project schedule, CEGMods can help you get all your tasks down in an organized manner.
## Content page
### [1. Quick Start](#1-quick-start-1)
### [2. Features](#2-features-1)
#### [2.1 Adding a Module: `add mod`](#21-adding-a-module-add-mod-1)
#### [2.2 Deleting a Module: `delete mod`](#22-deleting-a-module-delete-mod-1)
#### [2.3 Checking Modules: `check mod`](#23-checking-modules-check-mod-1)
#### [2.4 Viewing Timetable: `timetable`](#24-viewing-timetable-timetable-1)
#### [2.5 Viewing To-Do List: `task list`](#25-viewing-to-do-list-task-list-1)
#### [2.6 Adding a Task:](#26-adding-a-task-1)
##### [2.6.1 Adding a Todo Task: `todo`](#261-adding-a-todo-task-todo-1) 
##### [2.6.2 Adding a Deadline Task: `deadline`](#262-adding-a-deadline-deadline)
##### [2.6.3 Adding a Event Task: `event`](#263-adding-an-event-event)
#### [2.7 Marking a Task as Done: `done`](#27-marking-a-task-as-done-done-1)
#### [2.8 Deleting a Task: `delete`](#28-deleting-a-task-delete-1)
#### [2.9 Finding a Task with Keyword: `find`](#29-finding-a-task-with-keyword-find-1)
#### [2.10 Adding a Project Task: `project task`](#210-adding-a-project-task-project-task-1)
#### [2.11 Viewing project task list: `project task list`](#211-viewing-project-task-list-project-task-list-1)
#### [2.12 Viewing project progress: `project progress`](#212-viewing-project-progress-project-progress-1)
#### [2.13 Viewing to do list: `print todo list`](#213-viewing-to-do-list-print-todo-list-1)
#### [2.14 Viewing event list: `print event list`](#214-viewing-event-list-print-event-list-1)
#### [2.15 Viewing the deadline list: `print deadline list`](#215-viewing-the-deadline-list-print-deadline-list-1)
#### [2.16 Viewing the undone task list: `print undone task list`](#216-viewing-the-undone-task-list-print-undone-task-list-1)
#### [2.17 Clearing past deadlines: `clear deadlines`](#217-clearing-past-deadlines-clear-deadlines-1)
#### [2.18 Delete done tasks: `delete done task`](#218-delete-done-tasks-delete-done-task-1)
#### [2.19 Check added modules:`check modules`](#219-check-added-modulescheck-modules-1)
#### [2.20 Exiting the Program: `exit`](#220-exiting-the-program-exit-1)
#### [2.21 Viewing Help: `help`](#221-viewing-help-help-1)
### [3. FAQ](#3-faq-1)
### [4. Command Summary](#4-command-summary-1)

## 1. Quick Start
1. Ensure you have Java 11 installed in your Computer.
2. Download the jar file from (link).
3. Copy the file to the folder you want to use as the home folder for your CEGmods
4. Ensure you have internet connection.
5. Open a command window. Run the java -version command to ensure you are using Java 11. 
6. Launch the jar file using the java -jar command (do not use double-clicking). On successful launch, you will be greeted with a welcome screen as seen below:

```
_______________________________________________________
                   __ _                      _           
   __      ___    / _` |  _ __     ___    __| |    ___   
  / _|    / -_)   \__, | | '  \   / _ \  / _` |   (_-<   
  \__|_   \___|   |___/  |_|_|_|  \___/  \__,_|   /__/_  
_|"""""|_|"""""|_|"""""|_|"""""|_|"""""|_|"""""|_|"""""| 
"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-'"`-0-0-' 
Hello, this is CEGMods! What can I do for you?
Enter 'help' to get more information.
_______________________________________________________
Task list has been loaded successfully.
Module list has been loaded successfully.
Today is 2020-11-09
Now is 20:14
Currently you don't have any event!
```

6. Type `help` in the command box as prompted and press Enter to execute it. You will see the  commands available.

6. Refer to the Features below for details of each command.
## 2. Features
### 2.1 Adding a Module: `add mod`
You can add a module to the module list and timetable by typing in the module code and its time slots of lectures and tutorials (the time slot of lab is optional).

**Step 1:** Type in the module code of the module you want to add following the command format. 

Format: `add mod/<MODULE_CODE>`
Example of usage: `add mod/ST2334`

Then you will see the following: 
```
_______________________________________________________
Module code: ST2334
Title: Probability and Statistics
Description: Basic concepts of probability, conditional probability, independence, random variables, joint and marginal distributions, mean and variance, some common probability distributions, sampling distributions, estimation and hypothesis testing based on a normal population.  This module is targeted at students who are interested in Statistics and are able to meet the pre-requisites. Preclude ME students taking or have taken ME4273.
_______________________________________________________
Please enter your time slots for lectures, tutorials, and labs (optional) for this module.
The format of the time slots is: Day HH:mm-HH:mm (Eg. Thur 12:00-13:00)
Lecture slot:
```

**Step 2:** Type in the lecture slot and tutorial slot of this module, and indicate whether there is lab for this module as prompted.

Format: `Day HH:mm-HH:mm`
Example of usage: `Tue 12:00-14:00`

For example, if you key in the following:
```
Lecture slot: Tue 12:00-14:00
Does this module have another lecture slot?([Y] for yes,type any other character for no)
Y
Another lecture slot: Fri 12:00-14:00
Tutorial slot: Mon 14:00-15:00
Does this module have lab? ([Y] for yes,type any other character for no)
N
Noted! I have added this module.
```

If you have **conflicting module timeslots**, you will see the following as per the example:
```
OOPS!!! There is a time conflict.
_______________________________________________________
Which module do you want to keep? Please enter the module name.
Module: ST2334
Lecture Slot: Tue 12:00-14:00
Tutorial Slot: Mon 14:00-15:00
Lab Slot: null
_______________________________________________________
Module: GEH1036
Lecture Slot: Tue 12:00-14:00
Tutorial Slot: Mon 13:00-14:00
Lab Slot: null
_______________________________________________________
```
Please enter the module you wish to keep, and you will see the following:
```
ST2334
Got it! I have add ST2334 to timetable.
```

**Step 3:** Add the task for the module if necessary.
>Note: Please refer to [`Adding a Task`](#26-Adding-a-Task1) feature to see the detailed formats.

For example, if you key in the following:
```
Is there any task you want to add for this module? Y/N
Y
Please enter T for todo, D for deadline, E for event, P for project subtask.
E
Add an event: event <DESCRIPTION> /at <YYYY-MM-DD HH:mm>
event final exam /at 2020-11-23 17:00
Please type the duration of the event in hours:(e.g. 1, 0.5)
2
    ____________________________________________________________
     Got it. I've added this task:
      [E][F]final exam (at:2020-11-23 17:00)
     Now you have 3 tasks in the list.
    ____________________________________________________________

```

### 2.2 Deleting a Module: `delete mod`
This will delete a module from the module list.

Format: `delete mod/<MODULE_CODE>`
Example of usage: `delete mod/CG1111`

Expected outcome:
```
Noted. I've removed this module.
```

### 2.3 Checking modules: `check mod`
This will check all the modules existing in the module list.

Format: `check modules`
Expected outcome:
```
_______________________________________________________
Module: CG1111
Lecture Slot: Tue 14:00-17:00
Tutorial Slot: Fri 14:00-16:00
Lab Slot: null
_______________________________________________________
_______________________________________________________
_______________________________________________________
Module: ST2334
Lecture Slot: Tue 12:00-14:00
Tutorial Slot: Mon 14:00-15:00
Lab Slot: null
_______________________________________________________
_______________________________________________________
```

### 2.4 Viewing Timetable: `timetable`
This will view the whole timetable.

Format: 
1.`today timetable`: viewing today's timetable
2.`this week timetable`: viewing weekly timetable

Expected outcome:
```
today timetable
_______________________________________________________
2020-10-21
_______________________________________________________
14:00-15:00 CG1111 tutorial
_______________________________________________________

```
```
this week timetable
_______________________________________________________
2020-11-09
_______________________________________________________
14:00-15:00 ST2334 tutorial
_______________________________________________________
2020-11-10
_______________________________________________________
12:00-14:00 ST2334 lecture
14:00-17:00 CG1111 lecture
_______________________________________________________
2020-11-11
_______________________________________________________
_______________________________________________________
2020-11-12
_______________________________________________________
14:00-17:00 CG1111 lecture
_______________________________________________________
2020-11-13
_______________________________________________________
12:00-14:00 ST2334 lecture
14:00-16:00 CG1111 tutorial
_______________________________________________________
2020-11-14
_______________________________________________________
11:00-13:00 team meeting
_______________________________________________________
2020-11-15
_______________________________________________________

```

### 2.5 Viewing To-Do List: `task list`
This will view the to-do list.

Format: `task list`

Constraint: The task list must contain at least 1 task.

Expected outcome:
```
____________________________________________________________
     Here are the tasks in your list:
      1.[T][T]debug project
      2.[E][F]final exam (at:2020-11-23 17:00)
      3.[E][F]team meeting (at:2020-11-14 11:00)
      4.[D][F]project (by:2020-11-10 00:00) [Remaining time: 0 days 5 hours 10 minutes]
	You  have 3 undone task in your list. (3/4)
____________________________________________________________
```
### 2.6 Adding a Task
#### 2.6.1 Adding a Todo Task: `todo`
This will add a task todo to the task list.

Format: `todo <DESCRIPTION> `
Example of usage: `todo read book`

Expected outcome:
```
____________________________________________________________
     Got it. I've added this task:
      [T][F]read book
     Now you have 2 tasks in the list.
____________________________________________________________
```
#### 2.6.2 Adding a Deadline: `deadline`
This will add a deadline to the task list.
Format: `deadline <DESCRIPTION> /by <YYYY-MM-DD HH:mm>`
Example of usage: `deadline lab report /by 2020-11-26 00:00`

Expected outcome:
```
deadline CS2113T project submission /by 2020-11-09 23:59
    ____________________________________________________________
     Got it. I've added this task:
      [D][F]CS2113T project submission (by:2020-11-09 23:59) [Remaining time: 0 days 3 hours 34 minutes]
     Now you have 4 tasks in the list.
    ____________________________________________________________
```
#### 2.6.3 Adding an Event: `event`
This will add an event to the task list.
Format: `event <DESCRIPTION> /at <YYYY-MM-DD HH:mm>`
Example of usage: 
`event team meeting /at 2020-09-10 10:00`
Please type the duration of the event in hours:(e.g. 1, 0.5)
`1`

Expected outcome:
```
____________________________________________________________
     Got it. I've added this task:
      [E][F]party (at:2020-09-10 08:00)
     Now you have 5 tasks in the list.
    ____________________________________________________________
```
### 2.7 Marking a Task as Done: `done`
This will mark a task as done.

Format: `done task/<TASK_INDEX>`
Example of usage: `done task/2`

Expected outcome:
```
_______________________________________________________
 	Nice! I've marked this task as done:
	[T][T]lab report
_______________________________________________________

```
### 2.8 Deleting a Task: `delete`
This will delete a task from task list.

Format: `delete task/<TASK_INDEX>`
Example of usage: `delete task/1`

Expected outcome:
```
____________________________________________________________
	Noted. I've removed this task:
	   [T][T]lab report
    Now you have 1 task in the list.
    ____________________________________________________________
```
### 2.9 Finding a Task with Keyword `find`
This will find a task from task list with the keyword.

Format: `find <KEYWORD>`
Example of usage: `find book`

Expected outcome:
```
____________________________________________________________
	Here are the matching tasks in your list:
	1.[T][F]read book
____________________________________________________________
```
### 2.10 Adding a Project Task: `project task`
This will add a subtask of a specific project to the task list.

Format: `mod/<MODULE_CODE> ptask/<DESCRIPTION> by/<DEADLINE>`
Example of usage: `mod/CG2271 ptask/write report by/Fri 23:59`

Expected outcome:
```
_______________________________________________________
Is there any material you need for this project task?
Enter your materials or NA
NA
Module: CG2271
ProjectTask: write report
By: Fri 23:59
Materials: NA
_______________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
      [P][F]write report (by:Fri 23:59) material: NA
     Now you have 2 tasks in the list.
    ____________________________________________________________

```
### 2.11 Viewing project task list: `project task list`
This will view the project task list.

Format: `mod/<MODULE_CODE> project task list`
Example of usage: `mod/CG2271 project task list`

Expected outcome:
```
CG2271

1. [P][F]write report (by:Fri 23:59) material: NA
```
### 2.12 Viewing project progress: `project progress`
This will view the progress of a specific project.

Format: `mod/<MODULE_CODE> progress`
Example of usage: `mod/CG2271 progress`

Expected outcome:
```
CG2271
You have done 0/1 (0.00%).
```
### 2.13 Viewing to do list: `print todo list`
This will view the list of all the todo tasks
Format: `print todo list`
Expected outcome:
```
print todo list
    ____________________________________________________________
     Here are the todo tasks in your list:
      1.[T][F]read book
      2.[T][F]return book
	You have 2 undone tasks in your todo list (2/2).
    ____________________________________________________________

```
### 2.14 Viewing event list: `print event list`
This will view the list of all the events
Format:`print event list`
Expected outcome:
```
print event list
    ____________________________________________________________
     Here are the events in your list:
      1.[E][F]attend CS2113T project meeting (at:2020-11-19 20:00)
	You have 1 undone event in your list (1/1).
    ____________________________________________________________

```
### 2.15 Viewing the deadline list: `print deadline list`
This will view the list of all the deadlines
Format:`print deadline list`

Expected outcome:
```
print deadline list
    ____________________________________________________________
     Here are the deadlines in your list:
      1.[D][F]CS2102 assignment (by:2020-11-11 23:59) [Remaining time: 2 days 3 hours 46 minutes]
	You have 1 undone deadline in your list (1/1).
    ____________________________________________________________


```
### 2.16 Viewing the undone task list: `print undone task list`
This will view the list of all the undone tasks
Format: `print undone task list`

Expected outcome:
```
print undone task list
    ____________________________________________________________
     Here are the undone tasks in your list:
      1.[T][F]read book
      2.[T][F]return book
      3.[E][F]attend CS2113T project meeting (at:2020-11-19 20:00)
      4.[D][F]CS2102 assignment (by:2020-11-11 23:59) [Remaining time: 2 days 3 hours 40 minutes]
	You have 4 undone tasks in your list.
    ____________________________________________________________

```
### 2.17 Clearing past deadlines: `clear deadlines`
This will clear all the past dealines
Format:`clear deadlines`

Expected outcome:
```
clear deadlines
You have removed all the past deadlines!
    ____________________________________________________________
     Here are the deadlines in your list:
      1.[D][F]CS2102 assignment (by:2020-11-11 23:59) [Remaining time: 2 days 3 hours 37 minutes]
	You have 1 undone deadline in your list (1/1).
    ____________________________________________________________

```
### 2.18 Delete done tasks: `delete done task`
This will delete all the tasks that have been marked as done

Format:`delete done task`

Expected outcomes:
```
delete done task
You have deleted all the done tasks from task list.
     Now you have 3 tasks in the list.
    ____________________________________________________________

```
### 2.19 Check added modules:`check modules`
Format:`check modules`

Expected outcomes:
```
check modules
_______________________________________________________
Module: CS2030
Lecture Slot: Fri 08:00-10:00
Tutorial Slot: Tue 08:00-10:00
Lab Slot: null
_______________________________________________________
_______________________________________________________
_______________________________________________________
Module: CS2113T
Lecture Slot: Fri 16:00-18:00
Tutorial Slot: Fri 11:00-12:00
Lab Slot: null
_______________________________________________________
_______________________________________________________
_______________________________________________________
Module: CS2101
Lecture Slot: Mon 10:00-12:00
Tutorial Slot: Thur 10:00-12:00
Lab Slot: null
_______________________________________________________
_______________________________________________________
_______________________________________________________
Module: ST2334
Lecture Slot: Tue 14:00-16:00
Tutorial Slot: Mon 13:00-14:00
Lab Slot: null
_______________________________________________________
_______________________________________________________
_______________________________________________________
Module: GEH1036
Lecture Slot: Tue 18:00-20:00
Tutorial Slot: Wed 15:00-16:00
Lab Slot: null
_______________________________________________________
_______________________________________________________
_______________________________________________________
Module: CS2102
Lecture Slot: Tue 12:00-14:00
Tutorial Slot: Wed 11:00-12:00
Lab Slot: null
_______________________________________________________
_______________________________________________________
_______________________________________________________
Module: CS1231
Lecture Slot: Mon 08:00-09:00
Tutorial Slot: Tue 21:00-22:00
Lab Slot: null
_______________________________________________________
_______________________________________________________
```
### 2.20 Exiting the Program: `exit`
This will exit the program.

Format: `exit`

Expected outcome:
```
_______________________________________________________
Bye! Have a nice day with CEG!
_______________________________________________________
```
### 2.21 Viewing Help: `help`
This will view help message.

Format: `help`

Expected outcome:
```
_______________________________________________________
1. Add a module: add mod/<MODULE_CODE>
2. Delete a module: delete mod/<MODULE_CODE>
3. Add a task to do: todo <DESCRIPTION>
4. Add a deadline: deadline <DESCRIPTION> /by <YYYY-MM-DD HH-MM>
5. Add an event: event <DESCRIPTION> /at <YYYY-MM-DD HH-MM>
6. View today's timetable: today timetable
7. View weekly timetable: this week timetable
8. Add a project subtask: mod/<MODULE_CODE> ptask/<DESCRIPTION> by/<DEADLINE>
9. View project task list: mod/<MODULE_CODE> project task list
10. View project progress: mod/<MODULE_CODE> progress
11. View task list: task list
12. Delete task: delete <TASK_INDEX>
13. Mark task as done: done <TASK_INDEX>
14. Exit CEGMods: exit
_______________________________________________________
```

## 3. FAQ
**Q:** How can I save my data?
**A:** **CEGMods** automatically saves your data on every action you take. You can find them in /data folder in the same directory you run **CEGMods** in.

**Q:** Can I edit the information in data directory?
**A:** Yes! As **CEGMods** saves and loads your information from the data directory, editing the files in data folder works. However, we would highly recommend you not to as you may cause data corruption. Use the command line instead if you wish to edit your information!


## 4. Command Summary

|Feature                              |Command                                               |
|---                                   |---                                                   |
| Adding a Module                    |`add mod/<MODULE_CODE>`                             |
| Deleting a Module                    |`delete m/<MODULE_CODE>`                             |
| Viewing Timetable                  |`today timetable`, `this week timetable`  |
| Viewing Task List                   |`task list`     |
| Adding a Todo Task	                        |`todo <DESCRIPTION>` 
| Adding a Deadline Task	                        |`deadline <DESCRIPTION> /by <YYYY-MM-DD HH:mm`   |
| Adding an Event Task	                        |`event <DESCRIPTION> /at <YYYY-MM-DD HH:mm>` | 
| Marking a Task as Done               |`done <TASK_INDEX>`                                      |
| Deleting a Task  |`delete <TASK_INDEX>`                                    |
| Finding a Task    |`find/ <KEYWORD>`                                              |
| Adding a Project Task   |  `mod/<MODULE_CODE> ptask/<DESCRIPTION> by/<DEADLINE>`       |
| Viewing Project Task List  |  `mod/<MODULE_CODE> project task list`                      |
| Viewing project progress   |    `mod/<MODULE_CODE> progress`                                  |
| Exiting the Program                       |`exit`                                                |
| Viewing Help                                 |`help`                                                 |
