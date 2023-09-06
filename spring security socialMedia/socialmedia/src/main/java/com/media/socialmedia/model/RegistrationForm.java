package com.media.socialmedia.model;

import lombok.Data;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String repeatPassword;

    public boolean isValid() {
        return password.equals(repeatPassword);
    }
    
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for repeatPassword
    public String getRepeatPassword() {
        return repeatPassword;
    }

    // Setter for repeatPassword
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
