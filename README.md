
# abnerick-ivr

Welcome!
This project shows how to setup [asterisk](http://asterisk-java.org) + [JUnit](http://junit.org/junit4/) + [Mockito](http://site.mockito.org/) to test an Interactive Voice Response (IVR) Application


# Environment

ubuntu 	         
[Ubuntu Server](https://www.ubuntu.com/download/server) Virtual Box VM which contain our PBX Server  
ubuntu:/media/files is the mounting point for obelisk:/*path-to*/files  
audio files are in /var/lib/asterisk/sounds/custom which using a symlink custom -> /media/files 

obelisk        
Where is placed our asterisk-java application (IVR), and the audio files. BTW I called from this computer using [Jitsi](https://jitsi.org/)  
obelisk:/*path-to*/files  audio files directory shared using NFS

That's it

# Application 

I created this simple IVR app on [Eclipse](https://eclipse.org/) using [Maven](https://maven.apache.org/) to handle dependencies
 
 ![callflow!](https://abnerick.files.wordpress.com/2017/05/flowchart.png)

Call flow chart thanks to [Draw.io](https://www.draw.io/) 

The audio files were created using the Chrome [Text to Speech App](https://chrome.google.com/webstore/detail/text-to-speech-app/foboeiajimhaijdbfnknapkoiadkohio/related?utm_source=chrome-ntp-icon), and then converting the .mp3 to .wav using
`ffmpeg -i input.mp3 -ar 8000 -ac 1 output.wav`


# Testing 

Our goal is test the call flow to know if when the caller answer is 1, call.answer is "Yes". 

	// we create a AgiOperations mock and set an expected behaviour
	Mockito.when(opts.getData("custom/question", 5000, 1)).thenReturn("1"); 
	// we execute our app
	agi.executeAgiScript(opts, call); 
	// we check our results
    assertEquals(call.getAnswer(), "Yes");
    	
Here we mock AgiOperations to check the call flow but you should read about [Mock types you don't own](https://github.com/mockito/mockito/wiki/How-to-write-good-tests#dont-mock-type-you-dont-own)     
    

# Clone 
* Clone the repo
* Build and run the main class com.github.abnerick.Server
* Setup the audio files in the PBX server, and copy src/main/resources/audio content
* Setup an extension in the PBX Server, editing etc/extensions.conf  
`exten=>6003,1,Agi(agi://ip-where-is-your-app/obelisk.agi)`
* Setup Jitsi to call your PBX Server and use the IVR extension (for this example 6003) 







