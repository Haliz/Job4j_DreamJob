package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private static final PostStore INST = new PostStore();
    private final AtomicInteger id = new AtomicInteger();
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "Description for Junior", LocalDateTime.now()));
        posts.put(2, new Post(2, "Middle Java Job", "Description for Middle", LocalDateTime.now()));
        posts.put(3, new Post(3, "Senior Java Job", "Description for Senior", LocalDateTime.now()));
    id.set(3);
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public void add(Post post) {
        int currentId = id.incrementAndGet();
        post.setId(currentId);
        posts.put(currentId, post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public boolean update(Post post) {
        post.setCreated(LocalDateTime.now());
        return posts.replace(post.getId(), posts.get(post.getId()), post);
    }
}
