package com.github.abnerick;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.asteriskjava.fastagi.AgiException;
import org.asteriskjava.fastagi.AgiOperations;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.abnerick.models.Call;

public class ObeliskAgiScriptTest {
	
	@Test
	public void test() throws AgiException, IOException
	{		
		ObeliskAgiScript agi = new ObeliskAgiScript();
		AgiOperations opts = Mockito.mock(AgiOperations.class);
		Call call = new Call();
		
		// setting a behavior
		Mockito.when(opts.getData("custom/obelisk/question", 5000, 1)).thenReturn("1");
		agi.executeAgiScript(opts, call);

	    Mockito.verify(opts).getData("custom/obelisk/question", 5000, 1); 
	    
	    assertEquals(call.getAnswer(), "Yes");		    
	}

}
