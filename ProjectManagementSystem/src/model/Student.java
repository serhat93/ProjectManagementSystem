package model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "no")
	private String no;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@ElementCollection
	@MapKeyColumn(name = "project_id")
	@Column(name = "grade")
	private Map<Project, Integer> grades = new HashMap<Project, Integer>();

	@Transient
	private String grade;

	public Student() {
	}

	public Student(String no, String name, String surname) {
		this.no = no;
		this.name = name;
		this.surname = surname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Map<Project, Integer> getGrades() {
		return grades;
	}

	public void setGrades(Map<Project, Integer> grades) {
		this.grades = grades;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
