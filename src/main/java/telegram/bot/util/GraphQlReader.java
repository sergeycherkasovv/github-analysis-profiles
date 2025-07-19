package telegram.bot.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class GraphQlReader {

    public String reading(String fileName) {
        try {
            var resource = new ClassPathResource("graphql/" + fileName + ".graphql");
            var bytes = resource.getInputStream().readAllBytes();
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Cannot load GraphQL file: " + fileName, e);
        }
    }
}
