import org.junit.Assert;
import org.junit.Test;
import org.junit.Assert.*;

import java.util.List;

public class PasswordValidatorTest {

    // Test-case to test if file containing weak-passwords is imported
    @Test
    public void weakPasswordFileImportTest(){
        List<String> weakPasswordList = PasswordValidator.importWeakPasswordList();
        Assert.assertNotEquals(0,weakPasswordList.size());
    }

    // Test-case to test method that performs check if password is blank
    @Test
    public void blankPasswordTest(){
        Assert.assertTrue(PasswordValidator.checkBlankPassword(""));
        Assert.assertFalse(PasswordValidator.checkBlankPassword("test"));
    }

    // Test-case to test method that replaces non-ASCII characters with '*'
    @Test
    public void replaceNonAsciiCharactersTest(){
        Assert.assertEquals("****", PasswordValidator.replaceNonAsciiCharacters("भारत"));
        Assert.assertEquals("test",PasswordValidator.replaceNonAsciiCharacters("test"));
    }

    // Test-case to test method that checks minimum number of characters in the password
    @Test
    public void minCharactersPasswordTest(){
        Assert.assertTrue(PasswordValidator.checkMinCharsInPassword("test"));
        Assert.assertTrue(PasswordValidator.checkMinCharsInPassword("test1234"));
        Assert.assertFalse(PasswordValidator.checkMinCharsInPassword("test123456"));
    }

    // Test-case to test method that checks maximum number of characters in the password
    @Test
    public void maxCharactersPasswordTest(){
        Assert.assertFalse(PasswordValidator.checkMaxCharsInPassword("qwertyuioplkjhgfdsazxcvbnmmnbvcxzlkjhgfdsapoiuytrewq"));
        Assert.assertFalse(PasswordValidator.checkMaxCharsInPassword("qwertyuioplkjhgfdsazxcvbnmmnbvcxzlkjhgfdsapoiuytrewq123456789012"));
        Assert.assertTrue(PasswordValidator.checkMaxCharsInPassword("qwertyuioplkjhgfdsazxcvbnmmnbvcxzlkjhgfdsapoiuytrewq123456789012345"));
    }

    // Test-case to test method that checks minimum number of characters in the password
    @Test
    public void weakPasswordTest(){
        List<String> weakPasswordList = PasswordValidator.importWeakPasswordList();
        Assert.assertFalse(weakPasswordList.contains("Sharma123"));
    }

}
