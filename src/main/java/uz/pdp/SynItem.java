package uz.pdp;

import com.google.gson.annotations.SerializedName;

public class SynItem{

	@SerializedName("gen")
	private String gen;

	@SerializedName("pos")
	private String pos;

	@SerializedName("text")
	private String text;

	@SerializedName("fr")
	private int fr;

	public void setGen(String gen){
		this.gen = gen;
	}

	public String getGen(){
		return gen;
	}

	public void setPos(String pos){
		this.pos = pos;
	}

	public String getPos(){
		return pos;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setFr(int fr){
		this.fr = fr;
	}

	public int getFr(){
		return fr;
	}

	@Override
 	public String toString(){
		return 
			"SynItem{" + 
			"gen = '" + gen + '\'' + 
			",pos = '" + pos + '\'' + 
			",text = '" + text + '\'' + 
			",fr = '" + fr + '\'' + 
			"}";
		}
}