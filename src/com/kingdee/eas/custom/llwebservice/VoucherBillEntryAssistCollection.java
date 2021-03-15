package com.kingdee.eas.custom.llwebservice;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class VoucherBillEntryAssistCollection extends AbstractObjectCollection 
{
    public VoucherBillEntryAssistCollection()
    {
        super(VoucherBillEntryAssistInfo.class);
    }
    public boolean add(VoucherBillEntryAssistInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(VoucherBillEntryAssistCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(VoucherBillEntryAssistInfo item)
    {
        return removeObject(item);
    }
    public VoucherBillEntryAssistInfo get(int index)
    {
        return(VoucherBillEntryAssistInfo)getObject(index);
    }
    public VoucherBillEntryAssistInfo get(Object key)
    {
        return(VoucherBillEntryAssistInfo)getObject(key);
    }
    public void set(int index, VoucherBillEntryAssistInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(VoucherBillEntryAssistInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(VoucherBillEntryAssistInfo item)
    {
        return super.indexOf(item);
    }
}