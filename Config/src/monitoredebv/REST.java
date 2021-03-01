package monitoredebv;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class REST{

    public void main(String[] args){

		try {
			sendPOST(null, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }

    public void sendPOST(String teilen, String teilen_params) throws IOException {

		try {

		final String POST_PARAMS = teilen_params; 
		
		URL obj = new URL(teilen);
		//Öffnen der Connection
	 	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	 	//POST-Methode
	 	con.setRequestMethod("POST");
	 	//Properties
		con.setRequestProperty("Content-Type", "application/json");
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");

	 	con.setDoOutput(true);
	 	//ermöglicht Bytes an ein "beliebiges" Ziel zu senden 
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes("utf-8"));
	 	os.flush();
	 	

	 	//Antwortzeit berechnen in ms
	 	long start = System.currentTimeMillis();
	 	//Abruf des HTTP-Status
	 	int responseCode = con.getResponseCode();
	 	long end = System.currentTimeMillis();
		System.out.println("Status = " + responseCode);
		long zeit = end - start; 

		System.out.println("Antwortzeit = " + zeit + " ms");
		
	 	if (responseCode == HttpURLConnection.HTTP_OK) { 
	 		BufferedReader in = new BufferedReader(new InputStreamReader(
	 				con.getInputStream()));
	 		String inputLine;
	 		StringBuffer response = new StringBuffer();

	 		while ((inputLine = in.readLine()) != null) {
	 			response.append(inputLine);
	 		}
	 		in.close();	

	 		System.out.println(response.toString());
	 	} else {
	 		System.out.println("POST request not worked");
		 }
		 
	 	//schließt Connection
		 os.close();
		 
		 //INSERT
	
	 }catch (MalformedURLException e) {

        e.printStackTrace();

      } catch (IOException e) {

        e.printStackTrace();
	  }	    
	}

		 	
} 
    
    
