package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table (name = "users")

public class Users {
	@Id
	private int id;
	
	@Column (name = "name")
	private String nome; 
	
	private String address;
	private String email;
	private String phone;
	
	public Users() {
		
	}
	

	public Users(int id, String nome, String address, String email, String phone) {
	
		this.setId(id);
		this.setNome(nome);
		this.setAddress(address);;
		this.setEmail(email);
		this.setPhone(phone);
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void printUsers() {
		System.out.println("ID: " + this.getId());
		System.out.println("Nome: " + this.getNome());
		System.out.println("Address: " + this.getAddress());
		System.out.println("Email: " + this.getEmail());
		System.out.println("Phone: " + this.getPhone());
		System.out.println("------------------------------");
	}
}
