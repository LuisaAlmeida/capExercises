package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Application {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "application_id")
	private Integer id;

	@Column(name = "app_name", nullable = false)
	private String name;
	
	@Column(length = 2000)
	private String description;
	private String owner;
	
}
