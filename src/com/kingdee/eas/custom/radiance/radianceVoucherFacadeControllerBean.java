package com.kingdee.eas.custom.radiance;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.bos.*;
import com.kingdee.bos.metadata.entity.EntityViewInfo;
import com.kingdee.bos.metadata.entity.FilterInfo;
import com.kingdee.bos.metadata.entity.FilterItemInfo;
import com.kingdee.bos.metadata.query.util.CompareType;
import com.kingdee.bos.util.BOSUuid;
import com.kingdee.eas.base.permission.UserCollection;
import com.kingdee.eas.base.permission.UserFactory;
import com.kingdee.eas.base.permission.UserInfo;
import com.kingdee.eas.basedata.assistant.AccountBankCollection;
import com.kingdee.eas.basedata.assistant.AccountBankFactory;
import com.kingdee.eas.basedata.assistant.AccountBankInfo;
import com.kingdee.eas.basedata.assistant.BankCollection;
import com.kingdee.eas.basedata.assistant.BankFactory;
import com.kingdee.eas.basedata.assistant.BankInfo;
import com.kingdee.eas.basedata.assistant.CashFlowItemCollection;
import com.kingdee.eas.basedata.assistant.CashFlowItemFactory;
import com.kingdee.eas.basedata.assistant.CashFlowItemInfo;
import com.kingdee.eas.basedata.assistant.CurrencyCollection;
import com.kingdee.eas.basedata.assistant.CurrencyFactory;
import com.kingdee.eas.basedata.assistant.CurrencyInfo;
import com.kingdee.eas.basedata.assistant.MeasureUnitCollection;
import com.kingdee.eas.basedata.assistant.MeasureUnitFactory;
import com.kingdee.eas.basedata.assistant.MeasureUnitInfo;
import com.kingdee.eas.basedata.assistant.PeriodCollection;
import com.kingdee.eas.basedata.assistant.PeriodFactory;
import com.kingdee.eas.basedata.assistant.PeriodInfo;
import com.kingdee.eas.basedata.assistant.SettlementTypeCollection;
import com.kingdee.eas.basedata.assistant.SettlementTypeFactory;
import com.kingdee.eas.basedata.assistant.SettlementTypeInfo;
import com.kingdee.eas.basedata.assistant.VoucherTypeCollection;
import com.kingdee.eas.basedata.assistant.VoucherTypeFactory;
import com.kingdee.eas.basedata.assistant.VoucherTypeInfo;
import com.kingdee.eas.basedata.master.account.AccountViewCollection;
import com.kingdee.eas.basedata.master.account.AccountViewFactory;
import com.kingdee.eas.basedata.master.account.AccountViewInfo;
import com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeCollection;
import com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeFactory;
import com.kingdee.eas.basedata.master.auxacct.GeneralAsstActTypeInfo;
import com.kingdee.eas.basedata.master.cssp.CustomerCollection;
import com.kingdee.eas.basedata.master.cssp.CustomerFactory;
import com.kingdee.eas.basedata.master.cssp.CustomerInfo;
import com.kingdee.eas.basedata.master.cssp.SupplierCollection;
import com.kingdee.eas.basedata.master.cssp.SupplierFactory;
import com.kingdee.eas.basedata.master.cssp.SupplierInfo;
import com.kingdee.eas.basedata.master.material.MaterialCollection;
import com.kingdee.eas.basedata.master.material.MaterialFactory;
import com.kingdee.eas.basedata.master.material.MaterialInfo;
import com.kingdee.eas.basedata.org.AdminOrgUnitCollection;
import com.kingdee.eas.basedata.org.AdminOrgUnitFactory;
import com.kingdee.eas.basedata.org.AdminOrgUnitInfo;
import com.kingdee.eas.basedata.org.CompanyOrgUnitCollection;
import com.kingdee.eas.basedata.org.CompanyOrgUnitFactory;
import com.kingdee.eas.basedata.org.CompanyOrgUnitInfo;
import com.kingdee.eas.basedata.org.CostCenterOrgUnitCollection;
import com.kingdee.eas.basedata.org.CostCenterOrgUnitFactory;
import com.kingdee.eas.basedata.org.CostCenterOrgUnitInfo;
import com.kingdee.eas.basedata.org.CtrlUnitCollection;
import com.kingdee.eas.basedata.org.CtrlUnitFactory;
import com.kingdee.eas.basedata.org.CtrlUnitInfo;
import com.kingdee.eas.basedata.person.PersonCollection;
import com.kingdee.eas.basedata.person.PersonFactory;
import com.kingdee.eas.basedata.person.PersonInfo;
import com.kingdee.eas.common.EASBizException;
import com.kingdee.eas.custom.llwebservice.CallVoucherLogFactory;
import com.kingdee.eas.custom.llwebservice.CallVoucherLogInfo;
import com.kingdee.eas.custom.llwebservice.VoucherBillEntryAssistInfo;
import com.kingdee.eas.custom.llwebservice.VoucherBillEntryCashflowInfo;
import com.kingdee.eas.custom.llwebservice.VoucherBillEntryInfo;
import com.kingdee.eas.custom.llwebservice.VoucherBillFactory;
import com.kingdee.eas.custom.llwebservice.VoucherBillInfo;
import com.kingdee.eas.eai.EaiBillStatusEnum;
import com.kingdee.eas.eai.EaiResultEnum;
import com.kingdee.eas.fi.gl.CashflowTypeEnum;
import com.kingdee.eas.fi.gl.EntryDC;
import com.kingdee.eas.fi.gl.ItemFlag;
import com.kingdee.eas.fm.fs.InnerAccountCollection;
import com.kingdee.eas.fm.fs.InnerAccountFactory;
import com.kingdee.eas.fm.fs.InnerAccountInfo;
import com.kingdee.eas.hr.compensation.InterfaceBillEnum;
import com.kingdee.eas.util.app.DbUtil;
import com.kingdee.jdbc.rowset.IRowSet;

import java.lang.String;
import java.math.BigDecimal;

public class radianceVoucherFacadeControllerBean extends AbstractradianceVoucherFacadeControllerBean
{
    private static Logger logger =
        Logger.getLogger("com.kingdee.eas.custom.radiance.radianceVoucherFacadeControllerBean");

