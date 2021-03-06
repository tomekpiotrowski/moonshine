/*
 * Copyright 2013 Atteo.
 *
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
 */
package org.atteo.moonshine.tomcat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.atteo.config.Configurable;

@XmlRootElement(name = "connector")
public class TomcatConnectorConfig extends Configurable {
	/**
	 * Port on which to listen.
	 */
	@XmlElement
	private int port = 0;

	/**
	 * Coyote protocol.
	 */
	@XmlElement
	private String protocol = "HTTP/1.1";

	public int getPort() {
		return port;
	}

	public String getProtocol() {
		return protocol;
	}
}
