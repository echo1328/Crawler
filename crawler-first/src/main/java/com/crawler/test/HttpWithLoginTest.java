package com.crawler.test;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther wenlongzhou
 * @date 2019/6/18 11:10
 */

public class HttpWithLoginTest {

    public static void main(String[] args) throws Exception {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建HttpPost对象，设置url访问地址
        HttpPost httpPost = new HttpPost("https://passport.jd.com/new/login.aspx?ReturnUrl=https%3A%2F%2Fwww.jd.com%2F");

        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
        nvp.add(new BasicNameValuePair("username", "17343698034"));
        nvp.add(new BasicNameValuePair("password", "2427887763Zwl"));
        String sCharSet = "utf-8";
        httpPost.setEntity(new UrlEncodedFormEntity(nvp, sCharSet));

        System.out.println("发起请求的信息：" + httpPost);

        CloseableHttpResponse response = null;
        try {
            //使用HttpClient发起请求，获取response
            response = httpClient.execute(httpPost);

            //解析响应
            if (response.getStatusLine().getStatusCode() == 200) {
                //String content = EntityUtils.toString(response.getEntity(), "utf8");
                //System.out.println(content.length());

                String str = EntityUtils.toString(response.getEntity()); // post请求成功后的返回值
                String cookie = response.getLastHeader("Set-Cookie").getValue(); // 获取cookie值

                System.out.println(cookie);

                CloseableHttpClient httpClient2 = HttpClients.createDefault();

                HttpGet httpGet = new HttpGet("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&wq=%E6%89%8B%E6%9C%BA&pvid=9a2f948a8ebe40128858cd4075c2bd7f");

                httpGet.setHeader("Cookie", cookie); // 设置之前获取到的cookie
                httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");

                CloseableHttpResponse response2 = httpClient2.execute(httpGet);

                String str2 = EntityUtils.toString(response2.getEntity()); // 这里就是我们要的数据了。
                System.out.println(str2);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭response
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
