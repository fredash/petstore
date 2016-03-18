package org.petstore.sample1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/aPetStoreManager")
public class PetStoreManagerController {

	@RequestMapping(method = RequestMethod.GET)
	public String home() {
		return "petManagement.html";
	}
}
