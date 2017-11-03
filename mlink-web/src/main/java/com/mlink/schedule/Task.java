package com.mlink.schedule;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mlink.service.IExchangeService;
import com.mlink.util.ExchangeUtil;
import com.mlink.view.MlinkExchange;

@Component
public class Task {

	private Log log = LogFactory.getLog(getClass());

	@Autowired
	private IExchangeService exchangeService;

	@Scheduled(cron = "0 0/5 * * * ? ")
	public void exchange() {
		try {
			String info = ExchangeUtil.get(
					"http://api.k780.com:88/?app=finance.rate_cnyquot&curno=USD&appkey=21231&sign=f8e9e0fa28f0b930c1fce12daeccf793&format=json");
			JsonElement element = new Gson().fromJson(info, JsonElement.class);
			String code = element.getAsJsonObject().get("success").getAsString();
			if ("1".equals(code)) {
				String sell = element.getAsJsonObject().getAsJsonObject("result").getAsJsonObject("USD")
						.getAsJsonObject("BOC").get("se_sell").getAsString();
				
				String update=element.getAsJsonObject().getAsJsonObject("result").getAsJsonObject("USD")
						.getAsJsonObject("BOC").get("upddate").getAsString();
				
				BigDecimal sellbg = new BigDecimal(sell);
				MlinkExchange exchange = new MlinkExchange();
				exchange.setId(UUID.randomUUID().toString());
				exchange.setFromcurrency("USD");
				exchange.setTocurrency("CNY");
				exchange.setExchange(sellbg.movePointLeft(2).doubleValue());
				exchange.setUpdatetime(Timestamp.valueOf(update));
				exchange.setCreatetime(new Timestamp(System.currentTimeMillis()));
				exchangeService.save(exchange);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			try {
				String info = ExchangeUtil.get(
						"http://apis.baidu.com/apistore/currencyservice/currency?fromCurrency=USD&toCurrency=CNY&amount=1");
				Code code = new Gson().fromJson(info, Code.class);
				if (code != null) {
					MlinkExchange exchange = new MlinkExchange();
					exchange.setId(UUID.randomUUID().toString());
					exchange.setFromcurrency(code.getRetData().getFromCurrency());
					exchange.setTocurrency(code.getRetData().getToCurrency());
					exchange.setExchange(code.getRetData().getCurrency());
					exchange.setCreatetime(new Timestamp(System.currentTimeMillis()));
					exchangeService.save(exchange);
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}

class Code {
	private int errNum;
	private String errMsg;
	private Currency retData;

	public int getErrNum() {
		return errNum;
	}

	public void setErrNum(int errNum) {
		this.errNum = errNum;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public Currency getRetData() {
		return retData;
	}

	public void setRetData(Currency retData) {
		this.retData = retData;
	}

	@Override
	public String toString() {
		return "Code [errNum=" + errNum + ", errMsg=" + errMsg + ", retData=" + retData + "]";
	}

}

class Currency {
	private String date;
	private String time;
	private String fromCurrency;
	private int amount;
	private String toCurrency;
	private double currency;
	private double convertedamount;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public double getCurrency() {
		return currency;
	}

	public void setCurrency(double currency) {
		this.currency = currency;
	}

	public double getConvertedamount() {
		return convertedamount;
	}

	public void setConvertedamount(double convertedamount) {
		this.convertedamount = convertedamount;
	}

	@Override
	public String toString() {
		return "Currency [date=" + date + ", time=" + time + ", fromCurrency=" + fromCurrency + ", amount=" + amount
				+ ", toCurrency=" + toCurrency + ", currency=" + currency + ", convertedamount=" + convertedamount
				+ "]";
	}

}

class K780 {
	private String success;
	private K780result result;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public K780result getResult() {
		return result;
	}

	public void setResult(K780result result) {
		this.result = result;
	}

}

class K780result {
	private String status;
	private String scur;
	private String tcur;
	private String ratenm;
	private String rate;
	private String update;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getScur() {
		return scur;
	}

	public void setScur(String scur) {
		this.scur = scur;
	}

	public String getTcur() {
		return tcur;
	}

	public void setTcur(String tcur) {
		this.tcur = tcur;
	}

	public String getRatenm() {
		return ratenm;
	}

	public void setRatenm(String ratenm) {
		this.ratenm = ratenm;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

}