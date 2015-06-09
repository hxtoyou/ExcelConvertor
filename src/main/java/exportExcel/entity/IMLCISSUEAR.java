package exportExcel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import exportExcel.ORM.DAO.IDEntity;

/**
 * @author Xiao He(hxtoyou@163.com)
 * 
 * 
 */
@Entity
@Table(name = "IMLCISSUEAR")
public class IMLCISSUEAR extends IDEntity {
	private String txnno;
	private String lcno;
	private String isagent;
	private String agentbankname;
	private String agentlcno;
	private String clientbankname;
	private String clientlcno;
	private String recvbankswiftcode;
	private String recvbankname;
	private String advbankswiftcode;
	private String advbankname;
	private String appno;
	private String appenname;
	private String appcnname;
	private String isusance;
	private String blgorgno;
	private Date lcissuedate;
	private Date expirydate;
	private String expiryplace;
	private String lccursign;
	private Long lcamt;
	private Long lcaddiamt;
	private String addifunds;
	private String lcamttolerup;
	private String lcamttolerdown;
	private Long maxlcamt;
	private String lcmaxamtrepresent;
	private String contractno;
	private Long contractamt;
	private String lcform;
	private String availtype;
	private String applicablerules;
	private String othrrules;
	private String isfreenego;
	private String availbankpresent;
	private String negobankswiftcode;
	private String negobanknameaddr;
	private String tenortype;
	private Integer draftdays;
	private Integer draftinvpct;
	private String isneedtenor;
	private String draftdaysdesct;
	private String paybankswiftcode;
	private String paybanknameaddr;
	private String mixedpaydetails;
	private String deferpaydetails;
	private String confirminstruction;
	private String appbankswiftcode;
	private String appbanknameaddr;

	private String revolvingflag;
	private String revolvingtype;
	private Integer revolvingtimes;
	private Long evolvingtotoalamt;
	private Date nextrevolvingdate;
	private String iscount;
	private String isreimb;
	private String reimbbankswiftcode;

	private String reimbbannameaddr;
	private String reimbbankacctno;
	private String benefnameaddr;
	private String benefcountry;
	private String memo;
	private String parttialshipment;
	private String transshipment;
	private String loadportname;
	private String transportname;
	private Date latestshipdate;
	private String shipmentperiod;
	private String loadairportdest;
	private String dischairportdest;
	private String goodsservdescr;
	private String docrequred;
	private String addiconditions;
	private String presentperiod;
	private String charges;
	private String postscript;
	private String instruction;
	private Integer abtimes;
	private String lctype;
	private String lcinformno;
	private String isassignment;
	private String agentbankswiftcode;
	private String clientbankswiftcode;
	private String benecode;
	private String secdbenecode;
	private String secdbenefnameaddr;
	private String secdbenefcountry;
	private String feeno;
	private Double mgrrate;

	@Column(name = "TXNNO")
	public String getTxnno() {
		return txnno;
	}

	public void setTxnno(String txnno) {
		this.txnno = txnno;
	}

	@Column(name = "LCNO")
	public String getLcno() {
		return lcno;
	}

	public void setLcno(String lcno) {
		this.lcno = lcno;
	}

	@Column(name = "ISAGENT")
	public String getIsagent() {
		return isagent;
	}

	public void setIsagent(String isagent) {
		this.isagent = isagent;
	}

	@Column(name = "AGENTBANKNAME")
	public String getAgentbankname() {
		return agentbankname;
	}

	public void setAgentbankname(String agentbankname) {
		this.agentbankname = agentbankname;
	}

	@Column(name = "AGENTLCNO")
	public String getAgentlcno() {
		return agentlcno;
	}

	public void setAgentlcno(String agentlcno) {
		this.agentlcno = agentlcno;
	}

	@Column(name = "CLIENTBANKNAME")
	public String getClientbankname() {
		return clientbankname;
	}

	public void setClientbankname(String clientbankname) {
		this.clientbankname = clientbankname;
	}

	@Column(name = "CLIENTLCNO")
	public String getClientlcno() {
		return clientlcno;
	}

	public void setClientlcno(String clientlcno) {
		this.clientlcno = clientlcno;
	}

	@Column(name = "RECVBANKSWIFTCODE")
	public String getRecvbankswiftcode() {
		return recvbankswiftcode;
	}

	public void setRecvbankswiftcode(String recvbankswiftcode) {
		this.recvbankswiftcode = recvbankswiftcode;
	}

