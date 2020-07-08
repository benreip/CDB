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
@WebServlet(name="EditComputerServlet", urlPatterns="/editComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ComputerService service;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputerServlet() {
        super();
        this.service = new ComputerService();
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CompanieDTO> companies = new ArrayList();
		companies = service.afficheListeCompanie();
		request.setAttribute("companies",companies);
		
		if(request.getParameter("id") != null) {
			try {
				Integer id = Integer.parseInt(request.getParameter("id"));
				System.out.println(id);
				ComputerDTO computerDto = service.afficheListeComputerByID(id);
				request.setAttribute("computer", computerDto);
			}catch(NumberFormatException e) {
				String error = "Id is not an Integer";
				request.setAttribute("error", error);
			}finally {
				request.getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request, response);
			}
		}else {
			request.getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ComputerDTO computerDto = new ComputerDTO();
		computerDto.setComputerdtoid(request.getParameter("id"));
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
			Computer newComputer = service.mappingDtoToComputer(computerDto);
			newComputer = service.updateAllwebUI(newComputer);
			System.out.println(newComputer);
			String success = "Computer " + newComputer.getName() + " was successfully updated";
			request.setAttribute("success", success);
			
		}catch(IllegalArgumentException e) {
			request.setAttribute("success", e.getMessage());
			request.setAttribute("newComputer", computerDto);
		}finally {
			doGet(request, response);
		}
	}

}
