package handlers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import options.OptionsMapper;

public class CountLinesHandler extends Handler{

    private String info;
    private OptionsMapper mapper;
    private Path path;

    public CountLinesHandler(OptionsMapper mapper, Path path) {
        info="";
        this.mapper = mapper;
        this.path = path;
        options = Arrays.asList("-l","--lines");
    }

    private void execute() {
        if (!hasArg(mapper)) return;

        try (BufferedReader bf = new BufferedReader(new FileReader(path.toFile())) )  {
            int lines =0;
            while(bf.readLine()!=null) {
                lines += 1;
            }
            info=lines+"";
        } 
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeY() {
        if (!hasArg(mapper)) return;
        try {
            long lines = 
                Files.lines(path)
                .count();
            info = lines+"";
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
