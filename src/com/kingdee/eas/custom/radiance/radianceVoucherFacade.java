package com.kingdee.eas.custom.radiance;

import com.kingdee.bos.framework.ejb.EJBRemoteException;
import com.kingdee.bos.util.BOSObjectType;
import java.rmi.RemoteException;
import com.kingdee.bos.framework.AbstractBizCtrl;
import com.kingdee.bos.orm.template.ORMObject;

import com.kingdee.bos.Context;
import com.kingdee.bos.BOSException;
import com.kingdee.eas.custom.radiance.*;
import java.lang.String;
import com.kingdee.bos.framework.*;
import com.kingdee.bos.util.*;

public class radianceVoucherFacade extends AbstractBizCtrl implements IradianceVoucherFacade
{
    public radianceVoucherFacade()
    {
        super();
        registerInterface(IradianceVoucherFacade.class, this);
    }
    public radianceVoucherFacade(Context ctx)
    {
        super(ctx);
        registerInterface(IradianceVoucherFacade.class, this);
    }
    public BOSObjectType getType()
    {
        return new BOSObjectType("70960FF2");
    }
    private radianceVoucherFacadeController getController() throws BOSException
    {
        return (radianceVoucherFacadeController)getBizController();
    }
    /**
     *获取凭证数据-User defined method
     *@param request 字段信息
     *@return
     */
    public String getVoucherData(String request) throws BOSException
    {
        try {
            return getController().getVoucherData(getContext(), request);
        }
        catch(RemoteException err) {
            throw new EJBRemoteException(err);
        }
    }
}