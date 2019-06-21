package jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.jsoup.Jsoup.*;

/**
 * @auther wenlongzhou
 * @date 2019/6/18 14:50
 */

public class JsoupFirstTest {

    @Test
    public void testUrl() throws Exception {
        //解析url地址
        Document doc = Jsoup.parse(new URL("https://study.163.com/"),1000);

        //使用标签选择器，获取title标签中的内容
        String title = doc.getElementsByTag("title").first().text();

        //打印
        System.out.println(title);
    }

    @Test
    public void testString() throws Exception {
        //使用工具类读取文件，获取字符串
        String content = FileUtils.readFileToString(new File("C:\\Users\\wenlo\\Desktop\\test.html"),"utf8");

        //解析字符串
        Document doc = Jsoup.parse(content);
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    @Test
    public void testFile() throws Exception {
        //解析文件
        Document doc = Jsoup.parse(new File("C:\\Users\\wenlo\\Desktop\\test.html"),"utf8");
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    @Test
    public void testDom() throws Exception {
        //解析文件，获取Document对象
        Document doc = Jsoup.parse(new File("C:\\Users\\wenlo\\Desktop\\test.html"),"utf8");
        Element element = doc.getElementById("div1");
        //System.out.println("获取到的元素内容是：" + element.text());

        Element element1 = doc.getElementsByTag("span").first();
        //System.out.println(element1.text());

        Element element2 = doc.getElementsByClass("class_a").first();
        //System.out.println(element2.text());

        Element element3 = doc.getElementsByAttribute("abc").first();
        System.out.println(element3.text());
    }

    @Test
    public void testData() throws Exception {
        Document doc = Jsoup.parse(new File("C:\\Users\\wenlo\\Desktop\\test.html"),"utf8");
        Element element = doc.getElementById("div1");
        System.out.println(element.attr("aaa"));
        String str = element.id();
        System.out.println(str);
    }

    @Test
    public void testSelector() throws Exception {
        Document doc = Jsoup.parse(new File("C:\\Users\\wenlo\\Desktop\\test.html"),"utf8");
        Elements elements = doc.select("span");
        for (Element element : elements) {
            System.out.println(element.text());
        }
    }

}
