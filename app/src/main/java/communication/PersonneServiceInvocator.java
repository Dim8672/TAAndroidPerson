package communication;

import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import business.Person;

/**
 * Created by dimitri.mella on 21.12.2016.
 */

public class PersonneServiceInvocator {

    public static String Base_URL = "http://157.26.114.206:8080/PersonnesRestServices";
    //public static String Base_URL = "http://ne-ege-h054-fte:8080/CRUDPersonnesServletsMLoginPlus/";
    //"http://www.anddev.org/images/tut/basic/getdatafromtheweb/loadme.txt"
    public static boolean proxy = false;

    @SuppressWarnings("finally")
    public static String sendHttpGet(String service,String param) {
        URL myURL = null;
        String urlTocall="";
        BufferedInputStream bis = null;
        String returnString = "";

        try {
            if(service!=null)urlTocall=Base_URL+service;
            if(param!=null)urlTocall+="?"+param;
            Log.v("URL called", urlTocall);

            myURL = new URL(urlTocall);
            URLConnection ucon = null;
            if (!proxy) {
                ucon = myURL.openConnection();
            }
            else {
                SocketAddress addr = new InetSocketAddress("proxy.he-arc.ch",8080);
                Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
                ucon = myURL.openConnection(proxy);
            }

            returnString = readStream(ucon.getInputStream());


            Log.i("OK R", returnString);

        } catch (Exception e) {

            returnString = e.getMessage();
            Log.i("KO R", returnString);
            return null;

        } finally {
            return returnString;
        }

    }

    private static String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static ArrayList<Person> serviceR(int id, String n, String p, String adr, String ville) {
        ArrayList<Person> list = new ArrayList<Person>();
        String param=null;
        if (id!=-1)
            try {
                param="id="+java.net.URLEncoder.encode(String.valueOf(id),"UTF-8");
				/*
				//TO DO n, p, adr, vill
				 *
				 */
            } catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        String result = sendHttpGet("ServiceR",param);

        //result="[{\"prenom\":\"a\",\"id\":601,\"adresse\":\"a\",\"ville\":\"a\",\"nom\":\"asdf\"},{\"prenom\":\"Veronique\",\"id\":541,\"adresse\":\"Rue En Sola\",\"ville\":\"Courrendlin\",\"nom\":\"Bersier\"},{\"prenom\":\"David\",\"id\":401,\"adresse\":\"Av. sdfsdfsfd\",\"ville\":\"Neuchatel\",\"nom\":\"Montavon\"}]";
        //result="[{\"prenom\":\"a\",\"id\":601,\"adresse\":\"a\",\"ville\":\"a\",\"nom\":\"asdf\"}]";
        Log.v("serviceR", result);

        // JSONParser parser=new JSONParser();
        //JSONArray array;
        try {
            JSONArray mJsonArray = new JSONArray(result);
            org.json.JSONObject mJsonObject = new org.json.JSONObject();
            for (int i = 0; i < mJsonArray.length(); i++) {
                mJsonObject = mJsonArray.getJSONObject(i);
                String numero=mJsonObject.getString("id");
                String nom=mJsonObject.getString("nom");
                String prenom= mJsonObject.getString("prenom");
                String adresse=mJsonObject.getString("adresse");
                String vil=mJsonObject.getString("ville");
                Log.v("entry", numero + " "+ nom +" "+ prenom+" "+ adresse+ " "+ vil);
                list.add(new Person(new Long(numero),nom,prenom,adresse,vil));
            }



            // Log.v("parse nom", ((JSONObject)array.get(1)).get("nom").toString() );
		/*}catch(ParseException pe){
				    System.out.println("position: " + pe.getPosition());
				    System.out.println(pe);
		*/

            return list;
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }


    }

    public static ArrayList<Person> serviceRAll() {
        ArrayList<Person> list = new ArrayList<Person>();
        String param=null;

        String result = sendHttpGet("/serviceRAll",null);

        //result="[{\"prenom\":\"a\",\"id\":601,\"adresse\":\"a\",\"ville\":\"a\",\"nom\":\"asdf\"},{\"prenom\":\"Veronique\",\"id\":541,\"adresse\":\"Rue En Sola\",\"ville\":\"Courrendlin\",\"nom\":\"Bersier\"},{\"prenom\":\"David\",\"id\":401,\"adresse\":\"Av. sdfsdfsfd\",\"ville\":\"Neuchatel\",\"nom\":\"Montavon\"}]";
        //result="[{\"prenom\":\"a\",\"id\":601,\"adresse\":\"a\",\"ville\":\"a\",\"nom\":\"asdf\"}]";
        Log.v("serviceRAll", result);

        // JSONParser parser=new JSONParser();
        //JSONArray array;
        try {
            JSONArray mJsonArray = new JSONArray(result);
            org.json.JSONObject mJsonObject = new org.json.JSONObject();
            for (int i = 0; i < mJsonArray.length(); i++) {
                mJsonObject = mJsonArray.getJSONObject(i);
                String numero=mJsonObject.getString("id");
                String nom=mJsonObject.getString("nom");
                String prenom= mJsonObject.getString("prenom");
                String adresse=mJsonObject.getString("adresse");
                String vil=mJsonObject.getString("ville");
                Log.v("entry", numero + " "+ nom +" "+ prenom+" "+ adresse+ " "+ vil);
                list.add(new Person(new Long(numero),nom,prenom,adresse,vil));
            }



            // Log.v("parse nom", ((JSONObject)array.get(1)).get("nom").toString() );
		/*}catch(ParseException pe){
				    System.out.println("position: " + pe.getPosition());
				    System.out.println(pe);
		*/

            return list;
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }


    }
}
