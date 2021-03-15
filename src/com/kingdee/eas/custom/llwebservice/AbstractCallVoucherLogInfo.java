package com.kingdee.eas.custom.llwebservice;

import java.io.Serializable;
import com.kingdee.bos.dao.AbstractObjectValue;
import java.util.Locale;
import com.kingdee.util.TypeConversionUtils;
import com.kingdee.bos.util.BOSObjectType;


public class AbstractCallVoucherLogInfo extends com.kingdee.eas.framework.CoreBaseInfo implements Serializable 
{
    public AbstractCallVoucherLogInfo()
    {
        this("id");
    }
    protected AbstractCallVoucherLogInfo(String pkField)
    {
        super(pkField);
    }
    /**
     * Object:����ƾ֤�ӿ���־'s ���񵥾ݱ���property 
     */
    public String getLocalNumber()
    {
        return getString("localNumber");
    }
    public void setLocalNumber(String item)
    {
        setString("localNumber", item);
    }
    /**
     * Object:����ƾ֤�ӿ���־'s ��ȡʱ��property 
     */
    public java.util.Date getReadTime()
    {
        return getDate("readTime");
    }
    public void setReadTime(java.util.Date item)
    {
        setDate("readTime", item);
    }
    /**
     * Object:����ƾ֤�ӿ���־'s �������property 
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
     * Object:����ƾ֤�ӿ���־'s ���񵥾�IDproperty 
     */
    public String getLocalId()
    {
        return getString("localId");
    }
    public void setLocalId(String item)
    {
        setString("localId", item);
    }
    /**
     * Object:����ƾ֤�ӿ���־'s ҵ������IDproperty 
     */
    public String getBizId()
    {
        return getString("bizId");
    }
    public void setBizId(String item)
    {
        setString("bizId", item);
    }
    /**
     * Object:����ƾ֤�ӿ���־'s ҵ�����ݱ���property 
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
     * Object:����ƾ֤�ӿ���־'s ͬ�����property 
     */
    public com.kingdee.eas.eai.EaiResultEnum getResult()
    {
        return com.kingdee.eas.eai.EaiResultEnum.getEnum(getInt("result"));
    }
    public void setResult(com.kingdee.eas.eai.EaiResultEnum item)
    {
		if (item != null) {
        setInt("result", item.getValue());
		}
    }
    /**
     * Object:����ƾ֤�ӿ���־'s ����ʱ��property 
     */
    public java.util.Date getLastUpdateTime()
    {
        return getDate("lastUpdateTime");
    }
    public void setLastUpdateTime(java.util.Date item)
    {
        setDate("lastUpdateTime", item);
    }
    /**
     * Object:����ƾ֤�ӿ���־'s ҵ������property 
     */
    public String getJson()
    {
        return getString("json");
    }
    public void setJson(String item)
    {
        setString("json", item);
    }
    /**
     * Object: ����ƾ֤�ӿ���־ 's �Ƶ��� property 
     */
    public com.kingdee.eas.base.permission.UserInfo getCreator()
    {
        return (com.kingdee.eas.base.permission.UserInfo)get("creator");
    }
    public void setCreator(com.kingdee.eas.base.permission.UserInfo item)
    {
        put("creator", item);
    }
    /**
     * Object:����ƾ֤�ӿ���־'s ��������property 
     */
    public String getDataType()
    {
        return getString("dataType");
    }
    public void setDataType(String item)
    {
        setString("dataType", item);
    }
    /**
     * Object:����ƾ֤�ӿ���־'s ����property 
     */
    public String getBizName()
    {
        return getString("bizName");
    }
    public void setBizName(String item)
    {
        setString("bizName", item);
    }
    /**
     * Object:����ƾ֤�ӿ���־'s ����property 
     */
    public String getGroup()
    {
        return getString("group");
    }
    public void setGroup(String item)
    {
        setString("group", item);
    }
    /**
     * Object:����ƾ֤�ӿ���־'s ����property 
     */
    public String getParent()
    {
        return getString("parent");
    }
    public void setParent(String item)
    {
        setString("parent", item);
    }
    public BOSObjectType getBOSType()
    {
        return new BOSObjectType("1DB333F6");
    }
}