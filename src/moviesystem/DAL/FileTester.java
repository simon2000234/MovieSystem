///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package moviesystem.DAL;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//
///**
// *
// * @author Richart hansen
// */
//public class FileTester {
//
//    public static void main(String[] args) throws SQLException, IOException {
//        /*
//        Dette viser navn og rating på filmen. Der skal være et link for at det virker, dog også code smells. Richart :)
//         */
//        try {
//            Document doc = Jsoup.connect("https://www.imdb.com/title/tt1825683/?ref_=tt_rec_tti/").userAgent("Mozilla/17.0").get();
//            Elements movTemp = doc.select("div.titleBar");
//
//            Elements ratTemp = doc.select("div.imdbRating");
//            for (org.jsoup.nodes.Element filmNavn : movTemp) {
//                System.out.println(filmNavn.getElementsByTag("h1").first().text());
//
//            }
//        } catch (IOException e) {
//
//        }
//        {
//            try {
//                Document doc = Jsoup.connect("https://www.imdb.com/title/tt1825683/?ref_=tt_rec_tti/").userAgent("Mozilla/17.0").get();
//                Elements movTemp = doc.select("div.imdbRating");
//
//                for (org.jsoup.nodes.Element ratingValue : movTemp) {
//                    System.out.println("" + ratingValue.getElementsByTag("span").first().text());
//
//                }
//            } catch (IOException e) {
//
//            }
//
//        }
//    }
//}
