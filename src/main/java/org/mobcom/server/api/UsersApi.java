package org.mobcom.server.api;

import org.mobcom.server.lib.User;
import org.mobcom.server.service.UsersService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ "application/json" })
public class UsersApi {

    UsersService usersService = new UsersService();

    @GET
    public Response getAllUsers() {
        try {
            List<User> users = usersService.getAllUsers();
            return Response.ok(users).build();
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


}
