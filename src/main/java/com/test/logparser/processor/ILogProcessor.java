/**
 * 
 */
package com.test.logparser.processor;


/**
 * @author pakumar
 *
 */
@FunctionalInterface
public interface ILogProcessor {
	
	public void process(String filePath);
	
}
