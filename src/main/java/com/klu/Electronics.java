package com.klu;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Electronics {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		long id;
		int pid;
		String pname;
		int pcost;
		String pimage;
		int pqty;
		
		public Electronics() {
			
		}
		
		public Electronics(int pid, String pname, int pcost, String pimage, int pqty) {
			super();
			this.pid = pid;
			this.pname = pname;
			this.pcost = pcost;
			this.pimage = pimage;
			this.pqty = pqty;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public int getPid() {
			return pid;
		}
		public void setPid(int pid) {
			this.pid = pid;
		}
		public String getPname() {
			return pname;
		}
		public void setPname(String pname) {
			this.pname = pname;
		}
		public int getPcost() {
			return pcost;
		}
		public void setPcost(int pcost) {
			this.pcost = pcost;
		}
		public String getPimage() {
			return pimage;
		}
		public void setPimage(String pimage) {
			this.pimage = pimage;
		}
		public int getPqty() {
			return pqty;
		}
		public void setPqty(int pqty) {
			this.pqty = pqty;
		}
}
