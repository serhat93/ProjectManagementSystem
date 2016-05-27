package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import config.SessionBean;
import model.Project;
import service.ProjectService;
import util.DataHolder;

@ManagedBean(name = "home", eager = true)
@SessionScoped
@ViewScoped
public class HomeController implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Project> projectList;

	/**
	 * Veritabanında kayıtlı olan projeler çekilir.
	 */
	@PostConstruct
	public void init() {
		projectList = ProjectService.getProjects();
	}

	/**
	 * Seçilen projenin listelenmesi için list sayfasına yönlendirmek için
	 * kullanılır.
	 */
	public void listPage(Project project) {
		try {
			//Seçili olan proje hafızaya alınır.
			DataHolder.setProject(project);

			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().redirect("http://localhost:8080/project-management-system/faces/list.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Project> getProjectList() {
		return projectList;
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
}
