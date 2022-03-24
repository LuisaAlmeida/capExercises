package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Release {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String releaseDate;
	private String description;
	
	public Release() {	
	}
	
	public Release(Integer id, String description, String releaseDate) {
		this.id = id;
		this.releaseDate = releaseDate;
		this.description = description;
		
	}

}
