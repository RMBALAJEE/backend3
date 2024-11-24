package com.klu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepo extends JpaRepository<Login, String> {
	public Login findByUsername(String username);
}
