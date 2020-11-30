import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class Main {
    final static String uri1 = "https://allegro.pl/kategoria/bieganie-odziez-147265?string=bargain_zone&bmatch=baseline-product-eyesa2-engag-dict45-spo-1-1-1106";
    final static String uri2 = "https://allegro.pl/kategoria/militaria-wiatrowki-253883?bmatch=baseline-product-eyesa2-engag-dict45-spo-1-1-1106";
    final static String uri3 = "https://allegro.pl/kategoria/zegarki-meskie-19865?string=bargain_zone&bmatch=baseline-product-eyesa2-engag-dict45-fas-1-1-1106";

    public static void main(String[] args) {

        ArrayList<Offer> offers1 = Parser.parseCategory(uri1, 1, null);
        ArrayList<Offer> offers2 = Parser.parseCategory(uri2, 1, null);
        ArrayList<Offer> offers3 = Parser.parseCategory(uri3, 1, null);
        CSVwriter.WriteOffer(offers1, offers1.get(0).category);
        CSVwriter.WriteOffer(offers2, offers2.get(0).category);
        CSVwriter.WriteOffer(offers3, offers3.get(0).category);

        System.out.println("End");
    }
}