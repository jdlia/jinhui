package com.kingdee.eas.custom.llwebservice;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class VoucherBillCollection extends AbstractObjectCollection 
{
    public VoucherBillCollection()
    {
        super(VoucherBillInfo.class);
    }
    public boolean add(VoucherBillInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(VoucherBillCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(VoucherBillInfo item)
    {
        return removeObject(item);
    }
    public VoucherBillInfo get(int index)
    {
        return(VoucherBillInfo)getObject(index);
    }
    public VoucherBillInfo get(Object key)
    {
        return(VoucherBillInfo)getObject(key);
    }
    public void set(int index, VoucherBillInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(VoucherBillInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(VoucherBillInfo item)
    {
        return super.indexOf(item);
    }
}