package com.kingdee.eas.custom.llwebservice;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import java.lang.String;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.framework.ICoreBillBase;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.util.*;

public interface IVoucherBill extends ICoreBillBase
{
    public VoucherBillCollection getVoucherBillCollection() throws BOSException;
    public VoucherBillCollection getVoucherBillCollection(EntityViewInfo view) throws BOSException;
    public VoucherBillCollection getVoucherBillCollection(String oql) throws BOSException;
    public VoucherBillInfo getVoucherBillInfo(IObjectPK pk) throws BOSException, EASBizException;
    public VoucherBillInfo getVoucherBillInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public VoucherBillInfo getVoucherBillInfo(String oql) throws BOSException, EASBizException;
    public void passAudit(IObjectPK pk) throws BOSException, EASBizException;
}