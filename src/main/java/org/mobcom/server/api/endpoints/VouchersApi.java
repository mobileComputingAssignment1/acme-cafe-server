package org.mobcom.server.api.endpoints;

import org.mobcom.server.lib.Voucher;
import org.mobcom.server.service.VouchersService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/vouchers")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class VouchersApi {

    private VouchersService vouchersService = new VouchersService();

    @GET
    public Response getAllVouchers() {
        try {
            List<Voucher> vouchers = vouchersService.getAllVouchers();
            return Response.ok(vouchers).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }


    @GET
    @Path("/{voucherId}")
    public Response getUser(@PathParam("voucherId") String voucherId) {
        try {
            Voucher voucher = vouchersService.getVoucher(voucherId);
            return Response.ok(voucher).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e).build();
        }
    }

}
