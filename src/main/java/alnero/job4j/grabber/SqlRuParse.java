package alnero.job4j.grabber;

import alnero.job4j.grabber.model.Post;
import alnero.job4j.grabber.utils.DateTimeParser;
import alnero.job4j.grabber.utils.SqlRuDateTimeParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class SqlRuParse implements Parse {
    /** Date and time parser. */
    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) {
        DateTimeParser dateTimeParser = new SqlRuDateTimeParser();
        SqlRuParse sqlRuParse = new SqlRuParse(dateTimeParser);
        List<Post> posts = sqlRuParse.list("https://www.sql.ru/forum/job-offers/");
        System.out.println(posts.size());
    }

    @Override
    public List<Post> list(String link) {
        List<Post> posts = new ArrayList<>();
        IntStream.range(1, 6).forEach(pageNumber -> {
            StringBuilder url = new StringBuilder(link);
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
                Post post = detail(href.attr("href"));
                posts.add(post);
            }
        });
        return posts;
    }

    @Override
    public Post detail(String link) {
        Document doc = null;
        try {
            doc = Jsoup.connect(link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element initialEntry = doc.select(".msgTable").get(0);
        String title = initialEntry.select(".messageHeader").text();
        Element message = initialEntry.select(".msgBody").get(1);
        String description = message.text();
        Element footer = initialEntry.select(".msgFooter").get(0);
        String footerText = footer.ownText();
        LocalDateTime creationDateTime = this.dateTimeParser.parse(footerText.substring(0, footerText.indexOf("[") - 1));
        Post post = new Post();
        post.setId(new Random().nextInt());
        post.setTitle(title);
        post.setLink(link);
        post.setDescription(description);
        post.setCreated(creationDateTime);
        return post;
    }
}
