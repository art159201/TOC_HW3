import java.net.*;
import java.io.*;
import org.json.*;
import java.util.regex.*;


public class TocHw3

{

	

	public static void  main(String[] args) throws IOException, JSONException
	{
		
		
		String url=new String(args[0]);
		String sect = new String(args[1]);
		String road = new String(args[2]);
		int year = (int)(Integer.parseInt(args[3])*100);
		
		
        int count=0,i=0;
        //String temp;
        long  sum=0;
        
		URL pageUrl = new URL(url);
		URLConnection myconnection = pageUrl.openConnection();
		myconnection.connect();

		BufferedReader data = new BufferedReader(new InputStreamReader(pageUrl.openStream(),"UTF-8"));
		
		
		
		JSONTokener tok = new JSONTokener(data);
		JSONArray arr=new JSONArray(tok);
		
		
    	//System.out.println(arr.length());
		
		Pattern pattern=Pattern.compile(sect);
		Pattern pattern2=Pattern.compile(road);
		//Pattern pattern3=Pattern.compile(year);
		
		while(i<arr.length())
		{
			JSONObject obj =arr.getJSONObject(i);
			
			Matcher match1 =pattern.matcher(obj.getString("鄉鎮市區"));
			Matcher match2 =pattern2.matcher(obj.getString("土地區段位置或建物區門牌"));
			//System.out.println(obj.getInt("交易年月")/100);
			if(match1.find()&&match2.find()&&year<=obj.getInt("交易年月"))
			{
				sum+=obj.getInt("總價元");
				count++;
			
			}
			
			i++;
		}
		
		System.out.println(count);
		if(count>0)
		{
			System.out.println(sum/count);
			
			
		}
	
	
	
	}

}
