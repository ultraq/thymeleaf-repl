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

package nz.net.ultraq.thymeleaf.repl

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.thymeleaf.context.Context
import org.thymeleaf.exceptions.TemplateProcessingException
import org.thymeleaf.spring5.SpringTemplateEngine

import groovy.json.JsonSlurper

/**
 * Main (and likely the only) controller for the Thymeleaf REPL website.
 * 
 * @author Emanuel Rabina
 */
@Controller
class ReplController {

	private static final Logger logger = LoggerFactory.getLogger(ReplController)

	private static final ReplPageObject defaultPageObject = new ReplPageObject(
		template: '<p th:text="\'Hi \' + ${name} + \'! 👋\'"></p>',
		data: '{ "name": "Thymeleaf" }'
	)

	@Value('${thymeleaf-repl.version}')
	private String version

	@Value('${thymeleaf.version}')
	private String thymeleafVersion

	/**
	 * Add any global attributes to the model.
	 * 
	 * @param model
	 */
	@ModelAttribute
	void addAttributes(Model model) {

		model.addAttribute('version', version)
		model.addAttribute('thymeleafVersion', thymeleafVersion)
	}

	/**
	 * Serve the main page.
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping('/')
	String index(Model model) {

		model.addAttribute('repl', defaultPageObject)
		return 'Main'
	}

	/**
	 * Process the template in the form.
	 * 
	 * @param pageObject
	 * @param model
	 * @return
	 */
	@PostMapping('/')
	String process(ReplPageObject pageObject, Model model) {

		def resultObject = new ReplPageObject(
			template: pageObject.template,
			data: pageObject.data
		)
		try {
			resultObject.result = new SpringTemplateEngine().process(pageObject.template, new Context(
				variables: new JsonSlurper().parseText(pageObject.data)
			))
		}
		catch (TemplateProcessingException ex) {
			logger.error('Data used: {}', pageObject.data)
			resultObject.result = ex.rootCause
			resultObject.error = true
		}
		model.addAttribute('repl', resultObject)
		return 'Main'
	}
}
