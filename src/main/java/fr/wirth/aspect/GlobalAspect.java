/*
 * Copyright 2014 cwirth.
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
package fr.wirth.aspect;

import java.util.Arrays;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 *
 * @author cwirth
 */
@Aspect
public class GlobalAspect {
    
    private static final Logger LOGGER = Logger.getLogger(GlobalAspect.class);

    @Pointcut("execution(* fr.wirth..*(..))")
    public void fonctionMetier() {

    }

    @Around("execution(* fr.wirth..*(..))")
    public Object methodsWithArgumentsLogger(ProceedingJoinPoint joinPoint) throws Throwable {

        StringBuilder sb = new StringBuilder("Calling : ");
        sb.append(joinPoint.getSignature().getDeclaringType().getName());
        sb.append(".");
        sb.append(joinPoint.getSignature().getName());
        sb.append("(");
        sb.append(Arrays.toString(joinPoint.getArgs()));
        sb.append(")");

        LOGGER.debug(sb.toString());

        Object value = joinPoint.proceed();

        sb = new StringBuilder("Result : ");
        sb.append(value);
        LOGGER.debug(sb.toString());

        return value;
    }

}
