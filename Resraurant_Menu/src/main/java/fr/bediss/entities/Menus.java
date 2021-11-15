package fr.bediss.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @ ToString @AllArgsConstructor @NoArgsConstructor
public class Menus {
	
	
	@Id  @GeneratedValue(generator = "system-uuid") 
	@GenericGenerator(name= "system-uuid", strategy = "uuid")
	@Column(length = 100)
	private String identifiant;
	private String entrees;
	private String plats;
	private String desserts;

}
