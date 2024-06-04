package handlers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import options.OptionsMapper;

public class CountBytesHandler extends Handler{

    private String info;
    private OptionsMapper mapper;
    private Path path;

    public CountBytesHandler(OptionsMapper mapper, Path path) {
        info="";
        this.mapper = mapper;
        this.path = path;
        options =Arrays.asList("-c","--bytes");
    }

    private void execute() {

        if (!hasArg(mapper)) return;

        info = path.toFile().length()+"";

    }

    private void executeZ() {

        if (!hasArg(mapper)) return;

        try (SeekableByteChannel ch = Files.newByteChannel(path, StandardOpenOption.READ)) {
            long bytes =0;
            ByteBuffer bf = ByteBuffer.allocate(512);
            while(ch.read(bf)>0) {
                bytes += bf.array().length;
                bf.flip();
                bf.clear();
            }
            info=bytes+"";
        } 
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeF() {

        if (!hasArg(mapper)) return;

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
