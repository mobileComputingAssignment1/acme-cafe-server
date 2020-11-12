package org.mobcom.server.api.endpoints;

import org.mobcom.server.lib.User;
import org.mobcom.server.service.UsersService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class UsersApi {

    private UsersService usersService = new UsersService();

    @GET
    public Response getAllUsers() {
        try {
            List<User> users = usersService.getAllUsers();
            return Response.ok(users).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }

    @GET
    @Path("/{userId}")
    public Response getUser(@PathParam("userId") String userId) {
        try {
            User user = usersService.getUser(userId);
            return Response.ok(user).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }

    @POST
    public Response createUser(User user) {
        try {
            User createUser = usersService.createUser(user);
            return Response.ok(createUser).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }

    @POST
    @Path("/login")
    public Response loginUser(User user) {
        try {
            User loginUser = usersService.loginUser(user);
            return Response.ok(loginUser).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }


}
