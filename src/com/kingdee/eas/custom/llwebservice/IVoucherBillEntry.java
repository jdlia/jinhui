package com.kingdee.eas.custom.llwebservice;

import com.kingdee.bos.BOSException;
//import com.kingdee.bos.metadata.*;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;
import com.kingdee.bos.Context;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.ICoreBillEntryBase;
import com.kingdee.bos.BOSException;
import com.kingdee.bos.dao.IObjectPK;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import java.lang.String;
import com.kingdee.eas.framework.CoreBaseInfo;
import com.kingdee.eas.framework.CoreBaseCollection;
import com.kingdee.bos.framework.*;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.bos.metadata.entity.SelectorItemCollection;
import com.kingdee.bos.util.*;

public interface IVoucherBillEntry extends ICoreBillEntryBase
{
    public VoucherBillEntryInfo getVoucherBillEntryInfo(IObjectPK pk) throws BOSException, EASBizException;
    public VoucherBillEntryInfo getVoucherBillEntryInfo(IObjectPK pk, SelectorItemCollection selector) throws BOSException, EASBizException;
    public VoucherBillEntryInfo getVoucherBillEntryInfo(String oql) throws BOSException, EASBizException;
    public VoucherBillEntryCollection getVoucherBillEntryCollection() throws BOSException;
    public VoucherBillEntryCollection getVoucherBillEntryCollection(EntityViewInfo view) throws BOSException;
    public VoucherBillEntryCollection getVoucherBillEntryCollection(String oql) throws BOSException;
}