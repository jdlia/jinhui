package com.kingdee.eas.custom.llwebservice;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractVoucherBillInfo extends com.kingdee.eas.framework.CoreBillBaseInfo implements Serializable 
{
    public AbstractVoucherBillInfo()
    {
        this("id");
    }
    protected AbstractVoucherBillInfo(String pkField)
    {
        super(pkField);
        put("entrys", new com.kingdee.eas.custom.llwebservice.VoucherBillEntryCollection());
    }
    /**
     * Object: 凭证处理单 's 凭证分录 property 
     */
    public com.kingdee.eas.custom.llwebservice.VoucherBillEntryCollection getEntrys()
    {
        return (com.kingdee.eas.custom.llwebservice.VoucherBillEntryCollection)get("entrys");
    }
    /**
     * Object:凭证处理单's 是否生成凭证property 
     */
    public boolean isFivouchered()
    {
        return getBoolean("Fivouchered");
    }
    public void setFivouchered(boolean item)
    {
        setBoolean("Fivouchered", item);
    }
    /**
     * Object: 凭证处理单 's 财务组织 property 
     */
    public com.kingdee.eas.basedata.org.CompanyOrgUnitInfo getFICompany()
    {
        return (com.kingdee.eas.basedata.org.CompanyOrgUnitInfo)get("FICompany");
    }
    public void setFICompany(com.kingdee.eas.basedata.org.CompanyOrgUnitInfo item)
    {
        put("FICompany", item);
    }
    /**
     * Object: 凭证处理单 's 会计期间 property 
     */
    public com.kingdee.eas.basedata.assistant.PeriodInfo getPeriod()
    {
        return (com.kingdee.eas.basedata.assistant.PeriodInfo)get("period");
    }
    public void setPeriod(com.kingdee.eas.basedata.assistant.PeriodInfo item)
    {
        put("period", item);
    }
    /**
     * Object:凭证处理单's 记账日期property 
     */
    public java.util.Date getBookedDate()
    {
        return getDate("bookedDate");
    }
    public void setBookedDate(java.util.Date item)
    {
        setDate("bookedDate", item);
    }
    /**
     * Object:凭证处理单's 是否红冲property 
     */
    public boolean isIsReverseVoucher()
    {
        return getBoolean("isReverseVoucher");
    }
    public void setIsReverseVoucher(boolean item)
    {
        setBoolean("isReverseVoucher", item);
    }
    /**
     * Object:凭证处理单's EAS凭证号property 
     */
    public String getVoucherNumber()
    {
        return getString("voucherNumber");
    }
    public void setVoucherNumber(String item)
    {
        setString("voucherNumber", item);
    }
    /**
     * Object:凭证处理单's 失败原因property 
     */
    public String getResultDesc()
    {
        return getString("resultDesc");
    }
    public void setResultDesc(String item)
    {
        setString("resultDesc", item);
    }
    /**
     * Object: 凭证处理单 's 币别 property 
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
     * Object: 凭证处理单 's 凭证类型 property 
     */
    public com.kingdee.eas.basedata.assistant.VoucherTypeInfo getVoucherType()
    {
        return (com.kingdee.eas.basedata.assistant.VoucherTypeInfo)get("voucherType");
    }
    public void setVoucherType(com.kingdee.eas.basedata.assistant.VoucherTypeInfo item)
    {
        put("voucherType", item);
    }
    /**
     * Object:凭证处理单's 单据状态property 
     */
    public com.kingdee.eas.eai.EaiBillStatusEnum getBillStatus()
    {
        return com.kingdee.eas.eai.EaiBillStatusEnum.getEnum(getInt("billStatus"));
    }
    public void setBillStatus(com.kingdee.eas.eai.EaiBillStatusEnum item)
    {
		if (item != null) {
        setInt("billStatus", item.getValue());
		}
    }
    /**
     * Object:凭证处理单's 附件数property 
     */
    public int getAttaches()
    {
        return getInt("attaches");
    }
    public void setAttaches(int item)
    {
        setInt("attaches", item);
    }
    /**
     * Object:凭证处理单's 凭证IDproperty 
     */
    public String getVoucherId()
    {
        return getString("voucherId");
    }
    public void setVoucherId(String item)
    {
        setString("voucherId", item);
    }
    /**
     * Object:凭证处理单's 凭证处理单状态property 
     */
    public com.kingdee.eas.hr.compensation.InterfaceBillEnum getStatus()
    {
        return com.kingdee.eas.hr.compensation.InterfaceBillEnum.getEnum(getString("status"));
    }
    public void setStatus(com.kingdee.eas.hr.compensation.InterfaceBillEnum item)
    {
		if (item != null) {
        setString("status", item.getValue());
		}
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("C91E31D3");
    }
}