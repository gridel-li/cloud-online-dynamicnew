package com.sgcc.zj.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

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

    public static void main(String[] args) throws Exception {
        String url = "http://127.0.0.1:8764/hello?name=ss";
        System.out.println(HttpClientUtils.sendGet(url));
    }
}