package options;

public class Option {
    private String value;
    private String shortName;
    private String longName;
    private String description;
    private String valueName;
    private boolean required;

    private Option() {
    }

    private Option(String shortName) {
        this.shortName = shortName;
        this.required = false;
        description = "";
        valueName = "";
        longName = "";
        valueName = "";
    }

    public String getValue() {
        return value;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getDescription() {
        return description;
    }

    public String getValueName() {
        return valueName;
    }

    public boolean isRequired() {
        return required;
    }
        
    @Override
    public String toString() {
        return new StringBuilder(shortName)
                .append(",")
                .append(longName)
                .append(" ")
                .append(valueName)
                .append(description)
                .append(required ? "Required" : "")
                .toString();
    }

    public static OptionBuilder newBuilder(String shortName) {
        return new Option.OptionBuilder(shortName);
    }

    public static class OptionBuilder {
        private Option option;

        private OptionBuilder() {
        }

        private OptionBuilder(String shortName) {
            option = new Option(shortName);
        }

        public OptionBuilder required(boolean req) {
            option.required = req;
            return this;
        }

        public OptionBuilder longName(String longName) {
            option.longName = longName;
            return this;
        }

        public OptionBuilder valueName(String valueName) {
            option.valueName = valueName;
            return this;
        }

        public OptionBuilder description(String description) {
            option.description = description;
            return this;
        }

        public Option build() {
            return option;
        }
    }
}
