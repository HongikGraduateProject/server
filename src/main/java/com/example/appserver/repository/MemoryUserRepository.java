package com.example.appserver.repository;

import com.example.appserver.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository {

    private static final Map<Integer, User> store = new HashMap<>();
    private static int sequence = 0;

    @Override
    public User save(User user) {
//        user.setId(sequence++);
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public User findById(Integer id) {
        return store.get(id);
    }

    @Override
    public Optional<User> findByEmail(String emailAddress) {
        return store.values().stream()
                .filter(user -> user.getEmail().equals(emailAddress))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
