package com.klu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MobilesRepo extends JpaRepository<Mobiles, Long> {
	public Mobiles findByPid(int pid);
}
