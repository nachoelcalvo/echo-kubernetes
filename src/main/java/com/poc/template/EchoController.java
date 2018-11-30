package com.poc.template;

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

@Api(value = "Echo API")
@RestController
public class EchoController {

	@ApiOperation(value = "Return a message in upper case", tags = "Echo")
	@ApiResponses({
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error")
	})
	@GetMapping(path = "/{message}", produces = "text/plain")
	public String echo(@PathVariable String message) {
		return message.toUpperCase();
	}
}
