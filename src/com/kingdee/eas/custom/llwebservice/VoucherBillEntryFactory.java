package com.kingdee.eas.custom.llwebservice;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class VoucherBillEntryFactory
{
    private VoucherBillEntryFactory()
    {
    }
    public static com.kingdee.eas.custom.llwebservice.IVoucherBillEntry getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.IVoucherBillEntry)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("4327125F") ,com.kingdee.eas.custom.llwebservice.IVoucherBillEntry.class);
    }
    
    public static com.kingdee.eas.custom.llwebservice.IVoucherBillEntry getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.IVoucherBillEntry)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("4327125F") ,com.kingdee.eas.custom.llwebservice.IVoucherBillEntry.class, objectCtx);
    }
    public static com.kingdee.eas.custom.llwebservice.IVoucherBillEntry getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.IVoucherBillEntry)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("4327125F"));
    }
    public static com.kingdee.eas.custom.llwebservice.IVoucherBillEntry getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.IVoucherBillEntry)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("4327125F"));
    }
}