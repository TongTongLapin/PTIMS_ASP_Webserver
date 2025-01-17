package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetItem {
	private String url;
	private StringBuilder sBuffer;
	private String json="";
	
	public GetItem(String url)
	{
		this.url = url;
	}
	
	public String getJSON()
	{
		 
         try {
        	 URL jsonURL = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)jsonURL.openConnection();
			sBuffer = new StringBuilder();
			 if(conn != null)
             {
                 conn.setConnectTimeout(20000);
                 conn.setUseCaches( false);

                 //데이터를 정상적으로 받으면
                 if(conn.getResponseCode() == HttpURLConnection.HTTP_OK ){
                     InputStreamReader isr
                             = new InputStreamReader(conn.getInputStream(),"UTF-8");
                     //BufferedReader로 변환시킴(InputStreamReader는 잘 안쓴다)
                     BufferedReader br =
                             new BufferedReader(isr);

                     while( true){
                         String line = br.readLine();
                         if(line == null)
                             break;
                         sBuffer.append(line);
                         System.out.println(sBuffer);
                     }
                     br.close();
                     conn.disconnect();
                 }
             }
             json = sBuffer.toString();
             System.out.println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			json = "";
		}
         
         return json;
	}
	
	
}
