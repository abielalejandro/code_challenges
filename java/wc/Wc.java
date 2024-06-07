import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import file.FileManager;
import handlers.CountBytesHandler;
import handlers.CountCharsHandler;
import handlers.CountLinesHandler;
import handlers.CountWordsHandler;
import handlers.HelpHandler;
import options.OptionsMapper;

public class Wc {
    public Wc() {}

    private String createTempFileFromStdIn() {
        BufferedWriter bw = null ;
        BufferedReader br = null;
        try {
            File tmp = File.createTempFile("wcc","wcc");
            tmp.deleteOnExit();
            br = new BufferedReader(new InputStreamReader(System.in));
            bw = Files.newBufferedWriter(tmp.toPath(), StandardCharsets.UTF_8);
            String line;
            while ( (line = br.readLine())!=null) {
                bw.write(line);
                bw.newLine();
            }

            return tmp.getAbsolutePath();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        finally{
            try {
                if (null!=br)
                    br.close();

                if (null!=bw)
                    bw.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
            
        }
        return "";
    }
    public void Run(String[] args) {

        String file="";
        if (args.length > 0) {
            file = args[args.length - 1];
            if (!Path.of(file).toFile().isFile()) {
                file = createTempFileFromStdIn();
            }
            else {
                args[args.length-1] = "";
            }
        }
        else {
            file = createTempFileFromStdIn();
        }

        OptionsMapper mapper = new OptionsMapper();
        mapper.handleArgs(args);

        FileManager fileManager = new FileManager(file, mapper);
        fileManager.addHandler(new HelpHandler());
        fileManager.addHandler(new CountBytesHandler());
        fileManager.addHandler(new CountLinesHandler());
        fileManager.addHandler(new CountWordsHandler());
        fileManager.addHandler(new CountCharsHandler());        

        fileManager.info();
    }

    public static void main(String[] args) {
        new Wc().Run(args);
    }
}
