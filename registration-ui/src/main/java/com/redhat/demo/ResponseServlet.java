/*
 * #%L
 * Wildfly Camel
 * %%
 * Copyright (C) 2013 - 2015 RedHat
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.redhat.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@WebServlet(name = "RegistrationServlet", urlPatterns = { "/registration/*" }, loadOnStartup = 1)
public class ResponseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseServlet.class);

	@Autowired
	private CamelContext camelContext;

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		LOGGER.info("Handling the response");

        String candidatename = req.getParameter("candidatename");
        String email = req.getParameter("email");
        String seatname = req.getParameter("seatname");
        String seatno = req.getParameter("seatno");

		Registration registration = new Registration(candidatename,email,seatname,seatno);
		LOGGER.info("Publishing to kafka: " + registration);

    	ProducerTemplate producer = camelContext.createProducerTemplate();
        producer.requestBody("direct:start", registration);

		LOGGER.info("Redirecting to completed page");
		res.sendRedirect("/done.html");
    }
}