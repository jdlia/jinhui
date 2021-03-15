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
public abstract class AbstractVoucherBillEditUI extends com.kingdee.eas.framework.client.CoreBillEditUI
{
    private static final Logger logger = CoreUIObject.getLogger(AbstractVoucherBillEditUI.class);
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contCreateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contLastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contBizDate;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contDescription;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contFICompany;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contperiod;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contbookedDate;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkisReverseVoucher;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contvoucherNumber;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contresultDesc;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcurrency;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contvoucherType;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contbillStatus;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contattaches;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtEntrys;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtEntrys_detailPanel = null;
    protected com.kingdee.bos.ctrl.swing.KDCheckBox chkFivouchered;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contcreator;
    protected com.kingdee.bos.ctrl.swing.KDTabbedPane kDTabbedPane1;
    protected com.kingdee.bos.ctrl.swing.KDLabelContainer contstatus;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDateCreateTime;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker kDDateLastUpdateTime;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtNumber;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkBizDate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtDescription;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtFICompany;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtperiod;
    protected com.kingdee.bos.ctrl.swing.KDDatePicker pkbookedDate;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtvoucherNumber;
    protected com.kingdee.bos.ctrl.swing.KDTextField txtresultDesc;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtcurrency;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtvoucherType;
    protected com.kingdee.bos.ctrl.swing.KDComboBox billStatus;
    protected com.kingdee.bos.ctrl.swing.KDFormattedTextField txtattaches;
    protected com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox prmtcreator;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel1;
    protected com.kingdee.bos.ctrl.swing.KDPanel kDPanel2;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtAssist;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtAssist_detailPanel = null;
    protected com.kingdee.bos.ctrl.kdf.table.KDTable kdtCashflow;
	protected com.kingdee.eas.framework.client.multiDetail.DetailPanel kdtCashflow_detailPanel = null;
    protected com.kingdee.bos.ctrl.swing.KDComboBox status;
    protected com.kingdee.eas.custom.llwebservice.VoucherBillInfo editData = null;
    /**
     * output class constructor
     */
    public AbstractVoucherBillEditUI() throws Exception
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
        this.resHelper = new ResourceBundleHelper(AbstractVoucherBillEditUI.class.getName());
        this.setUITitle(resHelper.getString("this.title"));
        //actionSubmit
        String _tempStr = null;
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
        this.contCreateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contBizDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contDescription = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contFICompany = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contperiod = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contbookedDate = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.chkisReverseVoucher = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.contvoucherNumber = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contresultDesc = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contcurrency = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contvoucherType = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contbillStatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.contattaches = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kdtEntrys = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.chkFivouchered = new com.kingdee.bos.ctrl.swing.KDCheckBox();
        this.contcreator = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDTabbedPane1 = new com.kingdee.bos.ctrl.swing.KDTabbedPane();
        this.contstatus = new com.kingdee.bos.ctrl.swing.KDLabelContainer();
        this.kDDateCreateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.kDDateLastUpdateTime = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.pkBizDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtDescription = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtFICompany = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtperiod = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.pkbookedDate = new com.kingdee.bos.ctrl.swing.KDDatePicker();
        this.txtvoucherNumber = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.txtresultDesc = new com.kingdee.bos.ctrl.swing.KDTextField();
        this.prmtcurrency = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.prmtvoucherType = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.billStatus = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.txtattaches = new com.kingdee.bos.ctrl.swing.KDFormattedTextField();
        this.prmtcreator = new com.kingdee.bos.ctrl.extendcontrols.KDBizPromptBox();
        this.kDPanel1 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kDPanel2 = new com.kingdee.bos.ctrl.swing.KDPanel();
        this.kdtAssist = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.kdtCashflow = new com.kingdee.bos.ctrl.kdf.table.KDTable();
        this.status = new com.kingdee.bos.ctrl.swing.KDComboBox();
        this.contCreateTime.setName("contCreateTime");
        this.contLastUpdateTime.setName("contLastUpdateTime");
        this.contNumber.setName("contNumber");
        this.contBizDate.setName("contBizDate");
        this.contDescription.setName("contDescription");
        this.contFICompany.setName("contFICompany");
        this.contperiod.setName("contperiod");
        this.contbookedDate.setName("contbookedDate");
        this.chkisReverseVoucher.setName("chkisReverseVoucher");
        this.contvoucherNumber.setName("contvoucherNumber");
        this.contresultDesc.setName("contresultDesc");
        this.contcurrency.setName("contcurrency");
        this.contvoucherType.setName("contvoucherType");
        this.contbillStatus.setName("contbillStatus");
        this.contattaches.setName("contattaches");
        this.kdtEntrys.setName("kdtEntrys");
        this.chkFivouchered.setName("chkFivouchered");
        this.contcreator.setName("contcreator");
        this.kDTabbedPane1.setName("kDTabbedPane1");
        this.contstatus.setName("contstatus");
        this.kDDateCreateTime.setName("kDDateCreateTime");
        this.kDDateLastUpdateTime.setName("kDDateLastUpdateTime");
        this.txtNumber.setName("txtNumber");
        this.pkBizDate.setName("pkBizDate");
        this.txtDescription.setName("txtDescription");
        this.prmtFICompany.setName("prmtFICompany");
        this.prmtperiod.setName("prmtperiod");
        this.pkbookedDate.setName("pkbookedDate");
        this.txtvoucherNumber.setName("txtvoucherNumber");
        this.txtresultDesc.setName("txtresultDesc");
        this.prmtcurrency.setName("prmtcurrency");
        this.prmtvoucherType.setName("prmtvoucherType");
        this.billStatus.setName("billStatus");
        this.txtattaches.setName("txtattaches");
        this.prmtcreator.setName("prmtcreator");
        this.kDPanel1.setName("kDPanel1");
        this.kDPanel2.setName("kDPanel2");
        this.kdtAssist.setName("kdtAssist");
        this.kdtCashflow.setName("kdtCashflow");
        this.status.setName("status");
        // CoreUI		
        this.btnAddNew.setVisible(false);		
        this.btnEdit.setVisible(false);		
        this.btnSave.setVisible(false);		
        this.btnSubmit.setVisible(false);		
        this.btnCopy.setVisible(false);		
        this.btnTraceUp.setVisible(false);		
        this.btnWorkFlowG.setVisible(false);		
        this.btnCreateFrom.setVisible(false);		
        this.btnAddLine.setVisible(false);		
        this.btnCopyLine.setVisible(false);		
        this.btnInsertLine.setVisible(false);		
        this.btnRemoveLine.setVisible(false);		
        this.btnVoucher.setVisible(true);		
        this.btnDelVoucher.setVisible(true);		
        this.btnAuditResult.setVisible(false);		
        this.btnMultiapprove.setVisible(false);		
        this.btnNextPerson.setVisible(false);		
        this.separator1.setVisible(false);		
        this.menuItemCreateTo.setVisible(true);		
        this.separator3.setVisible(false);		
        this.menuItemTraceUp.setVisible(false);		
        this.menuItemTraceDown.setVisible(false);		
        this.menuTable1.setVisible(false);		
        this.menuItemAddLine.setVisible(false);		
        this.menuItemCopyLine.setVisible(false);		
        this.menuItemInsertLine.setVisible(false);		
        this.menuItemRemoveLine.setVisible(false);		
        this.menuItemViewSubmitProccess.setVisible(false);		
        this.menuItemViewDoProccess.setVisible(false);		
        this.menuItemAuditResult.setVisible(false);
        // contCreateTime		
        this.contCreateTime.setBoundLabelText(resHelper.getString("contCreateTime.boundLabelText"));		
        this.contCreateTime.setBoundLabelLength(100);		
        this.contCreateTime.setBoundLabelUnderline(true);		
        this.contCreateTime.setEnabled(false);
        // contLastUpdateTime		
        this.contLastUpdateTime.setBoundLabelText(resHelper.getString("contLastUpdateTime.boundLabelText"));		
        this.contLastUpdateTime.setBoundLabelLength(100);		
        this.contLastUpdateTime.setBoundLabelUnderline(true);		
        this.contLastUpdateTime.setEnabled(false);
        // contNumber		
        this.contNumber.setBoundLabelText(resHelper.getString("contNumber.boundLabelText"));		
        this.contNumber.setBoundLabelLength(100);		
        this.contNumber.setBoundLabelUnderline(true);
        // contBizDate		
        this.contBizDate.setBoundLabelText(resHelper.getString("contBizDate.boundLabelText"));		
        this.contBizDate.setBoundLabelLength(100);		
        this.contBizDate.setBoundLabelUnderline(true);		
        this.contBizDate.setBoundLabelAlignment(7);		
        this.contBizDate.setVisible(true);
        // contDescription		
        this.contDescription.setBoundLabelText(resHelper.getString("contDescription.boundLabelText"));		
        this.contDescription.setBoundLabelLength(100);		
        this.contDescription.setBoundLabelUnderline(true);
        // contFICompany		
        this.contFICompany.setBoundLabelText(resHelper.getString("contFICompany.boundLabelText"));		
        this.contFICompany.setBoundLabelLength(100);		
        this.contFICompany.setBoundLabelUnderline(true);		
        this.contFICompany.setVisible(true);
        // contperiod		
        this.contperiod.setBoundLabelText(resHelper.getString("contperiod.boundLabelText"));		
        this.contperiod.setBoundLabelLength(100);		
        this.contperiod.setBoundLabelUnderline(true);		
        this.contperiod.setVisible(true);
        // contbookedDate		
        this.contbookedDate.setBoundLabelText(resHelper.getString("contbookedDate.boundLabelText"));		
        this.contbookedDate.setBoundLabelLength(100);		
        this.contbookedDate.setBoundLabelUnderline(true);		
        this.contbookedDate.setVisible(true);
        // chkisReverseVoucher		
        this.chkisReverseVoucher.setText(resHelper.getString("chkisReverseVoucher.text"));		
        this.chkisReverseVoucher.setHorizontalAlignment(2);
        // contvoucherNumber		
        this.contvoucherNumber.setBoundLabelText(resHelper.getString("contvoucherNumber.boundLabelText"));		
        this.contvoucherNumber.setBoundLabelLength(100);		
        this.contvoucherNumber.setBoundLabelUnderline(true);		
        this.contvoucherNumber.setVisible(true);
        // contresultDesc		
        this.contresultDesc.setBoundLabelText(resHelper.getString("contresultDesc.boundLabelText"));		
        this.contresultDesc.setBoundLabelLength(100);		
        this.contresultDesc.setBoundLabelUnderline(true);		
        this.contresultDesc.setVisible(true);
        // contcurrency		
        this.contcurrency.setBoundLabelText(resHelper.getString("contcurrency.boundLabelText"));		
        this.contcurrency.setBoundLabelLength(100);		
        this.contcurrency.setBoundLabelUnderline(true);		
        this.contcurrency.setVisible(true);
        // contvoucherType		
        this.contvoucherType.setBoundLabelText(resHelper.getString("contvoucherType.boundLabelText"));		
        this.contvoucherType.setBoundLabelLength(100);		
        this.contvoucherType.setBoundLabelUnderline(true);		
        this.contvoucherType.setVisible(true);
        // contbillStatus		
        this.contbillStatus.setBoundLabelText(resHelper.getString("contbillStatus.boundLabelText"));		
        this.contbillStatus.setBoundLabelLength(100);		
        this.contbillStatus.setBoundLabelUnderline(true);		
        this.contbillStatus.setVisible(false);
        // contattaches		
        this.contattaches.setBoundLabelText(resHelper.getString("contattaches.boundLabelText"));		
        this.contattaches.setBoundLabelLength(100);		
        this.contattaches.setBoundLabelUnderline(true);		
        this.contattaches.setVisible(true);
        // kdtEntrys
		String kdtEntrysStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:Protection hidden=\"true\" /></c:Style><c:Style id=\"sCol3\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol6\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol7\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol9\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"id\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"0\" t:styleID=\"sCol0\" /><t:Column t:key=\"description\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" /><t:Column t:key=\"account\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" /><t:Column t:key=\"amount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" t:styleID=\"sCol3\" /><t:Column t:key=\"entryDC\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" /><t:Column t:key=\"currency\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" /><t:Column t:key=\"localRate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" t:styleID=\"sCol6\" /><t:Column t:key=\"qty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" t:styleID=\"sCol7\" /><t:Column t:key=\"measureUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"8\" /><t:Column t:key=\"price\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"9\" t:styleID=\"sCol9\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{id}</t:Cell><t:Cell>$Resource{description}</t:Cell><t:Cell>$Resource{account}</t:Cell><t:Cell>$Resource{amount}</t:Cell><t:Cell>$Resource{entryDC}</t:Cell><t:Cell>$Resource{currency}</t:Cell><t:Cell>$Resource{localRate}</t:Cell><t:Cell>$Resource{qty}</t:Cell><t:Cell>$Resource{measureUnit}</t:Cell><t:Cell>$Resource{price}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtEntrys.setFormatXml(resHelper.translateString("kdtEntrys",kdtEntrysStrXML));

                this.kdtEntrys.putBindContents("editData",new String[] {"id","description","account","amount","entryDC","currency","localRate","qty","measureUnit","price"});


