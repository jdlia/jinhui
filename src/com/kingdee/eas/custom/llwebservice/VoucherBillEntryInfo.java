package com.kingdee.eas.custom.llwebservice;

import java.io.Serializable;

public class VoucherBillEntryInfo extends AbstractVoucherBillEntryInfo implements Serializable 
{
    public VoucherBillEntryInfo()
    {
        super();
    }
    protected VoucherBillEntryInfo(String pkField)
    {
        super(pkField);
    }
}