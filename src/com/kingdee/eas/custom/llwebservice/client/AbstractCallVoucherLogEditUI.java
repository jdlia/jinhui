/**
 * output package name
 */
package com.kingdee.eas.custom.llwebservice.client;

import org.apache.log4j.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.event.*;
import javax.swing.KeyStroke;

import com.kingdee.bos.ctrl.swing.*;
import com.kingdee.bos.ctrl.kdf.table.*;
import com.kingdee.bos.ctrl.kdf.data.event.*;
import com.kingdee.bos.dao.*;
import com.kingdee.bos.dao.query.*;
import com.kingdee.bos.metadata.*;
import com.kingdee.bos.metadata.entity.*;
import com.kingdee.bos.ui.face.*;
import com.kingdee.bos.ui.util.ResourceBundleHelper;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.bos.service.ServiceContext;
import com.kingdee.jdbc.rowset.IRowSet;
import com.kingdee.util.enums.EnumUtils;
import com.kingdee.bos.ui.face.UIRuleUtil;
import com.kingdee.bos.ctrl.swing.event.*;
import com.kingdee.bos.ctrl.kdf.table.event.*;
import com.kingdee.bos.ctrl.extendcontrols.*;
import com.kingdee.bos.ctrl.kdf.util.render.*;
import com.kingdee.bos.ui.face.IItemAction;
import com.kingdee.eas.framework.batchHandler.RequestContext;
import com.kingdee.bos.ui.util.IUIActionPostman;
import com.kingdee.bos.appframework.client.servicebinding.ActionProxyFactory;
import com.kingdee.bos.appframework.uistatemanage.ActionStateConst;
import com.kingdee.bos.appframework.validator.ValidateHelper;
import com.kingdee.bos.appframework.uip.UINavigator;


/**
 * output class name
 */
