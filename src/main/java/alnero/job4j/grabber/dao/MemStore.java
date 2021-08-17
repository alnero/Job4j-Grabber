package alnero.job4j.grabber.dao;

import alnero.job4j.grabber.model.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemStore implements Store {
    /** Internal memory storage. */
    private Map<Integer, Post> storage = new HashMap<>();

    @Override
    public void save(Post post) {
        storage.put(post.getId(), post);
    }

    @Override
    public List<Post> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Post findById(int id) {
        return storage.get(id);
    }
}
