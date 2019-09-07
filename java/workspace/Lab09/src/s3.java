import java.util.*;
import java.io.*;
public class s3 {
	ArrayList<info> list = new ArrayList<info>();
	Scanner s = new Scanner(System.in);
	String id;
	String psw;
	String com;
	int input,i;
	void write(String some, int count){
		if(count==1){
			this.id = some;
		}
		else if(count==2){
			this.psw = some;
		}
		else{
			this.com = some;
			list.add(new info(id,psw,com));
		}
	}
	void open(File file){
		list.clear();
	    try {
	        BufferedReader in = new BufferedReader(new FileReader(file));
	        String some;
	        int count=1;
	        while ((some = in.readLine()) != null) {
	          write(some,count);
	          count++;
	          if(count==4){
	        	  count=1;
	          }
	        }
	        in.close();
	      } catch (IOException q) {
	      }
	}
	void save(File file){
		BufferedWriter bw = null;
		try{
			bw = new BufferedWriter(new FileWriter(file, false));
			
			String something = null;
			
			for(i=0;i<list.size();i++){
				something = list.get(i).id;
				bw.write(something);
				bw.newLine();
				something = list.get(i).psw;
				bw.write(something);
				bw.newLine();
				something = list.get(i).com;
				bw.write(something);
				bw.newLine();
			}
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(bw != null) {try{bw.close();}catch(IOException e){}}
		}
	}
	void Delete(String text){
		for(i=0;i<list.size();i++){
			if(list.get(i).com.equals(text)){
				list.remove(i);
			}
		}
	}
	
}
