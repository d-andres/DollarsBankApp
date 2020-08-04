# DollarsBankApp
A simple console banking application created to demonstrate skill in Java fundamentals, MySQL, and JDBC. 

## Objectives 
1. Use layered architecture (abstracted files under SOLID principles) to make an MVC banking application.
2. Create a user login system.
3. Once logged in, have a user menu display in console.
4. Allow user to make:
  a. Deposit
  b. Withdrawl
  c. Funds Transfer
  d. 5 recent transaction history
  e. Display customer information
  f. Sign out
5. Apply business logic to handle illegal operations in:
  a. login system
  b. Transactions (withdrawls / depsosits, etc.)
6. Use JDBC and DAO to connect user info to a database

## Database Setup
The application uses a mysql database. Run the [db_setup](https://github.com/d-andres/DollarsBankApp/blob/master/sql_queries/db_setup.sql) query to setup the schema. If you want to change the username, password, or URL to the database on the application, they can be set on [DatabaseConnectionUtil.java](https://github.com/d-andres/DollarsBankApp/blob/master/src/main/java/com/dollarsbank/utility/DatabaseConnectionUtil.java).

## Future updates
- More handling of illegal operations
- Stub generation
- File storage