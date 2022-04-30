import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LinkLecture {

    /**
     *
     */
    public static void lectureICS(){
        FileInputStream fin;
        try {
            fin = new FileInputStream("icalexport.ics");
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        CalendarBuilder builder = new CalendarBuilder();
        Calendar calendar;
        try {
            calendar = builder.build(fin);
        } catch (IOException | ParserException ex) {
            throw new RuntimeException(ex);
        }

        for (Component c: calendar.getComponents()){
            for (Property property : c.getProperties()){
                switch (property.getName()){
                    case "UID": {
                        System.out.println("\n\nUID:\n"+property.getValue());
                        break;
                    }
                    case "SUMMARY": {
                        System.out.println("SUMMARY:\n"+property.getValue());
                        break;
                    }
                    case "DESCRIPTION": {
                        System.out.println("DESCRIPTION:\n"+property.getValue());
                        break;
                    }
                    case "DTEND": {
                        System.out.println("DTEND:\n"+property.getValue());
                        break;
                    }
                    case "CATEGORIES": {
                        System.out.println("CATEGORIES:\n"+property.getValue());
                        break;
                    }
                    default:
                        break;
                }
            }
        }
    }


}
