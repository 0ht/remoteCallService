package com.oht.sample;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;


@Path("/transfer")
public class HostName {
 
	@Path("HostName")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> res(@Context HttpHeaders requestHeaders) throws Exception {
	
		System.out.println("requestHeaders == " + requestHeaders.getRequestHeaders());
		HashMap<String, Object>reqinfoMapEach = new HashMap<String, Object>();
		
		InetAddress ia = InetAddress.getLocalHost();
        String IpAddr = ia.getHostAddress();                                            
        String HostName = ia.getHostName();
		Map<String, List<String>> reqHeaderMap = requestHeaders.getRequestHeaders();
		reqinfoMapEach.putAll(reqHeaderMap);
		
        reqinfoMapEach.put("IpAddr", IpAddr);
        reqinfoMapEach.put("HostName", HostName);
        
        System.out.println("URL == " + reqinfoMapEach);
        
		String res = "Called.";
		
		return reqinfoMapEach;
	}
}