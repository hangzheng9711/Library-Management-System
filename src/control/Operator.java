package control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import jdbc.Database;
import model.Book;
import model.Lend;
import model.Reader;
import ui.panduan;

public class Operator {
	
	public static String global_account;
	public static String reader_account;
	public static String book_account;
	public static long maxID;
	
	public long getIDMax()
	{
		long max=0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select ID from BOOK");
			while(rs.next()){
				long id = (long)rs.getInt("ID");
				if(id>max)
					max=id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, rs);
		}
		return max;
	}
	
	public boolean isAdminPasswordRight(String account,String password)
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select password from ADMINISTRATOR where account='"+account+"'");
			if(rs.next()){
				String realPassword = rs.getString("password");
				if(realPassword.equals(password))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, rs);
		}
		return false;
	}
	
	public boolean isReaderPasswordRight(String readerNum,String password)
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select password from READER where readerNum='"+readerNum+"'");
			if(rs.next()){
				String realPassword = rs.getString("password");
				if(realPassword.equals(password))
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, rs);
		}
		return false;
	}
	
	public boolean chAdminPassword(String account,String password)
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			String sql = "update ADMINISTRATOR set password='"+password+"' where account='"+account+"'";
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Database.close(conn, stmt);
		}
	}
	
	public boolean chReaderPassword(String readerNum,String password)
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			String sql = "update READER set password='"+password+"' where readerNum='"+readerNum+"'";
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Database.close(conn, stmt);
		}
	}
	
	public ArrayList<Lend> getHaveLent(String readerNum)
	{
		ArrayList<Lend> lendlist = new ArrayList<Lend>();
		Connection conn = null;
		Statement stmt = null;
		Statement stmtt = null;
		ResultSet rs = null;
		ResultSet rss = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			stmtt = conn.createStatement();
			rs = stmt.executeQuery("select * from LEND where readerNum='"+readerNum+"' order by bookID");
			while(rs.next()){
				long bookID = (long)rs.getInt("bookID");
				int ID = rs.getInt("ID");
				String returnDate = rs.getString("returnDate");
				String retDeadline = rs.getString("retDeadline");
				String lendDate = rs.getString("lendDate");
				rss = stmtt.executeQuery("select * from BOOK where ID='"+bookID+"'");
				String bookName=null;
				long bookNum=-1;
				if(rss.next())
				{
				bookName=rss.getString("bookName");
				bookNum=Long.parseLong(rss.getString("bookNum"));
				}
				Lend lend = new Lend(bookNum,readerNum, lendDate, retDeadline, returnDate,bookName,ID);
				lendlist.add(lend);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, rs);
			Database.close(conn, stmtt, rss);
		}
		return lendlist;
	}
	
	public ArrayList<Book> getBookInfo(long bookNum,String bookName,String author,String pubHouse,String pubDate,int sumNow,int sum,String order)
	{
		ArrayList<Book> booklist = new ArrayList<Book>();
		int tag=0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			StringBuffer sql = new StringBuffer("select * from BOOK");;	
			if(bookNum!=-1&&tag==0) {sql.append(" where bookNum='"+bookNum+"'"); tag=1;}
			else if(bookNum!=-1&&tag==1) {sql.append(" and bookNum='"+bookNum+"'"); tag=1;}
			if(!bookName.equals("")&&tag==0) {sql.append(" where bookName='"+bookName+"'");tag=1;}
			else if(!bookName.equals("")&&tag==1) {sql.append(" and bookName='"+bookName+"'");tag=1;}
			if(!author.equals("")&&tag==0) {sql.append(" where author='"+author+"'");tag=1;}
			else if(!author.equals("")&&tag==1) {sql.append(" and author='"+author+"'");tag=1;}
			if(!pubHouse.equals("")&&tag==0) {sql.append(" where pubHouse='"+pubHouse+"'");tag=1;}
			else if(!pubHouse.equals("")&&tag==1) {sql.append(" and pubHouse='"+pubHouse+"'");tag=1;}
			if(!pubDate.equals("单击选择日期")&&!pubDate.equals("")&&tag==0) {sql.append(" where pubDate='"+pubDate+"'");tag=1;}
			else if(!pubDate.equals("单击选择日期")&&!pubDate.equals("")&&tag==1) {sql.append(" and pubDate='"+pubDate+"'");tag=1;}
			if(sumNow!=-1&&tag==0) {sql.append(" where sumNow='"+sumNow+"'");tag=1;}
			else if(sumNow!=-1&&tag==1) {sql.append(" and sumNow='"+sumNow+"'");tag=1;}
			if(sum!=-1&&tag==0) {sql.append(" where sum='"+sum+"'");tag=1;}
			else if(sum!=-1&&tag==1) {sql.append(" and sum='"+sum+"'");tag=1;}
			if(order.equals("书号"))
			{sql.append(" order by bookNum");}
			else
			{sql.append(" order by convert(bookName using gb2312) asc");}
			
			conn = Database.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()){
				long bookNumm = (long)rs.getInt("bookNum");
				String bookNamee = rs.getString("bookName");
				String authorr = rs.getString("author");
				String pubHousee = rs.getString("pubHouse");
				String contentt = rs.getString("content");
				String pubDatee = rs.getString("pubDate");
				int summ = rs.getInt("sum");
				int sumNoww = rs.getInt("sumNow");
				Book book = new Book(bookNumm,bookNamee,authorr,pubHousee,contentt,pubDatee,summ,sumNoww);
				booklist.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, rs);
		}
		return booklist;
	}
	
	public ArrayList<Reader> getReaderInfo(String readerNum,String Name,String sex,String grade,String department)
	{
		ArrayList<Reader> readerlist = new ArrayList<Reader>();
		int tag=0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			StringBuffer sql = new StringBuffer("select * from READER");
			if(!readerNum.equals("")&&tag==0) {sql.append(" where readerNum='"+readerNum+"'");tag=1;}
			else if(!readerNum.equals("")&&tag==1) {sql.append(" and readerNum='"+readerNum+"'");tag=1;}
			if(!Name.equals("")&&tag==0) {sql.append(" where Name='"+Name+"'");tag=1;}
			else if(!Name.equals("")&&tag==1) {sql.append(" and Name='"+Name+"'");tag=1;}
			if(!sex.equals("")&&tag==0) {sql.append(" where sex='"+sex+"'");tag=1;}
			else if(!sex.equals("")&&tag==1) {sql.append(" and sex='"+sex+"'");tag=1;}
			if(!grade.equals("")&&tag==0) {sql.append(" where grade='"+grade+"'");tag=1;}
			else if(!grade.equals("")&&tag==1) {sql.append(" and grade='"+grade+"'");tag=1;}
			if(!department.equals("")&&tag==0) {sql.append(" where department='"+department+"'");tag=1;}
			else if(!department.equals("")&&tag==1) {sql.append(" and department='"+department+"'");tag=1;}
			
			sql.append(" order by convert(Name using gb2312) asc");
			
			conn = Database.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			while(rs.next()){
				String readerNumm = rs.getString("readerNum");
				String Namee = rs.getString("Name");
				String sexx = rs.getString("sex");
				String gradee = rs.getString("grade");
				String departmentt = rs.getString("department");
				Reader reader = new Reader(readerNumm, Namee, sexx, gradee, departmentt);
				readerlist.add(reader);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, rs);
		}
		return readerlist;
	}
	
	public boolean isBookChaoqi(int ID)
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from LEND where ID='"+ID+"'");
			if(rs.next()){
				String retDeadline = rs.getString("retDeadline");
				String returnDate = rs.getString("returnDate");
				Date today = new Date();  
				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		        String toDay = formatter1.format(today);
				if(returnDate==null&&panduan.compare_date(toDay,retDeadline)==1||returnDate!=null&&panduan.compare_date(returnDate,retDeadline)==1)
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, rs);
		}
		return false;
	}
	
	public boolean isReaderChaoqiweihuan(String readerNum)
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from LEND where readerNum='"+readerNum+"'");
			while(rs.next()){
				int ID = rs.getInt("ID");
				String returnDate = rs.getString("returnDate");
				if(isBookChaoqi(ID)&&returnDate==null)
				{return true;}
		} }catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, rs);
		}
		return false;
	}
	
	public boolean addPreBook(long bookNum,int number)
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			String sql = "update BOOK set sum=sum+'"+number+"', sumNow=sumNow+'"+number+"' where bookNum='"+bookNum+"'";
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Database.close(conn, stmt);
		}
	}
	
	public boolean addNewBook(long ID,long bookNum,int number,String bookName,String author,String pubHouse,String pubDate,String content)
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			String sql = "insert into BOOK(ID,bookNum,bookName,author,pubHouse,pubDate,content,sum,sumNow) values('"+ID+"','"+bookNum+"','"+bookName+"','"+author+"','"+pubHouse+"','"+pubDate+"','"+content+"','"+number+"','"+number+"')";
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Database.close(conn, stmt);
		}
	}
	
	public boolean chBook(long bookNum,String bookName,String author,String pubHouse,String pubDate,String content)
	{
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			String sql = "update BOOK set bookName='"+bookName+"',author='"+author+"',pubHouse='"+pubHouse+"',pubDate='"+pubDate+"',content='"+content+"' where bookNum='"+bookNum+"'";
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			Database.close(conn, stmt);
		}
	}
	
	public int delBook(long bookNum,int number)
	{
		Connection conn = null;
		Statement stmt = null;
		Statement stmtt = null;
		ResultSet rs = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			stmtt = conn.createStatement();
			rs = stmt.executeQuery("select * from BOOK where bookNum='"+bookNum+"'");
			if(rs.next()){
				long id = (long)rs.getInt("ID");
				int summ = rs.getInt("sum");
				int sumNoww = rs.getInt("sumNow");
				if(summ>sumNoww) return 2;
				else if(number>summ) return 3;
				else
				{
				String sql=null;
				if(number<summ)
				{
					sql = "update BOOK set sum=sum-'"+number+"', sumNow=sumNow-'"+number+"' where bookNum='"+bookNum+"'";
					stmtt.execute(sql);
					
				}
				else
				{
					sql = "delete from LEND where bookID ='"+id+"'";
					stmtt.execute(sql);
					sql = "alter table LEND drop column ID,drop primary key,add column ID int(11) auto_increment not null,add primary key(ID)";
					stmtt.execute(sql);
					sql = "delete from BOOK where bookNum ='"+bookNum+"'";
					stmtt.execute(sql);
					sql = "alter table BOOK drop column bookNum,drop primary key,add column bookNum int(10) auto_increment not null,add primary key(bookNum,ID)";
					stmtt.execute(sql);
				}
				return 1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.close(conn, stmt, rs);
			Database.close(conn, stmtt);
		}
		return -1;
	}

public int delReader(String readerNum)
{
		Connection conn = null;
		Statement stmt = null;
		Statement stmtt = null;
		ResultSet rs = null;
		try {
			conn = Database.getConnection();
			stmt = conn.createStatement();
			stmtt = conn.createStatement();
			rs = stmt.executeQuery("select * from LEND where readerNum='"+readerNum+"'");
			while(rs.next()){
				String returnDate = rs.getString("returnDate");
				if(returnDate==null)
				{return 2;}
				}
			String sql=null;
			sql = "delete from LEND where readerNum ='"+readerNum+"'";
			stmtt.execute(sql);
			sql = "alter table LEND drop column ID,drop primary key,add column ID int(11) auto_increment not null,add primary key(ID)";
			stmtt.execute(sql);
			sql = "delete from READER where readerNum ='"+readerNum+"'";
			stmtt.execute(sql);
			sql = "alter table READER drop column ID,drop primary key,add column ID int(11) auto_increment not null,add primary key(ID)";
			stmtt.execute(sql);
			return 1;
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		Database.close(conn, stmt);
	}
	return -1;
}

public boolean chReader(String ReaderNum,String Name,String department,String grade,String sex)
{
	Connection conn = null;
	Statement stmt = null;
	try {
		conn = Database.getConnection();
		stmt = conn.createStatement();
		String sql = "update READER set Name='"+Name+"',department='"+department+"',grade='"+grade+"',sex='"+sex+"' where ReaderNum='"+ReaderNum+"'";
		stmt.execute(sql);
		return true;
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	} finally {
		Database.close(conn, stmt);
	}
}

public boolean addNewReader(String ReaderNum,String Name,String department,String grade,String sex)
{
	String password = new String("12345678");
	Connection conn = null;
	Statement stmt = null;
	try {
		conn = Database.getConnection();
		stmt = conn.createStatement();
		String sql = "insert into READER(ReaderNum,Name,department,grade,sex,password) values('"+ReaderNum+"','"+Name+"','"+department+"','"+grade+"','"+sex+"','"+password+"')";
		stmt.execute(sql);
		return true;
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	} finally {
		Database.close(conn, stmt);
	}
}

public int lend(String readerNum, long bookNum)
{
	Connection conn = null;
	Statement stmt = null;
	Statement stmtt = null;
	ResultSet rs = null;
	try {
		conn = Database.getConnection();
		stmt = conn.createStatement();
		stmtt = conn.createStatement();
		rs = stmt.executeQuery("select * from BOOK where bookNum='"+bookNum+"'");
		if(rs.next()){
			long bookID = (long)rs.getInt("ID");
			int sum = rs.getInt("sum");
			int sumNow = rs.getInt("sumNow");
			if(sumNow==0)
			{return 2;}
			else
			{
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date(); 
			Date nextMonth = new Date();
	        Calendar   calendar   =   new   GregorianCalendar(); 
	        calendar.setTime(today); 
	        calendar.add(calendar.MONTH, 1);
	        nextMonth=calendar.getTime(); 
	        String lendDate = formatter1.format(today);
	        String retDeadline = formatter1.format(nextMonth);

			String sql = "insert into LEND(readerNum,bookID,lendDate,retDeadline) values('"+readerNum+"','"+bookID+"','"+lendDate+"','"+retDeadline+"')";
			stmtt.execute(sql);
			sql = "update BOOK set sumNow=sumNow-1 where bookNum='"+bookNum+"'";
			stmtt.execute(sql);
			return 1;
			}
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		Database.close(conn, stmt, rs);
		Database.close(conn, stmtt);
	}
	return -1;
}

public boolean ret(int ID)
{
	Connection conn = null;
	Statement stmt = null;
	Statement stmtt = null;
	ResultSet rs = null;
	try {
		conn = Database.getConnection();
		stmt = conn.createStatement();
		stmtt = conn.createStatement();
		rs = stmt.executeQuery("select * from LEND where ID='"+ID+"'");
		if(rs.next()){
			long bookID = (long)rs.getInt("bookID");
			
			SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date(); 
	        String returnDate = formatter1.format(today);

			String sql = "update LEND set returnDate='"+returnDate+"' where ID='"+ID+"'";
			stmtt.execute(sql);
			sql = "update BOOK set sumNow=sumNow+1 where ID='"+bookID+"'";
			stmtt.execute(sql);
			return true;
			}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		Database.close(conn, stmt, rs);
		Database.close(conn, stmtt);
	}
	return false;
}
}