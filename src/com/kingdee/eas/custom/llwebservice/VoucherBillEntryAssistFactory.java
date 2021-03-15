package com.kingdee.eas.custom.llwebservice;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class VoucherBillEntryAssistFactory
{
    private VoucherBillEntryAssistFactory()
    {
    }
    public static com.kingdee.eas.custom.llwebservice.IVoucherBillEntryAssist getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.IVoucherBillEntryAssist)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("50F6FDA8") ,com.kingdee.eas.custom.llwebservice.IVoucherBillEntryAssist.class);
    }
    
    public static com.kingdee.eas.custom.llwebservice.IVoucherBillEntryAssist getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.IVoucherBillEntryAssist)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("50F6FDA8") ,com.kingdee.eas.custom.llwebservice.IVoucherBillEntryAssist.class, objectCtx);
    }
    public static com.kingdee.eas.custom.llwebservice.IVoucherBillEntryAssist getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.IVoucherBillEntryAssist)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("50F6FDA8"));
    }
    public static com.kingdee.eas.custom.llwebservice.IVoucherBillEntryAssist getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.IVoucherBillEntryAssist)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("50F6FDA8"));
    }
}