import java.io.IOException;
import java.net.URI;
import java.util.*;

class Handler implements URLHandler {
    //Initiate arraylists to contain a list of strings. 
    ArrayList<String> ListofString = new ArrayList<String>();
    ArrayList<String> ListofSubString = new ArrayList<String>();

    public String handleRequest(URI url) {
        //Display Strings in the list. 
        if (url.getPath().equals("/")) {
            return String.format("String(s) in the list: %s", ListofString.toString());
        } 
        else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] query = url.getQuery().split("=");
                if (query[0].equals("s")) {   
                    ListofString.add(query[1]);
                    return  String.format("String added: %s. String(s) now in the list: %s", query[1], ListofString.toString());
                }
            }
            else if (url.getPath().contains("/search")){
                String[] query = url.getQuery().split("="); 
                if (query[0].equals("s")) {
                    for(String str:ListofString){   
                        if(str.contains(query[1])){
                            ListofSubString.add(str);
                        }
                    }
                    return  String.format("String(s) that contain(s) this substring: %s ", ListofSubString.toString());
                }
            }
            return "404 Not Found!";
        
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
