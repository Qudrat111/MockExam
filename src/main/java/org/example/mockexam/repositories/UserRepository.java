package org.example.mockexam.repositories;

import org.example.mockexam.modules.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByUsernameAndDeletedFalse(String username);

    Boolean existsByUsername(String username);
}
