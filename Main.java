import java.util.*;
import java.io.*;
import java.nio.file.*;

class Main {
  public static void main(String[] args) throws IOException{

    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    System.out.println("Insira o caminho do arquivo a ser compactado");

    String texto = myObj.nextLine();  // Read user input*/

    Path path = Path.of(texto);

    byte[] bytes = Files.readAllBytes(path);
    String a = new String(bytes);
    
    System.out.println("Insira o nome do arquivo compactado");

    String cod = myObj.nextLine();  // Read user input*/

    String codificado = cod + ".cod";

    Map<Character, Integer> fr = new HashMap<>();
    
    Btree huff = new Btree();

    char[] txt = a.toCharArray();

    for(char ch : txt){
      if (fr.get(ch) == null) {
        fr.put(ch, 1);
      } else {
        fr.put(ch, fr.get(ch) + 1);
      }
    }

    for(char key : fr.keySet()){
      Hnode no = new Hnode(key, fr.get(key));
      huff.add(no);
    }

    //huff.show();

    huff.create();    

    Map<Character, String> map = huff.createCodeMap();
    StringBuilder data = new StringBuilder();

    for(char c : txt){
      data.append(map.get(c));
    }

    try {
      byte[] huffmanBytes = huff.zip(data);
      OutputStream outStream = new FileOutputStream(codificado);
      ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);
      objectOutStream.writeObject(huffmanBytes);
      objectOutStream.writeObject(fr);
      objectOutStream.close();
      outStream.close();
    } catch (Exception e) { e.printStackTrace(); }

    /**/

    
  }
}