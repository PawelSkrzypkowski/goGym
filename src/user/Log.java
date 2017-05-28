package user;

import java.io.Serializable;
import java.util.Date;

public class Log implements Serializable {
	private Date mensurationDate = new Date();
	private float weight;
	private float neck;
	private float chest;
	private float biceps;
	private float waist;
	private float stomach;
	private float hips;
	private float thigh;
	private float calf;
	private static final long serialVersionUID = 1L;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(biceps);
		result = prime * result + Float.floatToIntBits(calf);
		result = prime * result + Float.floatToIntBits(chest);
		result = prime * result + Float.floatToIntBits(hips);
		result = prime * result + ((mensurationDate == null) ? 0 : mensurationDate.hashCode());
		result = prime * result + Float.floatToIntBits(neck);
		result = prime * result + Float.floatToIntBits(stomach);
		result = prime * result + Float.floatToIntBits(thigh);
		result = prime * result + Float.floatToIntBits(waist);
		result = prime * result + Float.floatToIntBits(weight);
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
		Log other = (Log) obj;
		if (Float.floatToIntBits(biceps) != Float.floatToIntBits(other.biceps))
			return false;
		if (Float.floatToIntBits(calf) != Float.floatToIntBits(other.calf))
			return false;
		if (Float.floatToIntBits(chest) != Float.floatToIntBits(other.chest))
			return false;
		if (Float.floatToIntBits(hips) != Float.floatToIntBits(other.hips))
			return false;
		if (mensurationDate == null) {
			if (other.mensurationDate != null)
				return false;
		} else if (!mensurationDate.equals(other.mensurationDate))
			return false;
		if (Float.floatToIntBits(neck) != Float.floatToIntBits(other.neck))
			return false;
		if (Float.floatToIntBits(stomach) != Float.floatToIntBits(other.stomach))
			return false;
		if (Float.floatToIntBits(thigh) != Float.floatToIntBits(other.thigh))
			return false;
		if (Float.floatToIntBits(waist) != Float.floatToIntBits(other.waist))
			return false;
		if (Float.floatToIntBits(weight) != Float.floatToIntBits(other.weight))
			return false;
		return true;
	}
	public Log(){}
	public Log(float weight, float neck, float chest, float biceps, float waist, float stomach, float hips, float thigh, float calf){
		this.setWeight(weight);
		this.setNeck(neck);
		this.setChest(chest);
		this.setBiceps(biceps);
		this.setWaist(waist);
		this.setStomach(stomach);
		this.setHips(hips);
		this.setThigh(thigh);
		this.setCalf(calf);
	}
	public Log(Float[] log){
		this.setWeight(log[0]);
		this.setNeck(log[1]);
		this.setChest(log[2]);
		this.setBiceps(log[3]);
		this.setWaist(log[4]);
		this.setStomach(log[5]);
		this.setHips(log[6]);
		this.setThigh(log[7]);
		this.setCalf(log[8]);
	}
	public Date getMensurationDate() {
		return mensurationDate;
	}
	public void setMensurationDate(Date mensurationDate) {
		this.mensurationDate = mensurationDate;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public float getNeck() {
		return neck;
	}
	public void setNeck(float neck) {
		this.neck = neck;
	}
	public float getChest() {
		return chest;
	}
	public void setChest(float chest) {
		this.chest = chest;
	}
	public float getBiceps() {
		return biceps;
	}
	public void setBiceps(float biceps) {
		this.biceps = biceps;
	}
	public float getWaist() {
		return waist;
	}
	public void setWaist(float waist) {
		this.waist = waist;
	}
	public float getStomach() {
		return stomach;
	}
	public void setStomach(float stomach) {
		this.stomach = stomach;
	}
	public float getHips() {
		return hips;
	}
	public void setHips(float hips) {
		this.hips = hips;
	}
	public float getThigh() {
		return thigh;
	}
	public void setThigh(float thigh) {
		this.thigh = thigh;
	}
	public float getCalf() {
		return calf;
	}
	public void setCalf(float calf) {
		this.calf = calf;
	}
	@Override
	public String toString() {
		return "Log [mensurationDate=" + mensurationDate + ", weight=" + weight + ", neck=" + neck + ", chest=" + chest
				+ ", biceps=" + biceps + ", waist=" + waist + ", stomach=" + stomach + ", hips=" + hips + ", thigh="
				+ thigh + ", calf=" + calf + "]";
	}

}
