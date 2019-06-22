package com.tm.task;

import com.tm.pojo.Goods;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @program: crawler
 * @description: JobProcessor
 * @author: wenlongzhou
 * @create: 2019-06-20 19:56
 **/

@Component
public class JobProcessor implements PageProcessor {

    private String url = "https://list.tmall.com/search_product.htm?q=%CA%D6%BB%FA&type=p&spm=a220m.1000858.a2227oh.d100&from=.list.pc_1_searchbutton";

    @Override
    public void process(Page page) {
        //解析页面，获取商品列表的url地址
        List<Selectable> list = page.getHtml().css("div#J_ItemList div.product").nodes();

        //System.out.println("wait");

        //判断获取到的集合是否为空
        if (list.size() == 0) {
            //如果为空，表示这是商品详情页，解析页面，获取商品信息详情，保存数据
            this.saveJobInfo(page);
        } else {
            //如果不为空，表示这是列表页,解析出详情页的url地址，放到任务队列中
            for (Selectable selectable : list) {
                //获取url地址
                String jobInfoUrl = selectable.css("div.productImg-wrap").links().toString();
                //把获取到的url地址放到任务队列中
                page.addTargetRequest(jobInfoUrl);
            }

            //获取下一页的url
            String bkUrl = page.getHtml().css("a.ui-page-next").links().toString();
            //把url放到任务队列中
            page.addTargetRequest(bkUrl);
        }
    }

    //解析页面，获取招聘信息详情，保存数据
    private void saveJobInfo(Page page) {
        //创建招聘详情对象
        Goods goods = new Goods();

        //解析页面
        Html html = page.getHtml();

        //获取数据，封装到对象中
        String name = html.css("div#J_DetailMeta div.tb-detail-hd h1 a","text").toString();
        String caption = html.css("div#J_DetailMeta div.tb-detail-hd p","text").toString();
        int score = Integer.parseInt(html.css("span.tm-count","text").toString().trim());
        String url = page.getUrl().toString();
        String image = html.css("img#J_ImgBooth","src").toString();
        String sale = html.css("li#J_ItemRates","text").toString();
        //String activity = html.css("div.tm-shopPromo-panel dd","text").toString();

        //Selectable selectable = html.css("div.tm-shopPromo-panel");

        goods.setName(name);
        goods.setCaption(caption);
        goods.setScore(score);
        goods.setUrl(url);
        goods.setImage(image);
        goods.setStatus(1);

        //把结果保存起来

        page.putField("goods", goods);
    }

