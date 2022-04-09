import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TokenReader {

    public static String readUserName (){
        String salida = "";
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("userNamePoliCalendar.secret"));
            salida = buffer.readLine();
            buffer.close();
        }catch (FileNotFoundException e){
            System.out.println("Error: Fichero no encontrado");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salida;
    }

    public static String readToken (){
        String salida = "";
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("tokenPoliCalendar.secret"));
            salida = buffer.readLine();
            buffer.close();

        }catch (FileNotFoundException e){
            System.out.println("Error: Fichero no encontrado");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salida;
    }
}
