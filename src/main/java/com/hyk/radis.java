package com.hyk;

import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class radis {
    public static void main(String[] args) {
        Jedis jedis=new Jedis("localhost");
        Article article=new Article();
        article.setTitle("first");
        article.setAuthor("mm");
        article.setCount("gg");
        Long articleId=save(article,jedis);
        System.out.println("保存成功");
        System.out.println(articleId);//文章编号
        Article artice =getArticle(articleId,jedis);//获取文章
        System.out.println(artice);
        Article a = update(articleId,jedis);
        long article1=delArticle(articleId,jedis);
        System.out.println(article1);
//        jedis.set("BBB","www.baidu.com");
//        System.out.println("redis："+jedis.get("BBB"));
    }
    public static Long save(Article article, Jedis jedis){
        long articles =jedis.incr("articleId");
        Map<String,String>blog = new HashMap<String, String>();
        blog.put("title",article.getTitle());
        blog.put("author",article.getAuthor());
        blog.put("count",article.getCount());
        jedis.hmset("article:"+articles+":data",blog);
        return  articles;
    }
    public static Article getArticle(Long articleId, Jedis jedis){
        Map<String,String>myBlog=jedis.hgetAll("article:"+ articleId+":data");
        System.out.println(myBlog);
        Article article=new Article();
        article.setTitle(myBlog.get("title"));
        article.setAuthor(myBlog.get("author"));
        article.setCount(myBlog.get("count"));
        return article;
    }

    public static Article update(Long articleId, Jedis jedis){
        Article article = getArticle(articleId,jedis);
        article.setTitle("UpDate");
        String a =JSON.toJSONString(article);
        jedis.hset("article:" + articleId +":data","title",a);
        System.out.println("修改成功");
        return article;

    }
    public static long delArticle(Long articleId ,Jedis jedis){
        long a =jedis.del("article:"+ articleId+":data");
        return a;
    }
//    public static Article getArticle(Jedis jedis,Long articleId){
//        String article = jedis.get("article"+ articleId + ":data");
//        Article article1 = JSON.parseObject(article,Article.class);
//        return article1;
//    }

}
