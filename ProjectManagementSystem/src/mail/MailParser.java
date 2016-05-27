package mail;

import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;

import model.Course;
import model.Groups;
import model.Project;
import model.Student;
import service.CourseService;
import service.GroupService;
import service.ProjectService;
import service.StudentService;

public class MailParser {

	public static void parseMessage(Message message) {
		Multipart mp;
		try {
			mp = (Multipart) message.getContent();
			// Mesajın içeriği alınır.
			String messageContent = (String) mp.getBodyPart(0).getContent();
			// Mesaj satırları bölünür.
			String[] contentParts = messageContent.split("\r\n");

			if (!contentParts[0].equals("Merhaba Hocam,"))
				return;

			String[] lessonANDProject = contentParts[1].split(" ");

			String lessonName = "";
			String projectName = "";

			// Dersin ve projenin adı ayrıştırılarak değişkenlere atanır.
			for (int i = 0; i < lessonANDProject.length; i++) {
				if (lessonANDProject[i].equals("dersinin")) {
					for (int j = i + 1; j < lessonANDProject.length; j++) {
						if (lessonANDProject[j].equals("grup"))
							break;
						projectName += lessonANDProject[j];
					}
					break;
				}
				lessonName += lessonANDProject[i];
			}

			System.out.println("Dersin Adı: " + lessonName);
			System.out.println("Proje Adı: " + projectName);

			// Dersin veritabanında olup olmadığı kontrol edilir.
			// Yok ise yeni ders oluşturularak veritabanına eklenir.
			Course course = CourseService.selectCourse(lessonName);
			if (course == null) {
				course = new Course(lessonName);
				CourseService.addCourse(course);
			}

			// Projenin veritabanında olup olmadığı kontrol edilir.
			// Yok ise yeni proje oluşturularak veritabanına eklenir.
			Project project = ProjectService.selectProject(projectName);
			if (project == null || !project.getCourse().getName().equals(course.getName())) {
				project = new Project(projectName);
				project.setCourse(course);
				ProjectService.addProject(project);
			}

			// Mailin içeriğine uygun olarak öğrenciler oluşturulur ve bir gruba
			// eklenir.
			// Bu grup ve öğrenciler veritabanına eklenir.
			Groups group = new Groups();
			Student student;
			for (int i = 2; i < contentParts.length; i++) {
				String[] noANDname = contentParts[i].split("-");
				String no = noANDname[0];
				String[] name = noANDname[1].split(" ");
				System.out.println("Öğrenci No: " + no + " && Öğrenci Adı: " + name);
				student = new Student(no, name[0], name[1]);
				StudentService.addStudent(student);
				group.getStudents().add(student);
			}

			GroupService.addGroup(group);
			project.getGroup().add(group);
			// Projeye grup eklendikten sonra proje güncellenir.
			ProjectService.updateProject(project);

		} catch (IOException | MessagingException e) {
			e.printStackTrace();
		}

	}

}
