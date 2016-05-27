package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import config.SessionBean;
import model.Groups;
import model.Project;
import model.Student;
import service.StudentService;
import util.DataHolder;

@ManagedBean(name = "edit", eager = true)
@RequestScoped
@ViewScoped
public class EditController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Project project;
	private Groups group;
	private List<Student> studentList;

	/**
	 * Sayfa yüklenmeden gerekli olan değişkenlerin değerleri atanır.
	 */
	@PostConstruct
	public void init() {
		project = DataHolder.getProject();
		group = DataHolder.getGroup();
		studentList = group.getStudents();

		for (Student student : studentList) {
			if (student.getGrades().get(project) == null)
				student.setGrade("");
			else
				student.setGrade(String.valueOf(student.getGrades().get(project)));
		}
	}

	/**
	 * Öğrenci bilgileri güncellendikten sonra veritabanının güncellenmesi
	 * sağlanır.
	 */
	public void updateStudent(Student student) {
		if (student.getGrade() != null && student.getGrade() != "") {
			try {
				int grade = Integer.parseInt(student.getGrade());

				if (grade < 110 && grade > -1)
					student.getGrades().put(DataHolder.getProject(), Integer.parseInt(student.getGrade()));
				else
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Incorrect range. Please enter range -1 and 110 :) ", ""));

			} catch (NumberFormatException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Please enter only number.", ""));
			}
			StudentService.updateStudent(student);
		} else
			StudentService.updateStudent(student);
	}

	/**
	 * Öğrenciyi veritabanından silmek için kullanılır.
	 */
	public void deleteStudent(Student student) {
		group.getStudents().remove(student);
		studentList.remove(student);
		StudentService.deleteStudent(student.getId());
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	/**
	 * Kullanıcının sistemden çıkış yapmasını sağlamak için kullanılır.
	 */
	public void logout() {
		// Geçerli olan bağlantı alınır ve sonlandırılır.
		HttpSession session = SessionBean.getSession();
		session.invalidate();

		try {
			// Çıkış işleminden sonra login sayfasına yönlendirilir.
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("http://localhost:8080/project-management-system/faces/login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void back() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("http://localhost:8080/project-management-system/faces/list.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Project getProject() {
		return project;
	}

}
