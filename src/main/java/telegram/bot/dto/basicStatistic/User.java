package telegram.bot.dto.basicStatistic;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static telegram.bot.enums.Message.NOT_PUBLIC;

@Getter
@Setter
public class User {
    private String fullName;
    private String email;
    private String createdAt;
    private String profileURL;


    public String getCreatedAt() {
        if (this.createdAt.equals(NOT_PUBLIC.getMessage())) {
            return NOT_PUBLIC.getMessage();
        }
            var instant = java.time.Instant.parse(this.createdAt);
            var date = java.util.Date.from(instant);
            var formatter = new SimpleDateFormat("dd MMMMMMMM yyyy", Locale.ENGLISH);

            return formatter.format(date);
    }
}
