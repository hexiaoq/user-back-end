package com.wefive.goverment.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cloud.apigateway.sdk.utils.Client;
import com.cloud.apigateway.sdk.utils.Request;
import com.wefive.goverment.common.utils.PageUtils;
import com.wefive.goverment.common.utils.Query;
import com.wefive.goverment.dao.SearchDao;
import com.wefive.goverment.entity.BusinessEntity;
import com.wefive.goverment.entity.DepartmentEntity;
import com.wefive.goverment.entity.SearchEntity;
import com.wefive.goverment.service.BusinessService;
import com.wefive.goverment.service.DepartmentService;
import com.wefive.goverment.service.MaterialService;
import com.wefive.goverment.service.SearchService;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service("searchService")
public class SearchServiceImpl extends ServiceImpl<SearchDao, SearchEntity> implements SearchService {
	//@Override
	@Resource
	private SearchDao searchDao;
	@Resource
	private BusinessService businessService;
	@Resource
	private MaterialService materialService;
	@Resource
	private DepartmentService departmentService;
	public List<Map<String,Object>> Getsearchlist(int userid) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		list.addAll(searchDao.selectMaps(new QueryWrapper<SearchEntity>().eq("user_id", userid)));
		return list;
	}
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SearchEntity> page = this.page(
                new Query<SearchEntity>().getPage(params),
                new QueryWrapper<SearchEntity>()
        );

        return new PageUtils(page);
    }
    	public List<Map<String, Object>> getdepartwithbus(String info) {
    		List<Map<String, Object>> m=new LinkedList<Map<String, Object>>();
    		JSONObject json1=new JSONObject();
    		List<Map<String, Object>> buslist=new ArrayList<Map<String,Object>>();
    		List<Map<String, Object>> deptlist=new ArrayList<Map<String, Object>>();
    		//List<Map<String, Object>> materiaList=new ArrayList<Map<String, Object>>();
    		buslist.addAll(businessService.listMaps(new QueryWrapper<BusinessEntity>().like("bus_name", info)));
    		if(!buslist.isEmpty()) {
    			for(Map<String, Object> entry : buslist) {
    				//JSONObject json1=new JSONObject();
    	    		JSONObject json2=new JSONObject();
    	    		JSONObject json=new JSONObject();
    	    		
    				//json1.put("info",entry);
    				for(Map.Entry<String, Object> e : entry.entrySet() ){
    					/*if(e.getKey().equals("bus_id")) {
    						materiaList.addAll(materialService.listMaps(new QueryWrapper<MaterialEntity>().eq("bus_id", e.getValue())));
    						json.put("material", materiaList);
    					}*/
    					
    					if(e.getKey().equals("dept_id")) {
    						deptlist.addAll(departmentService.listMaps(new QueryWrapper<DepartmentEntity>().eq("dept_id",e.getValue())));
    						/*for(Map<String, Object> dlMap : deptlist) {
    							m.add(dlMap);
    							json2.putAll(dlMap);
    						}*/
    					}
    				}
    				//json1.putAll(json);
    				json1.putAll(json2);
    				//json3.add(json1);
    			}
    		}
    		return deptlist;
    	}
    	public List<Map<String, Object>> getdepart(String info){
    		//String test="公安局";
    		//HashMap<String, Object> map=new HashMap<String, Object>();
    		List<Map<String, Object>> m=new LinkedList<Map<String, Object>>();
    		m.addAll(departmentService.listMaps(new QueryWrapper<DepartmentEntity>().like("dept_name", info)));

    		/*if(!m.isEmpty()) {
    			for(Map<String, Object> entry : m) {
    				for(Map.Entry<String, Object> e : entry.entrySet()) {
    					System.out.println("Key = " + e.getKey() + ", Value = " + e.getValue());
    				}
    			}
    		}
    		else {
    			System.out.println("null");
    		}*/
    		return m;
    	}
    	public String mainfun(String info) throws IOException {
        	JSONObject json1=new JSONObject();
            //请求的region
            String region = "cn-north-4";
            //用户名
            String username = "Wanghan";
            //用户密码
            String password = "wefive0104";
            //账户名
            String userName = "WUTwanghan";
            //请求的url接口
            String url = "https://ebea1a8894d04e348c782b5402c39143.apig.cn-north-4.huaweicloudapis.com/v1/infers/d3e72ca6-c3cd-4e50-b786-c40e657d529d";
            //获取用token
            String token = getToken(region,username,password,userName);
            //System.out.println("Token"+token);
            //String param="身份证";
            json1.put("text", info);
            String data=getAPI(token, json1, url);
            //System.out.println("data"+data);
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
        public String getToken(String region,String username,String password,String userName) throws IOException {
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
            //返回所要用到的token信息
            return token;
        }
     
        //请求云上接口，调用接口服务
        public String getAPI(String token,JSONObject jsonObject,String uri) throws IOException {
        	Request request = new Request();
            try {
                //Set the request parameters.
                //AppKey, AppSecrect, Method and Url are required parameters.
                request.setKey("cb9d4202559b43f1a172c72af11bb49b");
                request.setSecret("a38f915b7907461386060e8752aa5a3e");
            	request.addHeader("X-Auth-Token", token);
                request.setMethod("POST");
                request.setUrl(uri);
                request.addHeader("Content-Type", "application/json");
                //if it was published in other envs(except for Release),you need to add the information x-stage and the value is env's name
                //request.addHeader("x-stage", "publish_env_name");
                request.setBody(jsonObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                //Sign the request.
                okhttp3.Request signedRequest = Client.signOkhttp(request);

                //Send the request.
                OkHttpClient client = new OkHttpClient.Builder().build();
                Response response = client.newCall(signedRequest).execute();

                //Print the status line of the response.
                //System.out.println("status:" + response.code());

                //Print the header fields of the response.
                Headers resHeaders = response.headers();
                /*for (String h : resHeaders.names()) {
                    System.out.println(h + ":" + resHeaders.get(h));
                }*/

                //Print the body of the response.
                ResponseBody resEntity = response.body();
                //System.out.println("\n" + resEntity.string());
                //System.out.println(getType(resEntity.string()));
                //String data=new String(resEntity.string());
                return resEntity.string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }
    	public String getType(Object test) {
    		return test.getClass().getName().toString();
    					
    	}
}