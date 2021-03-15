package com.kingdee.eas.custom.llwebservice;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractVoucherBillEntryAssistInfo extends com.kingdee.eas.framework.CoreBillEntryBaseInfo implements Serializable 
{
    public AbstractVoucherBillEntryAssistInfo()
    {
        this("id");
    }
    protected AbstractVoucherBillEntryAssistInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object: ������ 's null property 
     */
    public com.kingdee.eas.custom.llwebservice.VoucherBillEntryInfo getParent1()
    {
        return (com.kingdee.eas.custom.llwebservice.VoucherBillEntryInfo)get("parent1");
    }
    public void setParent1(com.kingdee.eas.custom.llwebservice.VoucherBillEntryInfo item)
    {
        put("parent1", item);
    }
    /**
     * Object:������'s ҵ�����property 
     */
    public String getBizNumber()
    {
        return getString("bizNumber");
    }
    public void setBizNumber(String item)
    {
        setString("bizNumber", item);
    }
    /**
     * Object:������'s �����property 
     */
    public String getSettlementCode()
    {
        return getString("settlementCode");
    }
    public void setSettlementCode(String item)
    {
        setString("settlementCode", item);
    }
    /**
     * Object:������'s ҵ������property 
     */
    public java.util.Date getBizDate()
    {
        return getDate("bizDate");
    }
    public void setBizDate(java.util.Date item)
    {
        setDate("bizDate", item);
    }
    /**
     * Object:������'s ���property 
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
     * Object:������'s ����property 
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
     * Object:������'s ����property 
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
     * Object: ������ 's �ͻ� property 
     */
    public com.kingdee.eas.basedata.master.cssp.CustomerInfo getCustomer()
    {
        return (com.kingdee.eas.basedata.master.cssp.CustomerInfo)get("customer");
    }
    public void setCustomer(com.kingdee.eas.basedata.master.cssp.CustomerInfo item)
    {
        put("customer", item);
    }
    /**
     * Object: ������ 's ��Ӧ�� property 
     */
    public com.kingdee.eas.basedata.master.cssp.SupplierInfo getSupplier()
    {
        return (com.kingdee.eas.basedata.master.cssp.SupplierInfo)get("supplier");
    }
    public void setSupplier(com.kingdee.eas.basedata.master.cssp.SupplierInfo item)
    {
        put("supplier", item);
    }
    /**
     * Object: ������ 's ������λ property 
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
     * Object: ������ 's ���� property 
     */
    public com.kingdee.eas.basedata.assistant.BankInfo getRegion()
    {
        return (com.kingdee.eas.basedata.assistant.BankInfo)get("region");
    }
    public void setRegion(com.kingdee.eas.basedata.assistant.BankInfo item)
    {
        put("region", item);
    }
    /**
     * Object: ������ 's ��˾ property 
     */
    public com.kingdee.eas.basedata.org.CompanyOrgUnitInfo getCompanyOrg()
    {
        return (com.kingdee.eas.basedata.org.CompanyOrgUnitInfo)get("companyOrg");
    }
    public void setCompanyOrg(com.kingdee.eas.basedata.org.CompanyOrgUnitInfo item)
    {
        put("companyOrg", item);
    }
    /**
     * Object: ������ 's ���� property 
     */
    public com.kingdee.eas.basedata.org.AdminOrgUnitInfo getAdminOrg()
    {
        return (com.kingdee.eas.basedata.org.AdminOrgUnitInfo)get("adminOrg");
    }
    public void setAdminOrg(com.kingdee.eas.basedata.org.AdminOrgUnitInfo item)
    {
        put("adminOrg", item);
    }
    /**
     * Object: ������ 's �ɱ����� property 
     */
    public com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo getCostOrg()
    {
        return (com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo)get("costOrg");
    }
    public void setCostOrg(com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo item)
    {
        put("costOrg", item);
    }
    /**
     * Object: ������ 's ���� property 
     */
    public com.kingdee.eas.basedata.master.material.MaterialInfo getMaterial()
    {
        return (com.kingdee.eas.basedata.master.material.MaterialInfo)get("material");
    }
    public void setMaterial(com.kingdee.eas.basedata.master.material.MaterialInfo item)
    {
        put("material", item);
    }
    /**
     * Object: ������ 's ���㷽ʽ property 
     */
    public com.kingdee.eas.basedata.assistant.SettlementTypeInfo getSettlementType()
    {
        return (com.kingdee.eas.basedata.assistant.SettlementTypeInfo)get("settlementType");
    }
    public void setSettlementType(com.kingdee.eas.basedata.assistant.SettlementTypeInfo item)
    {
        put("settlementType", item);
    }
    /**
     * Object:������'s ժҪproperty 
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
     * Object: ������ 's ְԱ property 
     */
    public com.kingdee.eas.basedata.person.PersonInfo getPerson()
    {
        return (com.kingdee.eas.basedata.person.PersonInfo)get("person");
    }
    public void setPerson(com.kingdee.eas.basedata.person.PersonInfo item)
    {
        put("person", item);
    }
    /**
     * Object: ������ 's �����˻� property 
     */
    public com.kingdee.eas.basedata.assistant.AccountBankInfo getBankAccount()
    {
        return (com.kingdee.eas.basedata.assistant.AccountBankInfo)get("bankAccount");
    }
    public void setBankAccount(com.kingdee.eas.basedata.assistant.AccountBankInfo item)
    {
        put("bankAccount", item);
    }
    /**
     * Object: ������ 's �ڲ��˻� property 
     */
    public com.kingdee.eas.fm.fs.InnerAccountInfo getInnerAccount()
    {
        return (com.kingdee.eas.fm.fs.InnerAccountInfo)get("innerAccount");
    }
    public void setInnerAccount(com.kingdee.eas.fm.fs.InnerAccountInfo item)
    {
        put("innerAccount", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ1 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType1()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType1");
    }
    public void setGeneralAssActType1(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType1", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ2 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType2()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType2");
    }
    public void setGeneralAssActType2(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType2", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ3 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType3()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType3");
    }
    public void setGeneralAssActType3(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType3", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ4 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType4()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType4");
    }
    public void setGeneralAssActType4(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType4", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ5 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType5()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType5");
    }
    public void setGeneralAssActType5(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType5", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ6 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType6()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType6");
    }
    public void setGeneralAssActType6(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType6", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ7 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType7()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType7");
    }
    public void setGeneralAssActType7(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType7", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ8 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType8()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType8");
    }
    public void setGeneralAssActType8(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType8", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ9 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType9()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType9");
    }
    public void setGeneralAssActType9(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType9", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ10 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType10()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType10");
    }
    public void setGeneralAssActType10(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType10", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ11 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType11()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType11");
    }
    public void setGeneralAssActType11(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType11", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ12 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType12()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType12");
    }
    public void setGeneralAssActType12(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType12", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ13 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType13()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType13");
    }
    public void setGeneralAssActType13(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType13", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ14 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType14()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType14");
    }
    public void setGeneralAssActType14(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType14", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ15 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType15()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType15");
    }
    public void setGeneralAssActType15(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType15", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ16 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType16()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType16");
    }
    public void setGeneralAssActType16(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType16", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ17 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType17()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType17");
    }
    public void setGeneralAssActType17(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType17", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ18 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType18()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType18");
    }
    public void setGeneralAssActType18(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType18", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ19 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType19()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType19");
    }
    public void setGeneralAssActType19(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType19", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ20 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType20()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType20");
    }
    public void setGeneralAssActType20(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType20", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ21 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType21()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType21");
    }
    public void setGeneralAssActType21(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType21", item);
    }
    /**
     * Object: ������ 's �Զ��������Ŀ22 property 
     */
    public com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo getGeneralAssActType22()
    {
        return (com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo)get("generalAssActType22");
    }
    public void setGeneralAssActType22(com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo item)
    {
        put("generalAssActType22", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("50F6FDA8");
    }
}