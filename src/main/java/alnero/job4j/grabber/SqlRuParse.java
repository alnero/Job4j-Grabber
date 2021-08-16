package alnero.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements postNameCells = doc.select(".postslisttopic");
        for (Element tableData : postNameCells) {
            Element tableRow = tableData.parent();
            Element postDateCell = tableRow.child(5);
            System.out.println(postDateCell.text());
        }
    }
}
