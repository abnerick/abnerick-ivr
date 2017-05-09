package com.github.abnerick;

import org.asteriskjava.fastagi.AgiChannel;
import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiOperations;
import org.asteriskjava.fastagi.AgiRequest;
import org.asteriskjava.fastagi.BaseAgiScript;

import com.github.abnerick.models.Call;

public class ObeliskAgiScript extends BaseAgiScript
{
	private final String customAudioPath = "custom/obelisk/";
	private final Integer timeout = 5000;
	
    public void service(AgiRequest request, AgiChannel channel)
    {
    	Call call = new Call();
    	    	
    	try {
    		// Answer the channel...
			answer();
			this.executeAgiScript(this, call);
		} 
    	catch (AgiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   	
    }
    
    public void executeAgiScript(AgiOperations opts, Call call) throws AgiException
    {    	
    	String input = null;
    	
        // welcome.wav  welcome :v
        opts.streamFile(customAudioPath + "welcome");
      
        // question.wav  do you want to know about asterisk-obelisk?  press 1 if yes,  press 2 if no
        input = opts.getData(customAudioPath + "question", timeout, 1);

        if(input != null && !input.isEmpty())
        {        	
        	// selected.wav  you selected
        	opts.streamFile(customAudioPath + "selected");
            opts.sayDigits(input);
            
            if(input.equals("1"))
            {
            	call.setAnswer("Yes");
            }
            else
            {
            	call.setAnswer("No");
            }            
        }
        
        // goodbye.wav  good bye :v
        opts.streamFile(customAudioPath + "good-bye");
        
        // ...and hangup.
        opts.hangup();    	
    }
}