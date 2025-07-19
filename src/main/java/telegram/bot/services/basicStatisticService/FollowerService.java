package telegram.bot.services.basicStatisticService;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import telegram.bot.dto.basicStatistic.Follower;

@Service
public class FollowerService {

    public Follower createFollower(JsonNode username) {
        var follower = new Follower();

        follower.setFollowers(username.path("followers").path("totalCount").asInt(0));
        follower.setFollowing(username.path("following").path("totalCount").asInt(0));

        return follower;
    }
}
