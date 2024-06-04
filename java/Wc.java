import java.nio.file.Path;

import file.FileManager;
import handlers.CountBytesHandler;
import handlers.CountLinesHandler;
import options.OptionsMapper;

public class Wc {
    public static void main(String[] args) {

        OptionsMapper mapper = new OptionsMapper();
        mapper.handleArgs(args);

        if (args.length==0) {
            mapper.printHelp();
            System.exit(1);

        };

        if (!mapper.hasOptions()) {
            mapper.printHelp();
            return;
        }

        Path p = Path.of(args[args.length-1]);
        FileManager fileManager = new FileManager(p);
        fileManager.addHandler(new CountBytesHandler(mapper, p));
        fileManager.addHandler(new CountLinesHandler(mapper, p));

        System.out.println(fileManager.info());
    }
}
