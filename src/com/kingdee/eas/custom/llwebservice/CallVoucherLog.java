package com.kingdee.eas.custom.llwebservice;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.framework.CoreBase;
import com.kingdee.eas.eai.base.service.EAIResult;
import com.kingdee.bos.dao.IObjectPK;
import java.util.List;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.Context;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.framework.ICoreBase;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.IMetaDataPK;
import com.kingdee.eas.custom.llwebservice.app.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

public class CallVoucherLog extends CoreBase implements ICallVoucherLog
{
    public CallVoucherLog()
    {
        super();
        registerInterface(ICallVoucherLog.class, this);
    }
    public CallVoucherLog(Context ctx)
    {
        super(ctx);
        registerInterface(ICallVoucherLog.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("1DB333F6");
    }
    private CallVoucherLogController getController() throws BOSException
    {
        return (CallVoucherLogController)getBizController();
    }
    /**
     *ȡ����-System defined method
     *@return
     */
    public CallVoucherLogCollection getCallVoucherLogCollection() throws BOSException
    {
        try {
            return getController().getCallVoucherLogCollection(getContext());
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ����-System defined method
     *@param view ȡ����
     *@return
     */
    public CallVoucherLogCollection getCallVoucherLogCollection(EntityViewInfo view) throws BOSException
    {
        try {
            return getController().getCallVoucherLogCollection(getContext(), view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡ����-System defined method
     *@param oql ȡ����
     *@return
     */
    public CallVoucherLogCollection getCallVoucherLogCollection(String oql) throws BOSException
    {
        try {
            return getController().getCallVoucherLogCollection(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@return
     */
    public CallVoucherLogInfo getCallVoucherLogInfo(IObjectPK pk) throws BOSException, EASBizException
    {
        try {
            return getController().getCallVoucherLogInfo(getContext(), pk);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡֵ-System defined method
     *@param pk ȡֵ
     *@param selector ȡֵ
     *@return
     */
    public CallVoucherLogInfo getCallVoucherLogInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException
    {
        try {
            return getController().getCallVoucherLogInfo(getContext(), pk, selector);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ȡֵ-System defined method
     *@param oql ȡֵ
     *@return
     */
    public CallVoucherLogInfo getCallVoucherLogInfo(String oql) throws BOSException, EASBizException
    {
        try {
            return getController().getCallVoucherLogInfo(getContext(), oql);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ͨ��Query�����ѯ-User defined method
     *@param pk queryPK
     *@param view EntityView
     *@return
     */
    public List executeQueryByPK(IMetaDataPK pk, EntityViewInfo view) throws BOSException
    {
        try {
            return getController().executeQueryByPK(getContext(), pk, view);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *����-User defined method
     *@param model model
     */
    public void createTo(CallVoucherLogInfo model) throws BOSException
    {
        try {
            getController().createTo(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ͬ������-User defined method
     *@param database ���ݿ����
     *@param synLevel ���ȼ�
     *@param dataType �������ͱ���
     */
    public void syn(String database, int synLevel, String dataType) throws BOSException
    {
        try {
            getController().syn(getContext(), database, synLevel, dataType);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *ͬ��һ��-User defined method
     *@param info �ӿ�����
     *@param log ���log=true�������������ws������ϵͳ�������������м����ô���Զ������ݱ��ֵ��м������¼ͬ����־�����log=false�����棬Ҳ����¼��־
     *@param force �Ƿ�ǿ��ִ��
     *@return
     */
    public EAIResult synSingle(CallVoucherLogInfo info, boolean log, boolean force) throws BOSException
    {
        try {
            return getController().synSingle(getContext(), info, log, force);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *��ѯ-User defined method
     *@param pk queryPK
     *@param view entityView
     *@param start start
     *@param length ����
     *@return
     */
    public IRowSet exceuteQuery(IMetaDataPK pk, EntityViewInfo view, int start, int length) throws BOSException
    {
        try {
            return getController().exceuteQuery(getContext(), pk, view, start, length);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
    /**
     *���-User defined method
     *@param model model
     */
    public void audit(CallVoucherLogInfo model) throws BOSException
    {
        try {
            getController().audit(getContext(), model);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}