package controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.User;
import service.UserService;

@ManagedBean(name = "signin")
@SessionScoped
@RequestScoped
public class SinginController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	public SinginController() {
	}

	/**
	 * Yeni kullanıcı oluşturmak için kullanılır.
	 */
	public void addNewUser() {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

		// Kullanıcı daha önceden veritabanında kayıtlı olup olmadığına bakılır.
		if (UserService.selectUser(username) == null) {
			// Yeni kullanıcı veritabanına eklenir.
			UserService.addUser(user);
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("http://localhost:8080/project-management-system/faces/login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Username already exist.", "Please enter another username"));
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
