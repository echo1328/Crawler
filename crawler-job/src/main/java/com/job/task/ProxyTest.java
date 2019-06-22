package com.job.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

/**
 * @program: crawler
 * @description: ProxyTest
 * @author: wenlongzhou
 * @create: 2019-06-21 23:25
 **/

@Component
public class ProxyTest implements PageProcessor {

    @Scheduled(fixedDelay = 1000)
    public void Process() {
        //创建下载器Downloader
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();

        //给下载器设置代理服务器消息
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("120.27.210.60",8080)));

        Spider.create(new ProxyTest())
                .addUrl("http://ip.chinaz.com/")
                .setDownloader(httpClientDownloader) //设置下载器
                .run();
    }

    @Override
    public void process(Page page) {
        System.out.println(page.getHtml().toString());
    }

    private Site site = Site.me();

    @Override
    public Site getSite() {
        return site;
    }

}
