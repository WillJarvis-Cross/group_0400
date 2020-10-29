import java.util.ArrayList;
import java.util.List;
import java.lang.String;
public class Event {
    private double time;
    private User Speaker, Organzier;
    private List<User> attending;

    public Event(double time, User speaker, User organzier) {
        this.time = time;
        Speaker = speaker;
        Organzier = organzier;
        this.attending = new ArrayList<>();
    }
}
