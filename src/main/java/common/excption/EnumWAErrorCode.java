package common.excption;

import common.util.WAErrorCode;

/**
 * authot xiao
 * 2018/3/5
 * 枚举错误码
 */
public enum  EnumWAErrorCode implements WAErrorCode{

    //TODO 可以增加异常信息的种类
    /**
     * 用于描述配置文件配置错误的信息
     */
    CONFIG_FILE_ERROR("WA-error-00","你的配置文件错误"),
    /**
     * 用于描述配置文件路径错误的信息
     */
    CONFIG_PATH_ERROR("WA-error-01","你的配置文件的路径错误"),
    /**
     * 用于描述配置文件中配置未完成造成错误的信息
     */
    CONFIG_FILENOTFINISH("WA-error-02","你的配置文件未配置完成"),
    /**
     * 用于描述系统编程的错误信息
     */
    SYS_RUN_ERROR("WA-error-10","系统的运行错误");

    /**
     * 错误编码
     */
    private final String code;
    /**
     * 对错误码的描述信息
     */
    private final String describe;

    private EnumWAErrorCode(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    /**
     * @return 返回错误码
     */
    public String getWACode() {
        return this.code;
    }

    public String getWADescription() {
        return this.describe;
    }

    /**
     * 和原本的datax异常输出一样
     * @return 错误异常的信息格式化输出
     */
    @Override
    public String toString() {
        return String.format("Code:[%s], Describe:[%s]", this.code,
                this.describe);
    }
}
