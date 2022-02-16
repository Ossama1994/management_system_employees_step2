	package entity;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "role")
public class Role {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id ;
    

	@Column(name= "status")
	private String status;
    
    @OneToMany(mappedBy="role")
    private List<Employe> employees;
    
    public Role() {
    	super();
    }
    
	public Role(String status) {
		super();
		this.status = status;
	}
	
	public List<Employe> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employe> employees) {
		this.employees = employees;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", status=" + status + "]";
	}
	
	
}
