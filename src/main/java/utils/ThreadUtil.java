package utils;


import web.WebSearcher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtil {

    private static ExecutorService executorService;

    public void init(){
        executorService = Executors.newFixedThreadPool(1);
    }

    public void execute(String[] list){
        executorService.submit(new WebSearcher(list));
    }

}
