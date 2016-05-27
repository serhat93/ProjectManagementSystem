package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import config.SessionBean;
import mail.MailReceiver;
import model.Groups;
import model.Project;
import model.Student;
import service.GroupService;
import util.DataHolder;

@ManagedBean(name = "list", eager = true)
@RequestScoped
@ViewScoped
@SessionScoped
public class ListController implements Serializable {

	private static final long serialVersionUID = 1L;

	private Project project;
	private List<Groups> groupList;

	/**
	 * Sayfa yüklenmeden önce home sayfasında seçilmiş olan project ile projenin
	 * grupları alınır.
	 */
	@PostConstruct
	public void init() {
		project = DataHolder.getProject();
		groupList = project.getGroup();
	}

	/**
	 * Seçilen grubun düzenlenmesi için edit sayfasına yönlendirme yapılır.
	 */
	public void editGroup(Groups group) {
		try {
			// Seçili olan grup hafızaya alınır.
			DataHolder.setGroup(group);

			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("http://localhost:8080/project-management-system/faces/edit.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Seçili olan grubu veritabanından silmek için kullanılır.
	 */
	public void deleteGroup(Groups group) {
		project.getGroup().remove(group);
		GroupService.deleteGroup(group.getId());
	}

	/**
	 * Öğrencilerin o anki projeye ait notu döndürür.
	 */
	public String getStudentGrade(Student student) {
		if (student.getGrades().get(project) == null)
			return "";

		int grade = student.getGrades().get(project);
		return String.valueOf(grade);
	}

	/**
	 * Maillerin kontrol edilmesi için kullanılır.
	 */
	public void checkMails() {
		String host = "pop.gmail.com";
		String mailStoreType = "pop3";
		String username = "sytms2016@gmail.com";
		String password = "syt12345";

		MailReceiver mailReceiver = new MailReceiver(host, mailStoreType, username, password);
		mailReceiver.check();
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

	/**
	 * Bir önceki sayfaya yönlendirmek için kullanılır.
	 */
	public void back() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("http://localhost:8080/project-management-system/faces/home.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Project getProject() {
		return project;
	}

	public List<Groups> getGroupList() {
		return groupList;
	}
}
