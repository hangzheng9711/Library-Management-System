package model;  
  
public class Lend {  
    private long bookNum;  
    private String readerNum; 
    private String lendDate;
    private String retDeadline;
    private String returnDate;
    private String bookName;
    private int ID;
      
    public Lend(long bookNum,String readerNum,String lendDate, String retDeadline,String returnDate,String bookName,int ID)  
    {  
        this.bookNum = bookNum;  
        this.bookName = bookName;
        this.readerNum = readerNum;  
        this.lendDate = lendDate;
        this.retDeadline = retDeadline;
        this.returnDate = returnDate;  
        this.ID = ID;
    }  
  
    public int getID() {  
        return ID;  
    }  
    public void setID(int ID) {  
        this.ID = ID;  
    }  
    
    public long getbookNum() {  
        return bookNum;  
    }  
    public void setbookNum(long bookNum) {  
        this.bookNum = bookNum;  
    }  
  
    public String getreaderNum() {  
        return readerNum;  
    }  
    public void setreaderNum(String readerNum) {  
        this.readerNum = readerNum;  
    }  
    
    public String getbookName() {  
        return bookName;  
    }  
    public void setbookName(String bookName) {  
        this.bookName = bookName;  
    }  
    
    public String getlendDate() {  
        return lendDate;  
    }  
    public void setlendDate(String lendDate) {  
        this.lendDate = lendDate;  
    }  
    
    public String getretDeadline() {  
        return retDeadline;  
    }  
    public void setretDeadline(String retDeadline) {  
        this.retDeadline = retDeadline;  
    }  
    
    public String getreturnDate() {  
        return returnDate;  
    }  
    public void setreturnDate(String returnDate) {  
        this.returnDate = returnDate;  
    }  
}  