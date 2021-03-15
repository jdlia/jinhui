package com.kingdee.eas.custom.llwebservice;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class VoucherBillFactory
{
    private VoucherBillFactory()
    {
    }
    public static com.kingdee.eas.custom.llwebservice.IVoucherBill getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.IVoucherBill)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("C91E31D3") ,com.kingdee.eas.custom.llwebservice.IVoucherBill.class);
    }
    
    public static com.kingdee.eas.custom.llwebservice.IVoucherBill getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.IVoucherBill)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("C91E31D3") ,com.kingdee.eas.custom.llwebservice.IVoucherBill.class, objectCtx);
    }
    public static com.kingdee.eas.custom.llwebservice.IVoucherBill getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.IVoucherBill)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("C91E31D3"));
    }
    public static com.kingdee.eas.custom.llwebservice.IVoucherBill getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.llwebservice.IVoucherBill)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("C91E31D3"));
    }
}