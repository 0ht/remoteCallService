package com.oht.sample;

import java.io.StringReader;
import java.net.URL;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
public class HttpClient {
	
	public static HashMap sendRequest(URL url) {
		
		System.out.println("calling from client...");
		
		final Response response = ClientBuilder.newClient() //
            .target(url.toString()) // 実行するWeb APIのエントリポイント
            //.path("/hello/text") // リクエストを投げるURLのパス部分
            .request(MediaType.APPLICATION_JSON) // 受け入れ可能なレスポンス形式(HTTPヘッダでいうAcceptに相当)
            // application/x-www-form-urlencoded形式でデータを作成(HTTPヘッダでいうContent-Typeに相当)し、リクエストを送信.
            .get();
		
		HashMap replyString = response.readEntity(HashMap.class);
	    
		System.out.println("replyString = "+ replyString);
		
		return replyString;
	}
}