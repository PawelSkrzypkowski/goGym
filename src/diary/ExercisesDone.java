package diary;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class ExercisesDone implements Serializable {
	private Exercise exercise;
	private List<Set> sets;

	@Override
	public String toString(){
			return "(" + exercise.toString() + ", " + sets.toString() + ")";
	}

	public List<Set> getSets() {
		return sets;
	}

	public void setSets(List<Set> sets) {
		this.sets = sets;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

}
