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

package org.atteo.moonshine.global_modules;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import org.atteo.moonshine.tests.MoonshineConfiguration;
import org.atteo.moonshine.tests.MoonshineTest;
import org.junit.Test;

@MoonshineConfiguration(fromString = ""
        + "<config>"
        + "    <global-modules/>"
        + "</config>")
public class GlobalModulesServiceTest extends MoonshineTest {
    @Inject
    private String value;

    @Test
    public void shouldRegisterAppModules() {
        assertThat(value).isEqualTo("Hello World!");
    }
}
