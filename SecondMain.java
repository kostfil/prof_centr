import java.io.*;
import java.nio.charset.StandardCharsets;

public class SecondMain {

    public static void main(String[] args){

        File firstFile = new File("resource/first.txt");
        File secondFile = new File("resource/second.txt");

        try(
                FileInputStream fileInput = new FileInputStream(firstFile);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInput, StandardCharsets.UTF_8);
                FileOutputStream fileOutput = new FileOutputStream(secondFile,true);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutput,StandardCharsets.UTF_8)
        ) {
            int c;
            int countCharStr = 0;
            while ((c = inputStreamReader.read()) != -1) {

                if ( isRandN(c) ) {
                    if(countCharStr > 0) {
                        byte[] lenStr = Integer.toString(countCharStr).getBytes();
                        for (int i = 0; i < lenStr.length; i++) {
                            outputStreamWriter.write(lenStr[i]);
                        }
                        countCharStr = 0;
                    }
                }else {
                    ++countCharStr;
                }
                outputStreamWriter.write(c);
            }

            if(countCharStr > 0) {
                byte[] lenStr = Integer.toString(countCharStr).getBytes();
                for (int i = 0; i < lenStr.length; i++) {
                    outputStreamWriter.write(lenStr[i]);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static boolean isRandN(int c){
        return (System.lineSeparator().indexOf(c) > -1);
    }
}