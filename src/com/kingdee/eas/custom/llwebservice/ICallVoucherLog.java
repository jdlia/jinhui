package com.kingdee.eas.custom.llwebservice;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.bos.BOSException;
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
import com.kingdee.bos.util.*;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;

public interface ICallVoucherLog extends ICoreBase
{
    public CallVoucherLogCollection getCallVoucherLogCollection() throws BOSException;
    public CallVoucherLogCollection getCallVoucherLogCollection(EntityViewInfo view) throws BOSException;
    public CallVoucherLogCollection getCallVoucherLogCollection(String oql) throws BOSException;
    public CallVoucherLogInfo getCallVoucherLogInfo(IObjectPK pk) throws BOSException, EASBizException;
    public CallVoucherLogInfo getCallVoucherLogInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public CallVoucherLogInfo getCallVoucherLogInfo(String oql) throws BOSException, EASBizException;
    public List executeQueryByPK(IMetaDataPK pk, EntityViewInfo view) throws BOSException;
    public void createTo(CallVoucherLogInfo model) throws BOSException;
    public void syn(String database, int synLevel, String dataType) throws BOSException;
    public EAIResult synSingle(CallVoucherLogInfo info, boolean log, boolean force) throws BOSException;
    public IRowSet exceuteQuery(IMetaDataPK pk, EntityViewInfo view, int start, int length) throws BOSException;
}