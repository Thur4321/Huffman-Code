import java.util.*;
import java.io.*;
import java.nio.file.*;
@SuppressWarnings("unchecked")

class Main {
  public static void main(String[] args) throws IOException{

    Scanner myObj = new Scanner(System.in);  // Create a Scanner object
    System.out.println("Insira o nome do texto compactado");

    String cod = myObj.nextLine();  // Read user input*/

    System.out.println("Insira o nome do arquivo descompactado");

    String texto = myObj.nextLine();  // Read user input*/

    String textoFinal = texto + ".txt";

    try {
      FileInputStream inStream = new FileInputStream(cod);
      ObjectInputStream objectInStream = new ObjectInputStream(inStream);
      byte[] huffmanBytes = (byte[]) objectInStream.readObject();
      Map<Character, Integer> freq =
        (Map<Character, Integer>) objectInStream.readObject();
      objectInStream.close();
      Btree huff = new Btree();

      StringBuilder sb1 = new StringBuilder();
        for (int i=0; i<huffmanBytes.length; i++) {
          byte b = huffmanBytes[i];
          boolean flag = (i == huffmanBytes.length - 1);
          sb1.append(convertbyteInBit(!flag, b));
      }
      
      for(char key : freq.keySet()){
        Hnode no = new Hnode(key, freq.get(key));
        huff.add(no);
      }

      huff.create();

  
      String decod = huff.decode(sb1.toString());


      Path path = Path.of(textoFinal);

      Files.writeString(path, decod);

     
    } catch (Exception e) { e.printStackTrace(); }

  }

  private static String convertbyteInBit(boolean flag, byte b) {
      int byte0 = b;
      if (flag) byte0 |= 256;
      String str0 = Integer.toBinaryString(byte0);
      if (flag || byte0 < 0)
          return str0.substring(str0.length() - 8);
      else return str0;
    }
  
}