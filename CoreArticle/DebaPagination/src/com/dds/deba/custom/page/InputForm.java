package com.dds.deba.custom.page;

import org.apache.struts.action.ActionForm;

/**
 * This class is used for
 * the action for for the struts
 * application.
 * @author Debadatta Mishra(PIKU)
 *
 */
public class InputForm extends ActionForm 
{
	private static final long serialVersionUID = 1L;
	private String inputText = null;
	
	public String getInputText() {
		return inputText;
	}
	public void setInputText(String inputText) {
		this.inputText = inputText;
	}
	

}
