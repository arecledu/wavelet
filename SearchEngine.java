import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class SearchEngineHandler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> arrayList = new ArrayList<String>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/add")) {
            String[] query = url.getQuery().split("=");
            if (query.length <= 1)
                return "Invalid query";
            if (query[0].equals("s")) {
                arrayList.add(query[1]);
                return "Added " + query[1] + " to the list";
            }
        }
        else if (url.getPath().equals("/search")) {
            String[] query = url.getQuery().split("=");
            if (query.length <= 1)
                return "Invalid query";
            if (query[0].equals("s")) {
                StringBuilder output = new StringBuilder();
                for (String str : arrayList) {
                    if (str.contains(query[1])) {}
                        output.append(str + " ");
                }
                return output.toString();
            }
        }
        return "404 Not Found!";
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new SearchEngineHandler());
    }
}
