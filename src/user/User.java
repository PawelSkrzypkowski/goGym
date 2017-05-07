package user;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class User implements Serializable {
	private Date startDate = new Date();
	private String firstName;
	private String lastName;
	private Date birthDate;
	private List<Log> logs;

	public User() {
		this.setLogs(new LinkedList<Log>());
	}

	public User(String firstName, String lastName, Date birthDate) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirthDate(birthDate);
		this.setLogs(new LinkedList<Log>());
	}

	public void saveUser() throws IOException {
		ObjectOutputStream file = null;
		try {
			file = new ObjectOutputStream(new FileOutputStream("user"));
			file.writeObject(this);
			file.flush();
		} finally {
			if (file != null)
				file.close();
		}
	}

	public static User readUser() throws IOException, ClassNotFoundException {
		ObjectInputStream file = null;
		User user = null;
		try {
			file = new ObjectInputStream(new FileInputStream("user"));
			user = (User) file.readObject();

		} catch (EOFException ex) {
			System.out.println("End of file");
		}

		finally {
			if (file != null) {
				file.close();
			}
		}
		return user;
	}

	public int calculateAge() {
		Calendar today = Calendar.getInstance();
		Calendar birthDate = Calendar.getInstance();

		int age = 0;

		birthDate.setTime(this.getBirthDate());
		if (birthDate.after(today)) {
			throw new IllegalArgumentException("Can't be born in the future");
		}

		age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
		if ((birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3)
				|| (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {
			age--;
		} else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH))
				&& (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {
			age--;
		}

		return age;
	}

	public void addLog(Log log) {
		logs.add(log);
	}

	public void removeLog(int index) {
		logs.remove(index);
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}
}
