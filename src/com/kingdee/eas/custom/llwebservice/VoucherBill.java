package com.kingdee.eas.custom.llwebservice;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.eas.framework.CoreBillBase;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.framework.ICoreBillBase;
import com.kingdee.bos.Context;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.custom.llwebservice.app.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

public class VoucherBill extends CoreBillBase implements IVoucherBill
{
    public VoucherBill()
    {
        super();
        registerInterface(IVoucherBill.class, this);
    }
    public VoucherBill(Context ctx)
    {
        super(ctx);
        registerInterface(IVoucherBill.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("C91E31D3");
    }
    private VoucherBillController getController() throws BOSException
    {
        return (VoucherBillController)getBizController();
    }
    /**
     *取集合-System defined method
     *@return
     */
    public VoucherBillCollection getVoucherBillCollection() throws BOSException
    {
        try {
            return getController().getVoucherBillCollection(getContext());
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
    public VoucherBillCollection getVoucherBillCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getVoucherBillCollection(getContext(), view);
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
    public VoucherBillCollection getVoucherBillCollection(String oql) throws BOSException
    {
        try {
            return getController().getVoucherBillCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *取值-System defined method
     *@param pk 取值
     *@return
     */
    public VoucherBillInfo getVoucherBillInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getVoucherBillInfo(getContext(), pk);
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
    public VoucherBillInfo getVoucherBillInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getVoucherBillInfo(getContext(), pk, selector);
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
    public VoucherBillInfo getVoucherBillInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getVoucherBillInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *审核通过-User defined method
     *@param pk pk
     */
    public void passAudit(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            getController().passAudit(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}