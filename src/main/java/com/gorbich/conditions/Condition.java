package com.gorbich.conditions;

public interface Condition<T> {
	boolean test(T t);
}
