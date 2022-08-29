import java.io.*;

public class HW8 {
    public static void main(String[] args) {
        File firstFile = new File("src/first.txt");
        File secondFile = new File("src/second.txt");
        try(
                BufferedReader bufferedReader = new BufferedReader(new FileReader(firstFile));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(secondFile, true));
        )
        {
            String str;
            while((str=bufferedReader.readLine())!=null){
                bufferedWriter.write(str);
                String lenStr = " " + Integer.toString(str.length());
                bufferedWriter.write(lenStr);
                bufferedWriter.newLine();
            }
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
}