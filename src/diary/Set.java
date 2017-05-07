package diary;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Set implements Serializable{
	private Integer reps;
	private Boolean dropSet;
	private List<Double> weight;


	public Integer getReps() {
		return reps;
	}


	public void setReps(Integer reps) {
		this.reps = reps;
	}


	public Boolean getDropSet() {
		return dropSet;
	}


	public void setDropSet(Boolean dropSet) {
		this.dropSet = dropSet;
	}


	public List<Double> getWeight() {
		return weight;
	}


	public void setWeight(List<Double> weight) {
		this.weight = weight;
	}


	@Override
	public String toString(){
		String wartosc = new String();
		wartosc = "(" + reps.toString() + ", " + dropSet.toString() + ", " + weight.toString() + ")";
		return wartosc;
	}
}
