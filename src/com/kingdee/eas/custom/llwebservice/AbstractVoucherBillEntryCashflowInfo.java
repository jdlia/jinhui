package com.kingdee.eas.custom.llwebservice;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractVoucherBillEntryCashflowInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractVoucherBillEntryCashflowInfo()
    {
        this("id");
    }
    protected AbstractVoucherBillEntryCashflowInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: 现金流量 's null property 
     */
    public com.kingdee.eas.custom.llwebservice.VoucherBillEntryInfo getEntry()
    {
        return (com.kingdee.eas.custom.llwebservice.VoucherBillEntryInfo)get("entry");
    }
    public void setEntry(com.kingdee.eas.custom.llwebservice.VoucherBillEntryInfo item)
    {
        put("entry", item);
    }
    /**
     * Object: 现金流量 's 科目 property 
     */
    public com.kingdee.eas.basedata.master.account.AccountViewInfo getAccount()
    {
        return (com.kingdee.eas.basedata.master.account.AccountViewInfo)get("account");
    }
    public void setAccount(com.kingdee.eas.basedata.master.account.AccountViewInfo item)
    {
        put("account", item);
    }
    /**
     * Object: 现金流量 's 币别 property 
     */
    public com.kingdee.eas.basedata.assistant.CurrencyInfo getCurrency()
    {
        return (com.kingdee.eas.basedata.assistant.CurrencyInfo)get("currency");
    }
    public void setCurrency(com.kingdee.eas.basedata.assistant.CurrencyInfo item)
    {
        put("currency", item);
    }
    /**
     * Object:现金流量's 分录方向property 
     */
    public com.kingdee.eas.fi.gl.EntryDC getEntryDC()
    {
        return com.kingdee.eas.fi.gl.EntryDC.getEnum(getInt("entryDC"));
    }
    public void setEntryDC(com.kingdee.eas.fi.gl.EntryDC item)
    {
		if (item != null) {
        setInt("entryDC", item.getValue());
		}
    }
    /**
     * Object: 现金流量 's 对方科目 property 
     */
    public com.kingdee.eas.basedata.master.account.AccountViewInfo getOppAccount()
    {
        return (com.kingdee.eas.basedata.master.account.AccountViewInfo)get("oppAccount");
    }
    public void setOppAccount(com.kingdee.eas.basedata.master.account.AccountViewInfo item)
    {
        put("oppAccount", item);
    }
    /**
     * Object:现金流量's 对方分录方向property 
     */
    public com.kingdee.eas.fi.gl.EntryDC getOppEntryDC()
    {
        return com.kingdee.eas.fi.gl.EntryDC.getEnum(getInt("oppEntryDC"));
    }
    public void setOppEntryDC(com.kingdee.eas.fi.gl.EntryDC item)
    {
		if (item != null) {
        setInt("oppEntryDC", item.getValue());
		}
    }
    /**
     * Object:现金流量's 现金流量项目标记property 
     */
    public com.kingdee.eas.fi.gl.ItemFlag getItemFlag()
    {
        return com.kingdee.eas.fi.gl.ItemFlag.getEnum(getInt("itemFlag"));
    }
    public void setItemFlag(com.kingdee.eas.fi.gl.ItemFlag item)
    {
		if (item != null) {
        setInt("itemFlag", item.getValue());
		}
    }
    /**
     * Object: 现金流量 's 主表项目 property 
     */
    public com.kingdee.eas.basedata.assistant.CashFlowItemInfo getPrimaryItem()
    {
        return (com.kingdee.eas.basedata.assistant.CashFlowItemInfo)get("primaryItem");
    }
    public void setPrimaryItem(com.kingdee.eas.basedata.assistant.CashFlowItemInfo item)
    {
        put("primaryItem", item);
    }
    /**
     * Object:现金流量's 主表金额系数property 
     */
    public int getPrimaryCoefficient()
    {
        return getInt("primaryCoefficient");
    }
    public void setPrimaryCoefficient(int item)
    {
        setInt("primaryCoefficient", item);
    }
    /**
     * Object: 现金流量 's 附表项目 property 
     */
    public com.kingdee.eas.basedata.assistant.CashFlowItemInfo getSupplementaryItem()
    {
        return (com.kingdee.eas.basedata.assistant.CashFlowItemInfo)get("supplementaryItem");
    }
    public void setSupplementaryItem(com.kingdee.eas.basedata.assistant.CashFlowItemInfo item)
    {
        put("supplementaryItem", item);
    }
    /**
     * Object:现金流量's 附表金额系数property 
     */
    public int getSupplementaryCoefficient()
    {
        return getInt("supplementaryCoefficient");
    }
    public void setSupplementaryCoefficient(int item)
    {
        setInt("supplementaryCoefficient", item);
    }
    /**
     * Object:现金流量's 原币金额property 
     */
    public java.math.BigDecimal getOriginalAmount()
    {
        return getBigDecimal("originalAmount");
    }
    public void setOriginalAmount(java.math.BigDecimal item)
    {
        setBigDecimal("originalAmount", item);
    }
    /**
     * Object:现金流量's 本位币金额property 
     */
    public java.math.BigDecimal getLocalAmount()
    {
        return getBigDecimal("localAmount");
    }
    public void setLocalAmount(java.math.BigDecimal item)
    {
        setBigDecimal("localAmount", item);
    }
    /**
     * Object:现金流量's 报告币金额property 
     */
    public java.math.BigDecimal getReportingAmount()
    {
        return getBigDecimal("reportingAmount");
    }
    public void setReportingAmount(java.math.BigDecimal item)
    {
        setBigDecimal("reportingAmount", item);
    }
    /**
     * Object:现金流量's 是否附表补充指定property 
     */
    public boolean isIsSupItem()
    {
        return getBoolean("isSupItem");
    }
    public void setIsSupItem(boolean item)
    {
        setBoolean("isSupItem", item);
    }
    /**
     * Object:现金流量's 性质property 
     */
    public com.kingdee.eas.fi.gl.CashflowTypeEnum getType()
    {
        return com.kingdee.eas.fi.gl.CashflowTypeEnum.getEnum(getInt("type"));
    }
    public void setType(com.kingdee.eas.fi.gl.CashflowTypeEnum item)
    {
		if (item != null) {
        setInt("type", item.getValue());
		}
    }
    /**
     * Object:现金流量's 对方科目分录号property 
     */
    public int getOppAccountSeq()
    {
        return getInt("oppAccountSeq");
    }
    public void setOppAccountSeq(int item)
    {
        setInt("oppAccountSeq", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("06AC5260");
    }
}