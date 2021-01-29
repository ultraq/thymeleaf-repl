/* 
 * Copyright 2021, Emanuel Rabina (http://www.ultraq.net.nz/)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nz.net.ultraq.thymeleaf.repl.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

import javax.servlet.http.HttpServletResponse

/**
 * Main (and likely the only) controller for the Thymeleaf REPL website.
 * 
 * @author Emanuel Rabina
 */
@Controller
class MainController {

	/**
	 * Serve the main page.
	 * 
	 * @param response
	 * @return
	 */
	@GetMapping('/')
	String index(HttpServletResponse response) {

		response.addHeader('X-Frame-Options', 'DENY')
		return 'Main'
	}

	/**
	 * Process the template in the form.
	 * 
	 * @param response
	 * @return
	 */
	@PostMapping('/')
	String process(HttpServletResponse response) {

		response.addHeader('X-Frame-Options', 'DENY')
		return 'Main'
	}
}
