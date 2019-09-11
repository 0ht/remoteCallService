package com.oht.sample;

import java.net.URL;
import java.util.HashMap;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
 
public class HttpClient {
	
	public static HashMap<String, Object> sendRequest(URL url) {
		
		System.out.println("calling from client...");
		
		final Response response = ClientBuilder.newClient() //
            .target(url.toString()) // 実行するWeb APIのエントリポイント
            //.path("/hello/text") // リクエストを投げるURLのパス部分
            //.request(MediaType.APPLICATION_JSON) // 受け入れ可能なレスポンス形式(HTTPヘッダでいうAcceptに相当)
            .request()
            // application/x-www-form-urlencoded形式でデータを作成(HTTPヘッダでいうContent-Typeに相当)し、リクエストを送信.
            .get();
		
		MultivaluedMap<String, Object> replyHeader = response.getHeaders();
		
	    HashMap<String, Object> replyMap = new HashMap<String, Object>();
	    replyMap.put("URL", url);
	    replyMap.put("Headers", replyHeader);
	    
		return replyMap;
	}
}