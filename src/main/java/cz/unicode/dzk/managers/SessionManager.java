package cz.unicode.dzk.managers;

import cz.unicode.dzk.models.UserModel;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class SessionManager implements Serializable {
    UserModel currentlyLoggedUser;

    public boolean logInUser(UserModel user) {
        if(currentlyLoggedUser == null) {
            currentlyLoggedUser = user;
            return true;
        } else {
            return false;
        }
    }

    public boolean logOffUser() {
        if(currentlyLoggedUser == null) {
            return false;
        } else {
            currentlyLoggedUser = null;
            return true;
        }
    }
}
