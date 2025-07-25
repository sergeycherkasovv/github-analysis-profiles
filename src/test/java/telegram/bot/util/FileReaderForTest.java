package telegram.bot.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReaderForTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static Path normalizeFilePath(String fileName) {
        return Paths.get(fileName)
                .toAbsolutePath().normalize();
    }

    public static JsonNode readJsonNodeFromFile(String fileName) throws Exception {
        Path path = normalizeFilePath(fileName);
        String content = Files.readString(path).trim();

        return objectMapper.readTree(content);
    }
}
