import java.util.*;
import java.io.Serializable;

public class Btree implements Serializable{
	private Bnode raiz;
  private Hnode root;
  private String codificado;
	
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

  public String decode(String data) {
        Hnode current = root;

    char[] txt = data.toCharArray();

        StringBuilder result = new StringBuilder();
        for (char ch : txt) {
            if (ch == '0') {
                current = current.getEsq();
            } else {
                current = current.getDir();
            }

            if (current.getDir() == null  && current.getEsq() == null) {
                result.append(current.getV());
                current = root;
            }
        }
        return result.toString();
    }

}