package com.excilys.formation.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.excilys.formation.cdb.dto.CompanieDTO;
import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.modele.Computer;
import com.excilys.formation.cdb.service.ComputerService;
/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet(name="AddComputerServlet", urlPatterns="/addComputer")
public class AddComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ComputerService service;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CompanieDTO> companies = new ArrayList();
		companies = service.afficheListeCompanie();
		request.setAttribute("companies",companies);
		request.getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerDTO computerDto = new ComputerDTO();
		computerDto.setComputerDtoName(request.getParameter("computerName"));
		if(request.getParameter("introduced") != null && !request.getParameter("introduced").equals("")) {		
			computerDto.setComputerdtointroductedin(request.getParameter("introduced"));
		}
		if(request.getParameter("discontinued") != null && !request.getParameter("discontinued").equals("")) {		
			computerDto.setComputerdtodiscontinuedin(request.getParameter("discontinued"));
		}
		if(request.getParameter("companyId") != null &&!request.getParameter("companyId").equals("0")) {
			computerDto.setComputerdtocompanieid(request.getParameter("companyId"));
		}
		System.out.println(computerDto);
		try {
			System.out.println("pd");
			Computer newComputer = service.mappingDtoToComputer(computerDto);
			System.out.println(newComputer);
			newComputer = service.insertComputerwebUI(newComputer);
			String success = "Computer " + newComputer.getName() + " was successfully add";
			request.setAttribute("success", success);
			
		}catch(IllegalArgumentException e) {
			request.setAttribute("success", e.getMessage());
			request.setAttribute("newComputer", computerDto);
		}finally {
			doGet(request, response);
		}
	}


}