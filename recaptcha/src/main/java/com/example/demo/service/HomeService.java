package com.example.demo.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.util.CaptchaSettings;

@Service
public class HomeService {
	
	@Autowired
	private CaptchaSettings captchaSettings;

	
	/**
	 * @param recaptcha
	 * @return
	 */
	public boolean verifyRecaptcha(String recaptcha) {
      	
    	final String SECRET_KEY = captchaSettings.getSecret();
    	final String RE_URL = captchaSettings.getUrl();
    	
		try {
			URL obj = new URL(RE_URL);
			HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("POST");
			
			String postParams = "secret=" + SECRET_KEY + "&response=" + recaptcha;
			con.setDoOutput(true);
			
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			JsonReader jsonReader = Json.createReader(new StringReader(response.toString()));
			JsonObject jsonObject = jsonReader.readObject();
			jsonReader.close();
			return jsonObject.getBoolean("success"); //true or false
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	/**
	 * @param workbook
	 * @param layout
	 * @return
	 */
	public CellStyle excelStyle(Workbook workbook,String layout){

		CellStyle cellStyle = null;

		if(layout.equals("head")){
			CellStyle headStyle = workbook.createCellStyle();//테이블 헤더 스타일
			// 가는 경계선을 가집니다.
			cellStyle.setBorderTop(BorderStyle.THIN);
			cellStyle.setBorderBottom(BorderStyle.THIN);
			cellStyle.setBorderLeft(BorderStyle.THIN);
			cellStyle.setBorderRight(BorderStyle.THIN);

			// 배경색은 노란색입니다.
			cellStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
			cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			// 데이터는 가운데 정렬합니다.
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			return cellStyle;
		}else{
			cellStyle = workbook.createCellStyle();
			cellStyle.setBorderTop(BorderStyle.THIN);
			cellStyle.setBorderBottom(BorderStyle.THIN);
			cellStyle.setBorderLeft(BorderStyle.THIN);
			cellStyle.setBorderRight(BorderStyle.THIN);
			return cellStyle;
		}
	}
	
}//end class
