package org.mobcom.server.api;

import org.mobcom.server.lib.MenuItem;
import org.mobcom.server.service.MenuService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/hello-world")
public class HelloWorld {

    private MenuService menuService = new MenuService();

    @GET
    @Produces("text/plain")
    public String getClichedMessage() {

        return "Hello, World!";
    }

    @GET
    @Path("/lala")
    public Response getMenu() {
        try {
            List<MenuItem> menuItem = menuService.getMenu();
            return Response.ok(menuItem).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }
}