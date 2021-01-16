package com.wefive.goverment.controller;
import static org.hamcrest.CoreMatchers.nullValue;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wefive.goverment.entity.DepartmentEntity;
import com.wefive.goverment.service.DepartmentService;
 
 
public class Getapi{
	public String mainfun(String info) throws IOException {
    	JSONObject json1=new JSONObject();
        //请求的region
        String region = "cn-north-4";
        //用户名
        String username = "Wanghan";
        //用户密码
        String password = "wanghan817";
        //账户名
        String userName = "WUTwanghan";
        //请求的url接口
        String url = "https://e88514a5e4b848fd8d191a1ee0e3b39e.apig.cn-north-4.huaweicloudapis.com/v1/infers/9befe690-304f-440f-aaad-3fa384d4c79a";
        //获取用token
        String token = getToken(region,username,password,userName);
        //String param="身份证";
        json1.put("text", info);
        String data=getAPI(token, json1, url);
        
        //System.out.println(data);
        //String dataString="{\"predicted_label\": \"部门\", \"scores\": [[\"业务\", \"0.930\"], [\"部门\", \"0.070\"]]}";
        String pattern="[\\u4e00-\\u9fa5]{2}";
        Pattern r=Pattern.compile(pattern);
        Matcher m = r.matcher(data);
        String s = null;
        while (m.find()) {
        	s=m.group();
        	break;
			//System.out.println(m.group());
		}
        //System.out.println(s);
        return s;
    }
 
    //获取请求链接中用户的token信息
    public static String getToken(String region,String username,String password,String userName) throws IOException {
        String iam = "https://iam."+region+".myhuaweicloud.com/v3/auth/tokens";
        String param = "{\"auth\":{\"identity\":{\"methods\":[\"password\"],\"password\":{\"user\":{\"name\":\"" + username + "\",\"password\":\"" + password + "\",\"domain\":{\"name\":\"" + userName + "\"}}}},\"scope\":{\"project\":{\"id\":\"0a3f43716480f4a02f65c0048ec98d0e\",\"name\":\"cn-north-4\"}}}}";
        PrintWriter out;
        BufferedReader in = null;
        String token = "";
        String response = "";
        try {
            //需要请求的url
            URL url = new URL(iam);
            //打开和URL之间的连接
            URLConnection connection = url.openConnection();
            //设置通用的请求属性，请求头部分
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0");
            // 发送POST请求必须设置如下两行
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(true);
            // 建立实际的连接
            connection.connect();
            ///获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            //发送请求参数
            out.write(param);
            //flush输出流的缓冲
            out.flush();
            //获取相应头中的token信息
            token = connection.getHeaderField("X-Subject-Token");
            //定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                //换行打印获取结果
                response += "\n" + line;
            }
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            /*for (String key : map.keySet()) {
                //打印出相应头中的信息
                //System.out.println(key + "--->" + map.get(key));
            }*/
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        //返回所要用到的token信息
        return token;
    }
 
    //请求云上接口，调用接口服务
    public static String getAPI(String token,JSONObject jsonObject,String uri) throws IOException {
    	String orc=uri;
        StringBuffer sBuffer=new StringBuffer();
        BufferedReader in = null;
        try {
            URL url = new URL(orc);
            HttpURLConnection connection =(HttpURLConnection) url.openConnection();
            //容易载跟头，表明请求体的部分为json形式
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Auth-Token", token);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            //转换为字节数组
            byte[] data=(jsonObject.toString()).getBytes();
            // 设置文件长度
            connection.setRequestProperty("Content-Length", String.valueOf(data.length));
            connection.connect();
            OutputStream out = new DataOutputStream(connection.getOutputStream()) ;
            out.write((jsonObject.toString()).getBytes());
            out.flush();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()){
				System.out.println("连接成功");
				// 请求返回的数据
				InputStream in1 = connection.getInputStream();
				try {
				      String readLine=new String();
				      BufferedReader responseReader=new BufferedReader(new InputStreamReader(in1,"UTF-8"));
				      while((readLine=responseReader.readLine())!=null){
				        sBuffer.append(readLine).append("\n");
				      }
				      responseReader.close();
				      //System.out.println(sBuffer.toString());
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} 
            else {
            	System.out.println("error++");
			}
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        //返回相应体中的结果，打印出来
        return sBuffer.toString();
    }
    @Resource
	private DepartmentService departmentService;
	public Map<String, Object> getdepart(String info){
		String test="公安";
		Map<String, Object> map=new HashMap<String, Object>();
		map=departmentService.getMap(new QueryWrapper<DepartmentEntity>().like("dept_name", test));
			
		return map;
	}
}

