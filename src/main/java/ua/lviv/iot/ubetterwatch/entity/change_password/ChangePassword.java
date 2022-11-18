package ua.lviv.iot.ubetterwatch.entity.change_password;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChangePassword {
    private String previousPassword;
    private String newPassword;

    public String getPreviousPassword() {
        return previousPassword;
    }

    public void setPreviousPassword(String previousPassword) {
        this.previousPassword = previousPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
