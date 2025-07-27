package telegram.bot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import telegram.bot.userCountStatistic.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByChatId(Long chatId);
}
