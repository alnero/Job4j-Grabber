package alnero.job4j.grabber;

import alnero.job4j.grabber.model.Post;
import alnero.job4j.grabber.utils.SqlRuDateTimeParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.IntStream;

public class SqlRuParse {
    public static void main(String[] args) {
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
                        Element href = tableData.child(0);
                        System.out.println(getPost(href.attr("href")));
                    }
                }
        );
    }

    public static Post getPost(String postUrl) {
        Document doc = null;
        try {
            doc = Jsoup.connect(postUrl).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element initialEntry = doc.select(".msgTable").get(0);
        String title = initialEntry.select(".messageHeader").text();
        Element message = initialEntry.select(".msgBody").get(1);
        String description = message.text();
        Element footer = initialEntry.select(".msgFooter").get(0);
        String footerText = footer.ownText();
        LocalDateTime creationDateTime = new SqlRuDateTimeParser().parse(footerText.substring(0, footerText.indexOf("[") - 1));
        Post post = new Post();
        post.setId(new Random().nextInt());
        post.setTitle(title);
        post.setLink(postUrl);
        post.setDescription(description);
        post.setCreated(creationDateTime);
        return post;
    }
}
