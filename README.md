# password_validator
Project to check security of a user-supplied password as per requirements laid out in Digital-Identity-Guidelines released by NIST in June 2017.
The project prompts the user to enter the password at STDIN and checks if it conforms to the below standards:
1) Password must have minimum 8 characters
2) Password can have maximum 64 characters
3) Password must contain only ASCII values

Apart from these, the project also allows users to validate if the password is a common password. The reference for common-passwords has been taken from Common Password List(https://raw.githubusercontent.com/danielmiessler/SecLists/master/Passwords/Common-Credentials/10-million-password-list-top-1000000.txt) and has been saved as file: 'weak_password_list.txt'.
The project allows users to use their own list of common-passwords. They can be concatenated with the existing file or alternate files can be used  provided:
1) the file is TXT file in newline delimited format
2) the name of the file must be 'weak_password_list.txt'

## Prerequisites
A linux or Windows or Mac OS system with the following
1. OpenJDK version 11
2. Apache Maven 3.6.0 

## Build & Run
1. Clone the git repo to your local machine or download the project as Zip
2. Open Terminal/Command-Prompt and navigate to folder password_validator
3. Execute the Maven command to clean and package the project: 
mvn clean package
4. A new folder 'target' will be created which houses the JAR file for the project.
5. The project requires that the JAR file and the 'weak_password_list.txt' files should be in the same folder. Execute the command to copy the JAR file to the current directory: 
mv target/password_validator-1.0-SNAPSHOT.jar .
6. Execute the below command to start the program: 
java -jar password_validator-1.0-SNAPSHOT.jar
7. A prompt 'Enter the password string: ' is visible on the screen for entering the password.
8. Enter the string and press Enter.
9. The program will execute and if no errors are encountered, success message is displayed on the screen.

## Running Tests
The project contains test-cases developed using Junit framework.
use the below command to execute the test-cases: 
mvn test



## Author

| Name | Email Address |
| --- | --- |
| Shashank Sharma | sh.shashank@gmail.com |
