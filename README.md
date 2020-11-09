# Duke project template

CEGMods is a desktop to manage NUS CEG's courses via a Command Line Interface (CLI). If you are an NUS CEG student and looking for an app to manage your course and project schedule, CEGMods can help you get all your tasks down in an organized manner.

## Setting up in Intellij

Prerequisites: JDK 11 (use the exact version), update Intellij to the most recent version.

1. **Configure Intellij for JDK 11**, as described [here](https://se-education.org/guides/tutorials/intellijJdk.html).
1. **Import the project _as a Gradle project_**, as described [here](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
1. **Verify the set up**: After the importing is complete, locate the `src/main/java/seedu/duke/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   > Task :compileJava
   > Task :processResources NO-SOURCE
   > Task :classes
   
   > Task :Duke.main()
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
   Now is 22:12
   Currently you don't have any event!
   
   Type `help` and press enter to let the execution to proceed.

## Build automation using Gradle

* This project uses Gradle for build automation and dependency management. It includes a basic build script as well (i.e. the `build.gradle` file).
* If you are new to Gradle, refer to the [Gradle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/gradle.html).

## Testing

### I/O redirection tests

* To run _I/O redirection_ tests (aka _Text UI tests_), navigate to the `text-ui-test` and run the `runtest(.bat/.sh)` script.

### JUnit tests

* A skeleton JUnit test (`src/test/java/seedu/duke/DukeTest.java`) is provided with this project template. 
* If you are new to JUnit, refer to the [JUnit Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/junit.html).

## Checkstyle

* A sample CheckStyle rule configuration is provided in this project.
* If you are new to Checkstyle, refer to the [Checkstyle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/checkstyle.html).

## CI using GitHub Actions

The project uses [GitHub actions](https://github.com/features/actions) for CI. When you push a commit to this repo or PR against it, GitHub actions will run automatically to build and verify the code as updated by the commit/PR.

## Documentation

`/docs` folder contains a skeleton version of the project documentation.

Steps for publishing documentation to the public: 
1. If you are using this project template for an individual project, go your fork on GitHub.<br>
   If you are using this project template for a team project, go to the team fork on GitHub.
1. Click on the `settings` tab.
1. Scroll down to the `GitHub Pages` section.
1. Set the `source` as `master branch /docs folder`.
1. Optionally, use the `choose a theme` button to choose a theme for your documentation.