package handlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import options.OptionsMapper;

public class CountBytesHandler implements Handler{

    private String info;
    private List<String> options = Arrays.asList("-c","--bytes");
    private OptionsMapper mapper;
    private Path path;

    public CountBytesHandler(OptionsMapper mapper, Path path) {
        info="";
        this.mapper = mapper;
        this.path = path;
    }

    private void execute() {
        Optional<String> has = options
            .stream()
            .filter(op-> {
                return this.mapper.hasOption(op);}
            )
            .findAny();

        if (!has.isPresent()) return;

        try {
            byte[] bytes = Files.readAllBytes(path);
            info = bytes.length+"";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String info() {
        execute();
        return info;
    }
    
}
