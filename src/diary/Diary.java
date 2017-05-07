package diary;

import java.io.EOFException;
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

	public int showTrainingTime() {
		double time = (finishDate.getTime() - startDate.getTime()) / 60000.0;
		return (int) time;
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
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
			file = new ObjectOutputStream(new FileOutputStream(format.format(startDate)));
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
		try {
			file = new ObjectInputStream(new FileInputStream(fileName));
			diary = (Diary) file.readObject();

		} catch (EOFException ex) {
			System.out.println("End of file");
		}

		finally {
			if (file != null)
				file.close();
		}
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

}
