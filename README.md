README for Project: Board Game Store Management System (LeiGames)
Introduction

This project involves developing a system to support the management of a board game store. The system will manage the sale and rental of board games, as well as provide café services. This specific iteration focuses on essential functionalities related to member management, game inventory, and game rentals.
Project Objectives

    Objective: Apply Object-Oriented Analysis and Design (OOAD) methods to develop a system for managing board games, including memberships, inventory, and rentals.
    Incremental Development: The project will be developed iteratively, with the first increment addressing essential functionalities.

Deliverables

    Object-Oriented Analysis (OOA):
        Domain Model: Diagram representing the key concepts and their relationships within the domain.
        Use Case Diagram: Diagram showing the interactions between actors and the system.
        Sequence Diagrams: Diagrams depicting the sequence of interactions for each use case.
        Operation Contracts: Detailed descriptions of operations in the sequence diagrams.

    Object-Oriented Design (OOD):
        Interaction Diagrams: Diagrams illustrating the interactions between objects for each use case.
        Class Diagram: Diagram representing the system's classes, their attributes, methods, and relationships.



README for Project: Digital Music Manager and Player (LeiTunes)
Project Overview
 The application serves as a digital music manager and player.

Key Features:

    Music Library Management: Organize and view your digital music collection.
    Playlist Creation: Create and manage custom playlists.
    Playback Functionality: Play music directly from the library or playlists.

Components:

    GUIClient: A graphical user interface that allows interaction with the music library and playlists, including functionalities like viewing and playing music.
    SimpleClient: A command-line client that demonstrates core functionalities of the music manager for testing and basic operations.

Running the Project

To run the project, follow these steps:

    Import the Project:
        Open Eclipse.
        Go to File -> Import -> Existing Projects into Workspace.
        Select the project folder and import it.

    Verify Dependencies:
        Ensure that the lib folder contains the necessary libraries:
            SWT: Download from Eclipse SWT and place swt.jar in the lib folder.
            mp3agic: Download from mp3agic and place the .jar file in the lib folder.

    Refresh the Project:
        Right-click on the project folder in Eclipse and select "Refresh" to update the project.

    Run the Application:
        To launch the GUIClient:
            Right-click on GUIClient.java in the src folder.
            Select Run As -> Java Application.
        To run the SimpleClient:
            Right-click on SimpleClient.java in the src folder.
            Select Run As -> Java Application.

    Interacting with the Application:
        GUIClient: Follow the on-screen instructions to view and manage your music library and playlists.
        SimpleClient: Use the command-line interface to test basic functionalities.

    Check for Errors:
        If you encounter any issues, check the "Problems" tab in Eclipse.
        Ensure that all libraries are correctly configured in the build path. Right-click the project, go to Build Path -> Configure Build Path, and make sure the necessary libraries are included.
        
