package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.dao.DaoImpl;
import com.model.TestCase;

//import com.sun.javafx.collections.MappingChange.Map;

@SuppressWarnings("serial")
public class WechatServlet extends HttpServlet {

	private String appid = "wxcc0c2954205d7829";
	private String secretKey = "64d08f94165ab130d3b49af89c7486cd";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet--");
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取操作类型，根据类型执行不同操作
		String operFlag = request.getParameter("operFlag");
		String results = "";
		if ("getOpenid".equals(operFlag)) {
			String code = request.getParameter("code"); // 拿到微信小程序传过来的code
			String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secretKey
					+ "&js_code=" + code + "&grant_type=authorization_code"; // 接口地址
			System.out.println("url" + url);
			results = sendGetReq(url).toString();// 发送http请求
			System.out.println("results" + results);

			Gson gson = new Gson();
			TestCase weChatSession = gson.fromJson(results, TestCase.class);
			String openid = weChatSession.getId();
			System.out.println(openid);
			DaoImpl daoImpl = new DaoImpl();
			daoImpl.addWxId(openid);
		}

		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("catch-control", "no-catch");
		PrintWriter out = response.getWriter();
		out.write(results);
		out.flush();
		out.close();
	}

	private String sendGetReq(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			java.util.Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		} // 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
}
