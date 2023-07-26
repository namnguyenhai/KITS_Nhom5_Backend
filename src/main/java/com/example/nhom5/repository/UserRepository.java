package com.example.nhom5.repository;

import com.example.nhom5.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findById(int userId);

//    @Query(value = "SELECT * FROM users u WHERE u.email = :email LIMIT 1",nativeQuery = true)
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


// HaiNam code
   @Query(value = "SELECT users.user_id as userid,users.address as address,users.email as email,users.first_name as firstname,users.image as image,users.last_name as lastname,users.phone_number as phonenumber,users.username as username\n" +
           "FROM users\n" +
           "WHERE users.user_id = (:userId)",nativeQuery = true)
   public List<Map<String,Object>> getUserByIds(@Param("userId") int userId);
}