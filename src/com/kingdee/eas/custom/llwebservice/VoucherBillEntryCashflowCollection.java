package com.kingdee.eas.custom.llwebservice;

import com.kingdee.bos.dao.AbstractObjectCollection;
import com.kingdee.bos.dao.IObjectPK;

public class VoucherBillEntryCashflowCollection extends AbstractObjectCollection 
{
    public VoucherBillEntryCashflowCollection()
    {
        super(VoucherBillEntryCashflowInfo.class);
    }
    public boolean add(VoucherBillEntryCashflowInfo item)
    {
        return addObject(item);
    }
    public boolean addCollection(VoucherBillEntryCashflowCollection item)
    {
        return addObjectCollection(item);
    }
    public boolean remove(VoucherBillEntryCashflowInfo item)
    {
        return removeObject(item);
    }
    public VoucherBillEntryCashflowInfo get(int index)
    {
        return(VoucherBillEntryCashflowInfo)getObject(index);
    }
    public VoucherBillEntryCashflowInfo get(Object key)
    {
        return(VoucherBillEntryCashflowInfo)getObject(key);
    }
    public void set(int index, VoucherBillEntryCashflowInfo item)
    {
        setObject(index, item);
    }
    public boolean contains(VoucherBillEntryCashflowInfo item)
    {
        return containsObject(item);
    }
    public boolean contains(Object key)
    {
        return containsKey(key);
    }
    public int indexOf(VoucherBillEntryCashflowInfo item)
    {
        return super.indexOf(item);
    }
}