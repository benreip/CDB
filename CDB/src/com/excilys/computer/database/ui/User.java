package com.excilys.computer.database.ui;

import java.time.LocalDate;
import java.util.Scanner;

import com.excilys.computer.database.service.Service;

public class User {
Service s= new Service();
	
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
		Scanner sc = new Scanner(System.in);
		int answer;
		answer=sc.nextInt();
		detailordis(answer);
		}
	
	public void detailordis(int id)  {
		s.afficheListeComputerByID(id);
	}
	
	public void nomordi() {
		System.out.println("Veuillez entrer l'id de l'ordinateur souhaité : ");
		Scanner sc = new Scanner(System.in);
		String answer;
		answer=sc.nextLine();
		createordi(answer);
	}
	
	public void createordi(String nomordi) {
		s.insertcomputer(nomordi);
	}
	
	public void deleteComputer() {
		System.out.println("Veuillez entrer l'id de l'ordinateur à supprimer : ");
		Scanner sc = new Scanner(System.in);
		int answer = sc.nextInt();
		s.deleteByID(answer);
	}
	/*public void majOrdi() {
		System.out.println("Quel MAJ à effectuer? ");
		System.out.println("0 - MAJ ");
		System.out.println("1 - Liste des entreprises");
		Scanner sc = new Scanner(System.in);
		int answer;
		answer=sc.nextInt();
		detailordis(answer);
	}*/
	/*public void updateid() {
		System.out.println("Veuillez entrer l'id de l'ordinateur à update : ");
		Scanner sc = new Scanner(System.in);
		int answer;
		answer=sc.nextInt();
		updateordi(answer);
	}*/
	
	public void majOrdi () {
		System.out.println("Veuillez entrer l'id de l'ordinateur à update : ");
		Scanner sc = new Scanner(System.in);
		int answer;
		answer=sc.nextInt();
		System.out.println("Veuillez entrer le nouveau nom de l'ordinateur : ");
		String updatenameordi = sc.nextLine();
		s.updateName(answer,updatenameordi );
		System.out.println("Veuillez entrer la nouvelle date de sortie :  elle doit être au format YYYY-MM-DD");
		String updatedatesortie = sc.nextLine();
		s.updateDateSortie(answer, LocalDate.parse(updatedatesortie));
		System.out.println("Veuillez entrer la nouvelle date de fin : elle doit être au format YYYY-MM-DD");
		String updatedatefin = sc.nextLine();
		s.updateDateFIn(answer,LocalDate.parse(updatedatefin) );
		System.out.println("Veuillez entrer l'id du nouveau fabricant");
		int idcompanieupdate = sc.nextInt();
		s.updateFabricant(answer, idcompanieupdate);
		
	}
	
	public void selectswitch() {
		Boolean stop=true;
		Scanner sc=new Scanner(System.in);
		String answer;
		answer=sc.nextLine();
		while (stop) {
			switch(answer) {
				case ("0"):
					System.out.println("Liste des ordinateurs :");
					s.afficheListeComputer();
					break;
					
				case ("1"):
					System.out.println("Liste des entreprises :");
					s.afficheListeCompanie();
					break;
					
				case ("2"):
					System.out.println("Détails d'un ordinateur :");
					transitordis();
					break;
					
				case ("3"):
					System.out.println(" Création ordinateur :");
					nomordi();
					break;
					
				case ("4"):
					System.out.println(" Mise à jour d'un ordinateur :");
					majOrdi();
					break;
					
				case ("5"):
					System.out.println("Supprimer un ordinateur :");
					deleteComputer();
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
