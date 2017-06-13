package diary;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/**
 * Klasa do obslugi cwiczen
 * @author Pawe³
 *
 */
public class Exercise implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	// private Obrazek obrazek;
	private String description;
	private String[] workingMuscles;
	private int record;
	private int recordReps;
	/**
	 * Konstruktor tworzacy cwiczenie
	 * @param name
	 * @param description
	 * @param workingMuscles
	 */
	public Exercise(String name, String description, String[] workingMuscles) {
		this.setName(name);
		this.setDescription(description);
		this.setWorkingMuscles(workingMuscles);
		this.setRecord(0);
		this.setRecordReps(0);
	}
	/**
	 * Konstruktor tworzacy puste cwiczenie
	 */
	public Exercise() {
	}
	/**
	 * Metoda zapisujacy cwiczenie
	 * @throws IOException
	 */
	public void saveExercise() throws IOException {
		new File("exercises/").mkdir();
		ObjectOutputStream file = null;
		try {
			file = new ObjectOutputStream(new FileOutputStream("exercises/" + name));
			file.writeObject(this);
			file.flush();
		} finally {
			if (file != null)
				file.close();
		}
	}
	/**
	 * Metoda odczytuj¹ca cwiczenie
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InvalidClassException
	 */
	public static Exercise readExercise(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException, InvalidClassException {
		new File("exercises/").mkdir();
		ObjectInputStream file = null;
		Exercise exercise = null;
		file = new ObjectInputStream(new FileInputStream("exercises/" + fileName));
		exercise = (Exercise) file.readObject();
		if (file != null) {
			file.close();
		}
		return exercise;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + record;
		result = prime * result + recordReps;
		result = prime * result + Arrays.hashCode(workingMuscles);
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
		Exercise other = (Exercise) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (record != other.record)
			return false;
		if (recordReps != other.recordReps)
			return false;
		if (!Arrays.equals(workingMuscles, other.workingMuscles))
			return false;
		return true;
	}
	/**
	 * Metoda pobierajaca liste wszystkich cwiczen
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static List<Exercise> downloadExercises() throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Exercise> list = new LinkedList<Exercise>();
		File folder = new File("exercises/");
		File[] listOfExercises = folder.listFiles();
		for (File file : listOfExercises) {
			if (file.isFile()) {
					list.add(Exercise.readExercise(file.getName()));
			}
		}
		return list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getWorkingMuscles() {
		return workingMuscles;
	}

	public void setWorkingMuscles(String[] workingMuscles) {
		this.workingMuscles = workingMuscles;
	}

	public int getRecord() {
		return record;
	}

	public void setRecord(int record) {
		this.record = record;
	}

	public int getRecordReps() {
		return recordReps;
	}

	public void setRecordReps(int recordReps) {
		this.recordReps = recordReps;
	}
	@Override
	public String toString() {
		return "Exercise [name=" + name + ", description=" + description + ", workingMuscles="
				+ Arrays.toString(workingMuscles) + ", record=" + record + ", recordReps=" + recordReps + "]";
	}

}
