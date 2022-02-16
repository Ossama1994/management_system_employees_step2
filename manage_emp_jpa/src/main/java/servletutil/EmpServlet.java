package servletutil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.context.WebContext;

import employeDao.EmployeImp;
//import employeDao.RoleImp;
import entity.Employe;
import entity.Role;
import repository.EmoployeRepositoryImp;
import repository.EmoployeRepositoryInt;
import thymeleafutil.TemplateEngineUtil;


@WebServlet("/")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	RoleImp roleDao;
	EmployeImp empDao;
	EmoployeRepositoryInt empR;
	
	TemplateEngine engine;
    WebContext context;
    
       


	@Override
	public void init() throws ServletException {
//    	roleDao = new RoleImp();
    	empDao = new EmployeImp();
    	empR = new EmoployeRepositoryImp();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String action = request.getServletPath();
		 
			  try {
				  
				  switch (action) {
				  
				  case "/new":
					                showNewForm(request, response);
					  break;
				  case "/insert":
					                insertEmp(request, response);
					  break;
				  case "/delete":
				                    deleteEmp(request, response);
					  break;
				  case "/edit":
				                    showEditForm(request, response);
					  break;
				  case "/update":
					                updateEmp(request, response);
					  break;
				  case"/list":
				                    AllEmployees(request, response);
					  break;
				  case"/signin":
					                ShowLoginForm(request, response);
					  break;
				  default:
					  	            UserLogin(request, response);
					  break;
				  }
			  }catch (SQLException ex) {
			      throw new ServletException(ex);
			  }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        this.context = new WebContext(request, response, request.getServletContext());
        
        engine.process("Employe/Add.html", context, response.getWriter());
    }
    
    private void insertEmp(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
    	this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        this.context = new WebContext(request, response, request.getServletContext());
    	
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String role = request.getParameter("role");
        
        
        Employe emp = new Employe();
        emp.setFirstName(fname);
        emp.setLastName(lname);
        emp.setPassword(pass);
        emp.setEmail(email);
        System.out.println(role);
        
        Role rol = new Role();
        rol.setStatus(role);
        
        emp.setRole(rol);
       
        empDao.saveEmployee(emp);
        engine.process("Employe/ok.html", context, response.getWriter());
        
    }
    private void AllEmployees(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        this.context = new WebContext(request, response, request.getServletContext());
        
        List<Employe> emp = empDao.getEmployes();
        context.setVariable("employees", emp);
        engine.process("Employe/ListEmp.html", context, response.getWriter());
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        this.context = new WebContext(request, response, request.getServletContext());
        
        Long id = Long.parseLong(request.getParameter("id"));
        Employe em = empDao.getEmpById(id);
        context.setVariable("employee", em);
        engine.process("Employe/Edit.html", context, response.getWriter());
        
    }
    private void updateEmp(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	    	this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
    	        this.context = new WebContext(request, response, request.getServletContext());
    	    	
    	        
    	       
    	        Long id = Long.parseLong(request.getParameter("id"));
    	        Employe em = empDao.getEmpById(id);
    	        
    	        String fname = request.getParameter("fname");
    	        String lname = request.getParameter("lname");
    	        String email = request.getParameter("email");
    	        String pass = request.getParameter("password");
    	  
    	      
    	        em.setFirstName(fname);
    	        em.setLastName(lname);
    	        em.setEmail(email);
    	        em.setPassword(pass);
    	      
    	        
    	        empDao.updateEmp(em);
    	        response.sendRedirect(request.getContextPath() + "/list");
    	        
    	        
    	    }
    private void deleteEmp(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	    	this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
    	        this.context = new WebContext(request, response, request.getServletContext());
    	    	
   	     
    	        
    	        Long id = Long.parseLong(request.getParameter("id"));
//    	        Employe em = empDao.getEmpById(id);
    	        empDao.deleteEmp(id);
//    	        engine.process("Employe/ListEmp.html", context, response.getWriter());
    	        response.sendRedirect(request.getContextPath() + "/list");
    	        
    	    }
    private void ShowLoginForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        this.context = new WebContext(request, response, request.getServletContext());
        
        engine.process("Employe/loginForm.html", context, response.getWriter());
    }
    private void UserLogin(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
    	this.context = new WebContext(request, response, request.getServletContext());
    	
    	String email = request.getParameter("email");
    	String pass = request.getParameter("password");
    	
    	if(empR.validateEmail(email, pass)) {
    		
    		response.sendRedirect(request.getContextPath() + "/list");
            
    	}else {
    		engine.process("Employe/loginForm.html", context, response.getWriter());    	}
    	
    }

}
