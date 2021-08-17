package alnero.job4j.grabber;

import alnero.job4j.grabber.model.Post;

import java.util.List;

public interface Parse {
    List<Post> list(String link);

    Post detail(String link);
}
