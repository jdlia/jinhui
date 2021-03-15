package com.kingdee.eas.custom.llwebservice;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractVoucherBillEntryInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractVoucherBillEntryInfo()
    {
        this("id");
    }
    protected AbstractVoucherBillEntryInfo(String pkField)
    {
        super(pkField);
        put("Cashflow", new com.kingdee.eas.custom.llwebservice.VoucherBillEntryCashflowCollection());
        put("Assist", new com.kingdee.eas.custom.llwebservice.VoucherBillEntryAssistCollection());
    }
    /**
     * Object: 凭证分录 's 单据头 property 
     */
    public com.kingdee.eas.custom.llwebservice.VoucherBillInfo getParent()
    {
        return (com.kingdee.eas.custom.llwebservice.VoucherBillInfo)get("parent");
    }
    public void setParent(com.kingdee.eas.custom.llwebservice.VoucherBillInfo item)
    {
        put("parent", item);
    }
    /**
     * Object:凭证分录's 摘要property 
     */
    public String getDescription()
    {
        return getString("description");
    }
    public void setDescription(String item)
    {
        setString("description", item);
    }
    /**
     * Object: 凭证分录 's 会计科目 property 
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
     * Object:凭证分录's 金额property 
     */
    public java.math.BigDecimal getAmount()
    {
        return getBigDecimal("amount");
    }
    public void setAmount(java.math.BigDecimal item)
    {
        setBigDecimal("amount", item);
    }
    /**
     * Object: 凭证分录 's 原币 property 
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
     * Object: 凭证分录 's 辅助账 property 
     */
    public com.kingdee.eas.custom.llwebservice.VoucherBillEntryAssistCollection getAssist()
    {
        return (com.kingdee.eas.custom.llwebservice.VoucherBillEntryAssistCollection)get("Assist");
    }
    /**
     * Object:凭证分录's 折算本位币汇率property 
     */
    public java.math.BigDecimal getLocalRate()
    {
        return getBigDecimal("localRate");
    }
    public void setLocalRate(java.math.BigDecimal item)
    {
        setBigDecimal("localRate", item);
    }
    /**
     * Object:凭证分录's 数量property 
     */
    public java.math.BigDecimal getQty()
    {
        return getBigDecimal("qty");
    }
    public void setQty(java.math.BigDecimal item)
    {
        setBigDecimal("qty", item);
    }
    /**
     * Object: 凭证分录 's 计量单位 property 
     */
    public com.kingdee.eas.basedata.assistant.MeasureUnitInfo getMeasureUnit()
    {
        return (com.kingdee.eas.basedata.assistant.MeasureUnitInfo)get("measureUnit");
    }
    public void setMeasureUnit(com.kingdee.eas.basedata.assistant.MeasureUnitInfo item)
    {
        put("measureUnit", item);
    }
    /**
     * Object:凭证分录's 单价property 
     */
    public java.math.BigDecimal getPrice()
    {
        return getBigDecimal("price");
    }
    public void setPrice(java.math.BigDecimal item)
    {
        setBigDecimal("price", item);
    }
    /**
     * Object:凭证分录's 借贷方向property 
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
     * Object: 凭证分录 's 现金流量 property 
     */
    public com.kingdee.eas.custom.llwebservice.VoucherBillEntryCashflowCollection getCashflow()
    {
        return (com.kingdee.eas.custom.llwebservice.VoucherBillEntryCashflowCollection)get("Cashflow");
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("4327125F");
    }
}