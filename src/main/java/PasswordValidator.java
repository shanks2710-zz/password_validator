import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PasswordValidator {
    //Variable declaration
    // Variable to store list of common passwords
    static List<String> weakPasswordList = new ArrayList<String>();

    // Variable for logger for this file
    // Logger
    private final static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // Main method for the class
    public static void main(String[] args) {

        // Set level of logging for the application
        log.setLevel(Level.SEVERE);

        // Initial log message
        log.log(Level.INFO, "Entered main() method of PasswordValidator class");

        // Read weak passwords from newline delimited file and populate a data-structure
        weakPasswordList = importWeakPasswordList();

        // Read the password entered on console
        System.out.println("Enter the password string: ");
        Scanner s = new Scanner(System.in);
        String pwdStr = s.nextLine();

        // Check if password is blank
        if(checkBlankPassword(pwdStr)){
            System.out.println("Error -> Password cannot be blank");
            log.log(Level.INFO, "Exiting main() method of PasswordValidator class");
            return;
        }

        // Check if password has non-ASCII characters
        boolean isAscii = pwdStr.chars().allMatch(ch -> ch<128 );
        if(!isAscii){
            pwdStr=replaceNonAsciiCharacters(pwdStr);
            System.out.println(pwdStr+" -> Error: Invalid Characters");
            log.log(Level.INFO, "Exiting main() method of PasswordValidator class");
            return;
        }

        // Check if password has minimum 8 characters and maximum 64 characters
        if(checkMinCharsInPassword(pwdStr)){
            System.out.println(pwdStr+" -> Error: Too Short!");
            log.log(Level.INFO, "Exiting main() method of PasswordValidator class");
            return;
        }

        // Check if password has maximum 64 characters
        if(checkMaxCharsInPassword(pwdStr)){
            System.out.println(pwdStr+" -> Error: Too Long!");
            log.log(Level.INFO, "Exiting main() method of PasswordValidator class");
            return;
        }

        // Check if password is a common password
        if(checkPasswordStrength(pwdStr)){
            System.out.println(pwdStr+" -> Error: Too Common!");
            log.log(Level.INFO, "Exiting main() method of PasswordValidator class");
            return;
        }

        log.log(Level.INFO, "Exiting main() method of PasswordValidator class");

    }


    // Method to import list of weak passwords from newline delimited file
    public static List<String> importWeakPasswordList(){
        log.log(Level.INFO, "Inside importWeakPasswordList() method of PasswordValidator class");
        try {
            Scanner s = new Scanner(new FileReader("weak_password_list.txt"));
            while(s.hasNextLine()){
                weakPasswordList.add(s.nextLine());
            }
        }catch(FileNotFoundException e){
            System.out.println("Exception occurred while reading weak-password-list. Please check the file and try again!");
            log.log(Level.SEVERE, "Exception occurred while importing weak-password-list from file: "+e.getMessage());    e.printStackTrace();
        }
        return weakPasswordList;
    }

    // Method to check if password is blank
    public static boolean checkBlankPassword(String password){
        log.log(Level.INFO, "Inside checkBlankPassword() method of PasswordValidator class");
        if((password.isEmpty() || password.length()==0)){
            return true;
        }
        return false;
    }

    // Method to replace all non-ASCII characters with '*' character
    public static String replaceNonAsciiCharacters(String password){
        log.log(Level.INFO, "Inside replaceNonAsciiCharacters() method of PasswordValidator class");
        StringBuilder pwdStrBuild = new StringBuilder();
        for(char val : password.toCharArray()){
            if(val > 127){
                val='*';
            }
            pwdStrBuild.append(val);
        }
        return pwdStrBuild.toString();
    }

    // Method to check minimum number of characters in password
    public static boolean checkMinCharsInPassword(String password){
        log.log(Level.INFO, "Inside checkMinCharsInPassword() method of PasswordValidator class");
        if(password.length()<=8){
            return true;
        }
        return false;
    }

    // Method to check maximum number of characters in password
    public static boolean checkMaxCharsInPassword(String password){
        log.log(Level.INFO, "Inside checkMaxCharsInPassword() method of PasswordValidator class");
        if(password.length()>64){
            return true;
        }
        return false;
    }

    // Method to check if password is a weak password
    public static boolean checkPasswordStrength(String password){
        log.log(Level.INFO, "Inside checkPasswordStrength() method of PasswordValidator class");
        if(weakPasswordList.contains(password)){
            return true;
        }
        return false;
    }
}
