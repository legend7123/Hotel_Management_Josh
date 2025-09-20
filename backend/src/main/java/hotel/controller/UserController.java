package controller;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.List;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import service.UserService;
import model.User;
import util.Utils;
import dto.ResponseDTO;
import util.ResponseUtils;


public class UserController{
	private final UserService userService;
	private final Connection conn;

	public UserController(UserService userService,Connection conn){
		this.userService = userService;
		this.conn = conn;
	}

	/*==================================================*/

	//Register routes
	public void registerRoutes(HttpServer server){
		server.createContext("/user",this::handleUserPath);
		server.createContext("/user/",this::handleUserIdPath);
//		server.createContext("/user/loyalty",this::handleUserLoyalty);
	}

	/*====================================================*/

	//Handle METHOD for /user
	public void handleUserPath(HttpExchange exchange) throws IOException{
		String method = exchange.getRequestMethod();

		switch(method.toUpperCase()){
			case "GET" : displayAllUsers(exchange);
				     break;
			case "POST" : createUser(exchange);
				      break;
			case "PUT" : updateUser(exchange);
				     break;

		}
	}

	//Handle METHOD for /user/
	public void handleUserIdPath(HttpExchange exchange) throws IOException{
		String method = exchange.getRequestMethod();
		String path = exchange.getRequestURI().getPath(); // /user/1
    		String[] parts = path.split("/");
    		Long id = Long.parseLong(parts[2]);
/*
		if(method!=null && id!=0){
			switch(method.toUpperCase()){
				case "GET" : displayUserById(exchange,id);
					     break;
				//case "PUT" : updateUserLoyalty(exchange,id);
				//	     break
			}
		}
*/
		if(parts.length == 3 && method.toUpperCase().equals("GET")){
			displayUserById(exchange,id);
		}else if(parts.length == 4 && method.toUpperCase().equals("PUT")){
			handleUserLoyalty(exchange,parts);
		}
	}

	public void handleUserLoyalty(HttpExchange exchange,String[] parts) throws IOException{
	//	String method = exchange.getRequestMethod();
	//	String path = exchange.getRequestURI().getPath();
	//	String[] parts = path.split("/");

	//	if(parts.length<3){
	//		ResponseDTO response = new ResponseDTO(400,"Invalid URL");
	//		ResponseUtils.sendResponse(exchange,response);
	//		System.out.println("Invalid url...");
	//		return;
	//	}
		
		Long id = Long.parseLong(parts[2]);

		// Read query parameter
    		String query = exchange.getRequestURI().getQuery();  // loyalty=100
    		Map<String, String> params = parseQuery(query);
    		if (!params.containsKey("loyalty")) {
        		ResponseDTO response = new ResponseDTO(400, "Missing loyalty parameter");
        		ResponseUtils.sendResponse(exchange, response);
        		return;
    		}

		Double loyalty = Double.parseDouble(params.get("loyalty"));

		try(Connection conn = Utils.getConnection()){
			User user = userService.updateLoyalty(id,loyalty,conn);
			ResponseDTO response = new ResponseDTO(200,user);
			ResponseUtils.sendResponse(exchange,response);
			System.out.println("User loyalty updated");
		} catch(SQLException e){
    			System.out.println("error :" + e.getMessage());
    			ResponseDTO response = new ResponseDTO(500,e.getMessage());
			ResponseUtils.sendResponse(exchange,response);
		} catch(Exception e){
    			System.out.println("error :" + e.getMessage());
    			ResponseDTO response = new ResponseDTO(500,e.getMessage());
			ResponseUtils.sendResponse(exchange,response);
		}
	}

	/*=======================================================*/

	//GET /user
	public void displayAllUsers(HttpExchange exchange) throws IOException{
		System.out.println("Received request: " + exchange.getRequestMethod() + "" + exchange.getRequestURI());

		try(Connection conn = Utils.getConnection()){
			List<User> users = userService.getAllUsers(conn);
			//System.out.println("list of users : " + users);
			ResponseDTO response = new ResponseDTO(200,users);
			//System.out.println("Response : " + response);
			ResponseUtils.sendResponse(exchange,response);
		} catch(SQLException e){
    			System.out.println("error :" + e.getMessage());
    			ResponseDTO response = new ResponseDTO(500,e.getMessage());
			ResponseUtils.sendResponse(exchange,response);
		} catch(Exception e){
    			System.out.println("error :" + e.getMessage());
    			ResponseDTO response = new ResponseDTO(500,e.getMessage());
			ResponseUtils.sendResponse(exchange,response);
		}
	}


