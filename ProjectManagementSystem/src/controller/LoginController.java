package controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import config.SessionBean;
import model.User;
import service.UserService;

@ManagedBean(name = "login")
@SessionScoped
@RequestScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	public LoginController() {
	}

	/**
	 * Kullanıcı girişi için kullanılır.
	 */
	public void login() {

		User user = UserService.selectUser(username);
		// Kullanıcı adı ve şifresi doğru olup olmadığı kontrol edilir.
		if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
			// O anki bağlantı alınır ve kullanıcı kayıt edilir.
			HttpSession session = SessionBean.getSession();
			session.setAttribute("username", user);
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("http://localhost:8080/project-management-system/faces/home.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Password", "Please enter correct username and Password"));
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
