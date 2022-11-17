package ua.lviv.iot.ubetterwatch.entity.change_password;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ChangePassword {
    private String previousPassword;
    private String newPassword;
}
