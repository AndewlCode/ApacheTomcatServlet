package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
  private final ConcurrentHashMap<Long, Post> longPostHashMap = new ConcurrentHashMap<>();
  private final AtomicLong idCounter = new AtomicLong(0);

  public List<Post> all() {
    Collection<Post> posts;
    posts = longPostHashMap.values();
    return new ArrayList<>(posts);
  }

  public Optional<Post> getById(long id) {
    if (longPostHashMap.containsKey(id)) {
      return Optional.ofNullable(longPostHashMap.get(id));
    }
    return Optional.empty();
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      Long postId = idCounter.addAndGet(1);
      post.setId(postId);
      longPostHashMap.put(postId, post);
    } else {
      longPostHashMap.put(post.getId(), post);
    }
    return post;
  }

  public void removeById(long id) {
    longPostHashMap.remove(id);
  }
}