	@Override
	protected String _getVoucherData(Context ctx, String data) {
		if (!data.isEmpty() || data.length() != 0) {
			CallVoucherLogInfo logInfo;
			try {
				JSONObject voucherObject = JSONObject.parseObject(data);
				logInfo = getLogInfo(ctx, voucherObject, data);
				try {
					System.out.println("����try");
					VoucherBillInfo billInfo = getBillInfo(ctx, voucherObject);
					System.out.println("billInfo==============+");
					getBillEntryInfoCol(ctx, voucherObject, billInfo);
					System.out.println("status========================+"+billInfo.getStatus());
					VoucherBillFactory.getLocalInstance(ctx).addnew(billInfo);
					updateStatus(ctx, billInfo);
					logInfo.setResult(EaiResultEnum.success);
					logInfo.setResultDesc("ͬ���ɹ�");
					//������־
					CallVoucherLogFactory.getLocalInstance(ctx).addnew(logInfo);
					return "success";
				} catch (EASBizException e) {
					logInfo.setResult(EaiResultEnum.fail);
					logInfo.setResultDesc(e.getMessage());
					System.out.println("EASBizException==============+");
					try {
						CallVoucherLogFactory.getLocalInstance(ctx).addnew(logInfo);
					} catch (EASBizException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
					return e.getMessage();
				} catch (Exception e) {
					logInfo.setResult(EaiResultEnum.fail);
					System.out.println("e.getMessage()"+e.getMessage());
					logInfo.setResultDesc(e.getMessage());
					System.out.println("Exception==============+");
					try {
						CallVoucherLogFactory.getLocalInstance(ctx).addnew(logInfo);
					} catch (EASBizException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
					return e.getMessage();
				}
			} catch (JSONException e2) {
				CallVoucherLogInfo logInfo2 = new CallVoucherLogInfo();
				Date newDate = new Date();
				logInfo2.setResult(EaiResultEnum.fail);
				logInfo2.setLastUpdateTime(newDate);
				//ҵ������
				logInfo2.setJson(data);
				//�Ƶ���
//				String creator = voucherObject.getString("creator");
//				logInfo.setCreator(getUserInfoF7(ctx, creator));
				//��������
				String msg = "json�ַ�����������";
				logInfo2.setDataType("����ƾ֤");
				logInfo2.setResultDesc(msg);
				try {
					CallVoucherLogFactory.getLocalInstance(ctx).addnew(logInfo2);
				} catch (EASBizException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BOSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return msg;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * ������־����
	 * @throws Exception 
	 */
	protected CallVoucherLogInfo getLogInfo(Context ctx, JSONObject voucherObject, String json) throws Exception {
		CallVoucherLogInfo logInfo = new CallVoucherLogInfo();
//		if (!json.isEmpty() || json.length() != 0) {
//			JSONObject voucherObject = JSONObject.parseObject(json);
			//ҵ�����ݱ���
			String number = voucherObject.getString("number");
			logInfo.setBizNumber(number);
			//����ʱ��
			Date newDate = new Date();
			logInfo.setLastUpdateTime(newDate);
			//ҵ������
			logInfo.setJson(json);
			//�Ƶ���
//			String creator = voucherObject.getString("creator");
//			logInfo.setCreator(getUserInfoF7(ctx, creator));
			//��������
			logInfo.setDataType("����ƾ֤");
//		}
		return logInfo;
	}
	/**
	 * ��ȡ����ͷ�ֶ�
	 * @throws Exception 
	 */
	protected VoucherBillInfo getBillInfo(Context ctx, JSONObject voucherObject) throws Exception {
		VoucherBillInfo billInfo = new VoucherBillInfo();
		//�Ƿ�����ƾ֤
//		int fivouchered = voucherObject.getIntValue("fivouchered");
//		
//		if (fivouchered == 0) {
//			billInfo.setFivouchered(false);
//		} else if (fivouchered == 1) {
//			billInfo.setFivouchered(true);
//		}
		String number = voucherObject.getString("number");
		//У�鵥�ݱ��
		checkNumber(ctx, number);
		//������֯
		String ficompany = voucherObject.getString("ficompany");
		billInfo.setFICompany(getCompanyOrgUnitInfoF7(ctx, ficompany, "1"));
		//����ڼ�
		String period = voucherObject.getString("period");
		billInfo.setPeriod(getPeriodInfoF7(ctx, period, "1"));
		//��������
		Date bookedDate = voucherObject.getDate("bookedDate");
		billInfo.setBookedDate(bookedDate);
		//�Ƿ���
		int isReverseVoucher = voucherObject.getIntValue("isReverseVoucher");
		if (isReverseVoucher == 0) {
			billInfo.setIsReverseVoucher(false);
		} else if (isReverseVoucher == 1) {
			billInfo.setIsReverseVoucher(true);
		}
		//EASƾ֤��
		String voucherNumber = voucherObject.getString("voucherNumber");
		billInfo.setVoucherNumber(voucherNumber);
		//ʧ��ԭ��
		String resultDesc = voucherObject.getString("resultDesc");
		billInfo.setResultDesc(resultDesc);
		//�ұ�
		String currency = voucherObject.getString("currency");
		billInfo.setCurrency(getCurrencyInfoF7(ctx, currency, "1"));
		//ƾ֤����
		String voucherType = voucherObject.getString("voucherType");
		billInfo.setVoucherType(getVoucherTypeInfoF7(ctx, voucherType));
		//����״̬
//		String billStatus = voucherObject.getString("status");
		billInfo.setStatus(InterfaceBillEnum.SUBMIT);
		//������
		int attaches = voucherObject.getIntValue("attaches");
		billInfo.setAttaches(attaches);
		//ƾ֤ID
		String voucherId = voucherObject.getString("voucherId");
		billInfo.setVoucherId(voucherId);
		//���
		billInfo.setNumber(number);
		//ҵ������
		Date bizDate = voucherObject.getDate("bizDate");
		billInfo.setBizDate(bizDate);
		//������
		String handler = voucherObject.getString("handler");
		billInfo.setHandler(getUserInfoF7(ctx, handler));
		//�ο���Ϣ
		String description = voucherObject.getString("description");
		billInfo.setDescription(description);
		//�Ƿ�������Ч
		int hasEffected = voucherObject.getIntValue("hasEffected");
		if (hasEffected == 0) {
			billInfo.setHasEffected(false);
		} else if (hasEffected == 1) {
			billInfo.setHasEffected(true);
		}
		//�����
		String auditor = voucherObject.getString("auditor");
		billInfo.setAuditor(getUserInfoF7(ctx, auditor));
		//ԭʼ����Id
		String sourceBillId = voucherObject.getString("sourceBillId");
		billInfo.setSourceBillId(sourceBillId);
		//��Դ����
		String sourceFunction = voucherObject.getString("sourceFunction");
		billInfo.setSourceFunction(sourceFunction);
		//������
		String creator = voucherObject.getString("creator");
		billInfo.setCreator(getUserInfoF7(ctx, creator));
		//����ʱ��
		Date createtime = voucherObject.getDate("createtime");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp createtimeStamp = Timestamp.valueOf(sdf.format(createtime));
		billInfo.setCreateTime(createtimeStamp);
		//����޸���
		String LastUpdateUser = voucherObject.getString("LastUpdateUser");
		billInfo.setLastUpdateUser(getUserInfoF7(ctx, LastUpdateUser));
		//����޸�ʱ��
		Date LastUpdateTime = voucherObject.getDate("LastUpdateTime");
		Timestamp updatetimeStamp = Timestamp.valueOf(sdf.format(LastUpdateTime));
		billInfo.setLastUpdateTime(updatetimeStamp);
		//���Ƶ�Ԫ
		String CU = voucherObject.getString("CU");
		billInfo.setCU(getCtrlUnitInfoF7(ctx, CU));
		
		return billInfo;
	}
	/**
	 * ������ϸ��¼
	 * @throws Exception 
	 */
	protected void getBillEntryInfoCol(Context ctx, JSONObject voucherObject, VoucherBillInfo billInfo) throws Exception {
		JSONArray itemizedEntityArray = voucherObject.getJSONArray("itemizedEntity");
		if (itemizedEntityArray.size() != 0) {
			for (int i = 0; i < itemizedEntityArray.size(); i++) {
				VoucherBillEntryInfo billEntryInfo = new VoucherBillEntryInfo();
				JSONObject itemizedEntityObject = itemizedEntityArray.getJSONObject(i);
				//���ݷ�¼���к�
				int seq = itemizedEntityObject.getIntValue("seq");
				billEntryInfo.setSeq(seq);
				//ժҪ
				String descriptionEntry = itemizedEntityObject.getString("description");
				billEntryInfo.setDescription(descriptionEntry);
				//��ƿ�Ŀ
				String account = itemizedEntityObject.getString("account");
				billEntryInfo.setAccount(getAccountViewInfoF7(ctx, account,"1", billInfo.getFICompany()));
				//���
				BigDecimal amount = itemizedEntityObject.getBigDecimal("amount");
				billEntryInfo.setAmount(amount);
				//�������
				String entryDC = itemizedEntityObject.getString("entryDC");
				if (entryDC != null) {
					if (entryDC.equals("1")) {
						billEntryInfo.setEntryDC(EntryDC.DEBIT);
					} else if (entryDC.equals("0")) {
						billEntryInfo.setEntryDC(EntryDC.CREDIT);
					}
				}
				//ԭ��
				String currencyEntry = itemizedEntityObject.getString("currency");
				billEntryInfo.setCurrency(getCurrencyInfoF7(ctx, currencyEntry, "1"));
				//���㱾λ�һ���
				BigDecimal localRate = itemizedEntityObject.getBigDecimal("localRate");
				billEntryInfo.setLocalRate(localRate);
				//����
				BigDecimal qty = itemizedEntityObject.getBigDecimal("qty");
				billEntryInfo.setQty(qty);
				//������λ
				String measureUnit = itemizedEntityObject.getString("measureUnit");
				billEntryInfo.setMeasureUnit(getMeasureUnitInfoF7(ctx, measureUnit, "1"));
				//����
				BigDecimal price = itemizedEntityObject.getBigDecimal("price");
				billEntryInfo.setPrice(price);
				getBillEntryAssCol(ctx, itemizedEntityObject, billEntryInfo);
				getBillEntryCashCol(ctx, itemizedEntityObject, billEntryInfo, billInfo);
				
				billEntryInfo.setParent(billInfo);
				billInfo.getEntrys().add(billEntryInfo);
				
			}
		}
	}
	/**
	 * ƾ֤��¼-������
	 * @throws Exception 
	 */
	protected void getBillEntryAssCol(Context ctx, JSONObject itemizedEntityObject, VoucherBillEntryInfo billEntryInfo) throws Exception {
		JSONArray assistEntryArray = itemizedEntityObject.getJSONArray("auxiliaryAccEntity");
		if (assistEntryArray.size() != 0) {
			for (int i = 0; i < assistEntryArray.size(); i++) {
				VoucherBillEntryAssistInfo billEntryAssistInfo = new VoucherBillEntryAssistInfo();
				JSONObject assistEntryObject = assistEntryArray.getJSONObject(i);
				//���ݷ�¼���к�
				int seq = assistEntryObject.getIntValue("seq");
				billEntryAssistInfo.setSeq(seq);
				//ժҪ
				String descriptionEntry = assistEntryObject.getString("description");
				billEntryAssistInfo.setDescription(descriptionEntry);
				//�����˻�
				String bankAccount = assistEntryObject.getString("bankAccount");
				billEntryAssistInfo.setBankAccount(getAccountBankInfoF7(ctx, bankAccount));
				//�ڲ��˻�
				String innerAccount = assistEntryObject.getString("innerAccount");
				billEntryAssistInfo.setInnerAccount(getInnerAccountInfoF7(ctx, innerAccount));
				//����
				String region = assistEntryObject.getString("region");
				billEntryAssistInfo.setRegion(getBankInfoF7(ctx, region));
				//ҵ�����
				String bizNumber = assistEntryObject.getString("bizNumber");
				billEntryAssistInfo.setBizNumber(bizNumber);
				//�����
				String settlementCode = assistEntryObject.getString("settlementCode");
				billEntryAssistInfo.setSettlementCode(settlementCode);
				//ҵ������
				Date bizDateEntry = assistEntryObject.getDate("bizDate");
				billEntryAssistInfo.setBizDate(bizDateEntry);
				//���
				BigDecimal amount = assistEntryObject.getBigDecimal("amount");
				billEntryAssistInfo.setAmount(amount);
				//����
				BigDecimal qty = assistEntryObject.getBigDecimal("qty");
				billEntryAssistInfo.setQty(qty);
				//����
				BigDecimal price = assistEntryObject.getBigDecimal("price");
				billEntryAssistInfo.setPrice(price);
				//�ͻ�
				String customer = assistEntryObject.getString("customer");
				billEntryAssistInfo.setCustomer(getCustomerInfoF7(ctx, customer));
				//��Ӧ��
				String supplier = assistEntryObject.getString("supplier");
				billEntryAssistInfo.setSupplier(getSupplierInfoF7(ctx, supplier));
				//������λ
				String measureUnit = assistEntryObject.getString("measureUnit");
				billEntryAssistInfo.setMeasureUnit(getMeasureUnitInfoF7(ctx, measureUnit, "2"));
				//��˾
				String companyOrg = assistEntryObject.getString("companyOrg");
				billEntryAssistInfo.setCompanyOrg(getCompanyOrgUnitInfoF7(ctx, companyOrg, "2"));
				//����
				String adminOrg = assistEntryObject.getString("adminOrg");
				billEntryAssistInfo.setAdminOrg(getAdminOrgUnitF7(ctx, adminOrg));
				//�ɱ�����
				String costOrg = assistEntryObject.getString("costOrg");
				billEntryAssistInfo.setCostOrg(getCostCenterOrgUnitInfoF7(ctx, costOrg));
				//����
				String material = assistEntryObject.getString("material");
				billEntryAssistInfo.setMaterial(getMaterialInfoF7(ctx, material));
				//���㷽ʽ
				String settlementType = assistEntryObject.getString("settlementType");
				billEntryAssistInfo.setSettlementType(getSettlementTypeInfoF7(ctx, settlementType));
				//ְԱ
				String person = assistEntryObject.getString("person");
				billEntryAssistInfo.setPerson(getPersonInfoForIdnumF7(ctx, person));
				//�Զ��������Ŀ1
				String generalAssActType1 = assistEntryObject.getString("generalAssActType1");
				billEntryAssistInfo.setGeneralAssActType1(getGeneralAsstActTypeInfoF7(ctx, generalAssActType1));
				//�Զ��������Ŀ2
				String generalAssActType2 = assistEntryObject.getString("generalAssActType2");
				billEntryAssistInfo.setGeneralAssActType2(getGeneralAsstActTypeInfoF7(ctx, generalAssActType2));
				//�Զ��������Ŀ3
				String generalAssActType3 = assistEntryObject.getString("generalAssActType3");
				billEntryAssistInfo.setGeneralAssActType3(getGeneralAsstActTypeInfoF7(ctx, generalAssActType3));
				//�Զ��������Ŀ4
				String generalAssActType4 = assistEntryObject.getString("generalAssActType4");
				billEntryAssistInfo.setGeneralAssActType4(getGeneralAsstActTypeInfoF7(ctx, generalAssActType4));
				//�Զ��������Ŀ5
				String generalAssActType5 = assistEntryObject.getString("generalAssActType5");
				billEntryAssistInfo.setGeneralAssActType5(getGeneralAsstActTypeInfoF7(ctx, generalAssActType5));
				//�Զ��������Ŀ6
				String generalAssActType6 = assistEntryObject.getString("generalAssActType6");
				billEntryAssistInfo.setGeneralAssActType6(getGeneralAsstActTypeInfoF7(ctx, generalAssActType6));
				//�Զ��������Ŀ7
				String generalAssActType7 = assistEntryObject.getString("generalAssActType7");
				billEntryAssistInfo.setGeneralAssActType7(getGeneralAsstActTypeInfoF7(ctx, generalAssActType7));
				//�Զ��������Ŀ8
				String generalAssActType8 = assistEntryObject.getString("generalAssActType8");
				billEntryAssistInfo.setGeneralAssActType8(getGeneralAsstActTypeInfoF7(ctx, generalAssActType8));
				//�Զ��������Ŀ9
				String generalAssActType9 = assistEntryObject.getString("generalAssActType9");
				billEntryAssistInfo.setGeneralAssActType9(getGeneralAsstActTypeInfoF7(ctx, generalAssActType9));
				//�Զ��������Ŀ10
				String generalAssActType10 = assistEntryObject.getString("generalAssActType10");
				billEntryAssistInfo.setGeneralAssActType10(getGeneralAsstActTypeInfoF7(ctx, generalAssActType10));
				//�Զ��������Ŀ11
				String generalAssActType11 = assistEntryObject.getString("generalAssActType11");
				billEntryAssistInfo.setGeneralAssActType11(getGeneralAsstActTypeInfoF7(ctx, generalAssActType11));
				//�Զ��������Ŀ12
				String generalAssActType12 = assistEntryObject.getString("generalAssActType12");
				billEntryAssistInfo.setGeneralAssActType12(getGeneralAsstActTypeInfoF7(ctx, generalAssActType12));
				//�Զ��������Ŀ13
				String generalAssActType13 = assistEntryObject.getString("generalAssActType13");
				billEntryAssistInfo.setGeneralAssActType13(getGeneralAsstActTypeInfoF7(ctx, generalAssActType13));
				//�Զ��������Ŀ14
				String generalAssActType14 = assistEntryObject.getString("generalAssActType14");
				billEntryAssistInfo.setGeneralAssActType14(getGeneralAsstActTypeInfoF7(ctx, generalAssActType14));
				//�Զ��������Ŀ15
				String generalAssActType15 = assistEntryObject.getString("generalAssActType15");
				billEntryAssistInfo.setGeneralAssActType15(getGeneralAsstActTypeInfoF7(ctx, generalAssActType15));
				//�Զ��������Ŀ16
				String generalAssActType16 = assistEntryObject.getString("generalAssActType16");
				billEntryAssistInfo.setGeneralAssActType16(getGeneralAsstActTypeInfoF7(ctx, generalAssActType16));
				//�Զ��������Ŀ17
				String generalAssActType17 = assistEntryObject.getString("generalAssActType17");
				billEntryAssistInfo.setGeneralAssActType17(getGeneralAsstActTypeInfoF7(ctx, generalAssActType17));
				//�Զ��������Ŀ18
				String generalAssActType18 = assistEntryObject.getString("generalAssActType18");
				billEntryAssistInfo.setGeneralAssActType18(getGeneralAsstActTypeInfoF7(ctx, generalAssActType18));
				//�Զ��������Ŀ19
				String generalAssActType19 = assistEntryObject.getString("generalAssActType19");
				billEntryAssistInfo.setGeneralAssActType19(getGeneralAsstActTypeInfoF7(ctx, generalAssActType19));
				//�Զ��������Ŀ20
				String generalAssActType20 = assistEntryObject.getString("generalAssActType20");
				billEntryAssistInfo.setGeneralAssActType20(getGeneralAsstActTypeInfoF7(ctx, generalAssActType20));
				//�Զ��������Ŀ21
				String generalAssActType21 = assistEntryObject.getString("generalAssActType21");
				billEntryAssistInfo.setGeneralAssActType21(getGeneralAsstActTypeInfoF7(ctx, generalAssActType21));
				//�Զ��������Ŀ22
				String generalAssActType22 = assistEntryObject.getString("generalAssActType22");
				billEntryAssistInfo.setGeneralAssActType22(getGeneralAsstActTypeInfoF7(ctx, generalAssActType22));
				
				billEntryAssistInfo.setParent1(billEntryInfo);
				billEntryInfo.getAssist().add(billEntryAssistInfo);
			}
		}
	}
	/**
	 * ƾ֤��¼-�ֽ�����
	 * @param billInfo 
	 * @throws Exception 
	 */
	protected void getBillEntryCashCol(Context ctx, JSONObject itemizedEntityObject, VoucherBillEntryInfo billEntryInfo, VoucherBillInfo billInfo) throws Exception {
//		List<VoucherBillEntryCashflowInfo> billEntryCashCol = new ArrayList<VoucherBillEntryCashflowInfo>();
//		VoucherBillEntryCashflowCollection voucherBillEntryCashflowCollection = new VoucherBillEntryCashflowCollection();
		JSONArray cashflowEntryArray = itemizedEntityObject.getJSONArray("moneyflowEntity");
		if (cashflowEntryArray.size() != 0) {
			for (int i = 0; i < cashflowEntryArray.size(); i++) {
				VoucherBillEntryCashflowInfo billEntryCashflowInfo = new VoucherBillEntryCashflowInfo();
				JSONObject cashflowEntryObject = cashflowEntryArray.getJSONObject(i);
				//���ݷ�¼���к�
				int seq = cashflowEntryObject.getIntValue("seq");
				billEntryCashflowInfo.setSeq(seq);
				//��Ŀ
				String account = cashflowEntryObject.getString("account");
				billEntryCashflowInfo.setAccount(getAccountViewInfoF7(ctx, account, "2", billInfo.getFICompany()));
				//�ұ�
				String currencyEntry = cashflowEntryObject.getString("currency");
				billEntryCashflowInfo.setCurrency(getCurrencyInfoF7(ctx, currencyEntry,"2"));
				//��¼����
				String entryDC = cashflowEntryObject.getString("entryDC");
				if (entryDC != null) {
					if (entryDC.equals("1")) {
						billEntryCashflowInfo.setEntryDC(EntryDC.DEBIT);
					} else if (entryDC.equals("0")) {
						billEntryCashflowInfo.setEntryDC(EntryDC.CREDIT);
					}
				}
				//�Է���Ŀ
				String oppAccount = cashflowEntryObject.getString("oppAccount");
				billEntryCashflowInfo.setOppAccount(getAccountViewInfoF7(ctx, oppAccount, "2", billInfo.getFICompany()));
				//�Է���¼����
				String oppEntryDC = cashflowEntryObject.getString("oppEntryDC");
				if (oppEntryDC != null) {
					if (oppEntryDC.equals("1")) {
						billEntryCashflowInfo.setOppEntryDC(EntryDC.DEBIT);
					} else if (oppEntryDC.equals("0")) {
						billEntryCashflowInfo.setOppEntryDC(EntryDC.CREDIT);
					}
				}
				//�ֽ�������Ŀ���
				String itemFlag = cashflowEntryObject.getString("itemFlag");
				billEntryCashflowInfo.setItemFlag(ItemFlag.getEnum(itemFlag));
				//������Ŀ
				String primaryItem = cashflowEntryObject.getString("primaryItem");
				billEntryCashflowInfo.setPrimaryItem(getCashFlowItemInfoF7(ctx, primaryItem));
				//������ϵ��
				int primaryCoefficient = cashflowEntryObject.getIntValue("primaryCoefficient");
				billEntryCashflowInfo.setPrimaryCoefficient(primaryCoefficient);
				//������Ŀ
				String supplementaryItem = cashflowEntryObject.getString("supplementaryItem");
				billEntryCashflowInfo.setPrimaryItem(getCashFlowItemInfoF7(ctx, supplementaryItem));
				//������ϵ��
				int supplementaryCoefficient = cashflowEntryObject.getIntValue("supplementaryCoefficient");
				billEntryCashflowInfo.setSupplementaryCoefficient(supplementaryCoefficient);
				//ԭ�ҽ��
				BigDecimal originalAmount = cashflowEntryObject.getBigDecimal("originalAmount");
				billEntryCashflowInfo.setOriginalAmount(originalAmount);
				//��λ�ҽ�� 
				BigDecimal localAmount = cashflowEntryObject.getBigDecimal("localAmount");
				billEntryCashflowInfo.setLocalAmount(localAmount);
				//����ҽ��
				BigDecimal reportingAmount = cashflowEntryObject.getBigDecimal("reportingAmount");
				billEntryCashflowInfo.setReportingAmount(reportingAmount);
				//�Ƿ񸽱���ָ��
				int isSupItem = cashflowEntryObject.getIntValue("isSupItem");
				if (isSupItem == 0) {
					billEntryCashflowInfo.setIsSupItem(false);
				} else if (isSupItem == 1) {
					billEntryCashflowInfo.setIsSupItem(true);
				}
				//����
				String type = cashflowEntryObject.getString("type");
				billEntryCashflowInfo.setType(CashflowTypeEnum.getEnum(type));
				//�Է���Ŀ��¼��
				int oppAccountSeq = cashflowEntryObject.getIntValue("oppAccountSeq");
				billEntryCashflowInfo.setOppAccountSeq(oppAccountSeq);
				
				billEntryCashflowInfo.setEntry(billEntryInfo);
				billEntryInfo.getCashflow().add(billEntryCashflowInfo);
			}
		}
	}
	protected EntityViewInfo getViewInfo(String number) {
		EntityViewInfo evi = new EntityViewInfo();
		FilterInfo filter = new FilterInfo();
		filter.getFilterItems().add(new FilterItemInfo("number", number, CompareType.EQUALS));
		evi.setFilter(filter);
		return evi;
	}
	protected AdminOrgUnitInfo getAdminOrgUnitF7(Context ctx, String number) throws Exception {
		AdminOrgUnitInfo adminOrgUnitInfo = new AdminOrgUnitInfo();
		if (number != null) {
			if (number.length() > 0) {
				try {
					AdminOrgUnitCollection adminOrgUnitCollection = AdminOrgUnitFactory.getLocalInstance(ctx).getAdminOrgUnitCollection(getViewInfo(number));
					if ((adminOrgUnitCollection  != null) && (adminOrgUnitCollection.size() > 0)) {
						adminOrgUnitInfo = adminOrgUnitCollection.get(0);
						
					} else {
						throw new Exception("δ�ҵ���Ӧ����֯��"+ number);
					}
				} catch (BOSException e) {
					e.printStackTrace();
				}
			}
		}
		return adminOrgUnitInfo;
	}
	/**
	 * У�鵥�ݱ��
	 * @throws Exception 
	 * 
	 */
	protected void checkNumber(Context ctx, String number) throws Exception {
		if (number == null) {
			throw new Exception("���ݱ�Ų���Ϊ�գ�");
		} else {
			if (number.length() < 0) {
				throw new Exception("���ݱ�Ų���Ϊ�գ�");
			}
			String sql = "select count(*) from CT_LL_VoucherBill where fnumber = '"+number+"'";
			try {
				IRowSet executeQuery = DbUtil.executeQuery(ctx, sql);
				int totalCount = getTotalCount(executeQuery);
				System.out.println("totalCount"+totalCount);
				if (totalCount > 0) {
					throw new Exception("EAS���Ѵ��ڵ��ݱ��Ϊ��"+ number +"��ƾ֤");
				}
			} catch (BOSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * ��˾F7	CompanyOrgUnitInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected CompanyOrgUnitInfo getCompanyOrgUnitInfoF7(Context ctx, String number, String type) throws Exception {
		CompanyOrgUnitInfo companyOrgUnitInfo = new CompanyOrgUnitInfo();
		CompanyOrgUnitCollection companyOrgUnitCollection = CompanyOrgUnitFactory.getLocalInstance(ctx).getCompanyOrgUnitCollection(getViewInfo(number));
		if (type.equals("2")) {
			if (number != null) {
				if (number.length() > 0) {
					try {
						if ((companyOrgUnitCollection  != null) && (companyOrgUnitCollection.size() > 0)) {
							companyOrgUnitInfo = companyOrgUnitCollection.get(0);
						} else {
							throw new Exception("δ��EAS���ҵ���Ӧ�Ĺ�˾��"+ number);
						}
					} catch (BOSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else {
			try {
				if ((companyOrgUnitCollection  != null) && (companyOrgUnitCollection.size() > 0)) {
					companyOrgUnitInfo = companyOrgUnitCollection.get(0);
				} else {
					throw new Exception("δ��EAS���ҵ���Ӧ�Ĺ�˾��"+ number);
				}
			} catch (BOSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return companyOrgUnitInfo;
	}
	/**
	 * ����ڼ�F7		PeriodInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected PeriodInfo getPeriodInfoF7(Context ctx, String number, String type) throws Exception {
		
		PeriodInfo periodInfo = new PeriodInfo();
		if (type.equals("2")) {
			if (number != null) {
				if (number.length() > 0) {
					try {
						PeriodCollection periodCollection = PeriodFactory.getLocalInstance(ctx).getPeriodCollection(getViewInfo(number));
						if ((periodCollection != null) && (periodCollection.size() > 0)) {
							periodInfo = periodCollection.get(0);
						} else {
							throw new Exception("EAS��δ�ҵ���Ӧ�Ļ���ڼ䣺"+ number +"�����顣");
						}
					} catch (BOSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			try {
				PeriodCollection periodCollection = PeriodFactory.getLocalInstance(ctx).getPeriodCollection(getViewInfo(number));
				if ((periodCollection != null) && (periodCollection.size() > 0)) {
					periodInfo = periodCollection.get(0);
				} else {
					throw new Exception("EAS��δ�ҵ���Ӧ�Ļ���ڼ䣺"+ number +"�����顣");
				}
			} catch (BOSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return periodInfo;
	}
	/**
	 * �ұ�F7	CurrencyInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected CurrencyInfo getCurrencyInfoF7(Context ctx, String number, String type) throws Exception {
		CurrencyInfo currencyInfo = new CurrencyInfo();
		CurrencyCollection currencyCollection = CurrencyFactory.getLocalInstance(ctx).getCurrencyCollection(getViewInfo(number));
		if (type.equals("2")) {
			if (number != null) {
				if (number.length() > 0) {
					try {
						if ((currencyCollection  != null) && (currencyCollection.size() > 0)) {
							currencyInfo = currencyCollection.get(0);
						} else {
							throw new Exception("EAS��δ�ҵ���Ӧ�ıұ�"+ number +"�����顣");
						}
					} catch (BOSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else{
			try {
				if ((currencyCollection  != null) && (currencyCollection.size() > 0)) {
					currencyInfo = currencyCollection.get(0);
				} else {
					throw new Exception("EAS��δ�ҵ���Ӧ�ıұ�"+ number +"�����顣");
				}
			} catch (BOSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return currencyInfo;
	}
	/**
	 * ƾ֤����F7		VoucherTypeInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected VoucherTypeInfo getVoucherTypeInfoF7(Context ctx, String number) throws Exception {
		if (number == null) {
			throw new Exception("ƾ֤���Ͳ���Ϊ�գ�");
		}
		VoucherTypeInfo voucherTypeInfo = new VoucherTypeInfo();
		try {
			VoucherTypeCollection voucherTypeCollection = VoucherTypeFactory.getLocalInstance(ctx).getVoucherTypeCollection(getViewInfo(number));
			if ((voucherTypeCollection  != null) && (voucherTypeCollection.size() > 0)) {
				voucherTypeInfo = voucherTypeCollection.get(0);
			} else {
				throw new Exception("EAS��δ�ҵ���Ӧ��ƾ֤���ͣ�"+ number +"�����顣");
			}
		} catch (BOSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return voucherTypeInfo;
	}
	/**
	 * �û�F7		UserInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	
	protected UserInfo getUserInfoF7(Context ctx, String number) throws Exception {
		UserInfo userInfo = new UserInfo();
		try {
//			UserCollection userCollection = UserFactory.getLocalInstance(ctx).getUserCollection(getViewInfo(number));
			PersonInfo personInfo = this.getPersonInfoForIdnumF7(ctx, number);
			String id = personInfo.getId().toString();
			//ͨ����Ӧ�����˲����û�
			UserCollection userCollection = UserFactory.getLocalInstance(ctx).getUserCollection("where person = '"+ id +"'");
			if ((userCollection  != null) && (userCollection.size() > 0)) {
				userInfo = userCollection.get(0);
			} else {
				throw new Exception("EASδ�ҵ���Ӧ���û���"+ number +"�����顣");
			}
		} catch (BOSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userInfo;
	}
	 */
	protected UserInfo getUserInfoF7(Context ctx, String number) throws Exception
	  {
	    UserInfo userInfo = new UserInfo();
	    try
	    {
	      UserCollection userCollection = UserFactory.getLocalInstance(ctx).getUserCollection("where person = '" + number + "'");
	      if ((userCollection != null) && (userCollection.size() > 0))
	        userInfo = userCollection.get(0);
	      else
	        throw new Exception("EASδ�ҵ���Ӧ���û���" + number + "�����顣");
	    }
	    catch (BOSException e)
	    {
	      e.printStackTrace();
	    }
	    return userInfo;
	  }
	/**
	 * ����ԪF7		CtrlUnitInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected CtrlUnitInfo getCtrlUnitInfoF7(Context ctx, String number) throws Exception {
		CtrlUnitInfo ctrlUnitInfo = new CtrlUnitInfo();
		try {
			CtrlUnitCollection ctrlUnitCollection = CtrlUnitFactory.getLocalInstance(ctx).getCtrlUnitCollection(getViewInfo(number));
			if ((ctrlUnitCollection  != null) && (ctrlUnitCollection.size() > 0)) {
				ctrlUnitInfo = ctrlUnitCollection.get(0);
			} else {
				throw new Exception("EAS��δ�ҵ���Ӧ�Ĺ���Ԫ��"+ number +"�����顣");
			}
		} catch (BOSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ctrlUnitInfo;
	}
	/**
	 * ��ƿ�ĿF7		AccountViewInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected AccountViewInfo getAccountViewInfoF7(Context ctx, String number, String type, CompanyOrgUnitInfo companyOrgUnitInfo) throws Exception {
		AccountViewInfo accountViewInfo = new AccountViewInfo();
		AccountViewCollection accountViewCollection = AccountViewFactory.getLocalInstance(ctx).getAccountViewCollection("where number = '"+number+"' and companyID = '"+companyOrgUnitInfo.getId().toString()+"'");
		if (type.equals("2")) {
			if (number != null) {
				if (number.length() > 0) {
					try {
						if ((accountViewCollection  != null) && (accountViewCollection.size() > 0)) {
							accountViewInfo = accountViewCollection.get(0);
						} else {
							throw new Exception("EASδ�ҵ���Ӧ�Ļ�ƿ�Ŀ��"+ number +"�����顣");
						}
					} catch (BOSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}else {
			try {
				if ((accountViewCollection  != null) && (accountViewCollection.size() > 0)) {
					accountViewInfo = accountViewCollection.get(0);
				} else {
					throw new Exception("EASδ�ҵ���Ӧ�Ļ�ƿ�Ŀ��"+ number +"�����顣");
				}
			} catch (BOSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return accountViewInfo;
	}
	/**
	 * ������λF7		MeasureUnitInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected MeasureUnitInfo getMeasureUnitInfoF7(Context ctx, String number, String type) throws Exception {
		MeasureUnitInfo meamsureUnitInfo = new MeasureUnitInfo();
		if (type.equals("2")) {
			if (number != null) {
				if (number.length() > 0) {
					try {
						MeasureUnitCollection measureUnitCollection = MeasureUnitFactory.getLocalInstance(ctx).getMeasureUnitCollection(getViewInfo(number));
						if ((measureUnitCollection  != null) && (measureUnitCollection.size() > 0)) {
							meamsureUnitInfo = measureUnitCollection.get(0);
						} else {
							throw new Exception("EAS��δ�ҵ���Ӧ�ļ�����λ��"+ number +"�����顣");
						}
					} catch (BOSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else {
			try {
				MeasureUnitCollection measureUnitCollection = MeasureUnitFactory.getLocalInstance(ctx).getMeasureUnitCollection(getViewInfo(number));
				if ((measureUnitCollection  != null) && (measureUnitCollection.size() > 0)) {
					meamsureUnitInfo = measureUnitCollection.get(0);
				} else {
					throw new Exception("EAS��δ�ҵ���Ӧ�ļ�����λ��"+ number +"�����顣");
				}
			} catch (BOSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return meamsureUnitInfo;
	}
	/**
	 * �����˻�F7		AccountBankInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected AccountBankInfo getAccountBankInfoF7(Context ctx, String number) throws Exception {
		AccountBankInfo accountBankInfo = new AccountBankInfo();
		if (number != null) {
			if (number.length() > 0) {
				
				try {
//					AccountBankCollection accountBankCollection = AccountBankFactory.getLocalInstance(ctx).getAccountBankCollection(getViewInfo(number));
					AccountBankCollection accountBankCollection = AccountBankFactory.getLocalInstance(ctx).getAccountBankCollection("where BANKACCOUNTNUMBER = '"+number+"'");
					if ((accountBankCollection  != null) && (accountBankCollection.size() > 0)) {
						accountBankInfo = accountBankCollection.get(0);
					} else {
						throw new Exception("EAS��δ�ҵ���Ӧ�������˻���"+ number +"�����顣");
					}
				} catch (BOSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return accountBankInfo;
	}
	/**
	 * �ڲ��˻�F7		InnerAccountInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected InnerAccountInfo getInnerAccountInfoF7(Context ctx, String number) throws Exception {
		InnerAccountInfo innerAccountInfo = new InnerAccountInfo();
		if (number != null) {
			if (number.length() > 0) {
				
				try {
					InnerAccountCollection innerAccountCollection = InnerAccountFactory.getLocalInstance(ctx).getInnerAccountCollection(getViewInfo(number));
					if ((innerAccountCollection  != null) && (innerAccountCollection.size() > 0)) {
						innerAccountInfo = innerAccountCollection.get(0);
					} else {
						throw new Exception("EAS��δ�ҵ���Ӧ���ڲ��˻���"+ number +"�����顣");
					}
				} catch (BOSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return innerAccountInfo;
	}
	/**
	 * ���ڻ���F7		BankInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected BankInfo getBankInfoF7(Context ctx, String number) throws Exception {
		BankInfo bankInfo = new BankInfo();
		if (number != null) {
			if (number.length() > 0) {
				
				try {
					BankCollection bankCollection = BankFactory.getLocalInstance(ctx).getBankCollection(getViewInfo(number));
					if ((bankCollection  != null) && (bankCollection.size() > 0)) {
						bankInfo = bankCollection.get(0);
					} else {
						throw new Exception("EAS��δ�ҵ���Ӧ�Ľ��ڻ�����"+ number +"�����顣");
					}
				} catch (BOSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bankInfo;
	}
	/**
	 * �ͻ�F7		CustomerInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected CustomerInfo getCustomerInfoF7(Context ctx, String number) throws Exception {
		CustomerInfo customerInfo = new CustomerInfo();
		if (number != null) {
			if (number.length() > 0) {
				
				try {
					CustomerCollection customerCollection = CustomerFactory.getLocalInstance(ctx).getCustomerCollection(getViewInfo(number));
					if ((customerCollection  != null) && (customerCollection.size() > 0)) {
						customerInfo = customerCollection.get(0);
					} else {
						throw new Exception("EAS��δ�ҵ���Ӧ�Ŀͻ���"+ number +"�����顣");
					}
				} catch (BOSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return customerInfo;
	}
	/**
	 * ��Ӧ��F7		SupplierInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected SupplierInfo getSupplierInfoF7(Context ctx, String number) throws Exception {
		SupplierInfo supplierInfo = new SupplierInfo();
		if (number != null) {
			if (number.length() > 0) {
				try {
//					SupplierCollection supplierCollection = SupplierFactory.getLocalInstance(ctx).getSupplierCollection(getViewInfo(number));
					SupplierCollection supplierCollection = SupplierFactory.getLocalInstance(ctx).getSupplierCollection("where MNEMONICCODE = '"+ number +"'");
					if ((supplierCollection  != null) && (supplierCollection.size() > 0)) {
						supplierInfo = supplierCollection.get(0);
					} else {
						throw new Exception("EAS��δ�ҵ���Ӧ�Ĺ�Ӧ�̣�"+ number +"�����顣");
					}
				} catch (BOSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return supplierInfo;
	}
	/**
	 * �ɱ�����F7		CostCenterOrgUnitInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected CostCenterOrgUnitInfo getCostCenterOrgUnitInfoF7(Context ctx, String number) throws Exception {
		CostCenterOrgUnitInfo costCenterOrgUnitInfo = new CostCenterOrgUnitInfo();
		if (number != null) {
			if (number.length() > 0) {
				try {
					CostCenterOrgUnitCollection costCenterOrgUnitCollection = CostCenterOrgUnitFactory.getLocalInstance(ctx).getCostCenterOrgUnitCollection(getViewInfo(number));
					if ((costCenterOrgUnitCollection  != null) && (costCenterOrgUnitCollection.size() > 0)) {
						costCenterOrgUnitInfo = costCenterOrgUnitCollection.get(0);
					} else {
						throw new Exception("EAS��δ�ҵ���Ӧ�ĳɱ����ģ�"+ number +"�����顣");
					}
				} catch (BOSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return costCenterOrgUnitInfo;
	}
	/**
	 * ����F7		MaterialInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected MaterialInfo getMaterialInfoF7(Context ctx, String number) throws Exception {
		MaterialInfo materialInfo = new MaterialInfo();
		if (number != null) {
			if (number.length() > 0) {
				try {
					MaterialCollection materialCollection = MaterialFactory.getLocalInstance(ctx).getMaterialCollection(getViewInfo(number));
					if ((materialCollection  != null) && (materialCollection.size() > 0)) {
						materialInfo = materialCollection.get(0);
					} else {
						throw new Exception("EAS��δ�ҵ���Ӧ�����ϣ�"+ number +"�����顣");
					}
				} catch (BOSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return materialInfo;
	}
	/**
	 * ���㷽ʽF7		SettlementTypeInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected SettlementTypeInfo getSettlementTypeInfoF7(Context ctx, String number) throws Exception {
		SettlementTypeInfo settlementTypeInfo = new SettlementTypeInfo();
		if (number != null) {
			if (number.length() > 0) {
				try {
					SettlementTypeCollection settlementTypeCollection = SettlementTypeFactory.getLocalInstance(ctx).getSettlementTypeCollection(getViewInfo(number));
					if ((settlementTypeCollection  != null) && (settlementTypeCollection.size() > 0)) {
						settlementTypeInfo = settlementTypeCollection.get(0);
					} else {
						throw new Exception("EAS��δ�ҵ���Ӧ�Ľ��㷽ʽ��"+ number +"�����顣");
					}
				} catch (BOSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return settlementTypeInfo;
	}
	/**
	 * ְԱF7		PersonInfo
	 * ͨ���������
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected PersonInfo getPersonInfoForNumberF7(Context ctx, String number) throws Exception {
		PersonInfo personInfo = new PersonInfo();
		if (number != null) {
			if (number.length() > 0) {
				try {
					
					PersonCollection personCollection = PersonFactory.getLocalInstance(ctx).getPersonCollection(getViewInfo(number));
					if ((personCollection  != null) && (personCollection.size() > 0)) {
						personInfo = personCollection.get(0);
					} else {
						throw new Exception("EAS��δ�ҵ���Ӧ��ְԱ��"+ number +"�����顣");
					}
				} catch (BOSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return personInfo;
	}
	/**
	 * ְԱF7		PersonInfo
	 * ͨ�����������
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected PersonInfo getPersonInfoForIdnumF7(Context ctx, String number) throws Exception {
		PersonInfo personInfo = new PersonInfo();
		if (number != null) {
			if (number.length() > 0) {
				try {
					
					PersonCollection personCollection = PersonFactory.getLocalInstance(ctx).getPersonCollection("where idnum = '"+ number +"'");
					if ((personCollection  != null) && (personCollection.size() > 0)) {
						personInfo = personCollection.get(0);
					} else {
						throw new Exception("EAS��δ�ҵ���Ӧ��ְԱ��"+ number +"�����顣");
					}
				} catch (BOSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return personInfo;
	}
	/**
	 * �Զ��������ĿF7		GeneralAsstActTypeInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected GeneralAsstActTypeInfo getGeneralAsstActTypeInfoF7(Context ctx, String number) throws Exception {
		GeneralAsstActTypeInfo generalAsstActTypeInfo = new GeneralAsstActTypeInfo();
		if (number != null) {
			if (number.length() > 0) {
				
				try {
					String longNumber = number.replace(".", "!");
					System.out.println("longNumber======================"+longNumber);
					GeneralAsstActTypeCollection generalAsstActTypeCollection = GeneralAsstActTypeFactory.getLocalInstance(ctx).getGeneralAsstActTypeCollection("where longNumber = '"+ longNumber +"'");
					if ((generalAsstActTypeCollection  != null) && (generalAsstActTypeCollection.size() > 0)) {
						generalAsstActTypeInfo = generalAsstActTypeCollection.get(0);
					} else {
						throw new Exception("EAS��δ�ҵ���Ӧ���Զ��������Ŀ��"+ number +"�����顣");
					}
				} catch (BOSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return generalAsstActTypeInfo;
	}
	/**
	 * �ֽ�������ĿF7		CashFlowItemInfo
	 * @param ctx
	 * @param number
	 * @return
	 * @throws Exception 
	 */
	protected CashFlowItemInfo getCashFlowItemInfoF7(Context ctx, String number) throws Exception {
		CashFlowItemInfo cashFlowItemInfo = new CashFlowItemInfo();
		if (number != null) {
			if (number.length() > 0) {
				try {
					CashFlowItemCollection cashFlowItemCollection = CashFlowItemFactory.getLocalInstance(ctx).getCashFlowItemCollection(getViewInfo(number));
					if ((cashFlowItemCollection  != null) && (cashFlowItemCollection.size() > 0)) {
						cashFlowItemInfo = cashFlowItemCollection.get(0);
					} else {
						throw new Exception("EAS��δ�ҵ���Ӧ���ֽ�������Ŀ��"+ number +"�����顣");
					}
				} catch (BOSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return cashFlowItemInfo;
	}
	
	/**
	 * �޸ĵ���״̬
	 * @param ctx
	 * @param number
	 * @return
	 */
	protected void updateStatus(Context ctx, VoucherBillInfo billInfo) {
		String sqldel = "update CT_LL_VoucherBill set cfstatus = 2 where fid='" + billInfo.getId()+ "'";
		try {
			DbUtil.execute(ctx, sqldel);
		} catch (BOSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static int getTotalCount(IRowSet rowSet) throws SQLException {
		int totalCount = 0;
		while (rowSet.next()) {
			totalCount = rowSet.getInt(1);
		}
		return totalCount;
	}
}