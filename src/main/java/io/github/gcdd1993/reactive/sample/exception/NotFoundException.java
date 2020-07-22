/*
 *
 *  * Copyright 2019 http://www.hswebframework.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package io.github.gcdd1993.reactive.sample.exception;

import org.springframework.http.HttpStatus;

/**
 *
 */
public class NotFoundException extends BusinessException {
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND.value());
    }

    public NotFoundException() {
        this("记录不存在");
    }
}
