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

package org.atteo.moonshine.websocket.tomcat;

import java.util.Arrays;
import java.util.List;

import javax.websocket.Decoder;
import javax.websocket.DeploymentException;
import javax.websocket.Encoder;
import javax.websocket.server.ServerEndpoint;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.tomcat.websocket.pojo.PojoEndpointServer;
import org.apache.tomcat.websocket.pojo.PojoMethodMapping;
import org.apache.tomcat.websocket.server.WsSci;
import org.atteo.moonshine.websocket.WebSocketContainerService;

import com.google.inject.Module;

/**
 * WebSocket container for Tomcat.
 */
@XmlRootElement(name = "tomcat-websocket-container")
public class TomcatWebSocketContainerService extends WebSocketContainerService {
	@Override
	public Module configure() {
		servletContainer.addServletContainerInitializer(new WsSci());
		return super.configure();
	}

	@Override
	protected <T> EndpointDefinition<T> createEndpointDefinition(Class<T> klass) {
		return new TomcatEndpointDefinition<>(klass);
	}

	private static class TomcatEndpointDefinition<T> extends EndpointDefinition<T> {
		private ServerEndpoint annotation;

		public TomcatEndpointDefinition(Class<T> endpointClass) {
			super(endpointClass);
			try {
				annotation = endpointClass.getAnnotation(ServerEndpoint.class);
				if (annotation != null) {
					PojoMethodMapping methodMapping = new PojoMethodMapping(endpointClass,
							annotation.decoders(), annotation.value());

					userProperties.put(PojoEndpointServer.POJO_METHOD_MAPPING_KEY, methodMapping);
				}
			} catch (DeploymentException ex) {
				throw new RuntimeException(ex);
			}
		}

		@Override
		public String getPath() {
			String path = super.getPath();
			if (path == null && annotation != null) {
				return annotation.value();
			}
			return path;
		}

		@Override
		public List<Class<? extends Decoder>> getDecoders() {
			List<Class<? extends Decoder>> decoders = super.getDecoders();

			if (decoders.isEmpty() && annotation != null) {
				return Arrays.asList(annotation.decoders());
			}
			return decoders;
		}

		@Override
		public List<Class<? extends Encoder>> getEncoders() {
			List<Class<? extends Encoder>> encoders = super.getEncoders();

			if (encoders.isEmpty() && annotation != null) {
				return Arrays.asList(annotation.encoders());
			}
			return encoders;
		}
	}

}
