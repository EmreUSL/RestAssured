package api.payload;

import lombok.Getter;
import lombok.Setter;

public class User {

    @Setter
    @Getter
    int id;
    @Setter
    @Getter
    String name;
    @Setter
    @Getter
    String firstName;
    @Setter
    @Getter
    String lastName;
    @Setter
    @Getter
    String email;
    @Setter
    @Getter
    String password;
    @Setter
    @Getter
    String phone;
    @Setter
    @Getter
    int userStatus = 0;



}
