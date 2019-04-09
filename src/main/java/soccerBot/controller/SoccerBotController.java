package soccerBot.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import soccerBot.model.errors.GenericError;
import soccerBot.service.SoccerBotService;

@RestController
@RequestMapping(path = "/api")
public class SoccerBotController {

	@Autowired
	SoccerBotService service;

	public static final Logger logger = LoggerFactory.getLogger(SoccerBotController.class);

	@RequestMapping(value = "/soccerBot", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<HttpStatus> soccerBot() throws JsonParseException, JsonMappingException, IOException, GenericError {
		HttpStatus status = HttpStatus.OK;
		service.socceBot();
		return new ResponseEntity<>(status);
	}

}
