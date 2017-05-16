package diary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("serial")
public class Diary implements Serializable {
	private Date startDate;
	private Date finishDate;
	private List<ExercisesDone> exercisesDone;
	private Integer restTime;//seconds
	
	public static LinkedList<Diary> downloadDiaries() throws ClassNotFoundException, IOException {
		LinkedList<Diary> diary = new LinkedList<Diary>();
		File folder = new File("diary/");
		File[] listOfDiaries = folder.listFiles();
		for (File file : listOfDiaries) {
			if (file.isFile()) {
				diary.add(Diary.readDiary(file.getName()));
			}
		}
		return diary;
	}
	public static Double getMonthlyRaisedWeight(int minusMonth) throws ClassNotFoundException, IOException{//dzisiejszy miesiac - minusMonth
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		if(Calendar.MONTH > minusMonth){
			cal1.set(Calendar.YEAR, Calendar.MONTH - minusMonth, 1, 0, 0, 0);
		}
		else{
			int minus = Math.abs(Calendar.MONTH - minusMonth);
			cal1.set(Calendar.YEAR - 1, 12 - minus, 1, 0, 0, 0);
		}
		if(Calendar.MONTH > minusMonth - 1){
			cal2.set(Calendar.YEAR, Calendar.MONTH - minusMonth - 1, 1, 0, 0, 0);
		}
		else{
			int minus = Math.abs(Calendar.MONTH - minusMonth + 1);
			cal2.set(Calendar.YEAR - 1, 12 - minus, 1, 0, 0, 0);
		}
		System.out.println(cal1.getTime().toString());
		System.out.println(cal2.getTime().toString());
		LinkedList<Diary> diary = downloadDiaries();
		Double weight = 0.0;
		for(Diary d : diary){
			if(d.getStartDate().before(cal2.getTime()) && d.getStartDate().after(cal1.getTime()))
				weight += d.showRaisedWeight();
		}
		return weight;
	}
	public static TreeMap<Date, Double> getMapDateRecord(Exercise exercise) throws ClassNotFoundException, IOException{
		LinkedList<Diary> diaries = downloadDiaries();
		TreeMap<Date, Double> hm = new TreeMap<Date, Double>();
		for(Diary diary : diaries){
			Double max = 0.0;
			for(ExercisesDone ed : diary.getExercisesDone()){
				if(ed.getExercise().equals(exercise)){
					for(Set set : ed.getSets()){
						for(Double weight : set.getWeight()){
							if(max < weight)
								max = weight;
						}
					}
				}
			}
			if(!max.equals(0.0)){
				hm.put(diary.getStartDate(), max);
			}
		}
		return hm;
	}
	
	public Integer showTrainingTime() {
		double time = (finishDate.getTime() - startDate.getTime()) / 60000.0;
		return (int) time;
	}
	
	public void increaseRestTime(int rest){
		setRestTime(getRestTime() + rest);
	}
	
	public Diary(){
		startDate = new Date();
		exercisesDone = new LinkedList<ExercisesDone>();
		restTime = 0;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exercisesDone == null) ? 0 : exercisesDone.hashCode());
		result = prime * result + ((finishDate == null) ? 0 : finishDate.hashCode());
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
		Diary other = (Diary) obj;
		if (exercisesDone == null) {
			if (other.exercisesDone != null)
				return false;
		} else if (!exercisesDone.equals(other.exercisesDone))
			return false;
		if (finishDate == null) {
			if (other.finishDate != null)
				return false;
		} else if (!finishDate.equals(other.finishDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	public double showRaisedWeight() {
		double weight = 0;
		for (int i = 0; i < exercisesDone.size(); i++)
			for (int j = 0; j < exercisesDone.get(i).getSets().size(); j++)
				for (int k = 0; k < exercisesDone.get(i).getSets().get(j).getWeight().size(); k++)
					weight += exercisesDone.get(i).getSets().get(j).getWeight().get(k);
		return weight;
	}

	public void saveDiary() throws IOException {
		ObjectOutputStream file = null;
		new File("diary/").mkdir();
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
			file = new ObjectOutputStream(new FileOutputStream("diary/" + format.format(startDate)));
			file.writeObject(this);
			file.flush();
		} finally {
			if (file != null)
				file.close();
		}
	}

	public static Diary readDiary(String fileName) throws IOException, ClassNotFoundException {
		ObjectInputStream file = null;
		Diary diary = null;
		new File("diary/").mkdir();
		file = new ObjectInputStream(new FileInputStream("diary/" + fileName));
		diary = (Diary) file.readObject();
		if (file != null)
			file.close();
		return diary;

	}

	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat();
		return format.format(startDate) + ", " + format.format(finishDate) + ", " + exercisesDone.toString();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public List<ExercisesDone> getExercisesDone() {
		return exercisesDone;
	}

	public void setExercisesDone(List<ExercisesDone> exercisesDone) {
		this.exercisesDone = exercisesDone;
	}
	
	public Integer getRestTime() {
		return restTime;
	}

	public void setRestTime(Integer restTime) {
		this.restTime = restTime;
	}
}
