package input;

import common.excption.EnumWAErrorCode;
import common.excption.WADataXExcption;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 读取文件的工具类
 */
class ReaderUtil {
    /**
     * jar的执行目录
     */
    private static String JAR_PATH = System.getProperty("user.dir");

    public static String ReadLogFile(String file){
        String path = JAR_PATH + "/config/" + file;
        String result = ReadFileToString(path);
        if(result.length() == 0){
            throw new WADataXExcption(EnumWAErrorCode.CONFIG_FILE_ERROR,"你的日志文件中没有所需的内容");
        }
        return result;
    }
    /**
     * 读取文件中的字符串
     * @param file 文件名(包含了文件的路径)
     * @return 读到的字符串
     */
    public static String ReadFileToString(String file){
        StringBuilder stringBuilder = new StringBuilder();
        String s = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while ((s=bufferedReader.readLine())!= null){
                stringBuilder.append(s+"");
            }
            bufferedReader.close();
            String str = stringBuilder.toString();
            return str;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(true){
            throw new WADataXExcption(EnumWAErrorCode.CONFIG_FILE_ERROR,"文件读取异常，程序终止！");
        }
        return null;
    }
}