	@Column(name = "RECVBANKNAME")
	public String getRecvbankname() {
		return recvbankname;
	}

	public void setRecvbankname(String recvbankname) {
		this.recvbankname = recvbankname;
	}

	@Column(name = "ADVBANKSWIFTCODE")
	public String getAdvbankswiftcode() {
		return advbankswiftcode;
	}

	public void setAdvbankswiftcode(String advbankswiftcode) {
		this.advbankswiftcode = advbankswiftcode;
	}

	@Column(name = "ADVBANKNAME")
	public String getAdvbankname() {
		return advbankname;
	}

	public void setAdvbankname(String advbankname) {
		this.advbankname = advbankname;
	}

	@Column(name = "APPNO")
	public String getAppno() {
		return appno;
	}

	public void setAppno(String appno) {
		this.appno = appno;
	}

	@Column(name = "APPENNAME")
	public String getAppenname() {
		return appenname;
	}

	public void setAppenname(String appenname) {
		this.appenname = appenname;
	}

	@Column(name = "APPCNNAME")
	public String getAppcnname() {
		return appcnname;
	}

	public void setAppcnname(String appcnname) {
		this.appcnname = appcnname;
	}

	@Column(name = "ISUSANCE")
	public String getIsusance() {
		return isusance;
	}

	public void setIsusance(String isusance) {
		this.isusance = isusance;
	}

	@Column(name = "BLGORGNO")
	public String getBlgorgno() {
		return blgorgno;
	}

	public void setBlgorgno(String blgorgno) {
		this.blgorgno = blgorgno;
	}

	@Column(name = "LCISSUEDATE")
	public Date getLcissuedate() {
		return lcissuedate;
	}

	public void setLcissuedate(Date lcissuedate) {
		this.lcissuedate = lcissuedate;
	}

