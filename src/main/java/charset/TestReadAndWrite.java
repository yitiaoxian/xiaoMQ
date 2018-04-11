package charset;

import java.io.*;

/**
 * utf-8输入输出的测试
 */
public class TestReadAndWrite {
    public static void main(String args[]){
        File file = new File("E:\\xiaoMQ\\config\\result.txt");
        String str = "";
        try {
            //以UTF8编码读取文件中的字符串
            FileInputStream in = new FileInputStream(file);
            int size=in.available();

            byte[] buffer=new byte[size];

            in.read(buffer);

            in.close();

            str=new String(buffer,"UTF-8");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fileOutputStream = null;

        try {
            String path = System.getProperty("user.dir")+"/config/result1.txt";
            fileOutputStream = new FileOutputStream(path);
            OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream, "UTF-8");

            osw.write(str);
            osw.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
