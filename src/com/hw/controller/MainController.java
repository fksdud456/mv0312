package com.hw.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	String[] city = { "서울", "부산", "대구" , "인천", "광주", "대전", "울산", "세종" };
	Connection conn;

	String statement_air = "SELECT Year, Month, COUNT(*) " + "FROM airline_delay " + "WHERE delayYear=2006 "
			+ "AND Month IN(1,2,3,4) " + "AND ArrDelay>0 " + "GROUP BY Year, Month";

	String stmt_airavg = "SELECT Year, Month, AVG(ArrDelay) FROM airline_delay " + "WHERE DelayYear AND "
			+ "Month in (1,2,3,4) GROUP BY Year, Month ";

	String cafe = "SELECT City, COUNT(Zipcode) FROM restaurant " + "GROUP BY BusinessName, City";
	String cafe_namyangju = "SELECT Placename, TotalSize FROM restaurant "
			+ "WHERE City = '남양주시' AND PermitDate > to_date('2013-01-01') ORDER BY TotalSize DESC";

	String crime_main = "select round(a.totalseoul/b.seoul*100000,1), round(a.totalbusan/b.pusan*100000,1), "
			+ "round(a.totaldaegu/b.daegu*100000,1), round(a.totalincheon/b.incheon*100000,1), "
			+ "round(a.totalgwangju/b.gwangju*100000,1), round(a.totaldaejeon/b.daejeon*100000,1), "
			+ "round(a.totalulsan/b.ulsan*100000,1), round(a.totalsaejong/b.saejong*100000,1) "
			+ "from (select sum(seoul) totalseoul, sum(pusan) totalbusan, sum(daegu) totaldaegu, sum(incheon) totalincheon, sum(gwangju) totalgwangju, "
			+ "      sum(daejeon) totaldaejeon, sum(ulsan) totalulsan, sum(saejong) totalsaejong, year "
			+ "      from criminal " + "      where year=2016 group by year) A " + "inner join population B "
			+ "on a.year=b.year";

	String crime = "select A.large, " + "ROUND((A.seoul/B.seoul*100000),5) seoul, "
			+ "ROUND((A.pusan/B.pusan*100000),5) pusan, " + "ROUND((A.daegu/B.daegu*100000),5) daegu "
			+ "FROM (select large_sec large," + "SUM(seoul) seoul, " + "SUM(pusan) pusan, " + "SUM(daegu) daegu, "
			+ "year FROM criminal GROUP BY large_sec, YEAR)" + "A JOIN population B On A.year = B.year";

	// 생성자
	public MainController() {
		try {
			Class.forName("org.apache.hive.jdbc.HiveDriver");
			conn = DriverManager.getConnection("jdbc:hive2://192.168.111.100:10000/default", "root", "111111");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/main.do")
	public String main() {
		return "main";
	}

	@RequestMapping("/chart1.do")
	public String chart1(Model m) {
		m.addAttribute("center", "chart1");
		return "main";
	}

	@RequestMapping("/chart2.do")
	public String chart2(Model m) {
		m.addAttribute("center", "chart2");
		return "main";
	}

	@RequestMapping("/chart3.do")
	public String chart3(Model m) {
		m.addAttribute("center", "chart3");
		return "main";
	}

	@RequestMapping("/chart4.do")
	public String chart4(Model m) {
		m.addAttribute("center", "chart4");
		return "main";
	}

	// spring 요청하면 무조건 페이지 이동하는 게 정상,인데
	// 이건 그냥 data만 전송하겠다고
	@RequestMapping("/chart1impl.do")
	// Data를 output으로 전송하겠다.고 들어가야돼
	@ResponseBody
	public void chart1impl(HttpServletResponse res) throws Exception {
		System.out.println("MainController chart1imple");

		res.setCharacterEncoding("EUC-KR");
		res.setContentType("application/json");

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(statement_air);
		PrintWriter out = res.getWriter();
		JSONArray ja = new JSONArray();

		while (rs.next()) {
			// ["1월", 2000]
			JSONArray data = new JSONArray();
			data.add(rs.getInt(2) + "월");
			data.add(rs.getInt(3));
			ja.add(data);
		}
		System.out.println(ja.toJSONString());
		out.print(ja.toJSONString());
		out.close();
	}

	@RequestMapping("/chart2impl.do")
	@ResponseBody
	public void chart1imp2(HttpServletResponse res) throws Exception {
		System.out.println("MainController chart2imple");

		res.setCharacterEncoding("EUC-KR");
		res.setContentType("application/json");

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(cafe_namyangju);

		PrintWriter out = res.getWriter();
		JSONArray ja = new JSONArray();

		for (int cityIndex = 0; cityIndex < 2; cityIndex++) {
			JSONObject data = new JSONObject();
			data.put("name", city[cityIndex]);
			data.put("id", city[cityIndex]);
			JSONArray crimes = new JSONArray();
			JSONArray crime;
			while (rs.next()) {
				crime = new JSONArray();
				crime.add(rs.getString(1));
				crime.add(rs.getInt(2));
				crimes.add(crime);
			}
			data.put("data", crimes);

		}

		out.print(ja.toJSONString());
		out.close();
	}

	@RequestMapping("/chart3impl.do")
	@ResponseBody
	public void chart3impl(HttpServletResponse res) throws Exception {
		System.out.println("MainController chart3impl");

		res.setCharacterEncoding("EUC-KR");
		res.setContentType("application/json");

		Statement stmt = conn.createStatement();

		PrintWriter out = res.getWriter();
		JSONArray ja = new JSONArray();

		JSONObject mainjo = new JSONObject();
		mainjo.put("name", "범죄");
		mainjo.put("colorByPoint", true);

		ResultSet rs = stmt.executeQuery(crime_main);

		JSONArray cityCrime = new JSONArray();
		JSONObject cityTotal;
		while (rs.next()) {
			for (int cityIndex = 0; cityIndex < city.length; cityIndex++) {
				cityTotal = new JSONObject();
				cityTotal.put("name", city[cityIndex]);
				cityTotal.put("drilldown", city[cityIndex]);
				cityTotal.put("y", rs.getInt(cityIndex + 1));
				cityCrime.add(cityTotal);
			}
		}
		mainjo.put("data", cityCrime);

		ja.add(mainjo);
		System.out.println(ja.toJSONString());
		out.print(ja.toJSONString());
		out.close();
	}

	@RequestMapping("/chart3impl2.do")
	@ResponseBody
	public void chart3impl2(HttpServletResponse res) throws Exception {
		System.out.println("MainController chart3impl2");

		res.setCharacterEncoding("EUC-KR");
		res.setContentType("application/json");

		Statement stmt = conn.createStatement();

		PrintWriter out = res.getWriter();

		ResultSet rs = stmt.executeQuery(crime);

		JSONArray cities = new JSONArray();
		JSONObject cityObj;
		for (int cityIndex = 0; cityIndex < 3; cityIndex++) {
			cityObj = new JSONObject();
			cityObj.put("name", city[cityIndex]);
			cityObj.put("id", city[cityIndex]);
			cities.add(cityObj);
		}

		JSONArray crimesCity = new JSONArray();
		JSONArray crimes = null;
		for (int cityIndex = 0; cityIndex < 3; cityIndex++) {
			crimes = new JSONArray();
			crimesCity.add(crimes);
		}
		
		JSONArray crime = null;
		while (rs.next()) {
			for (int cityIndex = 0; cityIndex < 3; cityIndex++) {
				crime = new JSONArray();
				crime.add(rs.getString(1));
				crime.add(rs.getInt(cityIndex + 2));
				crimes = (JSONArray) crimesCity.get(cityIndex);
				crimes.add(crime);
				cityObj = (JSONObject) cities.get(cityIndex);
				cityObj.put("data", crimes);
			}
		}
		out.print(cities.toJSONString());
		out.close();
	}

}