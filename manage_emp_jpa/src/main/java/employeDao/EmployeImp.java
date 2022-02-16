package employeDao;


import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import entity.Employe;
import hibernateUtil.JPAUtil;


public class EmployeImp implements EmpInterface {
	
	 public void saveEmployee(Employe employee) {
	        
		 EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		 try {
			 
			 em.getTransaction().begin();
			 em.persist(employee);
			 em.getTransaction().commit();
		 }catch(Exception e) {
			 if(em != null) {
				 em.getTransaction().rollback();
			 }
			 e.printStackTrace();
		 }
		 finally {
			 em.close();
		 }
		 
	    }
	 
	 @SuppressWarnings("unchecked")
	public List<Employe> getEmployes() {
		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		String query = "SELECT c FROM Employe c WHERE id IS NOT NULL";
		TypedQuery<Employe> emps = em.createQuery(query,Employe.class);
		List<Employe> listemps = null ; 
		try {
			listemps = emps.getResultList();
		}
		catch(NoResultException e){
			e.printStackTrace();
		}finally {
			em.close();
		}
	      
		 return listemps;
	        
	    }
	 
	 public Employe getEmpById(long id) {
		 
		 EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
			
			Employe employee = em.find(Employe.class, id);
			
			if(employee == null) {
				throw new EntityNotFoundException("Can't find Employe for "+id);
			}
			return employee;
	
	 }
	 
	 public void updateEmp(Employe emp) {
		 
		 EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
		 //persister un objet employe
		 try {
			 
			 em.getTransaction().begin();
			 em.merge(emp);
			 //executer une transaction
			 em.getTransaction().commit();
		 }catch( Exception e ) {
			 
			 e.printStackTrace();
			 
		 }finally {
			 
			 em.close();
			 
		 }
	   
	 }
	 public void deleteEmp(Long id) {
			EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
	        em.getTransaction().begin();
          try {
        	  
        	  Employe employee = em.find(Employe.class, id);
        	  
        	  em.remove(employee);
        	  em.getTransaction().commit();
          }catch(Exception e) {
        	  e.printStackTrace();
          }finally {
        	  em.close();
          }
	    }

	@Override
	public Employe getEmpByEmail(String email) {
		
        
	 return null;
	}
}
