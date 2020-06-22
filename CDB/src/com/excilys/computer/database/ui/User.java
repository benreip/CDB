package com.excilys.computer.database.ui;

import java.util.Scanner;

import com.excilys.computer.database.service.Service;

public class User {
Service s= new Service();
Scanner sc = new Scanner(System.in);
	
	public void affichage() {
		System.out.println("Que voulez vous faire? ");
		System.out.println("0 - Liste des ordinateurs");
		System.out.println("1 - Liste des entreprises");
		System.out.println("2 - Détails d'un ordinateur");
		System.out.println("3 - Création d'un ordinateur");
		System.out.println("4 - Mise à jour d'un ordinateur");
		System.out.println("5 - Suppression d'un ordinateur");
		System.out.println("6 - Quitter ");
		;
		
		selectswitch();

	}
	
	public void transitordis() {
		System.out.println("Veuillez entrer l'id de l'ordinateur souhaité : ");
		int answer;
		answer=sc.nextInt();
		detailordis(answer);
		}
	
	public void detailordis(int id)  {
		System.out.println(s.afficheListeComputerByID(id));
	}
	
	public void nomordi() {
		System.out.println("Veuillez entrer le nom du nouvel ordinateur : ");
		String answer;
		answer=sc.nextLine();
		createordi(answer);
	}
	
	public void createordi(String nomordi) {
		s.insertcomputer(nomordi);
	}
	
	public void deleteComputer() {
		System.out.println("Veuillez entrer l'id de l'ordinateur à supprimer : ");
		int answer = sc.nextInt();
		s.deleteByID(answer);
	}
	
	

	public void majOrdi () {
		System.out.println("Veuillez entrer l'id de l'ordinateur à update : ");
		int answer;
		answer=sc.nextInt();
		System.out.println("Veuillez entrer le nouveau nom de l'ordinateur : ");
		String updatenameordi = sc.nextLine();
		s.updateName(answer,updatenameordi );
		System.out.println("Veuillez entrer la nouvelle date de sortie :  elle doit être au format YYYY-MM-DD");
		String updatedatesortie = sc.nextLine();
		s.updateDateSortie(answer, updatedatesortie);
		System.out.println("Veuillez entrer la nouvelle date de fin : elle doit être au format YYYY-MM-DD");
		String updatedatefin = sc.nextLine();
		s.updateDateFIn(answer,updatedatefin );
		System.out.println("Veuillez entrer l'id du nouveau fabricant");
		int idcompanieupdate = sc.nextInt();
		s.updateFabricant(answer, idcompanieupdate);
		
	}
	
	public void select_pc(String answer,int pc,int pas) {
		switch(answer) {
		case("+") :
			pc = pc+9;
			System.out.println(s.afficheListeComputer(pc+9,pas));
			System.out.println("\n Appuyez sur + pour afficher les pc suivants, - pour les précédents");
			answer = sc.nextLine();
			select_pc(answer,pc,pas);
		
		case("-") :
			pc = pc-9;
			System.out.println(s.afficheListeComputer(pc-9,pas));
		System.out.println("\n Appuyez sur + pour afficher les pc suivants, - pour les précédents");
		answer = sc.nextLine();
		select_pc(answer,pc,pas);
		
		default:
			System.out.println("je ne comprends pas désolé");
		}
	}
	
	
	
	public void selectswitch() {
		Boolean stop=true;
		String answer;
		answer=sc.nextLine();
		while (stop) {
			switch(answer) {
				case ("0"):
					System.out.println("Liste des ordinateurs :");
					int pc = 0;
					int pas = 9;
					System.out.println(s.afficheListeComputer(pc,pas));
					System.out.println("\n Appuyez sur + pour afficher les pc suivants, - pour les précédents");
					answer = sc.nextLine();
					select_pc(answer,pc,pas);
					stop = false;
					break;
					
				case ("1"):
					System.out.println("Liste des entreprises :");
					System.out.println(s.afficheListeCompanie());
					stop = false;
					break;
					
				case ("2"):
					System.out.println("Détails d'un ordinateur :");
					transitordis();
					stop = false;
					break;
					
				case ("3"):
					System.out.println(" Création ordinateur :");
					nomordi();
					stop = false;
					break;
					
				case ("4"):
					System.out.println(" Mise à jour d'un ordinateur :");
					majOrdi();
					stop = false;
					break;
					
				case ("5"):
					System.out.println("Supprimer un ordinateur :");
					deleteComputer();
					stop = false;
					break;
					
				case ("6"):
					System.out.println("Au revoir !");
					stop=false;
					break;
				default:
					System.out.println("Désolé je n'ai pas compris.");
					break;
			}
			
		}
	}
}
