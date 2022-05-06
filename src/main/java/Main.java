import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class Main {
    public static void main(String[] args) throws Exception {

        CommandLineValues values = new CommandLineValues(args);
        CmdLineParser parser = new CmdLineParser(values);
        Command command = new Command(values);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.exit(1);
        }

        if (values.isErrorFree()) {
            System.out.println("All right");
        }

        command.printArguments();

        Trimmer trimmer = new Trimmer(command);
        trimmer.process();

    }
}
