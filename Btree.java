import java.util.*;
import java.io.Serializable;

public class Btree implements Serializable{
	private Bnode raiz;
  private Hnode root;
	
	public Btree(){
		raiz = null;
    root = null;
	}
  
  public Bnode getRaiz(){
    return raiz;
  }

  public Hnode getRoot(){
    return root;
  }
  
	public void add(Hnode no){
		if(raiz != null){
			raiz.add(no);
		} else{
			raiz = new Bnode(no);
		}
	}

  public void show(){
		if(raiz != null) raiz.show();
	}

  public void showBinary(){
		if(root != null) root.show();
	}

  public Bnode removeRaiz(Bnode no){
		if(no != null){
      if(no.getEsq() != null){
        no.setEsq(removeRaiz(no.getEsq()));
        return no;
      }else{
        if(no.getDir() != null) no = no.getDir();
        else no = null;
        return no;
      }
    }
    else return null;
	}

  public boolean isEmpty(){
    return raiz == null;
  }

  public Hnode getMin(){
    if (raiz != null) return raiz.getMin();
    else return null;
  }

  public Hnode tree(){
    while (true){
      Hnode m1 = getMin();
      raiz = removeRaiz(raiz);
      Hnode m2 = getMin();
      raiz = removeRaiz(raiz);
      
      Hnode pai = new Hnode(m1, m2);
    
      if (isEmpty()) {
        return pai;
      }
      add(pai);
    }
  }

  public void create(){
    root = tree();
  }

  public Map<Character, String> createCodeMap() {
        Map<Character, String> result = new TreeMap<>();
        root.fillCodeMap(result, "");
        return result;
    }

  
  public byte[] zip(StringBuilder strBuilder){
    int length=(strBuilder.length()+7)/8;
    byte[] huffCodeBytes = new byte[length];
    int idx = 0;
    for (int i = 0; i < strBuilder.length(); i += 8) {
      String strByte;
      if (i + 8 > strBuilder.length())
          strByte = strBuilder.substring(i);
      else strByte = strBuilder.substring(i, i + 8);
      huffCodeBytes[idx] = (byte) Integer.parseInt(strByte, 2);
      idx++;
    }
    return huffCodeBytes;
  }

}