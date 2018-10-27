import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Click {

    public static void openUrl(String url) throws IOException, InterruptedException {
        final Document document = Jsoup.connect(url).get();

        System.out.println("clicking.....");

        Element auths = document.body().select("#title > table > tbody > tr:nth-child(1) > td:nth-child(2) > span" /*css selector*/).first();
        System.out.println("Authors " + auths.text());
        System.out.println();
    }

}
