import org.apache.xpath.SourceTree;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.transform.sax.SAXSource;
import java.io.IOException;
import java.util.ArrayList;

import static org.jsoup.select.Selector.select;

public class JsoupScraper {
    public static void main(String[] args) throws IOException {
        System.out.println("YUDI MELTZER IS A FUCKING BADASS");
        System.out.println();
        final Document document = Jsoup.connect("https://www.ncbi.nlm.nih.gov/pmc/articles/PMC6197264/").get();
        System.out.println("Title: " + document.title());
        System.out.println();

        String refs = document.body().select("#reference-list" /*css selector*/).get(0).text();
        System.out.println("References: " +refs);
        System.out.println();

        Elements auths = document.select(".fm-author");
        System.out.println("Authors: " + auths.get(0).text());
        System.out.println();

        Elements otherContributingGroups = document.select(".contrib-group");
        System.out.println("Contributing groups: " + otherContributingGroups.get(0).text());
        //can also combine two classes, document.select(".contrib-group, fm-author");
        System.out.println();

        Elements pmid = document.select(".fm-citation-pmid");
        //you can select a class and the call select again to get href/text etc
        System.out.println( pmid.get(0).text());
        System.out.println();

        Elements PMCID = document.select(".fm-citation-pmcid");
        System.out.println(PMCID.get(0).text());
        System.out.println();

        Elements pubDate = document.select(".fm-vol-iss-date");
        System.out.println(pubDate.get(0).text());
        System.out.println();

        Elements DOI = document.select(".doi");
        System.out.println(DOI.get(0).text());
        System.out.println();

        Elements pubMedURL = document.select("head > link:nth-child(17)");
        System.out.println("PubMed URL: " + pubMedURL.get(0).baseUri());
        System.out.println();










    }
}
