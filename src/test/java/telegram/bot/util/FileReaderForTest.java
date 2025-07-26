package telegram.bot.util;

import lombok.AllArgsConstructor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
public class FileReaderForTest {
    private Path normalizeFilePath(String fileName) {
        return Paths.get(fileName)
                .toAbsolutePath().normalize();
    }

    public String readJsonNodeFromFile(String fileName) throws Exception {
        Path path = normalizeFilePath(fileName);
        return Files.readString(path).trim();
    }
}
