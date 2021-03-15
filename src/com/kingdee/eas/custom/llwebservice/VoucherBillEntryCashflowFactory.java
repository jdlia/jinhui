package com.kingdee.eas.custom.llwebservice;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class VoucherBillEntryCashflowFactory
{
    private VoucherBillEntryCashflowFactory()
    {
    }
    public static com.kingdee.eas.custom.llwebservice.IVoucherBillEntryCashflow getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.IVoucherBillEntryCashflow)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("06AC5260") ,com.kingdee.eas.custom.llwebservice.IVoucherBillEntryCashflow.class);
    }
    
    public static com.kingdee.eas.custom.llwebservice.IVoucherBillEntryCashflow getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.IVoucherBillEntryCashflow)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("06AC5260") ,com.kingdee.eas.custom.llwebservice.IVoucherBillEntryCashflow.class, objectCtx);
    }
    public static com.kingdee.eas.custom.llwebservice.IVoucherBillEntryCashflow getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.IVoucherBillEntryCashflow)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("06AC5260"));
    }
    public static com.kingdee.eas.custom.llwebservice.IVoucherBillEntryCashflow getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.IVoucherBillEntryCashflow)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("06AC5260"));
    }
}