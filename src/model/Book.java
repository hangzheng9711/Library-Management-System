package model;  
  
public class Book {  
    private long bookNum;  
    private String bookName; 
    private String author;
    private String pubHouse;
    private String content;
    private String pubDate;
    private int sum;
    private int sumNow;
      
    public Book(long bookNum,String bookName,String author, String pubHouse,String content,String pubDate,int sum,int sumNow)  
    {  
        this.bookNum = bookNum;  
        this.bookName = bookName;  
        this.author = author;
        this.pubHouse = pubHouse;
        this.content = content;  
        this.pubDate = pubDate;  
        this.sum = sum;  
        this.sumNow = sumNow;  
    }  
  
    public long getbookNum() {  
        return bookNum;  
    }  
    public void setbookNum(long bookNum) {  
        this.bookNum = bookNum;  
    }  
  
    public String getbookName() {  
        return bookName;  
    }  
    public void setbookName(String bookName) {  
        this.bookName = bookName;  
    }  
    
    public String getauthor() {  
        return author;  
    }  
    public void setauthor(String author) {  
        this.author = author;  
    }  
    
    public String getpubHouse() {  
        return pubHouse;  
    }  
    public void setpubHouse(String pubHouse) {  
        this.pubHouse = pubHouse;  
    }  
    
    public String getcontent() {  
        return content;  
    }  
    public void setcontent(String content) {  
        this.content = content;  
    }  
    
    public String getpubDate() {  
        return pubDate;  
    }  
    public void setpubDate(String pubDate) {  
        this.pubDate = pubDate;  
    }  
    
    public int getsum() {  
        return sum;  
    }  
    public void setsum(int sum) {  
        this.sum = sum;  
    }  
    
    public int getsumNow() {  
        return sumNow;  
    }  
    public void setsumNow(int sumNow) {  
        this.sumNow = sumNow;  
    }  
  
}  