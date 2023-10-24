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

package nz.net.ultraq.thymeleaf.repl.extensions

/**
 * Extensions to the {@code Throwable} class.
 * 
 * @author Emanuel Rabina
 */
class ThrowableExtensions {

	/**
	 * Go through the cause stack and pick out the root cause for the exception.
	 * 
	 * @param self
	 * @return
	 */
	static Throwable getRootCause(Throwable self) {

		return self.cause?.rootCause ?: self
	}
}
