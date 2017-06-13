package diary;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
/**
 * Klasa do tworzenia wykonanych serii
 * @author Pawe³
 *
 */
public class Set implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer reps;
	private Boolean dropSet;
	private List<Double> weight;


	public Integer getReps() {
		return reps;
	}
	/**
	 * Konstruktor tworzacy obiekt z dropSetem
	 * @param reps
	 * @param dropSet
	 * @param weight
	 */
	public Set(Integer reps, Boolean dropSet, List<Double> weight){
		setReps(reps);
		setDropSet(dropSet);
		setWeight(weight);
	}
	/**
	 * Konstruktor tworzacy obiekt bez dropsetu
	 * @param reps
	 * @param weight
	 */
	public Set(Integer reps, Double weight){
		setReps(reps);
		setDropSet(false);
		List<Double> list = new LinkedList<Double>();
		for(int i=0;i<reps;i++)
			list.add(weight);
		setWeight(list);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dropSet == null) ? 0 : dropSet.hashCode());
		result = prime * result + ((reps == null) ? 0 : reps.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
		Set other = (Set) obj;
		if (dropSet == null) {
			if (other.dropSet != null)
				return false;
		} else if (!dropSet.equals(other.dropSet))
			return false;
		if (reps == null) {
			if (other.reps != null)
				return false;
		} else if (!reps.equals(other.reps))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
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
	public String toString() {
		return "Set [reps=" + reps + ", dropSet=" + dropSet + ", weight=" + weight + "]";
	}
}
