package org.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class Crawler {
    //using hashset to avoid storing the same links
    HashSet<String> urlSet;
    //maintaining the depth to load urls upto 2 level 2 of the tree
    int max_depth=2;
    Crawler(){
        urlSet=new HashSet<>();
    }
    public void DFSNavigation(String url, int depth){
        //2 base cases
        if(urlSet.contains(url))return;
        if(depth>max_depth)return;
        if(urlSet.add(url)){
            System.out.println(url);
        }
        depth++;
        try {
            //for parsing html objects to java objects of type document
            // fetching links from page with links of response time<=4999 ms
            Document document = Jsoup.connect(url).timeout(5000).get();
            //indexer work will start here which updates/ stores the tile of page, links, document object into database
            Indexer indexer=new Indexer(document, url);
            System.out.println(document.title());

            //will traverse through all page links in the DOM object using DFS mechanism
            Elements allPageLinks = document.select("a[href]");
            for (Element link : allPageLinks) {
                DFSNavigation(link.attr("abs:href"), depth);
            }
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    public static void main(String[] args) {
        //creating the object of crawler
        Crawler crawler=new Crawler();
        //fetching the initial link to start DFS...
        crawler.DFSNavigation("https://www.javatpoint.com",1);
    }
}