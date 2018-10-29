import org.apache.xpath.SourceTree;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.transform.sax.SAXSource;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.jsoup.select.Selector.select;

public class JsoupScraper {
    public static void main(String[] args) throws IOException {

        JsoupScraper.scrapePmcAbstract("a");


    }
    //for articles of the type
    //https://www.ncbi.nlm.nih.gov/pmc/articles/PMC6197264/
    //PMC free article
    public static void scrapePmcFree(String url) throws IOException {

        // Get Title
    	final Document document = Jsoup.connect(url).get();
        System.out.println("Title: " + document.title());
        System.out.println();

        // Get Reference List
        String refs = document.body().select("#reference-list" /*css selector*/).get(0).text();
        System.out.println("References: " +refs);
        System.out.println();

        // Get Authors
        Elements auths = document.select(".fm-author");
        System.out.println("Authors: " + auths.get(0).text());
        System.out.println();

        // Get Contributing Groups (NOT NEEDED!)
        //Elements otherContributingGroups = document.select(".contrib-group");
        //System.out.println("Contributing groups: " + otherContributingGroups.get(0).text());
        //can also combine two classes, document.select(".contrib-group, fm-author");
        //System.out.println();

        // Get PMID
        Elements pmid = document.select(".fm-citation-pmid");
        //you can select a class and the call select again to get href/text etc
        System.out.println( pmid.get(0).text());
        System.out.println();

        // Get PMCID
        Elements PMCID = document.select(".fm-citation-pmcid");
        System.out.println(PMCID.get(0).text());
        System.out.println();

        // Get Publication Date
        Elements pubDate = document.select(".fm-vol-iss-date");
        System.out.println(pubDate.get(0).text());
        System.out.println();

        //Get DOI
        Elements DOI = document.select(".doi");
        System.out.println(DOI.get(0).text());
        System.out.println();

        // Get PubMed URL
        Elements pubMedURL = document.select("head > link:nth-child(17)");
        System.out.println("PubMed URL: " + pubMedURL.get(0).baseUri());
        System.out.println();
    }
    //karger style article
    //https://www.karger.com/Article/FullText/494644
    public static void scrapeKarger(String url) throws IOException {

        final Document document = Jsoup.connect("https://www.karger.com/Article/FullText/494644").get();
        System.out.println("Title: " + document.title());
        System.out.println();

        String refs = document.body().select("#tab6" /*css selector*/).get(0).text();
        System.out.println("References: " +refs);
        System.out.println();
        //check if keywords is null then get here
        Elements keywords = document.select("#title > table > tbody > tr:nth-child(1) > td:nth-child(2) > div.keywords" /*css selector*/);
        System.out.println("Keywords: " + keywords.get(0).text().replaceFirst("Key Words: ",""));
        System.out.println();

        Elements pubMedURL = document.select("head > link:nth-child(17)");
        System.out.println("PubMed URL: " + pubMedURL.get(0).baseUri());
        System.out.println();


    }
    //https://www.ncbi.nlm.nih.gov/pubmed/30359988
    public static void scrapePmcAbstract(String url) throws IOException{

        final Document document = Jsoup.connect("https://www.ncbi.nlm.nih.gov/pubmed/30359988").get();
        System.out.println("Title: " + document.title());
        System.out.println();

        Elements auths = document.select("#maincontent > div > div.rprt_all > div > div.auths");
        System.out.println("Authors: " + auths.get(0).text());
        System.out.println();

        Elements pmid = document.select("#maincontent > div > div.rprt_all > div > div.aux > div:nth-child(1) > dl > dd:nth-child(2)");
        //you can select a class and the call select again to get href/text etc
        System.out.println("pmid: " + pmid.get(0).text());
        System.out.println();

        //Elements PMCID = document.select(".fm-citation-pmcid");
        //System.out.println(PMCID.get(0).text());
        //System.out.println();

        //Elements pubDate = document.select(".fm-vol-iss-date");
        //System.out.println(pubDate.get(0).text());
        //System.out.println();

        Elements DOI = document.select("#maincontent > div > div.rprt_all > div > div.aux > div:nth-child(1) > dl > dd:nth-child(4) > a");
        System.out.println("DOI: " +DOI.get(0).text());
        System.out.println();

        //Elements pubMedURL = document.select("head > link:nth-child(17)");
        //System.out.println("PubMed URL: " + pubMedURL.get(0).baseUri());
        //System.out.println();
    }
}
