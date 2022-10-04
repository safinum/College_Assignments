import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Process;

public class Pro {
    
    public String out (String[] args) {
        try {
            Runtime r = Runtime.getRuntime();
            String[] cmd = new String[args.length + 1];
            cmd[0] = "./main.out";

            for (int i = 0; i < args.length; i++) {
                cmd[i + 1] = args[i];
            }

            Process proc = r.exec(cmd);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            String s = null;
            String output = "";

            while ((s = stdInput.readLine()) != null) {
                output += s + "\n";
            }
            return output;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
    }
}
