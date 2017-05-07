package diary;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class Workout implements Serializable {
	private String workoutName;
	private List<Exercise> exercises;
	private List<Integer> setsNumber;
	private List<Integer> rest;
	private String workoutDescription;
	private String workoutType;
	private String difficultyLevel;

	public void saveWorkout() {
		ObjectOutputStream file = null;
		try {
			file = new ObjectOutputStream(new FileOutputStream("workouts/" + workoutName));
			file.writeObject(this);
			file.flush();
			if (file != null)
				file.close();
		}
		catch(IOException e){
			
		}
	}

	public static Workout readWorkout(String fileName) throws IOException, ClassNotFoundException {
		ObjectInputStream file = null;
		Workout workout = null;
		if(new File("workouts/" + fileName).exists() == true)
			try {
				file = new ObjectInputStream(new FileInputStream("workouts/" + fileName));
				workout = (Workout) file.readObject();
				if (file != null) {
					file.close();
				}
			} catch (EOFException ex) {
				System.out.println("End of file");
			}
		return workout;
	}
	public void changeWorkoutProperties(String name, String description, String type, String level){
		if(!getWorkoutDescription().equals(description))
			setWorkoutDescription(description);
		if(!getWorkoutType().equals(type))
			setWorkoutType(type);
		if(!getDifficultyLevel().equals(level))
			setDifficultyLevel(level);
		if(!getWorkoutName().equals(name)){
			deleteWorkout();
			setWorkoutName(name);
		}
		saveWorkout();
	}
	public boolean checkIfWorkoutExist(String name) throws ClassNotFoundException, IOException{//sprawdza czy po zmianie nazwy nie zostanie nadpisany inny plan
		if(name.equals(getWorkoutName()))//jesli nazwa nie zostala zmieniona
			return false;
		if(Workout.readWorkout(name) == null)
			return false;
		return true;
	}
	public void deleteWorkout() {
		try {
			Files.delete(Paths.get("workouts/" + getWorkoutName()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void editExercise(int index, Exercise exercise, int setsNumber, int rest) {
		getExercises().set(index, exercise);
		getSetsNumber().set(index, setsNumber);
		getRest().set(index, rest);
		saveWorkout();
	}
	public void moveUpExercise(int index) {
		Exercise tempExercise = getExercises().get(index);
		Integer tempSetsNumber = getSetsNumber().get(index);
		Integer tempRest = getRest().get(index);
		editExercise(index, getExercises().get(index - 1), getSetsNumber().get(index - 1), getRest().get(index - 1));
		editExercise(index - 1, tempExercise, tempSetsNumber, tempRest);
	}
	public void moveDownExercise(int index) {
		Exercise tempExercise = getExercises().get(index);
		Integer tempSetsNumber = getSetsNumber().get(index);
		Integer tempRest = getRest().get(index);
		editExercise(index, getExercises().get(index + 1), getSetsNumber().get(index + 1), getRest().get(index + 1));
		editExercise(index + 1, tempExercise, tempSetsNumber, tempRest);
	}
	public Workout() {
		setExercises(new LinkedList<Exercise>());
		setSetsNumber(new LinkedList<Integer>());
		setRest(new LinkedList<Integer>());
	}

	public Workout(String name, String workoutDescription, String workoutType, String difficultyLevel) {
		setExercises(new LinkedList<Exercise>());
		setSetsNumber(new LinkedList<Integer>());
		setRest(new LinkedList<Integer>());
		this.setWorkoutName(name);
		this.setWorkoutDescription(workoutDescription);
		this.setWorkoutType(workoutType);
		this.setDifficultyLevel(difficultyLevel);
	}

	public void addItemAtTheEnd(Exercise exercise, Integer setsNumber, Integer rest) {
		this.exercises.add(exercise);
		this.setsNumber.add(setsNumber);
		this.rest.add(rest);
		saveWorkout();
	}

	public void addItemAtTheBeginning(Exercise exercise, Integer setsNumber, Integer rest) {
		this.exercises.add(0, exercise);
		this.setsNumber.add(0, setsNumber);
		this.rest.add(0, rest);
		saveWorkout();
	}

	public void addItemAfter(Exercise exercise, Integer setsNumber, Integer rest, int index) {
		this.exercises.add(index, exercise);
		this.setsNumber.add(index, setsNumber);
		this.rest.add(index, rest);
		saveWorkout();
	}
	public void deleteItem(int index){
		this.exercises.remove(index);
		this.setsNumber.remove(index);
		this.rest.remove(index);
		saveWorkout();
	}

	public String getWorkoutName() {
		return workoutName;
	}

	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	public List<Integer> getSetsNumber() {
		return setsNumber;
	}

	public void setSetsNumber(List<Integer> setsNumber) {
		this.setsNumber = setsNumber;
	}

	public List<Integer> getRest() {
		return rest;
	}

	public void setRest(List<Integer> rest) {
		this.rest = rest;
	}

	public String getWorkoutDescription() {
		return workoutDescription;
	}

	public void setWorkoutDescription(String workoutDescription) {
		this.workoutDescription = workoutDescription;
	}

	public String getWorkoutType() {
		return workoutType;
	}

	public void setWorkoutType(String workoutType) {
		this.workoutType = workoutType;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}


}
