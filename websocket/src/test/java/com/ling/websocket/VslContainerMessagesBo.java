package com.ling.websocket;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 箱动态报文业务对象 vsl_container_messages
 */
@Data
@Accessors(chain = true)
public class VslContainerMessagesBo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 发送人
     */
    private String sender;

    /**
     * 状态 1 未生成箱动态，2 已生成箱动态
     */
    private Long status;

    /**
     * 发送方
     */
    private String sendingParty;

    /**
     * 原始报文
     */
    private String originalMessage;

    /**
     * 我方返回
     */
    private String ourResponse;

    // =================================== 00 头记录 ===============================================

    // 报文类型
    private String messageType;
    // 文件说明
    private String fileDescription;
    // 文件功能
    private String fileFunction;
    // 发送方代码
    private String senderCode;
    // 接收方代码
    private String receiverCode;
    // 文件建立时间
    private Date fileCreateTime;
    // 发送港代码
    private String senderPortCode;
    // 接收港代码
    private String receiverPortCode;

    // =================================== 01 其他接收方 ===============================================

    // 其他方接收方代码
    private String otherReceiverCode;
    // 其他方接收方代码2
    private String otherReceiverCode2;

    // =================================== 10 描述船舶有关的基本数据项目 ===============================================

    // 船名代码
    private String vslVesselCode;
    // 船名
    private String vessel;
    // 舶国籍代码
    private String nationalityCode;
    // 航次
    private String voyage;
    // 航线代码
    private String tradeCode;
    // 航线
    private String trade;
    // 预计到达日期
    private Date etdArrivedDate;
    // 离港日期
    private Date sailingDate;
    // 离港地点代码
    private String departPortCode;
    // 离港地点
    private String departPort;
    // 下一挂港代码
    private String nextCallingPortCode;
    // 下一挂港
    private String nextCallingPort;

    // =================================== 11 描述船舶有关的基本数据项目 ===============================================

    // 船公司（承运人）代码
    private String shippingLineCode;
    // 船公司（承运人）
    private String shippingLine;

    // =================================== 12 描述船舶有关的基本数据项目 ===============================================


    // 提单号
    private String blNo;
    // 前程运输船名代码
    private String preVesselCode;
    // 前程运输船名
    private String preVessel;
    // 前程运输航次
    private String preVoyage;
    // 收货地代码
    private String placeCodeOfReceipt;
    // 收货地
    private String placeOfReceipt;
    // 装货港代码
    private String loadPortCode;
    // 装货港
    private String loadPort;
    // 运输条款
    private String deliveryTerm;
    // 付款方式
    private String prepaidOfCollect;
    // 装货日期
    private Date loadDate;
    // 检疫代码
    private String quarantineCode;
    // 签发日期
    private Date issueDate;
    // 币种
    private String currency;
    // 汇率
    private String exchangeRate;

    // =================================== 13 提单的地点信息 ===============================================

    // 卸货港代码
    private String dischargePortCode;
    // 卸货港
    private String dischargePort;
    // 交货地代码
    private String deliveryPlaceCode;
    // 交货地
    private String deliveryPlace;
    // 中转港代码
    private String transferPortCode;
    // 中转港
    private String transferPort;
    // 提单签发地代码
    private String blIssuePlaceCode;
    // 提单签发地
    private String blIssuePlace;

    // =================================== 14 提单的可选卸货港信息 ===============================================

    // 第一可选卸货港代码
    private String optDischPort1Code;
    // 第一可选卸货港
    private String optDischPort1;
    // 第二可选卸货港代码
    private String optDischPort2Code;
    // 第二可选卸货港
    private String optDischPort2;
    // 第三可选卸货港代码
    private String optDischPort3Code;
    // 第三可选卸货港
    private String optDischPort3;
    // 第四可选卸货港代码
    private String optDischPort4Code;
    // 第四可选卸货港
    private String optDischPort4;
    // 第五可选卸货港代码
    private String optDischPort5Code;
    // 第五可选卸货港
    private String optDischPort5;

    // =================================== 15 运费信息 ===============================================

    // 运费及费用代码
    private String freightCode;
    // 运费及费用说明
    private String freightRemark;
    // 付款地点代码
    private String payableAtCode;
    // 付款地点
    private String payableAt;
    // 数量
    private String freightQuantity;
    // 币种
    private String frChCurrency;
    // 费率
    private String frChRate;
    // 数量单位
    private String unit;
    // 总计
    private String amount;
    // 付款方式
    private String collectPrepaid;

    // =================================== 16 发货人信息 ===============================================

    // 发货人代码
    private String shipperCode;
    // 发货人
    private String shipper;

    // =================================== 17 收货人信息 ===============================================

    // 收货人代码
    private String consigneeCode;
    // 收货人
    private String consignee;

    // =================================== 18 第一通知人信息 ===============================================

    // 第一通知人代码
    private String firstNotifyCode;
    // 第一通知人
    private String firstNotify;

    // =================================== 19 第二通知人信息 ===============================================

    // 第二通知人代码
    private String secondNotifyCode;
    // 第二通知人
    private String secondNotify;

    // =================================== 20 第三通知人信息 ===============================================

    // 第三通知人代码
    private String thirdNotifyCode;
    // 第三通知人
    private String thirdNotify;

    // =================================== 41 货物信息 ===============================================

    // 货物序号
    private String cargoSeqNo;
    // 货类代码
    private String cargoCode;
    // 件数
    private String cargoQuantity;
    // 包装类型代码
    private String cargoPackagesKindCode;
    // 包装类型
    private String cargoPackagesKind;
    // 货毛重
    private String cargoGrossWeight;
    // 货净重
    private String cargoNetWeight;
    // 货尺码
    private String cargoMeasurement;

    // =================================== 43 危险品和冷藏信息 ===============================================

    // 危险品分类
    private String dangerousClass;
    // 危险品页号
    private String dangerousPage;
    // 联合国危险品编号
    private String dangerousUndgNo;
    // 危险品标签
    private String dangerousLabel;
    // 危险货物闪点
    private String dangerousFlashPoint;
    // 船运危险品应急措施号
    private String dangerousEmsNo;
    // 医疗急救指南号
    private String dangerousMfagNo;
    // 应急联系
    private String dangerousEmergencyContact;
    // 温度计量单位
    private String dangerousTemperatureId;
    // 设置温度
    private String dangerousTemperatureSetting;
    // 冷藏最低温度
    private String dangerousMinTemperature;
    // 冷藏最高温度
    private String dangerousMaxTemperature;

    // =================================== 44 唛头信息 ===============================================

    // 唛头 - 货物序号
    private String cargoMarksSeqNo;
    // 唛头
    private String cargoMarks;

    // =================================== 47 货物描述 ===============================================

    // 货物序号
    private String cargoDescSeqNo;
    // 货物描述
    private String cargoDesc;

    // =================================== 51 集装箱信息 ===============================================

    // 货物序号
    private String ctnCargoSeqNo;
    // 箱号
    private String ctnNo;
    // 铅封号
    private String ctnSealNo;
    // 集装箱尺寸类型
    private String ctnSizeAntType;
    // 箱状态
    private String ctnStatus;
    // 箱内货物件数
    private String ctnPackagesQuantity;
    // 箱内货重
    private String ctnNetWeight;
    // 箱皮重
    private String ctnTareWeight;
    // 箱内货物尺码
    private String ctnCargoMeasurement;
    // 前超
    private String ctnOverLengthFront;
    // 后超
    private String ctnOverLengthBack;
    // 左超
    private String ctnOverWidthLeft;
    // 右超
    private String ctnOverWidthRight;
    // 超高
    private String ctnOverHeight;
    // 贝位
    private String ctnStowageLocation;


    // =================================== 99 尾记录 ===============================================
    // 记录总数
    private String fileRecordTotal;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getSendingParty() {
        return sendingParty;
    }

    public void setSendingParty(String sendingParty) {
        this.sendingParty = sendingParty;
    }

    public String getOriginalMessage() {
        return originalMessage;
    }

    public void setOriginalMessage(String originalMessage) {
        this.originalMessage = originalMessage;
    }

    public String getOurResponse() {
        return ourResponse;
    }

    public void setOurResponse(String ourResponse) {
        this.ourResponse = ourResponse;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public String getFileFunction() {
        return fileFunction;
    }

    public void setFileFunction(String fileFunction) {
        this.fileFunction = fileFunction;
    }

    public String getSenderCode() {
        return senderCode;
    }

    public void setSenderCode(String senderCode) {
        this.senderCode = senderCode;
    }

    public String getReceiverCode() {
        return receiverCode;
    }

    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode;
    }

    public Date getFileCreateTime() {
        return fileCreateTime;
    }

    public void setFileCreateTime(Date fileCreateTime) {
        this.fileCreateTime = fileCreateTime;
    }

    public String getSenderPortCode() {
        return senderPortCode;
    }

    public void setSenderPortCode(String senderPortCode) {
        this.senderPortCode = senderPortCode;
    }

    public String getReceiverPortCode() {
        return receiverPortCode;
    }

    public void setReceiverPortCode(String receiverPortCode) {
        this.receiverPortCode = receiverPortCode;
    }

    public String getOtherReceiverCode() {
        return otherReceiverCode;
    }

    public void setOtherReceiverCode(String otherReceiverCode) {
        this.otherReceiverCode = otherReceiverCode;
    }

    public String getOtherReceiverCode2() {
        return otherReceiverCode2;
    }

    public void setOtherReceiverCode2(String otherReceiverCode2) {
        this.otherReceiverCode2 = otherReceiverCode2;
    }

    public String getVslVesselCode() {
        return vslVesselCode;
    }

    public void setVslVesselCode(String vslVesselCode) {
        this.vslVesselCode = vslVesselCode;
    }

    public String getVessel() {
        return vessel;
    }

    public void setVessel(String vessel) {
        this.vessel = vessel;
    }

    public String getNationalityCode() {
        return nationalityCode;
    }

    public void setNationalityCode(String nationalityCode) {
        this.nationalityCode = nationalityCode;
    }

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public Date getEtdArrivedDate() {
        return etdArrivedDate;
    }

    public void setEtdArrivedDate(Date etdArrivedDate) {
        this.etdArrivedDate = etdArrivedDate;
    }

    public Date getSailingDate() {
        return sailingDate;
    }

    public void setSailingDate(Date sailingDate) {
        this.sailingDate = sailingDate;
    }

    public String getDepartPortCode() {
        return departPortCode;
    }

    public void setDepartPortCode(String departPortCode) {
        this.departPortCode = departPortCode;
    }

    public String getDepartPort() {
        return departPort;
    }

    public void setDepartPort(String departPort) {
        this.departPort = departPort;
    }

    public String getNextCallingPortCode() {
        return nextCallingPortCode;
    }

    public void setNextCallingPortCode(String nextCallingPortCode) {
        this.nextCallingPortCode = nextCallingPortCode;
    }

    public String getNextCallingPort() {
        return nextCallingPort;
    }

    public void setNextCallingPort(String nextCallingPort) {
        this.nextCallingPort = nextCallingPort;
    }

    public String getShippingLineCode() {
        return shippingLineCode;
    }

    public void setShippingLineCode(String shippingLineCode) {
        this.shippingLineCode = shippingLineCode;
    }

    public String getShippingLine() {
        return shippingLine;
    }

    public void setShippingLine(String shippingLine) {
        this.shippingLine = shippingLine;
    }

    public String getBlNo() {
        return blNo;
    }

    public void setBlNo(String blNo) {
        this.blNo = blNo;
    }

    public String getPreVesselCode() {
        return preVesselCode;
    }

    public void setPreVesselCode(String preVesselCode) {
        this.preVesselCode = preVesselCode;
    }

    public String getPreVessel() {
        return preVessel;
    }

    public void setPreVessel(String preVessel) {
        this.preVessel = preVessel;
    }

    public String getPreVoyage() {
        return preVoyage;
    }

    public void setPreVoyage(String preVoyage) {
        this.preVoyage = preVoyage;
    }

    public String getPlaceCodeOfReceipt() {
        return placeCodeOfReceipt;
    }

    public void setPlaceCodeOfReceipt(String placeCodeOfReceipt) {
        this.placeCodeOfReceipt = placeCodeOfReceipt;
    }

    public String getPlaceOfReceipt() {
        return placeOfReceipt;
    }

    public void setPlaceOfReceipt(String placeOfReceipt) {
        this.placeOfReceipt = placeOfReceipt;
    }

    public String getLoadPortCode() {
        return loadPortCode;
    }

    public void setLoadPortCode(String loadPortCode) {
        this.loadPortCode = loadPortCode;
    }

    public String getLoadPort() {
        return loadPort;
    }

    public void setLoadPort(String loadPort) {
        this.loadPort = loadPort;
    }

    public String getDeliveryTerm() {
        return deliveryTerm;
    }

    public void setDeliveryTerm(String deliveryTerm) {
        this.deliveryTerm = deliveryTerm;
    }

    public String getPrepaidOfCollect() {
        return prepaidOfCollect;
    }

    public void setPrepaidOfCollect(String prepaidOfCollect) {
        this.prepaidOfCollect = prepaidOfCollect;
    }

    public Date getLoadDate() {
        return loadDate;
    }

    public void setLoadDate(Date loadDate) {
        this.loadDate = loadDate;
    }

    public String getQuarantineCode() {
        return quarantineCode;
    }

    public void setQuarantineCode(String quarantineCode) {
        this.quarantineCode = quarantineCode;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getDischargePortCode() {
        return dischargePortCode;
    }

    public void setDischargePortCode(String dischargePortCode) {
        this.dischargePortCode = dischargePortCode;
    }

    public String getDischargePort() {
        return dischargePort;
    }

    public void setDischargePort(String dischargePort) {
        this.dischargePort = dischargePort;
    }

    public String getDeliveryPlaceCode() {
        return deliveryPlaceCode;
    }

    public void setDeliveryPlaceCode(String deliveryPlaceCode) {
        this.deliveryPlaceCode = deliveryPlaceCode;
    }

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    public String getTransferPortCode() {
        return transferPortCode;
    }

    public void setTransferPortCode(String transferPortCode) {
        this.transferPortCode = transferPortCode;
    }

    public String getTransferPort() {
        return transferPort;
    }

    public void setTransferPort(String transferPort) {
        this.transferPort = transferPort;
    }

    public String getBlIssuePlaceCode() {
        return blIssuePlaceCode;
    }

    public void setBlIssuePlaceCode(String blIssuePlaceCode) {
        this.blIssuePlaceCode = blIssuePlaceCode;
    }

    public String getBlIssuePlace() {
        return blIssuePlace;
    }

    public void setBlIssuePlace(String blIssuePlace) {
        this.blIssuePlace = blIssuePlace;
    }

    public String getOptDischPort1Code() {
        return optDischPort1Code;
    }

    public void setOptDischPort1Code(String optDischPort1Code) {
        this.optDischPort1Code = optDischPort1Code;
    }

    public String getOptDischPort1() {
        return optDischPort1;
    }

    public void setOptDischPort1(String optDischPort1) {
        this.optDischPort1 = optDischPort1;
    }

    public String getOptDischPort2Code() {
        return optDischPort2Code;
    }

    public void setOptDischPort2Code(String optDischPort2Code) {
        this.optDischPort2Code = optDischPort2Code;
    }

    public String getOptDischPort2() {
        return optDischPort2;
    }

    public void setOptDischPort2(String optDischPort2) {
        this.optDischPort2 = optDischPort2;
    }

    public String getOptDischPort3Code() {
        return optDischPort3Code;
    }

    public void setOptDischPort3Code(String optDischPort3Code) {
        this.optDischPort3Code = optDischPort3Code;
    }

    public String getOptDischPort3() {
        return optDischPort3;
    }

    public void setOptDischPort3(String optDischPort3) {
        this.optDischPort3 = optDischPort3;
    }

    public String getOptDischPort4Code() {
        return optDischPort4Code;
    }

    public void setOptDischPort4Code(String optDischPort4Code) {
        this.optDischPort4Code = optDischPort4Code;
    }

    public String getOptDischPort4() {
        return optDischPort4;
    }

    public void setOptDischPort4(String optDischPort4) {
        this.optDischPort4 = optDischPort4;
    }

    public String getOptDischPort5Code() {
        return optDischPort5Code;
    }

    public void setOptDischPort5Code(String optDischPort5Code) {
        this.optDischPort5Code = optDischPort5Code;
    }

    public String getOptDischPort5() {
        return optDischPort5;
    }

    public void setOptDischPort5(String optDischPort5) {
        this.optDischPort5 = optDischPort5;
    }

    public String getFreightCode() {
        return freightCode;
    }

    public void setFreightCode(String freightCode) {
        this.freightCode = freightCode;
    }

    public String getFreightRemark() {
        return freightRemark;
    }

    public void setFreightRemark(String freightRemark) {
        this.freightRemark = freightRemark;
    }

    public String getPayableAtCode() {
        return payableAtCode;
    }

    public void setPayableAtCode(String payableAtCode) {
        this.payableAtCode = payableAtCode;
    }

    public String getPayableAt() {
        return payableAt;
    }

    public void setPayableAt(String payableAt) {
        this.payableAt = payableAt;
    }

    public String getFreightQuantity() {
        return freightQuantity;
    }

    public void setFreightQuantity(String freightQuantity) {
        this.freightQuantity = freightQuantity;
    }

    public String getFrChCurrency() {
        return frChCurrency;
    }

    public void setFrChCurrency(String frChCurrency) {
        this.frChCurrency = frChCurrency;
    }

    public String getFrChRate() {
        return frChRate;
    }

    public void setFrChRate(String frChRate) {
        this.frChRate = frChRate;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCollectPrepaid() {
        return collectPrepaid;
    }

    public void setCollectPrepaid(String collectPrepaid) {
        this.collectPrepaid = collectPrepaid;
    }

    public String getShipperCode() {
        return shipperCode;
    }

    public void setShipperCode(String shipperCode) {
        this.shipperCode = shipperCode;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getConsigneeCode() {
        return consigneeCode;
    }

    public void setConsigneeCode(String consigneeCode) {
        this.consigneeCode = consigneeCode;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getFirstNotifyCode() {
        return firstNotifyCode;
    }

    public void setFirstNotifyCode(String firstNotifyCode) {
        this.firstNotifyCode = firstNotifyCode;
    }

    public String getFirstNotify() {
        return firstNotify;
    }

    public void setFirstNotify(String firstNotify) {
        this.firstNotify = firstNotify;
    }

    public String getSecondNotifyCode() {
        return secondNotifyCode;
    }

    public void setSecondNotifyCode(String secondNotifyCode) {
        this.secondNotifyCode = secondNotifyCode;
    }

    public String getSecondNotify() {
        return secondNotify;
    }

    public void setSecondNotify(String secondNotify) {
        this.secondNotify = secondNotify;
    }

    public String getThirdNotifyCode() {
        return thirdNotifyCode;
    }

    public void setThirdNotifyCode(String thirdNotifyCode) {
        this.thirdNotifyCode = thirdNotifyCode;
    }

    public String getThirdNotify() {
        return thirdNotify;
    }

    public void setThirdNotify(String thirdNotify) {
        this.thirdNotify = thirdNotify;
    }

    public String getCargoSeqNo() {
        return cargoSeqNo;
    }

    public void setCargoSeqNo(String cargoSeqNo) {
        this.cargoSeqNo = cargoSeqNo;
    }

    public String getCargoCode() {
        return cargoCode;
    }

    public void setCargoCode(String cargoCode) {
        this.cargoCode = cargoCode;
    }

    public String getCargoQuantity() {
        return cargoQuantity;
    }

    public void setCargoQuantity(String cargoQuantity) {
        this.cargoQuantity = cargoQuantity;
    }

    public String getCargoPackagesKindCode() {
        return cargoPackagesKindCode;
    }

    public void setCargoPackagesKindCode(String cargoPackagesKindCode) {
        this.cargoPackagesKindCode = cargoPackagesKindCode;
    }

    public String getCargoPackagesKind() {
        return cargoPackagesKind;
    }

    public void setCargoPackagesKind(String cargoPackagesKind) {
        this.cargoPackagesKind = cargoPackagesKind;
    }

    public String getCargoGrossWeight() {
        return cargoGrossWeight;
    }

    public void setCargoGrossWeight(String cargoGrossWeight) {
        this.cargoGrossWeight = cargoGrossWeight;
    }

    public String getCargoNetWeight() {
        return cargoNetWeight;
    }

    public void setCargoNetWeight(String cargoNetWeight) {
        this.cargoNetWeight = cargoNetWeight;
    }

    public String getCargoMeasurement() {
        return cargoMeasurement;
    }

    public void setCargoMeasurement(String cargoMeasurement) {
        this.cargoMeasurement = cargoMeasurement;
    }

    public String getDangerousClass() {
        return dangerousClass;
    }

    public void setDangerousClass(String dangerousClass) {
        this.dangerousClass = dangerousClass;
    }

    public String getDangerousPage() {
        return dangerousPage;
    }

    public void setDangerousPage(String dangerousPage) {
        this.dangerousPage = dangerousPage;
    }

    public String getDangerousUndgNo() {
        return dangerousUndgNo;
    }

    public void setDangerousUndgNo(String dangerousUndgNo) {
        this.dangerousUndgNo = dangerousUndgNo;
    }

    public String getDangerousLabel() {
        return dangerousLabel;
    }

    public void setDangerousLabel(String dangerousLabel) {
        this.dangerousLabel = dangerousLabel;
    }

    public String getDangerousFlashPoint() {
        return dangerousFlashPoint;
    }

    public void setDangerousFlashPoint(String dangerousFlashPoint) {
        this.dangerousFlashPoint = dangerousFlashPoint;
    }

    public String getDangerousEmsNo() {
        return dangerousEmsNo;
    }

    public void setDangerousEmsNo(String dangerousEmsNo) {
        this.dangerousEmsNo = dangerousEmsNo;
    }

    public String getDangerousMfagNo() {
        return dangerousMfagNo;
    }

    public void setDangerousMfagNo(String dangerousMfagNo) {
        this.dangerousMfagNo = dangerousMfagNo;
    }

    public String getDangerousEmergencyContact() {
        return dangerousEmergencyContact;
    }

    public void setDangerousEmergencyContact(String dangerousEmergencyContact) {
        this.dangerousEmergencyContact = dangerousEmergencyContact;
    }

    public String getDangerousTemperatureId() {
        return dangerousTemperatureId;
    }

    public void setDangerousTemperatureId(String dangerousTemperatureId) {
        this.dangerousTemperatureId = dangerousTemperatureId;
    }

    public String getDangerousTemperatureSetting() {
        return dangerousTemperatureSetting;
    }

    public void setDangerousTemperatureSetting(String dangerousTemperatureSetting) {
        this.dangerousTemperatureSetting = dangerousTemperatureSetting;
    }

    public String getDangerousMinTemperature() {
        return dangerousMinTemperature;
    }

    public void setDangerousMinTemperature(String dangerousMinTemperature) {
        this.dangerousMinTemperature = dangerousMinTemperature;
    }

    public String getDangerousMaxTemperature() {
        return dangerousMaxTemperature;
    }

    public void setDangerousMaxTemperature(String dangerousMaxTemperature) {
        this.dangerousMaxTemperature = dangerousMaxTemperature;
    }

    public String getCargoMarksSeqNo() {
        return cargoMarksSeqNo;
    }

    public void setCargoMarksSeqNo(String cargoMarksSeqNo) {
        this.cargoMarksSeqNo = cargoMarksSeqNo;
    }

    public String getCargoMarks() {
        return cargoMarks;
    }

    public void setCargoMarks(String cargoMarks) {
        this.cargoMarks = cargoMarks;
    }

    public String getCargoDescSeqNo() {
        return cargoDescSeqNo;
    }

    public void setCargoDescSeqNo(String cargoDescSeqNo) {
        this.cargoDescSeqNo = cargoDescSeqNo;
    }

    public String getCargoDesc() {
        return cargoDesc;
    }

    public void setCargoDesc(String cargoDesc) {
        this.cargoDesc = cargoDesc;
    }

    public String getCtnCargoSeqNo() {
        return ctnCargoSeqNo;
    }

    public void setCtnCargoSeqNo(String ctnCargoSeqNo) {
        this.ctnCargoSeqNo = ctnCargoSeqNo;
    }

    public String getCtnNo() {
        return ctnNo;
    }

    public void setCtnNo(String ctnNo) {
        this.ctnNo = ctnNo;
    }

    public String getCtnSealNo() {
        return ctnSealNo;
    }

    public void setCtnSealNo(String ctnSealNo) {
        this.ctnSealNo = ctnSealNo;
    }

    public String getCtnSizeAntType() {
        return ctnSizeAntType;
    }

    public void setCtnSizeAntType(String ctnSizeAntType) {
        this.ctnSizeAntType = ctnSizeAntType;
    }

    public String getCtnStatus() {
        return ctnStatus;
    }

    public void setCtnStatus(String ctnStatus) {
        this.ctnStatus = ctnStatus;
    }

    public String getCtnPackagesQuantity() {
        return ctnPackagesQuantity;
    }

    public void setCtnPackagesQuantity(String ctnPackagesQuantity) {
        this.ctnPackagesQuantity = ctnPackagesQuantity;
    }

    public String getCtnNetWeight() {
        return ctnNetWeight;
    }

    public void setCtnNetWeight(String ctnNetWeight) {
        this.ctnNetWeight = ctnNetWeight;
    }

    public String getCtnTareWeight() {
        return ctnTareWeight;
    }

    public void setCtnTareWeight(String ctnTareWeight) {
        this.ctnTareWeight = ctnTareWeight;
    }

    public String getCtnCargoMeasurement() {
        return ctnCargoMeasurement;
    }

    public void setCtnCargoMeasurement(String ctnCargoMeasurement) {
        this.ctnCargoMeasurement = ctnCargoMeasurement;
    }

    public String getCtnOverLengthFront() {
        return ctnOverLengthFront;
    }

    public void setCtnOverLengthFront(String ctnOverLengthFront) {
        this.ctnOverLengthFront = ctnOverLengthFront;
    }

    public String getCtnOverLengthBack() {
        return ctnOverLengthBack;
    }

    public void setCtnOverLengthBack(String ctnOverLengthBack) {
        this.ctnOverLengthBack = ctnOverLengthBack;
    }

    public String getCtnOverWidthLeft() {
        return ctnOverWidthLeft;
    }

    public void setCtnOverWidthLeft(String ctnOverWidthLeft) {
        this.ctnOverWidthLeft = ctnOverWidthLeft;
    }

    public String getCtnOverWidthRight() {
        return ctnOverWidthRight;
    }

    public void setCtnOverWidthRight(String ctnOverWidthRight) {
        this.ctnOverWidthRight = ctnOverWidthRight;
    }

    public String getCtnOverHeight() {
        return ctnOverHeight;
    }

    public void setCtnOverHeight(String ctnOverHeight) {
        this.ctnOverHeight = ctnOverHeight;
    }

    public String getCtnStowageLocation() {
        return ctnStowageLocation;
    }

    public void setCtnStowageLocation(String ctnStowageLocation) {
        this.ctnStowageLocation = ctnStowageLocation;
    }

    public String getFileRecordTotal() {
        return fileRecordTotal;
    }

    public void setFileRecordTotal(String fileRecordTotal) {
        this.fileRecordTotal = fileRecordTotal;
    }
}
