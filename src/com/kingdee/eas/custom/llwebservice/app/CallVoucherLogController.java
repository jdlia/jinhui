package com.kingdee.eas.custom.llwebservice.app;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.custom.llwebservice.CallVoucherLogInfo;
import com.kingdee.eas.eai.base.service.EAIResult;
import com.kingdee.bos.dao.IObjectPK;
import java.util.List;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.Context;
import com.kingdee.eas.custom.llwebservice.CallVoucherLogCollection;
import com.kingdee.eas.framework.app.CoreBaseController;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.IMetaDataPK;
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

import java.rmi.RemoteException;
import com.kingdee.bos.framework.ejb.BizController;

public interface CallVoucherLogController extends CoreBaseController
{
    public CallVoucherLogCollection getCallVoucherLogCollection(Context ctx) throws BOSException, RemoteException;
    public CallVoucherLogCollection getCallVoucherLogCollection(Context ctx, EntityViewInfo view) throws BOSException, RemoteException;
    public CallVoucherLogCollection getCallVoucherLogCollection(Context ctx, String oql) throws BOSException, RemoteException;
    public CallVoucherLogInfo getCallVoucherLogInfo(Context ctx, IObjectPK pk) throws BOSException, EASBizException, RemoteException;
    public CallVoucherLogInfo getCallVoucherLogInfo(Context ctx, IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException, RemoteException;
    public CallVoucherLogInfo getCallVoucherLogInfo(Context ctx, String oql) throws BOSException, EASBizException, RemoteException;
    public List executeQueryByPK(Context ctx, IMetaDataPK pk, EntityViewInfo view) throws BOSException, RemoteException;
    public void createTo(Context ctx, CallVoucherLogInfo model) throws BOSException, RemoteException;
    public void syn(Context ctx, String database, int synLevel, String dataType) throws BOSException, RemoteException;
    public EAIResult synSingle(Context ctx, CallVoucherLogInfo info, boolean log, boolean force) throws BOSException, RemoteException;
    public IRowSet exceuteQuery(Context ctx, IMetaDataPK pk, EntityViewInfo view, int start, int length) throws BOSException, RemoteException;
    public void audit(Context ctx, CallVoucherLogInfo model) throws BOSException, RemoteException;
}