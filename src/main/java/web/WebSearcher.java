package web;

import javafx.application.Platform;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ui.SceneController;

public class WebSearcher implements Runnable{

    private String[] argList;
    private static SceneController controller;

    public WebSearcher(String[] list){
        argList = list;
    }

    public void registerController(SceneController sc){
        controller = sc;
    }

    @Override
    public void run() {
        controller.setRGB(10);
        controller.setFanHeight(0);
        controller.setCaseHeight(0);
        String hrefLink;
        Integer CHeight = 0;
        Integer AHeight = 0;
        try{
            controller.setStat("[쿨러] 다나와 접속중...");
            controller.setProg(0.2);
            hrefLink = Jsoup.connect("https://search.danawa.com/dsearch.php?k1="+argList[0]+"&module=goods&act=dispMain").userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").get()
                    .select("#productListArea > div.main_prodlist.main_prodlist_list > ul > li > div.prod_main_info > div.prod_info > p.prod_name > a ")
                    .attr("href");
            controller.setStat("[쿨러] 상세페이지로 접속중...");
            controller.setProg(0.4);
            Elements elementsList = Jsoup.connect(hrefLink).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").get()
                    .select("#blog_content > div.summary_info > div.top_summary > div > div.sub_dsc > div > dl > dd > div > div > "+argList[2]);
            int tempCount = 0;
            for(Element element : elementsList){
                tempCount ++;
                if(element.text().contains(argList[1])){
                    CHeight = Integer.parseInt(elementsList.get(tempCount).text()
                            .replaceAll("mm", ""));
                    controller.setFanHeight(CHeight);
                    break;
                }
            }
        }catch(Exception e){
            controller.setStat("[쿨러] 에러!!");
            return;
        }

        try{
            controller.setStat("[케이스] 다나와 접속중...");
            controller.setProg(0.6);
            hrefLink = Jsoup.connect("https://search.danawa.com/dsearch.php?k1="+argList[3]+"&module=goods&act=dispMain").userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").get()
                    .select("#productListArea > div.main_prodlist.main_prodlist_list > ul > li > div.prod_main_info > div.prod_info > p.prod_name > a ")
                    .attr("href");
            controller.setStat("[케이스] 상세페이지로 접속중...");
            controller.setProg(0.8);
            Elements elementsList = Jsoup.connect(hrefLink).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").get()
                    .select("#blog_content > div.summary_info > div.top_summary > div > div.sub_dsc > div > dl > dd > div > div > "+argList[5]);
            int tempCount = 0;
            for(Element element : elementsList) {
                tempCount++;
                if (element.text().contains(argList[4])) {
                    AHeight = Integer.parseInt(elementsList.get(tempCount).text()
                            .replaceAll("mm", ""));
                    controller.setCaseHeight(AHeight);
                    break;
                }
            }
            controller.setStat("완료됨");
            controller.setProg(1);
            controller.setRGB(AHeight - CHeight);
        }catch(Exception e){
            controller.setStat("[쿨러] 에러!!");
        }
    }
}
