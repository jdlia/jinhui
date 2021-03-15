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
     * Object: �ֽ����� 's null property 
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
     * Object: �ֽ����� 's ��Ŀ property 
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
     * Object: �ֽ����� 's �ұ� property 
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
     * Object:�ֽ�����'s ��¼����property 
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
     * Object: �ֽ����� 's �Է���Ŀ property 
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
     * Object:�ֽ�����'s �Է���¼����property 
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
     * Object:�ֽ�����'s �ֽ�������Ŀ���property 
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
     * Object: �ֽ����� 's ������Ŀ property 
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
     * Object:�ֽ�����'s ������ϵ��property 
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
     * Object: �ֽ����� 's ������Ŀ property 
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
     * Object:�ֽ�����'s ������ϵ��property 
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
     * Object:�ֽ�����'s ԭ�ҽ��property 
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
     * Object:�ֽ�����'s ��λ�ҽ��property 
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
     * Object:�ֽ�����'s ����ҽ��property 
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
     * Object:�ֽ�����'s �Ƿ񸽱���ָ��property 
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
     * Object:�ֽ�����'s ����property 
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
     * Object:�ֽ�����'s �Է���Ŀ��¼��property 
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