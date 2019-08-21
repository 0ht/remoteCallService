package com.oht.sample;

import java.net.InetAddress;
import java.net.URL;
import java.util.TreeMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
 
@Path("/")
public class EndPoint {
 
	@Path("/call")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TreeMap<String, Object> getParameterAsURL(@QueryParam("url") final URL remoteUrl, @Context HttpHeaders requestHeaders) throws Exception {
		
		System.out.println("URL = " + remoteUrl);
		
		//Map for response JSON
		TreeMap<String, Object> responseMap  = new TreeMap<String, Object>();
		//Map for local address/hostname Map
		TreeMap<String, Object> localInfoMap = new TreeMap<String, Object>();
		
			InetAddress ia = InetAddress.getLocalHost();
	        String IpAddr = ia.getHostAddress();                                            
	        String HostName = ia.getHostName();
	        MultivaluedMap<String, String> reqHeaderMap = requestHeaders.getRequestHeaders();
	        
	        localInfoMap.put("IpAddr", IpAddr);
	        localInfoMap.put("HostName", HostName);
	        localInfoMap.put("Headers", reqHeaderMap);

	        if ( remoteUrl == null) {
	        	responseMap.putAll(localInfoMap);
	        } else {
	        	responseMap.put("local", localInfoMap);
	        	responseMap.put("remote", HttpClient.sendRequest(remoteUrl));
	        }
		return responseMap;
	}
}