package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.CascadeOnDelete;

@Entity
@CascadeOnDelete
@Table(name = "Project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "name")
	private String name;

	@CascadeOnDelete
	@OneToOne(targetEntity = Course.class, cascade = { CascadeType.ALL }, orphanRemoval = true)
	private Course course;

	@CascadeOnDelete
	@OneToMany(targetEntity = Groups.class, cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Groups> group;

	public Project() {
	}

	public Project(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Groups> getGroup() {
		return group;
	}

	public void setGroup(List<Groups> group) {
		this.group = group;
	}
}