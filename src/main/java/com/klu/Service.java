package com.klu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Service {
	
	@Autowired
	LaptopsRepo repo1;
	
	@Autowired
	LoginRepo repo2;
	
	@Autowired
	MobilesRepo repo3;
	
	@Autowired
	HeadphonesRepo repo4;
	
	public String insertLaptops(Laptops laptop) {
		repo1.save(laptop);
		return "Inserted";
	}
	
	public List<Laptops> viewLaptops() {
		return repo1.findAll();
	}
	
	public Map<String, String> viewUser(Login login) {
		Login login2 = repo2.findByUsername(login.getUsername());
		Map<String, String> res = new HashMap<>();
		if(login2 == null) {
			res.put("login", "fail");
		}
		else if(login2.getPassword().equals(login.getPassword())) {
			res.put("login", "success");
		}
		else {
			res.put("login", "fail");
		}
		return res;
	}
	
	public Laptops viewOneLaptop(int pid) {
		return repo1.findByPid(pid);
	}
	
	public String insertMobiles(Mobiles mobile) {
		repo3.save(mobile);
		return "Inserted";
	}
	
	public List<Mobiles> viewMobiles() {
		return repo3.findAll();
	}
	
	public Mobiles viewOneMobile(int pid) {
		return repo3.findByPid(pid);
	}
	
	public String insertHeadphones(Headphones headphone) {
		repo4.save(headphone);
		return "Inserted";
	}
	
	public List<Headphones> viewHeadphones() {
		return repo4.findAll();
	}
	
	public Headphones viewOneHeadphones(int pid) {
		return repo4.findByPid(pid);
	}
	
}
