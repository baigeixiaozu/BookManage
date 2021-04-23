package cn.bookmanage.utils;

import java.io.*;

public class FileUtil {
    public static String ReadFile(String Path) {
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }
    public static boolean WriteFile(String file, String data){
        try {

            OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }
}
