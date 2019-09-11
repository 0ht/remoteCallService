package com.oht.sample;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;


@Path("/")
public class Snoop {
 
	@Path("Snoop")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> get(
			@Context HttpHeaders requestHeaders, 
			InputStream requestBody
			) throws Exception {
	
		BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody));
		StringBuilder bodytext = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
        	bodytext.append(line);
        }
        reader.close();
	
		System.out.println("requestHeaders == " + requestHeaders.getRequestHeaders());
		System.out.println("requestBody    == " + bodytext);
		HashMap<String, Object>reqinfoMapEach = new HashMap<String, Object>();
		
		InetAddress ia = InetAddress.getLocalHost();
        String IpAddr = ia.getHostAddress();                                            
        String HostName = ia.getHostName();
		Map<String, List<String>> reqHeaderMap = requestHeaders.getRequestHeaders();
		
		reqinfoMapEach.put("RequestHeaders", reqHeaderMap);
		reqinfoMapEach.put("RequestBody", bodytext);
        reqinfoMapEach.put("Host ip address", IpAddr);
        reqinfoMapEach.put("HostName", HostName);
		
		return reqinfoMapEach;
	}
	
	@Path("HostName")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> post(
			@Context HttpHeaders requestHeaders, 
			InputStream requestBody
			) throws Exception {
		
		return get(requestHeaders, requestBody);
	}
	
	@Path("HostName")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public HashMap<String, Object> put(
			@Context HttpHeaders requestHeaders, 
			InputStream requestBody
			) throws Exception {
		
		return get(requestHeaders, requestBody);
	}
	
}