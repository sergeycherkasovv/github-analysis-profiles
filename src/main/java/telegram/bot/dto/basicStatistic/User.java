package telegram.bot.dto.basicStatistic;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class User {
    private String fullname;
    private String email;
    private Date createdAt;
    private URL profileURL;


    public String getCreatedAt() {
        var formatter = new SimpleDateFormat("dd MMMMMMMM yyyy");
        if (this.createdAt == null) {
            return null;
        }
        return formatter.format(createdAt);
    }
}
