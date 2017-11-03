package com.mlink.util;

import java.io.IOException;
import java.math.BigDecimal;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.Base64Utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ExchangeUtil {
	// recommend way to execute http
	public static String get(String url) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(url);
			RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000)
					.setConnectionRequestTimeout(5000).build();
			httpget.setConfig(config);
			httpget.setHeader("apikey", "dc1012914056a625dfd735f4be8e1ebc");
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
					// TODO Auto-generated method stub
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};
			String responsebody = httpclient.execute(httpget, responseHandler);
			System.out.println("-----------------------------------------------------");
			System.out.println(responsebody);
			return responsebody;
		} finally {
			httpclient.close();
		}
	}

	public static String post(String url, String jsondata) throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(url);
			StringEntity entity = new StringEntity(jsondata, ContentType.APPLICATION_JSON);
			httppost.setEntity(entity);
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
					// TODO Auto-generated method stub
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};
			String responsebody = httpclient.execute(httppost, responseHandler);
			System.out.println("-------------------");
			System.out.println(responsebody);
			return responsebody;
		} finally {
			httpclient.close();
		}
	}

	public static void main(String[] args) {
		String url = "http://apis.baidu.com/apistore/currencyservice/currency?fromCurrency=USD&toCurrency=CNY&amount=1";

		url = "http://api.k780.com:88/?app=finance.rate_cnyquot&curno=USD&appkey=21231&sign=f8e9e0fa28f0b930c1fce12daeccf793&format=json";

		try {
			String json = ExchangeUtil.get(url);
			JsonElement element = new Gson().fromJson(json, JsonElement.class);
			String code = element.getAsJsonObject().get("success").getAsString();
			if ("1".equals(code)) {
				String sell = element.getAsJsonObject().getAsJsonObject("result").getAsJsonObject("USD")
						.getAsJsonObject("BOC").get("se_sell").getAsString();
				System.out.println(sell);
				BigDecimal currency=new BigDecimal(sell);
				System.out.println(currency.movePointLeft(2).toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