        this.kdtEntrys.checkParsed();
        KDTextField kdtEntrys_description_TextField = new KDTextField();
        kdtEntrys_description_TextField.setName("kdtEntrys_description_TextField");
        kdtEntrys_description_TextField.setMaxLength(255);
        KDTDefaultCellEditor kdtEntrys_description_CellEditor = new KDTDefaultCellEditor(kdtEntrys_description_TextField);
        this.kdtEntrys.getColumn("description").setEditor(kdtEntrys_description_CellEditor);
        final KDBizPromptBox kdtEntrys_account_PromptBox = new KDBizPromptBox();
        kdtEntrys_account_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.account.app.F7AccountViewQuery");
        kdtEntrys_account_PromptBox.setVisible(true);
        kdtEntrys_account_PromptBox.setEditable(true);
        kdtEntrys_account_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_account_PromptBox.setEditFormat("$number$");
        kdtEntrys_account_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_account_CellEditor = new KDTDefaultCellEditor(kdtEntrys_account_PromptBox);
        this.kdtEntrys.getColumn("account").setEditor(kdtEntrys_account_CellEditor);
        ObjectValueRender kdtEntrys_account_OVR = new ObjectValueRender();
        kdtEntrys_account_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("account").setRenderer(kdtEntrys_account_OVR);
        KDFormattedTextField kdtEntrys_amount_TextField = new KDFormattedTextField();
        kdtEntrys_amount_TextField.setName("kdtEntrys_amount_TextField");
        kdtEntrys_amount_TextField.setVisible(true);
        kdtEntrys_amount_TextField.setEditable(true);
        kdtEntrys_amount_TextField.setHorizontalAlignment(2);
        kdtEntrys_amount_TextField.setDataType(1);
        	kdtEntrys_amount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntrys_amount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntrys_amount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntrys_amount_CellEditor = new KDTDefaultCellEditor(kdtEntrys_amount_TextField);
        this.kdtEntrys.getColumn("amount").setEditor(kdtEntrys_amount_CellEditor);
        KDComboBox kdtEntrys_entryDC_ComboBox = new KDComboBox();
        kdtEntrys_entryDC_ComboBox.setName("kdtEntrys_entryDC_ComboBox");
        kdtEntrys_entryDC_ComboBox.setVisible(true);
        kdtEntrys_entryDC_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.fi.gl.EntryDC").toArray());
        KDTDefaultCellEditor kdtEntrys_entryDC_CellEditor = new KDTDefaultCellEditor(kdtEntrys_entryDC_ComboBox);
        this.kdtEntrys.getColumn("entryDC").setEditor(kdtEntrys_entryDC_CellEditor);
        final KDBizPromptBox kdtEntrys_currency_PromptBox = new KDBizPromptBox();
        kdtEntrys_currency_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.CurrencyQuery");
        kdtEntrys_currency_PromptBox.setVisible(true);
        kdtEntrys_currency_PromptBox.setEditable(true);
        kdtEntrys_currency_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_currency_PromptBox.setEditFormat("$number$");
        kdtEntrys_currency_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_currency_CellEditor = new KDTDefaultCellEditor(kdtEntrys_currency_PromptBox);
        this.kdtEntrys.getColumn("currency").setEditor(kdtEntrys_currency_CellEditor);
        ObjectValueRender kdtEntrys_currency_OVR = new ObjectValueRender();
        kdtEntrys_currency_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("currency").setRenderer(kdtEntrys_currency_OVR);
        KDFormattedTextField kdtEntrys_localRate_TextField = new KDFormattedTextField();
        kdtEntrys_localRate_TextField.setName("kdtEntrys_localRate_TextField");
        kdtEntrys_localRate_TextField.setVisible(true);
        kdtEntrys_localRate_TextField.setEditable(true);
        kdtEntrys_localRate_TextField.setHorizontalAlignment(2);
        kdtEntrys_localRate_TextField.setDataType(1);
        	kdtEntrys_localRate_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E24"));
        	kdtEntrys_localRate_TextField.setMaximumValue(new java.math.BigDecimal("1.0E24"));
        kdtEntrys_localRate_TextField.setPrecision(4);
        KDTDefaultCellEditor kdtEntrys_localRate_CellEditor = new KDTDefaultCellEditor(kdtEntrys_localRate_TextField);
        this.kdtEntrys.getColumn("localRate").setEditor(kdtEntrys_localRate_CellEditor);
        KDFormattedTextField kdtEntrys_qty_TextField = new KDFormattedTextField();
        kdtEntrys_qty_TextField.setName("kdtEntrys_qty_TextField");
        kdtEntrys_qty_TextField.setVisible(true);
        kdtEntrys_qty_TextField.setEditable(true);
        kdtEntrys_qty_TextField.setHorizontalAlignment(2);
        kdtEntrys_qty_TextField.setDataType(1);
        	kdtEntrys_qty_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntrys_qty_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntrys_qty_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntrys_qty_CellEditor = new KDTDefaultCellEditor(kdtEntrys_qty_TextField);
        this.kdtEntrys.getColumn("qty").setEditor(kdtEntrys_qty_CellEditor);
        final KDBizPromptBox kdtEntrys_measureUnit_PromptBox = new KDBizPromptBox();
        kdtEntrys_measureUnit_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7MeasureUnitQuery");
        kdtEntrys_measureUnit_PromptBox.setVisible(true);
        kdtEntrys_measureUnit_PromptBox.setEditable(true);
        kdtEntrys_measureUnit_PromptBox.setDisplayFormat("$number$");
        kdtEntrys_measureUnit_PromptBox.setEditFormat("$number$");
        kdtEntrys_measureUnit_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtEntrys_measureUnit_CellEditor = new KDTDefaultCellEditor(kdtEntrys_measureUnit_PromptBox);
        this.kdtEntrys.getColumn("measureUnit").setEditor(kdtEntrys_measureUnit_CellEditor);
        ObjectValueRender kdtEntrys_measureUnit_OVR = new ObjectValueRender();
        kdtEntrys_measureUnit_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtEntrys.getColumn("measureUnit").setRenderer(kdtEntrys_measureUnit_OVR);
        KDFormattedTextField kdtEntrys_price_TextField = new KDFormattedTextField();
        kdtEntrys_price_TextField.setName("kdtEntrys_price_TextField");
        kdtEntrys_price_TextField.setVisible(true);
        kdtEntrys_price_TextField.setEditable(true);
        kdtEntrys_price_TextField.setHorizontalAlignment(2);
        kdtEntrys_price_TextField.setDataType(1);
        	kdtEntrys_price_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtEntrys_price_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtEntrys_price_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtEntrys_price_CellEditor = new KDTDefaultCellEditor(kdtEntrys_price_TextField);
        this.kdtEntrys.getColumn("price").setEditor(kdtEntrys_price_CellEditor);
        // chkFivouchered		
        this.chkFivouchered.setVisible(true);		
        this.chkFivouchered.setHorizontalAlignment(2);		
        this.chkFivouchered.setText(resHelper.getString("chkFivouchered.text"));		
        this.chkFivouchered.setEnabled(false);
        // contcreator		
        this.contcreator.setBoundLabelText(resHelper.getString("contcreator.boundLabelText"));		
        this.contcreator.setBoundLabelLength(100);		
        this.contcreator.setBoundLabelUnderline(true);		
        this.contcreator.setVisible(true);
        // kDTabbedPane1
        // contstatus		
        this.contstatus.setBoundLabelText(resHelper.getString("contstatus.boundLabelText"));		
        this.contstatus.setBoundLabelLength(100);		
        this.contstatus.setBoundLabelUnderline(true);		
        this.contstatus.setVisible(true);
        // kDDateCreateTime		
        this.kDDateCreateTime.setTimeEnabled(true);		
        this.kDDateCreateTime.setEnabled(false);
        // kDDateLastUpdateTime		
        this.kDDateLastUpdateTime.setTimeEnabled(true);		
        this.kDDateLastUpdateTime.setEnabled(false);
        // txtNumber		
        this.txtNumber.setMaxLength(80);
        // pkBizDate		
        this.pkBizDate.setEnabled(true);
        // txtDescription		
        this.txtDescription.setMaxLength(80);
        // prmtFICompany		
        this.prmtFICompany.setQueryInfo("com.kingdee.eas.basedata.org.app.CompanyOrgUnitQuery4AsstAcct");		
        this.prmtFICompany.setEditable(true);		
        this.prmtFICompany.setDisplayFormat("$name$");		
        this.prmtFICompany.setEditFormat("$number$");		
        this.prmtFICompany.setCommitFormat("$number$");		
        this.prmtFICompany.setRequired(true);
        		setOrgF7(prmtFICompany,com.kingdee.eas.basedata.org.OrgType.getEnum("Company"));
					
        // prmtperiod		
        this.prmtperiod.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7PeriodQuery");		
        this.prmtperiod.setEditable(true);		
        this.prmtperiod.setDisplayFormat("$periodNumber$");		
        this.prmtperiod.setEditFormat("$number$");		
        this.prmtperiod.setCommitFormat("$number$");		
        this.prmtperiod.setRequired(false);
        // pkbookedDate		
        this.pkbookedDate.setRequired(false);
        // txtvoucherNumber		
        this.txtvoucherNumber.setHorizontalAlignment(2);		
        this.txtvoucherNumber.setMaxLength(255);		
        this.txtvoucherNumber.setRequired(false);
        // txtresultDesc		
        this.txtresultDesc.setHorizontalAlignment(2);		
        this.txtresultDesc.setMaxLength(255);		
        this.txtresultDesc.setRequired(false);
        // prmtcurrency		
        this.prmtcurrency.setQueryInfo("com.kingdee.eas.basedata.assistant.app.CurrencyQuery");		
        this.prmtcurrency.setEditable(true);		
        this.prmtcurrency.setDisplayFormat("$name$");		
        this.prmtcurrency.setEditFormat("$number$");		
        this.prmtcurrency.setCommitFormat("$number$");		
        this.prmtcurrency.setRequired(false);
        // prmtvoucherType		
        this.prmtvoucherType.setQueryInfo("com.kingdee.eas.basedata.assistant.app.VoucherTypeQuery");		
        this.prmtvoucherType.setEditable(true);		
        this.prmtvoucherType.setDisplayFormat("$name$");		
        this.prmtvoucherType.setEditFormat("$number$");		
        this.prmtvoucherType.setCommitFormat("$number$");		
        this.prmtvoucherType.setRequired(false);
        // billStatus		
        this.billStatus.addItems(EnumUtils.getEnumList("com.kingdee.eas.eai.EaiBillStatusEnum").toArray());		
        this.billStatus.setRequired(false);		
        this.billStatus.setVisible(false);		
        this.billStatus.setOpaque(false);		
        this.billStatus.setEnabled(false);
        // txtattaches		
        this.txtattaches.setHorizontalAlignment(2);		
        this.txtattaches.setDataType(0);		
        this.txtattaches.setSupportedEmpty(true);		
        this.txtattaches.setRequired(false);
        // prmtcreator		
        this.prmtcreator.setVisible(true);		
        this.prmtcreator.setEditable(true);		
        this.prmtcreator.setDisplayFormat("$name$");		
        this.prmtcreator.setEditFormat("$number$");		
        this.prmtcreator.setCommitFormat("$number$");		
        this.prmtcreator.setRequired(false);		
        this.prmtcreator.setEnabled(false);
        // kDPanel1
        // kDPanel2
        // kdtAssist
		String kdtAssistStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol7\"><c:NumberFormat>&amp;date</c:NumberFormat></c:Style><c:Style id=\"sCol8\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol9\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol10\"><c:Protection hidden=\"true\" /><c:NumberFormat>&amp;double</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"0\" t:styleID=\"sCol0\" /><t:Column t:key=\"description\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"1\" /><t:Column t:key=\"bankAccount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"2\" /><t:Column t:key=\"innerAccount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"3\" /><t:Column t:key=\"region\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"4\" /><t:Column t:key=\"bizNumber\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"5\" /><t:Column t:key=\"settlementCode\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"6\" /><t:Column t:key=\"bizDate\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"7\" t:styleID=\"sCol7\" /><t:Column t:key=\"amount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"8\" t:styleID=\"sCol8\" /><t:Column t:key=\"qty\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"9\" t:styleID=\"sCol9\" /><t:Column t:key=\"price\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"10\" t:styleID=\"sCol10\" /><t:Column t:key=\"customer\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"11\" /><t:Column t:key=\"supplier\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"12\" /><t:Column t:key=\"measureUnit\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"13\" /><t:Column t:key=\"companyOrg\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"14\" /><t:Column t:key=\"adminOrg\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"15\" /><t:Column t:key=\"costOrg\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"16\" /><t:Column t:key=\"material\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"17\" /><t:Column t:key=\"settlementType\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"18\" /><t:Column t:key=\"person\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"19\" /><t:Column t:key=\"generalAssActType1\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType2\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType3\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType4\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType5\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType6\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType7\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"26\" /><t:Column t:key=\"generalAssActType8\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"27\" /><t:Column t:key=\"generalAssActType9\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType10\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType11\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType12\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType13\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType14\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType15\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType16\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType17\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType18\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType19\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType20\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType21\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"generalAssActType22\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{description}</t:Cell><t:Cell>$Resource{bankAccount}</t:Cell><t:Cell>$Resource{innerAccount}</t:Cell><t:Cell>$Resource{region}</t:Cell><t:Cell>$Resource{bizNumber}</t:Cell><t:Cell>$Resource{settlementCode}</t:Cell><t:Cell>$Resource{bizDate}</t:Cell><t:Cell>$Resource{amount}</t:Cell><t:Cell>$Resource{qty}</t:Cell><t:Cell>$Resource{price}</t:Cell><t:Cell>$Resource{customer}</t:Cell><t:Cell>$Resource{supplier}</t:Cell><t:Cell>$Resource{measureUnit}</t:Cell><t:Cell>$Resource{companyOrg}</t:Cell><t:Cell>$Resource{adminOrg}</t:Cell><t:Cell>$Resource{costOrg}</t:Cell><t:Cell>$Resource{material}</t:Cell><t:Cell>$Resource{settlementType}</t:Cell><t:Cell>$Resource{person}</t:Cell><t:Cell>$Resource{generalAssActType1}</t:Cell><t:Cell>$Resource{generalAssActType2}</t:Cell><t:Cell>$Resource{generalAssActType3}</t:Cell><t:Cell>$Resource{generalAssActType4}</t:Cell><t:Cell>$Resource{generalAssActType5}</t:Cell><t:Cell>$Resource{generalAssActType6}</t:Cell><t:Cell>$Resource{generalAssActType7}</t:Cell><t:Cell>$Resource{generalAssActType8}</t:Cell><t:Cell>$Resource{generalAssActType9}</t:Cell><t:Cell>$Resource{generalAssActType10}</t:Cell><t:Cell>$Resource{generalAssActType11}</t:Cell><t:Cell>$Resource{generalAssActType12}</t:Cell><t:Cell>$Resource{generalAssActType13}</t:Cell><t:Cell>$Resource{generalAssActType14}</t:Cell><t:Cell>$Resource{generalAssActType15}</t:Cell><t:Cell>$Resource{generalAssActType16}</t:Cell><t:Cell>$Resource{generalAssActType17}</t:Cell><t:Cell>$Resource{generalAssActType18}</t:Cell><t:Cell>$Resource{generalAssActType19}</t:Cell><t:Cell>$Resource{generalAssActType20}</t:Cell><t:Cell>$Resource{generalAssActType21}</t:Cell><t:Cell>$Resource{generalAssActType22}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtAssist.setFormatXml(resHelper.translateString("kdtAssist",kdtAssistStrXML));

