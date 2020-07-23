package com.excilys.formation.cdb.modele;

public class Page {

	// Init la current page à 1 lors de l'appel
	private Integer currentPage = 1;

	private Integer nbOfPage;

	//init le nombres d'items par page à 10 lors du premier appel
	private  Integer nb_entries_per_page = 10;

	//init le premier tri par id
	private String colonne = "id";

	//init le premier appel à l'ascending ( ordre croissant)
	private String ascending="ASC";

	public Page() {

	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(final Integer currentPage) {
		if (currentPage < 1) {
			this.currentPage = 1;
		}
		this.currentPage = currentPage;
	}

	public Integer getNbOfPage() {
		return nbOfPage;
	}

	public void setNbOfPage(final Integer nbOfPage) {
		this.nbOfPage = nbOfPage;
	}



	public Integer getNb_entries_per_page() {
		return nb_entries_per_page;
	}

	public void setNb_entries_per_page(final Integer nb_entries_per_page) {
		this.nb_entries_per_page=nb_entries_per_page;
	}



	public String getColonne() {
		return colonne;
	}

	public void setColonne(final String colonne) {
		this.colonne = colonne;
	}

	public String getAscending() {
		return ascending;
	}

	public void setAscending(final String ascending) {
		this.ascending = ascending;
	}




}