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
package org.atteo.evo.firebuglogger;

import org.slf4j.LoggerFactory;

import com.google.inject.Module;
import com.google.inject.servlet.ServletModule;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import javax.xml.bind.annotation.XmlRootElement;
import org.atteo.evo.services.TopLevelService;

/**
 * Supports logging to the Firebug console with FirePHP installed.
 *
 * <p>
 * Download the extension from:<br/>
 * https://addons.mozilla.org/en-US/firefox/addon/firephp/
 * </p>
 */
@XmlRootElement(name = "firebuglogger")
public class FireBugLogger extends TopLevelService {

	@Override
	public Module configure() {
		return new ServletModule() {
			@Override
			protected void configureServlets() {
				filter("/*").through(FireBugFilter.class);
			}
		};
	}

	@Override
	public void start() {
		final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		final Logger rootLogger = context.getLogger(Logger.ROOT_LOGGER_NAME);

		Appender<ILoggingEvent> appender = new FireBugAppender();
		appender.setName("FireBug Appender");
		appender.setContext(context);
		appender.start();

		rootLogger.addAppender(appender);
	}

	@Override
	public void stop() {
		final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		final Logger rootLogger = context.getLogger(Logger.ROOT_LOGGER_NAME);
		rootLogger.detachAppender("FireBug Appender");
	}

}