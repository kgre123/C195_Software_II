package helper;

import dbConnections.DBUser;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Log {

    public static void logAttempts(String username, String password){

        BufferedWriter bw = null;

        try{
            bw = new BufferedWriter(new FileWriter("login_activity.txt", true));

            boolean login = DBUser.userLogin(username, password);

            if(login){
                String success = "User: " + username + " Successfully logged in at: " + LocalDateTime.now() + "\n";
                bw.write(success);
            }

            else {
                String failure = "User: " + username + " Failed to log in at: " + LocalDateTime.now() + "\n";
                bw.write(failure);
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        finally {

            try{
                if(bw!=null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
