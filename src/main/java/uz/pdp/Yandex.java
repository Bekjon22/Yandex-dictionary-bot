package uz.pdp;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Yandex{

	@SerializedName("head")
	private Head head;

	@SerializedName("def")
	private List<DefItem> def;

	public void setHead(Head head){
		this.head = head;
	}

	public Head getHead(){
		return head;
	}

	public void setDef(List<DefItem> def){
		this.def = def;
	}

	public List<DefItem> getDef(){
		return def;
	}

	@Override
 	public String toString(){
		return 
			"Yandex{" + 
			"head = '" + head + '\'' + 
			",def = '" + def + '\'' + 
			"}";
		}
}