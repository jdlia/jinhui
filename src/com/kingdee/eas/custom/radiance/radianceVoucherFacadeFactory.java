package com.kingdee.eas.custom.radiance;

import com.kingdee.bos.BOSException;
import com.kingdee.bos.BOSObjectFactory;
import com.kingdee.bos.util.BOSObjectType;
import com.kingdee.bos.Context;

public class radianceVoucherFacadeFactory
{
    private radianceVoucherFacadeFactory()
    {
    }
    public static com.kingdee.eas.custom.radiance.IradianceVoucherFacade getRemoteInstance() throws BOSException
    {
        return (com.kingdee.eas.custom.radiance.IradianceVoucherFacade)BOSObjectFactory.createRemoteBOSObject(new BOSObjectType("70960FF2") ,com.kingdee.eas.custom.radiance.IradianceVoucherFacade.class);
    }
    
    public static com.kingdee.eas.custom.radiance.IradianceVoucherFacade getRemoteInstanceWithObjectContext(Context objectCtx) throws BOSException
    {
        return (com.kingdee.eas.custom.radiance.IradianceVoucherFacade)BOSObjectFactory.createRemoteBOSObjectWithObjectContext(new BOSObjectType("70960FF2") ,com.kingdee.eas.custom.radiance.IradianceVoucherFacade.class, objectCtx);
    }
    public static com.kingdee.eas.custom.radiance.IradianceVoucherFacade getLocalInstance(Context ctx) throws BOSException
    {
        return (com.kingdee.eas.custom.radiance.IradianceVoucherFacade)BOSObjectFactory.createBOSObject(ctx, new BOSObjectType("70960FF2"));
    }
    public static com.kingdee.eas.custom.radiance.IradianceVoucherFacade getLocalInstance(String sessionID) throws BOSException
    {
        return (com.kingdee.eas.custom.radiance.IradianceVoucherFacade)BOSObjectFactory.createBOSObject(sessionID, new BOSObjectType("70960FF2"));
    }
}