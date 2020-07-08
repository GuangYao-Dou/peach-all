package cn.odboy.constant;

/**
 * 常用静态常量
 *
 * @date 2018-12-26
 */
public class PeachConstant {

    /**
     * 用于IP定位转换
     */
    public static final String REGION = "内网IP|内网IP";
    /**
     * win 系统
     */
    public static final String WIN = "win";

    /**
     * mac 系统
     */
    public static final String MAC = "mac";

    /**
     * 常用接口
     */
    public static class Url {
        // 免费图床
        public static final String SM_MS_URL = "https://sm.ms/api";
        // IP归属地查询
        public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";
    }

    /**
     * 常用列
     */
    public static final String SQL_COLUMN_ID = "id";

    /**
     * 异常常量
     */
    public static final String MSG_ID_NULL = "主键ID不能为null";
}
