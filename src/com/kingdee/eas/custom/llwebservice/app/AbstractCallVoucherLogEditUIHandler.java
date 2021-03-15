/**
 * output package name
 */
package com.kingdee.eas.custom.llwebservice.app;

import com.kingdee.bos.Context;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.eas.framework.batchHandler.ResponseContext;


/**
 * output class name
 */
public abstract class AbstractCallVoucherLogEditUIHandler extends com.kingdee.eas.framework.app.EditUIHandler

{
	public void handleActionCreateTo(RequestContext request,ResponseContext response, Context context) throws Exception {
		_handleActionCreateTo(request,response,context);
	}
	protected void _handleActionCreateTo(RequestContext request,ResponseContext response, Context context) throws Exception {
	}
}