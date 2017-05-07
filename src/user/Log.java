package user;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
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

}
