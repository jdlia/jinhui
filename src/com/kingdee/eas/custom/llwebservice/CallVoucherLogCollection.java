package com.kingdee.eas.custom.llwebservice;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class CallVoucherLogCollection extends AbstractObjectCollection 
{
    public CallVoucherLogCollection()
    {
        super(CallVoucherLogInfo.class);
    }
    public boolean add(CallVoucherLogInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(CallVoucherLogCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(CallVoucherLogInfo item)
    {
        return removeObject(item);
    }
    public CallVoucherLogInfo get(int index)
    {
        return(CallVoucherLogInfo)getObject(index);
    }
    public CallVoucherLogInfo get(Object key)
    {
        return(CallVoucherLogInfo)getObject(key);
    }
    public void set(int index, CallVoucherLogInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(CallVoucherLogInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(CallVoucherLogInfo item)
    {
        return super.indexOf(item);
    }
}