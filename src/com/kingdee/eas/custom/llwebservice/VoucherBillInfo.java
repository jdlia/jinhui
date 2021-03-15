package com.kingdee.eas.custom.llwebservice;

import java.io.Serializable;

public class VoucherBillInfo extends AbstractVoucherBillInfo implements Serializable 
{
    public VoucherBillInfo()
    {
        super();
    }
    protected VoucherBillInfo(String pkField)
    {
        super(pkField);
    }
}