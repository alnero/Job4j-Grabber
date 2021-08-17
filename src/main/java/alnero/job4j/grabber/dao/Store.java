package alnero.job4j.grabber.dao;

import alnero.job4j.grabber.model.Post;

import java.util.List;

public interface Store {
    void save(Post post);

    List<Post> getAll();

    Post findById(int id);
}
