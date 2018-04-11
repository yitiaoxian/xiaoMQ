package charset;

import java.io.UnsupportedEncodingException;

public class EncodingTest
{
    /**
     * public String(byte bytes[], Charset charset)函数可以用指定字节数组和编码来构造字符串。
     * public byte[] getBytes(Charset charset)函数把字符串按指定编码来得到字节数组。
     * 可以用这两个函数来实现编码转换。
     * @param args 参数
     */
    public static void main(String[] args)
    {
        try
        {
            //string 的编码方式默认为本地系统的编码方式相同
            String gb = new String("国标2312".getBytes(),"gb2312");
            String gbutf8 = new String("国标2312".getBytes(),"utf8");
            System.out.println(gb);
            byte [] b = gb.getBytes("gb2312");
            String ios = new String(b,"ISO-8859-1");
            System.out.println(ios);
            System.out.println("gb2312->UTF:"+new String(b,"UTF8"));
            System.out.println("utf8->UTF:"+new String(gbutf8.getBytes("utf8"),"UTF8"));
            System.out.println("utf8->UTF:"+new String(gbutf8.getBytes("gb2312"),"gb2312"));
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }
}