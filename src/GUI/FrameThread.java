package GUI;

public class FrameThread extends Thread{

	 public  void run() {
	      
	       int n =  0 ; 
	       while (n++ <  60) {
	          try {
	      
	            Thread.sleep(1000) ;
	         }  catch (InterruptedException e) {
	         }
	      }
	   }
}
