package com.ling.websocket;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.jcraft.jsch.ChannelSftp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

@SpringBootTest
class WebsocketApplicationTests {

    @Test
    void contextLoads() throws Exception {
        SftpClientUtil client = new SftpClientUtil("192.168.65.130", "sftpuser", "123456", 22, "UTF-8");
        while (true) {
            Thread.sleep(10000);
            String dir = "/upload";
            Vector vector = client.listFiles(dir);
            for (Object o : vector) {
                ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) o;
                if (".".equals(entry.getFilename()) || "..".equals(entry.getFilename())) {
                    continue;
                }
                String path = dir + "/" + entry.getFilename();
                System.out.println(path);
                // 下载文件并读取内容
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getSftp().get(path)));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();
                client.getSftp().rm(path);
                System.out.println(content);
                VslContainerMessagesBo bo = new VslContainerMessagesBo();
                bo.setOriginalMessage(content.toString());
                parseMessage(bo);
                System.out.println("JSONUtil.toJsonStr(vslContainerMessagesBo) = " + JSONUtil.toJsonStr(bo));
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SftpClientUtil client = new SftpClientUtil("192.168.65.130", "sftpuser", "123456", 22, "UTF-8");
        while (true) {
            Thread.sleep(10000);
            String dir = "/upload";
            Vector vector = client.listFiles(dir);
            for (Object o : vector) {
                ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) o;
                if (".".equals(entry.getFilename()) || "..".equals(entry.getFilename())) {
                    continue;
                }
                String path = dir + "/" + entry.getFilename();
                System.out.println(path);
                // 下载文件并读取内容
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getSftp().get(path)));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();
                client.getSftp().rm(path);
                System.out.println(content);
                VslContainerMessagesBo bo = new VslContainerMessagesBo();
                bo.setOriginalMessage(content.toString());
                parseMessage(bo);
                System.out.println("JSONUtil.toJsonStr(vslContainerMessagesBo) = " + JSONUtil.toJsonStr(bo));
            }
        }}


    /**
     * 解析报文
     * @param bo
     */
    public static void parseMessage(VslContainerMessagesBo bo) {
        if (StrUtil.isBlank(bo.getOriginalMessage())) {
            return;
        }
        String[] lines = bo.getOriginalMessage().split("\\n");
        StringBuffer cargoMarksSeqNo = new StringBuffer();
        StringBuffer cargoMarks = new StringBuffer();
        StringBuffer cargoDescSeqNo = new StringBuffer();
        StringBuffer cargoDesc = new StringBuffer();
        for (String line : lines) {
            String[] fields = line.split(":");
            if (line.startsWith("00")) {
                if (fields.length > 1) bo.setMessageType(fields[1]);
                if (fields.length > 2) bo.setFileDescription(fields[2]);
                if (fields.length > 3) bo.setFileFunction(fields[3]);
                if (fields.length > 4) bo.setSenderCode(fields[4]);
                if (fields.length > 5) bo.setReceiverCode(fields[5]);
                if (fields.length > 6) bo.setFileCreateTime(Convert.toDate(fields[6]));
                if (fields.length > 7) bo.setSenderPortCode(fields[7]);
                if (fields.length > 8) bo.setReceiverCode(fields[8]);
            }else if (line.startsWith("10")) {
                if (fields.length > 1) bo.setVslVesselCode(fields[1]);
                if (fields.length > 2) bo.setVessel(fields[2]);
                if (fields.length > 3) bo.setNationalityCode(fields[3]);
                if (fields.length > 4) bo.setVoyage(fields[4]);
                if (fields.length > 5) bo.setTradeCode(fields[5]);
                if (fields.length > 6) bo.setTrade(fields[6]);
                if (fields.length > 7) bo.setEtdArrivedDate(Convert.toDate(fields[7]));
                if (fields.length > 8) bo.setSailingDate(Convert.toDate(fields[8]));
                if (fields.length > 9) bo.setDepartPortCode(fields[9]);
                if (fields.length > 10) bo.setDepartPort(fields[10]);
                if (fields.length > 11) bo.setNextCallingPortCode(fields[11]);
                if (fields.length > 12) bo.setNextCallingPort(fields[12]);
            } else if (line.startsWith("11")) {
                if (fields.length > 1) bo.setShippingLineCode(fields[1]);
                if (fields.length > 2) bo.setShippingLine(fields[2]);
            }else if (line.startsWith("12")) {
                if (fields.length > 1) bo.setBlNo(fields[1]);
                if (fields.length > 2) bo.setPreVesselCode(fields[2]);
                if (fields.length > 3) bo.setPreVessel(fields[3]);
                if (fields.length > 4) bo.setPreVoyage(fields[4]);
                if (fields.length > 5) bo.setPlaceCodeOfReceipt(fields[5]);
                if (fields.length > 6) bo.setPlaceOfReceipt(fields[6]);
                if (fields.length > 7) bo.setLoadPortCode(fields[7]);
                if (fields.length > 8) bo.setLoadPort(fields[8]);
                if (fields.length > 9) bo.setDeliveryTerm(fields[9]);
                if (fields.length > 10) bo.setPrepaidOfCollect(fields[10]);
                if (fields.length > 11) bo.setLoadDate(Convert.toDate(fields[11]));
                if (fields.length > 12) bo.setQuarantineCode(fields[12]);
                if (fields.length > 13) bo.setIssueDate(Convert.toDate(fields[13]));
                if (fields.length > 14) bo.setCurrency(fields[14]);
                if (fields.length > 15) bo.setExchangeRate(fields[15]);
            } else if (line.startsWith("13")) {
                if (fields.length > 1) bo.setDischargePortCode(fields[1]);
                if (fields.length > 2) bo.setDischargePort(fields[2]);
                if (fields.length > 3) bo.setDeliveryPlaceCode(fields[3]);
                if (fields.length > 4) bo.setDeliveryPlace(fields[4]);
                if (fields.length > 5) bo.setTransferPortCode(fields[5]);
                if (fields.length > 6) bo.setTransferPort(fields[6]);
                if (fields.length > 7) bo.setBlIssuePlaceCode(fields[7]);
                if (fields.length > 8) bo.setBlIssuePlace(fields[8]);
            } else if (line.startsWith("14")) {
                if (fields.length > 1) bo.setOptDischPort1Code(fields[1]);
                if (fields.length > 2) bo.setOptDischPort1(fields[2]);
                if (fields.length > 3) bo.setOptDischPort2Code(fields[3]);
                if (fields.length > 4) bo.setOptDischPort2(fields[4]);
                if (fields.length > 5) bo.setOptDischPort3Code(fields[5]);
                if (fields.length > 6) bo.setOptDischPort3(fields[6]);
                if (fields.length > 7) bo.setOptDischPort4Code(fields[7]);
                if (fields.length > 8) bo.setOptDischPort4(fields[8]);
                if (fields.length > 9) bo.setOptDischPort5Code(fields[9]);
                if (fields.length > 10) bo.setOptDischPort5(fields[10]);
            }
            else if (line.startsWith("15")) {
                if (fields.length > 1) bo.setFreightCode(fields[1]);
                if (fields.length > 2) bo.setFreightRemark(fields[2]);
                if (fields.length > 3) bo.setPayableAtCode(fields[3]);
                if (fields.length > 4) bo.setPayableAt(fields[4]);
                if (fields.length > 5) bo.setFreightQuantity(fields[5]);
                if (fields.length > 6) bo.setFrChCurrency(fields[6]);
                if (fields.length > 7) bo.setFrChRate(fields[7]);
                if (fields.length > 8) bo.setUnit(fields[8]);
                if (fields.length > 9) bo.setAmount(fields[9]);
                if (fields.length > 10) bo.setCollectPrepaid(fields[10]);
            } else if (line.startsWith("16")) {
                if (fields.length > 1) bo.setShipperCode(fields[1]);
                if (fields.length > 2) bo.setShipper(fields[2]);
            }else if (line.startsWith("17")) {
                if (fields.length > 1) bo.setConsigneeCode(fields[1]);
                if (fields.length > 2) bo.setConsignee(fields[2]);
            }else if (line.startsWith("18")) {
                if (fields.length > 1) bo.setFirstNotifyCode(fields[1]);
                if (fields.length > 2) bo.setFirstNotify(fields[2]);
            }else if (line.startsWith("19")) {
                if (fields.length > 1) bo.setSecondNotifyCode(fields[1]);
                if (fields.length > 2) bo.setSecondNotify(fields[2]);
            }else if (line.startsWith("20")) {
                if (fields.length > 1) bo.setThirdNotifyCode(fields[1]);
                if (fields.length > 2) bo.setThirdNotify(fields[2]);
            }else if (line.startsWith("41")) {
                if (fields.length > 1) bo.setCargoSeqNo(fields[1]);
                if (fields.length > 2) bo.setCargoCode(fields[2]);
                if (fields.length > 3) bo.setCargoQuantity(fields[3]);
                if (fields.length > 4) bo.setCargoPackagesKindCode(fields[4]);
                if (fields.length > 5) bo.setCargoPackagesKind(fields[5]);
                if (fields.length > 6) bo.setCargoGrossWeight(fields[6]);
                if (fields.length > 7) bo.setCargoNetWeight(fields[7]);
                if (fields.length > 8) bo.setCargoMeasurement(fields[8]);
            }else if (line.startsWith("43")) {
                if (fields.length > 1) bo.setDangerousClass(fields[1]);
                if (fields.length > 2) bo.setDangerousPage(fields[2]);
                if (fields.length > 3) bo.setDangerousUndgNo(fields[3]);
                if (fields.length > 4) bo.setDangerousLabel(fields[4]);
                if (fields.length > 5) bo.setDangerousFlashPoint(fields[5]);
                if (fields.length > 6) bo.setDangerousEmsNo(fields[6]);
                if (fields.length > 7) bo.setDangerousMfagNo(fields[7]);
                if (fields.length > 8) bo.setDangerousEmergencyContact(fields[8]);
                if (fields.length > 9) bo.setDangerousTemperatureId(fields[9]);
                if (fields.length > 10) bo.setDangerousTemperatureSetting(fields[10]);
                if (fields.length > 11) bo.setDangerousMinTemperature(fields[11]);
                if (fields.length > 12) bo.setDangerousMaxTemperature(fields[12]);
            }else if (line.startsWith("44")) {
                fields = line.split(":",3);
                if (fields.length > 1) cargoMarksSeqNo.append(fields[1]);
                if (fields.length > 2) cargoMarks.append(fields[2]);
            }else if (line.startsWith("47")) {
                fields = line.split(":",3);
                if (fields.length > 1) cargoDescSeqNo.append(fields[1]);
                if (fields.length > 2) cargoDesc.append(fields[2]);
            }else if (line.startsWith("51")) {
                if (fields.length > 1) bo.setCtnCargoSeqNo(fields[1]);
                if (fields.length > 2) bo.setCtnNo(fields[2]);
                if (fields.length > 3) bo.setCtnSealNo(fields[3]);
                if (fields.length > 4) bo.setCtnSizeAntType(fields[4]);
                if (fields.length > 5) bo.setCtnStatus(fields[5]);
                if (fields.length > 6) bo.setCtnPackagesQuantity(fields[6]);
                if (fields.length > 7) bo.setCtnNetWeight(fields[7]);
                if (fields.length > 8) bo.setCtnTareWeight(fields[8]);
                if (fields.length > 9) bo.setCtnCargoMeasurement(fields[9]);
                if (fields.length > 10) bo.setCtnOverLengthFront(fields[10]);
                if (fields.length > 11) bo.setCtnOverLengthBack(fields[11]);
                if (fields.length > 12) bo.setCtnOverWidthLeft(fields[12]);
                if (fields.length > 13) bo.setCtnOverWidthRight(fields[13]);
                if (fields.length > 14) bo.setCtnOverHeight(fields[14]);
                if (fields.length > 15) bo.setCtnStowageLocation(fields[15]);
            }else if (line.startsWith("51")) {
                if (fields.length > 1) bo.setFileRecordTotal(fields[1]);
            }
        }
        bo.setCargoMarksSeqNo(cargoMarksSeqNo.toString());
        bo.setCargoMarks(cargoMarks.toString());
        bo.setCargoDescSeqNo(cargoDescSeqNo.toString());
        bo.setCargoDesc(cargoDesc.toString());
    }






}
