package telegram.bot.services.basicStatisticService;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import telegram.bot.dto.basicStatistic.Follower;

@Service
public class FollowerService {

    public Follower create(JsonNode usernameGitHub) {
        var follower = new Follower();

        follower.setFollowers(usernameGitHub.path("followers").path("totalCount").asInt(0));
        follower.setFollowing(usernameGitHub.path("following").path("totalCount").asInt(0));

        return follower;
    }
}
