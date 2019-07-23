
package com.frame.base.common;

public interface Constants
{

    public String CURRENTUSER = "_currentUser";

    /**
     * 返回状态值
     */
    public enum RESULT
    {
        /**
         * 成功
         */
        CODE_YES("0"),
        /**
         * 失败
         */
        CODE_NO("-1"),
        /**
         * 失败msg
         */
        MSG_YES("操作成功"),
        /**
         * 失败msg
         */
        MSG_NO("操作失败");
        private String value;

        private RESULT(String value)
        {
            this.value = value;
        }

        public String getValue()
        {
            return value;
        }
    }

    enum STATUS
    {

        NO_PAY(1, "未支付"), PAY_SUCCESS(2, "支付成功"), PAY_FAILED(3, "支付失败"), OVER_TIME(4, "订单超时");

        private Integer code;
        private String name;

        private STATUS(Integer code, String name)
        {
            this.code = code;
            this.name = name;
        }

        public Integer getCode()
        {
            return code;
        }

        public String getName()
        {
            return name;
        }

    }

}
