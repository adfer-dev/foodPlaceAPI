package com.adfer.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adfer.security.model.Token;

public interface TokenRepository extends JpaRepository<Token, Integer> {
	Optional<Token> findById(Integer id);
}
