package options;

import java.util.HashMap;
import java.util.Map;

public class OptionsMapper {
    private Map<String, String> mapper;

    public OptionsMapper() {
        mapper = new HashMap<String, String>();
    }

    public void handleArgs(String[] args) {
        if (args.length <= 0) {
            return;
        }

        for (String arg : args) {
            if (null==arg || arg.isEmpty()) continue;
            mapper.put(arg, arg);
        }
    }

    public boolean hasOptions() {
        return mapper.size() > 0;
    }

    public boolean hasOption(String option) {
        return null != mapper.get(option);
    }

}
