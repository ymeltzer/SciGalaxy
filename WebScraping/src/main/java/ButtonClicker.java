import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class ButtonClicker {

    public static void main(String[] args) throws IOException, InterruptedException {
        final Document document = Jsoup.connect("https://www.ncbi.nlm.nih.gov/pubmed/30359988").get();


        Element image = document.body().select("#EntrezForm > div:nth-child(1) > div.supplemental.col.three_col.last > div > div.icons.portlet > a" /*css selector*/).first();
        System.out.println("getting url to click.... " + image.attr("href"));
        Click.openUrl(image.attr("href"));
    }
}
