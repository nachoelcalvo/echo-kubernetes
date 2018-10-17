package com.example.echo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Hello Echo API")
@RestController
@RequestMapping("/greetings")
public class EchoController {

	public static final String CONTAINER_OWNER = "CONTAINER_OWNER";

	private EchoConfig config;

	private JKSConfiguration jksConfiguration;

	public EchoController(EchoConfig config, JKSConfiguration jksConfiguration) {
		this.config = config;
		this.jksConfiguration = jksConfiguration;
	}

	@ApiOperation(value = "Devuelve un saludo en mayusculas ", tags = "Echo")
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Error interno del servidor")
	})
	@GetMapping(path = "/{message}", produces = "text/plain")
	public String echo(@PathVariable String message) {
		return "Hey " + config.getUsername() + " with password " + config.getPassword();
	}
}
