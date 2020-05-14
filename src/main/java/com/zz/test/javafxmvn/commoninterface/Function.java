package com.zz.test.javafxmvn.commoninterface;

import java.util.Objects;


/**
 * 
 * <note>
 * Desc： 研读函数式接口。1》首先必须只有一个抽象方法。即没有方法体的方法，如下abstract R apply(T t)或者//R apply(T t);、
 * 2》jdk1.8后，可以有default，static方法。解决接口改造，需要其实现类也改造的问题。
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-24 12:08:38
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-24 12:08:38    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
@FunctionalInterface
public interface Function<T, R> {

    /**
     * Applies this Function to the given argument.
     *
     * @param t the Function argument
     * @return the Function result
     */
    abstract R apply(T t);
    // R apply(T t);
    
    

    /**
     * Returns a composed Function that first applies the {@code before}
     * Function to its input, and then applies this Function to the result.
     * If evaluation of either Function throws an exception, it is relayed to
     * the caller of the composed Function.
     *
     * @param <V> the type of input to the {@code before} Function, and to the
     *           composed Function
     * @param before the Function to apply before this Function is applied
     * @return a composed Function that first applies the {@code before}
     * Function and then applies this Function
     * @throws NullPointerException if before is null
     *
     * @see #andThen(Function)
     */
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * Returns a composed Function that first applies this Function to
     * its input, and then applies the {@code after} Function to the result.
     * If evaluation of either Function throws an exception, it is relayed to
     * the caller of the composed Function.
     *
     * @param <V> the type of output of the {@code after} Function, and of the
     *           composed Function
     * @param after the Function to apply after this Function is applied
     * @return a composed Function that first applies this Function and then
     * applies the {@code after} Function
     * @throws NullPointerException if after is null
     *
     * @see #compose(Function)
     */
    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * Returns a Function that always returns its input argument.
     *
     * @param <T> the type of the input and output objects to the Function
     * @return a Function that always returns its input argument
     */
     static <T> Function<T, T> identity() { //该静态方法，返回是 Function<T,T>,而lambuda表达式是是对abstract R apply(T t) 的实现。因此 表示传入什么返回什么，即传入T,返回T(得T=R),
    	 									//因此dentity()方法是对Function<T, R>的一个实例化,且T=R。
        return t -> t;
    }
    
     static String a() {
    	 System.out.println(11);
    	 return "aa";
     }
 

}
