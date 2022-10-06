package com.example.appserver.user;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryUserRepository implements UserRepository {

    private static final Map<Long, User> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public User save(User user) {
        user.setId(++sequence);
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public User findById(Long id) {
        return store.get(id);
    }

    @Override
    public Optional<User> findByEmail(String emailAddress) {
        return findAll().stream()
                .filter(u -> u.getEmail().equals(emailAddress))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteUser(Long id) {
        store.remove(id);
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}
