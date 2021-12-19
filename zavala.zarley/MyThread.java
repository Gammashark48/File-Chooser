import java.io.*;
import java.lang.*;
import java.util.Scanner;
import animal.carnivore.*;
import animal.herbivore.*;
import animal.*;
import java.util.ArrayList;

class MyThread implements Runnable {

	public String threadName;
	private String filePathWithName;
	private ArrayList<Animal> animalList = new ArrayList<Animal>();
	Thread thrd;

	public MyThread(String threadName, String fileEntryPoint, String fileName, int theGiraffeNum, int theLionNum) {
		thrd = new Thread(this, threadName);
		filePathWithName = fileName;
		fileEntry = fileEntryPoint;
		giraffeNum = theGiraffeNum;
		lionNum = theLionNum;
	}

	public static MyThread createAndStart(String threadName, String fileEntry, String filePathWithName, int giraffeNum, int lionNum) {
		MyThread myThrd = new MyThread(threadName, fileEntry, filePathWithName, giraffeNum, lionNum);

	myThrd.thrd.start();
	return myThrd;
	}

	public void run() {
		int numOfLions = lionNum;
		int numOfGiraffes = giraffeNum;
		String ani;

		try(BufferedReader bookworm = new BufferedReader(new FileReader(fileEntry))) {
			for(int i = 0; i < numOfGiraffes; i++) {
				ani = bookworm.readLine();
				animalList.add(new Giraffe(ani));
			}			
		}
		catch(IOException exc) {
			System.out.println("I/O Error: " + exc);
		}			
		
		try(BufferedReader bookworm = new BufferedReader(new FileReader(fileEntry))) {
			for(int i = 0; i < numOfLions; i++) {
				ani = bookworm.readLine();
				animalList.add(new Lion(ani));
			}			
		}
		catch(IOException exc) {
			System.out.println("I/O Error: " + exc);
		}
		
		animalList.trimToSize();

		try(FileWriter journalist = new FileWriter(filePathWithName)) {
			for(Animal thisAnimal : animalList) {
    				journalist.write(thisAnimal.getName() + " is a " + thisAnimal.getType() + "\n");
			}
		}
		catch(IOException exc) {
			System.out.println("I/O Error: " + exc);
		}
	}

}