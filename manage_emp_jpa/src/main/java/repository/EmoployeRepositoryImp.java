package repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import entity.Employe;
import hibernateUtil.JPAUtil;


public class EmoployeRepositoryImp implements EmoployeRepositoryInt {
	
	
	@Override
	public Boolean validateEmail(String email,String password) {

		EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        
      try {
    	  
    	 String Hql = "From Employe E WHERE E.email = :email";
    	 Query query = em.createQuery(Hql);
    	 query.setParameter("email", email);
    	 em.getTransaction().commit();
    	 
    	 Employe emp = (Employe) query.getSingleResult();
    	 if(emp != null) {
    		 return true;
    	 }
    	  
      }catch(Exception e) {
      	e.printStackTrace();
      }
    	  
	return false;
	}
}
