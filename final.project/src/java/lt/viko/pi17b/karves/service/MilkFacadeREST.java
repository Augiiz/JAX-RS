/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.pi17b.karves.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lt.viko.pi17b.karves.Milk;
import javax.json.JsonArray;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Simonas
 */
@Stateless
@Path("lt.viko.pi17b.karves.milk")
public class MilkFacadeREST extends AbstractFacade<Milk> {

    @PersistenceContext(unitName = "final.projectPU")
    private EntityManager em;

    public MilkFacadeREST() {
        super(Milk.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Milk entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Milk entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Milk find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Milk> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Milk> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("production/{location}/{country}")
    @Produces(MediaType.TEXT_PLAIN)
    public String production(@PathParam("location") String location, @PathParam("country") String country) throws IOException{
        JSONObject json = readJsonFromUrl("http://api.openweathermap.org/data/2.5/"
                + "weather?q="+location+","+country
                + "&APPID=48f159eebaad18d61837422eba77c32c&units=metric");
        //JSONArray data = json.getJSONArray("main");
        StringBuilder str=new StringBuilder();
        double temp=json.getJSONObject("main").getDouble("temp");
        double humidity=json.getJSONObject("main").getInt("humidity");
        double thi=0.8*temp+(humidity/100)*(temp-14.4)+ 46.4;
        
        if(temp>22){
            double result= (temp-22)*0.8;
            str.append("Temperatūros įtaka: - " + result + System.lineSeparator());
         
        }
        else
            str.append("Temperatūros įtakos nėra"+ System.lineSeparator());
        if(thi>69){
            double result= (thi-69)*0.4;
            str.append("Drėgmės įtaka: - " + result + System.lineSeparator());
        }
        else
            str.append("Drėgmės įtakos nėra"+ System.lineSeparator());
        return str.toString();
        
    }
    
    @GET
    @Path("production/{location}/{country}/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String productionToMilk(@PathParam("location") String location, @PathParam("country") String country, @PathParam("id") Integer id) throws IOException{
        JSONObject json = readJsonFromUrl("http://api.openweathermap.org/data/2.5/"
                + "weather?q="+location+","+country
                + "&APPID=48f159eebaad18d61837422eba77c32c&units=metric");
        //JSONArray data = json.getJSONArray("main");
        StringBuilder str=new StringBuilder();
        double temp=json.getJSONObject("main").getDouble("temp");
        double humidity=json.getJSONObject("main").getInt("humidity");
        double thi=0.8*temp+(humidity/100)*(temp-14.4)+ 46.4;
        
        if(temp>22){
            double minus=((temp-22)*0.8);
            double result= super.find(id).getAmount()-minus;
            str.append("Temperatūros įtaka pienui: - " + result +"(-" + minus+")" + System.lineSeparator());
         
        }
        else
            str.append("Temperatūros įtakos nėra"+ System.lineSeparator());
        if(thi>69){
            double minus=((thi-69)*0.4);
            double result= super.find(id).getAmount()-minus;
            str.append("Drėgmės įtaka pienui: - " + result +"(-" + minus+")"+ System.lineSeparator());
        }
        else
            str.append("Drėgmės įtakos nėra"+ System.lineSeparator());
        return str.toString();
        
    }
    
    @GET
    @Path("production/{location}/{country}/all")
    @Produces(MediaType.TEXT_PLAIN)
    public String productionToAllMilk(@PathParam("location") String location, @PathParam("country") String country, @PathParam("id") Integer id) throws IOException{
        
        
        JSONObject json = readJsonFromUrl("http://api.openweathermap.org/data/2.5/"
                + "weather?q="+location+","+country
                + "&APPID=48f159eebaad18d61837422eba77c32c&units=metric");
        //JSONArray data = json.getJSONArray("main");
        StringBuilder str=new StringBuilder();
        double temp=json.getJSONObject("main").getDouble("temp");
        double humidity=json.getJSONObject("main").getInt("humidity");
        double thi=0.8*temp+(humidity/100)*(temp-14.4)+ 46.4;
        
        if(temp>22){
            double result=0;
            double minus=((temp-22)*0.8);
            double totalMinus=0;
            for(int i=0; super.count()>i; i++){
            result=result + super.findAll().get(i).getAmount() - minus;
            totalMinus=totalMinus + minus;
            }
            
            str.append("Temperatūros įtaka visu karviu pienui: - " + result +"(-" + totalMinus+")" + System.lineSeparator());
         
        }
        else
            str.append("Temperatūros įtakos nėra"+ System.lineSeparator());
        if(thi>69){
            double result=0;
            double minus=((thi-69)*0.4);
            double totalMinus=0;
            for(int i=0; super.count()>i; i++){
            result=result + super.findAll().get(i).getAmount() - minus;
            totalMinus=totalMinus + minus;
            }
        
            str.append("Drėgmės įtaka pienui: - " + result +"(-" + totalMinus+")"+ System.lineSeparator());
        }
        else
            str.append("Drėgmės įtakos nėra"+ System.lineSeparator());
        return str.toString();
        
    }
    private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }
     public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }
}
