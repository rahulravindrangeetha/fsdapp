package com.booksandsubjectssecurity;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EmbeddedId;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OptionRedirectController 
{
	
	@RequestMapping("/login/optionRedirect")
	public String openOptionPage(ModelMap map) 
	{
		System.out.println("yeeeeeeeeeeeeeeeeeee");
		return "mainMenu";

	}

	@RequestMapping("/login/pageChooser")
	public String redirectToOptionPage(@RequestParam("optionVal") String option,ModelMap map,HttpServletRequest request) 
	{
		map.addAttribute("bookObject",new Book());
		map.addAttribute("subjectObject",new Subject());
		map.addAttribute("userObject",new UserObject());
		int optionPage=Integer.parseInt(option);
		switch (optionPage)
        {
            case 1:return "addSubject";
            case 2:return "addBook";        
            case 3:return "deleteSubject";
            case 4:return "deletebook";
            case 5:return "searchBook";
            case 6:return "searchSubject";
            case 7:return "registerUser";
            default:return "mainMenu";
        }
		

	}
	
	@RequestMapping("/login/searchBook")
	public String searchBook(@RequestParam("bookId") String bookId,ModelMap map) 
	{
		long bookIdLong=Long.parseLong(bookId);
		Session sessionOne = HibernateConnector.getSessionFactory().openSession();
        sessionOne.beginTransaction();
        Book book=(Book)sessionOne.get(Book.class, bookIdLong);
        sessionOne.getTransaction().commit();
		map.addAttribute("book",book);
		return "bookInfo";		

	}
	
	@RequestMapping("/login/searchSubject")
	public String searchSubject(@RequestParam("subjectId") String subjectId,ModelMap map) 
	{
		long subjectIdLong=Long.parseLong(subjectId);
		Session sessionOne = HibernateConnector.getSessionFactory().openSession();
        sessionOne.beginTransaction();
        Subject subject=(Subject)sessionOne.get(Subject.class, subjectIdLong);
        sessionOne.getTransaction().commit();
		map.addAttribute("subject",subject);
		return "subjectInfo";
		

	}
	
	@RequestMapping("/login/deleteBook")
	public String deleteBook(@RequestParam("bookId") String bookId,ModelMap map) throws IOException 
	{
		long bookIdLong=Long.parseLong(bookId);
		Session sessionOne = HibernateConnector.getSessionFactory().openSession();
        sessionOne.beginTransaction();
        Book book=(Book)sessionOne.get(Book.class, bookIdLong);
        if(book!=null)
        {
        	Subject subject=book.getSubject();
        	if(subject!=null)
        	{
        		subject.getReference().remove(book);
            	sessionOne.update(subject);
        	}
        	else
        	{
        		sessionOne.delete(book);
        	}
            
        	map.addAttribute("deleteStatus",'y');
        }
        else
        {
        	map.addAttribute("deleteStatus",'n');
        }
        sessionOne.getTransaction().commit();
		return "deleteBookInfo";
		

	}
	
	@RequestMapping("/login/deleteSubject")
	public String deleteSubject(@RequestParam("subjectId") String subjectId,ModelMap map) throws IOException 
	{
		long subjectIdLong=Long.parseLong(subjectId);
		Session sessionOne = HibernateConnector.getSessionFactory().openSession();
        sessionOne.beginTransaction();
        Subject subject=(Subject)sessionOne.get(Subject.class, subjectIdLong);
        
        if(subject!=null)
        {
        	sessionOne.delete(subject);
        	map.addAttribute("deleteStatus",'y');
        }
        else
        {
        	map.addAttribute("deleteStatus",'n');
        }
        sessionOne.getTransaction().commit();
		
		return "deleteSubjectInfo";
		

	}
	
	
	@RequestMapping("/login/addBook")
	public String addBook(@ModelAttribute("bookObject") Book book) throws IOException 
	{
		System.out.println("book from mvc->"+book);
		Session sessionOne = HibernateConnector.getSessionFactory().openSession();
        sessionOne.beginTransaction();
        sessionOne.save(book);
        sessionOne.getTransaction().commit();
		return "bookSuccess";

	}
	
	@RequestMapping("/login/registerUser")
	public String registerUser(@ModelAttribute("userObject") UserObject userObject) throws IOException 
	{
		System.out.println("userObject from mvc->"+userObject);
		Users user = new Users();
		user.setUsername(userObject.getUsername());
		user.setPassword(userObject.getPassword());
		user.setEnabled(userObject.isEnabled());
		List<Authorities> authorities= new ArrayList<Authorities>();
		Session sessionOne = HibernateConnector.getSessionFactory().openSession();
		if(userObject.getAuthority().contains(","))
		{
			String authorties []=userObject.getAuthority().split(",");
			for(String authority : authorties)
			{
				Authorities authorityObject= new Authorities();
				EmbeddedIdObject embeddedIdObject = new EmbeddedIdObject();
				embeddedIdObject.setAuthority(authority);
				embeddedIdObject.setUsername(user.getUsername());
				authorityObject.setEmbeddedIdObject(embeddedIdObject);
				authorities.add(authorityObject);
			}
			
		}
		else
		{
			Authorities authorityObject= new Authorities();
			EmbeddedIdObject embeddedIdObject = new EmbeddedIdObject();
			embeddedIdObject.setAuthority(userObject.getAuthority());
			embeddedIdObject.setUsername(user.getUsername());
			authorityObject.setEmbeddedIdObject(embeddedIdObject);
			authorities.add(authorityObject);
			
		}
		
        sessionOne.beginTransaction();
        sessionOne.save(user);
        for(Authorities authority:authorities)
        {
        	sessionOne.save(authority);
        }
        sessionOne.getTransaction().commit();
		return "registerUserSuccess";

	}
	
	@RequestMapping("/login/addSubject")
	public String addSubject(@ModelAttribute("subjectObject") Subject subject,@RequestParam String bookIds) throws IOException 
	{
		Session sessionOne = HibernateConnector.getSessionFactory().openSession();
        sessionOne.beginTransaction();
		if(!bookIds.contains(","))
		{
			
	        Book searchedBook=(Book)sessionOne.get(Book.class, Long.parseLong(bookIds.trim()));
			if(searchedBook!=null)
        	{
        		subject.getReference().add(searchedBook);
        	}
		}
		else
		{
        String books[] = bookIds.split(",");
	        for(String bookID : books)
	        {
	        	System.out.println("11111111");
	        	Book searchedBook=(Book)sessionOne.get(Book.class, Long.parseLong(bookID.trim()));
	        	if(searchedBook!=null)
	        	{
	        		subject.getReference().add(searchedBook);
	        		//searchedBook.setSubject(subject);
	        		//sessionOne.update(searchedBook);
	        	}
	        }
	        System.out.println("subject books"+subject.getReference().size());
		}
		sessionOne.save(subject);
   	    sessionOne.getTransaction().commit();
		return "subjectSuccess";

	}
	
	@InitBinder
	public void bindingPreparation(WebDataBinder binder) 
	{
	binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
		//DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd-MM-yyyy");

        @Override
        public void setAsText(String text) throws IllegalArgumentException 
        {
        	System.out.println("texttttt"+text);
        	setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }

        @Override
        public String getAsText() throws IllegalArgumentException {
            LocalDate localDate = (LocalDate) getValue();
            System.out.println("localdateeeee"+localDate);
            return localDate != null ? localDate.toString() : "";
        }
    });

}
}
