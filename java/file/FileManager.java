package file;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import handlers.Handler;

public class FileManager implements Handler{

    private List<Handler> handlers;
    private Path path;

    private FileManager() {}

    public FileManager(Path path) {
        handlers = new ArrayList<>();
        this.path = path;
        if (!Files.isRegularFile(path)) {
            throw new IllegalArgumentException("It is not a file");
        }

    }

    public FileManager addHandler(Handler h) {
        this.handlers.add(h);
        return this;
    }

    @Override
    public String info() {
        StringBuilder sb = new StringBuilder();
        for (Handler h: handlers) {
            sb.append(h.info()).append(" ");
        }

        sb.append(path.getFileName().toString());
        return sb.toString();
    }
    
}
