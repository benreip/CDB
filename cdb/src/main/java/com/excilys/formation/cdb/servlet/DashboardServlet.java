package com.excilys.formation.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.service.Service;
/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet(name="DashboardServlet", urlPatterns="/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Integer currentpage;
    private Service service;
    private Integer nb_entries_per_page;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
        service = new Service();
        currentpage = 1;
        nb_entries_per_page = 10;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ComputerDTO>  cdtos = new ArrayList<>();
		if(request.getParameter("page") != null) {
			String page = request.getParameter("page");
			currentpage = Integer.parseInt(page);
		}
		if(request.getParameter("nbByPage") != null) {
			String nbByPage = request.getParameter("nbByPage");
			nb_entries_per_page = (Integer.parseInt(nbByPage));
		}
		cdtos = service.afficheListeComputer(currentpage,nb_entries_per_page);
		request.setAttribute("currentpage", currentpage);
		System.out.println(currentpage);
		request.setAttribute("computers", cdtos);
		request.setAttribute("nbComputers", service.numberOfComputers());
		request.setAttribute("nbPageMax", service.getComputersNbPages(nb_entries_per_page));
		request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}