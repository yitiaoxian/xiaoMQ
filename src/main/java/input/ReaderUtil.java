package input;

import common.excption.EnumWAErrorCode;
import common.excption.WADataXExcption;

import java.io.*;

/**
 * 读取文件的工具类
 */
public class ReaderUtil {
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
        String str = "";
        try {
            //1乱码
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//            while ((str = bufferedReader.readLine())!= null){
//                stringBuilder.append(str+"");
//            }
//            bufferedReader.close();
//            str = new String(stringBuilder.toString().getBytes("UTF8"));

            //2不乱码
//            FileInputStream in = new FileInputStream(file);
//            int size=in.available();
//            byte[] buffer=new byte[size];
//            //byte[] buffer=new byte[1024];
//            in.read(buffer);
//            in.close();
//            //以UTF8编码读取字符串
//            str = new String(buffer,"UTF-8");

            //3
            FileInputStream inStream = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer=new byte[1024];
            int length=-1;
            while( (length = inStream.read(buffer) )!= -1)
            {
                bos.write(buffer,0,length);
                // .write方法 SDK 的解释是 Writes count bytes from the byte array buffer starting at offset index to this stream.
                //  当流关闭以后内容依然存在
            }
            bos.close();
            inStream.close();
            str = new String(bos.toByteArray(),"UTF8");
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
