package com.revature.cats.demobeans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// In this class we're going to have Spring make beans. This is an entirely different way of making
// beans from our stereotype annotations.
// This is called "Java Configuration" and to use it we annotation the class with @Configuration.
// Java configuration, and its predecessor
// XML configuration (the 3rd way of making beans) allow for centralized config of the beans in our
// Spring app. We can and do use multiple
// methods of declaring beans in the same application.

@Configuration
public class JavaBeanConfig {

  // Inside of the java class annotated with @Configuration, we write methods annotated with @Bean.
  // Whatever objects these methods return become Spring beans.
  @Bean
  public DemoBean demoBeanCreator() {
    return new DemoBean();
  }

}
