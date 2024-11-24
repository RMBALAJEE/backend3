package com.klu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopsRepo extends JpaRepository<Laptops, Long> {
	public Laptops findByPid(int pid);
}
