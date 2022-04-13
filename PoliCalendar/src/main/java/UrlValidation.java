public class UrlValidation {

    /** Lee links especificos que proporciona id y token*/
    static public boolean urlVerification(String url){
        String regex= "(https?)(://[a-z]+.?[a-z]{2,}.{1,})(/[a-z]+/)([a-z]+_[a-z]+.[php]+[?][a-z]+[=])([0-9]{1,})&[a-z]+[=]([a-z0-9A-Z]+&)(preset_what=all&preset_time=recentupcoming)";
        //(https?)(://[a-z]+.?[a-z]{2,}.{1,})([/]calendar[/]export_execute[.]php[?]userid[=])([0-9]{1,7}&authtoken=[a-zA-Z0-9]+&)(preset_what=all&preset_time=recentupcoming)
        return url.matches(regex);
    }

    static public void downloadFile(String url){

    }

}
