package com.klu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HeadphonesRepo extends JpaRepository<Headphones, Long> {
	public Headphones findByPid(int pid);
}
