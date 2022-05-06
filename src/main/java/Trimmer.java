import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Trimmer {
    private final Command command;
    private final List<String> inputData = new ArrayList<>();
    private List<String> outputData = new ArrayList<>();

    public Trimmer(Command command) {
        this.command = command;
    }

    //cut -w -o D:\Programming\String-trimmer-app-2.0\testDocs\output.txt D:\Programming\String-trimmer-app-2.0\testDocs\data1.txt range 5-6
//    java -jar .\String-trimmer-app-2.0.jar cut -w range 5-6

    public void process() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Starting trimming");

            if (!command.getInputFilePath().equals("console")) {
                readFile();
            } else {
                System.out.println("Ready to read your text from console, paste it:");
                while (true) {
                    String str = scanner.nextLine();
                    if (str.equals("")) {
                        break;
                    }
                    inputData.add(str);
                }
            }


            if (command.getMode().equals("words")) {
                outputData = trimByWords(inputData);
            } else {
                outputData = trimByChar(inputData);
            }

            if (!command.getOutputFilePath().equals("console")) {
                writeFile();
                System.out.println("Successfully trimmed!");
            } else {
                System.out.println("Successfully trimmed!");
                System.out.println();
                for (String string : outputData) {
                    System.out.println(string);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<String> trimByChar(List<String> inputData) {
        try {
            for (String inString : inputData) {
                StringBuilder newString = new StringBuilder();
                char[] chars = inString.toCharArray();
                int start;
                int end;
                if (command.getRangeFrom().equals("process")) {
                    start = 0;
                } else {
                    start = Integer.parseInt(command.getRangeFrom());
                }
                if (command.getRangeTo().equals("end")) {
                    end = chars.length;
                } else {
                    end = Integer.parseInt(command.getRangeTo());
                }

                for (int i = start - 1; i < end; i++) {
                    i = Math.max(i, 0);
                    if (i < chars.length) {
                        newString.append(chars[i]);
                    }
                }
                outputData.add(newString.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputData;
    }

    public List<String> trimByWords(List<String> inputData) {
        try {
            for (String inString : inputData) {
                StringBuilder newString = new StringBuilder();
                inString = inString.trim().replaceAll("\\\\s+", " ");
                List<String> words = List.of(inString.split(" "));
                int start;
                int end;
                if (command.getRangeFrom().equals("process")) {
                    start = 0;
                } else {
                    start = Integer.parseInt(command.getRangeFrom());
                }
                if (command.getRangeTo().equals("end")) {
                    end = words.size();
                } else {
                    end = Integer.parseInt(command.getRangeTo());
                }

                for (int i = start - 1; i < end; i++) {
                    i = Math.max(i, 0);
                    if (i < words.size()) {
                        newString.append(" ").append(words.get(i));
                    }
                }
                outputData.add(newString.toString().trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputData;
    }

    public void writeFile() {
        try {
            FileWriter writer = new FileWriter(command.getOutputFilePath());
            for (String string : outputData) {
                writer.write(string);
                writer.write("\n");
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        File file;
        try {
            file = new File(command.getInputFilePath());
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                inputData.add(line);
                line = reader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