	//POST /user
	public void createUser(HttpExchange exchange) throws IOException{
		System.out.println("Received request: " + exchange.getRequestMethod() + " " + exchange.getRequestURI());

		// Read request body
    		InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
   	 	BufferedReader br = new BufferedReader(isr);
		StringBuilder buf = new StringBuilder();
    		String line;
    		while ((line = br.readLine()) != null) {
        		buf.append(line);
    		}
    		br.close();
    		String requestBody = buf.toString();
    		System.out.println("Request body: " + requestBody);

		// Simple JSON parsing (without Jackson)
		String username = extractJsonValue(requestBody, "username");
		String email = extractJsonValue(requestBody, "email");
		String phone = extractJsonValue(requestBody, "phone");

		// Validate required fields
		if(username == null || email == null || phone == null){
			ResponseDTO response = new ResponseDTO(500,"Missing paramters");
    			ResponseUtils.sendResponse(exchange,response);
			return;
		}


		try(Connection conn = Utils.getConnection()){
			User user = userService.createUser(username,email,phone,conn);
			ResponseDTO response = new ResponseDTO(201,user);
			ResponseUtils.sendResponse(exchange,response);
			System.out.println("User created successfully: " + user.getUsername());
		} catch(SQLException e){
			ResponseDTO response = new ResponseDTO(500,e.getMessage());
			ResponseUtils.sendResponse(exchange,response);
			System.out.println("Error creating user: " + e.getMessage());
		} catch(RuntimeException e){
			ResponseDTO response = new ResponseDTO(500,e.getMessage());
			ResponseUtils.sendResponse(exchange,response);
			System.out.println("Error creating user: " + e.getMessage());
		}
	}

	
	// /user - PUT
	public void updateUser(HttpExchange exchange) throws IOException{
		System.out.println("Received request: " + exchange.getRequestMethod() + " " + exchange.getRequestURI());
		
		//Read body
		InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(),"utf-8");
		BufferedReader br = new BufferedReader(isr);
		StringBuilder buf = new StringBuilder();
		String line;
		while((line = br.readLine()) != null){
			buf.append(line);
		}
		br.close();
		String requestBody = buf.toString();
		System.out.println("Request body: " + requestBody);

		//JSON parsing
		String idstr = extractJsonValue(requestBody,"id");
		String username = extractJsonValue(requestBody,"username");
		String email = extractJsonValue(requestBody,"email");
		String phone = extractJsonValue(requestBody,"phone");
		String loyaltystr = extractJsonValue(requestBody,"loyalty");	

		Long id = Long.parseLong(idstr);
		Double loyalty = Double.parseDouble(loyaltystr);

		if(id == null || username == null || email == null || phone == null || loyalty == null){
			ResponseDTO response = new ResponseDTO(500,"Missing parameters");
			ResponseUtils.sendResponse(exchange,response);
			System.out.println("params : " + idstr + " " + username + " " + email + " " + phone + " " + loyaltystr);
			System.out.println("Error updating user : Missing parameter...");
			return;
		}
	

		User user = new User(id,username,email,phone,loyalty);

		try(Connection conn = Utils.getConnection()){
			User updatedUser = userService.updateUser(user,conn);
			ResponseDTO response = new ResponseDTO(200,updatedUser);
			ResponseUtils.sendResponse(exchange,response);
			System.out.println("User updated successfully...");
		} catch(SQLException e){
			ResponseDTO response = new ResponseDTO(500,e.getMessage());
			ResponseUtils.sendResponse(exchange,response);
			System.out.println("Error updating user : " + e.getMessage());
		} catch(RuntimeException e){
			ResponseDTO response = new ResponseDTO(500,e.getMessage());
			ResponseUtils.sendResponse(exchange,response);
			System.out.println("Error updating user : " + e.getMessage());
		}
	}


	// /user/id - GET
	public void displayUserById(HttpExchange exchange,Long id) throws IOException{
			System.out.println("Received request: " + exchange.getRequestMethod() + " " + exchange.getRequestURI());

			try(Connection conn = Utils.getConnection()){
				User user = userService.getById(id,conn);
				ResponseDTO response = new ResponseDTO(200,user);
				ResponseUtils.sendResponse(exchange,response);
			} catch(SQLException e){
				System.out.println("error :" + e.getMessage());
	    			ResponseDTO response = new ResponseDTO(500,e.getMessage());
				ResponseUtils.sendResponse(exchange,response);
			} catch(RuntimeException e){
				System.out.println("error :" + e.getMessage());
    				ResponseDTO response = new ResponseDTO(500,e.getMessage());
				ResponseUtils.sendResponse(exchange,response);
			}
	}


/*	private vo
 *	id sendResponse(HttpExchange exchange, String response) throws IOException {
    		exchange.sendResponseHeaders(200, response.getBytes().length);
    		OutputStream os = exchange.getResponseBody();
    		os.write(response.getBytes());
    		os.close();
	}

	

	// Overload for sending User object as JSON (simple example)
	private void sendResponse(HttpExchange exchange, User user) throws IOException {
		String json = "{ \"id\": " + user.getId() + 
                  ", \"username\": \"" + user.getUsername() + "\"" + 
                  ", \"email\": \"" + user.getEmail() + "\"" + 
                  ", \"phone\": \"" + user.getPhone() + "\"" + 
                  ", \"loyalty\": " + user.getLoyalty() + " }";
    		sendResponse(exchange, json);
	}
*/
/*	private String extractJsonValue(String json, String key){
    		String pattern = "\"" + key + "\"\\s*:\\s*\"([^\"]*)\"";
    		java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
    		java.util.regex.Matcher m = r.matcher(json);
    		if (m.find()){
        		return m.group(1);
    		}
    		return null;
	}
*/
	private String extractJsonValue(String json, String key){
		// Match "key": "value" OR "key": value (number, boolean)
    		String pattern = "\"" + key + "\"\\s*:\\s*\"?([^\"]+?)\"?(,|})";
    		java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
    		java.util.regex.Matcher m = r.matcher(json);
    		if (m.find()){
        		return m.group(1).trim();
    		}
    		return null;
	}

	public static Map<String, String> parseQuery(String query) {
    		Map<String, String> map = new java.util.HashMap<>();
    if (query == null || query.isEmpty()) return map;

    String[] pairs = query.split("&");
    for (String pair : pairs) {
        String[] kv = pair.split("=");
        if (kv.length == 2) {
            map.put(kv[0], kv[1]);
        }
    }
    return map;
}



}
