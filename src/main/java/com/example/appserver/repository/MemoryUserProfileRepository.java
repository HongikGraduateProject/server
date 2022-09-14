package com.example.appserver.repository;

import com.example.appserver.domain.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryUserProfileRepository implements UserProfileRepository {

    private static final Map<Long, UserProfile> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public UserProfile save(UserProfile userProfile) {
        userProfile.setId(sequence++);
        store.put(userProfile.getId(), userProfile);
        return userProfile;
    }

    @Override
    public Optional<UserProfile> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<UserProfile> findByEmail(String emailAddress) {
        return store.values().stream()
                .filter(userProfile -> userProfile.getEmail().equals(emailAddress))
                .findAny();
    }

    @Override
    public List<UserProfile> findAll() {
        return new ArrayList<>(store.values());
    }
}
