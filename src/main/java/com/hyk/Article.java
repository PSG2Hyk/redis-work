package com.hyk;

public class Article {
    public String title;
    public String count;
    public String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", count='" + count + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

