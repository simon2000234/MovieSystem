/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviesystem.DAL;

import java.io.IOException;
import java.sql.SQLException;
import javax.lang.model.element.Element;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
            
/**
 *
 * @author Richart hansen
 */
public class FileTester
{

    public static void main(String[] args) throws SQLException, IOException
    {
       try{
        Document doc = Jsoup.connect("https://www.imdb.com/title/tt1517451/?ref_=nv_sr_2/").userAgent("Mozilla/17.0").get();
           Elements temp=doc.select("div.imdbRating");
           
           int i= 1;
           for(org.jsoup.nodes.Element ratingValue:temp)
           {
               i++;
               System.out.println(i+""+ ratingValue.getElementsByTag("span").first().text() );
                  
           }
       }
       catch (IOException e) 
       {
           
       }
       
    }
    
}
