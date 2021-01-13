package cz.unicode.dzk.managers;

import cz.unicode.dzk.models.UserModel;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class UsersManager {
    ArrayList<UserModel> users = new ArrayList<>();
    int currentId = 0;
    public ArrayList<UserModel> getAllUsers() {
        return this.users;
    }

    public boolean registerUser(UserModel newUser) {
        if (!doesUserExist(newUser.getUsername())) {
            newUser.setId(currentId);
            currentId++;
            this.users.add(newUser);
            return true;
        }
        return false;
    }
    public boolean checkCredentials(String username, String password) {
        return users.stream()
                .filter(foundUser -> username.equals(foundUser.getUsername()) & password.equals(foundUser.getPassword()))
                .findAny()
                .orElse(null) != null;
    }
    public boolean doesUserExist(String username) {
        return users.stream()
                .filter(foundUser -> username.equals(foundUser.getUsername()))
                .findAny()
                .orElse(null) != null;
    }
}
