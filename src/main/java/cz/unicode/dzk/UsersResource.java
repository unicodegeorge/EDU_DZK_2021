package cz.unicode.dzk;

import cz.unicode.dzk.models.UserModel;
import cz.unicode.dzk.managers.SessionManager;
import cz.unicode.dzk.managers.UsersManager;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("users")
public class UsersResource {
    @Inject
    SessionManager sessionManager = new SessionManager();
    @Inject
    UsersManager usersManager = new UsersManager();

    @GET
    public Response getAllUsers() {
        return Response.ok(usersManager.getAllUsers()).build();
    }

    @POST
    @Path("register")
    public Response registerNewUser(UserModel user) {
        if (usersManager.registerUser(user)) {
            return Response.ok("User registered").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Path("login")
    public Response loginUser(UserModel user) {
        if (usersManager.checkCredentials(user.getUsername(), user.getPassword())) {
            if (sessionManager.logInUser(user)) {
                return Response.ok("User logged in.").build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    @Path("logout")
    public Response logOutUser() {
        if(sessionManager.logOffUser()){
            return Response.ok("user logged out!").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
