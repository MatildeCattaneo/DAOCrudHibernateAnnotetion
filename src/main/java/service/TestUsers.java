package service;


import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import entity.Users;


public class TestUsers {

	static Users user;							// DTO
	static DAOUserWithHibeAnnot dui = new DAOUserImplWithHibeAnnot();	// DAO
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args)  {

		((DAOUserImplWithHibeAnnot) dui).connessione();

		int scelta ;
		do {
			stampaMenu();

			scelta = sc.nextInt(); sc.nextLine();
			switch (scelta) {
			case 1: inserimento();								break;
			case 2: modifica();									break;
			case 3: lettura();									break;
			case 4: letturaAll();								break;
			case 5: cancellazione();							break;
			case 9:((DAOUserImplWithHibeAnnot) dui).uscita();				break;

			default: System.out.println("Scelta selezionata non valida, riprovare");
			}

		}while(scelta != 9);
		System.out.println("*** ARRIVEDERCI ***"); 
		System.exit(0);
	

	}

	private static void stampaMenu() {

		System.out.println();
		System.out.println("*** GESTIONE UTENTI ***");
		System.out.println("Specificare una delle seguenti opzioni:");
		System.out.println("1. Inserimenti");
		System.out.println("2. Modifica");
		System.out.println("3. Lettura");
		System.out.println("4. Lettura di tutti gli utenti");
		System.out.println("5. Cancellazione");
		System.out.println("9. Uscita");

	}

	private static void cancellazione() {
		user = new Users();

		System.out.println("Specificare l'id dell'utente che si vuole cancellare");
		int id = sc.nextInt();sc.nextLine();
		user.setId(id); 
		
		if((((DAOUserImplWithHibeAnnot) dui).checkUserId(user.getId()) == true)) {
			dui.deleteUser(id); 
		}else System.out.println("Impossibile cancellare utente. Id inserito non presente");
	}

	private static void letturaAll() {

		for(Users k : dui.getAllUsers()) {
			k.printUsers();
		}

	}

	private static void lettura() {


		System.out.println("Specificare l'id dello user di cui si vogliono vedere i dettagli");
		user = dui.getUser(sc.nextInt()); sc.nextLine();

		if(user!=null) {
			user.printUsers();
		} else System.out.println("Impossibile leggere utente. Id inserito non presente");

	}

	private static void modifica() {

		user = new Users();

		System.out.println("Inserire l'id dell'utente che si vuole modificare:");
		user.setId(sc.nextInt()); sc.nextLine();
		if(((DAOUserImplWithHibeAnnot) dui).checkUserId(user.getId()) == true) {

			System.out.println("Inserire il nuovo nome");
			user.setNome(sc.nextLine());
			System.out.println("Inserire il nuovo indirizzo");
			user.setAddress(sc.nextLine());
			System.out.println("Inserire la nuova email");
			user.setEmail(sc.nextLine());
			System.out.println("Inserire il nuovo numero");
			user.setPhone(sc.nextLine());

			dui.updateUser(user);

		}else System.out.println("Impossibile aggiornare user. Id inserito non valido");


	}

	private static void inserimento() {

		user = new Users();
		
		System.out.println("Specificare l'id del nuovo utente");
		user.setId(sc.nextInt()); sc.nextLine();
		if(((DAOUserImplWithHibeAnnot) dui).checkUserId(user.getId()) == false) {
			System.out.println("Specificare il nome del nuovo utente");
			user.setNome(sc.nextLine());
			System.out.println("Specificare l'indirizzo del nuovo utente");
			user.setAddress(sc.nextLine());
			System.out.println("Specificare l'email del nuovo utente");
			user.setEmail(sc.nextLine());
			System.out.println("Specificare il telefono del nuovo utente");
			user.setPhone(sc.nextLine());
			dui.addUser(user);
		}
		else System.out.println("Impossibile creare nuovo utente. Id già presente ");

	}

}
