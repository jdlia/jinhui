package com.kingdee.eas.custom.llwebservice;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class VoucherBillEntryCollection extends AbstractObjectCollection 
{
    public VoucherBillEntryCollection()
    {
        super(VoucherBillEntryInfo.class);
    }
    public boolean add(VoucherBillEntryInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(VoucherBillEntryCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(VoucherBillEntryInfo item)
    {
        return removeObject(item);
    }
    public VoucherBillEntryInfo get(int index)
    {
        return(VoucherBillEntryInfo)getObject(index);
    }
    public VoucherBillEntryInfo get(Object key)
    {
        return(VoucherBillEntryInfo)getObject(key);
    }
    public void set(int index, VoucherBillEntryInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(VoucherBillEntryInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(VoucherBillEntryInfo item)
    {
        return super.indexOf(item);
    }
}