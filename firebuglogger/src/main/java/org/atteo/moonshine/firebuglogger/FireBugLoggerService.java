/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.atteo.moonshine.firebuglogger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;

import org.atteo.moonshine.TopLevelService;
import org.atteo.moonshine.services.ImportService;
import org.atteo.moonshine.webserver.ServletContainer;
import org.slf4j.LoggerFactory;

import com.google.inject.Module;
import com.google.inject.PrivateModule;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;

/**
 * Supports logging to the Firebug console with FirePHP installed.
 *
 * <p>
 * Download the extension from:<br/>
 * https://addons.mozilla.org/en-US/firefox/addon/firephp/
 * </p>
 */
@XmlRootElement(name = "firebugLogger")
public class FireBugLoggerService extends TopLevelService {
	public static final String FIRE_BUG_APPENDER = "FireBug Appender";

	@XmlElement
	@XmlIDREF
	@ImportService
	private ServletContainer servletContainer;

	/**
	 * URL pattern specifying to which responses the headers will be added.
	 */
	@XmlElement
	private String pattern = "/*";

	@Override
	public Module configure() {
		return new PrivateModule() {
			@Override
			protected void configure() {
				bind(FireBugFilter.class);
				servletContainer.addFilter(getProvider(FireBugFilter.class), pattern);
			}
		};
	}

	@Override
	public void start() {
		final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		final Logger rootLogger = context.getLogger(Logger.ROOT_LOGGER_NAME);

		Appender<ILoggingEvent> appender = new FireBugAppender();
		appender.setName(FIRE_BUG_APPENDER);
		appender.setContext(context);
		appender.start();

		rootLogger.addAppender(appender);
	}

	@Override
	public void close() {
		final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		final Logger rootLogger = context.getLogger(Logger.ROOT_LOGGER_NAME);
		rootLogger.detachAppender(FIRE_BUG_APPENDER);
	}

}