    private Site site = Site.me()
            .setCharset("gbk") //设置编码
            .setTimeOut(10*10000) //设置超时时间
            .setRetrySleepTime(3000) //设置重试的间隔时间
            .setRetryTimes(3) //设置重试的次数
            .setDomain("list.tmall.com")
            .addCookie("_cc_","VT5L2FSpdA%3D%3D")
            .addCookie("_hvn_login","0")
            .addCookie("_l_g_","Ug%3D%3D")
            .addCookie("_l_g_","Ug%3D%3D")
            .addCookie("_lastvisited","3ZGMFUt86CICAdpMQWM5kNcn%2C%2C3ZGMFUt86CICAdpMQWM5kNcneo92zWTX%2Cjx72h37j%2Cjx72h37j%2C3%2C74fe978d%2C3ZGMFUt86CICAdpMQWM5kNcn%2Cjx7jjwvu")
            .addCookie("_med","dw:1536&dh:864&pw:1920&ph:1080&ist:0")
            .addCookie("_nk_","t_1500032860085_0764")
            .addCookie("_nk_","t_1500032860085_0764")
            .addCookie("_tb_token_","7196655ee8ee9")
            .addCookie("_tb_token_","7196655ee8ee9")
            .addCookie("_tb_token_","4ed4616ee163")
            .addCookie("_uab_collina","156118035473568498039695")
            .addCookie("_umdata","G4461395B2F007D059101BCE2965E4A13549574")
            .addCookie("atpsida","7c7e149ffcc5265026760f9f_1561208059_35")
            .addCookie("atpsidas","e970493bf32b7d25b3fa0123_1561208060_34")
            .addCookie("aui","3347030377")
            .addCookie("cad","H40ctph762J2lIaWmx6q8dTqCeAkNtSbR89ZhYretZ8=0001")
            .addCookie("cap","511d")
            .addCookie("cdpid","UNN5Fih3%252B5kmhQ%253D%253D")
            .addCookie("ck1","\"\"")
            .addCookie("cmida","1401053355_20190622205425")
            .addCookie("cna","3ZGMFUt86CICAdpMQWM5kNcn")
            .addCookie("cna","3ZGMFUt86CICAdpMQWM5kNcn")
            .addCookie("cna","3ZGMFUt86CICAdpMQWM5kNcn")
            .addCookie("cnaui","3347030377")
            .addCookie("cookie1","BxeNCeN2dvMd54qja285HsDWh1tO%2BxrVYNTqYJ226jU%3D")
            .addCookie("cookie1","BxeNCeN2dvMd54qja285HsDWh1tO%2BxrVYNTqYJ226jU%3D")
            .addCookie("cookie17","UNN5Fih3%2B5kmhQ%3D%3D")
            .addCookie("cookie17","UNN5Fih3%2B5kmhQ%3D%3D")
            .addCookie("cookie2","1cf3b473b38a69a5efea450ef8bf9843")
            .addCookie("cookie2","1cf3b473b38a69a5efea450ef8bf9843")
            .addCookie("cookie2","187c4774516b1397bb7cc3e3f90da68c")
            .addCookie("cq","ccp%3D0")
            .addCookie("csg","72c4c45e")
            .addCookie("csg","72c4c45e")
            .addCookie("csg","60d0bdd7")
            .addCookie("dnk","t_1500032860085_0764")
            .addCookie("dnk","t_1500032860085_0764")
            .addCookie("enc","7V3IZeBHQpsNyxjJyHXCZ6dLWGFuNgKilT8sbrAm6iP9FNHsz5mSxTGdGUQzLgkwMjcxIoTUWP3FU%2Bh0%2BiWTzg%3D%3D")
            .addCookie("enc","ljb%2FPRTZL2jDLv0mzBuWbFscnEo271k614iJbqmDTWrFkD5guyRNW%2B2VGpjUNEr%2FcVEK0nwxt%2B3s3wdyP4UNGw%3D%3D")
            .addCookie("existShop","MTU2MTIwNTEzNQ%3D%3D")
            .addCookie("hng","CN%7Czh-CN%7CCNY%7C156")
            .addCookie("hng","CN%7Czh-CN%7CCNY%7C156")
            .addCookie("isg","BL-_Rfqq3vg1_NrkUy02plPvTpNDWgyhCSSNeVGMW261YN_iWXSjlj16onA7Puu-")
            .addCookie("isg","BGRk0HYf5dneWhEVPOmeWwf6NWJcA5eAbv2G6H6E9y_OKQXzpg289RDI7eGU98C_")
            .addCookie("l","bBIJOYLuqL-Ce792BOCgIZwz167OSIRYouWjzGu6i_5Iv1Tss1bOkXozCe96Vj5RsU8B4R15R8p9-etkt")
            .addCookie("1","bBLRi3NrqL-Cv5GxBOfgqZwz167tCIRb8rVPhGIZ2ICP_-CHDi5dWZhEbQ8MC3GVa6F9R3y1Nww8BAYLvPUsQ")
            .addCookie("lgc","t_1500032860085_0764")
            .addCookie("lgc","t_1500032860085_0764")
            .addCookie("lgc","t_1500032860085_0764")
            .addCookie("login","true")
            .addCookie("mbk","a7f64757f80e3abf")
            .addCookie("miid","569333931333843112")
            .addCookie("mt","ci=1_1")
            .addCookie("pnm_cku822","098%23E1hvspvUvbpvUvCkvvvvvjiPRFzUQjEjRscUQj3mPmPw1jn8R2zO1jtPPF5p0jYbiQhvCvvv9UUEvpCWvUzUvvaw1nClYb8rwZnlfXxrj8gaWXxrgj7JhL6N%2BExrA8TJEcq9afFnR2Oic4mUk8p7%2B3%2BFaNoxfBeKf3Ax0XcEKfEIpfmxfwkK5dyCvm9vvvvvphvvvvvvvQCvpvCNvvv2vhCv2UhvvvWvphvWmpvvvQCvpvQEuphvmvvv9bZM4EILkphvC99vvOCzBvhCvvOvUvvvphvPvpvhvv2MMsyCvvpvvvvv")
            .addCookie("res","scroll%3A1519*6139-client%3A1519*215-offset%3A1519*6139-screen%3A1536*864")
            .addCookie("sca","58032a26")
            .addCookie("sg","471")
            .addCookie("sg","471")
            .addCookie("skt","37db49f07b49ad0b")
            .addCookie("skt","37db49f07b49ad0b")
            .addCookie("t","b8c59db51af03b241a9ce94f2991b502")
            .addCookie("t","3fc6474d7b80bbae3f851d01ccca437e")
            .addCookie("t","b8c59db51af03b241a9ce94f2991b502")
            .addCookie("tbsa","58fd5ad5ecc85f8026a31020_1561207171_5")
            .addCookie("tg","0")
            .addCookie("thw","cn")
            .addCookie("tk_trace","1")
            .addCookie("tk_trace","oTRxOWSBNwn9dPyorMJE%2FoPdY8zfvmw%2Fq5hnSzRcAGyGaQxzHyIkXAi6XGvVCEUWku548F0WCt9k6Ny48N2m%2F1TSWQTZfP%2Bq%2FCQ6houWRKXp6%2FIhxumxGs51vmsg99LmYpjkt4RWykVr5TuYJHBe8%2BJWy77uCKrDW49tfV3itdXURuf32hnfd1AnhGLS0q7kOudthpSqwLEKnGyKA4IPD5QgmhnEKT5uvxUJFBnpGLY1fxK8EWGxxNq1%2B30tLR8nbtrFbAmGclpsPLDK74DAToJcug%3D%3D")
            .addCookie("tracknick","t_1500032860085_0764")
            .addCookie("tracknick","t_1500032860085_0764")
            .addCookie("tt","tmall-main")
            .addCookie("uc1","cookie15=VT5L2FSpMGV7TQ%3D%3D")
            .addCookie("uc1","cookie15=URm48syIIVrSKA%3D%3D&cookie14=UoTaGd%2FsGWA4kw%3D%3D")
            .addCookie("uc3","vt3=F8dBy3kZwFZxRfktfyQ%3D&id2=UNN5Fih3%2B5kmhQ%3D%3D&nk2=F6k3HSxtA7vqU3h2Wf3PEzMs3Fo%3D&lg2=VFC%2FuZ9ayeYq2g%3D%3D")
            .addCookie("uc3","vt3=F8dBy3kZwFZxRfktfyQ%3D&id2=UNN5Fih3%2B5kmhQ%3D%3D&nk2=F6k3HSxtA7vqU3h2Wf3PEzMs3Fo%3D&lg2=VFC%2FuZ9ayeYq2g%3D%3D")
            .addCookie("unb","3347030377")
            .addCookie("unb","3347030377")
            .addCookie("uss","\"\"")
            .addCookie("v","0");

    @Override
    public Site getSite() {
        return site;
    }

    @Autowired
    private SpringDataPipeline springDataPipeline;

    //initialDelay当任务启动后,等待多久执行方法
    //fixedDelay每隔多久执行方法
    @Scheduled(initialDelay = 1000,fixedDelay = 100 * 1000)
    public void process() {

        //创建下载器Downloader
        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();

        //给下载器设置代理服务器消息
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("120.27.210.60",8080)));

        Spider.create(new JobProcessor())
                .addUrl(url)
                //.setDownloader(httpClientDownloader)
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(10)
                .addPipeline(springDataPipeline)
                .run();
    }

}
