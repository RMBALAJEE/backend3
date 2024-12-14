package com.klu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AppController {
	
	@Autowired
	DAO dao;
	
	@Autowired
	JWTManager jwt;
	
	@GetMapping("/")
	public String fun1() {
		return "This is Home Page";
	}
	
	@GetMapping("/welcome/{name}")
	public String fun2(@PathVariable("name") String name) {
		return "welcome " + name;
	}
	
	@PostMapping("/user")
	public String fun3(@RequestBody User user) {
		user.setPassword(jwt.encryptData(user.getPassword()));
		dao.insert(user);
		return "User Inserted";
	}
	
	//@GetMapping("/user")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String fun4(@RequestParam("email") String email) {
		return dao.findUser(email).toString();
	}
	
	@GetMapping("/all")
	public List<User> fun6() {
		return dao.find();
	}
	
	@GetMapping("/page")
	public String fun5(@RequestParam("page")int page,@RequestParam("limit") int limit) {
		return dao.findPage(page,limit).toString();
	}
	
	@DeleteMapping("/delete")
	public String fun7(@RequestParam("email") String email) {
		return dao.deleteUser(email);
	}
	
	@PutMapping("/update")
	public String fun8(@RequestBody User user) {
		System.out.println(user);
		return dao.editUser(user);
	}
	
	@PostMapping("/login")
	public User fun9(@RequestBody User user) {
		User user2=dao.findUser(user.getEmail());
		if(user2==null)
		{
			System.out.println("user2 is null");
			return user;
		}
		else if(user.password.equals(jwt.decryptData(user2.password)))
		{
			System.out.println("password is correct");
			user2.setPassword(jwt.generateToken(user2.getName(), String.valueOf(user2.getRole()), user2.getEmail(), user2.getPassword()));
			return user2;
		}
		System.out.println("password is wrong");
		return user;
	}
	
	@PostMapping("/vegin")
	public String vegetableInsert(@RequestBody Vegetables vegetables) {
		return dao.insertVegetables(vegetables);
	}
	
	@GetMapping("/vegetables")
	public List<Vegetables> vegetableView(@RequestParam("token") String token){
		if(jwt.validateToken(token).get("role").equals("1") || jwt.validateToken(token).get("role").equals("2"))
			return dao.viewVegetables();
		else
			return new ArrayList<Vegetables>();
	}
	
	@GetMapping("/vegetables/{pid}")
	public Vegetables oneVegetableView(@PathVariable("pid") int pid) {
		System.out.println("function called");
		return dao.viewOneVegetable(pid);
	}
	
	@PostMapping("/grossaryin")
	public String grossaryInsert(@RequestBody Grossary grossary) {
		return dao.insertGrossary(grossary);
	}
	
	@GetMapping("/grossary")
	public List<Grossary> grossaryView(@RequestParam("token") String token){
		if(jwt.validateToken(token).get("role").equals("1") || jwt.validateToken(token).get("role").equals("2"))
			return dao.viewGrossary();
		else
			return new ArrayList<Grossary>();
	}
	
	@GetMapping("/grossary/{pid}")
	public Grossary oneGrossaryView(@PathVariable("pid") int pid) {
		return dao.viewOneGrossary(pid);
	}
	
	@PostMapping("/electronicin")
	public String electronicsInsert(@RequestBody Electronics electronic) {
		return dao.insertElectronics(electronic);
	}
	
	@GetMapping("/electronics")
	public List<Electronics> electronicsView(@RequestParam("token") String token){
		if(jwt.validateToken(token).get("role").equals("1") || jwt.validateToken(token).get("role").equals("2"))
			return dao.viewElectronics();
		else
			return new ArrayList<Electronics>();
	}
	
	@GetMapping("/electronics/{pid}")
	public Electronics oneElectronicView(@PathVariable("pid") int pid) {
		return dao.viewOneElectronic(pid);
	}
	
	@PostMapping("/order")
	public String orderInsert(@RequestBody Order order) {
		//return order;
		return dao.insertOrder(order);
	}
	
	@GetMapping("/allorders")
	public List<Order> orderAll(@RequestParam String email) {
		//return order;
		return dao.AllOrder(email);
	}

}
