import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Parser {
    private static Logger log = Logger.getLogger(Parser.class.getClass().getName());
    final static String[] userAgent = {"Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0)", "Chrome/4.0.249.0 Safari/532.5", "Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)", "Mozilla/4.0 (compatible; MSIE 6.0; Windows 98; Win 9x 4.90)", "Mozilla/4.0 (compatible; MSIE 5.01; Windows NT 5.0)", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2)"};
    final static String[] userReffer = {"http://www.google.com", "http://www.yandex.ru", "https://www.bing.com/", "https://www.yahoo.com/"};

    public static ArrayList<Offer> parseCategory(String url, int page, ArrayList<Offer> offerList) {
        ArrayList<Offer> offers;
        if (offerList != null) {
            offers = offerList;
        } else {
            offers = new ArrayList<>();
        }
        try {

            Random rand = new Random();
            //anti antispam
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String agent = userAgent[rand.nextInt(4)];
            String reffer = userReffer[rand.nextInt(3)];

            log.info("connect to "+url);
            Document doc = Jsoup.connect(url + "&page=" + page)
                    .userAgent(agent)
                    .referrer(reffer)
                    .get();

            Elements el = doc.select("article");
            long id;
            String name;
            double price;
            double oldPrice;
            double deliveryPrice;
            String state;
            int dicountPercenge;
            String category = doc.select("span[itemprop=\"name\"]").text();
            for (Element e : el) {
                try {
                    id = Long.parseLong(e.attr("data-analytics-view-value").replaceAll("[^\\d.]", ""));
                    name = e.select("a._w7z6o").text();

                    price = Double.parseDouble(e.select("span._1svub").text().replaceAll(",", ".").replaceAll("[^\\d.]", ""));


                    oldPrice = Double.parseDouble(e.select(("span.mpof_uk")).text().replaceAll(",", ".").replaceAll("[^\\d.]", ""));

                    Offer offer = new Offer(id, name, price, oldPrice, category);
                    try {
                        deliveryPrice = Double.parseDouble(e.select(("div.mqu1_g3")).text().replaceAll(",", ".").replaceAll("[^\\d.]", ""));
                        offer.setDeliveryprice(deliveryPrice);
                    } catch (NumberFormatException ex) {

                    }
                    try {
                        dicountPercenge = Integer.parseInt(e.select(("span._9c44d_1uHr2")).text().replaceAll(",", ".").replaceAll("[^\\d.]", ""));
                        offer.setDicountPercentage(dicountPercenge);
                    } catch (NumberFormatException ex) {

                    }
                    state = e.select("DD.mpof_uk").text();
                    offer.setState(state);

                    offers.add(offer);
                    if (offers.size() >= 100) break;
                } catch (NumberFormatException ex) {

                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        if (offers.size() >=100)
            return offers;
        else return parseCategory(url, page++, offers);
    }

}
