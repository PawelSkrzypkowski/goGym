package diary;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ExercisesDone implements Serializable {
	private static final long serialVersionUID = 1L;
	private Exercise exercise;
	private List<Set> sets;
	
	public ExercisesDone(){
		setSets(new LinkedList<Set>());
	}
	public ExercisesDone(Exercise exercise){
		setSets(new LinkedList<Set>());
		setExercise(exercise);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exercise == null) ? 0 : exercise.hashCode());
		result = prime * result + ((sets == null) ? 0 : sets.hashCode());
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
		ExercisesDone other = (ExercisesDone) obj;
		if (exercise == null) {
			if (other.exercise != null)
				return false;
		} else if (!exercise.equals(other.exercise))
			return false;
		if (sets == null) {
			if (other.sets != null)
				return false;
		} else if (!sets.equals(other.sets))
			return false;
		return true;
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
	@Override
	public String toString() {
		return "ExercisesDone [exercise=" + exercise + ", sets=" + sets + "]";
	}

}