                this.kdtAssist.putBindContents("editData",new String[] {"Assist.seq","Assist.description","Assist.bankAccount","Assist.innerAccount","Assist.region","Assist.bizNumber","Assist.settlementCode","Assist.bizDate","Assist.amount","Assist.qty","Assist.price","Assist.customer","Assist.supplier","Assist.measureUnit","Assist.companyOrg","Assist.adminOrg","Assist.costOrg","Assist.material","Assist.settlementType","Assist.person","Assist.generalAssActType1","Assist.generalAssActType2","Assist.generalAssActType3","Assist.generalAssActType4","Assist.generalAssActType5","Assist.generalAssActType6","Assist.generalAssActType7","Assist.generalAssActType8","Assist.generalAssActType9","Assist.generalAssActType10","Assist.generalAssActType11","Assist.generalAssActType12","Assist.generalAssActType13","Assist.generalAssActType14","Assist.generalAssActType15","Assist.generalAssActType16","Assist.generalAssActType17","Assist.generalAssActType18","Assist.generalAssActType19","Assist.generalAssActType20","Assist.generalAssActType21","Assist.generalAssActType22"});


        this.kdtAssist.checkParsed();
        KDFormattedTextField kdtAssist_seq_TextField = new KDFormattedTextField();
        kdtAssist_seq_TextField.setName("kdtAssist_seq_TextField");
        kdtAssist_seq_TextField.setVisible(true);
        kdtAssist_seq_TextField.setEditable(true);
        kdtAssist_seq_TextField.setHorizontalAlignment(2);
        kdtAssist_seq_TextField.setDataType(0);
        KDTDefaultCellEditor kdtAssist_seq_CellEditor = new KDTDefaultCellEditor(kdtAssist_seq_TextField);
        this.kdtAssist.getColumn("seq").setEditor(kdtAssist_seq_CellEditor);
        final KDBizPromptBox kdtAssist_bankAccount_PromptBox = new KDBizPromptBox();
        kdtAssist_bankAccount_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7AccountBankQuery");
        kdtAssist_bankAccount_PromptBox.setVisible(true);
        kdtAssist_bankAccount_PromptBox.setEditable(true);
        kdtAssist_bankAccount_PromptBox.setDisplayFormat("$number$");
        kdtAssist_bankAccount_PromptBox.setEditFormat("$number$");
        kdtAssist_bankAccount_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_bankAccount_CellEditor = new KDTDefaultCellEditor(kdtAssist_bankAccount_PromptBox);
        this.kdtAssist.getColumn("bankAccount").setEditor(kdtAssist_bankAccount_CellEditor);
        ObjectValueRender kdtAssist_bankAccount_OVR = new ObjectValueRender();
        kdtAssist_bankAccount_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("bankAccount").setRenderer(kdtAssist_bankAccount_OVR);
        final KDBizPromptBox kdtAssist_innerAccount_PromptBox = new KDBizPromptBox();
        kdtAssist_innerAccount_PromptBox.setQueryInfo("com.kingdee.eas.fm.fs.F7InnerAccountQuery");
        kdtAssist_innerAccount_PromptBox.setVisible(true);
        kdtAssist_innerAccount_PromptBox.setEditable(true);
        kdtAssist_innerAccount_PromptBox.setDisplayFormat("$number$");
        kdtAssist_innerAccount_PromptBox.setEditFormat("$number$");
        kdtAssist_innerAccount_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_innerAccount_CellEditor = new KDTDefaultCellEditor(kdtAssist_innerAccount_PromptBox);
        this.kdtAssist.getColumn("innerAccount").setEditor(kdtAssist_innerAccount_CellEditor);
        ObjectValueRender kdtAssist_innerAccount_OVR = new ObjectValueRender();
        kdtAssist_innerAccount_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("innerAccount").setRenderer(kdtAssist_innerAccount_OVR);
        final KDBizPromptBox kdtAssist_region_PromptBox = new KDBizPromptBox();
        kdtAssist_region_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7BankQuery");
        kdtAssist_region_PromptBox.setVisible(true);
        kdtAssist_region_PromptBox.setEditable(true);
        kdtAssist_region_PromptBox.setDisplayFormat("$number$");
        kdtAssist_region_PromptBox.setEditFormat("$number$");
        kdtAssist_region_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_region_CellEditor = new KDTDefaultCellEditor(kdtAssist_region_PromptBox);
        this.kdtAssist.getColumn("region").setEditor(kdtAssist_region_CellEditor);
        ObjectValueRender kdtAssist_region_OVR = new ObjectValueRender();
        kdtAssist_region_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("region").setRenderer(kdtAssist_region_OVR);
        KDDatePicker kdtAssist_bizDate_DatePicker = new KDDatePicker();
        kdtAssist_bizDate_DatePicker.setName("kdtAssist_bizDate_DatePicker");
        kdtAssist_bizDate_DatePicker.setVisible(true);
        kdtAssist_bizDate_DatePicker.setEditable(true);
        KDTDefaultCellEditor kdtAssist_bizDate_CellEditor = new KDTDefaultCellEditor(kdtAssist_bizDate_DatePicker);
        this.kdtAssist.getColumn("bizDate").setEditor(kdtAssist_bizDate_CellEditor);
        KDFormattedTextField kdtAssist_amount_TextField = new KDFormattedTextField();
        kdtAssist_amount_TextField.setName("kdtAssist_amount_TextField");
        kdtAssist_amount_TextField.setVisible(true);
        kdtAssist_amount_TextField.setEditable(true);
        kdtAssist_amount_TextField.setHorizontalAlignment(2);
        kdtAssist_amount_TextField.setDataType(1);
        	kdtAssist_amount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtAssist_amount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtAssist_amount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtAssist_amount_CellEditor = new KDTDefaultCellEditor(kdtAssist_amount_TextField);
        this.kdtAssist.getColumn("amount").setEditor(kdtAssist_amount_CellEditor);
        KDFormattedTextField kdtAssist_qty_TextField = new KDFormattedTextField();
        kdtAssist_qty_TextField.setName("kdtAssist_qty_TextField");
        kdtAssist_qty_TextField.setVisible(true);
        kdtAssist_qty_TextField.setEditable(true);
        kdtAssist_qty_TextField.setHorizontalAlignment(2);
        kdtAssist_qty_TextField.setDataType(1);
        	kdtAssist_qty_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtAssist_qty_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtAssist_qty_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtAssist_qty_CellEditor = new KDTDefaultCellEditor(kdtAssist_qty_TextField);
        this.kdtAssist.getColumn("qty").setEditor(kdtAssist_qty_CellEditor);
        KDFormattedTextField kdtAssist_price_TextField = new KDFormattedTextField();
        kdtAssist_price_TextField.setName("kdtAssist_price_TextField");
        kdtAssist_price_TextField.setVisible(true);
        kdtAssist_price_TextField.setEditable(true);
        kdtAssist_price_TextField.setHorizontalAlignment(2);
        kdtAssist_price_TextField.setDataType(1);
        	kdtAssist_price_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtAssist_price_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtAssist_price_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtAssist_price_CellEditor = new KDTDefaultCellEditor(kdtAssist_price_TextField);
        this.kdtAssist.getColumn("price").setEditor(kdtAssist_price_CellEditor);
        final KDBizPromptBox kdtAssist_customer_PromptBox = new KDBizPromptBox();
        kdtAssist_customer_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.CustomerInfoQuery");
        kdtAssist_customer_PromptBox.setVisible(true);
        kdtAssist_customer_PromptBox.setEditable(true);
        kdtAssist_customer_PromptBox.setDisplayFormat("$number$");
        kdtAssist_customer_PromptBox.setEditFormat("$number$");
        kdtAssist_customer_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_customer_CellEditor = new KDTDefaultCellEditor(kdtAssist_customer_PromptBox);
        this.kdtAssist.getColumn("customer").setEditor(kdtAssist_customer_CellEditor);
        ObjectValueRender kdtAssist_customer_OVR = new ObjectValueRender();
        kdtAssist_customer_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("customer").setRenderer(kdtAssist_customer_OVR);
        final KDBizPromptBox kdtAssist_supplier_PromptBox = new KDBizPromptBox();
        kdtAssist_supplier_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.cssp.app.F7SupplierDefaultQuery");
        kdtAssist_supplier_PromptBox.setVisible(true);
        kdtAssist_supplier_PromptBox.setEditable(true);
        kdtAssist_supplier_PromptBox.setDisplayFormat("$number$");
        kdtAssist_supplier_PromptBox.setEditFormat("$number$");
        kdtAssist_supplier_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_supplier_CellEditor = new KDTDefaultCellEditor(kdtAssist_supplier_PromptBox);
        this.kdtAssist.getColumn("supplier").setEditor(kdtAssist_supplier_CellEditor);
        ObjectValueRender kdtAssist_supplier_OVR = new ObjectValueRender();
        kdtAssist_supplier_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("supplier").setRenderer(kdtAssist_supplier_OVR);
        final KDBizPromptBox kdtAssist_measureUnit_PromptBox = new KDBizPromptBox();
        kdtAssist_measureUnit_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7MeasureUnitQuery");
        kdtAssist_measureUnit_PromptBox.setVisible(true);
        kdtAssist_measureUnit_PromptBox.setEditable(true);
        kdtAssist_measureUnit_PromptBox.setDisplayFormat("$number$");
        kdtAssist_measureUnit_PromptBox.setEditFormat("$number$");
        kdtAssist_measureUnit_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_measureUnit_CellEditor = new KDTDefaultCellEditor(kdtAssist_measureUnit_PromptBox);
        this.kdtAssist.getColumn("measureUnit").setEditor(kdtAssist_measureUnit_CellEditor);
        ObjectValueRender kdtAssist_measureUnit_OVR = new ObjectValueRender();
        kdtAssist_measureUnit_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("measureUnit").setRenderer(kdtAssist_measureUnit_OVR);
        final KDBizPromptBox kdtAssist_companyOrg_PromptBox = new KDBizPromptBox();
        kdtAssist_companyOrg_PromptBox.setQueryInfo("com.kingdee.eas.basedata.org.app.CompanyOrgUnitQuery4AsstAcct");
        kdtAssist_companyOrg_PromptBox.setVisible(true);
        kdtAssist_companyOrg_PromptBox.setEditable(true);
        kdtAssist_companyOrg_PromptBox.setDisplayFormat("$number$");
        kdtAssist_companyOrg_PromptBox.setEditFormat("$number$");
        kdtAssist_companyOrg_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_companyOrg_CellEditor = new KDTDefaultCellEditor(kdtAssist_companyOrg_PromptBox);
        this.kdtAssist.getColumn("companyOrg").setEditor(kdtAssist_companyOrg_CellEditor);
        ObjectValueRender kdtAssist_companyOrg_OVR = new ObjectValueRender();
        kdtAssist_companyOrg_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("companyOrg").setRenderer(kdtAssist_companyOrg_OVR);
        final KDBizPromptBox kdtAssist_adminOrg_PromptBox = new KDBizPromptBox();
        kdtAssist_adminOrg_PromptBox.setQueryInfo("com.kingdee.eas.basedata.org.app.AdminItemQuery");
        kdtAssist_adminOrg_PromptBox.setVisible(true);
        kdtAssist_adminOrg_PromptBox.setEditable(true);
        kdtAssist_adminOrg_PromptBox.setDisplayFormat("$number$");
        kdtAssist_adminOrg_PromptBox.setEditFormat("$number$");
        kdtAssist_adminOrg_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_adminOrg_CellEditor = new KDTDefaultCellEditor(kdtAssist_adminOrg_PromptBox);
        this.kdtAssist.getColumn("adminOrg").setEditor(kdtAssist_adminOrg_CellEditor);
        ObjectValueRender kdtAssist_adminOrg_OVR = new ObjectValueRender();
        kdtAssist_adminOrg_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("adminOrg").setRenderer(kdtAssist_adminOrg_OVR);
        final KDBizPromptBox kdtAssist_costOrg_PromptBox = new KDBizPromptBox();
        kdtAssist_costOrg_PromptBox.setQueryInfo("com.kingdee.eas.basedata.org.app.CostCenterItemQuery");
        kdtAssist_costOrg_PromptBox.setVisible(true);
        kdtAssist_costOrg_PromptBox.setEditable(true);
        kdtAssist_costOrg_PromptBox.setDisplayFormat("$number$");
        kdtAssist_costOrg_PromptBox.setEditFormat("$number$");
        kdtAssist_costOrg_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_costOrg_CellEditor = new KDTDefaultCellEditor(kdtAssist_costOrg_PromptBox);
        this.kdtAssist.getColumn("costOrg").setEditor(kdtAssist_costOrg_CellEditor);
        ObjectValueRender kdtAssist_costOrg_OVR = new ObjectValueRender();
        kdtAssist_costOrg_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("costOrg").setRenderer(kdtAssist_costOrg_OVR);
        final KDBizPromptBox kdtAssist_material_PromptBox = new KDBizPromptBox();
        kdtAssist_material_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.material.app.F7MaterialBaseInfoQuery");
        kdtAssist_material_PromptBox.setVisible(true);
        kdtAssist_material_PromptBox.setEditable(true);
        kdtAssist_material_PromptBox.setDisplayFormat("$number$");
        kdtAssist_material_PromptBox.setEditFormat("$number$");
        kdtAssist_material_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_material_CellEditor = new KDTDefaultCellEditor(kdtAssist_material_PromptBox);
        this.kdtAssist.getColumn("material").setEditor(kdtAssist_material_CellEditor);
        ObjectValueRender kdtAssist_material_OVR = new ObjectValueRender();
        kdtAssist_material_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("material").setRenderer(kdtAssist_material_OVR);
        final KDBizPromptBox kdtAssist_settlementType_PromptBox = new KDBizPromptBox();
        kdtAssist_settlementType_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.SettlementTypeQuery");
        kdtAssist_settlementType_PromptBox.setVisible(true);
        kdtAssist_settlementType_PromptBox.setEditable(true);
        kdtAssist_settlementType_PromptBox.setDisplayFormat("$number$");
        kdtAssist_settlementType_PromptBox.setEditFormat("$number$");
        kdtAssist_settlementType_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_settlementType_CellEditor = new KDTDefaultCellEditor(kdtAssist_settlementType_PromptBox);
        this.kdtAssist.getColumn("settlementType").setEditor(kdtAssist_settlementType_CellEditor);
        ObjectValueRender kdtAssist_settlementType_OVR = new ObjectValueRender();
        kdtAssist_settlementType_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("settlementType").setRenderer(kdtAssist_settlementType_OVR);
        final KDBizPromptBox kdtAssist_person_PromptBox = new KDBizPromptBox();
        kdtAssist_person_PromptBox.setQueryInfo("com.kingdee.eas.basedata.person.app.PersonQuery");
        kdtAssist_person_PromptBox.setVisible(true);
        kdtAssist_person_PromptBox.setEditable(true);
        kdtAssist_person_PromptBox.setDisplayFormat("$number$");
        kdtAssist_person_PromptBox.setEditFormat("$number$");
        kdtAssist_person_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_person_CellEditor = new KDTDefaultCellEditor(kdtAssist_person_PromptBox);
        this.kdtAssist.getColumn("person").setEditor(kdtAssist_person_CellEditor);
        ObjectValueRender kdtAssist_person_OVR = new ObjectValueRender();
        kdtAssist_person_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("person").setRenderer(kdtAssist_person_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType1_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType1_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType1_PromptBox.setVisible(true);
        kdtAssist_generalAssActType1_PromptBox.setEditable(true);
        kdtAssist_generalAssActType1_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType1_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType1_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType1_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType1_PromptBox);
        this.kdtAssist.getColumn("generalAssActType1").setEditor(kdtAssist_generalAssActType1_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType1_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType1_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType1").setRenderer(kdtAssist_generalAssActType1_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType2_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType2_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType2_PromptBox.setVisible(true);
        kdtAssist_generalAssActType2_PromptBox.setEditable(true);
        kdtAssist_generalAssActType2_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType2_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType2_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType2_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType2_PromptBox);
        this.kdtAssist.getColumn("generalAssActType2").setEditor(kdtAssist_generalAssActType2_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType2_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType2_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType2").setRenderer(kdtAssist_generalAssActType2_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType3_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType3_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType3_PromptBox.setVisible(true);
        kdtAssist_generalAssActType3_PromptBox.setEditable(true);
        kdtAssist_generalAssActType3_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType3_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType3_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType3_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType3_PromptBox);
        this.kdtAssist.getColumn("generalAssActType3").setEditor(kdtAssist_generalAssActType3_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType3_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType3_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType3").setRenderer(kdtAssist_generalAssActType3_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType4_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType4_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType4_PromptBox.setVisible(true);
        kdtAssist_generalAssActType4_PromptBox.setEditable(true);
        kdtAssist_generalAssActType4_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType4_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType4_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType4_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType4_PromptBox);
        this.kdtAssist.getColumn("generalAssActType4").setEditor(kdtAssist_generalAssActType4_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType4_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType4_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType4").setRenderer(kdtAssist_generalAssActType4_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType5_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType5_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType5_PromptBox.setVisible(true);
        kdtAssist_generalAssActType5_PromptBox.setEditable(true);
        kdtAssist_generalAssActType5_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType5_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType5_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType5_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType5_PromptBox);
        this.kdtAssist.getColumn("generalAssActType5").setEditor(kdtAssist_generalAssActType5_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType5_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType5_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType5").setRenderer(kdtAssist_generalAssActType5_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType6_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType6_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType6_PromptBox.setVisible(true);
        kdtAssist_generalAssActType6_PromptBox.setEditable(true);
        kdtAssist_generalAssActType6_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType6_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType6_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType6_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType6_PromptBox);
        this.kdtAssist.getColumn("generalAssActType6").setEditor(kdtAssist_generalAssActType6_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType6_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType6_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType6").setRenderer(kdtAssist_generalAssActType6_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType7_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType7_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType7_PromptBox.setVisible(true);
        kdtAssist_generalAssActType7_PromptBox.setEditable(true);
        kdtAssist_generalAssActType7_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType7_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType7_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType7_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType7_PromptBox);
        this.kdtAssist.getColumn("generalAssActType7").setEditor(kdtAssist_generalAssActType7_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType7_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType7_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType7").setRenderer(kdtAssist_generalAssActType7_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType8_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType8_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType8_PromptBox.setVisible(true);
        kdtAssist_generalAssActType8_PromptBox.setEditable(true);
        kdtAssist_generalAssActType8_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType8_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType8_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType8_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType8_PromptBox);
        this.kdtAssist.getColumn("generalAssActType8").setEditor(kdtAssist_generalAssActType8_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType8_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType8_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType8").setRenderer(kdtAssist_generalAssActType8_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType9_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType9_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType9_PromptBox.setVisible(true);
        kdtAssist_generalAssActType9_PromptBox.setEditable(true);
        kdtAssist_generalAssActType9_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType9_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType9_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType9_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType9_PromptBox);
        this.kdtAssist.getColumn("generalAssActType9").setEditor(kdtAssist_generalAssActType9_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType9_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType9_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType9").setRenderer(kdtAssist_generalAssActType9_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType10_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType10_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType10_PromptBox.setVisible(true);
        kdtAssist_generalAssActType10_PromptBox.setEditable(true);
        kdtAssist_generalAssActType10_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType10_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType10_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType10_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType10_PromptBox);
        this.kdtAssist.getColumn("generalAssActType10").setEditor(kdtAssist_generalAssActType10_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType10_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType10_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType10").setRenderer(kdtAssist_generalAssActType10_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType11_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType11_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType11_PromptBox.setVisible(true);
        kdtAssist_generalAssActType11_PromptBox.setEditable(true);
        kdtAssist_generalAssActType11_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType11_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType11_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType11_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType11_PromptBox);
        this.kdtAssist.getColumn("generalAssActType11").setEditor(kdtAssist_generalAssActType11_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType11_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType11_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType11").setRenderer(kdtAssist_generalAssActType11_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType12_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType12_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType12_PromptBox.setVisible(true);
        kdtAssist_generalAssActType12_PromptBox.setEditable(true);
        kdtAssist_generalAssActType12_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType12_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType12_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType12_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType12_PromptBox);
        this.kdtAssist.getColumn("generalAssActType12").setEditor(kdtAssist_generalAssActType12_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType12_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType12_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType12").setRenderer(kdtAssist_generalAssActType12_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType13_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType13_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType13_PromptBox.setVisible(true);
        kdtAssist_generalAssActType13_PromptBox.setEditable(true);
        kdtAssist_generalAssActType13_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType13_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType13_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType13_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType13_PromptBox);
        this.kdtAssist.getColumn("generalAssActType13").setEditor(kdtAssist_generalAssActType13_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType13_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType13_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType13").setRenderer(kdtAssist_generalAssActType13_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType14_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType14_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType14_PromptBox.setVisible(true);
        kdtAssist_generalAssActType14_PromptBox.setEditable(true);
        kdtAssist_generalAssActType14_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType14_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType14_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType14_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType14_PromptBox);
        this.kdtAssist.getColumn("generalAssActType14").setEditor(kdtAssist_generalAssActType14_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType14_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType14_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType14").setRenderer(kdtAssist_generalAssActType14_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType15_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType15_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType15_PromptBox.setVisible(true);
        kdtAssist_generalAssActType15_PromptBox.setEditable(true);
        kdtAssist_generalAssActType15_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType15_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType15_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType15_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType15_PromptBox);
        this.kdtAssist.getColumn("generalAssActType15").setEditor(kdtAssist_generalAssActType15_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType15_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType15_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType15").setRenderer(kdtAssist_generalAssActType15_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType16_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType16_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType16_PromptBox.setVisible(true);
        kdtAssist_generalAssActType16_PromptBox.setEditable(true);
        kdtAssist_generalAssActType16_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType16_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType16_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType16_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType16_PromptBox);
        this.kdtAssist.getColumn("generalAssActType16").setEditor(kdtAssist_generalAssActType16_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType16_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType16_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType16").setRenderer(kdtAssist_generalAssActType16_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType17_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType17_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType17_PromptBox.setVisible(true);
        kdtAssist_generalAssActType17_PromptBox.setEditable(true);
        kdtAssist_generalAssActType17_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType17_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType17_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType17_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType17_PromptBox);
        this.kdtAssist.getColumn("generalAssActType17").setEditor(kdtAssist_generalAssActType17_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType17_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType17_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType17").setRenderer(kdtAssist_generalAssActType17_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType18_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType18_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType18_PromptBox.setVisible(true);
        kdtAssist_generalAssActType18_PromptBox.setEditable(true);
        kdtAssist_generalAssActType18_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType18_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType18_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType18_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType18_PromptBox);
        this.kdtAssist.getColumn("generalAssActType18").setEditor(kdtAssist_generalAssActType18_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType18_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType18_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType18").setRenderer(kdtAssist_generalAssActType18_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType19_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType19_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType19_PromptBox.setVisible(true);
        kdtAssist_generalAssActType19_PromptBox.setEditable(true);
        kdtAssist_generalAssActType19_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType19_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType19_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType19_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType19_PromptBox);
        this.kdtAssist.getColumn("generalAssActType19").setEditor(kdtAssist_generalAssActType19_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType19_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType19_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType19").setRenderer(kdtAssist_generalAssActType19_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType20_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType20_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType20_PromptBox.setVisible(true);
        kdtAssist_generalAssActType20_PromptBox.setEditable(true);
        kdtAssist_generalAssActType20_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType20_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType20_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType20_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType20_PromptBox);
        this.kdtAssist.getColumn("generalAssActType20").setEditor(kdtAssist_generalAssActType20_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType20_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType20_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType20").setRenderer(kdtAssist_generalAssActType20_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType21_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType21_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType21_PromptBox.setVisible(true);
        kdtAssist_generalAssActType21_PromptBox.setEditable(true);
        kdtAssist_generalAssActType21_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType21_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType21_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType21_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType21_PromptBox);
        this.kdtAssist.getColumn("generalAssActType21").setEditor(kdtAssist_generalAssActType21_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType21_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType21_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType21").setRenderer(kdtAssist_generalAssActType21_OVR);
        final KDBizPromptBox kdtAssist_generalAssActType22_PromptBox = new KDBizPromptBox();
        kdtAssist_generalAssActType22_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.auxacct.app.F7GeneralAsstActTypeQuery");
        kdtAssist_generalAssActType22_PromptBox.setVisible(true);
        kdtAssist_generalAssActType22_PromptBox.setEditable(true);
        kdtAssist_generalAssActType22_PromptBox.setDisplayFormat("$number$");
        kdtAssist_generalAssActType22_PromptBox.setEditFormat("$number$");
        kdtAssist_generalAssActType22_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtAssist_generalAssActType22_CellEditor = new KDTDefaultCellEditor(kdtAssist_generalAssActType22_PromptBox);
        this.kdtAssist.getColumn("generalAssActType22").setEditor(kdtAssist_generalAssActType22_CellEditor);
        ObjectValueRender kdtAssist_generalAssActType22_OVR = new ObjectValueRender();
        kdtAssist_generalAssActType22_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtAssist.getColumn("generalAssActType22").setRenderer(kdtAssist_generalAssActType22_OVR);
        // kdtCashflow
		String kdtCashflowStrXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DocRoot xmlns:c=\"http://www.kingdee.com/Common\" xmlns:f=\"http://www.kingdee.com/Form\" xmlns:t=\"http://www.kingdee.com/Table\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.kingdee.com/KDF KDFSchema.xsd\" version=\"0.0\"><Styles><c:Style id=\"sCol0\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol8\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol10\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style><c:Style id=\"sCol11\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol12\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol13\"><c:NumberFormat>&amp;double</c:NumberFormat></c:Style><c:Style id=\"sCol16\"><c:NumberFormat>&amp;int</c:NumberFormat></c:Style></Styles><Table id=\"KDTable\"><t:Sheet name=\"sheet1\"><t:Table t:selectMode=\"15\" t:mergeMode=\"0\" t:dataRequestMode=\"0\" t:pageRowCount=\"100\"><t:ColumnGroup><t:Column t:key=\"seq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol0\" /><t:Column t:key=\"account\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"currency\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"entryDC\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"oppAccount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"oppEntryDC\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"itemFlag\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"primaryItem\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"primaryCoefficient\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol8\" /><t:Column t:key=\"supplementaryItem\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"supplementaryCoefficient\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol10\" /><t:Column t:key=\"originalAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol11\" /><t:Column t:key=\"localAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol12\" /><t:Column t:key=\"reportingAmount\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol13\" /><t:Column t:key=\"isSupItem\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"type\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" /><t:Column t:key=\"oppAccountSeq\" t:width=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\" t:moveable=\"true\" t:group=\"false\" t:required=\"false\" t:index=\"-1\" t:styleID=\"sCol16\" /></t:ColumnGroup><t:Head><t:Row t:name=\"header\" t:height=\"-1\" t:mergeable=\"true\" t:resizeable=\"true\"><t:Cell>$Resource{seq}</t:Cell><t:Cell>$Resource{account}</t:Cell><t:Cell>$Resource{currency}</t:Cell><t:Cell>$Resource{entryDC}</t:Cell><t:Cell>$Resource{oppAccount}</t:Cell><t:Cell>$Resource{oppEntryDC}</t:Cell><t:Cell>$Resource{itemFlag}</t:Cell><t:Cell>$Resource{primaryItem}</t:Cell><t:Cell>$Resource{primaryCoefficient}</t:Cell><t:Cell>$Resource{supplementaryItem}</t:Cell><t:Cell>$Resource{supplementaryCoefficient}</t:Cell><t:Cell>$Resource{originalAmount}</t:Cell><t:Cell>$Resource{localAmount}</t:Cell><t:Cell>$Resource{reportingAmount}</t:Cell><t:Cell>$Resource{isSupItem}</t:Cell><t:Cell>$Resource{type}</t:Cell><t:Cell>$Resource{oppAccountSeq}</t:Cell></t:Row></t:Head></t:Table><t:SheetOptions><t:MergeBlocks><t:Head /></t:MergeBlocks></t:SheetOptions></t:Sheet></Table></DocRoot>";
		
        this.kdtCashflow.setFormatXml(resHelper.translateString("kdtCashflow",kdtCashflowStrXML));

                this.kdtCashflow.putBindContents("editData",new String[] {"Cashflow.seq","Cashflow.account","Cashflow.currency","Cashflow.entryDC","Cashflow.oppAccount","Cashflow.oppEntryDC","Cashflow.itemFlag","Cashflow.primaryItem","Cashflow.primaryCoefficient","Cashflow.supplementaryItem","Cashflow.supplementaryCoefficient","Cashflow.originalAmount","Cashflow.localAmount","Cashflow.reportingAmount","Cashflow.isSupItem","Cashflow.type","Cashflow.oppAccountSeq"});


        this.kdtCashflow.checkParsed();
        KDFormattedTextField kdtCashflow_seq_TextField = new KDFormattedTextField();
        kdtCashflow_seq_TextField.setName("kdtCashflow_seq_TextField");
        kdtCashflow_seq_TextField.setVisible(true);
        kdtCashflow_seq_TextField.setEditable(true);
        kdtCashflow_seq_TextField.setHorizontalAlignment(2);
        kdtCashflow_seq_TextField.setDataType(0);
        KDTDefaultCellEditor kdtCashflow_seq_CellEditor = new KDTDefaultCellEditor(kdtCashflow_seq_TextField);
        this.kdtCashflow.getColumn("seq").setEditor(kdtCashflow_seq_CellEditor);
        final KDBizPromptBox kdtCashflow_account_PromptBox = new KDBizPromptBox();
        kdtCashflow_account_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.account.app.F7AccountViewQuery");
        kdtCashflow_account_PromptBox.setVisible(true);
        kdtCashflow_account_PromptBox.setEditable(true);
        kdtCashflow_account_PromptBox.setDisplayFormat("$number$");
        kdtCashflow_account_PromptBox.setEditFormat("$number$");
        kdtCashflow_account_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtCashflow_account_CellEditor = new KDTDefaultCellEditor(kdtCashflow_account_PromptBox);
        this.kdtCashflow.getColumn("account").setEditor(kdtCashflow_account_CellEditor);
        ObjectValueRender kdtCashflow_account_OVR = new ObjectValueRender();
        kdtCashflow_account_OVR.setFormat(new BizDataFormat("$longName$"));
        this.kdtCashflow.getColumn("account").setRenderer(kdtCashflow_account_OVR);
        final KDBizPromptBox kdtCashflow_currency_PromptBox = new KDBizPromptBox();
        kdtCashflow_currency_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.CurrencyQuery");
        kdtCashflow_currency_PromptBox.setVisible(true);
        kdtCashflow_currency_PromptBox.setEditable(true);
        kdtCashflow_currency_PromptBox.setDisplayFormat("$number$");
        kdtCashflow_currency_PromptBox.setEditFormat("$number$");
        kdtCashflow_currency_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtCashflow_currency_CellEditor = new KDTDefaultCellEditor(kdtCashflow_currency_PromptBox);
        this.kdtCashflow.getColumn("currency").setEditor(kdtCashflow_currency_CellEditor);
        ObjectValueRender kdtCashflow_currency_OVR = new ObjectValueRender();
        kdtCashflow_currency_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtCashflow.getColumn("currency").setRenderer(kdtCashflow_currency_OVR);
        KDComboBox kdtCashflow_entryDC_ComboBox = new KDComboBox();
        kdtCashflow_entryDC_ComboBox.setName("kdtCashflow_entryDC_ComboBox");
        kdtCashflow_entryDC_ComboBox.setVisible(true);
        kdtCashflow_entryDC_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.fi.gl.EntryDC").toArray());
        KDTDefaultCellEditor kdtCashflow_entryDC_CellEditor = new KDTDefaultCellEditor(kdtCashflow_entryDC_ComboBox);
        this.kdtCashflow.getColumn("entryDC").setEditor(kdtCashflow_entryDC_CellEditor);
        final KDBizPromptBox kdtCashflow_oppAccount_PromptBox = new KDBizPromptBox();
        kdtCashflow_oppAccount_PromptBox.setQueryInfo("com.kingdee.eas.basedata.master.account.app.F7AccountViewQuery");
        kdtCashflow_oppAccount_PromptBox.setVisible(true);
        kdtCashflow_oppAccount_PromptBox.setEditable(true);
        kdtCashflow_oppAccount_PromptBox.setDisplayFormat("$number$");
        kdtCashflow_oppAccount_PromptBox.setEditFormat("$number$");
        kdtCashflow_oppAccount_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtCashflow_oppAccount_CellEditor = new KDTDefaultCellEditor(kdtCashflow_oppAccount_PromptBox);
        this.kdtCashflow.getColumn("oppAccount").setEditor(kdtCashflow_oppAccount_CellEditor);
        ObjectValueRender kdtCashflow_oppAccount_OVR = new ObjectValueRender();
        kdtCashflow_oppAccount_OVR.setFormat(new BizDataFormat("$longName$"));
        this.kdtCashflow.getColumn("oppAccount").setRenderer(kdtCashflow_oppAccount_OVR);
        KDComboBox kdtCashflow_oppEntryDC_ComboBox = new KDComboBox();
        kdtCashflow_oppEntryDC_ComboBox.setName("kdtCashflow_oppEntryDC_ComboBox");
        kdtCashflow_oppEntryDC_ComboBox.setVisible(true);
        kdtCashflow_oppEntryDC_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.fi.gl.EntryDC").toArray());
        KDTDefaultCellEditor kdtCashflow_oppEntryDC_CellEditor = new KDTDefaultCellEditor(kdtCashflow_oppEntryDC_ComboBox);
        this.kdtCashflow.getColumn("oppEntryDC").setEditor(kdtCashflow_oppEntryDC_CellEditor);
        KDComboBox kdtCashflow_itemFlag_ComboBox = new KDComboBox();
        kdtCashflow_itemFlag_ComboBox.setName("kdtCashflow_itemFlag_ComboBox");
        kdtCashflow_itemFlag_ComboBox.setVisible(true);
        kdtCashflow_itemFlag_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.fi.gl.ItemFlag").toArray());
        KDTDefaultCellEditor kdtCashflow_itemFlag_CellEditor = new KDTDefaultCellEditor(kdtCashflow_itemFlag_ComboBox);
        this.kdtCashflow.getColumn("itemFlag").setEditor(kdtCashflow_itemFlag_CellEditor);
        final KDBizPromptBox kdtCashflow_primaryItem_PromptBox = new KDBizPromptBox();
        kdtCashflow_primaryItem_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7CashFlowQuery");
        kdtCashflow_primaryItem_PromptBox.setVisible(true);
        kdtCashflow_primaryItem_PromptBox.setEditable(true);
        kdtCashflow_primaryItem_PromptBox.setDisplayFormat("$number$");
        kdtCashflow_primaryItem_PromptBox.setEditFormat("$number$");
        kdtCashflow_primaryItem_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtCashflow_primaryItem_CellEditor = new KDTDefaultCellEditor(kdtCashflow_primaryItem_PromptBox);
        this.kdtCashflow.getColumn("primaryItem").setEditor(kdtCashflow_primaryItem_CellEditor);
        ObjectValueRender kdtCashflow_primaryItem_OVR = new ObjectValueRender();
        kdtCashflow_primaryItem_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtCashflow.getColumn("primaryItem").setRenderer(kdtCashflow_primaryItem_OVR);
        KDFormattedTextField kdtCashflow_primaryCoefficient_TextField = new KDFormattedTextField();
        kdtCashflow_primaryCoefficient_TextField.setName("kdtCashflow_primaryCoefficient_TextField");
        kdtCashflow_primaryCoefficient_TextField.setVisible(true);
        kdtCashflow_primaryCoefficient_TextField.setEditable(true);
        kdtCashflow_primaryCoefficient_TextField.setHorizontalAlignment(2);
        kdtCashflow_primaryCoefficient_TextField.setDataType(0);
        KDTDefaultCellEditor kdtCashflow_primaryCoefficient_CellEditor = new KDTDefaultCellEditor(kdtCashflow_primaryCoefficient_TextField);
        this.kdtCashflow.getColumn("primaryCoefficient").setEditor(kdtCashflow_primaryCoefficient_CellEditor);
        final KDBizPromptBox kdtCashflow_supplementaryItem_PromptBox = new KDBizPromptBox();
        kdtCashflow_supplementaryItem_PromptBox.setQueryInfo("com.kingdee.eas.basedata.assistant.app.F7CashFlowQuery");
        kdtCashflow_supplementaryItem_PromptBox.setVisible(true);
        kdtCashflow_supplementaryItem_PromptBox.setEditable(true);
        kdtCashflow_supplementaryItem_PromptBox.setDisplayFormat("$number$");
        kdtCashflow_supplementaryItem_PromptBox.setEditFormat("$number$");
        kdtCashflow_supplementaryItem_PromptBox.setCommitFormat("$number$");
        KDTDefaultCellEditor kdtCashflow_supplementaryItem_CellEditor = new KDTDefaultCellEditor(kdtCashflow_supplementaryItem_PromptBox);
        this.kdtCashflow.getColumn("supplementaryItem").setEditor(kdtCashflow_supplementaryItem_CellEditor);
        ObjectValueRender kdtCashflow_supplementaryItem_OVR = new ObjectValueRender();
        kdtCashflow_supplementaryItem_OVR.setFormat(new BizDataFormat("$name$"));
        this.kdtCashflow.getColumn("supplementaryItem").setRenderer(kdtCashflow_supplementaryItem_OVR);
        KDFormattedTextField kdtCashflow_supplementaryCoefficient_TextField = new KDFormattedTextField();
        kdtCashflow_supplementaryCoefficient_TextField.setName("kdtCashflow_supplementaryCoefficient_TextField");
        kdtCashflow_supplementaryCoefficient_TextField.setVisible(true);
        kdtCashflow_supplementaryCoefficient_TextField.setEditable(true);
        kdtCashflow_supplementaryCoefficient_TextField.setHorizontalAlignment(2);
        kdtCashflow_supplementaryCoefficient_TextField.setDataType(0);
        KDTDefaultCellEditor kdtCashflow_supplementaryCoefficient_CellEditor = new KDTDefaultCellEditor(kdtCashflow_supplementaryCoefficient_TextField);
        this.kdtCashflow.getColumn("supplementaryCoefficient").setEditor(kdtCashflow_supplementaryCoefficient_CellEditor);
        KDFormattedTextField kdtCashflow_originalAmount_TextField = new KDFormattedTextField();
        kdtCashflow_originalAmount_TextField.setName("kdtCashflow_originalAmount_TextField");
        kdtCashflow_originalAmount_TextField.setVisible(true);
        kdtCashflow_originalAmount_TextField.setEditable(true);
        kdtCashflow_originalAmount_TextField.setHorizontalAlignment(2);
        kdtCashflow_originalAmount_TextField.setDataType(1);
        	kdtCashflow_originalAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtCashflow_originalAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtCashflow_originalAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtCashflow_originalAmount_CellEditor = new KDTDefaultCellEditor(kdtCashflow_originalAmount_TextField);
        this.kdtCashflow.getColumn("originalAmount").setEditor(kdtCashflow_originalAmount_CellEditor);
        KDFormattedTextField kdtCashflow_localAmount_TextField = new KDFormattedTextField();
        kdtCashflow_localAmount_TextField.setName("kdtCashflow_localAmount_TextField");
        kdtCashflow_localAmount_TextField.setVisible(true);
        kdtCashflow_localAmount_TextField.setEditable(true);
        kdtCashflow_localAmount_TextField.setHorizontalAlignment(2);
        kdtCashflow_localAmount_TextField.setDataType(1);
        	kdtCashflow_localAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtCashflow_localAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtCashflow_localAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtCashflow_localAmount_CellEditor = new KDTDefaultCellEditor(kdtCashflow_localAmount_TextField);
        this.kdtCashflow.getColumn("localAmount").setEditor(kdtCashflow_localAmount_CellEditor);
        KDFormattedTextField kdtCashflow_reportingAmount_TextField = new KDFormattedTextField();
        kdtCashflow_reportingAmount_TextField.setName("kdtCashflow_reportingAmount_TextField");
        kdtCashflow_reportingAmount_TextField.setVisible(true);
        kdtCashflow_reportingAmount_TextField.setEditable(true);
        kdtCashflow_reportingAmount_TextField.setHorizontalAlignment(2);
        kdtCashflow_reportingAmount_TextField.setDataType(1);
        	kdtCashflow_reportingAmount_TextField.setMinimumValue(new java.math.BigDecimal("-1.0E18"));
        	kdtCashflow_reportingAmount_TextField.setMaximumValue(new java.math.BigDecimal("1.0E18"));
        kdtCashflow_reportingAmount_TextField.setPrecision(10);
        KDTDefaultCellEditor kdtCashflow_reportingAmount_CellEditor = new KDTDefaultCellEditor(kdtCashflow_reportingAmount_TextField);
        this.kdtCashflow.getColumn("reportingAmount").setEditor(kdtCashflow_reportingAmount_CellEditor);
        KDCheckBox kdtCashflow_isSupItem_CheckBox = new KDCheckBox();
        kdtCashflow_isSupItem_CheckBox.setName("kdtCashflow_isSupItem_CheckBox");
        KDTDefaultCellEditor kdtCashflow_isSupItem_CellEditor = new KDTDefaultCellEditor(kdtCashflow_isSupItem_CheckBox);
        this.kdtCashflow.getColumn("isSupItem").setEditor(kdtCashflow_isSupItem_CellEditor);
        KDComboBox kdtCashflow_type_ComboBox = new KDComboBox();
        kdtCashflow_type_ComboBox.setName("kdtCashflow_type_ComboBox");
        kdtCashflow_type_ComboBox.setVisible(true);
        kdtCashflow_type_ComboBox.addItems(EnumUtils.getEnumList("com.kingdee.eas.fi.gl.CashflowTypeEnum").toArray());
        KDTDefaultCellEditor kdtCashflow_type_CellEditor = new KDTDefaultCellEditor(kdtCashflow_type_ComboBox);
        this.kdtCashflow.getColumn("type").setEditor(kdtCashflow_type_CellEditor);
        KDFormattedTextField kdtCashflow_oppAccountSeq_TextField = new KDFormattedTextField();
        kdtCashflow_oppAccountSeq_TextField.setName("kdtCashflow_oppAccountSeq_TextField");
        kdtCashflow_oppAccountSeq_TextField.setVisible(true);
        kdtCashflow_oppAccountSeq_TextField.setEditable(true);
        kdtCashflow_oppAccountSeq_TextField.setHorizontalAlignment(2);
        kdtCashflow_oppAccountSeq_TextField.setDataType(0);
        KDTDefaultCellEditor kdtCashflow_oppAccountSeq_CellEditor = new KDTDefaultCellEditor(kdtCashflow_oppAccountSeq_TextField);
        this.kdtCashflow.getColumn("oppAccountSeq").setEditor(kdtCashflow_oppAccountSeq_CellEditor);
        // status		
        this.status.setVisible(true);		
        this.status.addItems(EnumUtils.getEnumList("com.kingdee.eas.hr.compensation.InterfaceBillEnum").toArray());		
        this.status.setRequired(false);
        this.setFocusTraversalPolicy(new com.kingdee.bos.ui.UIFocusTraversalPolicy(new java.awt.Component[] {kdtAssist,prmtFICompany,prmtperiod,pkbookedDate,chkisReverseVoucher,kdtCashflow,txtvoucherNumber,txtresultDesc,prmtcurrency,prmtvoucherType,txtNumber,pkBizDate,txtDescription,kDDateCreateTime,kDDateLastUpdateTime,billStatus,txtattaches,kdtEntrys,prmtcreator,chkFivouchered,status}));
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
        this.setBounds(new Rectangle(0, 0, 995, 629));
        this.setLayout(new KDLayout());
        this.putClientProperty("OriginalBounds", new Rectangle(0, 0, 995, 629));
        contCreateTime.setBounds(new Rectangle(335, 586, 270, 19));
        this.add(contCreateTime, new KDLayout.Constraints(335, 586, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contLastUpdateTime.setBounds(new Rectangle(660, 586, 270, 19));
        this.add(contLastUpdateTime, new KDLayout.Constraints(660, 586, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contNumber.setBounds(new Rectangle(10, 34, 270, 19));
        this.add(contNumber, new KDLayout.Constraints(10, 34, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contBizDate.setBounds(new Rectangle(660, 34, 270, 19));
        this.add(contBizDate, new KDLayout.Constraints(660, 34, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contDescription.setBounds(new Rectangle(660, 82, 270, 19));
        this.add(contDescription, new KDLayout.Constraints(660, 82, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contFICompany.setBounds(new Rectangle(10, 10, 270, 19));
        this.add(contFICompany, new KDLayout.Constraints(10, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contperiod.setBounds(new Rectangle(335, 10, 270, 19));
        this.add(contperiod, new KDLayout.Constraints(335, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contbookedDate.setBounds(new Rectangle(660, 10, 270, 19));
        this.add(contbookedDate, new KDLayout.Constraints(660, 10, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        chkisReverseVoucher.setBounds(new Rectangle(335, 34, 270, 19));
        this.add(chkisReverseVoucher, new KDLayout.Constraints(335, 34, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contvoucherNumber.setBounds(new Rectangle(335, 58, 270, 19));
        this.add(contvoucherNumber, new KDLayout.Constraints(335, 58, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contresultDesc.setBounds(new Rectangle(10, 58, 270, 19));
        this.add(contresultDesc, new KDLayout.Constraints(10, 58, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contcurrency.setBounds(new Rectangle(660, 58, 270, 19));
        this.add(contcurrency, new KDLayout.Constraints(660, 58, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contvoucherType.setBounds(new Rectangle(10, 82, 270, 19));
        this.add(contvoucherType, new KDLayout.Constraints(10, 82, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contbillStatus.setBounds(new Rectangle(660, 106, 270, 19));
        this.add(contbillStatus, new KDLayout.Constraints(660, 106, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT));
        contattaches.setBounds(new Rectangle(10, 106, 270, 19));
        this.add(contattaches, new KDLayout.Constraints(10, 106, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kdtEntrys.setBounds(new Rectangle(10, 130, 940, 274));
        kdtEntrys_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtEntrys,new com.kingdee.eas.custom.llwebservice.VoucherBillEntryInfo(),null,false);
        this.add(kdtEntrys_detailPanel, new KDLayout.Constraints(10, 130, 940, 274, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_BOTTOM_SCALE | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
		kdtEntrys_detailPanel.addAddListener(new com.kingdee.eas.framework.client.multiDetail.IDetailPanelListener() {
			public void beforeEvent(com.kingdee.eas.framework.client.multiDetail.DetailPanelEvent event) throws Exception {
				IObjectValue vo = event.getObjectValue();
vo.put("entryDC",new Integer(1));
			}
			public void afterEvent(com.kingdee.eas.framework.client.multiDetail.DetailPanelEvent event) throws Exception {
			}
		});
        chkFivouchered.setBounds(new Rectangle(335, 106, 270, 19));
        this.add(chkFivouchered, new KDLayout.Constraints(335, 106, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        contcreator.setBounds(new Rectangle(10, 586, 270, 19));
        this.add(contcreator, new KDLayout.Constraints(10, 586, 270, 19, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        kDTabbedPane1.setBounds(new Rectangle(10, 407, 944, 162));
        this.add(kDTabbedPane1, new KDLayout.Constraints(10, 407, 944, 162, KDLayout.Constraints.ANCHOR_BOTTOM | KDLayout.Constraints.ANCHOR_LEFT | KDLayout.Constraints.ANCHOR_RIGHT));
        contstatus.setBounds(new Rectangle(333, 81, 270, 19));
        this.add(contstatus, new KDLayout.Constraints(333, 81, 270, 19, KDLayout.Constraints.ANCHOR_TOP | KDLayout.Constraints.ANCHOR_LEFT_SCALE | KDLayout.Constraints.ANCHOR_RIGHT_SCALE));
        //contCreateTime
        contCreateTime.setBoundEditor(kDDateCreateTime);
        //contLastUpdateTime
        contLastUpdateTime.setBoundEditor(kDDateLastUpdateTime);
        //contNumber
        contNumber.setBoundEditor(txtNumber);
        //contBizDate
        contBizDate.setBoundEditor(pkBizDate);
        //contDescription
        contDescription.setBoundEditor(txtDescription);
        //contFICompany
        contFICompany.setBoundEditor(prmtFICompany);
        //contperiod
        contperiod.setBoundEditor(prmtperiod);
        //contbookedDate
        contbookedDate.setBoundEditor(pkbookedDate);
        //contvoucherNumber
        contvoucherNumber.setBoundEditor(txtvoucherNumber);
        //contresultDesc
        contresultDesc.setBoundEditor(txtresultDesc);
        //contcurrency
        contcurrency.setBoundEditor(prmtcurrency);
        //contvoucherType
        contvoucherType.setBoundEditor(prmtvoucherType);
        //contbillStatus
        contbillStatus.setBoundEditor(billStatus);
        //contattaches
        contattaches.setBoundEditor(txtattaches);
        //contcreator
        contcreator.setBoundEditor(prmtcreator);
        //kDTabbedPane1
        kDTabbedPane1.add(kDPanel1, resHelper.getString("kDPanel1.constraints"));
        kDTabbedPane1.add(kDPanel2, resHelper.getString("kDPanel2.constraints"));
        //kDPanel1
        kDPanel1.setLayout(null);        kdtAssist.setBounds(new Rectangle(-1, 0, 940, 128));
        kdtAssist_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtAssist,new com.kingdee.eas.custom.llwebservice.VoucherBillEntryAssistInfo(),null,false);
        kDPanel1.add(kdtAssist_detailPanel, null);
        //kDPanel2
        kDPanel2.setLayout(null);        kdtCashflow.setBounds(new Rectangle(0, 0, 938, 132));
        kdtCashflow_detailPanel = (com.kingdee.eas.framework.client.multiDetail.DetailPanel)com.kingdee.eas.framework.client.multiDetail.HMDUtils.buildDetail(this,dataBinder,kdtCashflow,new com.kingdee.eas.custom.llwebservice.VoucherBillEntryCashflowInfo(),null,false);
        kDPanel2.add(kdtCashflow_detailPanel, null);
		kdtCashflow_detailPanel.addAddListener(new com.kingdee.eas.framework.client.multiDetail.IDetailPanelListener() {
			public void beforeEvent(com.kingdee.eas.framework.client.multiDetail.DetailPanelEvent event) throws Exception {
				IObjectValue vo = event.getObjectValue();
vo.put("entryDC",new Integer(1));
vo.put("oppEntryDC",new Integer(1));
vo.put("itemFlag",new Integer(1));
vo.put("type",new Integer(1));
			}
			public void afterEvent(com.kingdee.eas.framework.client.multiDetail.DetailPanelEvent event) throws Exception {
			}
		});
        //contstatus
        contstatus.setBoundEditor(status);

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
        this.menuBar.add(menuTable1);
        this.menuBar.add(menuTool);
        this.menuBar.add(menuWorkflow);
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
        menuFile.add(kDSeparator6);
        menuFile.add(menuItemSendMail);
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
        menuEdit.add(separator1);
        menuEdit.add(menuItemCreateFrom);
        menuEdit.add(menuItemCreateTo);
        menuEdit.add(menuItemCopyFrom);
        menuEdit.add(separatorEdit1);
        menuEdit.add(menuItemEnterToNextRow);
        menuEdit.add(separator2);
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
        menuView.add(separator3);
        menuView.add(menuItemTraceUp);
        menuView.add(menuItemTraceDown);
        menuView.add(kDSeparator7);
        menuView.add(menuItemLocate);
        //menuBiz
        menuBiz.add(menuItemCancelCancel);
        menuBiz.add(menuItemCancel);
        menuBiz.add(MenuItemVoucher);
        menuBiz.add(menuItemDelVoucher);
        menuBiz.add(MenuItemPCVoucher);
        menuBiz.add(menuItemDelPCVoucher);
        //menuTable1
        menuTable1.add(menuItemAddLine);
        menuTable1.add(menuItemCopyLine);
        menuTable1.add(menuItemInsertLine);
        menuTable1.add(menuItemRemoveLine);
        //menuTool
        menuTool.add(menuItemSendMessage);
        menuTool.add(menuItemMsgFormat);
        menuTool.add(menuItemCalculator);
        menuTool.add(menuItemToolBarCustom);
        //menuWorkflow
        menuWorkflow.add(menuItemStartWorkFlow);
        menuWorkflow.add(separatorWF1);
        menuWorkflow.add(menuItemViewSubmitProccess);
        menuWorkflow.add(menuItemViewDoProccess);
        menuWorkflow.add(MenuItemWFG);
        menuWorkflow.add(menuItemWorkFlowList);
        menuWorkflow.add(separatorWF2);
        menuWorkflow.add(menuItemMultiapprove);
        menuWorkflow.add(menuItemNextPerson);
        menuWorkflow.add(menuItemAuditResult);
        menuWorkflow.add(kDSeparator5);
        menuWorkflow.add(kDMenuItemSendMessage);
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
        this.toolBar.add(btnSave);
        this.toolBar.add(kDSeparatorCloud);
        this.toolBar.add(btnReset);
        this.toolBar.add(btnSubmit);
        this.toolBar.add(btnCopy);
        this.toolBar.add(btnRemove);
        this.toolBar.add(btnCancelCancel);
        this.toolBar.add(btnCancel);
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
        this.toolBar.add(btnTraceUp);
        this.toolBar.add(btnTraceDown);
        this.toolBar.add(btnWorkFlowG);
        this.toolBar.add(btnSignature);
        this.toolBar.add(btnViewSignature);
        this.toolBar.add(separatorFW4);
        this.toolBar.add(btnNumberSign);
        this.toolBar.add(separatorFW7);
        this.toolBar.add(btnCreateFrom);
        this.toolBar.add(btnCopyFrom);
        this.toolBar.add(btnCreateTo);
        this.toolBar.add(separatorFW5);
        this.toolBar.add(separatorFW8);
        this.toolBar.add(btnAddLine);
        this.toolBar.add(btnCopyLine);
        this.toolBar.add(btnInsertLine);
        this.toolBar.add(btnRemoveLine);
        this.toolBar.add(separatorFW6);
        this.toolBar.add(separatorFW9);
        this.toolBar.add(btnVoucher);
        this.toolBar.add(btnDelVoucher);
        this.toolBar.add(btnPCVoucher);
        this.toolBar.add(btnDelPCVoucher);
        this.toolBar.add(btnAuditResult);
        this.toolBar.add(btnMultiapprove);
        this.toolBar.add(btnWFViewdoProccess);
        this.toolBar.add(btnWFViewSubmitProccess);
        this.toolBar.add(btnNextPerson);


    }

	//Regiester control's property binding.
	private void registerBindings(){
		dataBinder.registerBinding("isReverseVoucher", boolean.class, this.chkisReverseVoucher, "selected");
		dataBinder.registerBinding("entrys.id", com.kingdee.bos.util.BOSUuid.class, this.kdtEntrys, "id.text");
		dataBinder.registerBinding("entrys", com.kingdee.eas.custom.llwebservice.VoucherBillEntryInfo.class, this.kdtEntrys, "userObject");
		dataBinder.registerBinding("entrys.description", String.class, this.kdtEntrys, "description.text");
		dataBinder.registerBinding("entrys.account", java.lang.Object.class, this.kdtEntrys, "account.text");
		dataBinder.registerBinding("entrys.amount", java.math.BigDecimal.class, this.kdtEntrys, "amount.text");
		dataBinder.registerBinding("entrys.currency", java.lang.Object.class, this.kdtEntrys, "currency.text");
		dataBinder.registerBinding("entrys.localRate", java.math.BigDecimal.class, this.kdtEntrys, "localRate.text");
		dataBinder.registerBinding("entrys.qty", java.math.BigDecimal.class, this.kdtEntrys, "qty.text");
		dataBinder.registerBinding("entrys.measureUnit", java.lang.Object.class, this.kdtEntrys, "measureUnit.text");
		dataBinder.registerBinding("entrys.price", java.math.BigDecimal.class, this.kdtEntrys, "price.text");
		dataBinder.registerBinding("entrys.entryDC", com.kingdee.util.enums.Enum.class, this.kdtEntrys, "entryDC.text");
		dataBinder.registerBinding("Fivouchered", boolean.class, this.chkFivouchered, "selected");
		dataBinder.registerBinding("createTime", java.sql.Timestamp.class, this.kDDateCreateTime, "value");
		dataBinder.registerBinding("lastUpdateTime", java.sql.Timestamp.class, this.kDDateLastUpdateTime, "value");
		dataBinder.registerBinding("number", String.class, this.txtNumber, "text");
		dataBinder.registerBinding("bizDate", java.util.Date.class, this.pkBizDate, "value");
		dataBinder.registerBinding("description", String.class, this.txtDescription, "text");
		dataBinder.registerBinding("FICompany", com.kingdee.eas.basedata.org.CompanyOrgUnitInfo.class, this.prmtFICompany, "data");
		dataBinder.registerBinding("period", com.kingdee.eas.basedata.assistant.PeriodInfo.class, this.prmtperiod, "data");
		dataBinder.registerBinding("bookedDate", java.util.Date.class, this.pkbookedDate, "value");
		dataBinder.registerBinding("voucherNumber", String.class, this.txtvoucherNumber, "text");
		dataBinder.registerBinding("resultDesc", String.class, this.txtresultDesc, "text");
		dataBinder.registerBinding("currency", com.kingdee.eas.basedata.assistant.CurrencyInfo.class, this.prmtcurrency, "data");
		dataBinder.registerBinding("voucherType", com.kingdee.eas.basedata.assistant.VoucherTypeInfo.class, this.prmtvoucherType, "data");
		dataBinder.registerBinding("billStatus", com.kingdee.eas.eai.EaiBillStatusEnum.class, this.billStatus, "selectedItem");
		dataBinder.registerBinding("attaches", int.class, this.txtattaches, "value");
		dataBinder.registerBinding("creator", com.kingdee.eas.base.permission.UserInfo.class, this.prmtcreator, "data");
		dataBinder.registerBinding("entrys.Assist.seq", int.class, this.kdtAssist, "seq.text");
		dataBinder.registerBinding("entrys.Assist", com.kingdee.eas.custom.llwebservice.VoucherBillEntryAssistInfo.class, this.kdtAssist, "userObject");
		dataBinder.registerBinding("entrys.Assist.bizNumber", String.class, this.kdtAssist, "bizNumber.text");
		dataBinder.registerBinding("entrys.Assist.settlementCode", String.class, this.kdtAssist, "settlementCode.text");
		dataBinder.registerBinding("entrys.Assist.bizDate", java.util.Date.class, this.kdtAssist, "bizDate.text");
		dataBinder.registerBinding("entrys.Assist.amount", java.math.BigDecimal.class, this.kdtAssist, "amount.text");
		dataBinder.registerBinding("entrys.Assist.qty", java.math.BigDecimal.class, this.kdtAssist, "qty.text");
		dataBinder.registerBinding("entrys.Assist.price", java.math.BigDecimal.class, this.kdtAssist, "price.text");
		dataBinder.registerBinding("entrys.Assist.customer", java.lang.Object.class, this.kdtAssist, "customer.text");
		dataBinder.registerBinding("entrys.Assist.supplier", java.lang.Object.class, this.kdtAssist, "supplier.text");
		dataBinder.registerBinding("entrys.Assist.measureUnit", java.lang.Object.class, this.kdtAssist, "measureUnit.text");
		dataBinder.registerBinding("entrys.Assist.region", java.lang.Object.class, this.kdtAssist, "region.text");
		dataBinder.registerBinding("entrys.Assist.companyOrg", java.lang.Object.class, this.kdtAssist, "companyOrg.text");
		dataBinder.registerBinding("entrys.Assist.adminOrg", java.lang.Object.class, this.kdtAssist, "adminOrg.text");
		dataBinder.registerBinding("entrys.Assist.costOrg", java.lang.Object.class, this.kdtAssist, "costOrg.text");
		dataBinder.registerBinding("entrys.Assist.material", java.lang.Object.class, this.kdtAssist, "material.text");
		dataBinder.registerBinding("entrys.Assist.settlementType", java.lang.Object.class, this.kdtAssist, "settlementType.text");
		dataBinder.registerBinding("entrys.Assist.description", String.class, this.kdtAssist, "description.text");
		dataBinder.registerBinding("entrys.Assist.person", java.lang.Object.class, this.kdtAssist, "person.text");
		dataBinder.registerBinding("entrys.Assist.bankAccount", java.lang.Object.class, this.kdtAssist, "bankAccount.text");
		dataBinder.registerBinding("entrys.Assist.innerAccount", java.lang.Object.class, this.kdtAssist, "innerAccount.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType1", java.lang.Object.class, this.kdtAssist, "generalAssActType1.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType2", java.lang.Object.class, this.kdtAssist, "generalAssActType2.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType3", java.lang.Object.class, this.kdtAssist, "generalAssActType3.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType4", java.lang.Object.class, this.kdtAssist, "generalAssActType4.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType5", java.lang.Object.class, this.kdtAssist, "generalAssActType5.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType6", java.lang.Object.class, this.kdtAssist, "generalAssActType6.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType8", java.lang.Object.class, this.kdtAssist, "generalAssActType8.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType7", java.lang.Object.class, this.kdtAssist, "generalAssActType7.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType9", java.lang.Object.class, this.kdtAssist, "generalAssActType9.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType10", java.lang.Object.class, this.kdtAssist, "generalAssActType10.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType11", java.lang.Object.class, this.kdtAssist, "generalAssActType11.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType12", java.lang.Object.class, this.kdtAssist, "generalAssActType12.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType13", java.lang.Object.class, this.kdtAssist, "generalAssActType13.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType14", java.lang.Object.class, this.kdtAssist, "generalAssActType14.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType15", java.lang.Object.class, this.kdtAssist, "generalAssActType15.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType16", java.lang.Object.class, this.kdtAssist, "generalAssActType16.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType17", java.lang.Object.class, this.kdtAssist, "generalAssActType17.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType18", java.lang.Object.class, this.kdtAssist, "generalAssActType18.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType19", java.lang.Object.class, this.kdtAssist, "generalAssActType19.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType20", java.lang.Object.class, this.kdtAssist, "generalAssActType20.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType21", java.lang.Object.class, this.kdtAssist, "generalAssActType21.text");
		dataBinder.registerBinding("entrys.Assist.generalAssActType22", java.lang.Object.class, this.kdtAssist, "generalAssActType22.text");
		dataBinder.registerBinding("entrys.Cashflow.seq", int.class, this.kdtCashflow, "seq.text");
		dataBinder.registerBinding("entrys.Cashflow", com.kingdee.eas.custom.llwebservice.VoucherBillEntryCashflowInfo.class, this.kdtCashflow, "userObject");
		dataBinder.registerBinding("entrys.Cashflow.account", java.lang.Object.class, this.kdtCashflow, "account.text");
		dataBinder.registerBinding("entrys.Cashflow.currency", java.lang.Object.class, this.kdtCashflow, "currency.text");
		dataBinder.registerBinding("entrys.Cashflow.entryDC", com.kingdee.util.enums.Enum.class, this.kdtCashflow, "entryDC.text");
		dataBinder.registerBinding("entrys.Cashflow.oppAccount", java.lang.Object.class, this.kdtCashflow, "oppAccount.text");
		dataBinder.registerBinding("entrys.Cashflow.oppEntryDC", com.kingdee.util.enums.Enum.class, this.kdtCashflow, "oppEntryDC.text");
		dataBinder.registerBinding("entrys.Cashflow.itemFlag", com.kingdee.util.enums.Enum.class, this.kdtCashflow, "itemFlag.text");
		dataBinder.registerBinding("entrys.Cashflow.primaryItem", java.lang.Object.class, this.kdtCashflow, "primaryItem.text");
		dataBinder.registerBinding("entrys.Cashflow.primaryCoefficient", int.class, this.kdtCashflow, "primaryCoefficient.text");
		dataBinder.registerBinding("entrys.Cashflow.supplementaryItem", java.lang.Object.class, this.kdtCashflow, "supplementaryItem.text");
		dataBinder.registerBinding("entrys.Cashflow.supplementaryCoefficient", int.class, this.kdtCashflow, "supplementaryCoefficient.text");
		dataBinder.registerBinding("entrys.Cashflow.originalAmount", java.math.BigDecimal.class, this.kdtCashflow, "originalAmount.text");
		dataBinder.registerBinding("entrys.Cashflow.localAmount", java.math.BigDecimal.class, this.kdtCashflow, "localAmount.text");
		dataBinder.registerBinding("entrys.Cashflow.reportingAmount", java.math.BigDecimal.class, this.kdtCashflow, "reportingAmount.text");
		dataBinder.registerBinding("entrys.Cashflow.isSupItem", boolean.class, this.kdtCashflow, "isSupItem.text");
		dataBinder.registerBinding("entrys.Cashflow.type", com.kingdee.util.enums.Enum.class, this.kdtCashflow, "type.text");
		dataBinder.registerBinding("entrys.Cashflow.oppAccountSeq", int.class, this.kdtCashflow, "oppAccountSeq.text");
		dataBinder.registerBinding("status", com.kingdee.eas.hr.compensation.InterfaceBillEnum.class, this.status, "selectedItem");		
	}
	//Regiester UI State
	private void registerUIState(){		
	}
	public String getUIHandlerClassName() {
	    return "com.kingdee.eas.custom.llwebservice.app.VoucherBillEditUIHandler";
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
        this.kdtAssist.requestFocusInWindow();
    }

	
	

    /**
     * output setDataObject method
     */
    public void setDataObject(IObjectValue dataObject)
    {
        IObjectValue ov = dataObject;        	    	
        super.setDataObject(ov);
        this.editData = (com.kingdee.eas.custom.llwebservice.VoucherBillInfo)ov;
    }
    protected void removeByPK(IObjectPK pk) throws Exception {
    	IObjectValue editData = this.editData;
    	super.removeByPK(pk);
    	recycleNumberByOrg(editData,"Company",editData.getString("number"));
    }
    
    protected void recycleNumberByOrg(IObjectValue editData,String orgType,String number) {
        if (!StringUtils.isEmpty(number))
        {
            try {
            	String companyID = null;            
            	com.kingdee.eas.base.codingrule.ICodingRuleManager iCodingRuleManager = com.kingdee.eas.base.codingrule.CodingRuleManagerFactory.getRemoteInstance();
				if(!com.kingdee.util.StringUtils.isEmpty(orgType) && !"NONE".equalsIgnoreCase(orgType) && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType))!=null) {
					companyID =com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType)).getString("id");
				}
				else if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit() != null) {
					companyID = ((com.kingdee.eas.basedata.org.OrgUnitInfo)com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit()).getString("id");
            	}				
				if (!StringUtils.isEmpty(companyID) && iCodingRuleManager.isExist(editData, companyID) && iCodingRuleManager.isUseIntermitNumber(editData, companyID)) {
					iCodingRuleManager.recycleNumber(editData,companyID,number);					
				}
            }
            catch (Exception e)
            {
                handUIException(e);
            }
        }
    }
    protected void setAutoNumberByOrg(String orgType) {
    	if (editData == null) return;
		if (editData.getNumber() == null) {
            try {
            	String companyID = null;
				if(!com.kingdee.util.StringUtils.isEmpty(orgType) && !"NONE".equalsIgnoreCase(orgType) && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType))!=null) {
					companyID = com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum(orgType)).getString("id");
				}
				else if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit() != null) {
					companyID = ((com.kingdee.eas.basedata.org.OrgUnitInfo)com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit()).getString("id");
            	}
				com.kingdee.eas.base.codingrule.ICodingRuleManager iCodingRuleManager = com.kingdee.eas.base.codingrule.CodingRuleManagerFactory.getRemoteInstance();
		        if (iCodingRuleManager.isExist(editData, companyID)) {
		            if (iCodingRuleManager.isAddView(editData, companyID)) {
		            	editData.setNumber(iCodingRuleManager.getNumber(editData,companyID));
		            }
	                txtNumber.setEnabled(false);
		        }
            }
            catch (Exception e) {
                handUIException(e);
                this.oldData = editData;
                com.kingdee.eas.util.SysUtil.abort();
            } 
        } 
        else {
            if (editData.getNumber().trim().length() > 0) {
                txtNumber.setText(editData.getNumber());
            }
        }
    }
			protected com.kingdee.eas.basedata.org.OrgType getMainBizOrgType() {
			return com.kingdee.eas.basedata.org.OrgType.getEnum("Company");
		}

	protected KDBizPromptBox getMainBizOrg() {
		return prmtFICompany;
}


    /**
     * output loadFields method
     */
    public void loadFields()
    {
        		setAutoNumberByOrg("Company");
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
		getValidateHelper().registerBindProperty("isReverseVoucher", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.id", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.account", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.amount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.currency", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.localRate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.qty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.measureUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.price", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.entryDC", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("Fivouchered", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("createTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("lastUpdateTime", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("number", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bizDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("FICompany", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("period", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("bookedDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("voucherNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("resultDesc", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("currency", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("voucherType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("billStatus", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("attaches", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("creator", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.bizNumber", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.settlementCode", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.bizDate", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.amount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.qty", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.price", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.customer", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.supplier", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.measureUnit", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.region", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.companyOrg", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.adminOrg", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.costOrg", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.material", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.settlementType", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.description", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.person", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.bankAccount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.innerAccount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType1", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType2", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType3", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType4", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType5", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType6", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType8", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType7", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType9", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType10", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType11", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType12", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType13", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType14", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType15", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType16", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType17", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType18", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType19", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType20", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType21", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Assist.generalAssActType22", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.seq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.account", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.currency", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.entryDC", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.oppAccount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.oppEntryDC", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.itemFlag", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.primaryItem", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.primaryCoefficient", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.supplementaryItem", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.supplementaryCoefficient", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.originalAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.localAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.reportingAmount", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.isSupItem", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.type", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("entrys.Cashflow.oppAccountSeq", ValidateHelper.ON_SAVE);    
		getValidateHelper().registerBindProperty("status", ValidateHelper.ON_SAVE);    		
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
        } else if (STATUS_FINDVIEW.equals(this.oprtState)) {
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
        sic.add(new SelectorItemInfo("isReverseVoucher"));
    	sic.add(new SelectorItemInfo("entrys.id"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.*"));
		}
		else{
		}
    	sic.add(new SelectorItemInfo("entrys.description"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.account.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.account.id"));
			sic.add(new SelectorItemInfo("entrys.account.name"));
        	sic.add(new SelectorItemInfo("entrys.account.number"));
		}
    	sic.add(new SelectorItemInfo("entrys.amount"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.currency.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.currency.id"));
			sic.add(new SelectorItemInfo("entrys.currency.name"));
        	sic.add(new SelectorItemInfo("entrys.currency.number"));
		}
    	sic.add(new SelectorItemInfo("entrys.localRate"));
    	sic.add(new SelectorItemInfo("entrys.qty"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.measureUnit.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.measureUnit.id"));
			sic.add(new SelectorItemInfo("entrys.measureUnit.name"));
        	sic.add(new SelectorItemInfo("entrys.measureUnit.number"));
		}
    	sic.add(new SelectorItemInfo("entrys.price"));
    	sic.add(new SelectorItemInfo("entrys.entryDC"));
        sic.add(new SelectorItemInfo("Fivouchered"));
        sic.add(new SelectorItemInfo("createTime"));
        sic.add(new SelectorItemInfo("lastUpdateTime"));
        sic.add(new SelectorItemInfo("number"));
        sic.add(new SelectorItemInfo("bizDate"));
        sic.add(new SelectorItemInfo("description"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("FICompany.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("FICompany.id"));
        	sic.add(new SelectorItemInfo("FICompany.number"));
        	sic.add(new SelectorItemInfo("FICompany.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("period.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("period.id"));
        	sic.add(new SelectorItemInfo("period.number"));
        	sic.add(new SelectorItemInfo("period.periodNumber"));
		}
        sic.add(new SelectorItemInfo("bookedDate"));
        sic.add(new SelectorItemInfo("voucherNumber"));
        sic.add(new SelectorItemInfo("resultDesc"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("currency.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("currency.id"));
        	sic.add(new SelectorItemInfo("currency.number"));
        	sic.add(new SelectorItemInfo("currency.name"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("voucherType.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("voucherType.id"));
        	sic.add(new SelectorItemInfo("voucherType.number"));
        	sic.add(new SelectorItemInfo("voucherType.name"));
		}
        sic.add(new SelectorItemInfo("billStatus"));
        sic.add(new SelectorItemInfo("attaches"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("creator.*"));
		}
		else{
        	sic.add(new SelectorItemInfo("creator.id"));
        	sic.add(new SelectorItemInfo("creator.number"));
        	sic.add(new SelectorItemInfo("creator.name"));
		}
    	sic.add(new SelectorItemInfo("entrys.Assist.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.id"));
		}
    	sic.add(new SelectorItemInfo("entrys.Assist.bizNumber"));
    	sic.add(new SelectorItemInfo("entrys.Assist.settlementCode"));
    	sic.add(new SelectorItemInfo("entrys.Assist.bizDate"));
    	sic.add(new SelectorItemInfo("entrys.Assist.amount"));
    	sic.add(new SelectorItemInfo("entrys.Assist.qty"));
    	sic.add(new SelectorItemInfo("entrys.Assist.price"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.customer.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.customer.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.customer.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.customer.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.supplier.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.supplier.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.supplier.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.supplier.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.measureUnit.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.measureUnit.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.measureUnit.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.measureUnit.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.region.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.region.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.region.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.region.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.companyOrg.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.companyOrg.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.companyOrg.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.companyOrg.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.adminOrg.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.adminOrg.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.adminOrg.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.adminOrg.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.costOrg.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.costOrg.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.costOrg.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.costOrg.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.material.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.material.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.material.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.material.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.settlementType.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.settlementType.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.settlementType.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.settlementType.number"));
		}
    	sic.add(new SelectorItemInfo("entrys.Assist.description"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.person.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.person.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.person.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.person.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.bankAccount.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.bankAccount.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.bankAccount.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.bankAccount.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.innerAccount.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.innerAccount.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.innerAccount.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.innerAccount.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType1.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType1.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType1.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType1.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType2.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType2.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType2.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType2.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType3.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType3.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType3.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType3.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType4.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType4.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType4.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType4.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType5.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType5.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType5.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType5.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType6.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType6.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType6.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType6.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType8.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType8.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType8.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType8.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType7.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType7.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType7.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType7.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType9.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType9.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType9.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType9.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType10.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType10.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType10.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType10.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType11.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType11.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType11.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType11.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType12.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType12.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType12.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType12.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType13.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType13.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType13.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType13.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType14.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType14.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType14.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType14.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType15.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType15.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType15.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType15.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType16.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType16.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType16.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType16.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType17.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType17.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType17.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType17.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType18.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType18.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType18.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType18.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType19.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType19.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType19.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType19.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType20.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType20.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType20.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType20.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType21.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType21.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType21.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType21.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType22.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType22.id"));
			sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType22.name"));
        	sic.add(new SelectorItemInfo("entrys.Assist.generalAssActType22.number"));
		}
    	sic.add(new SelectorItemInfo("entrys.Cashflow.seq"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Cashflow.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Cashflow.id"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Cashflow.account.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Cashflow.account.id"));
			sic.add(new SelectorItemInfo("entrys.Cashflow.account.longName"));
			sic.add(new SelectorItemInfo("entrys.Cashflow.account.name"));
        	sic.add(new SelectorItemInfo("entrys.Cashflow.account.number"));
		}
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Cashflow.currency.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Cashflow.currency.id"));
			sic.add(new SelectorItemInfo("entrys.Cashflow.currency.name"));
        	sic.add(new SelectorItemInfo("entrys.Cashflow.currency.number"));
		}
    	sic.add(new SelectorItemInfo("entrys.Cashflow.entryDC"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Cashflow.oppAccount.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Cashflow.oppAccount.id"));
			sic.add(new SelectorItemInfo("entrys.Cashflow.oppAccount.longName"));
			sic.add(new SelectorItemInfo("entrys.Cashflow.oppAccount.name"));
        	sic.add(new SelectorItemInfo("entrys.Cashflow.oppAccount.number"));
		}
    	sic.add(new SelectorItemInfo("entrys.Cashflow.oppEntryDC"));
    	sic.add(new SelectorItemInfo("entrys.Cashflow.itemFlag"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Cashflow.primaryItem.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Cashflow.primaryItem.id"));
			sic.add(new SelectorItemInfo("entrys.Cashflow.primaryItem.name"));
        	sic.add(new SelectorItemInfo("entrys.Cashflow.primaryItem.number"));
		}
    	sic.add(new SelectorItemInfo("entrys.Cashflow.primaryCoefficient"));
		if(selectorAll.equalsIgnoreCase("true"))
		{
			sic.add(new SelectorItemInfo("entrys.Cashflow.supplementaryItem.*"));
		}
		else{
	    	sic.add(new SelectorItemInfo("entrys.Cashflow.supplementaryItem.id"));
			sic.add(new SelectorItemInfo("entrys.Cashflow.supplementaryItem.name"));
        	sic.add(new SelectorItemInfo("entrys.Cashflow.supplementaryItem.number"));
		}
    	sic.add(new SelectorItemInfo("entrys.Cashflow.supplementaryCoefficient"));
    	sic.add(new SelectorItemInfo("entrys.Cashflow.originalAmount"));
    	sic.add(new SelectorItemInfo("entrys.Cashflow.localAmount"));
    	sic.add(new SelectorItemInfo("entrys.Cashflow.reportingAmount"));
    	sic.add(new SelectorItemInfo("entrys.Cashflow.isSupItem"));
    	sic.add(new SelectorItemInfo("entrys.Cashflow.type"));
    	sic.add(new SelectorItemInfo("entrys.Cashflow.oppAccountSeq"));
        sic.add(new SelectorItemInfo("status"));
        return sic;
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

    /**
     * output getMetaDataPK method
     */
    public IMetaDataPK getMetaDataPK()
    {
        return new MetaDataPK("com.kingdee.eas.custom.llwebservice.client", "VoucherBillEditUI");
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
        return com.kingdee.eas.custom.llwebservice.client.VoucherBillEditUI.class.getName();
    }

    /**
     * output getBizInterface method
     */
    protected com.kingdee.eas.framework.ICoreBase getBizInterface() throws Exception
    {
        return com.kingdee.eas.custom.llwebservice.VoucherBillFactory.getRemoteInstance();
    }

    /**
     * output createNewData method
     */
    protected IObjectValue createNewData()
    {
        com.kingdee.eas.custom.llwebservice.VoucherBillInfo objectValue = new com.kingdee.eas.custom.llwebservice.VoucherBillInfo();
				if (com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")) != null && com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")).getBoolean("isBizUnit"))
			objectValue.put("FICompany",com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentOrgUnit(com.kingdee.eas.basedata.org.OrgType.getEnum("Company")));
 
        objectValue.setCreator((com.kingdee.eas.base.permission.UserInfo)(com.kingdee.eas.common.client.SysContext.getSysContext().getCurrentUser()));		
        return objectValue;
    }


    	protected String getTDFileName() {
    	return "/bim/custom/llwebservice/VoucherBill";
	}
    protected IMetaDataPK getTDQueryPK() {
    	return new MetaDataPK("com.kingdee.eas.custom.llwebservice.app.VoucherBillQuery");
	}
    

    /**
     * output getDetailTable method
     */
    protected KDTable getDetailTable() {
        return kdtEntrys;
	}
    /**
     * output applyDefaultValue method
     */
    protected void applyDefaultValue(IObjectValue vo) {        
		vo.put("status","1");
        
    }        
	protected void setFieldsNull(com.kingdee.bos.dao.AbstractObjectValue arg0) {
		super.setFieldsNull(arg0);
		arg0.put("number",null);
	}

}