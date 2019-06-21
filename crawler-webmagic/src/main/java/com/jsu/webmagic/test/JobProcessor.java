package com.jsu.webmagic.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.scheduler.Scheduler;

/**
 * @auther wenlongzhou
 * @date 2019/6/19 22:36
 */

public class JobProcessor implements PageProcessor {

    //解析页面
    public void process(Page page) {
        //解析返回的数据page，并且把解析的结果放到ResultItems中
        //css选择器
        page.putField("div", page.getHtml().css("div.mod_top_news2>h2").all());

        //XPath
        page.putField("div2", page.getHtml().xpath("//div[@id=shortcut-2014]/div/ul/li/a"));

        //正则表达式
        page.putField("div3", page.getHtml().css("div#shortcut-2014 a").regex(".*京东.*").all());

        //处理结果Api
        page.putField("div4", page.getHtml().css("div#shortcut-2014 a").regex(".*京东.*").get());
        page.putField("div5", page.getHtml().css("div#shortcut-2014 a").regex(".*京东.*").toString());

        //获取链接
        page.addTargetRequests(page.getHtml().css("div#shortcut-2014").links().all());
        page.putField("url", page.getHtml().css("#navitems-group1 li a").all());
    }

    private Site site = Site.me()
            .setCharset("utf8") //设置编码
            .setTimeOut(10000) //设置超时时间，单位是ms毫秒
            .setRetrySleepTime(3000) //设置重试的间隔时间
            .setSleepTime(3) // 设置重试次数
            ;

    public Site getSite() {
        return site;
    }

    //主函数，执行爬虫
    public static void main(String[] args) {
        Spider spider = Spider.create(new JobProcessor())
                .addUrl("https://kuaibao.jd.com/?ids=175801562,176549950,176866571,176235283") //设置要爬取数据的页面
                //.addPipeline(new FilePipeline("C:\\Users\\wenlo\\Desktop\\result"))
                .thread(5) //设置有5个线程处理
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000))); //设置布隆去重过滤器，指定最多对1000万数据进行去重操作

        Scheduler scheduler = spider.getScheduler();

        //执行爬虫
        spider.run();
    }

}
