# ProductLineOOP

# Project Title

ProductLineOOP


## Demonstration


## Documentation
[Link to Java-Docs](file:///C:/Users/alann/OneDrive/OOP%20Class/ProductLineOOPtest/doc/index.html)

## Diagrams


## Getting Started
Prepare Scene

Develop FXML in scene builder to specific expectations

add Database functionality to a button press

Style the GUI with CSS



## Built With
IDE: IntelliJ Idea

DataBase: H2

FXML: Scene Builder

Styling: CSS



## Contributing
Title: OOP Project

Author: Scott Vanselow

Date: 2019

Code version: 1.0

Availability: https://sites.google.com/site/profvanselow/course/cop-3003/oop-project?authuser=0


## Author
Alan Norman


## License


## Acknowledgments


## History
Week 1

Create JavaFX project. See IntelliJ page and IntelliJ Help

Share to private repository on GitHub. See GitHub page.

Create README. Details in Documentation Expectations at bottom of page. 

Week 2

Add a tab view with three tabs: Product Line, Produce, and Production Log

Add a CSS file with some code TutorialsPoint Reference

Quality expectations: see bottom of page

Style expectations: see bottom of page

Documentation expectations: see bottom of page

Week 3

In the Product Line tab

In the AnchorPane

Add a 2x3 GridPane

Add a Label and text field for Product Name in row 0, columns 0 and 1

Add a Label and text field for Manufacturer in row 1, columns 0 and 1

Add a Label and ChoiceBox for Item Type in row 2, columns 0 and 1

Add a Button that says Add Product

Add an event handler to the button click event. For now, just have it print to the console (System.out.println)

Add a Label and a Table View for Existing Products

In the Produce tab

In the AnchorPane

Add a Label and ListView for Choose Product

Add a Label and ComboBox (data type String) for Choose Quantity

Add a Button that says Record Production

For now, just have the button print to the console (System.out.println)

In the Production Log tab

In the AnchorPane

Add a TextArea

Week 4

Install database software if necessary

Create database in a res folder at same level as src folder

Connect to database 

You will get a FindBugs error for not having a password or including the password. Eventually you will retrieve the password from another file but you don't need to worry about this for now. 

Make sure to commit and push your res folder to the remote repository.

Week 5

Use a controller for almost all code. 

In the Product Line tab, for the Add Product button event handler, add code to insert a product into the database

You could hard code this statement or build from the user interface, like INSERT INTO Product(type, manufacturer, name) VALUES ( 'AUDIO', 'Apple', 'iPod' );

See JDBC - Insert Records Example and JDBC PreparedStatement

In the Produce tab, for the ComboBox

Populate with values 1-10 in an initialize method in the Controller

To allow the users to enter other values in the combobox, call the method setEditable(true);

To show a default value, call the method getSelectionModel().selectFirst();

Prepare for submission 


## Key Programming Concepts Utilized
Object-Oriented Programming
Scene Control
JavaFX key concepts
DataBase functionality
