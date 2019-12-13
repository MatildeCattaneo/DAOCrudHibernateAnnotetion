package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entity.Users;

public class DAOUserImplWithHibeAnnot implements DAOUserWithHibeAnnot{

	Users user;
	Session session =  connessione().openSession();
	Transaction trx = session.beginTransaction();
	StandardServiceRegistry sr;
	SessionFactory sf;
  
	public Users getUser(int id) {
		user = new Users();

		user = session.get(Users.class, id);
		//session.close();
		return user;

	}

	public List<Users> getAllUsers() {
		List <Users> usersall = new ArrayList<Users>();
		Query<Users> query = session.createQuery("from Users");
		usersall = query.list();
		return usersall;
	}


	public void addUser(Users user) {
		if(StringUtils.isNumeric(user.getPhone())==true) {
		session.save(user);
		System.out.println("*** Inserimento avvenuto correttamente ***");
		}else System.out.println("Il numero inserito non è valido. Impossibile creare user.");

	}

	public void updateUser(Users user) {

		if(StringUtils.isNumeric(user.getPhone())==true) {

			this.user = session.get(Users.class, user.getId());
			this.user.setNome(user.getNome());
			this.user.setAddress(user.getAddress());
			this.user.setEmail(user.getEmail());
			this.user.setPhone(user.getPhone());
			System.out.println("*** Modifica avvenuta correttamente ***");

			session.save(this.user);
		}else System.out.println("Il numero inserito non è valido. Impossibile modificare user.");

		

	}


	public void deleteUser(int id) {
		//user = new Users();
	
		user = session.get(Users.class, id);
		session.delete(user);
		System.out.println("*** Cancellazione avvenuta correttamente ***");
		
	}

	public static SessionFactory connessione() {

		StandardServiceRegistry sr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(sr).getMetadataBuilder().build();
		SessionFactory sf = meta.getSessionFactoryBuilder().build();
		return sf;

	}

	public void uscita() {
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("Vuoi salvare le modifiche fatte al DB? Digitare si o no");
			String scelta = sc.nextLine();
			if(scelta.equalsIgnoreCase("si")) {
				trx.commit();
				session.close();
				
				System.out.println("*** Modifiche salvate correttamente ***");
				break;
			}
			else if(scelta.equalsIgnoreCase("no")) {
				trx.rollback();
				session.close();
				
				System.out.println("*** Modifiche non salvate ***");
				break;

			} System.out.println("Comando non valido");
		}while(true);

	}


	public boolean checkUserId(int id) {
		
			
		return session.get(Users.class, id) != null;

	}



}