public abstract class AbstractCallVoucherLogEditUI extends com.kingdee.eas.framework.client.EditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractCallVoucherLogEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contlocalNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contreadTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contlocalId;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contbizId;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contbizNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contresult;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contlastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDSplitPane kDSplitPane1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contdataType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contbizName;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contgroup;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contparent;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtlocalNumber;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkreadTime;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtlocalId;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtbizId;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtbizNumber;
    protected com.kingdee.bos.ctrl.swing.KDComboBox result;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pklastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDContainer kDContainer1;
    protected com.kingdee.bos.ctrl.swing.KDContainer kDContainer2;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane kDScrollPane2;
    protected com.kingdee.bos.ctrl.swing.KDTextArea kDResultDesc;
    protected com.kingdee.bos.ctrl.swing.KDScrollPane kDScrollPane1;
    protected com.kingdee.bos.ctrl.swing.KDTextArea kDJson;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtdataType;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtbizName;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtgroup;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtparent;
    protected com.kingdee.bos.ctrl.swing.KDWorkButton actionCreatTo;
    protected com.kingdee.eas.custom.llwebservice.CallVoucherLogInfo editData = null;
    protected ActionCreateTo actionCreateTo = null;
    /**
     * output class constructor
     */
    public AbstractCallVoucherLogEditUI() throws Exception
    {
        super();
        this.defaultObjectName = "editData";
        jbInit();
        
        initUIP();
    }

    /**
     * output jbInit method
     */
    private void jbInit() throws Exception
    {
        this.resHelper = new ResourceBundleHelper(AbstractCallVoucherLogEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionOnLoad
        String _tempStr = null;
        actionOnLoad.setEnabled(true);
        actionOnLoad.setDaemonRun(false);

        _tempStr = resHelper.getString("ActionOnLoad.SHORT_DESCRIPTION");
        actionOnLoad.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionOnLoad.LONG_DESCRIPTION");
        actionOnLoad.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionOnLoad.NAME");
        actionOnLoad.putValue(ItemAction.NAME, _tempStr);
        this.actionOnLoad.setExtendProperty("userDefined", "false");
        this.actionOnLoad.setExtendProperty("isObjectUpdateLock", "false");
         this.actionOnLoad.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionOnLoad.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionSubmit
        actionSubmit.setEnabled(true);
        actionSubmit.setDaemonRun(false);

        actionSubmit.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
        _tempStr = resHelper.getString("ActionSubmit.SHORT_DESCRIPTION");
        actionSubmit.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.LONG_DESCRIPTION");
        actionSubmit.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionSubmit.NAME");
        actionSubmit.putValue(ItemAction.NAME, _tempStr);
        this.actionSubmit.setBindWorkFlow(true);
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionSubmit.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPrint
        actionPrint.setEnabled(true);
        actionPrint.setDaemonRun(false);

        actionPrint.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl P"));
        _tempStr = resHelper.getString("ActionPrint.SHORT_DESCRIPTION");
        actionPrint.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrint.LONG_DESCRIPTION");
        actionPrint.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrint.NAME");
        actionPrint.putValue(ItemAction.NAME, _tempStr);
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPrint.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionPrintPreview
        actionPrintPreview.setEnabled(true);
        actionPrintPreview.setDaemonRun(false);

        actionPrintPreview.putValue(ItemAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift ctrl P"));
        _tempStr = resHelper.getString("ActionPrintPreview.SHORT_DESCRIPTION");
        actionPrintPreview.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrintPreview.LONG_DESCRIPTION");
        actionPrintPreview.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
        _tempStr = resHelper.getString("ActionPrintPreview.NAME");
        actionPrintPreview.putValue(ItemAction.NAME, _tempStr);
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.NetFunctionService());
         this.actionPrintPreview.addService(new com.kingdee.eas.framework.client.service.UserMonitorService());
        //actionCreateTo
        this.actionCreateTo = new ActionCreateTo(this);
        getActionManager().registerAction("actionCreateTo", actionCreateTo);
        this.actionCreateTo.setBindWorkFlow(true);
        this.actionCreateTo.setExtendProperty("canForewarn", "true");
        this.actionCreateTo.setExtendProperty("userDefined", "true");
        this.actionCreateTo.setExtendProperty("isObjectUpdateLock", "false");
         this.actionCreateTo.addService(new com.kingdee.eas.framework.client.service.PermissionService());
         this.actionCreateTo.addService(new com.kingdee.eas.framework.client.service.ForewarnService());
         this.actionCreateTo.addService(new com.kingdee.eas.framework.client.service.WorkFlowService());
        this.contlocalNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contreadTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contlocalId = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contbizId = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contbizNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contresult = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contlastUpdateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDSplitPane1 = new com.kingdee.bos.ctrl.swing.KDSplitPane();
        this.contdataType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contbizName = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contgroup = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contparent = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.txtlocalNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkreadTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtlocalId = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtbizId = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtbizNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.result = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.pklastUpdateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.kDContainer1 = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.kDContainer2 = new com.kingdee.bos.ctrl.swing.KDContainer();
        this.kDScrollPane2 = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.kDResultDesc = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.kDScrollPane1 = new com.kingdee.bos.ctrl.swing.KDScrollPane();
        this.kDJson = new com.kingdee.bos.ctrl.swing.KDTextArea();
        this.txtdataType = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtbizName = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtgroup = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtparent = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.actionCreatTo = new com.kingdee.bos.ctrl.swing.KDWorkButton();
        this.contlocalNumber.setName("contlocalNumber");
        this.contreadTime.setName("contreadTime");
        this.contlocalId.setName("contlocalId");
        this.contbizId.setName("contbizId");
        this.contbizNumber.setName("contbizNumber");
        this.contresult.setName("contresult");
        this.contlastUpdateTime.setName("contlastUpdateTime");
        this.kDSplitPane1.setName("kDSplitPane1");
        this.contdataType.setName("contdataType");
        this.contbizName.setName("contbizName");
        this.contgroup.setName("contgroup");
        this.contparent.setName("contparent");
        this.txtlocalNumber.setName("txtlocalNumber");
        this.pkreadTime.setName("pkreadTime");
        this.txtlocalId.setName("txtlocalId");
        this.txtbizId.setName("txtbizId");
        this.txtbizNumber.setName("txtbizNumber");
        this.result.setName("result");
        this.pklastUpdateTime.setName("pklastUpdateTime");
        this.kDContainer1.setName("kDContainer1");
        this.kDContainer2.setName("kDContainer2");
        this.kDScrollPane2.setName("kDScrollPane2");
        this.kDResultDesc.setName("kDResultDesc");
        this.kDScrollPane1.setName("kDScrollPane1");
        this.kDJson.setName("kDJson");
        this.txtdataType.setName("txtdataType");
        this.txtbizName.setName("txtbizName");
        this.txtgroup.setName("txtgroup");
        this.txtparent.setName("txtparent");
        this.actionCreatTo.setName("actionCreatTo");
        // CoreUI
        // contlocalNumber		
        this.contlocalNumber.setBoundLabelText(resHelper.getString("contlocalNumber.boundLabelText"));		
        this.contlocalNumber.setBoundLabelLength(100);		
        this.contlocalNumber.setBoundLabelUnderline(true);		
        this.contlocalNumber.setVisible(true);
        // contreadTime		
        this.contreadTime.setBoundLabelText(resHelper.getString("contreadTime.boundLabelText"));		
        this.contreadTime.setBoundLabelLength(100);		
        this.contreadTime.setBoundLabelUnderline(true);		
        this.contreadTime.setVisible(true);
        // contlocalId		
        this.contlocalId.setBoundLabelText(resHelper.getString("contlocalId.boundLabelText"));		
        this.contlocalId.setBoundLabelLength(100);		
        this.contlocalId.setBoundLabelUnderline(true);		
        this.contlocalId.setVisible(true);
        // contbizId		
        this.contbizId.setBoundLabelText(resHelper.getString("contbizId.boundLabelText"));		
        this.contbizId.setBoundLabelLength(100);		
        this.contbizId.setBoundLabelUnderline(true);		
        this.contbizId.setVisible(true);
        // contbizNumber		
        this.contbizNumber.setBoundLabelText(resHelper.getString("contbizNumber.boundLabelText"));		
        this.contbizNumber.setBoundLabelLength(100);		
        this.contbizNumber.setBoundLabelUnderline(true);		
        this.contbizNumber.setVisible(true);
        // contresult		
        this.contresult.setBoundLabelText(resHelper.getString("contresult.boundLabelText"));		
        this.contresult.setBoundLabelLength(100);		
        this.contresult.setBoundLabelUnderline(true);		
        this.contresult.setVisible(true);
        // contlastUpdateTime		
        this.contlastUpdateTime.setBoundLabelText(resHelper.getString("contlastUpdateTime.boundLabelText"));		
        this.contlastUpdateTime.setBoundLabelLength(100);		
        this.contlastUpdateTime.setBoundLabelUnderline(true);		
        this.contlastUpdateTime.setVisible(true);
        // kDSplitPane1		
        this.kDSplitPane1.setResizeWeight(0.7);		
        this.kDSplitPane1.setAutoscrolls(true);
        // contdataType		
        this.contdataType.setBoundLabelText(resHelper.getString("contdataType.boundLabelText"));		
        this.contdataType.setBoundLabelLength(100);		
        this.contdataType.setBoundLabelUnderline(true);		
        this.contdataType.setVisible(true);
        // contbizName		
        this.contbizName.setBoundLabelText(resHelper.getString("contbizName.boundLabelText"));		
        this.contbizName.setBoundLabelLength(100);		
        this.contbizName.setBoundLabelUnderline(true);		
        this.contbizName.setVisible(true);
        // contgroup		
        this.contgroup.setBoundLabelText(resHelper.getString("contgroup.boundLabelText"));		
        this.contgroup.setBoundLabelLength(100);		
        this.contgroup.setBoundLabelUnderline(true);		
        this.contgroup.setVisible(true);
        // contparent		
        this.contparent.setBoundLabelText(resHelper.getString("contparent.boundLabelText"));		
        this.contparent.setBoundLabelLength(100);		
        this.contparent.setBoundLabelUnderline(true);		
        this.contparent.setVisible(true);
        // txtlocalNumber		
        this.txtlocalNumber.setVisible(true);		
        this.txtlocalNumber.setHorizontalAlignment(2);		
        this.txtlocalNumber.setMaxLength(100);		
        this.txtlocalNumber.setRequired(false);
        // pkreadTime		
        this.pkreadTime.setVisible(true);		
        this.pkreadTime.setRequired(false);
        // txtlocalId		
        this.txtlocalId.setVisible(true);		
        this.txtlocalId.setHorizontalAlignment(2);		
        this.txtlocalId.setMaxLength(44);		
        this.txtlocalId.setRequired(false);
        // txtbizId		
        this.txtbizId.setVisible(true);		
        this.txtbizId.setHorizontalAlignment(2);		
        this.txtbizId.setMaxLength(100);		
        this.txtbizId.setRequired(false);
        // txtbizNumber		
        this.txtbizNumber.setVisible(true);		
        this.txtbizNumber.setHorizontalAlignment(2);		
        this.txtbizNumber.setMaxLength(100);		
        this.txtbizNumber.setRequired(false);
        // result		
        this.result.setVisible(true);		
        this.result.addItems(EnumUtils.getEnumList("com.kingdee.eas.eai.EaiResultEnum").toArray());		
        this.result.setRequired(false);
        // pklastUpdateTime		
        this.pklastUpdateTime.setVisible(true);		
        this.pklastUpdateTime.setRequired(false);
        // kDContainer1		
        this.kDContainer1.setTitle(resHelper.getString("kDContainer1.title"));
        // kDContainer2		
        this.kDContainer2.setTitle(resHelper.getString("kDContainer2.title"));
        // kDScrollPane2
        // kDResultDesc
        // kDScrollPane1
        // kDJson		
        this.kDJson.setRows(20);
        // txtdataType		
        this.txtdataType.setVisible(true);		
        this.txtdataType.setHorizontalAlignment(2);		
        this.txtdataType.setMaxLength(100);		
        this.txtdataType.setRequired(false);
        // txtbizName		
        this.txtbizName.setVisible(true);		
        this.txtbizName.setHorizontalAlignment(2);		
        this.txtbizName.setMaxLength(100);		
        this.txtbizName.setRequired(false);
        // txtgroup		
        this.txtgroup.setVisible(true);		
        this.txtgroup.setHorizontalAlignment(2);		
        this.txtgroup.setMaxLength(100);		
        this.txtgroup.setRequired(false);
        // txtparent		
        this.txtparent.setVisible(true);		
        this.txtparent.setHorizontalAlignment(2);		
        this.txtparent.setMaxLength(100);		
        this.txtparent.setRequired(false);
        // actionCreatTo
        this.actionCreatTo.setAction((IItemAction)ActionProxyFactory.getProxy(actionCreateTo, new Class[] { IItemAction.class }, getServiceContext()));		
        this.actionCreatTo.setText(resHelper.getString("actionCreatTo.text"));
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {txtlocalNumber,pkreadTime,txtlocalId,txtbizNumber,txtbizId,result,pklastUpdateTime,txtdataType,txtbizName,txtgroup,txtparent}));
        this.setFocusCycleRoot(true);
		//Register control's property binding
		registerBindings();
		registerUIState();


    }

	public com.kingdee.bos.ctrl.swing.KDToolBar[] getUIMultiToolBar(){
		java.util.List list = new java.util.ArrayList();
		com.kingdee.bos.ctrl.swing.KDToolBar[] bars = super.getUIMultiToolBar();
		if (bars != null) {
			list.addAll(java.util.Arrays.asList(bars));
		}
		return (com.kingdee.bos.ctrl.swing.KDToolBar[])list.toArray(new com.kingdee.bos.ctrl.swing.KDToolBar[list.size()]);
	}




    /**
     * output initUIContentLayout method
     */
    public void initUIContentLayout()
    {
        this.setBounds(new Rectangle(0, 0, 965, 625));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(0, 0, 965, 625));
        contlocalNumber.setBounds(new Rectangle(10, 34, 270, 19));
        this.add(contlocalNumber, new KDLayout.Constraints(10, 34, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contreadTime.setBounds(new Rectangle(640, 34, 270, 19));
        this.add(contreadTime, new KDLayout.Constraints(640, 34, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contlocalId.setBounds(new Rectangle(325, 34, 270, 19));
        this.add(contlocalId, new KDLayout.Constraints(325, 34, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contbizId.setBounds(new Rectangle(325, 10, 270, 19));
        this.add(contbizId, new KDLayout.Constraints(325, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contbizNumber.setBounds(new Rectangle(10, 10, 270, 19));
        this.add(contbizNumber, new KDLayout.Constraints(10, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contresult.setBounds(new Rectangle(325, 58, 270, 19));
        this.add(contresult, new KDLayout.Constraints(325, 58, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contlastUpdateTime.setBounds(new Rectangle(640, 10, 270, 19));
        this.add(contlastUpdateTime, new KDLayout.Constraints(640, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        kDSplitPane1.setBounds(new Rectangle(10, 106, 936, 511));
        this.add(kDSplitPane1, new KDLayout.Constraints(10, 106, 936, 511, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contdataType.setBounds(new Rectangle(10, 58, 270, 19));
        this.add(contdataType, new KDLayout.Constraints(10, 58, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contbizName.setBounds(new Rectangle(640, 58, 270, 19));
        this.add(contbizName, new KDLayout.Constraints(640, 58, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contgroup.setBounds(new Rectangle(10, 82, 270, 19));
        this.add(contgroup, new KDLayout.Constraints(10, 82, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contparent.setBounds(new Rectangle(325, 82, 270, 19));
        this.add(contparent, new KDLayout.Constraints(325, 82, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //contlocalNumber
        contlocalNumber.setBoundEditor(txtlocalNumber);
        //contreadTime
        contreadTime.setBoundEditor(pkreadTime);
        //contlocalId
        contlocalId.setBoundEditor(txtlocalId);
        //contbizId
        contbizId.setBoundEditor(txtbizId);
        //contbizNumber
        contbizNumber.setBoundEditor(txtbizNumber);
        //contresult
        contresult.setBoundEditor(result);
        //contlastUpdateTime
        contlastUpdateTime.setBoundEditor(pklastUpdateTime);
        //kDSplitPane1
        kDSplitPane1.add(kDContainer1, "right");
        kDSplitPane1.add(kDContainer2, "left");
        //kDContainer1
        kDContainer1.getContentPane().setLayout(new KDLayout());
        kDContainer1.getContentPane().putClientProperty("OriginalBounds", new Rectangle(0, 0, 462, 510));        kDScrollPane2.setBounds(new Rectangle(0, 0, 463, 509));
        kDContainer1.getContentPane().add(kDScrollPane2, new KDLayout.Constraints(0, 0, 463, 509, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //kDScrollPane2
        kDScrollPane2.getViewport().add(kDResultDesc, null);
        //kDContainer2
        kDContainer2.getContentPane().setLayout(new KDLayout());
        kDContainer2.getContentPane().putClientProperty("OriginalBounds", new Rectangle(0, 0, 462, 510));        kDScrollPane1.setBounds(new Rectangle(0, 0, 462, 504));
        kDContainer2.getContentPane().add(kDScrollPane1, new KDLayout.Constraints(0, 0, 462, 504, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        //kDScrollPane1
        kDScrollPane1.getViewport().add(kDJson, null);
        //contdataType
        contdataType.setBoundEditor(txtdataType);
        //contbizName
        contbizName.setBoundEditor(txtbizName);
        //contgroup
        contgroup.setBoundEditor(txtgroup);
        //contparent
        contparent.setBoundEditor(txtparent);

    }


    /**
     * output initUIMenuBarLayout method
     */
    public void initUIMenuBarLayout()
    {
        this.menuBar.add(menuFile);
        this.menuBar.add(menuEdit);
        this.menuBar.add(MenuService);
        this.menuBar.add(menuView);
        this.menuBar.add(menuBiz);
        this.menuBar.add(menuTool);
        this.menuBar.add(menuHelp);
        //menuFile
        menuFile.add(menuItemAddNew);
        menuFile.add(kDSeparator1);
        menuFile.add(menuItemCloudFeed);
        menuFile.add(menuItemSave);
        menuFile.add(menuItemCloudScreen);
        menuFile.add(menuItemSubmit);
        menuFile.add(menuItemCloudShare);
        menuFile.add(menuSubmitOption);
        menuFile.add(kdSeparatorFWFile1);
        menuFile.add(rMenuItemSubmit);
        menuFile.add(rMenuItemSubmitAndAddNew);
        menuFile.add(rMenuItemSubmitAndPrint);
        menuFile.add(separatorFile1);
        menuFile.add(MenuItemAttachment);
        menuFile.add(kDSeparator2);
        menuFile.add(menuItemPageSetup);
        menuFile.add(menuItemPrint);
        menuFile.add(menuItemPrintPreview);
        menuFile.add(kDSeparator3);
        menuFile.add(menuItemExitCurrent);
        //menuSubmitOption
        menuSubmitOption.add(chkMenuItemSubmitAndAddNew);
        menuSubmitOption.add(chkMenuItemSubmitAndPrint);
        //menuEdit
        menuEdit.add(menuItemCopy);
        menuEdit.add(menuItemEdit);
        menuEdit.add(menuItemRemove);
        menuEdit.add(kDSeparator4);
        menuEdit.add(menuItemReset);
        //MenuService
        MenuService.add(MenuItemKnowStore);
        MenuService.add(MenuItemAnwser);
        MenuService.add(SepratorService);
        MenuService.add(MenuItemRemoteAssist);
        //menuView
        menuView.add(menuItemFirst);
        menuView.add(menuItemPre);
        menuView.add(menuItemNext);
        menuView.add(menuItemLast);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        //menuTool
        menuTool.add(menuItemMsgFormat);
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
        //menuHelp
        menuHelp.add(menuItemHelp);
        menuHelp.add(kDSeparator12);
        menuHelp.add(menuItemRegPro);
        menuHelp.add(menuItemPersonalSite);
        menuHelp.add(helpseparatorDiv);
        menuHelp.add(menuitemProductval);
        menuHelp.add(kDSeparatorProduct);
        menuHelp.add(menuItemAbout);

    }

    /**
     * output initUIToolBarLayout method
     */
    public void initUIToolBarLayout()
    {
        this.toolBar.add(btnAddNew);
        this.toolBar.add(btnCloud);
        this.toolBar.add(btnEdit);
        this.toolBar.add(btnXunTong);
        this.toolBar.add(btnReset);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnSave);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnAttachment);
        this.toolBar.add(separatorFW1);
        this.toolBar.add(btnPageSetup);
        this.toolBar.add(btnPrint);
        this.toolBar.add(btnPrintPreview);
        this.toolBar.add(separatorFW2);
        this.toolBar.add(btnFirst);
        this.toolBar.add(btnPre);
        this.toolBar.add(btnNext);
        this.toolBar.add(btnLast);
        this.toolBar.add(separatorFW3);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);
        this.toolBar.add(actionCreatTo);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("localNumber", String.class, this.txtlocalNumber, "text");
		dataBinder.registerBinding("readTime", java.util.Date.class, this.pkreadTime, "value");
		dataBinder.registerBinding("localId", String.class, this.txtlocalId, "text");
		dataBinder.registerBinding("bizId", String.class, this.txtbizId, "text");
		dataBinder.registerBinding("bizNumber", String.class, this.txtbizNumber, "text");
		dataBinder.registerBinding("result", com.kingdee.eas.eai.EaiResultEnum.class, this.result, "selectedItem");
		dataBinder.registerBinding("lastUpdateTime", java.util.Date.class, this.pklastUpdateTime, "value");
		dataBinder.registerBinding("resultDesc", String.class, this.kDResultDesc, "text");
		dataBinder.registerBinding("json", String.class, this.kDJson, "text");
		dataBinder.registerBinding("dataType", String.class, this.txtdataType, "text");
		dataBinder.registerBinding("bizName", String.class, this.txtbizName, "text");
		dataBinder.registerBinding("group", String.class, this.txtgroup, "text");
		dataBinder.registerBinding("parent", String.class, this.txtparent, "text");		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.custom.llwebservice.app.CallVoucherLogEditUIHandler";
	}
	public IUIActionPostman prepareInit() {
		IUIActionPostman clientHanlder = super.prepareInit();
		if (clientHanlder != null) {
			RequestContext request = new RequestContext();
    		request.setClassName(getUIHandlerClassName());
			clientHanlder.setRequestContext(request);
		}
		return clientHanlder;
    }
	
	public boolean isPrepareInit() {
    	return false;
    }
    protected void initUIP() {
        super.initUIP();
    }


    /**
     * output onShow method
     */
    public void onShow() throws Exception
    {
        super.onShow();
        this.txtlocalNumber.requestFocusInWindow();
    }

	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
        this.editData = (com.kingdee.eas.custom.llwebservice.CallVoucherLogInfo)ov;
    }

    /**
     * output loadFields method
     */
    public void loadFields()
    {
        dataBinder.loadFields();
    }
		protected void setOrgF7(KDBizPromptBox f7,com.kingdee.eas.basedata.org.OrgType orgType) throws Exception
		{
			com.kingdee.eas.basedata.org.client.f7.NewOrgUnitFilterInfoProducer oufip = new com.kingdee.eas.basedata.org.client.f7.NewOrgUnitFilterInfoProducer(orgType);
			oufip.getModel().setIsCUFilter(true);
			f7.setFilterInfoProducer(oufip);
		}

    /**
     * output storeFields method
     */
    public void storeFields()
    {
		dataBinder.storeFields();
    }

	/**
	 * ????????§µ??
	 */
	protected void registerValidator() {
    	getValidateHelper().setCustomValidator( getValidator() );
		getValidateHelper().registerBindProperty("localNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("readTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("localId", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bizId", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bizNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("result", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("resultDesc", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("json", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("dataType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bizName", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("group", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("parent", ValidateHelper.ON_SAVE);    		
	}



    /**
     * output setOprtState method
     */
    public void setOprtState(String oprtType)
    {
        super.setOprtState(oprtType);
        if (STATUS_ADDNEW.equals(this.oprtState)) {
        } else if (STATUS_EDIT.equals(this.oprtState)) {
        } else if (STATUS_VIEW.equals(this.oprtState)) {
        }
    }

    /**
     * output getSelectors method
     */
    public SelectorItemCollection getSelectors()
    {
        SelectorItemCollection sic = new SelectorItemCollection();
		String selectorAll = System.getProperty("selector.all");
		if(StringUtils.isEmpty(selectorAll)){
			selectorAll = "true";
		}
        sic.add(new SelectorItemInfo("localNumber"));
        sic.add(new SelectorItemInfo("readTime"));
        sic.add(new SelectorItemInfo("localId"));
        sic.add(new SelectorItemInfo("bizId"));
        sic.add(new SelectorItemInfo("bizNumber"));
        sic.add(new SelectorItemInfo("result"));
        sic.add(new SelectorItemInfo("lastUpdateTime"));
        sic.add(new SelectorItemInfo("resultDesc"));
        sic.add(new SelectorItemInfo("json"));
        sic.add(new SelectorItemInfo("dataType"));
        sic.add(new SelectorItemInfo("bizName"));
        sic.add(new SelectorItemInfo("group"));
        sic.add(new SelectorItemInfo("parent"));
        return sic;
    }        
    	

    /**
     * output actionOnLoad_actionPerformed method
     */
    public void actionOnLoad_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionOnLoad_actionPerformed(e);
    }
    	

    /**
     * output actionSubmit_actionPerformed method
     */
    public void actionSubmit_actionPerformed(ActionEvent e) throws Exception
    {
        super.actionSubmit_actionPerformed(e);
    }
    	

    /**
     * output actionPrint_actionPerformed method
     */
    public void actionPrint_actionPerformed(ActionEvent e) throws Exception
    {
        ArrayList idList = new ArrayList();
    	if (editData != null && !StringUtils.isEmpty(editData.getString("id"))) {
    		idList.add(editData.getString("id"));
    	}
        if (idList == null || idList.size() == 0 || getTDQueryPK() == null || getTDFileName() == null)
            return;
        com.kingdee.bos.ctrl.kdf.data.impl.BOSQueryDelegate data = new com.kingdee.eas.framework.util.CommonDataProvider(idList,getTDQueryPK());
        com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
        appHlp.print(getTDFileName(), data, javax.swing.SwingUtilities.getWindowAncestor(this));
    }
    	

    /**
     * output actionPrintPreview_actionPerformed method
     */
    public void actionPrintPreview_actionPerformed(ActionEvent e) throws Exception
    {
        ArrayList idList = new ArrayList();
        if (editData != null && !StringUtils.isEmpty(editData.getString("id"))) {
    		idList.add(editData.getString("id"));
    	}
        if (idList == null || idList.size() == 0 || getTDQueryPK() == null || getTDFileName() == null)
            return;
        com.kingdee.bos.ctrl.kdf.data.impl.BOSQueryDelegate data = new com.kingdee.eas.framework.util.CommonDataProvider(idList,getTDQueryPK());
        com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper appHlp = new com.kingdee.bos.ctrl.report.forapp.kdnote.client.KDNoteHelper();
        appHlp.printPreview(getTDFileName(), data, javax.swing.SwingUtilities.getWindowAncestor(this));
    }
    	

    /**
     * output actionCreateTo_actionPerformed method
     */
    public void actionCreateTo_actionPerformed(ActionEvent e) throws Exception
    {
        com.kingdee.eas.custom.llwebservice.CallVoucherLogFactory.getRemoteInstance().createTo(editData);
    }
	public RequestContext prepareActionOnLoad(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionOnLoad(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionOnLoad() {
    	return false;
    }
	public RequestContext prepareActionSubmit(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionSubmit(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionSubmit() {
    	return false;
    }
	public RequestContext prepareActionPrint(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionPrint(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPrint() {
    	return false;
    }
	public RequestContext prepareActionPrintPreview(IItemAction itemAction) throws Exception {
			RequestContext request = super.prepareActionPrintPreview(itemAction);		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionPrintPreview() {
    	return false;
    }
	public RequestContext prepareActionCreateTo(IItemAction itemAction) throws Exception {
			RequestContext request = new RequestContext();		
		if (request != null) {
    		request.setClassName(getUIHandlerClassName());
		}
		return request;
    }
	
	public boolean isPrepareActionCreateTo() {
    	return false;
    }

    /**
     * output ActionCreateTo class
     */     
    protected class ActionCreateTo extends ItemAction {     
    
        public ActionCreateTo()
        {
            this(null);
        }

        public ActionCreateTo(IUIObject uiObject)
        {     
		super(uiObject);     
        
            String _tempStr = null;
            _tempStr = resHelper.getString("ActionCreateTo.SHORT_DESCRIPTION");
            this.putValue(ItemAction.SHORT_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCreateTo.LONG_DESCRIPTION");
            this.putValue(ItemAction.LONG_DESCRIPTION, _tempStr);
            _tempStr = resHelper.getString("ActionCreateTo.NAME");
            this.putValue(ItemAction.NAME, _tempStr);
        }

        public void actionPerformed(ActionEvent e)
        {
        	getUIContext().put("ORG.PK", getOrgPK(this));
            innerActionPerformed("eas", AbstractCallVoucherLogEditUI.this, "ActionCreateTo", "actionCreateTo_actionPerformed", e);
        }
    }

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.custom.llwebservice.client", "CallVoucherLogEditUI");
    }
    /**
     * output isBindWorkFlow method
     */
    public boolean isBindWorkFlow()
    {
        return true;
    }

    /**
     * output getEditUIName method
     */
    protected String getEditUIName()
    {
        return com.kingdee.eas.custom.llwebservice.client.CallVoucherLogEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.custom.llwebservice.CallVoucherLogFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.custom.llwebservice.CallVoucherLogInfo objectValue = new com.kingdee.eas.custom.llwebservice.CallVoucherLogInfo();
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }


    	protected String getTDFileName() {
    	return "/bim/custom/llwebservice/CallVoucherLog";
	}
    protected IMetaDataPK getTDQueryPK() {
    	return new MetaDataPK("com.kingdee.eas.custom.llwebservice.app.CallVoucherLogQuery");
	}
    

    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {        
        return null;
	}
    /**
     * output applyDefaultValue method
     */
    protected void applyDefaultValue(IObjectValue vo) {        
		vo.put("result",new Integer(1));
        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}

}