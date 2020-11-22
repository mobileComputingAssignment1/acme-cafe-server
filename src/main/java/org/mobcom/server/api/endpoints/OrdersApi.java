package org.mobcom.server.api.endpoints;

import org.mobcom.server.lib.Order;
import org.mobcom.server.lib.UserVoucher;
import org.mobcom.server.service.OrdersService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/orders")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class OrdersApi {

    private OrdersService ordersService = new OrdersService();

    @GET
    @Path("/{orderId}")
    public Response getOrder(@PathParam("orderId") String orderId) {
        try {
            Order order = ordersService.getOrder(orderId);
            return Response.ok(order).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }

    @POST
    public Response verifyOrder(Order order) {
        try {
            Order verifiedOrder = ordersService.verifyOrder(order);
            if (verifiedOrder == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("There were errors with the order submitted").build();
            }
            return Response.ok(verifiedOrder).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }

    @GET
    @Path("/{orderId}/confirm")
    public Response makeOrder(@PathParam("orderId") String orderId) {
        try {
            List<UserVoucher> vouchers = ordersService.makeOrder(orderId);
            return Response.ok(vouchers).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }

//    @POST
//    public Response generateReceipt(Order order) {
//        try {
//            User createUser = usersService.createUser(user);
//            return Response.ok(createUser).build();
//        } catch (Exception e) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
//        }
//        return null;
//    }

}
