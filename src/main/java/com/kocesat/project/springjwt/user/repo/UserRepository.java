package com.kocesat.project.springjwt.user.repo;

import com.kocesat.project.springjwt.user.exception.UserNotFoundException;
import com.kocesat.project.springjwt.user.model.User;
import org.bson.types.ObjectId;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Repository
@CacheConfig(cacheNames = "users")
public interface UserRepository extends MongoRepository<User, ObjectId> {
    @Cacheable
    Optional<User> findByUsername(String username);

    @CacheEvict(allEntries = true)
    <S extends User> List<S> saveAll(Iterable<S> entities);

    @Caching(
            evict = {
                    @CacheEvict(key = "#p0.id", condition = "#p0.id != null"),
                    @CacheEvict(key = "#p0.username", condition = "#p0.username != null")
            })
    <S extends User> S save(S entity);

    @Cacheable
    Optional<User> findById(ObjectId objectId);

    @Cacheable
    default User getById(ObjectId id) throws UserNotFoundException {
        final User user = findById(id)
                .orElseThrow(UserNotFoundException::new);
        if (!user.isEnabled()) {
            throw new UserNotFoundException(format("User %s is not enabled.", user.getUsername()));
        }
        return user;
    }
}
