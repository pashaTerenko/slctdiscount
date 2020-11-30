import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVwriter {
    private static Logger log = Logger.getLogger(CSVwriter.class.getClass().getName());

    static {
        try {
            log.info(new File(".").getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void WriteOffer(ArrayList<Offer> offers, String filename) {
        FileWriter out = null;
        try {
            log.info("file create " + new File(".").getCanonicalPath() + File.separator + filename + ".csv");
            out = new FileWriter(new File(".").getCanonicalPath() + File.separator + filename + ".csv");


        } catch (IOException e) {
            e.printStackTrace();
        }
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader("Id", "Name", "Price", "Old_price", "Category", "State", "Delivery_price", "Dicount_Percentage"))) {
            offers.forEach(of -> {
                try {
                    printer.printRecord(of.getId(), of.getName(), of.getPrice(), of.getOldPrice(), of.getCategory(), of.getState(), of.getDeliveryprice(), of.getDicountPercentage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
