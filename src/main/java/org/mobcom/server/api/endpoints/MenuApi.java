package org.mobcom.server.api.endpoints;

import org.mobcom.server.lib.MenuItem;
import org.mobcom.server.service.MenuService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/menu")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ "application/json" })
public class MenuApi {

    private MenuService menuService = new MenuService();

    @GET
    public Response getMenu() {
        try {
            List<MenuItem> menuItem = menuService.getMenu();
            return Response.ok(menuItem).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }

    @GET
    @Path("/{menuItemId}")
    public Response getMenuItem(@PathParam("menuItemId") String menuItemId) {
        try {
            MenuItem menuItem = menuService.getMenuItem(menuItemId);
            return Response.ok(menuItem).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }


}
