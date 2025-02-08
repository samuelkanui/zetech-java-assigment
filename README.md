## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## car Rental System

## Overview
The Car Rental System is a Java-based application that allows users to interact with a rental agency. It allows you to view available cars, add new customers, rent cars to customers, and view active rentals. The system is designed with a simple graphical user interface (GUI) using the Swing library, providing an easy-to-use and interactive experience.

## Features
View Available Cars: Displays a list of all cars that are currently available for rent.
View Customers: Displays a list of all customers in the system.
Add Customer: Allows you to add a new customer by providing their name and phone number.
Rent a Car: Rent a car to a customer for a specified number of days and calculate the total rental price.
View Active Rentals: Displays a list of all active car rentals with details such as car ID, customer ID, rental days, and total price.

 ## Technologies Used
Java: The application is written in Java.
Swing: The GUI is built using Java's Swing library.
Java Time API: Used for handling dates in the rental transactions.
DefaultTableModel: Used to display data in a tabular format within the GUI.

## Installation and Setup
Prerequisites
Java Development Kit (JDK) 8 or higher.
Steps to run the application
Clone or Download the repository to your local machine.
Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).
Compile and run the CarRentalSystemGUI class.
## How to Use the Application
Start the Application: The application starts with a welcome screen where you can click "Start" to proceed.
Main Menu: The main menu will display several options:
Show Available Cars: Displays a list of cars available for rent.
Show Customers: Displays a list of customers in the system.
Add Customer: Add a new customer by entering their name and phone number.
Rent a Car: Rent a car to a customer by entering the car ID, customer ID, and rental days.
Show Active Rentals: Displays a list of active car rentals.
Data Interaction: Depending on your action, the corresponding information will be displayed in the table or a dialog box.
## Example Screenshots
Welcome Screen: A greeting message and "Start" button to begin.
![alt text](<images/Screenshot 2025-02-08 210304.png>)

Main Menu: A menu with buttons to perform the actions such as viewing available cars or renting a car.
![alt text](<images/Screenshot 2025-02-08 213312.png>)
Table Views: Information displayed in a table format, such as available cars, customers, or active rentals.
## Available cars
![alt text](<images/Screenshot 2025-02-08 210616.png>)
## Available customers
![alt text](<images/Screenshot 2025-02-08 211111.png>)
## how to add customers
![alt text](<images/Screenshot 2025-02-08 211149.png>)
## The total of rented cars and amount
![alt text](<images/Screenshot 2025-02-08 211558.png>)
## How to select the car the customer want to rent
![alt text](<images/Screenshot 2025-02-08 211330.png>)

## Code Structure
The code is structured in the following way:

Classes:
Car: Represents a car with attributes such as car ID, make, model, daily rate, and availability.
Customer: Represents a customer with a customer ID, name, and phone number.
Rental: Represents a rental transaction with car ID, customer ID, start date, rental days, and total price.
RentalAgency: Manages the cars, customers, and rentals. Provides methods to add cars, customers, rent cars, and fetch data for available cars, customers, and active rentals.
CarRentalSystemGUI: Contains the main GUI logic, creating the user interface and handling button actions for displaying data and interacting with the RentalAgency.
Main Method:
The main method initializes the GUI and begins the application. The SwingUtilities.invokeLater() method is used to ensure that the GUI is created on the Event Dispatch Thread (EDT).
## This are the steps of running this code and navigating through the directories
![alt text](<images/Screenshot 2025-02-08 214434.png>)

## License
This project is open source and available under the MIT License.

## Contributing
Contributions to the project are welcome. If you want to add features, fix bugs, or improve the code, feel free to submit a pull request. Please make sure to follow the coding conventions and include appropriate tests.

## Future Enhancements
Extended Features: Implement a return car functionality, allow users to view detailed rental history, and implement a feature for payment processing.
Persistence: Save data to a database or file to retain the information between application runs.
Advanced Validation: Add more input validation and error handling for better user experience.

Thank you for using the Car Rental System!
