# Zhong Ningmou's - Project Portfolio Page

## Overview
CEGMods is a command line (CLI) application that helps computer engineering (CEG) students to manage their modules, tasks and projects. It is written in Java, and has about 3 kLoC.

Given below are my contributions to the project.
### Summary of Contributions
Code contributed: [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=ZhongNingmou&tabRepo=AY2021S1-CS2113T-F11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code~test-code)

Enhancements implemented:
- Added project management feature
    - What is does: allows students to manage their projects by modules.
    - Justification: This feature is a unique point of our product because our target users are NUS CEG students, who have plenty of projects. Users can use this feature to manage their projects based on modules. Users can view each project's task list, as well as the project progress (in percentage). Additionally, users can noted down the materials they need for a particular subtask. What's more, all the project subtasks will be added into the whole task list, in order to remind our users their coming tasks.
    - Highlights: This enhancement affects existing task types and storage to txt file. The 'ProjectTask' class need to extend on 'Task'. Additionally, our program need to identify whether the user command is in a specific pattern of project task or not. If so, our program will add this project task to each module's project task list, as well as the whole task list. After that, the storage function will store this task to the duke.txt file. When deleting a project subtask, the task list and txt file also need update. What's more, our product will confirm with users whether you want to add this project subtask if there is no such module in your timetable, which makes this feature more reasonable and logic.
    - [Pull Request](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/31)
    - [Pull Request](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/92)
- Added storage feature of modules
    - What is does: stores added module to a local module.txt file.
    - Justification: This feature helps our users to save their schedule. They do not need to re-add their modules to the timetable because our product will read from the local txt file to get all the information about the time slots.
    - [Pull Request](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/37)
- Added `addModule` and `deleteModule` feature
    - What is does: allows users to add or delete modules from their time table.
    - Justification: This feature can help our users to better plan their own timetable and add the timeslots of lecture, tutorial, or lab into their timetable.
    - Highlights: When adding or deleting a module, the module.txt file need to be updated.
    - [Pull Request](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/13)
- Added `Command` class
    - What is does: improves the whole structure.
    - Justification: The `Parser` class will take the user command and pass it to the `Command` class to call all the corresponding method.
    - [Pull Request](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/35)
- Added `Ui` class
    - What is does: print help, hello and goodbye messages.
    - Justification: The `Ui` class will print out hello message when a user launch our product, print goodbye message if a user enter in `exit` command, and print out help list when a user enter in `help` command. It will also print out the exception message if the user command does not follow specific patterns. 
    - [Pull Request](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/30)
- Modified exceptions
    - What is does: removes redundant exceptions to improve our code quality.
    - Justification: There were some redundant exception class in our program, I removed them and replaced with Java's built in exceptions like `NoSuchElementException`.
    - [Pull Request](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/101)
- Added Unit tests 
    - What is does: adds tests to examine the correctness of exceptions.
    - [Pull Request](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/106)
    - [Pull Request](https://github.com/AY2021S1-CS2113T-F11-2/tp/pull/98)
  
Contributions to the DG:
- [Testing Guide](https://demo.codimd.org/YxNfR9oUT5eDWl27gokPxg)
- Feature: Add a task
- Feature: Delete a task
- Feature: Print task list
- Feature: View time table

Review/mentoring contributions:
- Help my teammates to fix bugs.
- Improve the code quality and make the code more SLAP.

Contributions beyond the project team:
- Provide my idea to the whole product and help to distribute all the tasks.