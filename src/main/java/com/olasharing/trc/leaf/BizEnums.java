package com.olasharing.trc.leaf;

/**
 * biz enum
 *
 * @author liuyan
 * @date 2018-12-19
 */
public enum BizEnums {

    TOUR((byte) 0, "TOUR"),

    DRIVER((byte) 1, "DRIVER"),

    PAY((byte) 2, "PAY"),

    SHORT_RENT((byte) 3, "SHORT_RENT"),

    ;

    private byte bizNo;

    private String bizCode;

    BizEnums(Byte bizNo, String bizCode) {
        this.bizNo = bizNo;
        this.bizCode = bizCode;
    }

    public static BizEnums getBizByCode(String bizCode) {
        for (BizEnums bizEnum : BizEnums.values()) {
            if (bizEnum.getBizCode().equalsIgnoreCase(bizCode)) {
                return bizEnum;
            }
        }
        return null;
    }

    public byte getBizNo() {
        return bizNo;
    }

    public String getBizCode() {
        return bizCode;
    }
}
