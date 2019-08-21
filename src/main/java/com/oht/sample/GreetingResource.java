package com.oht.sample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("greeting/{name}")
public class GreetingResource {

    @GET
    @Produces("text/plain")
    public String sayHello(@PathParam("name") String name) {
        return "helloooooo," + name + "!";
    }
}