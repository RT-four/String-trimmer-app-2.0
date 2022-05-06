import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

public class CommandLineValues {

    private String workMode;
    private boolean errorFree = true;


    @Option(name = "-o", aliases = {"--output"},
            usage = "output file")
    private String outPut;

    @Option(name = "-c", usage = "reading by characters")
    private boolean readByChars = false;

    @Option(name = "-w", usage = "reading by words")
    private boolean readByWords = false;


    @Argument
    private List<String> arguments = new ArrayList<>();

    public CommandLineValues(String... args) throws Exception {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);

            if(!arguments.contains("cut") || !arguments.contains("range")){
                errorFree =false;
            }
            if ((!readByChars && !readByWords)|| (readByChars && readByWords)) {
                errorFree = false;
            }else if(readByWords){
                workMode = "words";
            }else {
                workMode = "characters";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!errorFree){
            throw new Exception();
        }
    }


    public boolean isErrorFree() {
        return errorFree;
    }

    public String getOutputPath() {
        return outPut;
    }

    public  List<String> getArguments(){
        return arguments;
    }

    public String getWorkMode() {
        return workMode;
    }
}