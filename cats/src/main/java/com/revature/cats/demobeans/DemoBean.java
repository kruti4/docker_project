package com.revature.cats.demobeans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class DemoBean implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware,
    InitializingBean, DisposableBean {

  public DemoBean() {
    System.out.println("DemoBean constructor");
  }

  @Override
  public void setBeanName(String name) {
    System.out.println("Set Bean Name");
  }

  @Override
  public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    System.out.println("Set Bean Factory");
  }

  @Override
  public void setBeanClassLoader(ClassLoader classLoader) {
    System.out.println("Set Bean Class Loader");
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("After Properties Set (custom init can go here)");
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("destroy (custom teardown can go here)");

  }


}
