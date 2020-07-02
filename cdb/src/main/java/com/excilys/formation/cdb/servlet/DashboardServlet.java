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
import com.excilys.formation.cdb.modele.Computer;
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
		String search = null;
		List<ComputerDTO>  cdtos = new ArrayList<>();
		if(request.getParameter("page") != null) {
			String page = request.getParameter("page");
			currentpage = Integer.parseInt(page);
		}
		if(request.getParameter("nbByPage") != null) {
			String nbByPage = request.getParameter("nbByPage");
			nb_entries_per_page = (Integer.parseInt(nbByPage));
		}
		
		if(request.getParameter("search") != null) {
			 search = request.getParameter("search");
			List<ComputerDTO> computersearch = service.searchByName(search,currentpage,nb_entries_per_page);
			request.setAttribute("search", search);
			request.setAttribute("computers",computersearch);
			request.setAttribute("currentpage", currentpage);
			request.setAttribute("nbComputers", service.numberOfComputersBySearch(search));
			request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
			}
		
		cdtos = service.afficheListeComputer(currentpage,nb_entries_per_page);
		request.setAttribute("search", search);
		request.setAttribute("currentpage", currentpage);
		request.setAttribute("computers", cdtos);
		request.setAttribute("nbComputers", service.numberOfComputers());
		request.setAttribute("control_page", service.getComputersNbPages(nb_entries_per_page));
		request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("selection") != null ) {
			String[] selection = request.getParameter("selection").split(",");
			for(String comp: selection) {
					System.out.println(comp);
					service.deleteByID(Integer.parseInt(comp));
			}
		}
		doGet(request, response);
	}

}