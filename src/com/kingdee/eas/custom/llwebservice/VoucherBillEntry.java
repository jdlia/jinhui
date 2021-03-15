package com.kingdee.eas.custom.llwebservice;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.Context;
import com.kingdee.eas.framework.ICoreBillEntryBase;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.CoreBillEntryBase;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.custom.llwebservice.app.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

public class VoucherBillEntry extends CoreBillEntryBase implements IVoucherBillEntry
{
    public VoucherBillEntry()
    {
        super();
        registerInterface(IVoucherBillEntry.class, this);
    }
    public VoucherBillEntry(Context ctx)
    {
        super(ctx);
        registerInterface(IVoucherBillEntry.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("4327125F");
    }
    private VoucherBillEntryController getController() throws BOSException
    {
        return (VoucherBillEntryController)getBizController();
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public VoucherBillEntryInfo getVoucherBillEntryInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getVoucherBillEntryInfo(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@param selector 取值
     *@return
     */
    public VoucherBillEntryInfo getVoucherBillEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getVoucherBillEntryInfo(getContext(), pk, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param oql 取值
     *@return
     */
    public VoucherBillEntryInfo getVoucherBillEntryInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getVoucherBillEntryInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@return
     */
    public VoucherBillEntryCollection getVoucherBillEntryCollection() throws BOSException
    {
        try {
            return getController().getVoucherBillEntryCollection(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@param view 取集合
     *@return
     */
    public VoucherBillEntryCollection getVoucherBillEntryCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getVoucherBillEntryCollection(getContext(), view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取集合-System defined method
     *@param oql 取集合
     *@return
     */
    public VoucherBillEntryCollection getVoucherBillEntryCollection(String oql) throws BOSException
    {
        try {
            return getController().getVoucherBillEntryCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}