	@Column(name = "EXPIRYDATE")
	public Date getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}

	@Column(name = "EXPIRYPLACE")
	public String getExpiryplace() {
		return expiryplace;
	}

	public void setExpiryplace(String expiryplace) {
		this.expiryplace = expiryplace;
	}

	@Column(name = "LCCURSIGN")
	public String getLccursign() {
		return lccursign;
	}

	public void setLccursign(String lccursign) {
		this.lccursign = lccursign;
	}

	@Column(name = "LCAMT")
	public Long getLcamt() {
		return lcamt;
	}

	public void setLcamt(Long lcamt) {
		this.lcamt = lcamt;
	}

	@Column(name = "LCADDIAMT")
	public Long getLcaddiamt() {
		return lcaddiamt;
	}

	public void setLcaddiamt(Long lcaddiamt) {
		this.lcaddiamt = lcaddiamt;
	}

	@Column(name = "ADDIFUNDS")
	public String getAddifunds() {
		return addifunds;
	}

	public void setAddifunds(String addifunds) {
		this.addifunds = addifunds;
	}

	@Column(name = "LCAMTTOLERUP")
	public String getLcamttolerup() {
		return lcamttolerup;
	}

	public void setLcamttolerup(String lcamttolerup) {
		this.lcamttolerup = lcamttolerup;
	}

	@Column(name = "LCAMTTOLERDOWN")
	public String getLcamttolerdown() {
		return lcamttolerdown;
	}

	public void setLcamttolerdown(String lcamttolerdown) {
		this.lcamttolerdown = lcamttolerdown;
	}

	@Column(name = "MAXLCAMT")
	public Long getMaxlcamt() {
		return maxlcamt;
	}

	public void setMaxlcamt(Long maxlcamt) {
		this.maxlcamt = maxlcamt;
	}

	@Column(name = "LCMAXAMTREPRESENT")
	public String getLcmaxamtrepresent() {
		return lcmaxamtrepresent;
	}

	public void setLcmaxamtrepresent(String lcmaxamtrepresent) {
		this.lcmaxamtrepresent = lcmaxamtrepresent;
	}

	@Column(name = "CONTRACTNO")
	public String getContractno() {
		return contractno;
	}

	public void setContractno(String contractno) {
		this.contractno = contractno;
	}

	@Column(name = "CONTRACTAMT")
	public Long getContractamt() {
		return contractamt;
	}

	public void setContractamt(Long contractamt) {
		this.contractamt = contractamt;
	}

	@Column(name = "LCFORM")
	public String getLcform() {
		return lcform;
	}

	public void setLcform(String lcform) {
		this.lcform = lcform;
	}

	@Column(name = "AVAILTYPE")
	public String getAvailtype() {
		return availtype;
	}

	public void setAvailtype(String availtype) {
		this.availtype = availtype;
	}

	@Column(name = "APPLICABLERULES")
	public String getApplicablerules() {
		return applicablerules;
	}

	public void setApplicablerules(String applicablerules) {
		this.applicablerules = applicablerules;
	}

	@Column(name = "OTHRRULES")
	public String getOthrrules() {
		return othrrules;
	}

	public void setOthrrules(String othrrules) {
		this.othrrules = othrrules;
	}

	@Column(name = "ISFREENEGO")
	public String getIsfreenego() {
		return isfreenego;
	}

	public void setIsfreenego(String isfreenego) {
		this.isfreenego = isfreenego;
	}

	@Column(name = "AVAILBANKPRESENT")
	public String getAvailbankpresent() {
		return availbankpresent;
	}

	public void setAvailbankpresent(String availbankpresent) {
		this.availbankpresent = availbankpresent;
	}

	@Column(name = "NEGOBANKSWIFTCODE")
	public String getNegobankswiftcode() {
		return negobankswiftcode;
	}

	public void setNegobankswiftcode(String negobankswiftcode) {
		this.negobankswiftcode = negobankswiftcode;
	}

	@Column(name = "NEGOBANKNAMEADDR")
	public String getNegobanknameaddr() {
		return negobanknameaddr;
	}

	public void setNegobanknameaddr(String negobanknameaddr) {
		this.negobanknameaddr = negobanknameaddr;
	}

	@Column(name = "TENORTYPE")
	public String getTenortype() {
		return tenortype;
	}

	public void setTenortype(String tenortype) {
		this.tenortype = tenortype;
	}

	@Column(name = "DRAFTDAYS")
	public Integer getDraftdays() {
		return draftdays;
	}

	public void setDraftdays(Integer draftdays) {
		this.draftdays = draftdays;
	}

	@Column(name = "DRAFTINVPCT")
	public Integer getDraftinvpct() {
		return draftinvpct;
	}

	public void setDraftinvpct(Integer draftinvpct) {
		this.draftinvpct = draftinvpct;
	}

	@Column(name = "ISNEEDTENOR")
	public String getIsneedtenor() {
		return isneedtenor;
	}

	public void setIsneedtenor(String isneedtenor) {
		this.isneedtenor = isneedtenor;
	}

	@Column(name = "DRAFTDAYSDESCT")
	public String getDraftdaysdesct() {
		return draftdaysdesct;
	}

	public void setDraftdaysdesct(String draftdaysdesct) {
		this.draftdaysdesct = draftdaysdesct;
	}

	@Column(name = "PAYBANKSWIFTCODE")
	public String getPaybankswiftcode() {
		return paybankswiftcode;
	}

	public void setPaybankswiftcode(String paybankswiftcode) {
		this.paybankswiftcode = paybankswiftcode;
	}

	@Column(name = "PAYBANKNAMEADDR")
	public String getPaybanknameaddr() {
		return paybanknameaddr;
	}

	public void setPaybanknameaddr(String paybanknameaddr) {
		this.paybanknameaddr = paybanknameaddr;
	}

	@Column(name = "MIXEDPAYDETAILS")
	public String getMixedpaydetails() {
		return mixedpaydetails;
	}

	public void setMixedpaydetails(String mixedpaydetails) {
		this.mixedpaydetails = mixedpaydetails;
	}

	@Column(name = "DEFERPAYDETAILS")
	public String getDeferpaydetails() {
		return deferpaydetails;
	}

	public void setDeferpaydetails(String deferpaydetails) {
		this.deferpaydetails = deferpaydetails;
	}

	@Column(name = "CONFIRMINSTRUCTION")
	public String getConfirminstruction() {
		return confirminstruction;
	}

	public void setConfirminstruction(String confirminstruction) {
		this.confirminstruction = confirminstruction;
	}

	@Column(name = "APPBANKSWIFTCODE")
	public String getAppbankswiftcode() {
		return appbankswiftcode;
	}

	public void setAppbankswiftcode(String appbankswiftcode) {
		this.appbankswiftcode = appbankswiftcode;
	}

	@Column(name = "APPBANKNAMEADDR")
	public String getAppbanknameaddr() {
		return appbanknameaddr;
	}

	public void setAppbanknameaddr(String appbanknameaddr) {
		this.appbanknameaddr = appbanknameaddr;
	}

	@Column(name = "REVOLVINGFLAG")
	public String getRevolvingflag() {
		return revolvingflag;
	}

	public void setRevolvingflag(String revolvingflag) {
		this.revolvingflag = revolvingflag;
	}

	@Column(name = "REVOLVINGTYPE")
	public String getRevolvingtype() {
		return revolvingtype;
	}

	public void setRevolvingtype(String revolvingtype) {
		this.revolvingtype = revolvingtype;
	}

	@Column(name = "REVOLVINGTIMES")
	public Integer getRevolvingtimes() {
		return revolvingtimes;
	}

	public void setRevolvingtimes(Integer revolvingtimes) {
		this.revolvingtimes = revolvingtimes;
	}

	@Column(name = "EVOLVINGTOTOALAMT")
	public Long getEvolvingtotoalamt() {
		return evolvingtotoalamt;
	}

	public void setEvolvingtotoalamt(Long evolvingtotoalamt) {
		this.evolvingtotoalamt = evolvingtotoalamt;
	}

	@Column(name = "NEXTREVOLVINGDATE")
	public Date getNextrevolvingdate() {
		return nextrevolvingdate;
	}

	public void setNextrevolvingdate(Date nextrevolvingdate) {
		this.nextrevolvingdate = nextrevolvingdate;
	}

	@Column(name = "ISCOUNT")
	public String getIscount() {
		return iscount;
	}

	public void setIscount(String iscount) {
		this.iscount = iscount;
	}

	@Column(name = "ISREIMB")
	public String getIsreimb() {
		return isreimb;
	}

	public void setIsreimb(String isreimb) {
		this.isreimb = isreimb;
	}

	@Column(name = "REIMBBANKSWIFTCODE")
	public String getReimbbankswiftcode() {
		return reimbbankswiftcode;
	}

	public void setReimbbankswiftcode(String reimbbankswiftcode) {
		this.reimbbankswiftcode = reimbbankswiftcode;
	}

	@Column(name = "REIMBBANNAMEADDR")
	public String getReimbbannameaddr() {
		return reimbbannameaddr;
	}

	public void setReimbbannameaddr(String reimbbannameaddr) {
		this.reimbbannameaddr = reimbbannameaddr;
	}

	@Column(name = "REIMBBANKACCTNO")
	public String getReimbbankacctno() {
		return reimbbankacctno;
	}

	public void setReimbbankacctno(String reimbbankacctno) {
		this.reimbbankacctno = reimbbankacctno;
	}

	@Column(name = "BENEFNAMEADDR")
	public String getBenefnameaddr() {
		return benefnameaddr;
	}

	public void setBenefnameaddr(String benefnameaddr) {
		this.benefnameaddr = benefnameaddr;
	}

	@Column(name = "BENEFCOUNTRY")
	public String getBenefcountry() {
		return benefcountry;
	}

	public void setBenefcountry(String benefcountry) {
		this.benefcountry = benefcountry;
	}

	@Column(name = "MEMO")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "PARTTIALSHIPMENT")
	public String getParttialshipment() {
		return parttialshipment;
	}

	public void setParttialshipment(String parttialshipment) {
		this.parttialshipment = parttialshipment;
	}

	@Column(name = "TRANSSHIPMENT")
	public String getTransshipment() {
		return transshipment;
	}

	public void setTransshipment(String transshipment) {
		this.transshipment = transshipment;
	}

	@Column(name = "LOADPORTNAME")
	public String getLoadportname() {
		return loadportname;
	}

	public void setLoadportname(String loadportname) {
		this.loadportname = loadportname;
	}

	@Column(name = "TRANSPORTNAME")
	public String getTransportname() {
		return transportname;
	}

	public void setTransportname(String transportname) {
		this.transportname = transportname;
	}

	@Column(name = "LATESTSHIPDATE")
	public Date getLatestshipdate() {
		return latestshipdate;
	}

	public void setLatestshipdate(Date latestshipdate) {
		this.latestshipdate = latestshipdate;
	}

	@Column(name = "SHIPMENTPERIOD")
	public String getShipmentperiod() {
		return shipmentperiod;
	}

	public void setShipmentperiod(String shipmentperiod) {
		this.shipmentperiod = shipmentperiod;
	}

	@Column(name = "LOADAIRPORTDEST")
	public String getLoadairportdest() {
		return loadairportdest;
	}

	public void setLoadairportdest(String loadairportdest) {
		this.loadairportdest = loadairportdest;
	}

	@Column(name = "DISCHAIRPORTDEST")
	public String getDischairportdest() {
		return dischairportdest;
	}

	public void setDischairportdest(String dischairportdest) {
		this.dischairportdest = dischairportdest;
	}

	@Column(name = "GOODSSERVDESCR")
	@Lob
	public String getGoodsservdescr() {
		return goodsservdescr;
	}

	public void setGoodsservdescr(String goodsservdescr) {
		this.goodsservdescr = goodsservdescr;
	}

	@Column(name = "DOCREQURED")
	@Lob
	public String getDocrequred() {
		return docrequred;
	}

	public void setDocrequred(String docrequred) {
		this.docrequred = docrequred;
	}

	@Column(name = "ADDICONDITIONS")
	@Lob
	public String getAddiconditions() {
		return addiconditions;
	}

	public void setAddiconditions(String addiconditions) {
		this.addiconditions = addiconditions;
	}

	@Column(name = "PRESENTPERIOD")
	public String getPresentperiod() {
		return presentperiod;
	}

	public void setPresentperiod(String presentperiod) {
		this.presentperiod = presentperiod;
	}

	@Column(name = "CHARGES")
	public String getCharges() {
		return charges;
	}

	public void setCharges(String charges) {
		this.charges = charges;
	}

	@Column(name = "POSTSCRIPT")
	public String getPostscript() {
		return postscript;
	}

	public void setPostscript(String postscript) {
		this.postscript = postscript;
	}

	@Column(name = "INSTRUCTION")
	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	@Column(name = "ABTIMES")
	public Integer getAbtimes() {
		return abtimes;
	}

	public void setAbtimes(Integer abtimes) {
		this.abtimes = abtimes;
	}

	@Column(name = "LCTYPE")
	public String getLctype() {
		return lctype;
	}

	public void setLctype(String lctype) {
		this.lctype = lctype;
	}

	@Column(name = "LCINFORMNO")
	public String getLcinformno() {
		return lcinformno;
	}

	public void setLcinformno(String lcinformno) {
		this.lcinformno = lcinformno;
	}

	@Column(name = "ISASSIGNMENT")
	public String getIsassignment() {
		return isassignment;
	}

	public void setIsassignment(String isassignment) {
		this.isassignment = isassignment;
	}

	@Column(name = "AGENTBANKSWIFTCODE")
	public String getAgentbankswiftcode() {
		return agentbankswiftcode;
	}

	public void setAgentbankswiftcode(String agentbankswiftcode) {
		this.agentbankswiftcode = agentbankswiftcode;
	}

	@Column(name = "CLIENTBANKSWIFTCODE")
	public String getClientbankswiftcode() {
		return clientbankswiftcode;
	}

	public void setClientbankswiftcode(String clientbankswiftcode) {
		this.clientbankswiftcode = clientbankswiftcode;
	}

	@Column(name = "BENECODE")
	public String getBenecode() {
		return benecode;
	}

	public void setBenecode(String benecode) {
		this.benecode = benecode;
	}

	@Column(name = "SECDBENECODE")
	public String getSecdbenecode() {
		return secdbenecode;
	}

	public void setSecdbenecode(String secdbenecode) {
		this.secdbenecode = secdbenecode;
	}

	@Column(name = "SECDBENEFNAMEADDR")
	public String getSecdbenefnameaddr() {
		return secdbenefnameaddr;
	}

	public void setSecdbenefnameaddr(String secdbenefnameaddr) {
		this.secdbenefnameaddr = secdbenefnameaddr;
	}

	@Column(name = "SECDBENEFCOUNTRY")
	public String getSecdbenefcountry() {
		return secdbenefcountry;
	}

	public void setSecdbenefcountry(String secdbenefcountry) {
		this.secdbenefcountry = secdbenefcountry;
	}

	@Column(name = "FEENO")
	public String getFeeno() {
		return feeno;
	}

	public void setFeeno(String feeno) {
		this.feeno = feeno;
	}

	@Column(name = "MGRRATE")
	public Double getMgrrate() {
		return mgrrate;
	}

	public void setMgrrate(Double mgrrate) {
		this.mgrrate = mgrrate;
	}

}
