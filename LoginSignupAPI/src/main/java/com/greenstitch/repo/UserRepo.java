package com.greenstitch.repo;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.greenstitch.model.Consumer;

public interface UserRepo extends JpaRepository<Consumer, Integer>{

	public Optional<Consumer> findByEmail(String email);

}
