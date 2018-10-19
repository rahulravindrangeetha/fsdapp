package com.booksandsubjectsmvchibernatedataboot;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OptionRedirectController 
{
	@Autowired
	private BookService bookService;
	
	@Autowired
	private SubjectService subjectService;
	
	@RequestMapping("/optionRedirect")
	public String openOptionPage(ModelMap map) 
	{
		System.out.println("yeeeeeeeeeeeeeeeeeee");
		return "mainMenu";

	}

	@RequestMapping("/pageChooser")
	public String redirectToOptionPage(@RequestParam("optionVal") String option,ModelMap map,HttpServletRequest request) 
	{
		map.addAttribute("bookObject",new Book());
		map.addAttribute("subjectObject",new Subject());
		int optionPage=Integer.parseInt(option);
		switch (optionPage)
        {
            case 1:return "addSubject";
            case 2:return "addBook";        
            case 3:return "deleteSubject";
            case 4:return "deletebook";
            case 5:return "searchBook";
            case 6:return "searchSubject";
            default:return "mainMenu";
        }
		

	}
	
	@RequestMapping("/searchBook")
	public String searchBook(@RequestParam("bookId") String bookId,ModelMap map) 
	{
		long bookIdLong=Long.parseLong(bookId);		
        return bookService.searchBook(bookIdLong, map);
	}
	
	@RequestMapping("/searchSubject")
	public String searchSubject(@RequestParam("subjectId") String subjectId,ModelMap map) 
	{
		long subjectIdLong=Long.parseLong(subjectId);
        return subjectService.searchSubject(subjectIdLong, map);
	}
	
	@RequestMapping("/deleteBook")
	public String deleteBook(@RequestParam("bookId") String bookId,ModelMap map) throws IOException 
	{
		long bookIdLong=Long.parseLong(bookId);
        return bookService.deleteBook(bookIdLong, map);
	}
	
	@RequestMapping("/deleteSubject")
	public String deleteSubject(@RequestParam("subjectId") String subjectId,ModelMap map) throws IOException 
	{
		long subjectIdLong=Long.parseLong(subjectId);
		return subjectService.deleteSubject(subjectIdLong, map);
 	}
	
	
	@RequestMapping("/addBook")
	public String addBook(@ModelAttribute("bookObject") Book book) throws IOException 
	{	
		System.out.println("add bokkkkkkkkkk"+book);
		return bookService.addBook(book);
     }

	@RequestMapping("/addSubject")
	public String addSubject(@ModelAttribute("subjectObject") Subject subject,@RequestParam String bookIds) throws IOException 
	{
		return subjectService.addSubject(subject, bookIds);
	}
	
	@InitBinder
	public void bindingPreparation(WebDataBinder binder) 
	{
	binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
		//DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd-MM-yyyy");

        @Override
        public void setAsText(String text) throws IllegalArgumentException 
        {
        	System.out.println("111111111111111");
        	setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }

        @Override
        public String getAsText() throws IllegalArgumentException 
        {
        	System.out.println("2222222222222222");
            LocalDate localDate = (LocalDate) getValue();
            return localDate != null ? localDate.toString() : "";
        }
    });

}
}
