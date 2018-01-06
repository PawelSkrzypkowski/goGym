package model.user;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
/**
 * Klasa do obslugi uzytkownika i jego pomiarow
 * @author Paweł
 *
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date startDate = new Date();
	private String firstName;
	private String lastName;
	private Date birthDate;
	private List<Log> logs;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((logs == null) ? 0 : logs.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (logs == null) {
			if (other.logs != null)
				return false;
		} else if (!logs.equals(other.logs))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	public User() {
		this.setLogs(new LinkedList<Log>());
	}

	public User(String firstName, String lastName, Date birthDate) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setBirthDate(birthDate);
		this.setLogs(new LinkedList<Log>());
	}
	/**
	 * Metoda zapisujaca uzytkownika
	 * @throws IOException
	 */
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
	/**
	 * Metoda odczytujaca uzytkownika
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InvalidClassException
	 */
	public static User readUser() throws FileNotFoundException, IOException, ClassNotFoundException, InvalidClassException {
		ObjectInputStream file = null;
		User user = null;
		file = new ObjectInputStream(new FileInputStream("user"));
		user = (User) file.readObject();
		if (file != null)
			file.close();
		return user;
	}
	/**
	 * Metoda obliczajca wiek uzytkownika
	 * @return
	 */
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
	/**
	 * Metoda zwracjaca mape Data - Pomiar
	 * @param logName
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public TreeMap<Date, Float> getDateLogMap(String logName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		TreeMap<Date, Float> map = new TreeMap<Date, Float>();
		for(Log l : logs){
			Method method = l.getClass().getMethod("get" + logName);
			map.put(l.getMensurationDate(), (Float)method.invoke(l));
		}
		return map;
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

	@Override
	public String toString() {
		return "User [startDate=" + startDate + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", logs=" + logs + "]";
	}
}
