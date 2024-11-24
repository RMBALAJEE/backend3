package com.klu;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AppController {
	
	@Autowired
	Service service;
	
	@GetMapping("/")
	public String fun1() {
		return "welcome";
	}
	
	@PostMapping("/lapin")
	public String loptopInsert(@RequestBody Laptops laptop) {
		return service.insertLaptops(laptop);
	}
	
	@GetMapping("/laptops")
	public List<Laptops> laptopView(){
		return service.viewLaptops();
	}
	
	@PostMapping("/login")
	public Map<String, String> loginView(@RequestBody Login login) {
		return service.viewUser(login);
	}
	
	@GetMapping("/laptops/{pid}")
	public Laptops oneLaptopView(@PathVariable("pid") int pid) {
		return service.viewOneLaptop(pid);
	}
	
	@PostMapping("/mobin")
	public String mobileInsert(@RequestBody Mobiles mobile) {
		return service.insertMobiles(mobile);
	}
	
	@GetMapping("/mobiles")
	public List<Mobiles> mobileView(){
		return service.viewMobiles();
	}
	
	@GetMapping("/mobiles/{pid}")
	public Mobiles oneMobileView(@PathVariable("pid") int pid) {
		return service.viewOneMobile(pid);
	}
	
	@PostMapping("/headin")
	public String headphoneInsert(@RequestBody Headphones headphone) {
		return service.insertHeadphones(headphone);
	}
	
	@GetMapping("/headphones")
	public List<Headphones> headphoneView(){
		return service.viewHeadphones();
	}
	
	@GetMapping("/headphones/{pid}")
	public Headphones oneHeadphoneView(@PathVariable("pid") int pid) {
		return service.viewOneHeadphones(pid);
	}
	
}
