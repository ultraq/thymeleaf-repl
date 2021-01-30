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

import nz.net.ultraq.thymeleaf.spring.environment.SpringEnvironmentDialect

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

/**
 * Configuration and main class for the Thymeleaf REPL website as a Spring Boot
 * application.
 * 
 * @author Emanuel Rabina
 */
@SpringBootApplication
class ThymeleafReplApplication {

	@Bean
	SpringEnvironmentDialect springEnvironmentDialect() {
		return new SpringEnvironmentDialect()
	}

	/**
	 * Run the website as a Spring Boot application.
	 * 
	 * @param args
	 */
	static void main(String[] args) {
		SpringApplication.run(ThymeleafReplApplication, args)
	}
}
