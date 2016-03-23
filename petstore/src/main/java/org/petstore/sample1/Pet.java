package org.petstore.sample1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
@Entity
public class Pet {

    @Id @GeneratedValue(strategy=GenerationType.AUTO) 
    protected Integer id;
    @Column
    protected String name;
    @Column
    protected String status;
    @Column
    protected String category;

	public Integer getId() {
		return id; 
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category; 
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", status=" + status + ", category=" + category + "]";
	}
}