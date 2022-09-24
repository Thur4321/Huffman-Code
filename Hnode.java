import java.io.Serializable;
import java.util.*;

public class Hnode implements Serializable{

  private char v;
  private int f;
	private Hnode esq, dir;

  public Hnode(Hnode left, Hnode right){
		this.v = '+';
    f = left.getF() + right.getF();
		this.esq = left;
    this.dir = right;
	}

  public Hnode(char valor, int freq){
    v = valor;
    f = freq;
  }

  public void show(){
		System.out.println(v + " : " + f);
    if(esq != null) esq.show();
		if(dir != null) dir.show();
  }

  public Hnode getDir(){
    return dir;
  }

  public void setDir(Hnode dir){
    this.dir = dir;
  }

  public Hnode getEsq(){
    return esq;
  }

  public void setEsq(Hnode esq){
    this.esq = esq;
  }

  public char getV(){
    return v;
  }

  public void setV(char v){
    this.v = v;
  }

  public int getF(){
    return f;
  }

  public void setF(int f){
    this.f = f;
  }

  public void fillCodeMap(Map<Character, String> codemap, String work) {
        if (dir == null && esq == null) {
            codemap.put(getV(), work);
            return;
        }
        esq.fillCodeMap(codemap, work + "0");
        dir.fillCodeMap(codemap, work + "1");
    }
  
}