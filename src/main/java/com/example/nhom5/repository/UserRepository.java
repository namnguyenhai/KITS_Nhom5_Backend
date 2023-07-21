package com.example.nhom5.repository;

import com.example.nhom5.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findById(int userId);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    public User findByEmail(String email);
    //Optional<User> findByUserName(String username);
    @Query(value = "SELECT * FROM users u WHERE u.username = :username LIMIT 1", nativeQuery = true)
    User findByUsername(String username);
    @Modifying
    @Transactional
    @Query(value = "UPDATE users u SET u.token = ? WHERE u.user_id = ?", nativeQuery = true)
    int updateTokenById(String token, int id);

    @Query(value = "SELECT * FROM users u WHERE u.token = :token ", nativeQuery = true)
    User findByToken(String token);
//
//    Boolean existsByUsername(String username);
//
//    Boolean existsByEmail(String email);
}
