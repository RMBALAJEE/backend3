package com.klu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class DAO {
	
	@Autowired
	UserInterface repo;
	
	@Autowired
	VegRepo repo1;
	
	@Autowired
	GrossaryRepo repo3;
	
	@Autowired
	ElectronicsRepo repo4;
	
	@Autowired
	OrderRepo repo5;
	
	public String insertVegetables(Vegetables vegetable) {
		repo1.save(vegetable);
		return "Inserted";
	}
	
	public List<Vegetables> viewVegetables() {
		return repo1.findAll();
	}
	
	public Vegetables viewOneVegetable(int pid) {
		return repo1.findByPid(pid);
	}
	
	public String insertGrossary(Grossary grossary) {
		repo3.save(grossary);
		return "Inserted";
	}
	
	public List<Grossary> viewGrossary() {
		return repo3.findAll();
	}
	
	public Grossary viewOneGrossary(int pid) {
		return repo3.findByPid(pid);
	}
	
	public String insertElectronics(Electronics electronics) {
		repo4.save(electronics);
		return "Inserted";
	}
	
	public List<Electronics> viewElectronics() {
		return repo4.findAll();
	}
	
	public Electronics viewOneElectronic(int pid) {
		return repo4.findByPid(pid);
	}
	
	public void insert(User u1) {
		repo.save(u1);
	}
	
	public User findUser(String email) {
		return repo.findByEmail(email);
	}
	
	public List<User> find(){
		return repo.findAll();
	}
	
	public List<User> findPage(int page, int limit) {
		Sort sort = Sort.by(Sort.Direction.DESC, "name");
		Pageable pageable = PageRequest.of(page, limit, sort);
		return repo.findAll(pageable).toList();
	}
	
	public String deleteUser(String email) {
		repo.delete(repo.findByEmail(email));
		return "Deleted " + email;
	}
	
	public String editUser(User user) {
		System.out.println("edit function");
		deleteUser(user.getEmail());
		System.out.println("deleted");
		insert(user);
		System.out.println("editted");
		return "Edited Successfully";
	}
	
	public String insertOrder(Order order) {
		repo5.save(order);
		return "Inserted Successfully";
	}
	
	public List<Order> AllOrder(String email) {
		return repo5.findByEmail(email);
	}
}
