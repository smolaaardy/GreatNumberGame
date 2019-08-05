package com.codingdojo.greatnumbergame;

pimport java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/main.jsp");
        view.forward(request, response);
//      load the jsp when someone navigates to mapped url "/"
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		when form is submitted start a new session
		HttpSession session = request.getSession();
//		set num to be what is stored as num in session
		Integer num = (Integer) session.getAttribute("num");
//		set guess to be what was entered in the guess field of the form
		Integer guess = Integer.parseInt(request.getParameter("guess"));
		Random rand = new Random();
//		if num in session is null it means that a new game is starting so set the session num to be a random int between 1-100;
		if(session.getAttribute("num") == null) {
			session.setAttribute("num", rand.nextInt(100));
		}
//		prints guess and num so we can see to test
		System.out.println("guess: "+guess);
		System.out.println("num: "+num);
//		cast both the entered guess and the session num to int to compare. If they are the same then the game is over so set responsetype to correct and create a new session num
		if((int)num == (int)guess) {
			session.setAttribute("num", rand.nextInt(100));
			session.setAttribute("responseType", "correct");
//		if the guess is greater than the session num set responseType to high
		} else if(guess > num) {
			session.setAttribute("responseType", "high");
//		if guess is less than session num (the only option left) set responseType to low
		} else {
			session.setAttribute("responseType", "low");
		} 
		doGet(request, response);
	}


}