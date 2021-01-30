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

import org.springframework.stereotype.Component
import org.springframework.web.context.support.StandardServletEnvironment

import javax.inject.Inject
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

/**
 * A filter to add some headers to the HTTP response.
 * 
 * @author Emanuel Rabina
 */
@Component
class ResponseHeadersFilter implements Filter {

	private static final List<String> cspBase = [
		// Fetch directives
		'default-src \'none\'',
		'font-src https://fonts.gstatic.com',
		'img-src \'self\'',
		'style-src \'self\' https://fonts.googleapis.com https://cdnjs.cloudflare.com',

		// Document directives
		'base-uri \'none\'',

		// Navigation directives
		'form-action \'self\'',
		'frame-ancestors \'none\''
	]
	private static final List<String> cspDevelopment = cspBase + [
		'script-src localhost:35729',
		'connect-src ws://localhost:35729'
	]
	private static final List<String> cspProduction = cspBase + [
		'upgrade-insecure-requests'
	]

	@Inject
	private StandardServletEnvironment environment

	@Override
	void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {

		if (request.requestURI == '/') {
			response.addHeader('Content-Security-Policy', (environment.development ? cspDevelopment : cspProduction).join('; '))
			response.addHeader('X-Content-Type-Options', 'nosniff')
			response.addHeader('X-Frame-Options', 'DENY')
			response.addHeader('X-XSS-Protection', '1; mode=block')
		}

		chain.doFilter(request, response)
	}
}
