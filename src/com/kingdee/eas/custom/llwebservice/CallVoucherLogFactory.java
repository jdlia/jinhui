package com.kingdee.eas.custom.llwebservice;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class CallVoucherLogFactory
{
    private CallVoucherLogFactory()
    {
    }
    public static com.kingdee.eas.custom.llwebservice.ICallVoucherLog getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.ICallVoucherLog)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("1DB333F6") ,com.kingdee.eas.custom.llwebservice.ICallVoucherLog.class);
    }
    
    public static com.kingdee.eas.custom.llwebservice.ICallVoucherLog getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.ICallVoucherLog)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("1DB333F6") ,com.kingdee.eas.custom.llwebservice.ICallVoucherLog.class, objectCtx);
    }
    public static com.kingdee.eas.custom.llwebservice.ICallVoucherLog getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.ICallVoucherLog)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("1DB333F6"));
    }
    public static com.kingdee.eas.custom.llwebservice.ICallVoucherLog getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.ICallVoucherLog)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("1DB333F6"));
    }
}