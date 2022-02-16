package employeDao;

import java.util.List;

import entity.Employe;

public interface EmpInterface {

	 void saveEmployee(Employe employee);
	 List<Employe> getEmployes();
	 Employe getEmpById(long id);
	 void updateEmp(Employe emp);
	 void deleteEmp(Long id);
	 Employe getEmpByEmail(String email);
}
