package telegram.bot.util;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class NormalizeUsername {
    public String normalized (String username) {
        if (username == null || username.isBlank()) {
            return "";
        }

        if(username.startsWith("@")) {
            return username.substring(1);
        }

        if (username.startsWith("https://github.com/")) {
            try {
                var uri = new URI(username);
                var path = uri.getPath();

                if(path != null && path.length() > 1) {
                    var segments = path.split("/");
                    return segments[1];
                }
            } catch (URISyntaxException e) {
                return  "Invalid URI: " + username + e;
            }
        }

        return username;
    }
}
