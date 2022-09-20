package model;  
  
public class Reader {  
    private String readerNum;  
    private String Name; 
    private String sex;
    private String grade;
    private String department;
      
    public Reader(String readerNum,String Name, String sex,String grade,String department)  
    {  
        this.readerNum = readerNum;  
        this.Name = Name;  
        this.sex = sex;
        this.grade = grade;
        this.department = department;  
    }  
  
    public String getreaderNum() {  
        return readerNum;  
    }  
    public void sereaderNum(String readerNum) {  
        this.readerNum = readerNum;  
    }  
  
    public String getName() {  
        return Name;  
    }  
    public void setbookName(String Name) {  
        this.Name = Name;  
    }  
    
    public String getsex() {  
        return sex;  
    }  
    public void setsex(String sex) {  
        this.sex = sex;  
    }  
    
    public String getgrade() {  
        return grade;  
    }  
    public void setgrade(String grade) {  
        this.grade = grade;  
    }  
    
    public String getdepartment() {  
        return department;  
    }  
    public void setdepartment(String department) {  
        this.department = department;  
    }  
}  