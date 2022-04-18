import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ReadUrl {
    /**
     * Reconocimiento de URL validos para la obtención de calendario academico exportado.
     * @param url Enlace que se obtiene de la exportación del calendario
     * @return Un booleano que reconoce la validez del enlace especifico para exportar calendario de moodle
     */
    static public boolean isUrl(String url)
    {
        String regex= "(https?)(://[a-z]+.?[a-z]{2,}.{1,})(/[a-z]+/)([a-z]+_[a-z]+.[php]+[?][a-z]+[=])([0-9]{1,})&[a-z]+[=]([a-z0-9A-Z]+&)(preset_what=all&preset_time=recentupcoming)";
        //(https?)(://[a-z]+.?[a-z]{2,}.{1,})([/]calendar[/]export_execute[.]php[?]userid[=])([0-9]{1,7}&authtoken=[a-zA-Z0-9]+&)(preset_what=all&preset_time=recentupcoming)
        return url.matches(regex);
    }

    /**
     * Creación y/o escritura de archivo con datos obtenidos del url para exportar el calendario academico.
     * @param url Enlace que se obtiene de la exportación del calendario
     * @throws IOException
     */
    static public void writeFile(String url) throws IOException{
        if(isUrl(url)){
            URL urlChecked = new URL(url.toString());
            String data = IOUtils.toString(urlChecked, StandardCharsets.UTF_8);
            File file = new File("../PoliCalendarBot/file.tmp");
            FileUtils.writeStringToFile(file,data,StandardCharsets.UTF_8);
        }else{
            System.out.println("Link no valido");
        }

    }



}
