import java.util.List;

public class Command {
    private String mode;
    private String inputFilePath;
    private String outputFilePath;
    private String rangeFrom;
    private String rangeTo;

    public Command(CommandLineValues values) {

        List<String> arguments = values.getArguments();
        try {
            mode = values.getWorkMode();
            if (values.getOutputPath() != null) {
                outputFilePath = values.getOutputPath();
            } else {
                outputFilePath = "console";
            }
            if (arguments.size() <= 3) {
                inputFilePath = "console";
            } else {
                inputFilePath = arguments.get(1);
            }


            String rangeString = arguments.get(arguments.size() - 1);
            if (rangeString.startsWith("-")) {
                rangeFrom = "process";
                rangeTo = rangeString.substring(1);
            } else if (rangeString.endsWith("-")) {
                rangeFrom = rangeString.substring(0, rangeString.length() - 1);
                rangeTo = "end";
            } else {
                rangeFrom = rangeString.substring(0, rangeString.indexOf('-'));
                rangeTo = rangeString.substring(rangeString.indexOf('-') + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getMode() {
        return mode;
    }

    public String getInputFilePath() {
        return inputFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public String getRangeFrom() {
        return rangeFrom;
    }

    public String getRangeTo() {
        return rangeTo;
    }

    public void printArguments() {
        System.out.println("Mode:" + mode + " Input:" + inputFilePath + " Output:" + outputFilePath + " RangeFrom:" + rangeFrom + " RangeTo:" + rangeTo);
    }
}
