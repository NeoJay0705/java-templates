package com.example.templates.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Aspect1 {

    // @Pointcut("execution(public * com.example.templates..*(..))")
    // public void pointcut(){}

    @Before("execution(* AspectController.*(..))")
	public void getNameAdvice1(JoinPoint thisJoinPoint){
		for(Object arg : thisJoinPoint.getArgs()) {
			System.out.println(arg);
		}
		System.out.println("Executing Advice on getName()");
	}

	// orderby function name if duplicated join point
	@Before(value = "execution(* AspectController.*(..))")
	public void getNameAdvice10(JoinPoint thisJoinPoint){
		for(Object arg : thisJoinPoint.getArgs()) {
			System.out.println(arg);
		}
		System.out.println("0000");
	}

	@Before(value = "execution(* AspectController.*(..))")
	public void getNameAdvice1(){
		
		System.out.println("1111");
	}

	// have return
	@AfterReturning("execution(* AspectController.*(..))")
	public void getNameAdvice3(){
		System.out.println("returng");
	}

	// finally
	@After("execution(* AspectController.*(..))")
	public void getNameAdvice2(){
		System.out.println("After");
	}

	@AfterThrowing("execution(* AspectController.*(..))")
	public void getNameAdvice4(){
		System.out.println("exception");
	}
	
	@Around("execution(* AspectController.*(..))")
	public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
		System.out.println("Before invoking getName() method");
		Object value = null;
		try {
			value = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("After invoking getName() method. Return value="+value);
		return value + "asd";
	}

	@Pointcut("execution(* *..AspectController.testObject(..))")
	public void getNamePointcut() {
		System.out.println("pointcut");
	}

	@After("execution(* *..AspectController.get*(String))")
	public void getNamePointcut2() {
		System.out.println("pointcut222");
	}

	@After("execution(* *..AspectController.testObject(..))")
	public void getNamePointcut3() {
		System.out.println("testobject");
	}

	
	public void repositoryClassMethods() {
		System.out.println("PPPPPPPPPPPPP cut");
	}

	// Pointcut liminizes the scope of joinpoints
	// https://stackoverflow.com/questions/15447397/spring-aop-whats-the-difference-between-joinpoint-and-pointcut
	@Pointcut("within(@com.example.templates.aop.AspectController)")
	@After("execution(* *..templates..*(..))")
	public void getNamePointcut4() {
		System.out.println("allll");
	}

	@Before("@annotation(AspectAnnotation)")
	public void myAdvice(){
		System.out.println("Executing AspectAnnotation!!");
	}

	// @Before("args(TestObject)")
	// public void logStringArguments(TestObject x){
	// 	System.out.println("String argument passed="+x);
	// }
}
