package datvtp.dtos;

import java.io.Serializable;

public class Tbl_UserErrorObject implements Serializable {

    private String emailError;
    private String passwordError;

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

}
