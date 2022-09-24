public class Bnode{
	private Hnode no;
  private int f;
	private Bnode esq, dir;

  public Bnode(Hnode no){
    this.no = no;
    f = no.getFreq();
    esq = dir = null;
  }

  public void add(Hnode no){
		if(no.getFreq() == f){
			if(esq != null) esq.add(no);
			else esq = new Bnode(no);
		}
		else{
			if(dir != null) dir.add(no);
			else dir = new Bnode(no);
		}
	}

  public void show(){
    if(dir != null) dir.show();
    System.out.println(no.getV() + " : " + f);
		if(esq != null) esq.show();
  }

  public Bnode getDir(){
    return dir;
  }

  public void setDir(Bnode dir){
    this.dir = dir;
  }

  public Bnode getEsq(){
    return esq;
  }

  public void setEsq(Bnode esq){
    this.esq = esq;
  }

  public Hnode getNo(){
    return no;
  }

  public void setNo(Hnode no){
    this.no = no;
  }

  public int getF(){
    return f;
  }

  public void setF(int f){
    this.f = f;
  }

  public Hnode getMin(){
    if(esq != null) return esq.getMin();
    else return no;
  }

}
