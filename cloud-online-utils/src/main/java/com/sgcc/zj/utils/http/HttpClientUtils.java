package com.sgcc.zj.utils.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 * @author liyingjie
 * @Title: HttpClientUtils
 * @Description: httpUtils
 * @date 2019/2/25
 */
public class HttpClientUtils {
    public static String sendPost(String url,String json,String chartset){
        System.out.println("http开始post");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String returnValue = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity requestEntity = new StringEntity(json,chartset);
            requestEntity.setContentEncoding(chartset);
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);
            HttpResponse result = httpClient.execute(httpPost);
            HttpEntity entity = result.getEntity();
            returnValue = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("http结束post");
        return returnValue;
    }


    public static String sendGet(String url){
        System.out.println("http开始get");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String returnValue = "";
        try {
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                returnValue= EntityUtils.toString(response.getEntity(),"utf-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("http结束get");
        return returnValue;
    }


    public static String doPostWithParams(String url,Map<String,Object> map,String charset){
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String,Object> elem = (Map.Entry<String, Object>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue().toString()));
            }
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String url = "http://127.0.0.1:8764/hello?name=AA";
        System.out.println(HttpClientUtils.sendGet(url));
        Map map = new HashMap();
        map.put("name","ss");
        String jsonStr = JSONObject.toJSONString(map);
        String postUrl = "http://127.0.0.1:8764/hello";
        System.out.println(HttpClientUtils.sendPost(postUrl,jsonStr,"UTF-8"));
        System.out.println("-----------------------------------------");
        System.out.println(HttpClientUtils.doPostWithParams(postUrl,map,"UTF-8"));

    }
}