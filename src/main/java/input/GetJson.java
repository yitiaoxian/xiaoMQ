package input;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import common.Configuration;
import common.excption.EnumWAErrorCode;
import common.excption.WADataXExcption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;


/**
 * 获取日志文件中的输入测试用例
 * 读取字符串中两个字符串之间的内容（日志文件中所有的）
 */
public class GetJson {
    Logger logger = LogManager.getLogger(GetJson.class);
    private static String CONFIG_PATH = System.getProperty("user.dir") + "/config/readConfig.json";
    private  Configuration configuration = Configuration.from(GetJson.getConfig());
    private  String startFlag = configuration.getString("begin");
    private  String endFlag = configuration.getString("end");
    private  String file = configuration.getString("logfile");
    private  String logContent = ReaderUtil.ReadLogFile(file);
    /**
     * 获取配置文件中的配置信息
     * @return 返回获取的配置
     */
    private static String getConfig(){
        String s = ReaderUtil.ReadFileToString(CONFIG_PATH);
        if (s.length() == 0){
            throw new WADataXExcption(EnumWAErrorCode.CONFIG_FILE_ERROR,"配置文件读取异常");
        }
        return s;
    }
//    private LinkedList<String> getJson(String start,String end,String content){
//        LinkedList<String> jsonObjects = new LinkedList<String>();
//        while (content.contains(start)){
//            int begLocation = content.indexOf(start);
//            String str = content.substring(begLocation);
//            int endLocation = str.indexOf(end);
//            if(endLocation>0){
//                String tmp = str.substring(start.length(),endLocation);
//                jsonObjects.add(tmp);
//                logger.error(tmp+"  \n size:"+jsonObjects.size());
//            }
//            logger.error(str);
//        }
//        return jsonObjects;
//    }
    private static JSONArray stringJson = new JSONArray();

    /**
     * 从日志文件中获取字符串中两个子字符串之间需要的数据
     * @param startFlag 前一个子字符串
     * @param endFlag 后一个子字符串
     * @param content 日志内容的字符串
     */
    private void getContent(String startFlag,String endFlag,String content){
        if(content.length() == 0){
            throw new WADataXExcption(EnumWAErrorCode.CONFIG_FILE_ERROR,"日志文件错误");
        }
        if(content.contains(startFlag)){
            int begLocation = content.indexOf(startFlag);
            String str = content.substring(begLocation+startFlag.length());
            int endLocation = str.indexOf(endFlag);
            if(endLocation>0){
                String tmp = str.substring(0,endLocation);

                //解决乱码？
                stringJson.add( JSONObject.parse(tmp));
//                try {
//                    logger.error(tmp.equals(new String(tmp.getBytes("iso-8859-1"),"iso-8859-1")));
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }

            }
            String tmpS = content.substring(begLocation+startFlag.length()+endLocation);
            getContent(startFlag,endFlag,tmpS);
        }
    }
    private void dealProgress(){
        Configuration configuration = Configuration.from(GetJson.getConfig());
        String logContent = ReaderUtil.ReadLogFile(configuration.getString("logfile"));

        getContent(configuration.getString("begin"),configuration.getString("end"),logContent);
        for (Object object:stringJson){
            logger.info(object);
        }
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        Logger logger1 = LogManager.getLogger("Main");
        GetJson main = new GetJson();
        main.dealProgress();
        String result = null;
        result = stringJson.toString();
//        logger1.info(stringJson.toString().equals(new String(result.getBytes("utf-8"),"utf-8")));
//        这是utf-8编码的字符串啊!!!

        //乱码解决
        FileOutputStream fileOutputStream = null;

        try {
            String path = System.getProperty("user.dir")+"/config/result.txt";
            fileOutputStream = new FileOutputStream(path);
            OutputStreamWriter osw = new OutputStreamWriter(fileOutputStream, "UTF-8");

            osw.write(result);
            osw.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        Configuration configuration = Configuration.from(GetJson.getConfig());
//        String logContent = ReaderUtil.ReadLogFile(configuration.getString("logfile"));
//
//        main.getContent(configuration.getString("begin"),configuration.getString("end"),logContent);
//        for (Object object:stringJson){
//            logger.info(object);
//        }
    }
}
