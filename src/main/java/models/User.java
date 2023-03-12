package models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    private String username;
    private String email;
    private String confirmEmail;
    private String password;
    private String confirmPassword;
    private Boolean termsAndConditions;
    private Boolean newsletter;

}
