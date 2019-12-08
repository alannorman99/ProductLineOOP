package sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {

  private String name;
  private String username;
  private String password;
  private String email;

  /**
   * Employee constructor for basic employee.
   * @param name employee name
   * @param password employee password
   */
  public Employee(String name, String password) {

    if (name.contains(" ")) {
      this.name = name;
      if (checkName(name)) {
        setUsername(name);
        setEmail(name);
      }


    } else {
      this.name = name;
      this.username = "default";
      this.email = "user@oracleacademy.Test";

    }

    if (isValidPassword(password)) {
      this.password = password;
    } else {
      this.password = "pw";
    }


  }

  private boolean isValidPassword(String password) {
    String specialChars = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?1234567890";
    char currentCharacter;

    boolean upperCasePresent = false;
    boolean lowerCasePresent = false;
    boolean specialCharacterPresent = false;

    for (int i = 0; i < password.length(); i++) {
      currentCharacter = password.charAt(i);
      if (Character.isUpperCase(currentCharacter)) {
        upperCasePresent = true;
      } else if (Character.isLowerCase(currentCharacter)) {
        lowerCasePresent = true;
      } else if (specialChars.contains(String.valueOf(currentCharacter))) {
        specialCharacterPresent = true;
      }
    }

    return
        upperCasePresent && lowerCasePresent && specialCharacterPresent;
  }

  /**
   * Checks if name entered contains a space.
   * @param name name to check
   * @return returns true or false based on if space is found
   */
  private boolean checkName(String name) {
    Pattern pattern = Pattern.compile("\\s");
    Matcher matcher = pattern.matcher(name);
    return matcher.find();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  /**
   * Set username to resemble specific format.
   * @param username username to be formatted
   */
  private void setUsername(String username) {
    String firstInitial = username.substring(0, 1);
    int index = username.indexOf(" ");
    String lastName = username.substring(index + 1);
    this.username = firstInitial.toLowerCase() + lastName.toLowerCase();
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  /**
   * Set the email to a specific format.
   * @param email email to format
   */
  private void setEmail(String email) {
    int index = email.indexOf(" ");
    String firstName = email.substring(0, index);
    String lastName = email.substring(index + 1);
    this.email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@oracleacademy.Test";


  }

  @Override
  public String toString() {
    return "Employee Details" + '\n'
        + "Name : " + name + '\n'
        + "Username : " + username + '\n'
        + "Email : " + email + '\n'
        + "Initial Password : " + password;

  }
}
