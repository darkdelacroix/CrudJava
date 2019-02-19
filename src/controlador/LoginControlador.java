package controlador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import entities.*;


public class LoginControlador {
	
	
	
	public Boolean login(String usuario,String password) {
		URL url;
		try {
			url = new URL("http://localhost/CutreLogin/login.php");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			//con.setRequestProperty("Content-Type", "application/json");
		    //con.setRequestProperty("Content-Type", "application/x-www-form-url-encoded");
			   
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			//para la peticion post, para añadir parametros
			Map<String, String> parameters = new HashMap<>();
			parameters.put("usuario",usuario);
			parameters.put("clave",password);
			con.setDoOutput(true);
			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			String params = ParameterStringBuilder.getParamsString(parameters);  
			out.writeBytes(params);
			out.flush();
			out.close();
			/*URI
			 URI.Builder params = new Uri.Builder()
//                   .appendQueryParameter("_token", "9GrZM0aoeRuqGHuAlUxli7D7qB2hqfAjkkDmhSqI")
                   .appendQueryParameter("usuario", usuario)
                   .appendQueryParameter("clave", password);
//
           BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
           bw.write(params.build().getEncodedQuery());
           bw.flush();
           bw.close();*/
          
			BufferedReader in = new BufferedReader(
					  new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer content = new StringBuffer();
					while ((inputLine = in.readLine()) != null) {
					    content.append(inputLine);
					}
					in.close();
					
					con.disconnect();
					//para parsear el json usamos un package de internet 
			
					JSONObject obj = new JSONObject(content.toString());
					
					Boolean exito = obj.getBoolean("estado");
					return exito;
							
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
