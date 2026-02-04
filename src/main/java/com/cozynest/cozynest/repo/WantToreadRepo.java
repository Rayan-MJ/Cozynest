package com.cozynest.cozynest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozynest.cozynest.model.User;
import com.cozynest.cozynest.model.WantToRead;
import java.util.List;


public interface WantToreadRepo extends JpaRepository<WantToRead,Integer> {
    List<WantToRead> findByUser(User user);
}
