package alnero.job4j.grabber;

import alnero.job4j.grabber.utils.SqlRuDateTimeParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.stream.IntStream;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        IntStream.range(1, 6).forEach(pageNumber -> {
            StringBuilder url = new StringBuilder("https://www.sql.ru/forum/job-offers/");
            url.append(pageNumber);
            Document doc = null;
            try {
                doc = Jsoup.connect(url.toString()).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements postNameCells = doc.select(".postslisttopic");
                    for (Element tableData : postNameCells) {
                        Element tableRow = tableData.parent();
                        Element postDateCell = tableRow.child(5);
                        System.out.println(new SqlRuDateTimeParser().parse(postDateCell.text()));
                    }
                }
        );
    }
}
