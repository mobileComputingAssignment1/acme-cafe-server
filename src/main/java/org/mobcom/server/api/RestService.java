package org.mobcom.server.api;

import org.glassfish.jersey.server.ResourceConfig;
import org.mobcom.server.api.endpoints.*;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/v1")
public class RestService extends ResourceConfig {

    public RestService() {
        register(HelloWorld.class);
        register(MenuApi.class);
        register(UsersApi.class);
        register(VouchersApi.class);
        register(OrdersApi.class);
    }
}